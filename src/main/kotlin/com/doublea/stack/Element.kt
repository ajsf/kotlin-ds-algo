package com.doublea.stack

data class Element<T>(val data: T, var next: Element<T>? = null)
