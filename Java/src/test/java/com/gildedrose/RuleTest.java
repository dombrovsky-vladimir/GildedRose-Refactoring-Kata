package com.gildedrose;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RuleTest {

    private Rule rule;
    private SellIn sellIn;
    private Quality quality;

    @BeforeEach
    public void setUp() {
        rule = new Rule();
        sellIn = rule.getSellIn();
        quality = rule.getQuality();
    }

    @Test
    public void testUpdate_Applicable() {
        // Setting sellIn and quality to default values
        Item item = new Item("",10, 10);

        assertTrue(rule.update(item));
        assertEquals(9, item.sellIn); // default inc is -1
        assertEquals(9, item.quality); // default inc is 1
    }

    @Test
    public void testUpdate_NotApplicable() {
        sellIn.setMin(0);
        sellIn.setMax(5);

        Item item = new Item("",6, 10);

        assertFalse(rule.update(item));
        assertEquals(6, item.sellIn); // unchanged
        assertEquals(10, item.quality); // unchanged
    }

    @Test
    public void testUpdate_CustomSellInIncrement() {
        sellIn.setInc(-2);

        Item item = new Item("",10, 10);

        assertTrue(rule.update(item));
        assertEquals(8, item.sellIn); // custom inc is -2
    }

    @Test
    public void testUpdate_CustomQualityIncrement() {
        quality.setInc(2);

        Item item = new Item("",10, 10);

        assertTrue(rule.update(item));
        assertEquals(12, item.quality); // custom inc is 2
    }

    @Test
    public void testUpdate_QualityExceedsMax() {
        quality.setInc(10);
        quality.setMax(15);

        Item item = new Item("",10, 10);

        assertTrue(rule.update(item));
        assertEquals(15, item.quality); // quality should not exceed max
    }

    @Test
    public void testToString() {
        String expected = "Rule{" +
            "quality=" + quality +
            ", sellIn=" + sellIn +
            '}';

        assertEquals(expected, rule.toString());
    }

    @Test
    public void testGettersAndSetters() {
        Quality newQuality = new Quality();
        SellIn newSellIn = new SellIn();

        rule.setQuality(newQuality);
        rule.setSellIn(newSellIn);

        assertEquals(newQuality, rule.getQuality());
        assertEquals(newSellIn, rule.getSellIn());
    }
}

