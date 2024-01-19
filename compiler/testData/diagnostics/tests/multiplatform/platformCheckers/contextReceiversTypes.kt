// LANGUAGE: +ContextReceivers
// MUTE_LL_FIR: platform checkers in AA are run in common session by design

// MODULE: common
expect interface <!NO_ACTUAL_FOR_EXPECT!>A<!>
expect interface <!NO_ACTUAL_FOR_EXPECT!>B<!>

<!SUBTYPING_BETWEEN_CONTEXT_RECEIVERS{JVM}!>context(A, B)<!>
fun test_1() {}

fun test_2(f: <!SUBTYPING_BETWEEN_CONTEXT_RECEIVERS{JVM}!>context(A, B)<!> () -> Unit) {}

// MODULE: platform()()(common)
interface C
actual typealias A = C
actual typealias B = C

<!SUBTYPING_BETWEEN_CONTEXT_RECEIVERS!>context(A, B)<!>
fun test_3() {}

fun test_4(f: <!SUBTYPING_BETWEEN_CONTEXT_RECEIVERS!>context(A, B)<!> () -> Unit) {}
