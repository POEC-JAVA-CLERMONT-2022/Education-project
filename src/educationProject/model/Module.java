package educationProject.model;

import java.util.LinkedList;
import java.util.List;

public class Module {
	private String title;
	private Lesson lesson;
	private List<Review> reviews;
	private Video video;

	public Module(String title) {
		super();
		this.title = title;
		this.reviews=new LinkedList<Review>();
		
	}
	
	

}
