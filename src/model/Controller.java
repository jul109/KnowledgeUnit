package model;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

public class Controller{
	public static final int MAX_PROJECTS=2;
	public static final int MAX_TOTAL_CAPSULES=3000;
	private Project projects [];
	private Capsule capsules [];
	/**
	 * Take a gregorian calendar and return an string of the date.
 	* 
 	* @param  gregoriancalendar instance of the class gregorianCalendar
	* @return An string of the calendar
 	*/

	public static String calendarToString(GregorianCalendar calendar) {
    	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    	String dateInTxtx = format.format(calendar.getTime());
    	return dateInTxtx;
	}
	/**
	 * Initialized the array of projects, and the array of capsules 
 	*/
	public Controller(){
		projects=new Project[MAX_PROJECTS];
		capsules=new Capsule[MAX_TOTAL_CAPSULES];
	}

	
	public String addProject(String name, String clientName, double budget, String[] nameManagers, String[] phoneManagers,GregorianCalendar initialDatePlanned, GregorianCalendar finalDatePlanned){
		String msg = "The project was not added. The maximum number of projects is "+ MAX_PROJECTS;
		int pos = getfirstValidPos();
		if(pos != -1){
			projects[pos] = new Project(name, clientName, budget, nameManagers,phoneManagers,initialDatePlanned,finalDatePlanned);
			msg = "The project was added succesfully";
		}
		return msg;
	}

	public String initStages(String projectName,int months[]){
		Project project= searchProjectByName(projectName);
		project.initStages(months);
		return "The stages has been inicialized";
	}
	
	
	private int getfirstValidPos(){
		int pos = -1;
		for(int i = 0;i<MAX_PROJECTS && pos == -1;i++){
			if(projects[i]==null){
				pos=i;
			}
		}
		return pos;
	}

	public int getNumManagers(){
		return Project.NUM_MANAGERS;
	}

	public boolean validateProjectName(String name){
		Project projectToSearch=searchProjectByName(name);
		boolean isValid=false;
		if(projectToSearch==null){
			isValid=true;
		}
		return isValid;
	}
	/**
	 * Take the name, And returns a project with this name. If there is no any project, return null.
	 *@param projectName an string with the name of the project
	 *@return a project with the name that was given as a parameter
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
	 * Returns an array of string that contains all of the possibles stagesTypes
	 * @return an array of strings 
 	*/

	public String[] possibleStageTypesInStr(){
		return StageType.optionsInStr();
	}
	/**
	 *Take the name of a project and culminate its current stage
	 *@param projectName the name of the project
	 *@return It says if there is no project with this name, if all of the project stages were finished, and if it was possible to finish the current stage

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
	 * Take an id an returns a capsule whit this id
	 * @return If there is no any capsule, returns null 
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
	 * Validate if it is possible to choose the string as an ID of a project

 	*/
	public boolean validateIdCapsule(String id){
		boolean isValid=true;
		if(searchCapsuleById(id)!=null){
			isValid=false;
		}
		return isValid;
	}

	public String[] possibleCapsuleTypeInStr(){
		return CapsuleType.optionsInStr();
	}

	public boolean validateCapsuleType(String capsuleType){
		boolean isValid=false;
		if(CapsuleType.contains(capsuleType)){
			isValid=true;
		}
		return isValid;
	}
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