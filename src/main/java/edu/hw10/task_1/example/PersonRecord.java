package edu.hw10.task_1.example;

import edu.hw10.task_1.annotations.Max;
import edu.hw10.task_1.annotations.Min;
import edu.hw10.task_1.annotations.NotNull;

public record PersonRecord(@NotNull @Min(5) String name, @Min(0) @Max(110) int age, int salary) implements Rogable {
    @Override
    public String toString() {
        return "PersonRecord{"
                + "name='" + name + '\''
                + ", age=" + age
                + ", salary=" + salary
                + '}';
    }

}
