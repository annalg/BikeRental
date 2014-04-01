package model;

public class MountainBike extends Bike {
	protected int NumberOfShifters;
	private static int CheckUpDistance = 2000;
	private static int Price = 30;
	
	public MountainBike(int Mileage, int WheelSize, int NumberOfShifters) {
		super(Mileage,WheelSize);
		this.NumberOfShifters = NumberOfShifters;
		this.DistanceToCheckUp = CheckUpDistance - Mileage;
	}
	public int getNumberOfShifters() {
		return NumberOfShifters;
	}
	public void setNumberOfShifters(int numberOfShifters) {
		NumberOfShifters = numberOfShifters;
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
		return super.toString() + "\tIlosc przerzutek: " + NumberOfShifters;
	}
}
