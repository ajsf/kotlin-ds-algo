package com.doublea.linkedlist

class LinkedList<T : Comparable<T>> : Cloneable {

    private var head: Element<T>? = null
    public var count = 0

    fun countNodes(): Int {
        var count = 0
        var current = head
        while (current != null) {
            count++
            current = current.next
        }
        return count
    }

    fun prepend(data: T) {
        val newNode = Element(data)
        newNode.next = head
        head = newNode
        count++
    }

    fun append(data: T) {
        val newNode = Element(data)
        var current = head
        if (current == null) {
            head = newNode
        } else {
            while (current?.next != null) {
                current = current.next
            }
            current?.next = newNode
        }
        count++
    }

    fun removeAt(index: Int) {
        if (index == 0) {
            head = head?.next
            count--
            return
        }
        var current = head
        var prev = head
        for (i in 1..index) {
            prev = current
            current = current?.next
            if (current == null) throw IndexOutOfBoundsException("$index is greater than the length of the list")
        }
        println("Prev: $prev, Current: $current")
        prev?.next = current?.next
        count--

    }

    fun valueAt(index: Int): T {
        head?.let {
            var current = it
            for (i in 0 until index) {
                if (current.next == null) throw IndexOutOfBoundsException("$index is greater than the length of the list")
                current = current.next!!
            }
            return current.data
        }
        throw IndexOutOfBoundsException("$index is greater than the length of the list")
    }

    fun contains(data: T): Boolean {
        var current = head
        while (current != null) {
            if (current.data == data) return true
            current = current.next
        }
        return false
    }

    fun printList() {
        var current = head
        while (current != null) {
            println(current.data)
            current = current.next
        }
    }

    fun pop(): T? {
        head?.let {
            val data = it.data
            head = it.next
            return data
        }
        return null
    }
}