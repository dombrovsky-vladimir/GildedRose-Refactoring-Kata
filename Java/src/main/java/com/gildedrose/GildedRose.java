package com.gildedrose;

class GildedRose {
    Item[] items;

    Templates templates;

    public GildedRose(Item[] items) {
        this.items = items;
        templates = new Templates();
    }

    public void updateQuality() {
        try {
            for (Item item : items) {
                templates.updateItem(item);
            }
        } catch (Exception e) {
            System.out.println("Something went wrong! " + e.getMessage());
        }
    }

}