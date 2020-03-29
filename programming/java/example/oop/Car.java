package spc.compsoc.example;

public class Car{
  private String name;
  private String colour;
  public Car(String name,String colour){
    this.name = name;
    this.colour = colour;
  }
  public String getName(){
    return name;
  }
  public String getColour(){
    return colour;
  }
}
