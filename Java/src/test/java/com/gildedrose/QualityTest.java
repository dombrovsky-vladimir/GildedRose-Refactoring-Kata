package com.gildedrose;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QualityTest {

    private Quality quality;

    @BeforeEach
    public void setUp() {
        quality = new Quality();
    }

    @Test
    public void testCalc_DefaultValues() {
        assertEquals(9, quality.calc(10)); // default inc is -1 and multiplier is 1
        assertEquals(0, quality.calc(0));  // should not go below min (0)
    }

    @Test
    public void testCalc_CustomIncrement() {
        quality.setInc(2);
        assertEquals(12, quality.calc(10));
        assertEquals(2, quality.calc(0));  // should add increment to min
    }

    @Test
    public void testCalc_CustomMultiplier() {
        quality.setMultiplier(2);
        assertEquals(8, quality.calc(10));  // (10 + -1 * 2) = 8
        assertEquals(0, quality.calc(1));  // (1 + -1 * 2) should be min (0)
    }

    @Test
    public void testCalc_ExceedMax() {
        quality.setInc(5);
        quality.setMax(15);
        assertEquals(15, quality.calc(12)); // should not exceed max (15)
    }

    @Test
    public void testCalc_BelowMin() {
        quality.setInc(-10);
        quality.setMin(0);
        assertEquals(0, quality.calc(5)); // should not go below min (0)
    }

    @Test
    public void testGettersAndSetters() {
        quality.setInc(2);
        assertEquals(2, quality.getInc());

        quality.setMultiplier(3);
        assertEquals(3, quality.getMultiplier());

        quality.setMin(5);
        assertEquals(5, quality.getMin());

        quality.setMax(25);
        assertEquals(25, quality.getMax());
    }

    @Test
    public void testToString() {
        quality.setInc(2);
        quality.setMultiplier(3);
        quality.setMin(5);
        quality.setMax(25);

        String expected = "Quality{inc=2, multiplier=3, min=5, max=25}";
        assertEquals(expected, quality.toString());
    }
}

