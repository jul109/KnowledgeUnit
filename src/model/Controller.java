package model;

public class Controller{
	public static final int MAX_PROJECTS=10;
	private Project projects [];

	public Controller(){
		projects=new Project[MAX_PROJECTS];
		System.out.println("Hola desde el constructor del controller");
	}
	
	public String addProject(String name, String clientName, int months, double budget, String[] nameManagers, String[] phoneManagers){
		String msg = "The project was not added. The maximun number of project is "+ MAX_PROJECTS;
		int pos = getfirstValidPos();
		if(pos != -1){
			projects[pos] = new Project(name, clientName, months, budget, nameManagers,phoneManagers);
			msg = "The project was added succesfully";
		}
		return msg;
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
		boolean isEnded = false;
		boolean isFound = false;
		
		for(int i=0;i< MAX_PROJECTS && !isEnded ;i++){
			if(projects[i]==null){
				isEnded=true;
			}else{
				if( projects[i].getName().equalsIgnoreCase(name) ){
					isFound=true;
					isEnded=true;
				}
			}
		}
		return !isFound;
	}

}