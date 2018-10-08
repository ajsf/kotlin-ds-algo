package com.doublea.tree

import com.doublea.queue.CircularQueue
import com.doublea.stack.Stack

fun <T> Node<T>.breadthFirstTraversal(): List<T> {
    val nodesToTraverse = CircularQueue<Node<T>>()
    nodesToTraverse.enqueue(this)
    val result = mutableListOf<T>()
    while (nodesToTraverse.isEmpty().not()) {
        val currentNode = nodesToTraverse.dequeue()
        currentNode.left?.let {
            nodesToTraverse.enqueue(it)
        }
        currentNode.right?.let {
            nodesToTraverse.enqueue(it)
        }
        result.add(currentNode.data)
    }
    return result
}

fun <T> Node<T>.depthFirstPreOrder(): List<T> {
    val nodesToTraverse = Stack<Node<T>>()
    nodesToTraverse.push(this)
    val result = mutableListOf<T>()
    while (nodesToTraverse.isEmpty().not()) {
        val currentNode = nodesToTraverse.pop()
        currentNode.right?.let {
            nodesToTraverse.push(it)
        }
        currentNode.left?.let {
            nodesToTraverse.push(it)
        }
        result.add(currentNode.data)
    }
    return result
}

fun <T> Node<T>.depthFirstPreOrderRecursive(): List<T> {
    val result = mutableListOf<T>()
    result.add(data)
    left?.let {
        result.addAll(it.depthFirstPreOrderRecursive())
    }
    right?.let {
        result.addAll(it.depthFirstPreOrderRecursive())
    }
    return result
}

fun <T> Node<T>.depthFirstInOrder(): List<T> {
    val result = mutableListOf<T>()
    left?.let {
        result.addAll(it.depthFirstInOrder())
    }
    result.add(data)
    right?.let {
        result.addAll(it.depthFirstInOrder())
    }
    return result
}

fun <T> Node<T>.depthFirstPostOrder(): List<T> {
    val result = mutableListOf<T>()
    left?.let {
        result.addAll(it.depthFirstPostOrder())
    }
    right?.let {
        result.addAll(it.depthFirstPostOrder())
    }
    result.add(data)
    return result
}