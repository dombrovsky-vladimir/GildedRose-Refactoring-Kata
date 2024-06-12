package com.gildedrose;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Templates {
    final Map<String, List<Rule>> map = new HashMap<>();
    public static final String ORDINARY_GOODS = "__ORDINARY_GOODS__";


    public Templates(String config) {
        InputStream inputStream = Templates.class.getResourceAsStream("/" + config);
        loadFromYaml(inputStream);
    }

    public Templates(InputStream inputStream) {
        loadFromYaml(inputStream);
    }

    public Templates() {
        this("templates.yaml");
    }

    public void loadFromYaml(InputStream inputStream) {
        Yaml yaml = new Yaml(new Constructor(new LoaderOptions()));
        Map<String, Object> yamlMap = yaml.load(inputStream);

        List<Map<String, Object>> items = (List<Map<String, Object>>) yamlMap.get("templates");
        items.forEach(item -> {
            List<Map<String, Object>> ruleList = (List<Map<String, Object>>) item.get("rules");
            List<Rule> rules = ruleList.stream().map(Templates::parseRule).toList();
            map.put((String) item.get("name"), rules);
        });

    }

    static Rule parseRule(Map<String, Object> ruleMap) {
        Rule rule = new Rule();
        rule.setQuality(parseQuality(ruleMap));
        rule.setSellIn(parseSellIn(ruleMap));
        return rule;
    }

    private static SellIn parseSellIn(Map<String, Object> ruleMap) {
        Map<String, Object> saleInMap = (Map<String, Object>) ruleMap.get("sell-in");
        SellIn sellIn = new SellIn();
        if (saleInMap != null) {
            if (saleInMap.containsKey("inc")) {
                sellIn.setInc((Integer) saleInMap.get("inc"));
            }
            if (saleInMap.containsKey("min")) {
                sellIn.setMin((Integer) saleInMap.get("min"));
            }
            if (saleInMap.containsKey("max")) {
                sellIn.setMax((Integer) saleInMap.get("max"));
            }
        }
        return sellIn;
    }

    private static Quality parseQuality(Map<String, Object> ruleMap) {
        Map<String, Object> qualityMap = (Map<String, Object>) ruleMap.get("quality");
        Quality quality = new Quality();
        if (qualityMap != null) {
            if (qualityMap.containsKey("inc")) {
                quality.setInc((Integer) qualityMap.get("inc"));
            }
            if (qualityMap.containsKey("multiplier")) {
                quality.setMultiplier((Integer) qualityMap.get("multiplier"));
            }
            if (qualityMap.containsKey("min")) {
                quality.setMin((Integer) qualityMap.get("min"));
            }
            if (qualityMap.containsKey("max")) {
                quality.setMax((Integer) qualityMap.get("max"));
            }
            return quality;
        }
        return quality;
    }


    public void updateItem(Item item) throws GildedRoseException {
        List<Rule> rules = map.get(item.name);
        if (rules == null) {
            rules = map.get(ORDINARY_GOODS);
        }
        if (rules == null) {
            throw new GildedRoseException("Protocol do not contain '" + item.name + "'");
        } else {
            for (Rule rule: rules) {
                if (rule.update(item)) {
                    return;
                }
            }
        }
        throw new GildedRoseException("Protocol do not contain rule of '" + item.name + "' with sellIn=" + item.sellIn);
    }

    public static class GildedRoseException extends Exception {
        public GildedRoseException(String message) {
            super(message);
        }
    }
}
