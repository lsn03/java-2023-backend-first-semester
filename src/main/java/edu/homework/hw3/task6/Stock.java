package edu.homework.hw3.task6;

public class Stock {
    private final Integer price;
    private final String company;

    public Stock(Integer price, String company) {
        this.price = price;
        this.company = company;
    }

    public Integer getPrice() {
        return price;
    }

    public String getCompany() {
        return company;
    }
}
