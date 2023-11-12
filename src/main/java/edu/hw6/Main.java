package edu.hw6;

public class Main {
    public static void main(String[] args) {
        DiskMap diskMap = new DiskMap("idk.txt");
        var res = diskMap.getInMemoryMap();
        System.out.println(res);
        diskMap.remove("тест_кей_1");
        System.out.println(res);


    }
}
