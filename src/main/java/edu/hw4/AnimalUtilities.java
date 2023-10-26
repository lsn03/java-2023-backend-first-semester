package edu.hw4;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public static Map<Animal.Type, Integer> getCounterOfAnimalTypeTask3(List<Animal> list) {
        return list.stream().collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(elem -> 1)));
    }

    public static Animal getTheLongestName(List<Animal> list) {
        return list.stream().max((animal1, animal2) -> Integer.compare(animal1.name().length(), animal2.name().length())).orElse(null);
    }
    public static Animal.Sex getTheMostPopularSex(List<Animal> list) {
        long males = list.stream().filter(animal -> animal.sex() == Animal.Sex.M).count();
        long females = list.stream().filter(animal -> animal.sex() == Animal.Sex.F).count();

        if(males>=females){
            return Animal.Sex.M;
        }else {
            return Animal.Sex.F;
        }


    }
}
