import Outcome.Draw
import Outcome.Lose
import Outcome.Win
import Hand.Paper
import Hand.Rock
import Hand.Scissors

private enum class Hand(val score: Int) {
    Rock(1),
    Paper(2),
    Scissors(3);

    companion object {
        fun fromChar(char: Char) = when (char) {
            'A', 'X' -> Rock
            'B', 'Y' -> Paper
            'C', 'Z' -> Scissors
            else -> throw IllegalStateException()
        }
    }
}

private enum class Outcome(val score: Int) {
    Lose(0),
    Draw(3),
    Win(6);

    override fun toString(): String = when (this) {
        Win -> "beats"
        Lose -> "loses to"
        Draw -> "draws"
    }

    companion object {
        fun fromChar(char: Char) = when (char) {
            'X' -> Lose
            'Y' -> Draw
            'Z' -> Win
            else -> throw IllegalStateException()
        }
    }
}

private data class Round(val theirMove: Hand, val yourMove: Hand) {
    constructor(theirChar: Char, yourChar: Char) : this(
        Hand.fromChar(theirChar),
        Hand.fromChar(yourChar)
    )

    private val outcome: Outcome = when {
        theirMove == Rock && yourMove == Rock -> Draw
        theirMove == Paper && yourMove == Rock -> Lose
        theirMove == Scissors && yourMove == Rock -> Win
        theirMove == Rock && yourMove == Paper -> Win
        theirMove == Paper && yourMove == Paper -> Draw
        theirMove == Scissors && yourMove == Paper -> Lose
        theirMove == Rock && yourMove == Scissors -> Lose
        theirMove == Paper && yourMove == Scissors -> Win
        theirMove == Scissors && yourMove == Scissors -> Draw
        else -> throw IllegalStateException("Impossible")
    }

    val score: Int = yourMove.score + outcome.score

    override fun toString(): String {
        return "$yourMove $outcome $theirMove - score: $score (${yourMove.score} + ${outcome.score})"
    }
}

fun main() {
    fun part1(input: Sequence<String>): Int {
        return input.sumOf { line ->
            val (theirMove, _, yourMove) = line.toCharArray()
            val round = Round(theirMove, yourMove)
//            println("$line - $round")
            round.score
        }
    }

    fun part2(input: Sequence<String>): Int {
        return input.sumOf { line ->
            val (theirMove, _, outcome) = line.toCharArray()
            val theirHand = Hand.fromChar(theirMove)
            val yourHand = when (Outcome.fromChar(outcome)) {
                Lose -> when (theirHand) {
                    Rock -> Scissors
                    Paper -> Rock
                    Scissors -> Paper
                }
                Draw -> theirHand
                Win -> when (theirHand) {
                    Rock -> Paper
                    Paper -> Scissors
                    Scissors -> Rock
                }
            }
            val round = Round(theirHand, yourHand)
//            println("$line - $round")
            round.score
        }
    }

    println(part1(readInput("day02")))
    println(part2(readInput("day02")))
}
