package com.doublea.tree

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

internal class BinarySearchTreeTest : StringSpec({

    "it inserts items in sorted order" {
        val tree = BinarySearchTree<Int>()
        tree.insert(5)
        tree.breadthFirstTraversal() shouldBe listOf(5)
        tree.insert(2)
        tree.breadthFirstTraversal() shouldBe listOf(5, 2)
        tree.insert(9)
        tree.breadthFirstTraversal() shouldBe listOf(5, 2, 9)
        tree.insert(4)
        tree.breadthFirstTraversal() shouldBe listOf(5, 2, 9, 4)
        tree.depthFirstPreOrder() shouldBe listOf(5, 2, 4, 9)
        tree.depthFirstInOrder() shouldBe listOf(2, 4, 5, 9)
        tree.insert(7)
        tree.breadthFirstTraversal() shouldBe listOf(5, 2, 9, 4, 7)
        tree.depthFirstPreOrder() shouldBe listOf(5, 2, 4, 9, 7)
        tree.depthFirstInOrder() shouldBe listOf(2, 4, 5, 7, 9)
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

    "findMinimum returns the minimum value" {
        val tree = BinarySearchTree<Int>()
        tree.insert(5)
        tree.insert(2)
        tree.insert(9)
        tree.findMinimumValue() shouldBe 2
        tree.insert(1)
        tree.findMinimumValue() shouldBe 1
        tree.insert(-100)
        tree.findMinimumValue() shouldBe -100
    }

    "findMinimum returns null for an empty tree" {
        val tree = BinarySearchTree<Int>()
        tree.findMinimumValue() shouldBe null
    }

})
