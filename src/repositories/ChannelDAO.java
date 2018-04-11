package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import dataclasses.Channel;
import dataclasses.User;
import exceptions.IllegalInputException;
import exceptions.InvalidDataException;

public class ChannelDAO {
	//for DB
	//selects
	private static final String ALL_CHANNELS = 
			"SELECT channel_id,user_name FROM channels JOIN users ON users.user_id=channels.user_id;";
	private static final String BY_USERNAME = 
			"SELECT c.channel_id, u.user_name FROM channels c JOIN users u ON u.user_id=c.user_id WHERE u.user_name = ?;";
    private static final String BY_CHANNEL_ID =
			"SELECT c.channel_id, u.user_name FROM channels c JOIN users u ON u.user_id=c.user_id WHERE c.channel_id = ?;";
    private static final String COUNT_OF_FOLLOWERS =
      "SELECT COUNT(follower_channel_id) FROM channels_followed_channels WHERE followed_channel_id = ?;";
    private static final String FOLLOWED_CHANNELS = 
			"SELECT u.user_name,f.channel_id FROM users u JOIN channels f ON f.user_id=u.user_id"+
                 " JOIN channels_followed_channels t ON f.channel_id = followed_channel_id WHERE follower_channel_id = ?;";
   //inserts 
    private static final String INSERT_INTO_CHANNELS =
			"INSERT INTO channels (user_id) VALUES (?);";
    private static final String FOLLOW_CHANNEL = 
			"INSERT INTO channels_followed_channels (follower_channel_id, followed_channel_id) VALUES (?,?);";
    
    //delete
    private static final String UNFOLLOW_CHANNEL = 
            "DELETE FROM channels_followed_channels where follower_channel_id = ? and followed_channel_id = ? ;";
	private static final String DELETE_CHANNEL = 
                       "DELETE FROM channels WHERE channel_id = ?;";
	private static final String DELETE_FOLLOWERS = 
			"DELETE FROM channels_followed_channels where follower_channel_id = ? ";
	private static final String DELETE_FOLLOWEDS= 
			"DELETE FROM channels_followed_channels where followed_channel_id = ? ";
//   json
//	private static final String CHANNELS_JSON_FILE = ".//JSONfiles//channels.json";
//    private Map<String, Channel> channels;

	private static ChannelDAO instance;
	private Connection connection;

	private ChannelDAO() {
		 connection = DBManager.getInstance().getConnection();

	}

	public static ChannelDAO getInstance() {
		if (instance == null) {
			instance = new ChannelDAO();
		}
		return instance;
	}
	
	// DB   
	public void addNewChannelToDB(User user) throws SQLException {
		PreparedStatement st = connection.prepareStatement(INSERT_INTO_CHANNELS);
		st.setInt(1, user.getUserId());
		st.executeUpdate();
		st.close();
	}
	
	public Map<String, Channel> getAllChannels() throws SQLException, IllegalInputException {
        Map<String, Channel> channels = new HashMap<String, Channel>();
		PreparedStatement channelSt = connection.prepareStatement(ALL_CHANNELS);
		ResultSet channelRS = channelSt.executeQuery();
		// get all channles
		for(Channel channel : createChannelsFromRezultSet(channelRS)){
			channels.put(channel.getUser().getUserName(),channel);
		};
		channelRS.close();
		channelSt.close();
        // System.out.println("Users loaded successfully");

        return Collections.unmodifiableMap(channels);
	}

	private List<Channel> createChannelsFromRezultSet(ResultSet channelRS) throws SQLException, IllegalInputException {
		List<Channel> allChannels = new ArrayList<Channel>();
		while (channelRS.next()) {
			User user = UserDAO.getInstance().getUserByUserName(channelRS.getString("user_name"));
			Channel channel = new Channel(channelRS.getInt("channel_id"),user);
//			channel.setVideoclips(VideoRepository.getInstance().getVideosForChannel(channel));
//			channel.setPlaylists(PlaylistDAO.getInstance().getPlaylistForChannel(channel));
//			channel.setChannels(this.getFollowedChannels(channel));
//			channel.setFollowers(this.getFolowersCountForChannel(channel));
//			//put channels to map -key is usarname, value Channel object
			allChannels.add(channel);
		}
		
		return allChannels;
	}
	
	public Channel getChannelById(int id) throws SQLException, IllegalInputException {
		PreparedStatement channelSt = connection.prepareStatement(BY_CHANNEL_ID);
		channelSt.setInt(1,id);
		ResultSet channelRS = channelSt.executeQuery();
		List<Channel> list = createChannelsFromRezultSet(channelRS);
		channelRS.close();
		channelSt.close();
		if(list.isEmpty()){
			throw new IllegalInputException("CHANNEL WITH THIS ID NOT FOUND");
		}
		return list.get(0);
	}
	
