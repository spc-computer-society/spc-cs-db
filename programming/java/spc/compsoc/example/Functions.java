package spc.compsoc.example;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Collectors;
public Class Functions{
  public static void main(String[] args){
    /*When they say functions, they mean those things that are defined with func/def/functions
    But we don't have them anyways, so why waste our time?
    Instead, we are going to talk about something else
    The java.util.function package.
    Some things in the Java library (Java 8+) take functions and consumers as arguments.
    A function takes something in and changes it to something
    A consumer takes something in and dosen't return anything
    They can be declared as
    (args) -> {
    Statements;
    }
    Or, if its a one-liner, just (args) -> statement;*/
    List<String> s = new ArrayList<>();
    s.forEach((str) -> System.out.println(str));
    //The forEach uses a consumer function on each element of the list
    //The code is functionally equal to:
    s.forEach(new Consumer<String>(){
      @Override
      public void accept(String s){
        System.out.println(s);
      }
    });
    //But that is excessive boilerplate
    //For Function:
    s.stream().map(str -> str.substring(2)).collect(Collectors.toList());
    //map() takes a function which takes a string and returns any type, in this case String
    //Again, it is equal to
    s.stream().map(new Function<String,String>(){
      @Override
      public String apply(String s){
        return s.substring(2);
      }
    });
    //But it is agin boilerplate
    //For some one-line methods, use a method reference instead for conciseness
    //The last forEach example can be just
    f.forEach(System.out::println);
  }
}
