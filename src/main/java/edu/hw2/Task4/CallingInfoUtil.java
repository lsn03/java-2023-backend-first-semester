package edu.hw2.Task4;

public class CallingInfoUtil {
    private CallingInfoUtil() {
    }

    public static CallingInfo callingInfo() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

        StackTraceElement element = stackTraceElements[2];
        String className = element.getClassName();
        String methodName = element.getMethodName();

        return new CallingInfo(className, methodName);


    }
}
