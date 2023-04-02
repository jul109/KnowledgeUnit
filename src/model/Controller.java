package model;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

public class Controller{
	public static final int MAX_PROJECTS=2;
	private Project projects [];

	public static String calendarToString(GregorianCalendar calendar) {
    	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    	String dateInTxtx = format.format(calendar.getTime());
    	return dateInTxtx;
	}
	
	public Controller(){
		projects=new Project[MAX_PROJECTS];
		System.out.println("Hola desde el constructor del controller");
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
		project.test();
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

	public String[] possibleStageTypesInStr(){
		return StageType.optionsInStr();
	}
	public void test(){
		for(int i=0;i<projects.length;i++){
			if(projects[i]!=null){
				System.out.println(projects[i].getProjectInfo());
			}
		}
	}
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



}