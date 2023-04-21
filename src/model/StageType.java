package model;
public enum StageType{
	INITIATION,
	ANALYSIS,
	EXECUTION,
	CLOSURE,
	MONITORING,
	CONTROL;
	/**
 	* This method checks if the given option in string is a valid StageType.
 	*
 	* @param option the option to check
 	* @return true if the given option is a valid StageType, false otherwise
 	*/
	public static boolean contains(String option){
		StageType options[]=StageType.values();
		boolean isValid=false;
		for(int i=0;i<options.length&&!isValid;i++){
			StageType aux=options[i];
			if(aux.name().equalsIgnoreCase(option)){
				isValid=true;
			}
		}
		return isValid;
	}
	/**
 	* This method return an array of the names of all StageType options.
 	*
 	* @return an array of strings containing the names of all StageType options
 	*/
	public static String[] optionsInStr(){
		StageType options[]=StageType.values();
		String[] optionsInString=new String[options.length];
		
		for(int i=0;i<options.length;i++){
			optionsInString[i]=options[i].name();
		}
		return optionsInString;
	}
}