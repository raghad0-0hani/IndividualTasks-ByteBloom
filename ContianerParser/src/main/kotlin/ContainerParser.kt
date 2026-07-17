package org.example
/*
 * ContainerParser.kt
 *
 * This parser uses structural recursion to process nested cargo containers.
 *
 * JVM Call Stack Behavior:
 *
 * 1. parseContainers() is invoked first and creates the initial stack frame.
 *
 * 2. Whenever a nested container (such as Crate[...] or Box[...]) is encountered,
 *    parseRecursive() calls itself. Each recursive call creates a new stack frame
 *    containing its own local variables (current index, current parsing state,
 *    and partial results).
 *
 * 3. When a closing bracket (']) is reached, the current recursive call finishes
 *    and returns its ParseResult to its caller. The corresponding stack frame is
 *    removed from the JVM call stack.
 *
 * 4. This process continues until all nested containers have been processed.
 *    The recursion unwinds one level at a time, removing stack frames in the
 *    reverse order in which they were created (Last-In, First-Out).
 *
 * Example:
 * parseContainers()
 *      |
 *      +-- parseRecursive(Crate)
 *              |
 *              +-- parseRecursive(Box)
 *                      |
 *                      +-- parseRecursive(PKG-101)
 * After PKG-101 is processed:
 *      Return from parseRecursive(PKG-101)
 *      Return from parseRecursive(Box)
 *      Return from parseRecursive(Crate)
 * Finally, parseContainers() returns the flattened list of package IDs.
 */

fun main() {
    val tests = listOf(
        "Crate[Box[PKG-101],PKG-102]",
        " Crate [ Box [ PKG-101 ] , PKG-102 ] ",
        "Crate[Box[],PKG-202]",
        "Box[PKG-101,,PKG-102]",
        "Crate[Box[PKG-101]")

    for (input in tests) {

        try {
            val result = parseContainers(input)
            println("Input: $input")
            println("Packages: $result")
        } catch (e: Exception) {
            println("Input: $input")
            println("Error Type: ${e::class.simpleName}")
            println("Error Message: ${e.message}")
        }
        println("======================================")
    }
}

data class ParseResult(
    val packages: List<String>,
    val nextIndex: Int
)

class ContainerParseException(message: String) : Exception(message)

private fun skipWhitespace(input: String, startIndex: Int): Int {

    var index = startIndex
    while ( index < input.length && input[index].isWhitespace()) {
        index++
    }
    return index
}

private fun readWord( input: String, startIndex: Int): Pair<String, Int> {

    var index = startIndex
    val builder = StringBuilder()

    while ( index < input.length &&
        input[index] !in charArrayOf('[', ']', ',') ) {
        builder.append(input[index])
        index++
    }
    return Pair(
        builder.toString().trim(),
        index
    )
}

private fun isPackageId(word: String): Boolean {

    return word.startsWith("PKG-")
}


fun parseContainers(input: String): List<String> {

    return parseRecursive(input, 0, false).packages
}

private fun parseRecursive(
    input: String,
    startIndex: Int,
    insideContainer: Boolean): ParseResult {

    var index = skipWhitespace(input, startIndex)
    var packages = emptyList<String>()

    while (index < input.length) {

        index = skipWhitespace(input,index)

        if (index >= input.length) {
            break
        }

        // End of current container
        if (input[index] == ']') {

            if (!insideContainer) {
                throw ContainerParseException("Unexpected closing bracket at index $index")
            }
            return ParseResult(packages, index + 1)
        }
        // Ignore structural commas
        if (input[index] == ',') {
            index++
            continue
        }
        // Read current word
        val (word, nextIndex) = readWord(input, index)
        index = nextIndex

        // Package ID found
        if (isPackageId(word)) {
            packages = packages + word
            continue
        }

        // Container name found (Crate / Box)
        index = skipWhitespace(input, index)

        if (index < input.length && input[index] == '[') {

            val nestedResult = parseRecursive(
                input,
                index + 1,
                true)

            packages = packages + nestedResult.packages
            index = nestedResult.nextIndex
        }
    }
    // Input ended while we were still inside a container
    if (insideContainer) {
        throw ContainerParseException("Missing closing bracket")
    }
    return ParseResult( packages, index)
}