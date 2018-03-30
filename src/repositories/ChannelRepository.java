package repositories;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import dataclasses.Channel;
import dataclasses.User;
import exceptions.IllegalUserArgumentException;

public class ChannelRepository {
	//for DB
	private static final String INSERT_INTO_CHANNELS =
			"INSERT INTO users (user_id) VALUES (?);";
	private static final String SELECT_ALL_CHANNELS = 
			"SELECT channel_id,user_name FROM channels JOIN users ON user.user_id=channel.user_id;";
	private static final String SELECT_CHANNEL_BY_USER_ID =
			"SELECT channel_id FROM channels WHERE user_id = ?";
	

	// json
	private static final String CHANNELS_JSON_FILE = ".//JSONfiles//channels.json";
	


	
	private Map<String, Channel> channels;

	private static ChannelRepository instance;
	private Connection connection;

	private ChannelRepository() {
		 connection = DBManager.getInstance().getConnection();

	}

	public static ChannelRepository getInstance() {
		if (instance == null) {
			instance = new ChannelRepository();
		}
		return instance;
	}
	// DB
      
	public void addNewChannelToDB(User user) throws SQLException {
		PreparedStatement st = connection.prepareStatement(INSERT_INTO_CHANNELS);
		st.setInt(1, user.getUserId());
		st.close();
		//TODO call channeldao for insert new channel in channel table 

	}
	
	public Map<String, Channel> getAllChannels() throws SQLException {
        Map<String, Channel> channels = new HashMap<String, Channel>();
		PreparedStatement channelSt = connection.prepareStatement(SELECT_ALL_CHANNELS);
		ResultSet channelRS = channelSt.executeQuery();
		// get all channles
		while (channelRS.next()) {
			User user = UserRepository.getInstance().getUserByUserName(channelRS.getString("user_name"));
			Channel channel = new Channel(channelRS.getInt("channel_id"),user);
			channel.setVideoclips(VideoRepository.getInstance().getVideosForChannel(channel));
			channel.setPlaylists(PlaylistDAO.getInstance().getPlaylistForChannel(channel));
			channel.setChannels(this.getFollowedChannels(channel));
			channel.setFollowers(this.getFolowersCountForChannel(channel));
			//put channels to map -key is usarname, value Channel object
			channels.put(user.getUserName(), channel);
		}
		channelRS.close();
		channelSt.close();
        // System.out.println("Users loaded successfully");

        return Collections.unmodifiableMap(channels);
	}
	
	public Channel getChannelById() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Channel getChannelByUserName(String username) throws SQLException  {
	      return this.getAllChannels().get(username);
	}
	private Set<Channel> getFollowedChannels(Channel channel){
		//TO DO Write selection for this
		return Collections.EMPTY_SET;
	}
   public long getFolowersCountForChannel(Channel channel){
	   //to do write select for this
	   return 0;
   }

	// JSON
	private Map<String, Channel> getChannelsFromJSONFILE() {
		Gson gson = new Gson();
		Map<String, Channel> map = null;
		try (Reader reader = new FileReader(CHANNELS_JSON_FILE)) {
			JsonElement json = gson.fromJson(reader, JsonElement.class);
			String jsonInString = gson.toJson(json);

			// System.out.println(jsonInString);
			map = gson.fromJson(jsonInString, new TypeToken<Map<String, Channel>>() {
			}.getType());

		} catch (IOException e) {
			e.printStackTrace();
		}
		if (map == null) {
			map = new TreeMap<>();
		}
		return map;

	}

	private void writeUsersToJSONFile(Map<String, Channel> channels) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		String json = gson.toJson(channels);
		// System.out.println(json);
		try (FileWriter writer = new FileWriter(CHANNELS_JSON_FILE)) {

			gson.toJson(channels, writer);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Channel getChannelToUser(User user) throws IllegalUserArgumentException {
		this.channels = this.getChannelsFromJSONFILE();
		if (channels == null) {
			channels = new HashMap<>();
		}
		if (!channels.containsKey(user.getUserName())) {
			Channel channel = new Channel(user);
			channels.put(user.getUserName(), channel);
			this.writeUsersToJSONFile(channels);
		}
		return this.channels.get(user.getUserName());

	}

	
}
