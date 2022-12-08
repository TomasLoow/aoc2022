package aoc2022

import DailyProblem
import utils.*
import java.io.File
import kotlin.time.ExperimentalTime

class Day8Problem(override val inputFilePath: String) : DailyProblem<Int> {

    override val number = 8
    override val name = "Treetop Tree House"


    private lateinit var forrestMap: Array2D<Int>

    private fun parseFile(): Array2D<Int> {
        return Array2D.parseFromLines(File(inputFilePath).readText()) { c -> c.digitToInt() }
    }

    private fun canBeSeenFromOutside(map: Array2D<Int>, pos: Coord, h: Int): Boolean {
        val (x,y) = pos
        if (map.onEdge(pos)) return true

        val visibleFromLeft = (0 until x).all { i -> map[i,y] < h }
        val visibleFromRight = (x + 1 until map.width).all { i -> map[i,y] < h }

        val visibleFromTop = (0 until y).all { i -> map[x,i] < h }
        val visibleFromBottom = (y + 1 until map.height).all { i -> map[x,i] < h }

        return visibleFromLeft || visibleFromRight || visibleFromTop || visibleFromBottom
    }

    private fun scenicScore(map: Array2D<Int>, pos: Coord, height: Int): Int {

        val (x,y) = pos
        if (map.onEdge(pos)) return 0

        var lookingUp = (y - 1 downTo 0).takeWhile { yi -> map[x,yi] < height }.count()
        var lookingLeft = (x - 1 downTo 0).takeWhile { xi -> map[xi,y] < height }.count()
        var lookingDown = (y + 1 until map.height).takeWhile { yi -> map[x,yi] < height }.count()
        var lookingRight = (x + 1 until map.width).takeWhile { xi -> map[xi,y] < height }.count()

        /* If we hit the end of the map the values are correct, otherwise add 1 to each */
        if (lookingUp != y) lookingUp++
        if (lookingLeft != x) lookingLeft++
        if (lookingDown != map.height-y-1) lookingDown++
        if (lookingRight != map.width-x-1) lookingRight++

        return lookingLeft * lookingRight * lookingUp * lookingDown
    }

    override fun commonParts() {
        forrestMap = parseFile()
    }

    override fun part1(): Int {
        return forrestMap.countIndexedByCoordinate { c, h ->
            canBeSeenFromOutside(forrestMap, c, h)
        }
    }

    override fun part2(): Int {
        return forrestMap.mapListIndexedByCoordinate { c, height ->
            scenicScore(forrestMap, c, height)
        }.maxOf { it }
    }
}


val day8Problem = Day8Problem("input/aoc2022/day8.txt")

@OptIn(ExperimentalTime::class)
fun main() {
    day8Problem.runBoth(1000)
}