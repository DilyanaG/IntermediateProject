package datastorage;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.*;




public class Video {
 private final String url;
 private final Channel channel;
 private String title;
 private String description;
 private LocalDateTime date;
 private Set<String> tags;
 private List<Comment> comments;
 private int countOfLikes=0;
 private int countOfDislikes=0;
 private int views=0;
 
 //TODO validate
public Video(String url, Channel channel, String title) {
	this.url = url;
	this.channel = channel;
	this.setTitle(title);
	comments=new ArrayList<>();
	tags=new TreeSet<String>((t1,t2)->t1.compareTo(t2));
	this.date =LocalDateTime.now();
	
}
public void  addDiscription(String text){
	//TODO validate
	this.description=text;
   
}

public String getTitle() {
	return title;
}


public void setTitle(String title) {
	this.title = title;
}
 
 
}
