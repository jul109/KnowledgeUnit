package ui;
import java.util.Scanner;
import java.util.GregorianCalendar;
import model.Controller;

public class Main{
	private Controller controller;
	private Scanner reader;
	Main(){
		controller=new Controller();
		reader=new Scanner(System.in);
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
			System.out.println("||||||WELCOME|||||||||");
			System.out.println("1 to add a project");
			System.out.println("2 to culminate the current stage of a project");
			System.out.println("3 to register a capsule");
			System.out.println("4 to approve a capsule");
			System.out.println("5 to publish a capsule");
			System.out.println("6 to count the number of capsules for any type.");
			System.out.println("7 to show the learning experiences of the capsules in a project stage");
			System.out.println("8 to show the names of the projects that contain the greater number of capsules registered");
			System.out.println("9 to verify by his name if a collaborator has registered a capsule");
			System.out.println("10 to search for the capsules that have been published and approved. It is necessary to type some key words ");
			System.out.println("11 to exit the program");
			System.out.println("|||||||||||||||||||||||");
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
					countCapsulesByType();
					break;
				case 7:
					viewLearningExperiencesInAProjectStage();
					break;
				case 8:
					showProjectsWithTheGreaterNumOfCapsules();
					break;
				case 9:
					showCapsulesOfACollabByHisName();
					break;
				case 10:
					searchCapsulesByKeyWords();
					break;
				case 11:
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
			name=reader.next();
			isValid=controller.validateProjectName(name);			
			
			if( isValid ){
				System.out.println("The name is valid");
				isValid=true;
			}else{
				System.out.println("This name was chosen before. Type other");
			}
		
		}while(!isValid);
		
		System.out.println("Type the name of the client");
		clientName=reader.next();		
		
		
		System.out.println("Type the budget");
		budget=validatePositiveDouble();

		for(int i=0;i<nameManagers.length;i++){
			if(i%2==0){
				System.out.println("Register the information of the manager of Green SQA");
			}else{
				System.out.println("Register the information of the manager of the client");
			}
			System.out.println("Type the name of the manager");
			nameManagers[i]=reader.next();
			System.out.println("Type the phone of the manager");
			phoneManagers[i]=reader.next();
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
		projectName=reader.next();
		
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
		collabName=reader.next();
		System.out.println("Type the charge of the collaborator");
		collabCharge=reader.next();
		
		do{
			System.out.println("Type the type of the capsule.These are the types");
			for(int i=0;i<possibleTypes.length;i++){
				System.out.print(possibleTypes[i]+" ");
			}
			System.out.println();
			capsuleType=reader.next();
			if(!controller.validateCapsuleType(capsuleType)){
				System.out.println("Invalid value");
			}
		
		}while( !controller.validateCapsuleType(capsuleType) );
		reader.nextLine();
		System.out.println("Type description of the capsule");
		description=reader.nextLine();

		System.out.println("Type the learningExperience");
		learningExperience=reader.nextLine();

		System.out.println("Type the id");
		do{
			id=reader.next();
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
		id=reader.next();
		
		msg=controller.approveCapsule(id);
		System.out.println(msg);

	}
	/**
    * Request Id adn URL. Prints a message that says if it was possible to publish the capsule with the given url and id.
    * 
    */

	public void publishCapsule(){
		String id="",url="";
		String msg="";
		System.out.println("Type the id of the capsule");
		id=reader.next();
		System.out.println("Type the url");
		url=reader.next();
		msg=controller.publishCapsule(id,url);
		System.out.println(msg);
	}
	/**
	* This method counts the number of capsules by type and prints the result.
	* The counting is performed by the controller object.
	*/

	public void countCapsulesByType(){
		String msg=controller.countCapsulesByType();
		System.out.println(msg);
	}
	/**
	* This method allows the user to view the learning experiences in a specific project stage.
	* The user is prompted to enter the name of the project and the stage.
	* The information is retrieved from the controller object and printed to the console.
	*/
	public void viewLearningExperiencesInAProjectStage(){
		String projectName,stageName,msg="";
		System.out.println("Type the name of the project");
		projectName=reader.next();
		System.out.println("Type the name of the stage");
		stageName=reader.next();
		msg=controller.getStageCapsulesInfo(projectName,stageName);
		System.out.println(msg);

	}
	/**
    * This method displays the projects with the greatest number of capsules.
    * The information is retrieved from the controller object and printed to the console.
    */
	public void showProjectsWithTheGreaterNumOfCapsules(){
		String msg=controller.projectsWithTheGreaterNumberOfCapsules();
		System.out.println(msg);
	}
	
	/**
    * This method displays ONE capsule of a collaborator by their name.
    * The user is prompted to enter the name of the collaborator.
    * The information is retrieved from the controller object and printed to the console.
    */

	public void showCapsulesOfACollabByHisName(){
		String collabName="";
		String msg="";
		System.out.println("Type the name of the collaborator");
		collabName=reader.next();
		msg=controller.searchCollabCapsules(collabName);
		System.out.println(msg);

	}
	/**
 	* Searches for capsules that contain the specified keywords.
 	* The user is prompted to enter the number of keywords to search for,
 	* and then to enter each keyword. The search results are then displayed.
 	*/
	public void searchCapsulesByKeyWords(){
		int numKeywords;
		String msg="";
		System.out.println("Type the number of key words to search for between the capsules");
		numKeywords=validatePositiveInt();
		reader.nextLine();
		String keyWords[]=new String[numKeywords];
		
		for (int i=0;i<numKeywords ;i++ ) {
			System.out.println("Type the keyword number: "+ (i+1));
			keyWords[i]=reader.nextLine();
		}
		msg=controller.searchCapsulesByKeyWords(keyWords);
		System.out.println(msg);


	}


	/*||||||||||||||||||||||||||||||||||||||VALIDATION FUNCTIONS||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||**/

	/**
    * Reads the input of the user. If the user typed a non integer value, returns -1. Otherwise, returns the input.
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
    * Puts the user in a loop until he types a positive integer
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
    * Reads the input of the user. If the user typed a non double value, returns -1.
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
    * Puts the user in a loop until he types a positive double
    * 
    * @return A positive double
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
	* Puts the user in a loop until he types a valid date.
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