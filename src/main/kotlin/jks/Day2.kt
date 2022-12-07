package jks

import java.io.File
import java.lang.RuntimeException
import jks.Item.PAPER
import jks.Item.ROCK
import jks.Item.SCISSORS
import jks.Result.DRAW
import jks.Result.LOSER
import jks.Result.WINNER

fun main() {
    val uri = object {}::class.java.getResource("/day2_input")?.toURI()
        ?: throw RuntimeException("oh noes!")
    val lines = File(uri).readLines()

    val sumPart1 = lines
        .map { chars -> Round(toItem(chars[0]), toItem(chars[2])) }
        .map { round -> Pair(round, result(round)) }
        .sumOf { (round, result) -> round.myItem.points + result.points }
    println("Part 1: $sumPart1")

    val sumPart2 = lines
        .map { chars -> Pair(toItem(chars[0]), toResult(chars[2])) }
        .map { Pair(it, itemForDesiredResult(it.first, it.second)) }
        .sumOf { (othersItemAndResult, myItem) -> myItem.points + othersItemAndResult.second.points }
    println("Part 2: $sumPart2")
}

data class Round(val opponentsItem: Item, val myItem: Item)

enum class Item(val points: Int){
    ROCK(1),
    PAPER(2),
    SCISSORS(3)
}

enum class Result(val points: Int) {
    WINNER(6),
    DRAW(3),
    LOSER(0)
}

private fun result(round: Round): Result = when (round) {
    Round(ROCK, ROCK) -> DRAW
    Round(ROCK, PAPER) -> WINNER
    Round(ROCK, SCISSORS) -> LOSER
    Round(PAPER, ROCK) -> LOSER
    Round(PAPER, PAPER) -> DRAW
    Round(PAPER, SCISSORS) -> WINNER
    Round(SCISSORS, ROCK) -> WINNER
    Round(SCISSORS, PAPER) -> LOSER
    Round(SCISSORS, SCISSORS) -> DRAW
    else -> throw RuntimeException("$round is not valid")
}

private fun toItem(c: Char) = when (c) {
    'A', 'X' -> ROCK
    'B', 'Y' -> PAPER
    'C', 'Z' -> SCISSORS
    else -> throw RuntimeException("oh noes!")
}

private fun toResult(c: Char) = when (c) {
    'X' -> LOSER
    'Y' -> DRAW
    'Z' -> WINNER
    else -> throw RuntimeException("oh noes!")
}

private fun itemForDesiredResult(othersItem: Item, desiredResult: Result) = when {
    desiredResult == DRAW -> othersItem
    othersItem == ROCK && desiredResult == WINNER -> PAPER
    othersItem == ROCK && desiredResult == LOSER -> SCISSORS
    othersItem == PAPER && desiredResult == WINNER -> SCISSORS
    othersItem == PAPER && desiredResult == LOSER -> ROCK
    othersItem == SCISSORS && desiredResult == WINNER -> ROCK
    othersItem == SCISSORS && desiredResult == LOSER -> PAPER
    else -> throw RuntimeException("oh noes!")
}
