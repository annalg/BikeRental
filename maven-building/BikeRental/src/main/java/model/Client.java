package model;

public class Client implements Comparable<Client>, RecordInterface{
	private static int NextId;
	private int Id;
	private String Name;
	private String Surname;
	private String Pesel;
	private int Debt;
		
	public Client(String Name, String Surname, String Pesel){
		this.Name = Name;
		this.Surname = Surname;
		this.Pesel = Pesel;
		Id = NextId;
		NextId++;
	}
	public int getId() {
		return Id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getSurname() {
		return Surname;
	}
	public void setSurname(String surname) {
		Surname = surname;
	}
	public String getPesel() {
		return Pesel;
	}
	public void setPesel(String pesel) {
		Pesel = pesel;
	}
	public int getDebt() {
		return Debt;
	}
	public void setDebt(int debt) {
		Debt = debt;
	}
	@Override
	public String toString(){
		return "Id: " + Id + "\tImie: " + Name + "\tNazwisko: " + Surname +
				"\tPesel: " + Pesel;
	}
	@Override
	public int compareTo(Client client){
		return Surname.compareTo(client.Surname);
	}
}
