package edu.hw3;

import edu.hw3.task6.Stock;
import edu.hw3.task6.StockMarket;
import edu.hw3.task6.StockMarketConcret;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class StockMarketTest {
    @Test
    public void testMostValuableStockEmptyMarket() {
        StockMarket stockMarket = new StockMarketConcret();
        assertNull(stockMarket.mostValuableStock());
    }
    @ParameterizedTest
    @CsvSource({
            "AAPL, 150",
            "GOOGL, 2500",
            "AMZN, 3500",
            "TSLA, 800"
    })
    public void testMostValuableStockWithStocks(String symbol, int price) {
        StockMarket stockMarket = new StockMarketConcret();
        Stock stock = new Stock(price,symbol);
        stockMarket.add(stock);

        assertEquals(stock, stockMarket.mostValuableStock());
    }
    @Test
    public void testMostValuableStockWithOtherStocks() {
        StockMarket stockMarket = new StockMarketConcret();
        int price = 100;
        String symbol = "AAPL";

        Stock stockHigh = new Stock(price,symbol);

        stockMarket.add(stockHigh);
        assertEquals(stockHigh, stockMarket.mostValuableStock());

        Stock newHigh =  new Stock(150,"DOSK");

        Stock[] stocks = new Stock[]{new Stock(10,"GOOGL"),newHigh};

        for (int i = 0; i < stocks.length; i++) {
            stockMarket.add(stocks[i]);
        }

        assertEquals(newHigh,stockMarket.mostValuableStock());


    }

    @Test
    public void testMostValuableStockWithOtherStocksWithRemove() {
        StockMarket stockMarket = new StockMarketConcret();

        String name  = "AAPL";
        int price  = 150;
        String name2  = "GOOGL";
        int price2 = 2500;
        String name3  = "AMZN";
        int price3 = 3500;
        String name4  = "TSLA";
        int price4  = 800;

        Stock[] stocks = new Stock[]{new Stock(price,name),new Stock(price2,name2),new Stock(price3,name3),new Stock(price4,name4)};


        Stock stockToRemove = stocks[3];
        Stock stockToMax = stocks[0];

        stockMarket.add(stockToMax);
        stockMarket.add(stockToRemove);

        stockMarket.remove(stockToRemove);

        assertEquals( stockToMax, stockMarket.mostValuableStock());

        stockMarket.remove(stockToMax);
        assertNull(stockMarket.mostValuableStock());

        for (int i = 0; i < stocks.length; i++) {
            stockMarket.add(stocks[i]);
        }
        stockToMax = stocks[1];
        stockToRemove = stocks[2];
        stockMarket.remove(stockToRemove);
        assertEquals(stockToMax,stockMarket.mostValuableStock());

    }
}
