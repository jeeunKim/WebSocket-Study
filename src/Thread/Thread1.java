package Thread;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Thread1 extends Thread{

    @Override
    public void run() {
        List<String> stringList = Arrays.asList("apple", "banana", "cherry", "ddalggi", "elephant");
        Stream<String> stream = stringList.stream();

        stream.filter(s -> s.contains("a"))
            .forEach(System.out::println);
    }

}
