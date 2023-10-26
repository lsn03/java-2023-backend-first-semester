package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public final class AnimalUtilities {

    private AnimalUtilities() {
    }
    public static List<Animal> sortHeight(List<Animal> list){
        return list.stream().sorted((o1, o2) -> {
            if(o1.height()>o2.height()){
                return 1;
            }else {
                return o1.height() < o2.height()? -1 : 0;
            }

        }).toList();
    }
}
