package aoc.year2015

import DailyProblem
import aoc.utils.geometry.Coord
import aoc.utils.geometry.Direction
import aoc.utils.parseDirectionFromArrow
import kotlin.time.ExperimentalTime

class Day03Problem : DailyProblem<Int>() {

    override val number = 3
    override val year = 2015
    override val name = "Perfectly Spherical Houses in a Vacuum"

    private lateinit var data: List<Direction>

    private fun parseFile(): List<Direction> {
        return getInputText().map(::parseDirectionFromArrow)
    }

    override fun commonParts() {
        data = parseFile()
    }


    override fun part1(): Int {
        return data
            .runningFold(Coord.origin) { pos, dir -> pos.stepInDir(dir) }
            .toSet()
            .size
    }

    override fun part2(): Int {
        var pos1 = Coord.origin
        var pos2 = Coord.origin
        val seen = mutableSetOf(pos1)
        data.forEach { dir ->
            val temp = pos1.stepInDir(dir)
            pos1 = pos2
            pos2 = temp
            seen.add(pos2)
        }
        return seen.size
    }
}


val day03Problem = Day03Problem()

@OptIn(ExperimentalTime::class)
fun main() {
    day03Problem.runBoth(10000)
}