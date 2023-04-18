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
	/**
    * Show a menu with the possible options that can be chosen by the user. 
    * Read an execute the option of the user. If the option is not valid, request it again.
    */
	public void menu(){
		int option=-1;
		boolean execute=true;
		while(execute){
			System.out.println("1 to add a project");
			System.out.println("2 to culminate the current stage of a project");
			System.out.println("3 to register a capsule");
			System.out.println("4 to approve a capsule");
			System.out.println("5 to publish a capsule");
			System.out.println("6 to exit the program");
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
					approveCapsule();
					break;
				case 5:
					publishCapsule();
					break;
				case 6:
					execute=false;
					break;
				default:
					System.out.println("Invalid option");
			}
		}
	}

	
	/**
    * Read an validate the information of the information of a project
    * prints a message that says if it was possible to add this new project
    * 
    */

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
	/**
    * Request an array of positive values and initialize the dates of the project with the name that was given
    * @param name The name of the project
    */
	public void manageStages(String name){
		int []durationStages=new int[controller.possibleStageTypesInStr().length];
		
		for(int i=0;i<durationStages.length;i++){
			System.out.println("Type the duration in months of the stage "+controller.possibleStageTypesInStr()[i] );
			durationStages[i]=validatePositiveInt();
		}
		controller.initStages(name,durationStages);
		System.out.println("The stages were initialized succesfully");

		
		
	}
	/**
    * Read the name of a project, and prints a message that says if it was possible to culminate the currentStage
    * 
    */

	public void culminateCurrentStage(){
		String projectName, msg="";
		
		System.out.println("Type the name of the project");
		projectName=validateStringInput();
		
		msg=controller.culminateCurrentStage(projectName);
		System.out.println(msg);
	}
	/**
    * Request the name and the charge of a collaborator and the information about his capsule. 
    * Prints a message indicating whether the capsule was successfully added or not.
    * 
    */

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
		
		String msg=controller.addCapsule(projectName,collabName,collabCharge,description,learningExperience,id,capsuleType);
		System.out.println(msg);

	}
	/**
    * Request an url of a capsule and prints a message that says if it was possible to approved it.
    * 
    */

	public void approveCapsule(){
		
		String id="";
		String msg="";
		System.out.println("Type the id of the capsule");
		id=validateStringInput();
		
		msg=controller.approveCapsule(id);
		System.out.println(msg);

	}
	/**
    * Request strings that contain an a ID and a URL. Prints a message that says if it was possible to publish the capsule or not
    * 
    */

	public void publishCapsule(){
		String id="",url="";
		String msg="";
		System.out.println("Type the id of the capsule");
		id=validateStringInput();
		System.out.println("Type the url");
		url=validateStringInput();
		msg=controller.publishCapsule(id,url);
		System.out.println(msg);
	}


	/*||||||||||||||||||||||||||||||||||||||VALIDATION FUNCTIONS||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||**/

	/**
    * Read the input of the user and return it. If the user typed a non integer value, return -1.
    * 
    * @return The value that was registered by the user, or -1 if the user didn't type an integer.
    */


	public int validateIntegerInput(){
		int num = -1;
		if(reader.hasNextInt()){
			num = reader.nextInt();
		}else{
			reader.next();
		}
		return num;
	}
	/**
    * Put the user in a loop until he types a positive integer
    * 
    * @return An positive double
    */
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
	
	/**
    * Read the input of the user. If the user typed a non double value, returns -1.
    * 
    * @return The value that was registered by the user, or -1 if the user didn't type a double.
    */

	public double validateDoubleInput(){
		double num=-1.0;
		if(reader.hasNextDouble()){
			num=reader.nextDouble();
		}else{
			reader.next();
		}
		return num;

	}

	/**
    * Put the user in a loop until he types a positive double
    * 
    * @return An positive double
    */
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

	


	/**
    * Put the user in a loop until he or she types a non empty string.
    * 
    * @return An non empty string
    */

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




	/**
	* Put the user in a loop until the user types a valid date.
	* @return Return a valid GregorianCalendar date. A valid day is an integer from [1 to 31]. A valid month is an integer from [1 to 12]. A valid year is an integer greater than 0.
	*/
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