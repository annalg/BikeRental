package model;

public class ExtremeBike extends MountainBike{
	private String KindOfBike;
	private static int CheckUpDistance = 1000;
	private static int Price = 50;
	
	public ExtremeBike(int Mileage, int WheelSize, int NumberOfShifters, String KindOfBike){
		super(Mileage, WheelSize, NumberOfShifters);
		this.KindOfBike = KindOfBike;
		this.DistanceToCheckUp = CheckUpDistance - Mileage;
	}
	public String getKindOfBike() {
		return KindOfBike;
	}
	public void setKindOfBike(String kindOfBike) {
		KindOfBike = kindOfBike;
	}
	public void setMileage(int mileage) {
		Mileage = mileage;
		DistanceToCheckUp = CheckUpDistance - Mileage;
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
		return super.toString() + "\tRodzaj: " + KindOfBike;
	}
}
