package com.rhapsody.android.demo.db;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.rhapsody.android.demo.db.data.Album;
import com.rhapsody.android.demo.db.data.Track;

public class DBHelper extends OrmLiteSqliteOpenHelper {
	
	private static final String DB_NAME = "rhapsody.db";
	private static final int CURRENT_VERSION = 2;
	private Dao<Track, Integer> trackDao;
	private Dao<Album, Integer> albumDao;
	public DBHelper(Context context) {
		super(context, DB_NAME, null, CURRENT_VERSION, R.raw.db_config);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0, ConnectionSource arg1) {
		try {
			TableUtils.createTable(connectionSource, Track.class);
			TableUtils.createTable(connectionSource, Album.class);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, ConnectionSource arg1, int oldVer,
			int newVer) {
		if (oldVer <= 2) {
			try {
				getTrackDataDao().executeRaw("alter table 'track' add column genreId String;");
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	/**
	 * Returns the Database Access Object (DAO) for our SimpleData class. It will create it or just give the cached
	 * value.
	 */
	public Dao<Track, Integer> getTrackDataDao() {
		if (trackDao == null) {
			try {
				trackDao = getDao(Track.class);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return trackDao;
	}
	
	public Dao<Album, Integer> getAlbumDataDao() {
		if (albumDao == null) {
			try {
				albumDao = getDao(Album.class);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return albumDao;
	}

}
