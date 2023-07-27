fun printRules() {
    println(
        "Welcome to the game!\n" +
                "\n" +
                "In this game, you need to guess the word chosen by the computer.\n" +
                "The hidden word will appear as a sequence of underscore characters, one per letter.\n" +
                "You have $maxAttemptsCount attempts to guess the word.\n" +
                "All words are English words, consisting of $wordLength letters.\n" +
                "At each attempt, you should enter one letter; \n" +
                "if it is present in the hidden word, all matches will be displayed.\n" +
                "\n" +
                "For example, if the word CAT was chosen by the computer, _ _ _ will be displayed first,\n" +
                "since the word has 3 letters.\n" +
                "If you enter the letter A, you will see _ A _, and so on.\n" +
                "\n" +
                "Good luck with the game!"
    )
}

fun generateNewUserWord(secret: String, guess: Char, currentUserWord: String): String {
    val newUserWord = currentUserWord.filterNot { it.isWhitespace() }.toMutableList()
    var correct = false
    secret.forEachIndexed { index, symbol ->
        if (symbol == guess) {
            correct = true
            newUserWord[index] = symbol
        }
    }
    if (correct) {
        print("Great! This letter is in the word! The current word: ")
    } else {
        print("Sorry, the secret does not contain the symbol: $guess. The current word: ")
    }
    val result = newUserWord.joinToString(separator)
    println(result)
    return result
}

fun isWin(complete: Boolean, maxAttemptsCount: Int, numOfAttempts: Int): Boolean {
    return (complete && (numOfAttempts < maxAttemptsCount))
}

fun isLoss(complete: Boolean, maxAttemptsCount: Int, numOfAttempts: Int): Boolean {
    return (!complete && (numOfAttempts >= maxAttemptsCount))
}

fun isComplete(secret: String, currentUserWord: String): Boolean {
    return currentUserWord.filter { !it.isWhitespace() } == secret
}

fun isCorrectInput(guess: String): Boolean {
    if (guess.length != 1) {
        println("The length of your guess should be 1! Try again!")
        return false
    }
    if (!guess[0].isLetter()) {
        println("You should input only English letters! Try again!")
        return false
    }
    return true
}

fun safeUserInput(): Char {
    var guess: String
    do {
        println("Please, input your guess.")
        guess = safeReadLine()
    } while (!isCorrectInput(guess))
    return guess[0].uppercaseChar()
}

fun playGame(secret: String) {
    var complete = false
    var numOfAttempts = 0
    var currentUserWord = "_".repeat(wordLength)

    while (!complete && (numOfAttempts < maxAttemptsCount)) {
        val guess = safeUserInput()
        if (guess != '.') {
            currentUserWord = generateNewUserWord(secret, guess, currentUserWord)
        }
        complete = isComplete(secret, currentUserWord)
        ++numOfAttempts
    }
    if (isWin(complete, maxAttemptsCount, numOfAttempts)) {
        println("Congratulations! You guessed!")
    }
    if (isLoss(complete, maxAttemptsCount, numOfAttempts)) {
        println("Sorry, you lost! My word is $secret!")
    }
}

fun main() {
    val secret: String = words.random()
    printRules()
    playGame(secret)
}

