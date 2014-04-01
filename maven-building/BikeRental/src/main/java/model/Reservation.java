package model;

import java.util.Date;

public class Reservation implements Comparable<Reservation>, RecordInterface{
	private static int NextId;
	private int Id;
	private Date DateStart;
	private Date DateEnd;
	private Client OurClient;
	private Bike OurBike;
	private ReservationStatus Status; 
	
	public Reservation(Date DateStart, Date DateEnd, Client OurClient, Bike OurBike){
		this.DateStart = DateStart;
		this.DateEnd = DateEnd;
		this.OurClient = OurClient;
		this.OurBike = OurBike;
		this.Status = ReservationStatus.ZAREZERWOWANO;
		Id = NextId;
		NextId++;
	}
	public int getId() {
		return Id;
	}
	public Date getDateStart() {
		return DateStart;
	}
	public void setDateStart(Date date) {
		DateStart = date;
	}
	public Date getDateEnd() {
		return DateEnd;
	}
	public void setDateEnd(Date date) {
		DateEnd = date;
	}
	public Client getOurClient() {
		return OurClient;
	}
	public void setOurClient(Client ourClient) {
		OurClient = ourClient;
	}
	public Bike getOurBike() {
		return OurBike;
	}
	public void setOurBike(Bike ourBike) {
		OurBike = ourBike;
	}
	public ReservationStatus getStatus() {
		return Status;
	}
	public void setStatus(ReservationStatus status) {
		Status = status;
	}
	@Override
	public String toString(){
		return "Id rezerwacji: " + Id + "\tId klienta: " + OurClient.getId() +
				"\tId roweru: " + OurBike.getId() + "\tData poczatkowa: " + 
				DateStart + "\tData zwrotu: " + DateEnd + "\tStatus: " + Status;
	}
	@Override
	public int compareTo(Reservation reserv){
		return DateStart.compareTo(reserv.DateStart);
	}
}
