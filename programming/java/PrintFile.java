package spc.compsoc.example;

import java.nio.file.Paths;
import java.nio.file.Files;

public class PrintFile{
  public static void main(String[] args){
    try{
      Files.readAllLines(Paths.get("../common/PrintText.txt")).forEach(System.out::println);
    }catch(IOException ioe){
      System.err.println("Unable to read file.");
      ioe.printStackTrace();
    }
  }
}
