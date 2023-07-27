fun trimPicture(picture: String): String {
    return picture.trimIndent()
}

fun applyBordersFilter(picture: String): String {
    val lines = picture.lines()
    val updatedPicture = StringBuilder()
    val squireSide = lines.maxByOrNull { it.length }?.length ?: 0
    updatedPicture.append(borderSymbol.toString().repeat(4 + squireSide)).append(newLineSymbol)
    for (line in lines) {
        updatedPicture.append(borderSymbol).append(separator).append(line)
            .append(separator.toString().repeat(1 + squireSide - line.length)).append(borderSymbol)
            .append(newLineSymbol)
    }
    updatedPicture.append(borderSymbol.toString().repeat(4 + squireSide)).append(newLineSymbol)
    return updatedPicture.toString()
}

fun doubleImage(updatedPicture: StringBuilder, pictureWithBorders: List<String>, index: Int = 2): StringBuilder {
    for (i in 0 until pictureWithBorders.size - index) {
        updatedPicture.append(pictureWithBorders[i]).append(pictureWithBorders[i]).append(newLineSymbol)
    }
    return updatedPicture
}

fun applySquaredFilter(picture: String): String {
    val pictureWithBorders = applyBordersFilter(picture).lines()
    var updatedPicture = StringBuilder()
    updatedPicture = doubleImage(updatedPicture, pictureWithBorders)
    updatedPicture.append(updatedPicture)
    updatedPicture.append(borderSymbol.toString().repeat(pictureWithBorders[0].length * 2)).append(newLineSymbol)
    return updatedPicture.toString()
}

fun applyFilter(trimmedPic: String, filterName: String): String {
    return when (filterName) {
        "borders" -> applyBordersFilter(trimmedPic)
        "squared" -> applySquaredFilter(trimmedPic)
        else -> error("unexpected filter name")
    }
}

fun safeReadLine(): String {
    return readlnOrNull() ?: error("null value was received")
}

fun chooseFilter(): String {
    var correctAnser = false
    var filter: String
    do {
        println("Please choose the filter: 'borders' or 'squared'.")
        filter = safeReadLine()
        if (filter != "borders" && filter != "squared") {
            println("Please input 'borders' or 'squared'")
            continue
        }
        correctAnser = true
    } while (!correctAnser)
    return filter
}

fun choosePicture(): String {
    println("Please, choose a picture. The possible options: ${allPictures()}")
    val name = safeReadLine()
    getPictureByName(name)?.let {
        return it
    }
    return choosePicture()
}

fun getPicture(): String {
    println("Do you want to use a pre-defined picture or use a custom one? " +
            "Please, input 'yes' for a pre-defined image or 'no' for a custom one")
    var correctAnswer = false
    var answer: String
    do {
        answer = safeReadLine()
        answer.let{
            when (answer){
                "yes" -> {
                    answer = choosePicture()
                    correctAnswer = true
                }

                "no" -> {
                    println("Please, input a custom picture")
                    answer = safeReadLine()
                    correctAnswer = true
                }

                else -> {
                    println("Please, input 'yes' or 'no':")
                }
            }
        }
        println("Please, input 'yes' or 'no':")
    } while (!correctAnswer)
    return answer
}

fun photoshop() {
    val picture = getPicture()
    val filter = chooseFilter()
    println("The old image:\n$picture")
    val trimmedPicture = trimPicture(picture)
    val transformedPicture = applyFilter(trimmedPicture, filter)
    println("The transformed picture:\n$transformedPicture")
}

fun main() {
    photoshop()
}
