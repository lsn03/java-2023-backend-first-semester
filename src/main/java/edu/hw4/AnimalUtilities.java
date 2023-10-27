package edu.hw4;

import java.util.Comparator;
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

    public static Animal getTheLongestNameTask4(List<Animal> list) {
        return list.stream().max(Comparator.comparingInt(animal -> animal.name().length())).orElse(null);
    }

    public static Animal.Sex getTheMostPopularSexTask5(List<Animal> list) {
        long males = list.stream().filter(animal -> animal.sex() == Animal.Sex.M).count();
        long females = list.stream().filter(animal -> animal.sex() == Animal.Sex.F).count();

        if (males >= females) {
            return Animal.Sex.M;
        } else {
            return Animal.Sex.F;
        }


    }

    public static Map<Animal.Type, Animal> getTheWeightAnimalOfTypeTask6(List<Animal> list) {

        return list.stream().collect(Collectors.toMap(
                Animal::type,
                animal -> animal,
                (existing, replacement) ->
                        existing.weight() >= replacement.weight() ? existing : replacement
        ));

    }
    public static Animal getTheKTheMostOldAnimalTask7(List<Animal> list,int k) {

        if(k<0 || k > list.size()){
            throw new ArrayIndexOutOfBoundsException();
        }

        var newlist =  list.stream().sorted(Comparator.comparingInt(Animal::age).reversed()).toList();
        return newlist.get(k);

    }
}
