package com.doublea.graph

interface WeightedGraph : Graph {
    fun addEdge(v1: Int, v2: Int, weight: Int)
}
