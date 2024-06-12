package com.gildedrose;

public class SellIn {
    private int inc = -1;
    private int min = Integer.MIN_VALUE;
    private int max = Integer.MAX_VALUE;

    public boolean isApplicable(int sellIn) {
        return sellIn > min && sellIn <= max;
    }

    public int calc(int sellIn) {
        return sellIn + inc;
    }

    public int getInc() {
        return inc;
    }

    public void setInc(int inc) {
        this.inc = inc;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    @Override
    public String toString() {
        return "SellIn{" +
                "inc=" + inc +
                ", min=" + min +
                ", max=" + max +
                '}';
    }
}
