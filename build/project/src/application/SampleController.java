package application;

import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;

public class SampleController {

	@FXML
	private DatePicker birthDayPicker;

	@FXML
	private DatePicker todayDatePicker;

	@FXML
	private LineChart<String, Number> lineChart;

	public SampleController() {
	}

	@FXML
	private void initialize() {

		XYChart.Series<String, Number> physical = new XYChart.Series<>();
		physical.setName("Физический");
		XYChart.Series<String, Number> emotional = new XYChart.Series<>();
		emotional.setName("Эмоциональный");
		XYChart.Series<String, Number> intellectual = new XYChart.Series<>();
		intellectual.setName("Интеллектуальный");

		lineChart.setTitle("График биоритмов");
		lineChart.getData().addAll(physical, emotional, intellectual);

		todayDatePicker.setOnAction(e -> {
			if (birthDayPicker.getValue() != null) {
				physical.getData().clear();
				emotional.getData().clear();
				intellectual.getData().clear();
				LocalDate dateOfBirth = birthDayPicker.getValue();
				LocalDate dateToCalculate = todayDatePicker.getValue();
				BiorhytmCalculator.calculateAll(dateOfBirth, dateToCalculate, lineChart);
			}
		});

		birthDayPicker.setOnAction(e -> {
			if (todayDatePicker.getValue() != null) {
				physical.getData().clear();
				emotional.getData().clear();
				intellectual.getData().clear();
				LocalDate dateOfBirth = birthDayPicker.getValue();
				LocalDate dateToCalculate = todayDatePicker.getValue();
				BiorhytmCalculator.calculateAll(dateOfBirth, dateToCalculate, lineChart);
			}
		});

	};

}
