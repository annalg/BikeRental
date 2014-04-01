package app;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import logic.*;
import model.*;

public class BikeRentalApp {	
	private static final Logger logger = Logger.getLogger(BikeRentalApp.class.getName());
	
	public static void main(String[] args) throws ParseException {
		logger.setLevel(Level.ALL);
		try {
			LogManager.getLogManager().readConfiguration(new FileInputStream("mylogging.properties"));
		}
		catch(Exception e)
		{
			logger.logp(Level.SEVERE, "BikeRentalApp", 
					"inicjalization block", "Failed to create FileHandler.");
		}
		logger.logp(Level.INFO, "BikeRentalApp", "main()", "App starts!");
		
		RecordManager<Bike> bikes_book;		
		RecordManager<Client> clients_book;
		RecordManager<Reservation> reservations_book;
		
		bikes_book = new RecordManager<Bike>();
		clients_book = new RecordManager<Client>();
		reservations_book = new RecordManager<Reservation>();
		
		Scanner input = new Scanner(System.in);
		
		String action1 = "0";
		
		// sample data for 3 books:
		Client client1 = new Client("Jan", "Kowalski", "98323434564");
		Client client2 = new Client("Maria", "Boczek", "92356767564");
		clients_book.add(client1);
		clients_book.add(client2);
		MountainBike bike1 = new MountainBike(1300, 23, 5);
		ExtremeBike bike2 = new ExtremeBike(400, 20, 3, "bmx");
		bikes_book.add(bike1);
		bikes_book.add(bike2);
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
	    Date formated_date1 = df.parse("2014/03/05");
	    Date formated_date2 = df.parse("2014/01/21");
	    Date formated_date3 = df.parse("2014/02/01");
	    Date formated_date4 = df.parse("2014/03/07");
	    Date formated_date5 = df.parse("2014/01/21");
	    Date formated_date6 = df.parse("2014/02/10");
		Reservation reserv1 = new Reservation(formated_date1, formated_date4, client2, bike1);
		Reservation reserv2 = new Reservation(formated_date2, formated_date5, client1, bike2);
		Reservation reserv3 = new Reservation(formated_date3, formated_date6, client1, bike1);
		reservations_book.add(reserv1);
		reservations_book.add(reserv2);
		reservations_book.add(reserv3);
		
		while (!action1.equals("5")){
			System.out.println("\nSystem rezerwacji rowerow.");
			System.out.println("Wybierz z menu nr czynnosci:");
			System.out.println("1 - tryb edycji klientow");
			System.out.println("2 - tryb edycji rowerow");
			System.out.println("3 - tryb edycji rezerwacji");
			System.out.println("4 - tryb konfiguracji logowania");
			System.out.println("5 - zakoncz");

			action1 = input.next();
			switch(action1){
			case "1":
				BikeRentalApp.editClients(input, clients_book, 
						reservations_book);
				break;
			case "2":
				BikeRentalApp.editBikes(input, bikes_book);
				break;
			case "3":
				BikeRentalApp.editReservations(input, 
						reservations_book, clients_book, bikes_book);
				break;
			case "4":
				BikeRentalApp.editLogger(input);
				break;
			case "5":
				break;
			default:
				System.out.println("Nie ma takiej pozycji w menu.");
				break;
			}
		}	
		input.close();  		
	}	
	public static void editLogger(Scanner input) {
		String action2 = "0";
		while (!action2.equals("9")){
			System.out.println("\nTryb konfiguracji loggera.");
			System.out.println("Wybierz z menu poziom logowania:");
			System.out.println("1 - SEVERE");
			System.out.println("2 - WARNING");
			System.out.println("3 - INFO");
			System.out.println("4 - CONFIG");
			System.out.println("5 - FINE");
			System.out.println("6 - FINER");
			System.out.println("7 - FINEST");
			System.out.println("8 - OFF");
			System.out.println("9 - wroc do menu glownego");

			Logger loggerRM = LogManager.getLogManager().getLogger("logic.RecordManager");
			action2 = input.next();
			switch(action2){
			case "1":		
				logger.setLevel(Level.SEVERE);
				loggerRM.setLevel(Level.SEVERE);
				break;
			case "2":
				logger.setLevel(Level.WARNING);
				loggerRM.setLevel(Level.WARNING);
				break;
			case "3":
				logger.setLevel(Level.INFO);
				loggerRM.setLevel(Level.INFO);
				break;
			case "4":
				logger.setLevel(Level.CONFIG);
				loggerRM.setLevel(Level.CONFIG);
				break;
			case "5":
				logger.setLevel(Level.FINE);
				loggerRM.setLevel(Level.FINE);
				break;
			case "6":
				logger.setLevel(Level.FINER);
				loggerRM.setLevel(Level.FINER);
				break;
			case "7":
				logger.setLevel(Level.FINEST);
				loggerRM.setLevel(Level.FINEST);
				break;
			case "8":
				logger.setLevel(Level.OFF);
				loggerRM.setLevel(Level.OFF);
				break;
			case "9":
				break;	
			default:
				System.out.println("Nie ma takiej pozycji w menu.");
				break;
			}
			if (!action2.equals("9"))
				System.out.println("Ustawiono poziom " + logger.getLevel()+ ".");
		}
	}
	public static void editClients(Scanner input, RecordManager<Client> clients_book,
			RecordManager<Reservation> reservations_book){
		String action2 = "0";
		while (!action2.equals("7")){
			System.out.println("\nTryb edycji klienta.");
			System.out.println("Wybierz z menu nr czynnosci:");
			System.out.println("1 - dodaj klienta");
			System.out.println("2 - usun klienta o danym id");
			System.out.println("3 - sortuj klientow po nazwisku");
			System.out.println("4 - pokaz klienta o danym id");
			System.out.println("5 - policz naleznosc klienta o danym id");
			System.out.println("6 - wypisz wszystkich klientow");
			System.out.println("7 - wroc do menu glownego");
			
			action2 = input.next();
			switch(action2){
			case "1":		
				BikeRentalApp.addClient(input, clients_book);
				break;
			case "2":
				BikeRentalApp.deleteClient(input, clients_book);
				break;
			case "3":
				List<Client> book = clients_book.getAllRecords();
				Collections.sort(book);
				//clients_book.sort();
				BikeRentalApp.showAllClients(input, clients_book);
				break;
			case "4":
				BikeRentalApp.showClient(input, clients_book);
				break;
			case "5":
				BikeRentalApp.countClientsDebt(input, clients_book, 
						reservations_book);
				break;
			case "6":
				BikeRentalApp.showAllClients(input, clients_book);
				break;
			case "7":
				break;
			default:
				System.out.println("Nie ma takiej pozycji w menu.");
				break;
			}
		}		
	}
	public static void editBikes(Scanner input, RecordManager<Bike> bikes_book){
		String action2 = "0";
		while (!action2.equals("6")){
			System.out.println("\nTryb edycji rowerow.");
			System.out.println("Wybierz z menu nr czynnosci:");
			System.out.println("1 - dodaj rower");
			System.out.println("2 - usun rower o danym id");
			System.out.println("3 - sortuj rowery po dystansie pozostalym do przegladu");
			System.out.println("4 - pokaz rower o danym id");
			System.out.println("5 - wypisz wszystkie rowery");
			System.out.println("6 - wroc do menu glownego");
			
			action2 = input.next();
			switch(action2){
			case "1":		
				BikeRentalApp.addBike(input, bikes_book);
				break;
			case "2":
				BikeRentalApp.deleteBike(input, bikes_book);
				break;
			case "3":
				List<Bike> book = bikes_book.getAllRecords();
				Collections.sort(book);
				//bikes_book.sort();
				BikeRentalApp.showAllBikes(input, bikes_book);
				break;
			case "4":
				BikeRentalApp.showBike(input, bikes_book);
				break;
			case "5":
				BikeRentalApp.showAllBikes(input, bikes_book);
				break;
			case "6":
				break;
			default:
				System.out.println("Nie ma takiej pozycji w menu.");
				break;
			}
		}		
	}
	public static void editReservations(Scanner input, RecordManager<Reservation> reservations_book, 
			RecordManager<Client> clients_book, RecordManager<Bike> bikes_book){
		String action2 = "0";
		while (!action2.equals("10")){
			System.out.println("\nTryb edycji rezerwacji.");
			System.out.println("Wybierz z menu nr czynnosci:");
			System.out.println("1 - dodaj rezerwacje");
			System.out.println("2 - potwierdz rezerwacje o danym id");
			System.out.println("3 - anuluj rezerwacje o danym id");
			System.out.println("4 - odnotuj wypozyczenie o danym id");
			System.out.println("5 - odnotuj zwrot o danym id");
			System.out.println("6 - sortuj rezerwacje wg daty");
			System.out.println("7 - pokaz rezerwacje o danym id");
			System.out.println("8 - wypisz aktualne rezerwacje");
			System.out.println("9 - wypisz archiwum rezerwacji");
			System.out.println("10 - wroc do menu glownego");
			
			action2 = input.next();
			switch(action2){
			case "1":		
				BikeRentalApp.addReservation(input, reservations_book,
						clients_book, bikes_book);
				break;
			case "2":
				BikeRentalApp.editReservation(input, reservations_book, ReservationStatus.POTWIERDZONO);				
				break;
			case "3":
				BikeRentalApp.editReservation(input, reservations_book, ReservationStatus.ANULOWANO);
				break;
			case "4":
				BikeRentalApp.editReservation(input, reservations_book, ReservationStatus.W_REALIZACJI);
				break;
			case "5":
				BikeRentalApp.editReservation(input, reservations_book, ReservationStatus.ZAKONCZONA);
				break;
			case "6":
				List<Reservation> book = reservations_book.getAllRecords();
				Collections.sort(book);
				//reservations_book.sort();
				boolean showCurrent = true;
				BikeRentalApp.showAllReservations(input, reservations_book, showCurrent);
				break;
			case "7":
				BikeRentalApp.showReservation(input, reservations_book);
				break;
			case "8":
				showCurrent = true;
				BikeRentalApp.showAllReservations(input, reservations_book, showCurrent);
				break;
			case "9":
				showCurrent = false;
				BikeRentalApp.showAllReservations(input, reservations_book, showCurrent);
				break;
			case "10":
				break;
			default:
				System.out.println("Nie ma takiej pozycji w menu.");
				break;
			}
		}		
	}
	public static void addClient(Scanner input, RecordManager<Client> clients_book){
		System.out.print("Podaj imie: ");
		String name = input.next();
		System.out.print("Podaj nazwisko: ");
		String surname = input.next();
		System.out.print("Podaj pesel: ");
		String pesel = input.next();
		// if there were separate books for clients etc, this block could be in "logic"
		try{		
			List<Client> book = clients_book.getAllRecords();
			for(Client c: book){
				if(c.getSurname().equals(surname) && c.getName().equals(name) 
						&& c.getPesel().equals(pesel)){
					throw new RecordExistsException();
				}
			}
			Client client;
			client = new Client(name, surname, pesel);
			clients_book.add(client);
			System.out.println("Kontakt o id: " + client.getId()+ " dodany!");
		}
		catch(RecordExistsException e){
			logger.logp(Level.SEVERE, "BikeRentalApp", "addClient()", "Type of exception: " + e.getMessage());
			System.out.println(e.getMessage());
		}
	}
	public static void deleteClient(Scanner input, RecordManager<Client> clients_book){
		System.out.print("Podaj id: ");
		int id_del = input.nextInt();
		Client client = clients_book.getRecord(id_del);
		try{
			clients_book.delete(client);
		}
		catch(RecordNotFoundException e){
			logger.logp(Level.SEVERE, "BikeRentalApp", "deleteClient()", "Type of exception: " + e.getMessage());
			System.out.println(e.getMessage());
		}
	}
	public static void showClient(Scanner input, RecordManager<Client> clients_book){
		System.out.print("Podaj id: ");
		int id = input.nextInt();
		Client client = clients_book.getRecord(id);
		if (client != null)
			System.out.println(client);
	}
	public static void showAllClients(Scanner input, RecordManager<Client> clients_book){
		List<Client> clients_list = clients_book.getAllRecords();
		FormattedTable.line();
		FormattedTable.printHeaderClient();
		FormattedTable.line();
		for(Client c: clients_list){
			FormattedTable.printRow(c);
		}
		FormattedTable.line();
	}
	private static void countClientsDebt(Scanner input,
			RecordManager<Client> clients_book, 
			RecordManager<Reservation> reservations_book) {
		int sum = 0;
		int c_id = 0;
		System.out.println("Podaj id klienta: ");
		c_id = input.nextInt();
		List<Reservation> r_book = reservations_book.getAllRecords();
		for (Reservation r: r_book){
			if (r.getOurClient().getId() == c_id){
				if (r.getStatus().equals(ReservationStatus.ZAKONCZONA)){
					long days_number = (r.getDateEnd().getTime() - 
							r.getDateStart().getTime())/(1000*3600*24)+1;
					System.out.println("Ilosc dni: " + days_number);
					boolean isExtBike = r.getOurBike() instanceof ExtremeBike;
					boolean isMountBike = r.getOurBike() instanceof MountainBike;
					if (isExtBike){
						sum += days_number * ExtremeBike.getPrice();
					} else if (isMountBike){
						sum += days_number * MountainBike.getPrice();
					} else {
						sum += days_number * Bike.getPrice();
					}
				}	
			}
		}
		Client c = clients_book.getRecord(c_id);
		c.setDebt(sum);
		System.out.println("Naleznosc wynosi: "+ c.getDebt()+ " zl.");
	}
	public static void addBike(Scanner input, RecordManager<Bike> bikes_book){
		System.out.println("Podaj typ roweru:");
		System.out.println("t - tradycyjny");
		System.out.println("g - gorski");
		System.out.println("w - wyczynowy");
		String action = input.next();
		
		System.out.println("Podaj wielkosc kol w calach: ");
		int wheel = input.nextInt();
		System.out.println("Podaj aktualny przebieg roweru: ");
		int mileage = input.nextInt();
		
		switch(action){
		case "t":
			Bike bike = new Bike(mileage,wheel);
			bikes_book.add(bike);
			System.out.println("Dodano rower tradycyjny o id = " + bike.getId()+
					" (Cena: " + Bike.getPrice() + ")");
			break;
		case "g":
			System.out.println("Podaj ilosc przerzutek: ");
			int shifters = input.nextInt();
			MountainBike mbike = new MountainBike(mileage, wheel, shifters);
			bikes_book.add(mbike);
			System.out.println("Dodano rower gorski o id = " + mbike.getId()+
					" (Cena: " + MountainBike.getPrice() + ")");
			break;
		case "w":
			System.out.println("Podaj ilosc przerzutek: ");
			shifters = input.nextInt();
			System.out.println("Podaj rodzaj roweru: ");
			String kind = input.next();
			ExtremeBike ebike = new ExtremeBike(mileage, wheel, shifters, kind);
			bikes_book.add(ebike);
			System.out.println("Dodano rower wyczynowy o id = " + ebike.getId()+
					" (Cena: " + ExtremeBike.getPrice() + ")");
			break;
		default:
			System.out.println("Nie ma takiego roweru :D");
			break;
		}
	}
	public static void deleteBike(Scanner input, RecordManager<Bike> bikes_book){
		System.out.print("Podaj id: ");
		int id_del = input.nextInt();
		Bike bike = bikes_book.getRecord(id_del);
		try{
			bikes_book.delete(bike);;
		}
		catch(RecordNotFoundException e){
			logger.logp(Level.SEVERE, "BikeRentalApp", "deleteClient()", "Type of exception: " + e.getMessage());
			System.out.println(e.getMessage());
		}
	}
	public static void showBike(Scanner input, RecordManager<Bike> bikes_book){
		System.out.print("Podaj id: ");
		int id = input.nextInt();
		Bike bike = bikes_book.getRecord(id);
		if (bike != null)
			System.out.println(bike);
	}
	public static void showAllBikes(Scanner input, RecordManager<Bike> bikes_book){
		List<Bike> bikes_list = bikes_book.getAllRecords();
		FormattedTable.line();
		FormattedTable.printHeaderBike();
		FormattedTable.line();
		for(Bike b: bikes_list){
			FormattedTable.printRow(b);
		}
		FormattedTable.line();
	}
	public static void addReservation(Scanner input, RecordManager<Reservation> reservations_book,
			RecordManager<Client> clients_book, RecordManager<Bike> bikes_book){
		Client client = null;
		int client_id = 0;
		try{
			System.out.println("Podaj id klienta: ");
			client_id = input.nextInt();
			client = clients_book.getRecord(client_id);
			if (client == null){
				System.out.println("Nie ma takiego klienta.");
				return;
			}
		}
		catch(InputMismatchException e){
			logger.logp(Level.SEVERE, "BikeRentalApp", "addReservation()", "Type of exception: " + e.toString());
			System.out.println("Id jest liczba...!");
			input.next();			// for input clean up 
			return;
		}
		Bike bike = null;
		int bike_id = 0;
		try{
			System.out.println("Podaj id roweru: ");
			bike_id = input.nextInt();
			bike = bikes_book.getRecord(bike_id);
			if (bike == null){
				System.out.println("Nie ma takiego roweru.");
				return;
			}
		}			
		catch(InputMismatchException e){
			logger.logp(Level.SEVERE, "BikeRentalApp", "addReservation()", "Type of exception: " + e.toString());
			System.out.println("Id jest liczba...!");
			input.next();			// for input clean up 
			return;
		}
		Date f_date_start, f_date_end;
		try{
			System.out.println("Podaj date poczatkowa (rrrr/mm/dd): ");
			String d_start = input.next();
			DateFormat df= new SimpleDateFormat("yyyy/MM/dd");
			f_date_start = df.parse(d_start);
			System.out.println("Podaj date koncowa (rrrr/mm/dd): ");
			String d_end = input.next();
			f_date_end = df.parse(d_end);
			if (f_date_end.before(f_date_start)){
				System.out.println("Daty w zlej kolejnosci!"); 
				return;
			}				
		}
		catch(ParseException e){
			logger.logp(Level.SEVERE, "BikeRentalApp", "addReservation()", "Type of exception: " + e.getMessage());
			System.out.println("Zly format daty...!"); 
			return;
		}
		try{
			List<Reservation> reservations_list = reservations_book.getAllRecords();
			for(Reservation r: reservations_list){
				if (r.getStatus().equals(ReservationStatus.ANULOWANO)){
					continue;
				}
				boolean dateIsAvailable = ((r.getOurBike().getId()!=bike_id) || ((r.getOurBike().getId()==bike_id) &&
						(r.getDateStart().after(f_date_end) || r.getDateEnd().before(f_date_start))));
				if (!dateIsAvailable){
					throw new BikeNotAvailableException();
				}				
			}
			Reservation reserv = new Reservation(f_date_start, f_date_end, client, bike);
			reservations_book.add(reserv);
			System.out.println("Dokonano rezerwacji o id = " + reserv.getId());
		}
		catch(BikeNotAvailableException e){
			logger.logp(Level.SEVERE, "BikeRentalApp", "addReservation()", "Type of exception: " + e.getMessage());
			System.out.println(e.getMessage());
		}
	}
	public static void editReservation(Scanner input, 
			RecordManager<Reservation> reservations_book, ReservationStatus status){
		System.out.print("Podaj id: ");
		int id_del = input.nextInt();
		Reservation reserv = reservations_book.getRecord(id_del);
		try{
			reservations_book.delete(reserv);
			switch (status){
			case POTWIERDZONO:
				reserv.setStatus(ReservationStatus.POTWIERDZONO);
				break;
			case ANULOWANO:
				reserv.setStatus(ReservationStatus.ANULOWANO);
				break;
			case W_REALIZACJI:
				reserv.setStatus(ReservationStatus.W_REALIZACJI);
				break;
			case ZAKONCZONA:
				int mileage;
				Bike bike = reserv.getOurBike();
				do{
					System.out.println("Podaj przebieg: ");
					mileage = input.nextInt();
				} while (bike.getMileage() > mileage);
				bike.setMileage(mileage);
				reserv.setStatus(ReservationStatus.ZAKONCZONA);
				break;
			default:
				break;
			}
			reservations_book.add(reserv);
		}
		catch(RecordNotFoundException e){
			logger.logp(Level.SEVERE, "BikeRentalApp", "editReservation()", "Type of exception: " + e.getMessage());
			System.out.println(e.getMessage());
		}		
	}
	public static void showReservation(Scanner input, RecordManager<Reservation> reservations_book){
		System.out.print("Podaj id: ");
		int id = input.nextInt();
		Reservation reserv = reservations_book.getRecord(id);
		if (reserv != null){
			System.out.println(reserv);
		}
	}
	public static void showAllReservations(Scanner input, RecordManager<Reservation> reservations_book,
			boolean showCurrent){
		List<Reservation> reservations_list = reservations_book.getAllRecords();
		FormattedTable.line();
		FormattedTable.printHeaderReservation();
		FormattedTable.line();
		for(Reservation r: reservations_list){
			if (showCurrent){
				if (r.getStatus().equals(ReservationStatus.ANULOWANO) || 
						r.getStatus().equals(ReservationStatus.ZAKONCZONA)){
					continue;
				} else{
					FormattedTable.printRow(r);
					//System.out.println(r);
				}	
			} else{
				FormattedTable.printRow(r);
				//System.out.println(r);
			}	
		}
		FormattedTable.line();
	}
}
