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
	/**
	* Constructs a new Stage with the given parameters.
	*
	* @param stageType the type of the stage
	* @param initialPlannedDate the planned initial date for the stage
	* @param finalPlannedDate the planned final date for the stage
	*/
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
	/**
	 * Returns the planned initial date for this stage.
	 *
	 * @return a gregorian calendar that represents the planned initial date of the stage.
	 */	
	public GregorianCalendar getInitialPlannedDate(){
		return initialPlannedDate;
	}
	/**
	 * Returns the planned final date for this stage.
	 *
	 * @return A gregorian calendar that contains the planned final date of the stage
	 */
	public GregorianCalendar getFinalPlannedDate(){
		return finalPlannedDate;
	}
	/**
	 * Returns the type of this stage.
	 *
 	* @return the type of this stage
 	*/

	public String getStageType(){
		return stageType.name();
	}
	/**
    * Sets the active status of this stage.
    *
    * @param active A boolean that represents the new active status for the stage
    */

	public void setActive(boolean active){
		this.active=active;
	}
	/**
	* Returns the active status of this stage.
	*
	* @return the active status of this stage
	*/

	public boolean getActive(){
		return this.active;
	}
		/**
	 * Sets the real initial date for this stage.
	 *
	 * @param newDate A gregorian calendar that represents the new real initial date for this stage
	 */
	public void setInitialRealDate(GregorianCalendar newDate){
		this.initialRealDate=newDate;
	}
	/**
	* Sets the final real date of this object to the specified value.
	*
	* @param newDate the new final real date to be set
	*/

	public void setFinalRealDate(GregorianCalendar newDate){
		this.finalRealDate=newDate;
	}

	/**
	* Returns the final real date of this object.
	*
	* @return A gregorian calendar that contains the final real date
	*/
	public GregorianCalendar getFinalRealDate(){
		return this.finalRealDate;
	}
	/**
	* Returns the initial real date of this object.
	*
	* @return the initial real date
	*/

	public GregorianCalendar getInitialRealDate(){
		return this.initialRealDate;
	}
	/**
	* Returns the first valid position in the capsules array.
	*
	* @return An integer that represents the first valid position in the capsules array, or -1 if no valid position is found
	*/
	private int getFirstValidPosInCapsules(){
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
	/**
	 * Adds a Capsule object to the capsules array at the first valid position.
	 *
	 * @param capsule the Capsule object to be added
	 * @return true if the Capsule object was added successfully, false otherwise
	 */
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