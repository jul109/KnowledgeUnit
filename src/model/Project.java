package model;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Project{
	private String name;
	private String clientName;
	private Calendar initialDatePlanned;
	private Calendar finalDatePlanned;
	private double budget;
	private boolean done;
	private int numCapsulesAdded;
	private int[] numCapsulesPerType;
	Project(){
		System.out.println("Hola desde el contructor del cada proyecto");
	}
	
}