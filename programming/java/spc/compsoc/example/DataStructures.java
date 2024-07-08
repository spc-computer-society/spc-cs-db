package spc.compsoc.example;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.HashSet;
import java.util.Set;

public class DataStructures {
  public static void man(String[] args) {
    // The List interface
    List<Integer> list = new ArrayList<>();
    // Add elements to the list
    list.add(1);
    list.add(2);
     // Prints 1 and 2
    for(int i : list) {
      System.out.println(i);
    }
    List<Integer> sub = list.subList(1, list.size()); // Sublist only has 2 in it (equivalent to python [1:])
    sub.forEach(System.out::println);

    // The Map interface
    //Maps map a key to a value, and does not allow duplicate keys
    Map<String, Integer> ids = new HashMap<>();
    // Add pairs to the Map
    ids.put("Bash", 30624700);
    ids.put("Patchy", 30624770);
    ids.put("Zaggy", 514202);
    System.out.println(ids.get("Zaggy")); // Outputs 514202
    System.out.println(ids.get("Zappies")); // Outputs null for values that don't exist
    System.out.println(ids.getOrDefault("Zappies", 0)); // Outputs the default value given (0) if the given element does not exist
    System.out.println(ids.entrySet().stream().mapToInt(ent -> ent.getValue()).sum()); // Returns sum of all ids

    // The Queue interface (ArrayBlockingQueue has a fixed size (5 here))
    Queue<Long> l = new ArrayBlockingQueue<>(5);
    // Add elements to the queue (the `l` prefix denotes a long)
    l.add(1l);
    l.add(200l);
    System.out.println(l.peek()); // Outputs 1, in queue = {1, 200} (Gets, but does not remove the first element)
    System.out.println(l.poll()); // Outputs 1, in queue = {200} (Gets and removes the first element)

    // The Set interface
    Set<String> set = new HashSet<>();
    // Add elements to the set
    set.add("Hi");
    set.add("Hi");
    // No duplicates, so the set only contains 1 "Hi"
    System.out.println(set.size()); // 1
  }
}
