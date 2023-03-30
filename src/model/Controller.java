package model;

public class Controller{
	public static final int MAX_PROJECTS=10;
	private Project projects [];

	public Controller(){
		projects=new Project[MAX_PROJECTS];
		System.out.println("Hola desde el constructor del controller");
	}

}