package com.example.ribath.nupuit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Ribath on 3/31/2017.
 */

public class DBHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "NupuITDB";
    private static final int DATABASE_VERSION = 1;
    private Dao<ContactClass, String> ContactClassDao = null;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, ContactClass.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, ContactClass.class, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<ContactClass, String> getContactClassDao() throws SQLException {
        if (ContactClassDao == null)
        {
            ContactClassDao = getDao(ContactClass.class);
        }
        return ContactClassDao;
    }
}
