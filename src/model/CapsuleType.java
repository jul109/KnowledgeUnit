package model;
public enum CapsuleType{
	TECHNICAL,
	MANAGEMENT,
	DOMAIN,
	EXPERIENCES;

	public static boolean contains(String option){
		CapsuleType options[]=CapsuleType.values();
		boolean isValid=false;
		for(int i=0;i<options.length&&!isValid;i++){
			CapsuleType aux=options[i];
			if(aux.name().equalsIgnoreCase(option)){
				isValid=true;
				System.out.println(option);
			}
		}
		return isValid;
	}
	public static String[] optionsInStr(){
		CapsuleType options[]=CapsuleType.values();
		String[] optionsInString=new String[options.length];
		
		for(int i=0;i<options.length;i++){
			optionsInString[i]=options[i].name();
		}
		return optionsInString;
	}
}