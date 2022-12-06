package aoc2022

import DailyProblem
import java.io.File
import kotlin.time.ExperimentalTime
import utils.*

class Day4Problem(override val inputFilePath: String) : DailyProblem<Int> {

    override val number = 4
    override val name = "Camp Cleanup"

    private lateinit var data: List<Pair<IntRange, IntRange>>

    private fun parseFile(): List<Pair<IntRange, IntRange>> {
        val cleanupLineRegEx = """(\d+)-(\d+),(\d+)-(\d+)""".toRegex()
        return File(inputFilePath)
            .readLines()
            .filter { line -> line.isNotEmpty() }
            .map { line ->
                val (elf1Start, elf1End, elf2Start, elf2End) = cleanupLineRegEx
                    .matchEntire(line)
                    ?.destructured
                    ?: throw IllegalArgumentException("Failed to parse input line $line")
                Pair(
                    IntRange(elf1Start.toInt(), elf1End.toInt()),
                    IntRange(elf2Start.toInt(), elf2End.toInt())
                )
            }
    }

    override fun commonParts() {
        this.data = parseFile()
    }
    override fun part1(): Int {
        return data.count { (elf1, elf2) ->
            elf1.containsRange(elf2) ||elf2.containsRange(elf1)
        }
    }

    override fun part2(): Int {
        return data.count { (elf1, elf2) ->
            elf1.intersectRange(elf2)
        }
    }
}



val day4Problem = Day4Problem("input/aoc2022/day4.txt")

@OptIn(ExperimentalTime::class)
fun main() {
    day4Problem.runBoth(10)
}