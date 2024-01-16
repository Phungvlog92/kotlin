// MODULE: common
@Repeatable
expect annotation class A1

@Repeatable
expect annotation class A2

expect annotation class A3


// MODULE: platform()()(common)

<!REPEATABLE_ANNOTATION_HAS_NESTED_CLASS_NAMED_CONTAINER_ERROR!>@Repeatable<!>
actual annotation class A1 {
    class Container
}

expect annotation class A2 {
    class Container
}

<!REPEATABLE_ANNOTATION_HAS_NESTED_CLASS_NAMED_CONTAINER_ERROR!>@Repeatable<!>
expect annotation class A3 {
    class Container
}
