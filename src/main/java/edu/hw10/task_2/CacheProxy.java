package edu.hw10.task_2;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CacheProxy implements InvocationHandler {
    private final Object target;
    private final String pathToDirectory;
    private final Map<Object, Object> inMemoryCache;

    private CacheProxy(Object target, String pathToDirectory) {
        this.target = target;
        this.pathToDirectory = pathToDirectory;
        inMemoryCache = new HashMap<>();
    }

    public static <T> T create(Object target, Class<?> interfac) {
        return (T) Proxy.newProxyInstance(interfac.getClassLoader(),
                new Class<?>[]{interfac},
//                , target.getClass().getInterfaces(),
                new CacheProxy(target, ""));
    }

    public static <T> T create(Object target, Class<?> interfac, String pathToDirectory) {
        return (T) Proxy.newProxyInstance(interfac.getClassLoader(),
                new Class<?>[]{interfac},
//                , target.getClass().getInterfaces(),
                new CacheProxy(target, pathToDirectory));
    }

    public Object getTarget() {
        return target;
    }

    public String getPathToDirectory() {
        return pathToDirectory;
    }

    public Map<Object, Object> getInMemoryCache() {
        return inMemoryCache;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Cache cache = method.getAnnotation(Cache.class);
        Objects.requireNonNull(cache);

        if (cache.persist()) {
            return cacheToDisk(method, args, cache.filePath());
        } else {
            return cacheToMemory(method, args);
        }

    }

    private Object cacheToMemory(Method method, Object[] args) {
        int argsHashCode = Arrays.hashCode(args);
        if (inMemoryCache.containsKey(argsHashCode)) {
            return inMemoryCache.get(argsHashCode);

        } else {
            try {
                Object result = method.invoke(target, args);
                inMemoryCache.put(argsHashCode, result);
                return result;
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException(e);
            }

        }
    }

    private Object cacheToDisk(Method method, Object[] args, String filePath) {
        Path path;
        if (filePath.isBlank() || filePath.isEmpty()) {
            path = Path.of(this.pathToDirectory + target.getClass().getSimpleName() + ".txt");
        } else {
            path = Path.of(filePath);
        }

        if (!Files.exists(path)) {
            createFile(path);
        }

        try {
            var returnType = method.getReturnType();
            var results = readDataFromFile(returnType, path);
            Object argsHashCode = castValue(Arrays.hashCode(args), returnType);
            if (results.containsKey(argsHashCode)) {
                return results.get(argsHashCode);
            } else {
                Object result = method.invoke(target, args);
                results.put(argsHashCode, result);
                writeDataToFile(path, results);
                return result;
            }


        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }


    }

    private static Map<Object, Object> readDataFromFile(Class<?> returnType, Path path) {
        try {
            if (!Files.exists(path)) {
                createFile(path);
                return new HashMap<>();
            } else {
                List<String> res = Files.readAllLines(path);
                Map<Object, Object> resultMap = new HashMap<>();
                for (var line : res) {
                    String[] parts = line.split(":");
                    if (parts.length != 2) {
                        throw new IllegalArgumentException();
                    }
                    Object key = castValue(parts[0], returnType);
                    Object value = castValue((parts[1]), returnType);
                    resultMap.put(key, value);
                }
                return resultMap;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Object castValue(Object stringValue, Class<?> returnType) {
        if (returnType == int.class) {
            return Integer.parseInt(String.valueOf(stringValue));
        } else if (returnType == long.class) {
            return Long.parseLong(String.valueOf(stringValue));
        } else if (returnType == double.class) {
            return Double.parseDouble(String.valueOf(stringValue));
        } else {

            return stringValue;
        }
    }

    private static void writeDataToFile(Path path, Map<Object, Object> data) {
        try {
            List<String> lines = new ArrayList<>();

            for (Map.Entry<Object, Object> entry : data.entrySet()) {

                String line = entry.getKey() + ":" + entry.getValue();
                lines.add(line);
            }


            Files.write(path, lines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createFile(Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        FibImpl fib = new FibImpl();
        FibCalculator fibProxy = CacheProxy.create(fib, FibCalculator.class, "src/main/resources/");
        System.out.println(fibProxy.fib(7));

        System.out.println(fibProxy.fib(7));
        System.out.println(fibProxy.fib(8));
        System.out.println(fibProxy.fib(9));
        SummatorImpl summator = new SummatorImpl();
        Summator summatorProxy = CacheProxy.create(summator, Summator.class);
        System.out.println(summatorProxy.summ(5, 5));
        System.out.println(summatorProxy.summ(5, 5));
        System.out.println(summatorProxy.summ(5, 10));
        System.out.println(summatorProxy.summ(25, 25));
    }
}
