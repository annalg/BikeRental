package model;

public class Bike implements Comparable<Bike>, RecordInterface{
	protected int Id;
	protected static int NextId;
	protected int WheelSize;
	protected int Mileage;  
	protected int DistanceToCheckUp;	// distance left to do check up
	private static int CheckUpDistance = 5000;	// check up time: every 5000km
	private static int Price = 20;
	
	public Bike(int Mileage, int WheelSize) {
		this.Mileage = Mileage;
		this.DistanceToCheckUp = CheckUpDistance - Mileage;
		this.WheelSize = WheelSize;
		Id = NextId;
		NextId++;
	}
	public int getId() {
		return Id;
	}
	public int getWheelSize() {
		return WheelSize;
	}
	public void setWheelSize(int wheelSize) {
		WheelSize = wheelSize;
	}
	public int getMileage() {
		return Mileage;
	}
	public void setMileage(int mileage) {
		Mileage = mileage;
		DistanceToCheckUp = CheckUpDistance - Mileage;
	}
	public int getDistanceToCheckUp() {
		return DistanceToCheckUp;
	}
	public static int getCheckUpDistance() {
		return CheckUpDistance;
	}
	public static void setCheckUpDistance(int checkUpDistance) {
		CheckUpDistance = checkUpDistance;
	}
	public static int getPrice() {
		return Price;
	}
	public static void setPrice(int price) {
		Price = price;
	}
	@Override
	public String toString(){
		return "Id roweru: " + Id + "\tPrzebieg: " + Mileage +
	"\tIlosc km do przegladu: " + DistanceToCheckUp + "\tRozmiar kol: " + WheelSize;
	}
	@Override
	public int compareTo(Bike bike){
		return Integer.valueOf(DistanceToCheckUp).compareTo(Integer.valueOf(bike.DistanceToCheckUp));
	}
}
