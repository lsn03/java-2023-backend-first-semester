package edu.hw10.task_1;

import edu.hw10.task_1.annotations.Max;
import edu.hw10.task_1.annotations.Min;
import edu.hw10.task_1.annotations.NotNull;
import edu.hw10.task_1.example.CarClass;
import edu.hw10.task_1.example.PersonClass;
import edu.hw10.task_1.example.PersonRecord;
import edu.hw10.task_1.example.Rogable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Random;

public class RandomObjectGenerator {
    private final Random random;

    public RandomObjectGenerator(Random random) {
        this.random = random;
    }

    public RandomObjectGenerator(long seed) {
        random = new Random(seed);
    }

    public RandomObjectGenerator() {
        random = new Random();
    }

    public <T extends Rogable> T nextObject(Class<T> clas) throws ReflectiveOperationException {
        var constructors = clas.getDeclaredConstructors();
        for (var constructor : constructors) {
            int modifier = constructor.getModifiers();
            if (Modifier.isPublic(modifier)) {
                var constructorParams = constructor.getParameters();
                var fields = clas.getDeclaredFields();
                Object[] args = new Object[constructorParams.length];
                int index = 0;

                for (var param : constructorParams) {
                    var field = findFieldByName(fields, param.getName());
                    if (field != null) {
                        args[index++] = generateValue(param.getType(), field.getAnnotations());
                    }
                }
                return (T) constructor.newInstance(args);
            }
        }
        return null;
    }


    public <T extends Rogable> T nextObject(Class<T> clas, String factoryMethod) throws ReflectiveOperationException {
        if (factoryMethod == null || factoryMethod.isEmpty() || factoryMethod.isBlank()) {
            throw new IllegalArgumentException("Factory method is null or blank");
        }


        Method[] methods = clas.getDeclaredMethods();
        Method targetFactoryMethod = null;
        for (Method m : methods) {
            if (m.getName().equals(factoryMethod)) {
                targetFactoryMethod = m;
                break;
            }
        }
        if (targetFactoryMethod == null) {
            throw new NoSuchMethodException();
        }

        return (T) invokeFactoryMethod(targetFactoryMethod, clas.getDeclaredFields());
    }


    private <T> T invokeFactoryMethod(Method method, Field[] declaredFields) {
        if (!Modifier.isStatic(method.getModifiers())) {
            throw new IllegalArgumentException("Method " + method.getName() + " is not static");
        }
        Parameter[] params = method.getParameters();
        Object[] args = new Object[params.length];
        int index = 0;
        for (var param : params) {

            Field field = findFieldByName(declaredFields, param.getName());
            if (field == null) {
                continue;
            }
            Annotation[] annotations = field.getAnnotations();
            args[index++] = generateValue(field.getType(), annotations);
        }

        try {
            return (T) method.invoke(null, args);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    private static Field findFieldByName(Field[] fields, String name) {
        for (Field field : fields) {
            if (field.getName().equals(name)) {
                return field;
            }
        }
        return null;
    }

    private <T> Object generateValue(Class<T> type, Annotation[] annotations) {
        return switch (type.getName()) {
            case "java.lang.String" -> generateStringValue(annotations);
            case "int", "double" -> generateNumericValue(type, annotations);
            case "boolean" -> generateBooleanValue();
            case "byte" -> generateByteValue();
            case "char" -> generateCharValue();
            default -> null;
        };
    }

    private char generateCharValue() {
        return (char) random.nextInt(Character.MIN_VALUE, Character.MAX_VALUE);
    }

    private byte generateByteValue() {
        return (byte) random.nextInt(Byte.MIN_VALUE, Byte.MAX_VALUE);
    }

    private boolean generateBooleanValue() {
        return random.nextBoolean();
    }

    private Object generateStringValue(Annotation[] annotations) {
        boolean notNull = findNotNull(annotations);
        int min = findMinAnnotation(annotations);
        int max = findMaxAnnotation(annotations);

        if (notNull) {
            if (min == Integer.MIN_VALUE && max == Integer.MAX_VALUE) {
                return "RandomString_" + random.nextInt();
            } else {
                return generateString(min, max);
            }
        } else {
            return null;
        }
    }

    private Object generateNumericValue(Class<?> type, Annotation[] annotations) {
        int min = findMinAnnotation(annotations);
        int max = findMaxAnnotation(annotations);

        validateMinMax(min, max);

        if (type == int.class) {
            return random.nextInt(min, max);
        } else if (type == double.class) {
            return random.nextDouble(min, max);
        }

        return null;
    }

    private static void validateMinMax(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException();
        }
    }

    private String generateString(int minLength, int maxLength) {
        validateMinMax(minLength, maxLength);
        if (minLength == Integer.MIN_VALUE) {
            minLength = 0;
        }
        maxLength = maxLength == Integer.MAX_VALUE ? minLength + 2 : maxLength;

        int length = random.nextInt(minLength, maxLength);
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < length; i++) {
            result.append(generateCharValue());
        }

        return result.toString();
    }

    private boolean findNotNull(Annotation[] annotations) {
        for (var annotation : annotations) {
            if (annotation instanceof NotNull) {
                return true;
            }
        }
        return false;
    }

    private int findMinAnnotation(Annotation[] annotations) {
        for (var annotation : annotations) {
            if (annotation instanceof Min) {
                return ((Min) annotation).value();
            }
        }
        return Integer.MIN_VALUE;
    }

    private int findMaxAnnotation(Annotation[] annotations) {
        for (var annotation : annotations) {
            if (annotation instanceof Max) {
                return ((Max) annotation).value();
            }
        }
        return Integer.MAX_VALUE;
    }

    public static void main(String[] args) throws ReflectiveOperationException {
        RandomObjectGenerator rog = new RandomObjectGenerator();

        Rogable rogable1 = rog.nextObject(CarClass.class);
        Rogable rogable2 = rog.nextObject(PersonClass.class, "create");
        Rogable rogable3 = rog.nextObject(PersonRecord.class);
        System.out.println(rogable1);
        System.out.println(rogable2);
        System.out.println(rogable3);

    }
}
