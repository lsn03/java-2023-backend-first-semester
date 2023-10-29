package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public final class AnimalUtilities {

    private AnimalUtilities() {
    }

    public static List<Animal> sortHeightTask1(List<Animal> list) {
        nullChecker(list);
        return list.stream().sorted((o1, o2) -> {
            if (o1.height() > o2.height()) {
                return 1;
            } else {
                return o1.height() < o2.height() ? -1 : 0;
            }

        }).toList();
    }

    public static List<Animal> sortWeightTask2(List<Animal> list, int k) {
        nullChecker(list);
        return list.stream().sorted((o1, o2) -> {
            if (o1.weight() < o2.weight()) {
                return 1;
            } else {
                return o1.weight() > o2.weight() ? -1 : 0;
            }

        }).limit(k).toList();
    }

    public static Map<Animal.Type, Integer> getCounterOfAnimalTypeTask3(List<Animal> list) {
        nullChecker(list);
        return list.stream().collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(elem -> 1)));
    }

    public static Animal getTheLongestNameTask4(List<Animal> list) {
        nullChecker(list);
        return list.stream().max(Comparator.comparingInt(animal -> animal.name().length())).orElse(null);
    }

    public static Animal.Sex getTheMostPopularSexTask5(List<Animal> list) {
        nullChecker(list);
        long males = list.stream().filter(animal -> animal.sex() == Animal.Sex.M).count();
        long females = list.stream().filter(animal -> animal.sex() == Animal.Sex.F).count();

        if (males >= females) {
            return Animal.Sex.M;
        } else {
            return Animal.Sex.F;
        }


    }

    public static Map<Animal.Type, Animal> getTheWeightAnimalOfTypeTask6(List<Animal> list) {

        nullChecker(list);
        return list.stream().collect(
                Collectors.toMap(
                        Animal::type,
                        animal -> animal,
                        (existing, replacement) ->
                                existing.weight() >= replacement.weight() ? existing : replacement
                )
        );

    }

    public static Animal getTheKTheMostOldAnimalTask7(List<Animal> list, int k) {

        nullChecker(list);
        if (k < 0 || k > list.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }

        var newlist = list.stream().sorted(Comparator.comparingInt(Animal::age).reversed()).toList();
        return newlist.get(k);

    }

    public static Optional<Animal> getTheMostWeightAnimalLessNHeightTask8(List<Animal> list, int height) {

        nullChecker(list);
        if (height < 0) {
            throw new IllegalArgumentException("The height cannot be less than zero");
        }

        return list.stream().filter(animal -> animal.height() < height).max(Comparator.comparingInt(Animal::weight));

    }

    public static Integer getTheCountOFPawsTask9(List<Animal> list) {
        nullChecker(list);
        return list.stream().collect(Collectors.summingInt(Animal::paws));

    }

    public static List<Animal> getAnimalWithAgeNotEqualPawsTask10(List<Animal> list) {
        nullChecker(list);
        return list.stream().filter(animal -> animal.age() != animal.paws()).toList();

    }

    @SuppressWarnings("magicnumber")
    public static List<Animal> getAnimalsWhoCanBiteAndToldTask11(List<Animal> list) {

        nullChecker(list);
        int height = 100;
        return list.stream().filter(animal -> animal.bites() && animal.height() > height).toList();

    }

    public static Integer getCountOfAnimalWhereWeightMoreThanHeightTask12(List<Animal> list) {

        nullChecker(list);

        return list.stream().filter(animal -> animal.weight() > animal.height()).toList().size();

    }

    public static List<Animal> getAnimalsWithNameMoreThan2WordTask13(List<Animal> list) {

        nullChecker(list);

        return list.stream().filter(animal -> animal.name().split(" ").length > 1).toList();

    }

    public static boolean isExistDogWithKHeightTask14(List<Animal> list, int k) {

        nullChecker(list);

        return !(list.stream().filter(
                animal -> animal.type() == Animal.Type.DOG && animal.height() > k
        ).toList().isEmpty());

    }

    public static Integer getAnimalWeightWithRangeOfAgeTask15(List<Animal> list, int left, int right) {

        nullChecker(list);
        if (left > right || left < 0) {
            throw new IllegalArgumentException("The left and right should be in range [left,right] and left >= 0");
        }

        return list.stream().filter(
                animal -> animal.age() >= left && animal.age() <= right
        ).mapToInt(Animal::weight).sum();

    }

    public static List<Animal> sortedByTypeThenSexThenNameTask16(List<Animal> list) {

        nullChecker(list);

        return list.stream().sorted(
                Comparator.comparing(Animal::type)
                        .thenComparing(Animal::sex)
                        .thenComparing(Animal::name)
        ).toList();

    }

    public static boolean isSpiderBitesMoreThanDogsTask17(List<Animal> list) {

        nullChecker(list);
        long dogCount = list.stream().filter(animal -> animal.type() == Animal.Type.DOG && animal.bites()).count();
        long spiderCount = list.stream().filter(
                animal -> animal.type() == Animal.Type.SPIDER && animal.bites()
        ).count();
        return spiderCount > dogCount;

    }

    public static Animal getTheMostWeightFishTask18(List<List<Animal>> list) {
        Objects.requireNonNull(list);

        return list.stream().flatMap(
                List::stream).filter(
                animal -> animal.type() == Animal.Type.FISH
        ).max(Comparator.comparingInt(Animal::weight)).orElse(null);

    }

    public static void nullChecker(List<Animal> list) {
        Objects.requireNonNull(list);
        if (list.contains(null)) {
            throw new NullPointerException();
        }
    }
}
