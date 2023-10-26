package edu.hw4;

import java.util.List;

public final class AnimalUtilities {

    private AnimalUtilities() {
    }

    public static List<Animal> sortHeightTask1(List<Animal> list) {
        return list.stream().sorted((o1, o2) -> {
            if (o1.height() > o2.height()) {
                return 1;
            } else {
                return o1.height() < o2.height() ? -1 : 0;
            }

        }).toList();
    }

    public static List<Animal> sortWeightTask2(List<Animal> list, int k) {
        return list.stream().sorted((o1, o2) -> {
            if (o1.weight() < o2.weight()) {
                return 1;
            } else {
                return o1.weight() > o2.weight() ? -1 : 0;
            }

        }).limit(k).toList();
    }
}
