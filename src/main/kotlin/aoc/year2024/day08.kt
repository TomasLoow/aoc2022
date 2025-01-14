package aoc.year2024

import DailyProblem
import aoc.utils.*
import aoc.utils.extensionFunctions.allUnorderedPairs
import aoc.utils.extensionFunctions.mutateImp
import aoc.utils.geometry.Array2D
import aoc.utils.geometry.Coord
import kotlin.time.ExperimentalTime

class Day08Problem : DailyProblem<Int>() {

    override val number = 8
    override val year = 2024
    override val name = "Resonant Collinearity"

    private lateinit var map: Array2D<Char>
    private lateinit var antennas: MutableMap<Char, MutableList<Coord>>

    override fun commonParts() {
        map = parseCharArray(getInputText())
        antennas = emptyMutableMap()
        map.mapAndFilterToListByNotNull { coord, c -> c to coord }.forEach { (c, coord) ->
            if (c != '.') {
                antennas.mutateImp(c, mutableListOf<Coord>()) { it.add(coord) }
            }
        }
    }


    override fun part1(): Int {
        val res = mutableSetOf<Coord>()
        for (char in antennas.keys) {
            val coords = antennas[char]!!
            coords.allUnorderedPairs().forEach { (a, b) ->
                getAntinodesPart1(a, b).filter { it in map }.forEach { antinode ->
                    res.add(antinode)
                }
            }
        }
        return res.size
    }

    private fun getAntinodesPart1(a: Coord, b: Coord): Sequence<Coord> {
        val diff = a - b
        return sequenceOf(a + diff, b - diff)
    }

    private fun getAntinodesPart2(a: Coord, b: Coord): Sequence<Coord> {
        val diff = a - b
        val s1 = sequence<Coord> {
            // Walking in one direction...
            var tmp = a
            while (true) {
                yield(tmp)
                tmp += diff
            }
        }.takeWhile { it in map }
        val s2 = sequence<Coord> {
            // Walking in the other direction...
            var tmp = b
            while (true) {
                yield(tmp)
                tmp -= diff
            }
        }.takeWhile { it in map }
        return s1 + s2
    }

    override fun part2(): Int {
        val res = mutableSetOf<Coord>()
        for (char in antennas.keys) {
            val coords = antennas[char]!!
            coords.allUnorderedPairs().forEach { (a, b) ->
                getAntinodesPart2(a, b).forEach { antinode ->
                    res.add(antinode)
                }
            }
        }
        return res.size
    }
}

val day08Problem = Day08Problem()

@OptIn(ExperimentalTime::class)
fun main() {
    day08Problem.runBoth(100)
}