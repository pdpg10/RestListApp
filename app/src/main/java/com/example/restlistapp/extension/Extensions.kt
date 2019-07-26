package com.example.restlistapp.extension

fun <T> lazyFast(initializer: () -> T): Lazy<T> =

    lazy(LazyThreadSafetyMode.NONE) { initializer() }
