package logic;

import java.util.List;

import model.RecordInterface;

public interface RecordManagerInterface<T extends RecordInterface>{	
	public void add(T newRecord);
	public void delete(T ourRecord) throws RecordNotFoundException;
	//public void sort();
	public T getRecord(int id);
	public List<T> getAllRecords();
}