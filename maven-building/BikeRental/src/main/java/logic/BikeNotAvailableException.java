package logic;

public class BikeNotAvailableException extends Exception{
	private static final long serialVersionUID = 1L;

	public BikeNotAvailableException() {
		super("Ten rower jest juz zajety w podanym terminie.");
	}
}
