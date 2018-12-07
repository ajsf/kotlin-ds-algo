package com.doublea.graph

import java.util.*
import kotlin.Comparator

abstract class WeightedGraph : Graph() {
    abstract fun addEdge(v1: Int, v2: Int, weight: Int)

    override fun shortestPath(v1: Int, v2: Int): List<Int> = shortestPath(v1, v2, ::createDistanceTable)

    private fun shortestPath(v1: Int, v2: Int, distanceFunction: (Int) -> Map<Int, Graph.DistanceTableEntry>): List<Int> {
        if (v1 >= numVertices || v1 < 0) throw IllegalArgumentException("Argument v1 is invalid vertex: $v1")
        if (v2 >= numVertices || v2 < 0) throw IllegalArgumentException("Argument v2 is invalid vertex: $v2")

        val distanceTable = distanceFunction(v1)
        return distanceTable.getPath(v1, v2)
    }

    private fun createDistanceTable(v1: Int): Map<Int, Graph.DistanceTableEntry> {
        val distanceTable = createDistanceTable(numVertices, Int.MAX_VALUE, v1)

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


    fun bellmanFordShortestPath(v1: Int, v2: Int): List<Int> = shortestPath(v1, v2, ::bellmanFordDistanceTable)

    private fun bellmanFordDistanceTable(v1: Int): Map<Int, Graph.DistanceTableEntry> {
        val distanceTable = createDistanceTable(numVertices, 100_000, v1)

        repeat(numVertices - 1) {
            val visited = mutableSetOf<WeightedEdge>()

            repeat(numVertices) { v ->
                getAdjacentEdges(v).forEach { edge ->
                    if (visited.contains(edge).not()) {
                        visited.add(edge)
                        distanceTable[edge.toVertex]?.let { entry ->
                            val newDistance = distanceTable[v]!!.distance + edge.weight
                            if (newDistance < entry.distance) {
                                entry.distance = newDistance
                                entry.lastVertex = v
                            }
                        }
                    }
                }
            }
        }

        repeat(numVertices) {
            getAdjacentEdges(it).forEach { edge ->
                distanceTable[edge.toVertex]?.let { entry ->
                    val newDistance = distanceTable[it]!!.distance + edge.weight
                    if (newDistance < entry.distance) throw IllegalArgumentException("The graph has a negative cycle")
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