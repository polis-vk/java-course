package ru.mail.polis.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExample {

    public static void create() {
        DoubleStream randomNumbers = DoubleStream.generate(Math::random);
        IntStream integers = IntStream.iterate(0, n -> n + 1);
        IntStream smallIntegers = IntStream.range(0, 100);
        IntStream concat = IntStream.concat(integers, smallIntegers);
        IntStream my = IntStream.of(1, 4, 6, 7, 10);
        Stream<String> empty = Stream.empty();
    }

    static AtomicInteger a = new AtomicInteger(0);
    static AtomicInteger b = new AtomicInteger(0);
    static AtomicInteger c = new AtomicInteger(0);
    static AtomicInteger d = new AtomicInteger(0);
    static AtomicInteger e = new AtomicInteger(0);
    static AtomicInteger f = new AtomicInteger(0);
    static AtomicInteger g = new AtomicInteger(0);


    public static void example() {
        Collection<String> names = Arrays.asList("Alex", "Maria", "Anna", "Petr", "N", null);
        Stream<Character> stream =  names.stream()
                .peek(i -> a.incrementAndGet())
                .filter(Objects::nonNull)
                .filter(name -> name.length() > 3)
                .peek(i -> b.incrementAndGet())
                .map(String::toLowerCase)
                .flatMapToInt(String::chars)
                .peek(i -> c.incrementAndGet())
                .sorted()
                .peek(i -> d.incrementAndGet())
                .distinct()
                .peek(i -> e.incrementAndGet())
                .skip(2)
                .peek(i -> f.incrementAndGet())
                .limit(5)
                .peek(i -> g.incrementAndGet())
                .boxed()
                .map(ch -> (char) ((int) ch));

        List<Character> chars = stream.collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        for (Character aChar : chars) {
            sb.append((char) aChar).append(" ");
        }
        System.out.println(sb);
        System.out.println("a = " + a + ", b = " + b + ", c = " + c + ", d = " + d + ", e = " + e + ", f = " + f + ", g = " + g);
    }


    public static void main(String[] args) {
//        example();
//        badParallelStream();

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        sumReduce(numbers);
        System.out.println("---");
        sumParallelReduce(numbers);
        System.out.println("---");
        sumParallelReduceOther(numbers);
    }

    private static void badParallelStream() {
        final List<Integer> ints = new ArrayList<>();
        IntStream.range(0, 1000000)
                .parallel()
                .forEach(ints::add);
        System.out.println(ints.size());
//        (1, 2, 3, 4, 5)
        // res = 0
        //acum: res = function(res, element)
    }

    public static int sumReduce(List<Integer> numbers) {
        return numbers.stream()
                .reduce(0,
                        (res, elem) -> {
                            System.out.println("res: " + res + ", " + elem);
                            return res + elem;
                        });
    }

    public static int sumParallelReduce(List<Integer> numbers) {
        return numbers.parallelStream()
                .reduce(0,
                        (res, elem) -> {
                            System.out.println("res: " + res + ", " + elem);
                            return res + elem;
                        });
    }

    public static int sumParallelReduceOther(List<Integer> numbers) {
        return numbers.parallelStream()
                .reduce(0,
                        (res, elem) -> {
                            System.out.println("accumulator: " + res + ", " + elem);
                            return res + elem;
                        },
                        (left, right) -> {
                            System.out.println("combiner: " + left + ", " + right);
                            return left + right;
                        });
    }
}
