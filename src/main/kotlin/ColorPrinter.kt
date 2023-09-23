object ColorPrinter {
    enum class Color(val value: String) {
        RED("\u001B[31m"),
        GREEN("\u001B[32m"),
        YELLOW("\u001B[33m"),
        BLUE("\u001B[34m")
    }

    fun println(message: String, color: Color) {
        println("${color.value}$message\u001B[0m")
    }
    fun printTestLn(number: Int, message: String, color: Color) {
        println("${Color.GREEN.value}TEST[$number] : ${color.value}$message\u001B[0m")
    }
}
