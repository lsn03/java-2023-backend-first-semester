package edu.hw10.task_1.example;

import edu.hw10.task_1.annotations.Max;
import edu.hw10.task_1.annotations.Min;
import edu.hw10.task_1.annotations.NotNull;

public class PersonClass implements Rogable {
    @NotNull
    @Max(5)
    private String name;
    @Min(0)
    @Max(110)
    private int age;
    @Min(10000)
    private int salary;


    private PersonClass(String name, int age, int salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public static PersonClass create(String name, int age, int salary) {
        return new PersonClass(name, age, salary);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "PersonClass{"
                + "name='" + name + '\''
                + ", age=" + age
                + ", salary=" + salary
                + '}';
    }
}
