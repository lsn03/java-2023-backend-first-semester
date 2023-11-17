package edu.project3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
    public static void main(String[] args) {
        String commandLine = String.join(" ", args);
        System.out.println(commandLine);

        Pattern pathPattern = Pattern.compile("--path\\s+(\\S+)");
        Pattern fromPattern = Pattern.compile("--from\\s+(\\S+)");
        Pattern toPattern = Pattern.compile("--to\\s+(\\S+)");
        Pattern formatPattern = Pattern.compile("--format\\s+(\\S+)");


        Matcher pathMatcher = pathPattern.matcher(commandLine);
        Matcher fromMatcher = fromPattern.matcher(commandLine);
        Matcher toMatcher = toPattern.matcher(commandLine);
        Matcher formatMatcher = formatPattern.matcher(commandLine);


        String path = pathMatcher.find() ? pathMatcher.group(1) : null;
        String from = fromMatcher.find() ? fromMatcher.group(1) : null;
        String to = toMatcher.find() ? toMatcher.group(1) : null;
        String format = formatMatcher.find() ? formatMatcher.group(1) : null;

        
        System.out.println("Path: " + path);
        System.out.println("From: " + from);
        System.out.println("To: " + to);
        System.out.println("Format: " + format);


//        String logExample = "93.180.71.3 - - [17/May/2015:08:05:27 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"";
//        String regex = "^(\\S+) (\\S+) (\\S+) \\[([^\\]]+)\\] \"([^\"]+)\" (\\d+) (\\d+) \"([^\"]+)\" \"([^\"]+)\"";
//
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(logExample);
//
//        if (matcher.matches()) {
//            System.out.println("Match found!");
//            System.out.println("Group 1 (remote_addr): " + matcher.group(1));
//            System.out.println("Group 2 (remote_user): " + matcher.group(2));
//            System.out.println("Group 3: " + matcher.group(3));
//            System.out.println("Group 4 (time_local): " + matcher.group(4));
//            System.out.println("Group 5 (request): " + matcher.group(5));
//            System.out.println("Group 6 (status): " + matcher.group(6));
//            System.out.println("Group 7 (body_bytes_sent): " + matcher.group(7));
//            System.out.println("Group 8 (http_referer): " + matcher.group(8));
//            System.out.println("Group 9 (http_user_agent): " + matcher.group(9));
//        } else {
//            System.out.println("No match found.");
//        }
    }
}
