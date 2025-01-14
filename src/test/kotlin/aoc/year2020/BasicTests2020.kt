package aoc.year2020

import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class BasicTests2020 {

    @Test
    fun testDay1() {
        val problem = Day01Problem()
        problem.testData = true
        problem.commonParts()
        assertEquals(514579, problem.part1())
        assertEquals(241861950, problem.part2())
    }

    @Test
    fun testDay2() {
        val problem = Day02Problem()
        problem.testData = true
        problem.commonParts()
        assertEquals(2, problem.part1())
        assertEquals(1, problem.part2())
    }

    @Test
    fun testDay3() {
        val problem = Day03Problem()
        problem.testData = true
        problem.commonParts()
        assertEquals(7, problem.part1())
        assertEquals(336, problem.part2())
    }

    @Test
    fun testDay5() {
        val problem = Day05Problem()
        problem.testData = true
        problem.commonParts()
        assertEquals(820, problem.part1())
    }

    @Test
    fun testDay9() {
        val problem = Day09Problem()
        problem.testData = true
        problem.commonParts()
        assertEquals(127, problem.part1())
        assertEquals(62, problem.part2())
    }

    @Test
    fun testDay10() {
        val problem = Day10Problem()
        problem.testData = true
        problem.commonParts()
        assertEquals(220, problem.part1())
        assertEquals(19208, problem.part2())
    }

    @Test
    fun testDay13() {
        val problem = Day13Problem()
        problem.testData = true
        problem.commonParts()
        assertEquals(295.toBigInteger(), problem.part1())
        assertEquals(1068781.toBigInteger(), problem.part2())
    }

    @Test
    fun testDay19() {
        val problem = Day19Problem()
        problem.testData = true
        problem.commonParts()
        assertEquals(3, problem.part1())
        assertEquals(12, problem.part2())
    }
    @Test
    fun testDay24() {
        val problem = Day24Problem()
        problem.testData = true
        problem.commonParts()
        assertEquals(10, problem.part1())
        assertEquals(2208, problem.part2())
    }

}
