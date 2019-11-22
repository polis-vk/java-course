package ru.mail.polis.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
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

    public static void main(String[] args) {
        example();
//        badParallelStream();

//        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
//        sumReduce(numbers);
//        System.out.println("---");
//        sumParallelReduce(numbers);
//        System.out.println("---");
//        sumParallelReduceOther(numbers);
    }


    public static void example() {
        Collection<String> names = Arrays.asList("Alex", "Maria", "Anna", "Petr", "N", null);
        AtomicInteger d = new AtomicInteger(0);
        AtomicInteger a = new AtomicInteger(0);
        AtomicInteger b = new AtomicInteger(0);
        AtomicInteger c = new AtomicInteger(0);
        List<Character> chars = null;
        Stream<Character> stream =  names.stream()
                .filter(Objects::nonNull)
                .filter(name -> name.length() > 3)
                .map(String::toLowerCase)
                .flatMapToInt(String::chars)
                .peek(i -> a.incrementAndGet())
                .sorted()
//                .distinct()
                .peek(i -> b.incrementAndGet())
                .skip(2)
                .peek(i -> c.incrementAndGet())
                .limit(3)
                .peek(i -> d.incrementAndGet())
                .boxed()
                .map(ch -> (char) ((int) ch));
//                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
//        for (Character aChar : chars) {
//            sb.append((char) aChar).append(" ");
//        }
//        System.out.println(sb);
        System.out.println("a = " + a + ", b = " + b + ", c = " + c + ", d = " + d);
    }

    private static void badParallelStream() {
        final List<Integer> ints = new ArrayList<>();
        IntStream.range(0, 1000000)
                .parallel()
                .forEach(ints::add);
        System.out.println(ints.size());
    }

    public static int sumReduce(List<Integer> numbers) {
        return numbers.stream()
                .reduce(0,
                        (acc, elem) -> {
                            System.out.println("accumulator: " + acc + ", " + elem);
                            return acc + elem;
                        });
    }

    public static int sumParallelReduce(List<Integer> numbers) {
        return numbers.parallelStream()
                .reduce(0,
                        (acc, elem) -> {
                            System.out.println("accumulator: " + acc + ", " + elem);
                            return acc + elem;
                        });
    }

    public static int sumParallelReduceOther(List<Integer> numbers) {
        return numbers.parallelStream()
                .reduce(0,
                        (acc, elem) -> {
                            System.out.println("accumulator: " + acc + ", " + elem);
                            return acc + elem;
                        },
                        (left, right) -> {
                            System.out.println("combiner: " + left + ", " + right);
                            return left + right;
                        });
    }
}
