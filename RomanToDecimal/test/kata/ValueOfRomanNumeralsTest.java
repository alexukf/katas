package kata;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

public class ValueOfRomanNumeralsTest {
	@Test
	public void I_is_1() {
		assertRomanEqualsValue('I', 1);
	}

	@Test
	public void V_is_5() {
		assertRomanEqualsValue('V', 5);
	}

	@Test
	public void X_is_10() {
		assertRomanEqualsValue('X', 10);
	}

	@Test
	public void L_is_50() {
		assertRomanEqualsValue('L', 50);
	}

	@Test
	public void C_is_100() {
		assertRomanEqualsValue('C', 100);
	}

	@Test
	public void D_is_500() {
		assertRomanEqualsValue('D', 500);
	}

	@Test
	public void M_is_1000() {
		assertRomanEqualsValue('M', 1000);
	}

	@Test
	public void value_of_roman_numeral_with_nonincreasing_symbol_values_is_the_sum_of_its_values () {
		// Given
		String romanNumeral = "MMVI";

		// Calculate the value of the numeral
		int numeralValue = 0;
		for (int i = 0; i < romanNumeral.length(); i ++) {
			char romanSymbol = romanNumeral.charAt(i);
			numeralValue += getSymbolValue(romanSymbol);
		}

		// The value of the numeral is equal to the sum of its symbols
		int expected = 2006;
		assertEquals(expected, numeralValue);
	}

	@Test
	public void context_value_of_smaller_symbol_preceding_a_larger_symbol_is_the_negative_value_of_the_smaller_symbol() {
		// Given
		String smallerSymbolPrecedingALargerSymbol = "IV";

		// Calculate the value of the numeral
		int smallerSymbolValue = getCurrentSymbolContextValue(
			smallerSymbolPrecedingALargerSymbol.charAt(0),
			smallerSymbolPrecedingALargerSymbol.charAt(1)
		);

		// The value of the roman numeral is equal to the difference between the larger and the smaller symbols
		int positiveSymbolValue = getSymbolValue(smallerSymbolPrecedingALargerSymbol.charAt(0));
		int negativeSymbolValue = -1 * positiveSymbolValue;
		assertEquals(negativeSymbolValue, smallerSymbolValue);
	}

	@Test
	public void value_of_roman_numeral_is_the_sum_of_the_context_values_of_its_symbols () {
		String romanNumeral = "MCMXLIV";

		int numeralValue = 0;
		for (int i = 0; i < romanNumeral.length(); i ++) {
			char currentRomanSymbol = romanNumeral.charAt(i);
			char nextRomanSymbol = ' ';
			if (i + 1 < romanNumeral.length()) {
				nextRomanSymbol = romanNumeral.charAt(i + 1);
			}

			int currentSymbolContextValue = getCurrentSymbolContextValue(
				currentRomanSymbol, nextRomanSymbol
			);
			numeralValue += currentSymbolContextValue;
		}

		int sumOfSymbolsContextValues = 1944;
		assertEquals(sumOfSymbolsContextValues, numeralValue);
	}


	//~~~~~~ Test helper ~~~~~~

	private void assertRomanEqualsValue(char romanNumeral, final int expected) {
		//When
		int decimalValue = getSymbolValue(romanNumeral);

		//Then
		assertEquals(decimalValue, expected);
	}


	//~~~~~~ Production code ~~~~~~

	public int getCurrentSymbolContextValue(char firstSymbol, char secondSymbol) {
		int currentSymbolContextValue;

		int firstSymbolValue = getSymbolValue(firstSymbol);
		int secondSymbolValue = getSymbolValue(secondSymbol);

		if (firstSymbolValue < secondSymbolValue) {
			currentSymbolContextValue = -firstSymbolValue;
		} else {
			currentSymbolContextValue = firstSymbolValue;
		}

		return currentSymbolContextValue;
	}

	public int getSymbolValue(char romanNumeral) {
		Map<Character, Integer> romanValues = new HashMap();

		romanValues.put('I', 1);
		romanValues.put('V', 5);
		romanValues.put('X', 10);
		romanValues.put('L', 50);
		romanValues.put('C', 100);
		romanValues.put('D', 500);
		romanValues.put('M', 1000);
		romanValues.put(' ', 0);

		return romanValues.get(romanNumeral);
	}
}