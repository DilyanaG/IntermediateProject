package comparators;

import java.util.Comparator;
import dataclasses.Playlist;

public class PlaylistByLastVideoUploadComparator implements Comparator<Playlist> {

	@Override
	public int compare(Playlist playlist1, Playlist playlist2) {
		return playlist1.getLastVideoUploaded().compareTo(playlist2.getLastVideoUploaded());
	}

}
