package edu.hw2.Task1;

public sealed interface Expr {
    double evaluate();

    record Constant(double value) implements Expr {

        @Override
        public double evaluate() {
            return value;
        }

        @Override
        public String toString() {
            return "Constant{"
                    + "value= " + evaluate() + '}';
        }
    }

    record Negate(Expr operand) implements Expr {
        @Override
        public double evaluate() {
            return -operand.evaluate();
        }

        @Override
        public String toString() {
            return "Negate{"
                    + "value= " + evaluate() + '}';
        }
    }

    record Exponent(Expr base, int exponent) implements Expr {
        @Override
        public double evaluate() {
            return Math.pow(base().evaluate(), exponent);
        }

        @Override
        public String toString() {
            return "Exponent{"
                    + "value = " + base.evaluate() + "; exponent = " + exponent
                    + "; result = " + evaluate() + '}';
        }
    }

    record Addition(Expr left, Expr right) implements Expr {
        @Override
        public double evaluate() {
            return left.evaluate() + right.evaluate();
        }

        @Override
        public String toString() {
            return "Addition{" +
                    "left = " + left.evaluate() + "; right = " + right.evaluate() + "; result = " + evaluate() +
                    '}';
        }
    }

    record Multiplication(Expr left, Expr right) implements Expr {
        @Override
        public double evaluate() {
            return left.evaluate() * right.evaluate();
        }

        @Override
        public String toString() {
            return "Multiplication{" +
                    "left = " + left.evaluate() + "; right = " + right.evaluate() + "; result = " + evaluate() +
                    '}';
        }
    }
}
