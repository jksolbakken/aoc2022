package jks

import java.io.File
import java.lang.RuntimeException

fun main() {
    val uri = object {}::class.java.getResource("/day3_input")?.toURI()
        ?: throw RuntimeException("oh noes!")
    val lines = File(uri).readLines()
    println("Part 1: ${part1(lines)}")
    println("Part 2: ${part2(lines)}")
}

private fun part1(input: List<String>) =
    input.map { it.toList() }
        .map { it.chunked(it.size / 2) }
        .map { halves -> halves[0].intersect(halves[1].toSet()) }
        .flatten().sumOf { priority(it) }

private fun part2(input: List<String>) =
    input.chunked(3)
        .map(::inAll)
        .filter { it.isNotBlank() }
        .map {it[0]}
        .map(::priority)
        .sum()

private fun priority(c: Char) = with (c.code.toByte().toInt()) {
    if (this in 65..90) this - 38 else this - 96
}

private fun inAll(lines: List<String>) = lines.fold("") {
        a, b -> if (a == "") b else a.toList().intersect(b.toList().toSet()).joinToString()
}