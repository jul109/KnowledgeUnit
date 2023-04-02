package model;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
public class Stage{
	public static int MAX_CAPSULES=50;
	private StageType stageType;
	private boolean active;
	private boolean approved;
	private int[] numCapsulesPerType;
	private int numCapsulesAdded;
	private GregorianCalendar initialPlannedDate;
	private GregorianCalendar finalPlannedDate;
	private GregorianCalendar initialRealDate;
	private GregorianCalendar finalRealDate;
	private Capsule[] capsules;
	
	public GregorianCalendar getInitialPlannedDate(){
		return initialPlannedDate;
	}
	public GregorianCalendar getFinalPlannedDate(){
		return finalPlannedDate;
	}
	public Stage(StageType stageType, GregorianCalendar initialPlannedDate, GregorianCalendar finalPlannedDate){
		this.stageType=stageType;
		this.initialPlannedDate=initialPlannedDate;
		this.finalPlannedDate=finalPlannedDate;
		active=false;
		approved=false;
		numCapsulesAdded=0;
		numCapsulesPerType=new int[StageType.values().length];
		capsules=new Capsule[MAX_CAPSULES];
	}
	public String getStageType(){
		return stageType.name();
	}
	public void setActive(boolean active){
		this.active=active;
	}
	public boolean getActive(){
		return this.active;
	}
	public void setInitialRealDate(GregorianCalendar newDate){
		this.initialRealDate=newDate;
	}
	public void setFinalRealDate(GregorianCalendar newDate){
		this.finalRealDate=newDate;
	}
	public GregorianCalendar getFinalRealDate(){
		return this.finalRealDate;
	}
	public GregorianCalendar getInitialRealDate(){
		return this.initialRealDate;
	}
	public int getFirstValidPosInCapsules(){
		int pos=-1;
		boolean isEnded=false;
		for(int i=0;i<capsules.length&&!isEnded;i++){
			if(capsules[i]==null){
				pos=i;
				isEnded=true;
			}
		}
		return pos;
	}
	public boolean addCapsule(Capsule capsule){
		int pos=getFirstValidPosInCapsules();
		boolean isAdded=false;
		if(pos!=-1){
			capsules[pos]=capsule;
			isAdded=true;
		}
		return isAdded;
	}

}