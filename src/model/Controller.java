package model;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

public class Controller{
	public static final int MAX_PROJECTS=2;
	public static final int MAX_TOTAL_CAPSULES=3000;
	private Project projects [];
	private Capsule capsules [];
	/**
	 * Take a an object of the class GregorianCalendar and return an string that represents it
 	* @param  gregoriancalendar instance of the class GregorianCalendar
	* @return An string of the calendar
 	*/

	public static String calendarToString(GregorianCalendar calendar) {
    	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    	String dateInTxtx = format.format(calendar.getTime());
    	return dateInTxtx;
	}
	/**
	 * Initialize an object of the class Controller. 
	 * the size of the array of projects and capsules is assigned according to the constants.
 	*/
	public Controller(){
		projects=new Project[MAX_PROJECTS];
		capsules=new Capsule[MAX_TOTAL_CAPSULES];
	}
	/**
	 * Adds a new project to the array of projects if the maximum number of projects has not been reached.
	* @param name The name of the project.
	* @param clientName The name of the client.
	* @param budget The budget allocated to the project.
	* @param nameManagers An array with the names of the project managers.
	* @param phoneManagers An array with the phone numbers of the project managers.
	* @param initialDatePlanned The planned initial date of the project.
	* @param finalDatePlanned The planned final date of the project.
	* @return A message indicating if the project was added successfully or if the maximum number of projects has been reached.
 	*/
	public String addProject(String name, String clientName, double budget, String[] nameManagers, String[] phoneManagers,GregorianCalendar initialDatePlanned, GregorianCalendar finalDatePlanned){
		String msg = "The project was not added. The maximum number of projects is "+ MAX_PROJECTS;
		int pos = getfirstValidPos();
		if(pos != -1){
			projects[pos] = new Project(name, clientName, budget, nameManagers,phoneManagers,initialDatePlanned,finalDatePlanned);
			msg = "The project was added succesfully";
		}
		return msg;
	}
	/**
	 * Initializes the stages of a project with the given name, according to the given array of durations in months.
	 * 
	 * @param projectName The name of the project.
	 * @param months An array of integers representing the duration in months of each stage.
	 * @return A message indicating that the stages have been initialized.
 	*/

	public String initStages(String projectName,int months[]){
		Project project= searchProjectByName(projectName);
		project.initStages(months);
		return "The stages has been inicialized";
	}
	/**
	 * Finds and returns the first valid position empty or null position in the array of projects.
	 * 
	 * @return The first valid position in the array of projects, or -1 if the array of projects is full.
	*/
	
	private int getfirstValidPos(){
		int pos = -1;
		for(int i = 0;i<MAX_PROJECTS && pos == -1;i++){
			if(projects[i]==null){
				pos=i;
			}
		}
		return pos;
	}
	/**
	 * Returns the number of managers that has to be in a project.
	 * 
	 * @return The constant of the number of managers.
	*/

	public int getNumManagers(){
		return Project.NUM_MANAGERS;
	}
	/**
	 * Takes a project name and checks if it is valid (not already used by another project). 
	 * Returns `true` if the name is valid, `false` otherwise.
	 *
	 * @param name the name of the project to validate
	 * @return `true` if the project name is valid, `false` otherwise
	*/

	public boolean validateProjectName(String name){
		Project projectToSearch=searchProjectByName(name);
		boolean isValid=false;
		if(projectToSearch==null){
			isValid=true;
		}
		return isValid;
	}
	/**
	 * Returns a project with the given name. If no project is found with the given name, return null.
	 *
	 * @param name a string representing the name of the project to be searched
	 * @return the project with the given name, or null if no such project exists
	*/

	public Project searchProjectByName(String name){
		Project projectToSearch=null;
		boolean isEnded = false;
		boolean isFound = false;
		for(int i=0;i< projects.length && !isEnded ;i++){
			if(projects[i]==null){
				isEnded=true;
			}else{
				if( projects[i].getName().equalsIgnoreCase(name) ){
					projectToSearch=projects[i];
					isFound=true;
					isEnded=true;
				}
			}
		}
		return projectToSearch;
	}
	/**
	 * Returns an array of string that contains all of the possibles stagesTypes of a project.
	 * @return an array of strings 
 	*/

	public String[] possibleStageTypesInStr(){
		return StageType.optionsInStr();
	}
	/**
	 *Take the name of a project and culminate its current stage
	 *@param projectName the name of the project
	 *@return A message in string that can say 3 things. 1 there is no project with this name. 2 all of the project stages were finished. 3 The current stage was finished succesfully
	*/
	public String culminateCurrentStage(String projectName){
		String msg="";
		Project project=searchProjectByName(projectName);
		if(project==null){
			msg="There is no project with this name";
		}
		if(project!=null){
			boolean culminated=project.culminateCurrentStage();
			if(culminated==true){
				msg="The stage has been culminated" ;

			}else{
				msg="All of the stages of this project has been completed";
			}
		}
		return msg;
	}
	/**
	 * Takes an id an returns a capsule whit this id or null.
	 *@param id the id of the capsule 
	 * @return If there is no any capsule, return null. Otherwise, returns the capsule.
 	*/

