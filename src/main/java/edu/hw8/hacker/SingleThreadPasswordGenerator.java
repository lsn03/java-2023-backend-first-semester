package edu.hw8.hacker;

import java.util.HashMap;
import java.util.Map;

public class SingleThreadPasswordGenerator extends AbstractPasswordGenerator {

    public SingleThreadPasswordGenerator() {
        this(MyUtility.DEFAULT_LENGTH, MyUtility.fillWithDefaultLength());
    }

    public SingleThreadPasswordGenerator(Integer length, Map<String, String> passwordDatabase) {
        super(length, passwordDatabase);

        this.foundPasswords = new HashMap<>();

    }


    public void launch() {
        generatePasswords(0, maxCombinations);
    }


}
