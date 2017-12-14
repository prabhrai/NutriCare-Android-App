
package com.murach.prabhdeep.nutricare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

public class UserWeightDB {

    // database constants
    public static final String DB_NAME = "UserWeight.db";
    public static final int DB_VERSION = 1;

    // task table constants
    public static final String USERWEIGHT_TABLE = "userweight";

    public static final String LOG_ID = "_id";
    public static final int LOG_ID_COL = 0;

    public static final String LOG_UNAME = "username";
    public static final int LOG_UNAME_COL = 1;

    public static final String LOG_DATE = "date";
    public static final int LOG_DATE_COL = 2;

    public static final String LOG_WEIGHT = "weight";
    public static final int LOG_WEIGHT_COL1 = 3;

    public static final String LOG_BMI = "bmi";
    public static final int LOG_BMI_COL = 4;

//    private String username = null;
//    private String date = null;
//    private String weight = null;
//    private Double bmi = null;

    // CREATE and DROP TABLE statements
    public static final String CREATE_LOG_TABLE =
            "CREATE TABLE " + USERWEIGHT_TABLE + " (" +
                    LOG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    LOG_UNAME + " TEXT    NOT NULL ," +
                    LOG_DATE + " TEXT NOT NULL, " +
                    LOG_WEIGHT + " REAL NOT NULL, " +
                    LOG_BMI + " REAL NOT NULL );";

    public static final String DROP_USER_WEIGHT_TABLE =
            "DROP TABLE IF EXISTS " + USERWEIGHT_TABLE;

    public static final String TRIM_USER_WEIGHT_TABLE =
            "DELETE from " + USERWEIGHT_TABLE;


    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name,
                        CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // create tables
            db.execSQL(CREATE_LOG_TABLE);

            // insert default lists
//            db.execSQL("INSERT INTO list VALUES (1, 'Personal')");
//            db.execSQL("INSERT INTO list VALUES (2, 'Business')");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db,
                              int oldVersion, int newVersion) {
            Log.d("Task list", "Upgrading db from version "
                    + oldVersion + " to " + newVersion);
            db.execSQL(UserWeightDB.DROP_USER_WEIGHT_TABLE);
            onCreate(db);
        }
    }

    // database and database helper objects
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    // constructor
    public UserWeightDB(Context context) {
        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
    }

    // private methods
    private void openReadableDB() {
        db = dbHelper.getReadableDatabase();
    }

    private void openWriteableDB() {
        db = dbHelper.getWritableDatabase();
    }

    private void closeDB() {
        if (db != null)
            db.close();
    }

    public void TrimDB(){
        this.openWriteableDB();
        db.execSQL(TRIM_USER_WEIGHT_TABLE);
        this.closeDB();
    }


    // public methods
    public ArrayList<UserWeightLog> getUserWeightLog() {
        ArrayList<UserWeightLog> UWL_lists = new ArrayList<UserWeightLog>();
        openReadableDB();
        Cursor cursor = db.query(USERWEIGHT_TABLE,
                null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            UserWeightLog UWL = new UserWeightLog();
            UWL.setUsername(cursor.getString(LOG_UNAME_COL));
            UWL.setDate(cursor.getString(LOG_DATE_COL));
            UWL.setWeight(cursor.getDouble(LOG_WEIGHT_COL1));
            UWL.setBmi(cursor.getDouble(LOG_BMI_COL));

            UWL_lists.add(UWL);
        }
        if (cursor != null)
            cursor.close();
        closeDB();

        return UWL_lists;
    }



//    private static UserWeightLog getLogFromCursor(Cursor cursor) {
//        if (cursor == null || cursor.getCount() == 0) {
//            return null;
//        } else {
//            try {
//                Task task = new Task(
//                        cursor.getInt(TASK_ID_COL),
//                        cursor.getInt(TASK_LIST_ID_COL),
//                        cursor.getString(TASK_NAME_COL),
//                        cursor.getString(TASK_NOTES_COL),
//                        cursor.getString(TASK_COMPLETED_COL),
//                        cursor.getString(TASK_HIDDEN_COL));
//                return task;
//            } catch (Exception e) {
//                return null;
//            }
//        }
//    }

//    public static final String LOG_UNAME = "username";
//    public static final int LOG_UNAME_COL = 1;
//
//    public static final String LOG_DATE = "date";
//    public static final int LOG_DATE_COL = 2;
//
//    public static final String LOG_WEIGHT = "weight";
//    public static final int LOG_WEIGHT_COL1 = 3;
//
//    public static final String LOG_BMI = "bmi";
//    public static final int LOG_BMI_COL = 4;

    public long insertUserWeightLog(UserWeightLog uwl) {
        ContentValues cv = new ContentValues();
        cv.put(LOG_UNAME, uwl.getUsername());
        cv.put(LOG_DATE, uwl.getDate());
        cv.put(LOG_WEIGHT, uwl.getWeight());
        cv.put(LOG_BMI, uwl.getBmi());

        this.openWriteableDB();
        long rowID = db.insert(USERWEIGHT_TABLE, null, cv);
        this.closeDB();

        return rowID;
    }

//    public int updateTask(Task task) {
//        ContentValues cv = new ContentValues();
//        cv.put(TASK_LIST_ID, task.getListId());
//        cv.put(TASK_NAME, task.getName());
//        cv.put(TASK_NOTES, task.getNotes());
//        cv.put(TASK_COMPLETED, task.getCompletedDate());
//        cv.put(TASK_HIDDEN, task.getHidden());
//
//        String where = TASK_ID + "= ?";
//        String[] whereArgs = {String.valueOf(task.getId())};
//
//        this.openWriteableDB();
//        int rowCount = db.update(TASK_TABLE, cv, where, whereArgs);
//        this.closeDB();
//
//        return rowCount;
//    }
//
//    public int deleteTask(long id) {
//        String where = TASK_ID + "= ?";
//        String[] whereArgs = {String.valueOf(id)};
//
//        this.openWriteableDB();
//        int rowCount = db.delete(TASK_TABLE, where, whereArgs);
//        this.closeDB();
//
//        return rowCount;
//    }

}
