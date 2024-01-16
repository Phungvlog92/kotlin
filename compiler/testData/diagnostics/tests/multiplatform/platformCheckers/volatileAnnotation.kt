// WITH_STDLIB
// MODULE: common
import kotlin.reflect.KProperty
@Target(AnnotationTarget.FIELD)
expect annotation class <!NO_ACTUAL_FOR_EXPECT!>Ann<!>()

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String = "OK"

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {}
}

class Test {
    @Ann
    var x: String = ""

    <!VOLATILE_ON_VALUE{JVM}!>@Ann<!>
    val y: String = ""

    <!WRONG_ANNOTATION_TARGET, WRONG_ANNOTATION_TARGET{JVM}!>@Ann<!>
    var z: String by Delegate()
}

// MODULE: platform()()(common)
actual typealias Ann = kotlin.jvm.Volatile
