package app;

import java.text.DateFormat;
import java.util.Locale;

public abstract class FormattedTable {
	static void line() {
		System.out.println("-----------------------------"
				+ "--------------------------------------"
				+ "--------------------------------------"
				+ "--------");
	}
	static void printHeaderClient() {
		System.out.printf("%s \t %-15s \t %-15s \t %-11s \n",
		"Id", "Imie", "Nazwisko", "Pesel");
	}
	static void printHeaderBike() {
		System.out.printf("%-5s \t %s \t %s \t %s \t %s \t %s \n",
		"Id", "Przebieg", "Ilosc km do przegladu", "Rozmiar kol", 
		"Ilosc przerzutek", "Rodzaj");
	}
	static void printHeaderReservation() {
		System.out.printf("%13s \t %10s \t %9s "
				+ "\t %15s \t %11s \t\t %6s \n",
		"Id rezerwacji", "Id klienta", "Id roweru", 
		"Data poczatkowa", "Data zwrotu", "Status");
	}
	static void printRow(model.Client client) {
		System.out.printf("%d \t %-15s \t %-15s \t %11s \n",
		client.getId(), client.getName(), client.getSurname(),
		client.getPesel());
	}
	static void printRow(model.Bike bike) {
		System.out.printf("%-5d \t %8d \t %21d \t %11d \t ",
		bike.getId(), bike.getMileage(), 
		bike.getDistanceToCheckUp(), bike.getWheelSize());
		if (bike instanceof model.MountainBike){
		  model.MountainBike mbike = (model.MountainBike) bike;
		  System.out.printf("%16d \t",mbike.getNumberOfShifters());
		}
		if (bike instanceof model.ExtremeBike){
			model.ExtremeBike ebike = (model.ExtremeBike) bike;
			System.out.printf(" %-6s", ebike.getKindOfBike());
		}
		System.out.println();
	}
	static void printRow(model.Reservation reserv) {
		DateFormat dateFormatter; 
		dateFormatter = DateFormat.getDateInstance
				(DateFormat.LONG, Locale.ENGLISH);
		String date_start = dateFormatter.format
				(reserv.getDateStart()).toString();
		String date_end = dateFormatter.format
				(reserv.getDateEnd()).toString();
		System.out.printf("%-13d \t %10d \t %9d \t %-15s \t"
				+ " %-15s \t %-15s \n",
		reserv.getId(), reserv.getOurClient().getId(), 
		reserv.getOurBike().getId(), date_start,
		date_end, reserv.getStatus());
	}
}
