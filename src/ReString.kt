import kotlin.math.pow

class ReString(private val string: CharArray, val length: Int) {
    /**
     * Init new object without data.
     */
    constructor() : this(CharArray(0), 0)

    /**
     * Init new object with data from String.
     */
    constructor(s: String) : this(s.toCharArray())

    /**
     * Init new object with data from Char.
     */
    constructor(c: Char) : this(CharArray(1).apply { set(0, c) }, 1)

    /**
     * Init new object with data from CharArray.
     */
    constructor(charArray: CharArray) : this(charArray, charArray.size)

    /**
     * Init new object with data from old ReString class.
     */
    constructor(reString: ReString) : this(reString.string, reString.length)

    /**
     * Adding a new CharArray to an existing one.
     * Returns a new object with new data.
     */
    fun join(charArray: CharArray): ReString {
        return concat(charArray)
    }

    /**
     * Adding a String to an existing data.
     * Returns a new object with new data.
     */
    fun join(s: String): ReString {
        return concat(s.toCharArray())
    }

    /**
     * Adding a Char to an existing data.
     * Returns a new object with new data.
     */
    fun join(char: Char): ReString {
        return concat(char)
    }

    /**
     * Adding a ReString data to an existing data.
     * Returns a new object with new data.
     */
    fun join(s: ReString): ReString {
        return concat(s.string)
    }

    /**
     * Searches a string for a given char.
     * Returns its position if one was found, and -1 if not found.
     */
    fun indexOf(char: Char, startIndex: Int = 0, endIndex: Int = length): Int {
        checkBoundsBeginEnd(startIndex, endIndex, length)
        for (i in startIndex until endIndex)
            if (char == string[i]) return i
        return -1
    }

    /**
     * Searches the string for the character from the end.
     * Returns its position if one was found, and -1 if not found.
     */
    fun lastIndexOf(char: Char, startIndex: Int = 0, endIndex: Int = length): Int {
        checkBoundsBeginEnd(startIndex, endIndex, length)
        for (i in endIndex - 1 downTo startIndex)
            if (char == string[i]) return i
        return -1
    }

    /**
     * Cuts an existing string at the indices.
     * Returns a new object with the result.
     */
    fun substring(startIndex: Int, endIndex: Int): ReString {
        val length = length
        checkBoundsBeginEnd(startIndex, endIndex, length)
        if (startIndex == 0 && endIndex == length) {
            return this
        }
        val newStr = CharArray(endIndex - startIndex)
        string.copyInto(newStr, 0, startIndex, endIndex)
        return ReString(newStr)
    }

    /**
     * Converts a string to a float.
     * Returns a floating point number.
     */
    fun toFloat(): Float = toFloat(string)

    /**
     * Converts a String to a float.
     * Returns a floating point number.
     */
    fun toFloat(str: String): Float = toFloat(str.toCharArray())

    /**
     * Converts a string to a float.
     * Returns a floating point number.
     */
    fun toFloat(reString: ReString): Float = toFloat(reString.string)

    /**
     * Converts a CharArray to a float.
     * Returns a floating point number.
     */
    private fun toFloat(charArray: CharArray): Float {
        val negative = charArray[0] == '-'
        val size = if (negative) charArray.size - 1 else charArray.size
        var tempFloat = 0.0f
        var mid: Int = -1
        for (i in charArray.indices)
            if (charArray[i] == '.') {
                mid = if (negative) i - 1 else i
                break
            }
        var powCounter = if (mid == -1) size else mid - 1
        for (i in charArray.indices) {
            if (charArray[i] == '.' || charArray[i] == '-') {
                continue
            }
            val float = charArray[i].toFloat() - 48
            tempFloat += float * 10f.pow(powCounter)
            powCounter--
        }
        if (negative) tempFloat *= -1
        return tempFloat
    }

    /**
     * Converts an int to a ReString.
     * Returns a new object with new data.
     */
    fun intToString(number: Int): ReString {
        var tempArray = CharArray(0)
        var num = number
        val negative = num < 0
        var tempNum: Int
        if (negative) num *= -1
        do {
            tempNum = (num % 10) + 48
            tempArray = concatToFirst(tempArray, tempNum.toChar())
            num /= 10
        } while (num != 0)
        tempArray = if (negative) concatToFirst(tempArray, '-') else tempArray
        return ReString(tempArray)
    }

    /**
     * Adds the char to the beginning of an existing string.
     * Returns a new CharArray.
     */
    private fun concatToFirst(charArray: CharArray, char: Char): CharArray {
        val temp = CharArray(charArray.size + 1)
        charArray.copyInto(temp, 1, 0, charArray.size)
        temp[0] = char
        return temp
    }

    /**
     * Adds the String to the beginning of an existing string.
     * Returns a new object with new data.
     */
    fun concat(str: String): ReString {
        return concat(str.toCharArray())
    }

    /**
     * Adds the ReString to the beginning of an existing string.
     * Returns a new object with new data.
     */
    fun concat(reString: ReString): ReString {
        return concat(reString.string)
    }

    /**
     * Adds the char to the beginning of an existing string.
     * Returns a new object with new data.
     */
    fun concat(char: Char): ReString {
        val temp = CharArray(length + 1)
        string.copyInto(temp, 0, 0, length)
        temp[length] = char
        return ReString(temp)
    }

    /**
     * Adds the CharArray to the beginning of an existing string.
     * Returns a new object with new data.
     */
    private fun concat(charArray: CharArray): ReString {
        val temp = CharArray(length + charArray.size)
        string.copyInto(temp, 0, 0, length)
        charArray.copyInto(temp, length, 0, charArray.size)
        return ReString(temp)
    }

    /**
     * Reverse a string.
     * Returns a new object with new data.
     */
    fun reversed(): ReString {
        val temp: CharArray = string.clone()
        temp.reverse()
        return ReString(temp)
    }

    /**
     * Checks a string for a char.
     * Returns a boolean response.
     */
    fun contains(char: Char): Boolean = string.contains(char)

    /**
     * Overriding the toString() method
     */
    override fun toString(): String {
        var s = ""
        for (i in 0 until length) s += string[i]
        return s
    }

    /**
     * Generating an error if the string is out of bounds
     */
    private fun checkBoundsBeginEnd(start: Int, end: Int, length: Int) {
        if (start < 0 || start > end || end > length) {
            throw StringIndexOutOfBoundsException(
                "begin $start, end $end, length $length"
            )
        }
    }
}
