package com.doublea.graph

import java.util.*
import kotlin.Comparator

abstract class WeightedGraph : Graph() {
    abstract fun addEdge(v1: Int, v2: Int, weight: Int)

    override fun shortestPath(v1: Int, v2: Int): List<Int> {
        if (v1 >= numVertices || v1 < 0) throw IllegalArgumentException("Argument v1 is invalid vertex: $v1")
        if (v2 >= numVertices || v2 < 0) throw IllegalArgumentException("Argument v2 is invalid vertex: $v2")

        val distanceTable = createDistanceTable(v1)
        return distanceTable.getPath(v1, v2)
    }

    private fun createDistanceTable(v1: Int): Map<Int, Graph.DistanceTableEntry> {
        val distanceTable = (0 until numVertices).map { it to Graph.DistanceTableEntry(Int.MAX_VALUE) }.toMap()
        distanceTable[v1]!!.distance = 0
        distanceTable[v1]!!.lastVertex = v1

        val toVisit = PriorityQueue<Pair<Int, Int>>(Comparator<Pair<Int, Int>> { p0, p1 -> p0.second.compareTo(p1.second) })
        toVisit.add(v1 to 0)

        while (toVisit.isNotEmpty()) {
            val v = toVisit.poll()
            val edges = getAdjacentEdges(v.first)
            edges.forEach { edge ->
                distanceTable[edge.toVertex]?.let { entry ->
                    val newDistance = v.second + edge.weight
                    if (newDistance < entry.distance) {
                        entry.distance = newDistance
                        entry.lastVertex = v.first
                        toVisit.add(edge.toVertex to newDistance)
                    }
                }
            }
        }
        return distanceTable
    }

    protected abstract fun getAdjacentEdges(v: Int): Set<WeightedEdge>

    protected data class WeightedEdge(val toVertex: Int, val weight: Int)
}

class WeightedAdjacencySetGraph(override val numVertices: Int, override val graphType: GraphType) : WeightedGraph() {

    private val adjacencySet = MutableList(numVertices) { mutableSetOf<WeightedEdge>() }

    override fun addEdge(v1: Int, v2: Int, weight: Int) {
        if (v1 >= numVertices || v1 < 0) throw IllegalArgumentException("Argument v1 is invalid vertex: $v1")
        if (v2 >= numVertices || v2 < 0) throw IllegalArgumentException("Argument v2 is invalid vertex: $v2")
        adjacencySet[v1].add(WeightedEdge(v2, weight))
        if (graphType == GraphType.UNDIRECTED) adjacencySet[v2].add(WeightedEdge(v1, weight))
    }

    override fun getAdjacentVertices(v: Int): List<Int> {
        if (v >= numVertices || v < 0) {
            throw IllegalArgumentException("Vertex number $v is invalid")
        }
        return adjacencySet[v].map { it.toVertex }.sorted()
    }

    override fun getAdjacentEdges(v: Int): Set<WeightedEdge> = adjacencySet[v]
}