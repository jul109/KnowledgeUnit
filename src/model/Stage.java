package model;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
public class Stage{
	private StageType stageType;
	private boolean active;
	private boolean approved;
	private int[] numCapsulesPerType;
	private int numCapsulesAdded;
	private GregorianCalendar initialPlannedDate;
	private GregorianCalendar finalPlannedDate;
	private GregorianCalendar initialRealDate;
	private GregorianCalendar finalRealDate;
	
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

}