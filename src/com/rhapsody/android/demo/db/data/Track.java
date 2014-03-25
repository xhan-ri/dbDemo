package com.rhapsody.android.demo.db.data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.misc.BaseDaoEnabled;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Track extends BaseDaoEnabled<Track, Integer> {
	@DatabaseField(id=true, columnName="id", index=true)
	private String trackId;
	@DatabaseField
	private String title;
	@DatabaseField
	private int length;
	@DatabaseField
	private String genreId;
	
	@DatabaseField(foreign=true, columnName="album_id")
	private Album album;
	
	
	public Track() {
		super();
	}
	
	public Track(String trackId, String title, int length) {
		super();
		this.trackId = trackId;
		this.title = title;
		this.length = length;
	}

	public String getTrackId() {
		return trackId;
	}
	public void setTrackId(String trackId) {
		this.trackId = trackId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	
	
	
	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}
	
	

	public String getGenreId() {
		return genreId;
	}

	public void setGenreId(String genreId) {
		this.genreId = genreId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Track [trackId=").append(trackId).append(", title=")
				.append(title).append(", length=").append(length)
				.append(", genreId=").append(genreId).append("]");
		return builder.toString();
	}

}
