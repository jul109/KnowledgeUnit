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
	private int numCapsulesAdded;
	private int[] numCapsulesPerType;
	private Manager[] managers;
	private Stage stages[];
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
		this.stages=new Stage[StageType.values().length];

	}
	public void initStages(int[] months ){
		GregorianCalendar initialPlannedDateOfTheFirstStage=initialPlannedDate; //The initial planned date  of the first stage
		//is the same as the initial planned date of the project.
		

		// Then, in order to assign the final planned date, we create a clone and add the months to this clone. 
		/// The operation "=" doesn't work, we are using objects and we don't want to lose information.
		// StageType.values() is an array that contains the objects of my enum "StageType". The first stage of the project has the first stage Type. The second stage of the project has
		//the second stage type, and so on.
		stages[0]=new Stage(StageType.values()[0],initialPlannedDateOfTheFirstStage,createCopyAndAddMonths(initialPlannedDateOfTheFirstStage,months[0]));
		stages[0].setActive(true); //the first stage changes to active
		

		for(int i=1;i<StageType.values().length;i++){
			//For the other stages:
			//we loop through the array of the StageType values. And take the StageType at the position i.
			//The initial date of the stage i, is the same as the final date of the stage i-1
			//The final date of the stage i, is the same as the initial date plus the months that the user typed.
			stages[i]=new Stage(StageType.values()[i],stages[i-1].getFinalPlannedDate(),createCopyAndAddMonths(stages[i-1].getFinalPlannedDate(),months[i]));
		}
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
	public GregorianCalendar createCopyAndAddMonths(GregorianCalendar date,int months){
		GregorianCalendar copy= (GregorianCalendar) date.clone(); //we create a clone of date that was taken as parameter
		copy.add(copy.MONTH,months); //we add the months
		return copy; //and we return a reference to this new object
	}
	public int positionOfCurrentStage(){
		int pos=-1;
		boolean isFound=false;
		for(int i=0;i<stages.length&& !isFound;i++){
			if(stages[i].getActive()==true){
				pos=i;
				isFound=true;
			}
		}
		return pos;

	}

	public void test(){
		for(int i=0;i<stages.length;i++){
			System.out.println(stages[i].getStageType());
			System.out.println(Controller.calendarToString(stages[i].getInitialPlannedDate()));
			System.out.println(Controller.calendarToString(stages[i].getFinalPlannedDate()));
		}
	}

	public boolean culminateCurrentStage(){
		int pos=positionOfCurrentStage();
		boolean culminated=false;
		if(pos==-1){
			culminated=false;
		}
		if(pos==stages.length-1){
			stages[pos].setActive(false);
			stages[pos].setFinalRealDate(new GregorianCalendar());
			culminated=true;

		}
		if(0<=pos && pos<stages.length-1){
			stages[pos].setActive(false);
			stages[pos+1].setActive(true);
			stages[pos].setFinalRealDate(new GregorianCalendar());
			stages[pos+1].setInitialRealDate(stages[pos].getFinalRealDate());
			culminated=true;
		}
		return culminated;
	}
	public boolean addCapsuleToCurrentStage(Capsule capsule){
		int pos=positionOfCurrentStage();
		boolean isAdded=false;
		if(pos!=-1){
			if(stages[pos].addCapsule(capsule)){
				isAdded=true;
			}
		}
		return isAdded;
	}


	
}