package comp1721.cwk1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class CovidDataset {
  private List<CaseRecord> list;

  public CovidDataset() {
      this.list = new LinkedList<CaseRecord>();
  }

  // TODO: Write stub for size()
  public int size() {
      return list.size();
  }

  // TODO: Write stub for getRecord()
  public CaseRecord getRecord(int index) {
      if (index<0 || index>= list.size()) {
          throw new DatasetException("Wrong index!");
      }
      return list.get(index);
  }

  // TODO: Write stub for addRecord()
  public void addRecord(CaseRecord rec) {
      list.add(rec);
  }

  // TODO: Write stub for dailyCasesOn()
  public CaseRecord dailyCasesOn(LocalDate day) {
      for (int i=0;i< list.size();i++){
          if (list.get(i).getDate() == day) {
              return list.get(i);
          }
      }
      throw new DatasetException("Wrong day!");
  }
  // TODO: Write stub for readDailyCases()
  public void readDailyCases(String filename)
          throws FileNotFoundException, UnsupportedEncodingException {
      list.clear();
      BufferedReader reader =
              new BufferedReader(new InputStreamReader
                      (new FileInputStream(filename), "utf-8"));
      try {
          reader.readLine();
          String line = null;
          while((line=reader.readLine())!=null) {
              String item[] = line.split(",");
              if (item.length < 4) {
                  throw new DatasetException("No data here!");
              }
              CaseRecord a = new CaseRecord(LocalDate.parse(item[0]),
                      Integer.parseInt(item[1]),
                      Integer.parseInt(item[2]),Integer.parseInt(item[3]));
              addRecord(a);
          }
      } catch (IOException e) {
          e.printStackTrace();
      }


      return;
  }

  // TODO: Write stub for writeActiveCases()
  public void writeActiveCases(String filename) throws IOException {
      BufferedWriter writer = new BufferedWriter
              (new OutputStreamWriter(new FileOutputStream(filename), "utf-8"));
      writer.write("Date,Staff,Students,Other");
      //Excep handling
      if (list.size() < 10){
          throw new DatasetException("Too small!");
      }
      for (int i= 9;i< list.size();i++){
          int stf = 0;
          int std = 0;
          int oth = 0;
          for (int j=0;j<10;j++){
              stf = stf + this.list.get(i-j).getStaffCases();
              std = std + this.list.get(i-j).getStudentCases();
              oth = oth + this.list.get(i-j).getOtherCases();
          }
          writer.newLine();
          writer.write(String.format("%s,%d,%d,%d", this.list.get(i).getDate(),stf,std,oth));//write into file

      }
      writer.close();
  }

}
