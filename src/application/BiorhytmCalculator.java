package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class BiorhytmCalculator {

	private static final int PHYSICAL_PHASE = 23;
	private static final int EMOTIONAL_PHASE = 28;
	private static final int INTELLECTUAL_PHASE = 33;

	private static final double PI = 3.14;

	private static double calculatePhysical(int daysFromBirth) {
		double b = (Math.sin((2 * PI * daysFromBirth) / PHYSICAL_PHASE)) * 100;
		return b;
	}

	private static double calculateEmotional(int daysFromBirth) {
		double b = (Math.sin((2 * PI * daysFromBirth) / EMOTIONAL_PHASE)) * 100;
		return b;
	}

	private static double calculateIntellectual(int daysFromBirth) {
		double b = (Math.sin((2 * PI * daysFromBirth) / INTELLECTUAL_PHASE)) * 100;
		return b;
	}

	public static void calculateAll(LocalDate dateOfBirth, LocalDate dateToCalculate,
			LineChart<String, Number> lineChart) {
		int daysFromBirth = (int) ChronoUnit.DAYS.between(dateOfBirth, dateToCalculate);

		for (int i = 0; i < 31; i++) {
			String day = dateToCalculate.plusDays(i).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")).toString();

			double physical = calculatePhysical(daysFromBirth + i);
			double emotional = calculateEmotional(daysFromBirth + i);
			double intellectual = calculateIntellectual(daysFromBirth + i);

			for (XYChart.Series series : lineChart.getData()) {
				switch (series.getName()) {
				case "Физический":
					series.getData().add(new XYChart.Data<>(day, physical));
					break;
				case "Эмоциональный":
					series.getData().add(new XYChart.Data<>(day, emotional));
					break;
				case "Интеллектуальный":
					series.getData().add(new XYChart.Data<>(day, intellectual));
					break;
				}
			}

		}
	}

}
