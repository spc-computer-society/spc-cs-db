package spc.compsoc.example;
public class Looping{
  public static void main(String[] args){
    List<String> ls = new ArrayList<>();
    ls.add("Mer");
    //First way
    //Loops over every element in this list,
    //Is possible to use for Arrays
    for(String s : ls){
      System.out.println(s);
  }
  //Second way
  //Using forEach(), which exists in most collection classes
  ls.forEach(s -> System.out.println(s));
  //Note that forEach requires basic knowledge in lambda functions or method references
  
  /Third way
  //Using .stream(), which exists in most collection classes, except Map which you need to convert into a set first.
  ls.stream().forEach(System.out::println);
  //Stream is not only limited to these methods, but also has other useful functions such as map and filter
  //See the standard library for details.
  //Note that this requires some knowledge of lambda functions
}
