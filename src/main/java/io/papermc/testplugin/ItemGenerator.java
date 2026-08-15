package io.papermc.testplugin;



import java.util.ArrayList;

import java.util.Random;

public class ItemGenerator {

    private ArrayList<String> items;
    public ItemGenerator(ArrayList<String> items) {
        this.items = items;
    }


    public String generateNextItem() {
        Random random = new Random();
        int randomIndex = random.nextInt(items.size());
        return items.get(randomIndex);
    }
}
