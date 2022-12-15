package tictactoe

import java.util.*

internal class OutBoundsMoveException : Exception()

internal class OccupiedCoordinateException : Exception()

class Coordinates(val x: Int, val y: Int) {
    init {
        if (x < 0 || x > 2 || y < 0 || y > 2) {
            throw OutBoundsMoveException()
        }
    }
}

// Utility
fun getRows(gameState: Array<Char>): Array<Array<Char>> {
    val rows = Array(3) { arrayOf(' ', ' ', ' ') }
    for (i in 0..2) {
        System.arraycopy(gameState, i * 3, rows[i], 0, 3)
    }
    return rows
}

fun getCols(gameState: Array<Char>): Array<Array<Char>> {
    val cols = Array(3) { arrayOf(' ', ' ', ' ') }
    for (i in 0..2) {
        for (j in 0..2) {
            cols[i][j] = gameState[i + 3 * j]
        }
    }
    return cols
}

fun isLineOf(player: Char, line: Array<Char>): Boolean {
    val expectedLine = arrayOf(player, player, player)
    return line.contentEquals(expectedLine)
}

fun isLinesOf(player: Char, lines: Array<Array<Char>>): Boolean {
    for (line in lines) {
        if (isLineOf(player, line)) {
            return true
        }
    }
    return false
}

fun isRowOf(player: Char, gameState: Array<Char>): Boolean {
    return isLinesOf(player, getRows(gameState))
}

fun isColOf(player: Char, gameState: Array<Char>): Boolean {
    return isLinesOf(player, getCols(gameState))
}

fun isRowOfXs(gameState: Array<Char>): Boolean {
    return isRowOf('X', gameState)
}

fun isRowOfOs(gameState: Array<Char>): Boolean {
    return isRowOf('O', gameState)
}

fun isColOfXs(gameState: Array<Char>): Boolean {
    return isColOf('X', gameState)
}

fun isColOfOs(gameState: Array<Char>): Boolean {
    return isColOf('O', gameState)
}

fun getRightDiagonal(gameState: Array<Char>): Array<Char> {
    return arrayOf(gameState[0], gameState[4], gameState[8])
}

fun getLeftDiagonal(gameState: Array<Char>): Array<Char> {
    return arrayOf(gameState[2], gameState[4], gameState[6])
}

fun isRightDiagonalOf(player: Char, gameState: Array<Char>): Boolean {
    return isLineOf(player, getRightDiagonal(gameState))
}

fun isLeftDiagonalOf(player: Char, gameState: Array<Char>): Boolean {
    return isLineOf(player, getLeftDiagonal(gameState))
}

fun isRightDiagonalOfXs(gameState: Array<Char>): Boolean {
    return isRightDiagonalOf('X', gameState)
}

fun isRightDiagonalOfOs(gameState: Array<Char>): Boolean {
    return isRightDiagonalOf('O', gameState)
}

fun isLeftDiagonalOfXs(gameState: Array<Char>): Boolean {
    return isLeftDiagonalOf('X', gameState)
}

fun isLeftDiagonalOfOs(gameState: Array<Char>): Boolean {
    return isLeftDiagonalOf('O', gameState)
}

fun isDiagonalOfXs(gameState: Array<Char>): Boolean {
    return isRightDiagonalOfXs(gameState) || isLeftDiagonalOfXs(gameState)
}

fun isDiagonalOfOs(gameState: Array<Char>): Boolean {
    return isRightDiagonalOfOs(gameState) || isLeftDiagonalOfOs(gameState)
}

fun isXWins(gameState: Array<Char>): Boolean {
    return isRowOfXs(gameState) || isColOfXs(gameState) || isDiagonalOfXs(gameState)
}

fun isOWins(gameState: Array<Char>): Boolean {
    return isRowOfOs(gameState) || isColOfOs(gameState) || isDiagonalOfOs(gameState)
}

fun movesAvailable(gameState: Array<Char>?): Boolean {
    if (gameState == null) {
        return false
    }
    return gameState.contains(' ')
}

fun hasNoWinner(gameState: Array<Char>): Boolean {
    return !isXWins(gameState) && !isOWins(gameState)
}

fun count(player: Char, gameState: Array<Char>): Int {
    var count = 0
    for (move in gameState) {
        if (move == player) {
            count++
        }
    }
    return count
}

fun wrongNumberOfMoves(gameState: Array<Char>): Boolean {
    val movesDiff = count('X', gameState) - count('O', gameState)
    return movesDiff < -1 || movesDiff > 1
}

fun isDraw(gameState: Array<Char>): Boolean {
    return hasNoWinner(gameState) && !movesAvailable(gameState)
}

fun isNotFinished(gameState: Array<Char>): Boolean {
    return hasNoWinner(gameState) && movesAvailable(gameState)
}

fun isImpossible(gameState: Array<Char>): Boolean {
    return isXWins(gameState) && isOWins(gameState) || wrongNumberOfMoves(gameState)
}

// Read state from input
fun readState(): Array<Char> {
    val sc = Scanner(System.`in`)
    val line = sc.nextLine().uppercase()
    return line.toCharArray().toTypedArray()
}

// Print the board
fun printBoard(gameState: Array<Char>) {
    println("---------")
    for (i in 0 until 3) {
        print("| ")
        for (j in 0 until 3) {
            print(gameState[3 * i + j])
            print(' ')
        }
        println("|")
    }
    println("---------")
}

// Checks
fun getStatus(gameState: Array<Char>): String {
    var status = "Unknown game state"

    if (isImpossible(gameState)) {
        status = "Impossible"
    } else if (isNotFinished(gameState)) {
        status = "Game not finished"
    } else if (isXWins(gameState)) {
        status = "X wins"
    } else if (isOWins(gameState)) {
        status = "O wins"
    } else if (isDraw(gameState)) {
        status = "Draw"
    }
    return status
}

@Throws(OutBoundsMoveException::class)
fun readUserMove(): Coordinates {
    val scanner = Scanner(System.`in`)
    val y = scanner.nextInt() - 1
    val x = scanner.nextInt() - 1
    return Coordinates(x, y)
}

@Throws(OccupiedCoordinateException::class)
fun makeUserMove(move: Coordinates, gameState: Array<Char>, player: Char) {
    val target = gameState[move.y * 3 + move.x]
    if (target != ' ') {
        throw OccupiedCoordinateException()
    }
    gameState[move.y * 3 + move.x] = player
}

fun processUserMove(gameState: Array<Char>, player: Char) {
    try {
        print("Enter the coordinates: ")
        val move = readUserMove()
        makeUserMove(move, gameState, player)
    } catch (e: OutBoundsMoveException) {
        println("Coordinates should be from 1 to 3!")
        processUserMove(gameState, player)
    } catch (e: OccupiedCoordinateException) {
        println("This cell is occupied! Choose another one!")
        processUserMove(gameState, player)
    } catch (e: InputMismatchException) {
        println("You should enter numbers!")
        processUserMove(gameState, player)
    }
}

fun emptyBoard(): Array<Char> {
    return arrayOf<Char>(' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ')
}

fun main() {
    val gameState = emptyBoard()
    var status = getStatus(gameState)
    var nextPlayer = 'X'

    while (status == "Game not finished") {
        printBoard(gameState)
        processUserMove(gameState, nextPlayer)
        status = getStatus(gameState)
        nextPlayer = if (nextPlayer == 'X') 'O' else 'X'
    }

    printBoard(gameState);
    println(status);
}