package model;
public enum StageType{
	INITIATION,
	ANALYSIS,
	EXECUTION,
	CLOSURE,
	MONITORING,
	CONTROL;

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
	public static String[] optionsInStr(){
		StageType options[]=StageType.values();
		String[] optionsInString=new String[options.length];
		
		for(int i=0;i<options.length;i++){
			optionsInString[i]=options[i].name();
		}
		return optionsInString;
	}
}