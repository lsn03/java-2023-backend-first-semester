package edu.hw4;

public record Animal(
        String name,
        Type type,
        Sex sex,
        int age,
        int height,
        int weight,
        boolean bites
) {
    enum Type {
        CAT, DOG, BIRD, FISH, SPIDER
    }

    enum Sex {

        M, F
    }

    private static final int MAX_PAWS_DOG_CAT = 4;
    private static final int MAX_PAWS_BIRD = 2;
    private static final int MAX_PAWS_SPIDER = 8;
    private static final int MAX_PAWS_FISH = 0;

    public int paws() {

        return switch (type) {
            case CAT, DOG -> MAX_PAWS_DOG_CAT;
            case BIRD -> MAX_PAWS_BIRD;
            case FISH -> MAX_PAWS_FISH;
            case SPIDER -> MAX_PAWS_SPIDER;
        };
    }
}
