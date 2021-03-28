package comp1721.cwk1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ActiveCases {
  // Implement program for full solution here
  public static void main(String[] args) throws IOException {
      CovidDataset a = new CovidDataset();
      String b = args[0];
      String c = args[1];
      try {
          a.readDailyCases(b);
      } catch (FileNotFoundException e) {
          e.printStackTrace();
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }
      a.writeActiveCases(c);
      int x = 0;
      for (int i = 0;i<a.size();i++){
          x = x + a.getRecord(i).totalCases();
      }
      System.out.println(x);
  }
}


