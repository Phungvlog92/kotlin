// MODULE: common
@Repeatable
expect annotation class <!NO_ACTUAL_FOR_EXPECT!>A1<!>

@Repeatable
expect annotation class <!NO_ACTUAL_FOR_EXPECT, NO_ACTUAL_FOR_EXPECT{JVM}, PACKAGE_OR_CLASSIFIER_REDECLARATION{JVM}!>A2<!>

expect annotation class <!NO_ACTUAL_FOR_EXPECT, NO_ACTUAL_FOR_EXPECT{JVM}, PACKAGE_OR_CLASSIFIER_REDECLARATION{JVM}!>A3<!>


// MODULE: platform()()(common)

<!REPEATABLE_ANNOTATION_HAS_NESTED_CLASS_NAMED_CONTAINER_ERROR!>@Repeatable<!>
actual annotation class A1 {
    class Container
}

expect annotation class <!NO_ACTUAL_FOR_EXPECT, PACKAGE_OR_CLASSIFIER_REDECLARATION!>A2<!> {
    class Container
}

<!REPEATABLE_ANNOTATION_HAS_NESTED_CLASS_NAMED_CONTAINER_ERROR!>@Repeatable<!>
expect annotation class <!NO_ACTUAL_FOR_EXPECT, PACKAGE_OR_CLASSIFIER_REDECLARATION!>A3<!> {
    class Container
}
