package ui;
import java.util.Scanner;
import java.util.GregorianCalendar;
import model.Controller;

public class Main{
	public static Scanner reader=new Scanner(System.in);
	private Controller controller;
	Main(){
		controller=new Controller();
	}
	

	public static void main(String args[]){
		Main exe=new Main();
		exe.menu();
		

	}
	public void menu(){
		int option=-1;
		boolean execute=true;
		while(execute){
			System.out.println("Type 1 to add a project");
			System.out.println("Type 2 to culminate the current stage of a project");
			System.out.println("Type 3 to register a capsule");
			option=validateIntegerInput();
			switch (option) {
				case 1:
					addProject();
					break;
				case 2:
					culminateCurrentStage();
					break;
				case 3:
					registerCapsule();
					break;
				case 4:
					execute=false;
					break;
				default:
					System.out.println("Invalid option");
			}
		}
	}

	

	


	public void addProject(){
		String name = "";
		String clientName= "";
		int months=-1;
		double budget=-1;
		String[] nameManagers= new String [ controller.getNumManagers() ];
		String[] phoneManagers=new String [ controller.getNumManagers() ];
		GregorianCalendar initialDate;
		GregorianCalendar finalDate;
		boolean isValid=false;
		
		do{
			System.out.println("Type the name of the project");
			name=validateStringInput();
			isValid=controller.validateProjectName(name);			
			
			if( isValid ){
				System.out.println("The name is valid");
				isValid=true;
			}else{
				System.out.println("This name was chosen before. Type other");
			}
		
		}while(!isValid);
		
		System.out.println("Type the name of the client");
		clientName=validateStringInput();		
		
		
		System.out.println("Type the budget");
		budget=validatePositiveDouble();

		for(int i=0;i<nameManagers.length;i++){
			System.out.println("Type the name of the manager");
			nameManagers[i]=validateStringInput();
			System.out.println("Type the phone of the manager");
			phoneManagers[i]=validateStringInput();
		}
		
		System.out.println("Register the inital date planned");
		initialDate = requestDate();
		
		do {
    		System.out.println("Enter the final planned date:");
    		finalDate = requestDate();
    		if (initialDate.compareTo(finalDate) >= 0) {
        	System.out.println("The final date must be greater than the initial date.");
    		}
		} while (initialDate.compareTo(finalDate) >= 0);

		
		String msg= controller.addProject(name, clientName, budget,nameManagers,phoneManagers,initialDate,finalDate);
		System.out.println(msg);
		if( msg.equals("The project was added succesfully") ){
			manageStages(name);
		}


	}

	public void manageStages(String name){
		System.out.println("||||||||||||||||||");
		int []durationStages=new int[controller.possibleStageTypesInStr().length];
		
		for(int i=0;i<durationStages.length;i++){
			System.out.println("Type the duration in months of the stage "+controller.possibleStageTypesInStr()[i] );
			durationStages[i]=validatePositiveInt();
		}
		controller.initStages(name,durationStages);

		
		
	}

	public void culminateCurrentStage(){
		String projectName, msg="";
		
		System.out.println("Type the name of the project");
		projectName=validateStringInput();
		
		msg=controller.culminateCurrentStage(projectName);
		System.out.println(msg);
	}

	public void registerCapsule(){
		String collabName="",capsuleType="",collabCharge="",description="";
		String learningExperience="",id="";
		String[] possibleTypes=controller.possibleCapsuleTypeInStr();
		
		System.out.println("Type the name of the collaborator");
		collabName=validateStringInput();
		System.out.println("Type the charge of the collaborator");
		collabCharge=validateStringInput();
		
		do{
			System.out.println("Type the type of the capsule.These are the types");
			for(int i=0;i<possibleTypes.length;i++){
				System.out.print(possibleTypes[i]+" ");
			}
			System.out.println();
			capsuleType=validateStringInput();
			if(!controller.validateCapsuleType(capsuleType)){
				System.out.println("Invalid value");
			}
		
		}while( !controller.validateCapsuleType(capsuleType) );
		reader.nextLine();
		System.out.println("Type description of the capsule");
		description=validateStringInput();
		reader.nextLine();

		System.out.println("Type the learningExperience");
		learningExperience=reader.nextLine();

		System.out.println("Type the id");
		do{
			id=validateStringInput();
			if(!controller.validateIdCapsule(id)){
				System.out.println("This id was chosen before. Type other");
			}
		}while(!controller.validateIdCapsule(id));
		System.out.println("Type the name of the project");
		String projectName="";
		projectName=reader.next();
/*
		System.out.println("collabName: " + collabName);
		System.out.println("collabCharge: " + collabCharge);
		System.out.println("capsuleType: " + capsuleType);
		System.out.println("description: " + description);
		System.out.println("learningExperience: " + learningExperience);
		System.out.println("id: " + id);
*/		

		String msg=controller.addCapsule(projectName,collabName,collabCharge,description,learningExperience,id,capsuleType);
		System.out.println(msg);
		controller.test();


	}

	/*||||||||||||||||||||||||||||||||||||||VALIDATION FUNCTIONS||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||**/

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
	public GregorianCalendar requestDate(){
		int day=-1,month=-1,year=-1;

		while(! (1<=day&& day<=31  ) ){
			System.out.println("Type the day");
			day=validateIntegerInput();
			if( !(1<=day&& day<=31)  ){
				System.out.println("Invalid value");
			}
		}
		
		while(! (1<=month&&month<=12  ) ){
			
			System.out.println("Type the month");
			month=validateIntegerInput();
			if(!  (1<=month&&month<=12)  ){
				System.out.println("Invalid value");
			}
		}
		
		while(year<=0){
			System.out.println("Type the year");
			year=validateIntegerInput();
			if(year<=0){
				System.out.println("Invalid year");
			}
		}
		GregorianCalendar date=new GregorianCalendar(year,month-1,day);
		return date;
	}
	


}