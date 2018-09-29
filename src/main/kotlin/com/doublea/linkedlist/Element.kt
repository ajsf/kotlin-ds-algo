package com.doublea.linkedlist

data class Element<T : Comparable<T>>(val data: T, var next: Element<T>? = null)