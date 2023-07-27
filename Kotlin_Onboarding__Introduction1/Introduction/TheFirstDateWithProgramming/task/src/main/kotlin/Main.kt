fun main() {
    var firstUserAnswer = ""
    var secondUserAnswer = ""
    var thirdUserAnswer = ""
    val questions: MutableList<String> = mutableListOf(
        "What is TROTEN?",
        "How did you spend your graduation?",
        "Why does a spider need eight legs?",
        "What is Kotlin?",
        "How was Kotlin invented?",
        "Why are you learning Kotlin?"
    )
    print(
        "Hello! I will ask you several questions.\n" +
                "Please answer all of them and be honest with me!\n"
    )
    for (i in 1..3) {
        println(questions[i - 1])
        when (i) {
            1 -> {
                firstUserAnswer = readlnOrNull().toString()
            }

            2 -> {
                secondUserAnswer = readlnOrNull().toString()
            }

            else -> {
                thirdUserAnswer = readlnOrNull().toString()
            }
        }
    }
    println("Now let's have fun!")
    for (i in 4..questions.size) {
        println(questions[i - 1])
        when (i) {
            4 -> {
                println(firstUserAnswer)
            }

            5 -> {
                println(secondUserAnswer)
            }

            else -> {
                println(thirdUserAnswer)
            }
        }
    }
}
