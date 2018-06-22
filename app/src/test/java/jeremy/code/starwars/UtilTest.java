package jeremy.code.starwars;

import org.junit.Test;

import jeremy.code.starwars.Util.Util;

import static junit.framework.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UtilTest {

    @Test
    public void convertCentimetersToMeters_givenCentimeters_returnMeters() {
        assertEquals("1.70m", Util.convertCentimetersToMeters("170"));
    }

    @Test
    public void convertCentimetersToMeters_givenEmptyString_returnNull() {
        assertEquals(null, Util.convertCentimetersToMeters(null));
    }

    @Test
    public void parseKilograms_givenKilograms_returnKilogramsWithSuffix() {
        assertEquals("80kg", Util.parseKilograms("80"));
    }

    @Test
    public void parseKilograms_givenNullString_returnNull() {
        assertEquals(null, Util.parseKilograms(null));
    }
}