package com.gildedrose;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TemplatesTest {

    private Templates templates;

    @BeforeEach
    public void setUp() {
        InputStream inputStream = TemplatesTest.class.getResourceAsStream("/templates.yaml");
        templates = new Templates(inputStream);
    }

    @Test
    public void testLoadFromYaml() {
        assertFalse(templates.map.isEmpty());
        assertTrue(templates.map.containsKey("__ORDINARY_GOODS__"));
    }

    @Test
    public void testParseRule() {
        Map<String, Object> ruleMap = Map.of(
            "quality", Map.of("inc", 1, "min", 0, "max", 50),
            "sell-in", Map.of("inc", -1, "min", 0, "max", 10)
        );

        Rule rule = Templates.parseRule(ruleMap);

        assertNotNull(rule);
        assertEquals(1, rule.getQuality().getInc());
        assertEquals(0, rule.getQuality().getMin());
        assertEquals(50, rule.getQuality().getMax());
        assertEquals(-1, rule.getSellIn().getInc());
        assertEquals(0, rule.getSellIn().getMin());
        assertEquals(10, rule.getSellIn().getMax());
    }

    @Test
    public void testUpdateItem_ValidTemplate() throws Templates.GildedRoseException {
        Item item = new Item("test_template", 5, 10);
        Rule ruleMock = mock(Rule.class);
        when(ruleMock.update(item)).thenReturn(true);

        templates.map.put("test_template", List.of(ruleMock));

        templates.updateItem(item);

        verify(ruleMock, times(1)).update(item);
    }

    @Test
    public void testUpdateItem_InvalidTemplate() {
        Item item = new Item("invalid_template", 5, 10);
        InputStream inputStream = TemplatesTest.class.getResourceAsStream("/templates-broken.yaml");
        templates = new Templates(inputStream);

        assertThrows(Templates.GildedRoseException.class, () -> templates.updateItem(item));
    }

    @Test
    public void testUpdateItem_OrdinaryGoods() throws Templates.GildedRoseException {
        Item item = new Item("ordinary_item", 5, 10);
        Rule ruleMock = mock(Rule.class);
        when(ruleMock.update(item)).thenReturn(true);

        templates.map.put(Templates.ORDINARY_GOODS, List.of(ruleMock));

        templates.updateItem(item);

        verify(ruleMock, times(1)).update(item);
    }
}


