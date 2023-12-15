package edu.project5;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;


@State(Scope.Thread)
public class RefBanchmark {

    public static final int WARM_UP_TIME = 5;
    public static final int ITERATIONS = 10;
    public static final int SECONDS = 3;
    public static final String METHOD_NAME_SIMPLE_NAME = "name";

    private Student student;
    private Method reflectionMethod;
    private MethodHandle methodHandle;
    private Supplier<String> lambdaMethod;



    @Setup
    public void setup() throws Throwable {
        student = new Student("Sergey", "Lvov");
        reflectionMethod = Student.class.getMethod(METHOD_NAME_SIMPLE_NAME);


        MethodHandles.Lookup lookup = MethodHandles.lookup();
        methodHandle = lookup.unreflect(reflectionMethod);

        lookup = MethodHandles.lookup();

        CallSite site = LambdaMetafactory.metafactory(
                lookup,
                "get",
                MethodType.methodType(Supplier.class, Student.class),
                MethodType.methodType(Object.class),
                lookup.findVirtual(Student.class, METHOD_NAME_SIMPLE_NAME, MethodType.methodType(String.class)),
                MethodType.methodType(String.class)
        );


        lambdaMethod = (Supplier<String>) site.getTarget().invokeExact(student);

    }

    @Benchmark
    public void directAccess(Blackhole bh) {
        String name = student.name();
        bh.consume(name);
    }

    @Benchmark
    public void reflectAccess(Blackhole bh) throws InvocationTargetException, IllegalAccessException {
        String name = (String) reflectionMethod.invoke(student);
        bh.consume(name);
    }

    @Benchmark
    public void invokeAccess(Blackhole bh) throws Throwable {
        String name = (String) methodHandle.invoke(student);
        bh.consume(name);
    }

    @Benchmark
    public void lambdaMetafactoryAccess(Blackhole bh) {
        String name = lambdaMethod.get();
        bh.consume(name);
    }

    public static void run() throws RunnerException {
        Options options = new OptionsBuilder()
                .include(RefBanchmark.class.getSimpleName())
                .shouldFailOnError(true)
                .shouldDoGC(true)
                .mode(Mode.AverageTime)
                .timeUnit(TimeUnit.NANOSECONDS)
                .forks(1)
                .warmupForks(1)
                .warmupIterations(1)
                .warmupTime(TimeValue.seconds(WARM_UP_TIME))
                .measurementIterations(ITERATIONS)
                .measurementTime(TimeValue.seconds(SECONDS))
                .build();
        new Runner(options).run();
    }

    record Student(String name, String surname) {

    }

}
