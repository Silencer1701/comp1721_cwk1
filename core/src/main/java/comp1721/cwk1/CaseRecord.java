package comp1721.cwk1;

import java.time.LocalDate;

public class CaseRecord {

    private LocalDate date;
    private int staffCases;
    private int studentCases;
    private int otherCases;
  // TODO: Write stub for constructor
  public CaseRecord(LocalDate localdate, int numofstf, int numofstd, int numofoth){
      if (numofstf < 0 || numofstd < 0 || numofoth < 0) {
          throw new DatasetException("Please input valid value!");
      }
      this.date = localdate;
      this.staffCases = numofstf;
      this.studentCases = numofstd;
      this.otherCases = numofoth;
  }
  // TODO: Write stubs for four getter methods
  public LocalDate getDate() {
      return date;
  }
  public int getStaffCases() {
      return staffCases;
  }
  public int getStudentCases() {
      return studentCases;
  }
  public int getOtherCases() {
      return otherCases;
  }
  // TODO: Write stub for totalCases()
  public int totalCases() {
      return staffCases + studentCases + otherCases;
  }
  // TODO: Write stub for toString()
  public String toString() {
      System.out.println(date+": "+studentCases+" staff, "+studentCases+
              " students, "+otherCases+" other");
      return String.format("%s: %d staff, %d students, %d other",
              date,staffCases,studentCases,otherCases);
  }
}
