package logic;

public class RecordNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;

	public RecordNotFoundException() {
		super("Proba usuniecia nieistniejacego rekordu!");
	}
}
