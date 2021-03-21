package sample;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextArea;
import java.util.*;
import java.util.stream.Collectors;

public class StatisticsController {

    BasicFunctionality functions;

    //SortingMethods sortingMethods = new SortingMethods();
    SortingMethodsStreams sortingMethods = new SortingMethodsStreams();
    @FXML  private BarChart<String, Integer> barChart;
    @FXML  private CategoryAxis xAxis;
    @FXML private TextArea textArea;
    @FXML  private NumberAxis yAxis;
    private  XYChart.Series<String, Integer> series1;
    public void setUp(BasicFunctionality f)
    {
        functions=f;
        yAxis.setLabel("Количество");
        Map<String,Integer> map= sortingMethods.getSortedStatistic(functions.patients);

        XYChart.Series<String,Integer> dataSeries = new XYChart.Series();

        dataSeries.getData().addAll(map.entrySet().stream().map(e->new XYChart.Data<String,Integer>(e.getKey(),e.getValue())).collect(Collectors.toList()));
        barChart.getData().addAll(dataSeries);
        sortingMethods.getNonRepetDiags(functions.patients);
        sortingMethods.getNotRepetitiveDiag(functions.patients).forEach(t->textArea.appendText(t.toString()+"\n"));
    }
}



