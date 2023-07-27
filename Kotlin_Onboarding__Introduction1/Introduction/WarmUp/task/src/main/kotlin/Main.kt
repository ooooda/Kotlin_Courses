fun getGameRules(wordLength: Int, maxAttemptsCount: Int, secretExample: String = "ACEB", alphabet: String): String {
    return "Welcome to the game!\n\nTwo people play this game: " +
            "one chooses a word (a sequence of letters), the other guesses it. " +
            "In this version, the computer chooses the word: a sequence of $wordLength " +
            "letters (for example, $secretExample). " +
            "The user has several attempts to guess it " +
            "(the max number is $maxAttemptsCount). " +
            "For each attempt, the number of complete matches (letter and position) " +
            "and partial matches (letter only) is reported. " +
            "The possible symbols in the word: $alphabet. \n\nFor example, " +
            "with $secretExample as the hidden word, " +
            "the BCDF guess will give 1 full match (C) and 1 partial match (B)."
}

fun generateSecret(wordLength: Int, alphabet: String): String {
    return List(wordLength) { alphabet.random() }.joinToString(separator = "")
}

fun countPartialMatches(secret: String, guess: String): Int {
    require(secret.length == guess.length)
    return secret.filter { guess.contains(it) && guess.indexOf(it) != secret.indexOf(it) }.length

}

fun countExactMatches(secret: String, guess: String): Int {
    require(secret.length == guess.length)
    return secret.filterIndexed { index, symbol -> symbol == guess[index] }.length
}

fun playGame(wordLength: Int, maxAttemptsCount: Int, secret: String, alphabet: String) {
    var complete: Boolean = false
    var guess: String
    var countAttempts: Int = 0
    while (!complete && countAttempts != maxAttemptsCount + 1) {
        safeUserInput(wordLength, alphabet)
        guess = safeReadLine()
        isCorrectInput(guess, wordLength, alphabet)
        ++countAttempts
        printRoundResults(secret, guess)
        complete = isComplete(secret, guess)
    }
    if (isWin(complete, countAttempts, maxAttemptsCount)) {
        println("Congratulations! You guessed it!")
    }
    if (isLoss(complete, countAttempts, maxAttemptsCount)) {
        println("Sorry, you lost! :( My word is $secret")
    }
}

fun isComplete(secret: String, guess: String): Boolean {
    return secret == guess
}

fun printRoundResults(secret: String, guess: String) {
    println(
        "Your guess has ${countExactMatches(secret, guess)} full matches and ${
            countPartialMatches(
                secret,
                guess
            )
        } partial matches."
    )
}

fun isWin(complete: Boolean, attempts: Int, maxAttemptsCount: Int): Boolean {
    return (complete && attempts <= maxAttemptsCount)
}

fun isLoss(complete: Boolean, attempts: Int, maxAttemptsCount: Int): Boolean {
    return (!complete && attempts > maxAttemptsCount)
}

fun safeUserInput(wordLength: Int, alphabet: String) {
    println(
        "Please input your guess. It should be of length $wordLength, " +
                "and each symbol should be from the alphabet: $alphabet.\n"
    )
}

fun isCorrectInput(userInput: String, wordLength: Int, alphabet: String): Boolean {
    val correctLength: Boolean = userInput.length == wordLength
    val correctSymbols: Boolean = userInput.filter { alphabet.contains(it) }.length == wordLength
    if (!correctLength) {
        println("The length of your guess should be $wordLength characters! Try again!")
    }
    if (!correctSymbols) {
        println("All symbols in your guess should be the $alphabet alphabet characters! Try again!")
    }
    return correctLength && correctSymbols
}

fun main() {
    val alphabet = "ABCDEFGH"
    val wordLength = 4
    val maxAttemptsCount = 3
    val secretExample: String = generateSecret(wordLength, alphabet)
    println(getGameRules(wordLength, maxAttemptsCount, secretExample, alphabet))
    playGame(wordLength, maxAttemptsCount, secretExample, alphabet)
}
