package com.example.decimalformatter

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.text.DecimalFormatSymbols
import java.util.*

class DecimalFormatterTest {

    private val subject = DecimalFormatter(symbols = DecimalFormatSymbols(Locale.US))

    @Test
    fun `test cleanup decimal without fraction`() {
        val inputs = arrayOf("1", "123", "123131231", "3423423")
        for (input in inputs) {
            val result = subject.cleanup(input)
            assertEquals(input, result)
        }
    }

    @Test
    fun `test cleanup decimal with fraction normal case`() {
        val inputs = arrayOf(
            "1.00", "123.1", "1231.31231", "3.423423"
        )

        for (input in inputs) {
            val result = subject.cleanup(input)
            assertEquals(input, result)
        }
    }

    @Test
    fun `test cleanup decimal with fraction irregular inputs`() {
        val inputs = arrayOf(
            Pair("1231.12312.12312.", "1231.1231212312"),
            Pair("1.12312..", "1.12312"),
            Pair("...12..31.12312.123..12.", "12.311231212312"),
            Pair("---1231.-.-123-12.1-2312.", "1231.1231212312"),
            Pair("-.--1231.-.-123-12.1-2312.", "1231.1231212312"),
            Pair("....", ""),
            Pair(".-.-..-", ""),
            Pair("---", ""),
            Pair(".", ""),
            Pair("      ", ""),
            Pair("     1231.  -   12312.   -   12312.", "1231.1231212312"),
            Pair("1231.  -   12312.   -   12312.     ", "1231.1231212312")
        )

        for ((input, expected) in inputs) {
            val result = subject.cleanup(input)
            assertEquals(expected, result)
        }
    }

    @Test
    fun `test formatForVisual decimal without fraction`() {
        val inputs = arrayOf(
            Pair("1", "1"),
            Pair("12", "12"),
            Pair("123", "123"),
            Pair("1234", "1,234"),
            Pair("12345684748049", "12,345,684,748,049"),
            Pair("10000", "10,000")
        )

        for ((input, expected) in inputs) {
            val result = subject.formatForVisual(input)
            assertEquals(expected, result)
        }
    }

    @Test
    fun `test formatForVisual decimal with fraction`() {
        val inputs = arrayOf(
            Pair("1.0", "1.0"),
            Pair("12.01723817", "12.01723817"),
            Pair("123.999", "123.999"),
            Pair("1234.92834928", "1,234.92834928"),
            Pair("12345684748049.0", "12,345,684,748,049.0"),
            Pair("10000.0009", "10,000.0009"),
            Pair("0.0009", "0.0009"),
            Pair("0.0", "0.0"),
            Pair("0.0100008", "0.0100008"),
        )

        for ((input, expected) in inputs) {
            val result = subject.formatForVisual(input)
            assertEquals(expected, result)
        }
    }
}