	public Capsule searchCapsuleById(String id){
		boolean isFinished=false;
		Capsule capsule=null;
		for(int i=0;i<MAX_TOTAL_CAPSULES&&!isFinished;i++){
			if(capsules[i]==null){
				isFinished=true;
			}
			if( capsules[i]!=null && capsules[i].getId().equals(id) ){
				capsule=capsules[i];
			}
		}
		return capsule;
	}
	/**
	* Validate if it is possible to choose the string, that was given as parameter, as an ID of a project
	* @param id The id of the capsule
	* @return 
 	*/
	public boolean validateIdCapsule(String id){
		boolean isValid=true;
		if(searchCapsuleById(id)!=null){
			isValid=false;
		}
		return isValid;
	}
	/**
	* Returns an array of strings representing the possible capsule types.
	*
	* @return an array of strings with the possible capsule types.
	*
	*/
	public String[] possibleCapsuleTypeInStr(){
		return CapsuleType.optionsInStr();
	}
	/**
	 * Checks if a given capsule type is valid.
	 *
	 * @param capsuleType a string representing the capsule type to validate
	 * @return true if the capsule type is valid, false otherwise
	 *
	*/

	public boolean validateCapsuleType(String capsuleType){
		boolean isValid=false;
		if(CapsuleType.contains(capsuleType)){
			isValid=true;
		}
		return isValid;
	}
	/**
	 * Returns the index of the first available position in the capsules array.
	 * @return The index of the first null element in the capsules array. Returns -1 if the array is full.
	*/

	public int firstValidPosInCapsules(){
		int pos=-1;
		boolean isFinished=false;
		for(int i=0;i<MAX_TOTAL_CAPSULES&&!isFinished;i++){
			if(capsules[i]==null){
				pos=i;
				isFinished=true;
			}
		}
		return pos;
	}
	/**
	* Adds a new capsule to the array of capsules of the current stage of a given project.
	* @param projectName the name of the project where the capsule will be added.
	* @param nameCollab the name of the collaborator who creates the capsule
	* @param chargeCollab the job title of the collaborator who creates the capsule.
	* @param description the description of the capsule.
	* @param learningExperiences the learning experiences associated with the capsule.
	* @param id the ID of the capsule.
	* @param type the type of capsule, which must be a valid value from the CapsuleType enum.
	* @return a string message indicating whether the capsule was added or not, and why.
	*/


	public String addCapsule(String projectName,String nameCollab, String chargeCollab, String description, String learningExperiences, String id, String type){
		String str="";
		Project project=searchProjectByName(projectName);
		if(project==null){
			str="There is no project with this name";
		}else{
			Collaborator collaborator= new Collaborator(nameCollab,chargeCollab);
			Capsule capsule=new Capsule(collaborator,description,learningExperiences,id, CapsuleType.valueOf(type.toUpperCase() ) );
			
			if(project.addCapsuleToCurrentStage(capsule)){
				str="The capsule was added";
				capsules[firstValidPosInCapsules()]=capsule;
			}else{
				str="The maximum number of capsules in the stage is "+Stage.MAX_CAPSULES+"the capsule was not added";
			}
		}
		return str;
		
	}
	/**
	 * Approves a capsule with the given ID.
	 * 
	 * @param id the ID of the capsule to approve
	 * @return a message in string indicating the success of the approval
	*/

	public String approveCapsule(String id){
		String msg="";
		Capsule capsule=searchCapsuleById(id);
		if(capsule==null){
			msg="There is no any capsule with this name";
		}else{
			if(capsule.getApproved()){
				msg="This capsule was approved before";
			}else{
				msg="The capsule was approved succesfully";
				capsule.setApproved(true);
			}
		}
		return msg;
	}
	/**
	* Publishes a capsule with the given id and url.
	*
	* @param id  the id of the capsule to be published
	* @param url the url to set for the published capsule
	* @return a message indicating the status of the publishing process
	*/
	public String publishCapsule(String id, String url){
		String msg="";
		Capsule capsule=searchCapsuleById(id);
		if(capsule==null){
			msg="There is no any capsule with this name";
		}
		if(capsule!=null && !capsule.getApproved()){
			msg="This capsule has not been approved yet";
		}
		if(capsule!=null && capsule.getApproved()){
			capsule.setPublished(true);
			capsule.setUrl(url);
			msg="Capsuled published";
		}
		return msg;
	}






}