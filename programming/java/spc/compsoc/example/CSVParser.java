package spc.compsoc.example;
import java.nio.file.Files;
import java.nio.file.Path;
public class CSVParser(){
  private CSVParser(){
    throw new AssertionError();
  }
  public static List<String[]> fromCSV(Path p,String delim) throws IOException{
    return Files.readAllLines(p).stream().map(str -> str.split(delim)).collect(Collectors.toList());
  }
  public static void toCSV(Path p,String delim,List<String[]> toWrite) throws IOExeption(){
    Files.write(p,toWrite.stream().map(sArr -> Arrays.asList(toWrite).stream().collect(Collectors.joining());
  }
}
