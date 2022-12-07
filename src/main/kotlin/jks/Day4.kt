package jks

import java.io.File
import java.lang.RuntimeException

fun main() {
    val uri = object {}::class.java.getResource("/day4_input")?.toURI()
        ?: throw RuntimeException("oh noes!")
    val lines = File(uri).readLines()
    println("Part 1: ${part1(lines)}")
}

private fun part1(lines: List<String>) =
    lines.map(::pairs)
        .map(::ranges)
        .count { oneFullyContainsOther(it[0], it[1]) }

private fun pairs(line: String) = line.split(",")

private fun ranges(pair: List<String>) = pair.map { elves ->
    elves.split("-").let { elf ->
        elf[0].toInt()..elf[1].toInt()
    } }

private fun oneFullyContainsOther(a: IntRange, b: IntRange) =
    a.fullyContains(b) || b.fullyContains(a)

private fun IntRange.fullyContains(other: IntRange) = this.all { other.contains(it) }
