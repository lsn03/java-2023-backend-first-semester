package edu.project3;

import java.net.URI;
import java.util.List;

public class LogParser {
    public static List<LogRecord> parseLogs(String path){
        if(path.startsWith("http")){
            return parseLogsFromHttp(path);
        }
    }

    private static List<LogRecord> parseLogsFromHttp(String path) {
        try {

        }
        return null;
    }

    public static List<LogRecord> parseLogs(String uri){

    }
}
