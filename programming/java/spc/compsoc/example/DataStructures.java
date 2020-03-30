package spc.compsoc.example;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.HashSet;
import java.util.Set;

public class DataStructures{
  public static void man(String[] args){
    List<Integer> list = new ArrayList<>();
    list.add(1);
    list.add(2);
    for(int i : list){
      System.out.println(i + "\n");//1 and 2
    }
    List<Integer> sub = list.subList(1,list.size());//Sublist contains 2;
    sub.forEach(System.out::println);
    

    //Maps map a key to a value, and does not allow duplicate keys
    Map<String,Integer> ids = new HashMap<>();
    ids.put("Bash",30624700);
    ids.put("Patchy",30624770);
    ids.put("Zaggy",514202);
    System.out.println(ids.get("Zaggy"));//Outputs 514202
    System.out.println(ids.get("Zappies"));//Outputs null
    System.out.println(ids.getOrDefault("Zappies",0));//Outputs 0
    System.out.println(ids.entrySet().stream().mapToInt(ent -> ent.getValue()).sum());//Returns sum of all ids
    
    Queue<Long> l = new ArrayBlockingQueue<>(5);
    l.add(1l);
    l.add(200l);
    System.out.println(l.peek());//outputs 1, in queue = {1,200}
    System.out.println(l.poll());//Outputs 1, in queue = {200}
    
    Set<String> set = new HashSet<>();
    set.add("Hi");
    set.add("Hi");
    System.out.println(set.size());// 1
    }
  }
