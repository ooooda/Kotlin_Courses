const val NEW_LINE: String = System.lineSeparator()

fun safeReadLine(): String {
    return readlnOrNull() ?: error("null value was received")
}

fun checkSize(size: String): Boolean {
    return size.filterNot { it.isDigit() }.isEmpty()
}

fun getPatternHeight(pattern: String) = pattern.removeSuffix(NEW_LINE).lines().size

fun getSizes(param: String): Int {
    var correctInput: Boolean
    var size: String
    do {
        println("Please, input the $param of the resulting picture:")
        size = safeReadLine()
        correctInput = checkSize(size)
    } while (!correctInput)
    return size.toInt()
}

fun repeatVerticallyWithGaps(pattern: String, n: Int): String {
    return pattern.repeat(n)
}

fun repeatHorizontallyWithGaps(pattern: String, n: Int, even: Int): String {
    val repeatedPattern = StringBuilder()
    val patternWidth = getPatternWidth(pattern)
    pattern.lines().forEach {
        for (i in 0 until n) {
            if (i % 2 == even) {
                repeatedPattern.append(fillPatternRow(it, patternWidth))
            } else {
                repeatedPattern.append(fillPatternRow("", patternWidth))
            }
        }
        repeatedPattern.append(newLineSymbol)
    }
    return repeatedPattern.toString()
}


fun canvasWithGapsGenerator(pattern: String, width: Int, height: Int): String {
    val first = repeatHorizontallyWithGaps(pattern, width, 0)
    val second = repeatHorizontallyWithGaps(pattern, width, 1)
    val repeatedPattern = StringBuilder()
    if (width == 1) {
        return first.repeat(height)
    }
    for (i in 0 until height) {
        if (i % 2 == 0) {
            repeatedPattern.append(first)
        } else {
            repeatedPattern.append(second)
        }
    }
    return repeatedPattern.toString()
}

fun applyGenerator(pattern: String, generatorName: String, width: Int, height: Int): String {
    return when (generatorName) {
        "canvas" -> {
            canvasGenerator(pattern, width, height)
        }

        "canvasGaps" -> {
            canvasWithGapsGenerator(pattern, width, height)
        }

        else -> {
            error("unknown generatorName: $generatorName")
        }
    }
}

fun dropTopFromLine(pattern: String): String {
    val split = pattern.split(newLineSymbol, limit = 2)
    if (split[1] == "") return pattern
    return split[1]
}

fun repeatHorizontally(pattern: String, n: Int): String {
    val repeatedPattern = StringBuilder()
    pattern.lines().forEach {
        repeatedPattern.append(fillPatternRow(it, getPatternWidth(pattern)).repeat(n)).append(newLineSymbol)
    }
    return repeatedPattern.toString()
}

fun repeatVertically(str1: String, height: Int): String {
    return str1.repeat(height)
}


fun canvasGenerator(pattern: String, width: Int, height: Int): String {
    val repeatedPattern = repeatHorizontally(pattern, width)
    val droppedPattern = dropTopFromLine(repeatedPattern)
    return buildString {
        append(repeatedPattern)
        if (height > 1) {
            append(repeatVertically(droppedPattern, height - 1))
        }
    }
}


fun fillPatternRow(patternRow: String, patternWidth: Int): String {
    if (patternRow.length > patternWidth) {
        throw IllegalStateException()
    }
    val updatedRow = StringBuilder()
    updatedRow.append(patternRow).append(separator.toString().repeat(patternWidth - patternRow.length))
    return updatedRow.toString()
}


fun choosePattern(): String {
    var correctInput = false
    var inputPattern: String
    var pattern: String?
    do {
        println("Please, choose pattern.${NEW_LINE}The possible options: ${allPatterns()}")
        inputPattern = safeReadLine()
        pattern = getPatternByName(inputPattern)
        pattern?.let {
            correctInput = true
        }
    } while (!correctInput)
    return pattern!!
}

fun getPattern(): String {
    var chosenPattern: String
    var correctInput = false
    var pattern = ""
    println(
            "Do you want to use a pre-defined pattern or a custom one?${NEW_LINE}" +
                    "Please input 'yes' for a pre-defined pattern or 'no' for a custom one."
    )
    do {
        chosenPattern = safeReadLine()
        when (chosenPattern) {
            "yes" -> {
                correctInput = true
                pattern = choosePattern()
            }

            "no" -> {
                correctInput = true
                pattern = safeReadLine()
            }

            else -> {
                println(
                        "Please input 'yes' or 'no'"
                )
            }
        }
    } while (!correctInput)
    return pattern
}

fun chooseGeneratorName(): String {
    var correctInput = false
    var input: String? = null
    while (!correctInput) {
        println("Please, choose the generator: 'canvas' or 'canvasGaps'.")
        input = readlnOrNull()
        input?.let {
            when {
                (input == "canvas" || input == "canvasGaps") -> {
                    correctInput = true
                }
            }
        }

    }
    return input!!
}

fun main() {
    val pattern = getPattern()
    val name = chooseGeneratorName()
    val width = getSizes("width")
    val height = getSizes("height")
    println(applyGenerator(pattern, name, width, height))
}