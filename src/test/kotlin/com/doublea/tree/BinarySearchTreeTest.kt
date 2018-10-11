package com.doublea.tree

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

private val sortedArray = intArrayOf(1, 2, 3, 4, 6, 7, 8, 9, 10)

internal class BinarySearchTreeRecursiveTest : StringSpec({
    val list = sortedArray.toMutableList()

    "it inserts items in sorted order" {
        val tree = BinarySearchTree<Int>()
        tree.insert(5)
        tree.head?.breadthFirstTraversal() shouldBe listOf(5)
        tree.insert(2)
        tree.head?.breadthFirstTraversal() shouldBe listOf(5, 2)
        tree.insert(9)
        tree.head?.breadthFirstTraversal() shouldBe listOf(5, 2, 9)
        tree.insert(4)
        tree.head?.breadthFirstTraversal() shouldBe listOf(5, 2, 9, 4)
        tree.head?.depthFirstPreOrder() shouldBe listOf(5, 2, 4, 9)
        tree.head?.depthFirstInOrder() shouldBe listOf(2, 4, 5, 9)
        tree.insert(7)
        tree.head?.breadthFirstTraversal() shouldBe listOf(5, 2, 9, 4, 7)
        tree.head?.depthFirstPreOrder() shouldBe listOf(5, 2, 4, 9, 7)
        tree.head?.depthFirstInOrder() shouldBe listOf(2, 4, 5, 7, 9)
    }

    "lookup finds items" {
        val tree = BinarySearchTree<Int>()
        tree.lookup(5) shouldBe null
        tree.insert(5)
        tree.lookup(5) shouldBe 5
        tree.insert(2)
        tree.lookup(2) shouldBe 2
        tree.insert(9)
        tree.lookup(9) shouldBe 9
    }

})
