package ui;
import java.util.Scanner;
import model.Controller;

public class Main{
	public static Scanner reader=new Scanner(System.in);
	private Controller controller;
	Main(){
		controller=new Controller();
	}
	

	public static void main(String args[]){
		Main view=new Main();
		int x;
		view.addProject();


	}

	public void menuAddProject(){
		int selectedOption;
		boolean execute=true;
		int totalOptions=2;
		do{
			System.out.println("Type 1 to add a project"); //We show the options. These are 1 and 0
			System.out.println("Type 0 to cancel the operation"); 
			
			selectedOption=validateIntegerInput();

			switch (selectedOption) {
				
				case 0: //If the user typed 0. We get out of the loop
					execute=false;
					break;
				
				case 1://if the user typed 1. We try to add the project.
					addProject();
					execute = false; //and we get out of the loop
					break;
				
				default:
					System.out.println("Invalid option");
			}	

		}while(execute);


	}

	public int validateIntegerInput(){
		int num = -1;
		if(reader.hasNextInt()){
			num = reader.nextInt();
		}else{
			reader.next();
		}
		return num;
	}
	public int validatePositiveInt(){
		int value=-1;
		while(value<=0){
			value=validateIntegerInput();
			if(value<=0){
				System.out.println("Invalid value");
			}
		}
		return value;
	}

	public double validateDoubleInput(){
		double num=-1.0;
		if(reader.hasNextDouble()){
			num=reader.nextDouble();
		}else{
			reader.next();
		}
		return num;

	}

	

	public double validatePositiveDouble(){
		double value=-1.0;
		while(value<=0){
			value=validateDoubleInput();
			if(value<=0){
				System.out.println("Invalid value");
			}
		}
		return value;
	}

	public String validateStringInput(){
		boolean isValid=false;
		String str="";
		while(!isValid){
			
			if(reader.hasNext()){
				str=reader.next();
				isValid=true;
			}else{
				System.out.println("It is not valid to choose an empty String");
				reader.nextLine();
			}
			
		}
		return str;

	}


	public void addProject(){
		String name = "";
		String clientName= "";
		int months=-1;
		double budget=-1;
		String[] nameManagers= new String [ controller.getNumManagers() ];
		String[] phoneManagers=new String [ controller.getNumManagers() ];
		boolean isValid=false;
		
		do{
			System.out.println("Type the name of the project");
			name=validateStringInput();
			isValid=controller.validateProjectName(name);			
			
			if( isValid ){
				System.out.println("Valid name");
				isValid=true;
			}else{
				System.out.println("This name was chosen before. Type other");
			}
		
		}while(!isValid);
		
		System.out.println("Type the name of the client");
		clientName=validateStringInput();		
		
		System.out.println("Type the duration of the project in months");
		months=validatePositiveInt();
		
		System.out.println("Type the budget");
		budget=validatePositiveDouble();

		for(int i=0;i<nameManagers.length;i++){
			System.out.println("Type the name of the manager");
			nameManagers[i]=validateStringInput();
			System.out.println("Type the phone of the manager");
			phoneManagers[i]=validateStringInput();
		}
		String msg= controller.addProject(name, clientName,months, budget,nameManagers,phoneManagers);
		System.out.println(msg);


	}
	


}