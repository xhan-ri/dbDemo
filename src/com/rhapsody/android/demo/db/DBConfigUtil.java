package com.rhapsody.android.demo.db;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;
import com.rhapsody.android.demo.db.data.Album;
import com.rhapsody.android.demo.db.data.Track;

public class DBConfigUtil extends OrmLiteConfigUtil {
	public static void main(String[] args) throws Exception {
        writeConfigFile("db_config.txt", new Class[] {Album.class, Track.class});
      }
}
