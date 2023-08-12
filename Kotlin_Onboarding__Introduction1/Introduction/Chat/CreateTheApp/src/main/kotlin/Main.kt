fun main() {
    val numberOfQuestions = 2
    val questions: MutableList<String> = mutableListOf(
        "Hello! I'm glad to meet you, let me get to know you better! What is your name?",
        "Nice to meet you, %s! My name is Kotlin Bot! I am a young programming language created in 2010. How old are you?",
        "%s is great! I hope you successfully complete this course! Anyone can learn programming at any age!"
    )
    val answers: MutableList<String> = mutableListOf()
    for (i in 0 until questions.size) {
        println(questions[i].format(*answers.toTypedArray()))
        if (i < numberOfQuestions) {
            val value = readlnOrNull()
            answers.add(value.toString())
        }
    }
}
