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
	public Capsule(Collaborator collaborator, String description, String learningExperiences, String id, CapsuleType type){
	    this.collaborator = collaborator;
	    this.description = description;
	    this.learningExperiences = learningExperiences;
	    this.id = id;
	    this.type = type;
	    this.keyWordsDescription=description.split("#");
	    this.keyWordsLearningExperiences=learningExperiences.split("#");
	}
	public String getId(){
		return id;
	}
	public String getInfo() {
    return "Collaborator: " + collaborator.getName() + ", Description: " + description +
           ", Learning Experiences: " + learningExperiences + ", ID: " + id + ", Type: " + type.name();
	}
	public void setPublished(boolean published){
		this.published=published;
	}
	public void setApproved(boolean approved){
		this.approved=approved;
	}
	public void setApprovalDate(GregorianCalendar approvalDate){
		this.approvalDate=approvalDate;
	}
	public void setUrl(String url){
		this.url=url;
	}
	public boolean getApproved(){
		return this.approved;
	}
	public String getCollaboratorName(){
		return this.collaborator.getName();
	}
	public CapsuleType getType(){
		return type;
	}
	public boolean isPublished(){
		return published;
	}
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