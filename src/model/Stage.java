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

}