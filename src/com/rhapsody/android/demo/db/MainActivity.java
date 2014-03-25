package com.rhapsody.android.demo.db;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.table.TableUtils;
import com.rhapsody.android.demo.db.data.Album;
import com.rhapsody.android.demo.db.data.Track;

public class MainActivity extends Activity {

	private DBHelper dbHelper;
	private SelectArg selArg;
	PreparedQuery<Album> query;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);
		selArg = new SelectArg();
		try {
			query = getDBHelper().getAlbumDataDao().queryBuilder().where().eq("id", selArg).prepare();
		} catch (SQLException e1) {
			throw new RuntimeException(e1);
		}
		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
			
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public void onClick(View v) {
				List<Album> albums = null;
				selArg.setValue("alb.1");
				albums = new RuntimeExceptionDao(getDBHelper().getAlbumDataDao()).query(query);
				alertAlbums(albums);
			}
		});
		
		findViewById(R.id.button2).setOnClickListener(new OnClickListener() {
			
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public void onClick(View v) {
				selArg.setValue("alb.2");
				List<Album> albums = new RuntimeExceptionDao(getDBHelper().getAlbumDataDao()).query(query);
				alertAlbums(albums);
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		try {
			TableUtils.clearTable(getDBHelper().getConnectionSource(), Album.class);
			TableUtils.clearTable(getDBHelper().getConnectionSource(), Track.class);
			getDBHelper().getAlbumDataDao().callBatchTasks(new Callable<Void>() {

				@Override
				public Void call() {
					try {
						Album album = newAlbum();
						
						album.setId("alb.1");
						album.setName("album 1");
						album.setYear(2003);
						album.create();
						
						Track track = newTrack();
						track.setAlbum(album);
						track.setTrackId("tra.1");
						track.setTitle("Track title 1");
						track.setLength(230);
						track.setGenreId("g.123");
						track.create();
						
						track = newTrack();
						track.setAlbum(album);
						track.setTrackId("tra.2");
						track.setTitle("Track title 2");
						track.setLength(240);
						track.setGenreId("g.234");
						track.create();
						
						album = newAlbum();
						album.setId("alb.2");
						album.setName("album 2");
						album.setYear(2010);
						album.create();
						
						track = newTrack();
						track.setTrackId("tra.3");
						track.setTitle("track title 3");
						track.setLength(250);
						track.setAlbum(album);
						track.setGenreId("g.345");
						track.create();
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
					return null;
				}
				
			});
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (dbHelper != null) {
			OpenHelperManager.releaseHelper();
			dbHelper = null;
		}
	}

	private DBHelper getDBHelper() {
		if (dbHelper == null) {
			dbHelper = OpenHelperManager.getHelper(this, DBHelper.class);
		}
		return dbHelper;
	}
	
	private Album newAlbum() throws SQLException {
		Album album = new Album();
		album.setDao(getDBHelper().getAlbumDataDao());
		return album;
	}
	
	private Track newTrack() throws SQLException {
		Track track = new Track();
		track.setDao(getDBHelper().getTrackDataDao());
		return track;
	}

	private void alertAlbums(List<Album> albums) {
		StringBuilder sb = new StringBuilder();
		for (Album album : albums) {
			sb.append("album=").append(album);
			AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
			builder.setMessage(sb.toString());
			builder.setPositiveButton("OK", null);
			builder.create().show();
			
		}
	}
	
}
