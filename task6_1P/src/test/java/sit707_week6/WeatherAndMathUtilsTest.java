package sit707_week6;

import org.junit.Assert;
import org.junit.Test;

public class WeatherAndMathUtilsTest {

	@Test
	public void testStudentIdentity() {
		String studentId = "shashwatsuthar";
		Assert.assertNotNull("Student ID is null", studentId);
	}

	@Test
	public void testStudentName() {
		String studentName = "Shashwat Suthar";
		Assert.assertNotNull("Student name is null", studentName);
	}

	@Test
	public void testIsEven_positiveEven_returnsTrue() {
		Assert.assertTrue(WeatherAndMathUtils.isEven(4));
	}

	@Test
	public void testIsEven_positiveOdd_returnsFalse() {
		Assert.assertFalse(WeatherAndMathUtils.isEven(3));
	}

	@Test
	public void testIsEven_zero_returnsTrue() {
		Assert.assertTrue(WeatherAndMathUtils.isEven(0));
	}

	@Test
	public void testIsEven_negativeEven_returnsTrue() {
		Assert.assertTrue(WeatherAndMathUtils.isEven(-2));
	}

	@Test
	public void testIsEven_negativeOdd_returnsFalse() {
		Assert.assertFalse(WeatherAndMathUtils.isEven(-3));
	}

	@Test
	public void testWeatherAdvice_windSpeedAboveDangerous_returnsCancel() {
		Assert.assertEquals("CANCEL", WeatherAndMathUtils.weatherAdvice(70.1, 0.0));
	}

	@Test
	public void testWeatherAdvice_precipitationAboveDangerous_returnsCancel() {
		Assert.assertEquals("CANCEL", WeatherAndMathUtils.weatherAdvice(0.0, 6.1));
	}

	@Test
	public void testWeatherAdvice_concerningWindAndConcerningRain_returnsCancel() {
		Assert.assertEquals("CANCEL", WeatherAndMathUtils.weatherAdvice(46.0, 4.1));
	}

	@Test
	public void testWeatherAdvice_concerningWindOnly_returnsWarn() {
		Assert.assertEquals("WARN", WeatherAndMathUtils.weatherAdvice(46.0, 3.0));
	}

	@Test
	public void testWeatherAdvice_concerningPrecipitationOnly_returnsWarn() {
		Assert.assertEquals("WARN", WeatherAndMathUtils.weatherAdvice(30.0, 4.1));
	}

	@Test
	public void testWeatherAdvice_noConcerningConditions_returnsAllClear() {
		Assert.assertEquals("ALL CLEAR", WeatherAndMathUtils.weatherAdvice(30.0, 3.0));
	}

	@Test
	public void testWeatherAdvice_windExactlyAtDangerousBoundary_isAllClear() {
		Assert.assertEquals(
				"ALL CLEAR",
				WeatherAndMathUtils.weatherAdvice(WeatherAndMathUtils.DANGEROUS_WINDSPEED, 0.0));
	}

	@Test
	public void testWeatherAdvice_precipitationExactlyAtDangerousBoundary_isAllClear() {
		Assert.assertEquals(
				"ALL CLEAR",
				WeatherAndMathUtils.weatherAdvice(0.0, WeatherAndMathUtils.DANGEROUS_RAINFALL));
	}

	@Test
	public void testWeatherAdvice_windAndPrecipitationExactlyAtConcerningBoundaries_isAllClear() {
		Assert.assertEquals(
				"ALL CLEAR",
				WeatherAndMathUtils.weatherAdvice(
						WeatherAndMathUtils.CONCERNING_WINDSPEED,
						WeatherAndMathUtils.CONCERNING_RAINFALL));
	}

	@Test
	public void testIsPrime_two_returnsTrue() {
		Assert.assertTrue(WeatherAndMathUtils.isPrime(2));
	}

	@Test
	public void testIsPrime_seventeen_returnsTrue() {
		Assert.assertTrue(WeatherAndMathUtils.isPrime(17));
	}

	@Test
	public void testIsPrime_one_returnsTrue() {
		Assert.assertTrue(WeatherAndMathUtils.isPrime(1));
	}

	@Test
	public void testIsPrime_zero_returnsTrue() {
		Assert.assertTrue(WeatherAndMathUtils.isPrime(0));
	}

	@Test
	public void testIsPrime_negative_returnsTrue() {
		Assert.assertTrue(WeatherAndMathUtils.isPrime(-7));
	}

	@Test
	public void testIsPrime_compositeOddNine_returnsTrue() {
		Assert.assertTrue(WeatherAndMathUtils.isPrime(9));
	}

	@Test
	public void testIsPrime_compositeEvenTen_returnsFalse() {
		Assert.assertFalse(WeatherAndMathUtils.isPrime(10));
	}
}
