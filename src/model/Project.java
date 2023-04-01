package model;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

public class Project{
	public static final int NUM_MANAGERS=2;
	private String name;
	private String clientName;
	private GregorianCalendar initialPlannedDate;
	private GregorianCalendar finalPlannedDate;
	private double budget;
	private boolean done;
	private int numCapsulesAdded;
	private int[] numCapsulesPerType;
	private Manager[] managers;
	public Project( String name, String clientName, double budget, String[] nameManagers, String[] phoneManagers, GregorianCalendar initialDatePlanned, GregorianCalendar finalDatePlanned){
		this.name=name;
		this.clientName=clientName;
		this.budget=budget;
		managers=new Manager[NUM_MANAGERS];
		for(int i=0;i<NUM_MANAGERS;i++){
			managers[i]=new Manager(nameManagers[i],phoneManagers[i]);
		}
		this.initialPlannedDate=initialDatePlanned;
		this.finalPlannedDate=finalDatePlanned;

	}


	public String getName(){
		return name;
	}
	public String getProjectInfo(){
		return "\nName: " + name + "\nClient: " + clientName + "\nInitial Date: " + getInitialPlannedDateFormated() + 
		"\nFinal Date: " + getFinalPlannedDateFormated() + "\nTotalBudget: " + budget;
	}

	

	public GregorianCalendar getInitialDate(){
		return initialPlannedDate;
	}
	
	public String getInitialPlannedDateFormated() {
		return Controller.calendarToString(initialPlannedDate);

	}

	public GregorianCalendar getFinalPlannedDate(){
		return finalPlannedDate;
	}

	public String getFinalPlannedDateFormated(){
		return Controller.calendarToString(finalPlannedDate);
	}


	
}