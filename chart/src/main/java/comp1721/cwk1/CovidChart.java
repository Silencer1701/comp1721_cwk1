package comp1721.cwk1;

// Implement your solution to the Advanced task here
// (Note: the class must be named CovidChart)
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;

public class CovidChart extends Application {
    public void start(Stage stage) throws FileNotFoundException, UnsupportedEncodingException {


        CovidDataset a = new CovidDataset();
        a.readDailyCases("../datafiles/2020-daily.csv");

        NumberAxis xAxis = new NumberAxis(280, 349, 5);
        xAxis.setLabel("Day of Year");

        NumberAxis yAxis = new NumberAxis   (0, 850, 50);
        yAxis.setLabel("Active Cases");

        LineChart linechart = new LineChart(xAxis, yAxis);

        XYChart.Series series = new XYChart.Series();
        series.setName("../datafiles/2020-daily.csv");

        LocalDate one = LocalDate.of(2020,1,1);

        for (int i=9;i<a.size();i++){
            int tot = 0;
            for (int j=0;j<10;j++){
                tot = tot+
                        a.getRecord(i-j).getOtherCases()+
                        a.getRecord(i-j).getStudentCases()+
                        a.getRecord(i-j).getStaffCases();
            }
            series.getData().add(new XYChart.Data
                    (a.getRecord(i).getDate().toEpochDay()
                            - one.toEpochDay() + 1, tot));
        }


        linechart.getData().add(series);
        linechart.setCreateSymbols(false);

        Scene scene = new Scene(linechart, 1200, 800);

        stage.setTitle("Active Coronavirus Cases, University of Leeds");

        stage.setScene(scene);
        stage.show();
    }
    public static void main(String args[]){
        launch(args);
    }
}