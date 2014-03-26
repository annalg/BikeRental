package logic;

public class RecordExistsException extends Exception{
	private static final long serialVersionUID = 1L;

	public RecordExistsException(){
		super("Taki rekord juz istnieje!");
	}
}
