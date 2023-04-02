package model;
public class Collaborator{
	private String name;
	private String charge;
	public Collaborator(String name,String charge){
		this.name=name;
		this.charge=charge;
	}
	public String getName(){
		return name;
	}
	public String getCharge(){
		return charge;
	}


}