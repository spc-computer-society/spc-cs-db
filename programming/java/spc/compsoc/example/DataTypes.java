package spc.compsoc.example;
public class DataTypes {
  public static void main(String[] args) {
    // booleans are true or false values. Lowercase, unlike Python
    boolean b = true;
    // Integers are integers between -2^31 to 2^31 - 1
    int i = 59;
    // Longs are integers between -2^63 to 2^63 - 1
    // Specify integers as longs by appending `l` to the integer.
    long l = 59l;
    // Floats are single-precision decimals (32 bits are used to represent each float).
    // Specify decimal numbers as floats by appending `f` to the number.
    // For added precision, use double.
    float f = 69.420f;
    // Doubles are double-precision decimals (64 bits are used to represent each double).
    double d = 69.420;
    // Characters are single letters/digits.
    // For unicode characters, prefer Strings for less confusing behaviour
    char c = 'D';
    // Strings are lists of characters.
    String s = "I love ict";
    System.out.println(b);
    System.out.println(i);
    System.out.println(l);
    System.out.println(f);
    System.out.println(d);
    System.out.println(c);
    System.out.println(s);
  }
}
    
    
