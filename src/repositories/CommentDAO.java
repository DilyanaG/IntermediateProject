package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dataclasses.Channel;
import dataclasses.Comment;
import dataclasses.Playlist;
import dataclasses.Video;

public class CommentDAO {
	
	private static final String SELECT_ALL_COMMENTS_BY_VIDEO_ID =
			"SELECT comment_id, content, date, likes, dislikes,  FROM comments WHERE video_id = ?";
	private static CommentDAO instance;
	private Connection connection;
	
	private CommentDAO() {
		 connection = DBManager.getInstance().getConnection();

	}

	public static CommentDAO getInstance() {
		if (instance == null) {
			instance = new CommentDAO();
		}
		return instance;
	}
	List<Comment> getCommentsForVideo(Video video) throws SQLException {

		  List<Comment> comments = new  ArrayList<Comment>();
			PreparedStatement st = connection.prepareStatement(SELECT_ALL_COMMENTS_BY_VIDEO_ID);
			st.setInt(1,video.getVideoId());
			ResultSet rezultSet= st.executeQuery();
			// get all channles
			while (rezultSet.next()) {
				
				Comment comment = new Comment(rezultSet.getInt("comment_id"),
												video.getChannel(),
												rezultSet.getString("content"),
												rezultSet.getDate("date"),
												rezultSet.getInt("likes"),
												rezultSet.getInt("dislikes"));
				comment.setResponses(this.getResponsesForComment(comment));
				
			    comments.add(comment);
		
			}
			rezultSet.close();
			st.close();
	        // System.out.println("Users loaded successfully");

	        return Collections.unmodifiableList(comments);
	}

	private List<Comment> getResponsesForComment(Comment comment) {
		//TO DO select
		return Collections.EMPTY_LIST;
	}

}
