import org.junit.jupiter.api.Assertions.*

internal class ReStringTest {

    private lateinit var str: String
    private lateinit var reStr: ReString

    @org.junit.jupiter.api.BeforeEach
    fun setUp() {
        val num = -131214.09412f
        reStr = ReString(num.toString())
        str = num.toString()
    }

    @org.junit.jupiter.api.Test
    fun testInit() {
        assertEquals(ReString("MyNewString").toString(), "MyNewString")
    }

    @org.junit.jupiter.api.Test
    fun concat() {
        assertEquals(reStr.concat(ReString("123")).toString(), str + "123")
        assertEquals(reStr.concat("123").toString(), str + "123")
        assertEquals(reStr.concat('c').toString(), str + 'c')
    }

    @org.junit.jupiter.api.Test
    fun substring() {
        assertEquals(reStr.substring(3, 6).toString(), str.substring(3, 6))
    }

    @org.junit.jupiter.api.Test
    fun indexOf() {
        assertEquals(reStr.indexOf('5'), str.indexOf('5'))
        assertEquals(reStr.indexOf('f'), str.indexOf('f'))
    }

    @org.junit.jupiter.api.Test
    fun lastIndexOf() {
        assertEquals(reStr.lastIndexOf('4'), str.lastIndexOf('4'))
        assertEquals(reStr.lastIndexOf('y'), str.lastIndexOf('y'))
    }

    @org.junit.jupiter.api.Test
    fun contains() {
        assertEquals(reStr.contains('1'), str.contains('1'))
        assertEquals(reStr.contains('p'), str.contains('p'))
    }

    @org.junit.jupiter.api.Test
    fun reversed() {
        assertEquals(reStr.reversed().toString(), str.reversed())
    }

    @org.junit.jupiter.api.Test
    fun intToString() {
        val testStr = 389570
        assertEquals(reStr.intToString(testStr).toString(), testStr.toString())
    }

    @org.junit.jupiter.api.Test
    fun parseToFloat() {
        assertEquals(reStr.toFloat(), str.toFloat())
    }

    @org.junit.jupiter.api.Test
    fun length() {
        assertEquals(reStr.length, str.length)
    }

    @org.junit.jupiter.api.AfterEach
    fun content() {
        assertEquals(reStr.toString(), str)
    }

}