	public Channel getChannelByUserName(String username) throws SQLException, IllegalInputException  {
		PreparedStatement channelSt = connection.prepareStatement(BY_USERNAME);
		channelSt.setString(1,username);
		ResultSet channelRS = channelSt.executeQuery();
		List<Channel> list = createChannelsFromRezultSet(channelRS);
		channelRS.close();
		channelSt.close();
		if(list.isEmpty()){
			throw new IllegalInputException("CHANNEL WITH THIS USERNAME NOT FOUND!");
		}
		return list.get(0);
	}
	
    public int getFolowersCountForChannel(Channel channel) throws SQLException{
		     PreparedStatement channelSt = connection.prepareStatement(COUNT_OF_FOLLOWERS);
             channelSt.setInt(1, channel.getChannelId());
             ResultSet channelRS = channelSt.executeQuery();
			int count=0;
			while(channelRS.next()){
				count=channelRS.getInt(1);
			}
			channelRS.close();
			channelSt.close();
		   return count;
	   }
	
	public List<Channel> getFollowedChannels(Channel channel) throws  SQLException, IllegalInputException{
		PreparedStatement channelSt = connection.prepareStatement(FOLLOWED_CHANNELS);
		channelSt.setInt(1,channel.getChannelId());
		ResultSet channelRS = channelSt.executeQuery();
		List<Channel> list = createChannelsFromRezultSet(channelRS);
		channelRS.close();
		channelSt.close();
		if(list.isEmpty()){
			throw new IllegalInputException("CHANNEL WITH THIS USERNAME NOT FOUND!");
		}
		return list;
	}
  
	public void  followChannel(Channel followerChannel, Channel folowedChannel) throws SQLException, IllegalInputException {
		if(followerChannel.getChannelId()==folowedChannel.getChannelId()){
			throw new IllegalInputException("YOU CAN NOT FOLLOW OWN CHANNEL!");
		}
	    PreparedStatement st = connection.prepareStatement(FOLLOW_CHANNEL);
		st.setInt(1, followerChannel.getChannelId());
		st.setInt(2, folowedChannel.getChannelId());
		st.executeUpdate();
		st.close();
	
}
 
	public void unfollowChannel(Channel followerChannel, Channel folowedChannel) throws SQLException, IllegalInputException{
		if(followerChannel.getChannelId()==folowedChannel.getChannelId()){
			throw new IllegalInputException("INVALID INPUT");
		}
	    PreparedStatement st = connection.prepareStatement(UNFOLLOW_CHANNEL);
		st.setInt(1, followerChannel.getChannelId());
		st.setInt(2, folowedChannel.getChannelId());
		st.executeUpdate();
		st.close();
	}

	public void deleteChannel(Channel channel) throws SQLException, IllegalInputException{
		this.deleteChannelFromFolowerTable(channel,DELETE_FOLLOWERS );
		this.deleteChannelFromFolowerTable(channel,DELETE_FOLLOWEDS );
		CommentDAO.getInstance().deleteChannelComments(channel);
		VideoDAO.getInstance().deleteChannelVideos(channel);
		PlaylistDAO.getInstance().deleteChannelPlaylists(channel);
	    PreparedStatement st = connection.prepareStatement(DELETE_CHANNEL);
		st.setInt(1, channel.getChannelId());
		st.executeUpdate();
		st.close();
	}


	public void deleteChannelByUsername(String userName) throws SQLException, IllegalInputException {
		Channel channel = this.getChannelByUserName(userName);
		this.deleteChannel(channel);

	}
	
    private void deleteChannelFromFolowerTable(Channel channel,String string) throws SQLException{
    	 PreparedStatement st = connection.prepareStatement(string);
 		st.setInt(1, channel.getChannelId());
 		st.executeUpdate();
 		st.close();
    }
//	JSON
//	private Map<String, Channel> getChannelsFromJSONFILE() {
//		Gson gson = new Gson();
//		Map<String, Channel> map = null;
//		try (Reader reader = new FileReader(CHANNELS_JSON_FILE)) {
//			JsonElement json = gson.fromJson(reader, JsonElement.class);
//			String jsonInString = gson.toJson(json);
//
//			// System.out.println(jsonInString);
//			map = gson.fromJson(jsonInString, new TypeToken<Map<String, Channel>>() {
//			}.getType());
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		if (map == null) {
//			map = new TreeMap<>();
//		}
//		return map;
//
//	}
//
//	private void writeUsersToJSONFile(Map<String, Channel> channels) {
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
//
//		String json = gson.toJson(channels);
//		// System.out.println(json);
//		try (FileWriter writer = new FileWriter(CHANNELS_JSON_FILE)) {
//
//			gson.toJson(channels, writer);
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public Channel getChannelToUser(User user) throws IllegalUserArgumentException {
//		this.channels = this.getChannelsFromJSONFILE();
//		if (channels == null) {
//			channels = new HashMap<>();
//		}
//		if (!channels.containsKey(user.getUserName())) {
//			Channel channel = new Channel(user);
//			channels.put(user.getUserName(), channel);
//			this.writeUsersToJSONFile(channels);
//		}
//		return this.channels.get(user.getUserName());
//
//	}

	
}
