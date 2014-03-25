package com.rhapsody.android.demo.db.data;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.misc.BaseDaoEnabled;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Album extends BaseDaoEnabled<Album, Integer> {
	@DatabaseField(id=true)
	private String id;
	@DatabaseField
	private String name;
	@DatabaseField
	private int year;
	
	@ForeignCollectionField
	ForeignCollection<Track> tracks;
	public Album() {
		super();
	}
	public Album(String id, String name, int year) {
		super();
		this.id = id;
		this.name = name;
		this.year = year;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	protected ForeignCollection<Track> getTracks() {
		return tracks;
	}
	protected void setTracks(ForeignCollection<Track> tracks) {
		this.tracks = tracks;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Album [id=").append(id).append(", name=").append(name)
				.append(", year=").append(year).append(", tracks=[");
		if (tracks != null) {
			for (Track track : tracks) {
				builder.append(track).append(", ");
			}
		}
		builder.append("]]");
		return builder.toString();
	}
	
	
}
