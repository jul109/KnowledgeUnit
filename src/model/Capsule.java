package model;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
public class Capsule{
	private Collaborator collaborator;
	private String description;
	private String learningExperiences;
	private String id;
	private boolean approved;
	private boolean published;
	private String url;
	private CapsuleType type; 
	private String[] keyWordsLearningExperiences;
	private String[] keyWordsDescription;
	private GregorianCalendar approvalDate;
	/**
 	* This constructor creates a new Capsule object with the given collaborator, description,
 	* learning experiences, id, and type.
 	*
 	* @param collaborator the collaborator who registered the capsule
 	* @param description the description of the capsule
 	* @param learningExperiences the learning experiences of the capsule
 	* @param id the id of the capsule
 	* @param type the type of the capsule in string
 	*/
	public Capsule(Collaborator collaborator, String description, String learningExperiences, String id, CapsuleType type){
	    this.collaborator = collaborator;
	    this.description = description;
	    this.learningExperiences = learningExperiences;
	    this.id = id;
	    this.type = type;
	    this.keyWordsDescription=description.split("#");
	    this.keyWordsLearningExperiences=learningExperiences.split("#");
	}
	/**
 	* This method retrieves the id of this capsule.
 	*
 	* @return the id of this capsule
 	*/

	public String getId(){
		return id;
	}
	/**
 	* This method returns information about the capsule.
 	*
 	* @return a string containing information about this capsule
 	*/
	public String getInfo() {
    return "Collaborator: " + collaborator.getName() + ", Description: " + description +
           ", Learning Experiences: " + learningExperiences + ", ID: " + id + ", Type: " + type.name();
	}
	/**
 	* This method sets the published status of the capsule.
 	*
 	* @param published the new published status of this capsule
 	*/
	public void setPublished(boolean published){
		this.published=published;
	}
	/**
 	*This method sets the approved status of this capsule.
 	*
 	*@param approved the new approved status of this capsule
 	*/
	public void setApproved(boolean approved){
		this.approved=approved;
	}
	/**
 	* Sets the approval date for this object.
 	*
 	* @param approvalDate the GregorianCalendar object representing the approval date
 	*/
	public void setApprovalDate(GregorianCalendar approvalDate){
		this.approvalDate=approvalDate;
	}
	/**
 	* Sets the URL for this object.
 	*
 	* @param url the String representing the URL
 	*/
	public void setUrl(String url){
		this.url=url;
	}
	/**
 	* Returns the approval status of this the capsule
 	*
 	* @return true if this object is approved, false otherwise
 	*/
	public boolean getApproved(){
		return this.approved;
	}
	/**
 	* Returns the name of the collaborator for this object.
 	*
 	* @return the name of the collaborator as a String
 	*/
	public String getCollaboratorName(){
		return this.collaborator.getName();
	}
	/**
 	* Returns the type of this capsule.
 	*
 	* @return the CapsuleType representing the type of this capsule
 	*/
	public CapsuleType getType(){
		return type;
	}
	/**
 	* Returns the publication status of the capsule
 	*
 	* @return true if the capsule is published, false otherwise
 	*/
	public boolean isPublished(){
		return published;
	}
	/**
	* Checks if the capsule contains any of the given words as a keyword of the description or as a keyword of the learning experience
	*
	* @param keyWordsToSearch an array of Strings representing the keywords to search for
	* @return true if this object contains any of the given keywords, false otherwise
	*/
	public boolean contains(String keyWordsToSearch[]){
		boolean contains=false;
		for(int j=0;j<keyWordsToSearch.length;j++){
			String keyWordToSearch=keyWordsToSearch[j];
			
			for (int i=0;i<this.keyWordsDescription.length&&!contains;i++ ) {
				if(this.keyWordsDescription[i].equalsIgnoreCase(keyWordToSearch)){
					contains=true;
				}
			}
			for (int i=0;i<this.keyWordsLearningExperiences.length&&!contains ;i++ ) {
				if(this.keyWordsLearningExperiences[i].equalsIgnoreCase(keyWordToSearch)){
					contains=true;
				}
			}
		}
		return contains;
	}




}