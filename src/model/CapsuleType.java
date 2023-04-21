package model;
public enum CapsuleType{
	TECHNICAL,
	MANAGEMENT,
	DOMAIN,
	EXPERIENCES;
	/**
 	* Checks if the given string is a valid CapsuleType.
 	*
 	* @param option the option to check
 	* @return true if the given string is a valid CapsuleType, false otherwise
 	*/

	public static boolean contains(String option){
		CapsuleType options[]=CapsuleType.values();
		boolean isValid=false;
		for(int i=0;i<options.length&&!isValid;i++){
			CapsuleType aux=options[i];
			if(aux.name().equalsIgnoreCase(option)){
				isValid=true;
			}
		}
		return isValid;
	}
	/**
 	* Returns the names of all CapsuleType options as an array of strings.
 	*
 	* @return an array of strings containing the names of all CapsuleType options
 	*/

	public static String[] optionsInStr(){
		CapsuleType options[]=CapsuleType.values();
		String[] optionsInString=new String[options.length];
		
		for(int i=0;i<options.length;i++){
			optionsInString[i]=options[i].name();
		}
		return optionsInString;
	}
}