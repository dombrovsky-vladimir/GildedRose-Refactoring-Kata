package com.gildedrose;

public class Rule {
    private Quality quality = new Quality();
    private SellIn sellIn = new SellIn();

    public boolean update(Item item) {
        if (sellIn.isApplicable(item.sellIn)) {
            item.quality = quality.calc(item.quality);
            item.sellIn = sellIn.calc(item.sellIn);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "quality=" + quality +
                ", sellIn=" + sellIn +
                '}';
    }

    public Quality getQuality() {
        return quality;
    }

    public void setQuality(Quality quality) {
        this.quality = quality;
    }

    public SellIn getSellIn() {
        return sellIn;
    }

    public void setSellIn(SellIn sellIn) {
        this.sellIn = sellIn;
    }
}
