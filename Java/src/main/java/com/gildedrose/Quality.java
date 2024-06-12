package com.gildedrose;

public class Quality {
    private int inc = -1;
    private int multiplier = 1;
    private int min = 0;
    private int max = 50;

    public int calc(int quality) {
        int result = quality + inc * multiplier;
        if (result > max) {
            result = max;
        } else if (result < min) {
            result = min;
        }
        return result;

    }

    public int getInc() {
        return inc;
    }

    public void setInc(int inc) {
        this.inc = inc;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return "Quality{" + "inc=" + inc + ", multiplier=" + multiplier + ", min=" + min + ", max=" + max + '}';
    }
}
