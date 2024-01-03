# Interaction of inlining with typeOf function

[typeOf](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/type-of.html) function is a standard
library function, which gets type argument, and returns KType, corresponding with this type argument. 

typeOf function is quite special for inliner, as it has reified type parameter, but is an intrinsic, 
not something, which can be normally inlined  

Basic case of interaction looks like the following:

```kotlin
import kotlin.reflect.*

inline fun <reified T> typeOfValue(x: T) = typeOf<T>()
fun main() {
    println(typeOfValue(1)) // prints int
    println(typeOfValue("a")) // prints java.lang.String
    println(typeOfValue(if (true) listOf(1) else mutableListOf(null))) // prints java.util.List<java.lang.Integer?>
}
```

In interaction with inline functions call-chains it can become trickier

```kotlin
inline fun <T> typeOfNonReifiedList(x: List<T>) = typeOfValue(x)

fun main() {
    println(typeOfNonReifiedList(listOf(1, 2, 3))) // prints java.util.List<T> 
}
```

Unfortunately, in that case, `T` is neither erased nor substituted. 

This enforces us doing part of typeOf processing before inlining, as there is no T in callsite context, 
while we can't process reified types, so another part must be done after inlining.

