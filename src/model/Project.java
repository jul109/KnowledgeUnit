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
	private int[]numCapsulesPerType;
	private Manager[] managers;
	private Stage stages[];
		/**
	 * Constructs a new Project with the given parameters.
	 *
	 * @param name the name of the project
	 * @param clientName the name of the client
	 * @param budget the budget for the project
	 * @param nameManagers an array of the project's magers.
	 * @param phoneManagers an array of phone numbers of the managers of the project
	 * @param initialDatePlanned the planned initial date for the project
	 * @param finalDatePlanned the planned final date for the project
	 */
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
		numCapsulesPerType=new int[CapsuleType.values().length];

	}
		/**
	 * Initializes the stages of the project with the given number of months for each stage.
	 *
	 * @param months an array of integers representing the number of months for each stage
	 */
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
	/**  Returns the name of the project
	*@return An string that represents the name of the project
	*/
	public String getName(){
		return name;
	}
	/**  Returns information about the project.
	*@return An string that that contains the project name, the cliente name, the initial date, the final date and the budget.
	*/
	public String getProjectInfo(){
		return "\nName: " + name + "\nClient: " + clientName + "\nInitial Date: " + Controller.calendarToString(initialPlannedDate) + 
		"\nFinal Date: " + Controller.calendarToString(finalPlannedDate) + "\nTotalBudget: " + budget;
	}
	/**  Returns the initial date of the project.
	*@return A gregorian calendar that represents the initial date of the project. 
	*/

	public GregorianCalendar getInitialDate(){
		return initialPlannedDate;
	}
	
	/**  Returns the final date of the project.
	*@return A gregorian calendar that represents the final date of the project. 
	*/
	public GregorianCalendar getFinalPlannedDate(){
		return finalPlannedDate;
	}
		/**
	 * Creates a copy of the given GregorianCalendar object and adds the specified number of months to it.
	 *
	 * @param date the GregorianCalendar object to copy
	 * @param months the number of months to add to the copy
	 * @return a new GregorianCalendar object that is a copy of the given date with the specified number of months added
	 */
	private GregorianCalendar createCopyAndAddMonths(GregorianCalendar date,int months){
		GregorianCalendar copy= (GregorianCalendar) date.clone(); //we create a clone of the calendar that was taken as parameter
		copy.add(copy.MONTH,months); //we add the months
		return copy; //and we return a reference to this new object
	}
	/**
	* Returns the position of the current active stage in the stages array of the project.
	*
	* @return An integer that represents the position of the current active stage, or -1 if no active stage is found
	*/
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
	/**
	* Culminates the current active stage of the project.
	*
	* @return true if the current stage was successfully culminated, otherwise returns false.
	*/

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
	/**
    * Adds a capsule to the current active stage of the project.
 	*
 	* @param capsule the capsule to be added
 	* @return returns a boolean. True if the capsule was successfully added, false otherwise
 	*/
	public boolean addCapsuleToCurrentStage(Capsule capsule){
		int pos=positionOfCurrentStage();
		boolean isAdded=false;
		if(pos!=-1&& stages[pos].addCapsule(capsule)){
			isAdded=true;
			numCapsulesAdded++; //When we are adding a new capsule, we sum 1 to the array that tracks the total of capsules added
			CapsuleType typesOfCapsule[]=CapsuleType.values(); //we also loop through the array of possible types of capsule
			for(int i=0;i<typesOfCapsule.length;i++){ //
				if(capsule.getType().equals(typesOfCapsule[i])){
					numCapsulesPerType[i]++; //And We add one to the position of the array that tracks the number of capsules of the type i
				}
			}
		}
		return isAdded;
	}
	public int[] getNumCapsulesPerType(){
		return this.numCapsulesPerType;
	}
	/**
	* This method retrieves information about the learning experiences of capsules in a given stage.
	*
	* @param stageName the name of the stage to retrieve information for
	* @return information about the learning experiences of capsules in the given stage,
	* or a message indicating that the stage does not exist
	*/
	public String getStageCapsulesInfo(String stageName){
		String msg="";
		for(int i=0;i<stages.length;i++){
			if(stages[i].getStageTypeInStr().equalsIgnoreCase(stageName)){
				msg=stages[i].getLearningExperiencesOfCapsules();
			}
		}
		if(msg.equals("")){
			msg="This stage does not exist";
		}
		return msg;

	}
	/**
	* This method returns the number of capsules of each type.
	*
	* @return an array containing the number of capsules of each type
	*/
	public int getNumCapsulesAdded(){
		return this.numCapsulesAdded;
	}


	
}