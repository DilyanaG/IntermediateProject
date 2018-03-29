package repositories;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import dataclasses.Channel;
import dataclasses.User;
import exceptions.IllegalUserArgumentException;

public class ChannelRepository {
	private static final String CHANNELS_JSON_FILE = ".//JSONfiles//channels.json";
	private static ChannelRepository channelRepository;
	
	private  Map<String, Channel> channels;
	private ChannelRepository() {

	}
	public static ChannelRepository getInstance(){
		if(channelRepository == null){
			channelRepository = new ChannelRepository();
		}
		return channelRepository;
	}
	
	private  Map<String, Channel> getChannelsFromJSONFILE() {
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
		if(map==null){
			map=new TreeMap<>();
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
	public Channel getChannelToUser(User user) 
			throws IllegalUserArgumentException{
		this.channels = this.getChannelsFromJSONFILE();
		if(channels == null){
			channels = new HashMap<>();
		}
		if(!channels.containsKey(user.getUserName())){
			Channel channel = new Channel(user);
			channels.put(user.getUserName(),channel);
			this.writeUsersToJSONFile(channels);
		}
		return this.channels.get(user.getUserName());
	}
	
}

