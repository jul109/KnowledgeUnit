package model;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Project{
	public static final int NUM_MANAGERS=2;
	private String name;
	private String clientName;
	private Calendar initialDatePlanned;
	private Calendar finalDatePlanned;
	private int months;
	private double budget;
	private boolean done;
	private int numCapsulesAdded;
	private int[] numCapsulesPerType;
	private Manager[] managers;
	public Project( String name, String clientName, int months, double budget, String[] nameManagers, String[] phoneManagers){
		this.name=name;
		this.clientName=clientName;
		this.months=months;
		this.budget=budget;
		managers=new Manager[NUM_MANAGERS];
		for(int i=0;i<NUM_MANAGERS;i++){
			managers[i]=new Manager(nameManagers[i],phoneManagers[i]);
		}
		initialDatePlanned = Calendar.getInstance();
		finalDatePlanned =   Calendar.getInstance();

	}

	public String getName(){
		return name;
	}


	
}