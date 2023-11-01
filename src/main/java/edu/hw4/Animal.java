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
        CAT(4), DOG(4), BIRD(2), FISH(0), SPIDER(8);
        private final int paw;
        Type(int paw){
            this.paw = paw;
        }
        public int getPaw(){
            return paw;
        }
    }

    enum Sex {

        M, F
    }

    public int paws() {
        return type.getPaw();
    }
}
