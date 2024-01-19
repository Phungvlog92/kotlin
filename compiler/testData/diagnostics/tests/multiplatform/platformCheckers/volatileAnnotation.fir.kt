// WITH_STDLIB
// MUTE_LL_FIR: platform checkers in AA are run in common session by design

// MODULE: common
import kotlin.reflect.KProperty
@Target(AnnotationTarget.FIELD)
expect annotation class Ann()

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String = "OK"

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {}
}

class Test {
    @Ann
    var x: String = ""

    <!VOLATILE_ON_VALUE!>@Ann<!>
    val y: String = ""

    <!VOLATILE_ON_DELEGATE, WRONG_ANNOTATION_TARGET!>@Ann<!>
    var z: String by Delegate()
}

// MODULE: platform()()(common)
actual typealias Ann = kotlin.jvm.Volatile
