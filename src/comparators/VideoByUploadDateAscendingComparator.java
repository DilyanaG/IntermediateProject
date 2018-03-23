package comparators;

import java.util.Comparator;

import dataclasses.Video;

public class VideoByUploadDateAscendingComparator implements Comparator<Video> {

	@Override
	public int compare(Video video1, Video video2) {
		return video1.getUploadDate().compareTo(video2.getUploadDate());
	}

}
