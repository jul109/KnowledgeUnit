package model;

public class Controller{
	public static final int MAX_PROJECTS=10;
	private Project projects [];

	public Controller(){
		projects=new Project[MAX_PROJECTS];
		System.out.println("Hola desde el constructor del controller");
	}
	
	public String addProject(){
		String msg = "The project was not added. The maximun number of project is "+ MAX_PROJECTS;
		int pos = getfirstValidPos();
		System.out.println(pos);
		if(pos != -1){
			projects[pos] = new Project();
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

}