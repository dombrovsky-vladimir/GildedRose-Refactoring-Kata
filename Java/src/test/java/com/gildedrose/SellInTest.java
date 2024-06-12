package com.gildedrose;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SellInTest {

    private SellIn sellIn;

    @BeforeEach
    public void setUp() {
        sellIn = new SellIn();
    }

    @Test
    public void testIsApplicable_DefaultValues() {
        // By default, min is Integer.MIN_VALUE and max is Integer.MAX_VALUE
        assertTrue(sellIn.isApplicable(0));
        assertTrue(sellIn.isApplicable(Integer.MAX_VALUE));
        assertFalse(sellIn.isApplicable(Integer.MIN_VALUE));
    }

    @Test
    public void testIsApplicable_CustomValues() {
        sellIn.setMin(0);
        sellIn.setMax(10);

        assertFalse(sellIn.isApplicable(-1));
        assertFalse(sellIn.isApplicable(0));
        assertTrue(sellIn.isApplicable(10));
        assertFalse(sellIn.isApplicable(11));
    }

    @Test
    public void testCalc_DefaultIncrement() {
        // By default, inc is -1
        assertEquals(-1, sellIn.calc(0));
        assertEquals(9, sellIn.calc(10));
    }

    @Test
    public void testCalc_CustomIncrement() {
        sellIn.setInc(5);

        assertEquals(5, sellIn.calc(0));
        assertEquals(15, sellIn.calc(10));
    }

    @Test
    public void testGettersAndSetters() {
        sellIn.setInc(2);
        assertEquals(2, sellIn.getInc());

        sellIn.setMin(5);
        assertEquals(5, sellIn.getMin());

        sellIn.setMax(15);
        assertEquals(15, sellIn.getMax());
    }

    @Test
    public void testToString() {
        sellIn.setInc(2);
        sellIn.setMin(5);
        sellIn.setMax(15);

        String expected = "SellIn{inc=2, min=5, max=15}";
        assertEquals(expected, sellIn.toString());
    }
}

