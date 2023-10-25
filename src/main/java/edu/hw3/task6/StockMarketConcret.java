package edu.hw3.task6;

import java.util.PriorityQueue;

public class StockMarketConcret implements StockMarket {
    private PriorityQueue<Stock> stockQueue;

    public StockMarketConcret() {
        stockQueue = new PriorityQueue<>((stock1, stock2) -> Integer.compare(stock2.getPrice(), stock1.getPrice()));
    }

    @Override
    public void add(Stock stock) {
        stockQueue.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        stockQueue.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return stockQueue.peek();
    }
}
