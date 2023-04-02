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
	private String[] keyWords;
	public Capsule(Collaborator collaborator, String description, String learningExperiences, String id, CapsuleType type){
	    this.collaborator = collaborator;
	    this.description = description;
	    this.learningExperiences = learningExperiences;
	    this.id = id;
	    this.type = type;
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
	public void setUrl(String url){
		this.url=url;
	}
	public boolean getApproved(){
		return this.approved;
	}



}