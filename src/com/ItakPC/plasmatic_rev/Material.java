package com.ItakPC.plasmatic_rev;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Material {

    public static final Material GRASS = new Material("grass");

    private List<String> reseourceIds;

    public Material(String resourceId) {
        reseourceIds = new ArrayList<String>();
        reseourceIds.add(resourceId);
    }

    public String getResourceId(int i) {
        return reseourceIds.get(i);
    }
    Random random = new Random();
    public String getRandomResourceId() {
        return reseourceIds.get(random.nextInt(reseourceIds.size()));
    }
}
