package logic;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import model.RecordInterface;

public class RecordManager<T extends RecordInterface> implements RecordManagerInterface<T>{
	private List<T> OurBook;
	
	private static final Logger loggerRM = Logger.getLogger(RecordManager.class.getName());
	{
		try {
			LogManager.getLogManager().readConfiguration(new FileInputStream("mylogging.properties"));
		}
		catch(Exception e)
		{
			loggerRM.logp(Level.SEVERE, getClass().getName(), 
					"inicjalization block", "Failed to create FileHandler.");
		}
		loggerRM.setLevel(Level.ALL);
	}
	
	public RecordManager(){
		OurBook = new ArrayList<T>();
		loggerRM.logp(Level.INFO, getClass().getName(), "RecordManager()",
				OurBook.getClass().getName()+ " created.");
	}
	public void add(T newRecord){
		//logger.addHandler(new FileHandler());		
		loggerRM.logp(Level.CONFIG, getClass().getName(), "add() " + newRecord.getClass().getName(),
				"size: " + OurBook.size());
		OurBook.add(newRecord);
		loggerRM.logp(Level.CONFIG, getClass().getName(), "add() ", "size: " + OurBook.size());
	}
	public void delete(T ourRecord) throws RecordNotFoundException{
		if (ourRecord == null){
			throw new RecordNotFoundException();
		}
		loggerRM.logp(Level.CONFIG, getClass().getName(), "delete() " + ourRecord.getClass().getName(),
				"size: " + OurBook.size());
		OurBook.remove(ourRecord);
		loggerRM.logp(Level.CONFIG, getClass().getName(), "delete() " + ourRecord.getClass().getName(),
				"size: " + OurBook.size());
	}
	/*public void sort(){	
		T record = null;
		if (record instanceof model.Bike){
			List<model.Bike> book = (List<model.Bike>) OurBook;
			Collections.sort(book);
		} else if (record instanceof model.Client){
			List<model.Client> book = (List<model.Client>) OurBook;
			Collections.sort(book);
		} else if (record instanceof model.Reservation){
			List<model.Reservation> book = (List<model.Reservation>) OurBook;
			Collections.sort(book);
		}
	}*/
	public T getRecord(int id){
		loggerRM.log(Level.CONFIG, "Entering getRecord()");
		T record = null;
		for(T r: OurBook){	
			if (r!= null && r.getId() == id){
				record = r;
				break;
			}
		}
		loggerRM.log(Level.CONFIG, "Exiting getRecord()");
		return record;
	}
	public List<T> getAllRecords(){
		loggerRM.log(Level.CONFIG, "Entering getAllRecords()");
		loggerRM.log(Level.CONFIG, "Exiting getAllRecords()");
		return OurBook;
	}
}
