package model;
public class Collaborator{
	private String name;
	private String charge;
	/**
 	* This constructor creates a new Collaborator object with the given name and charge.
 	*
 	* @param name the name of the collaborator
 	* @param charge the charge of the collaborator
 	*/
	public Collaborator(String name,String charge){
		this.name=name;
		this.charge=charge;
	}
	/**
 	* This method is used to get the name of a collaborator
 	*
 	* @return An string that contains the name of the collaborator
 	*/
	public String getName(){
		return name;
	}
	/**
 	* This method is used to get the charge of a collaborator
 	*
 	* @return A string that contains the charge of the collaborator
 	*/
	public String getCharge(){
		return charge;
	}


}