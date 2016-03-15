package lhy.gorunner;

/**
 * Created by HAUYANG on 14/03/2016.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.gcm.Task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class DataBaseHelper extends SQLiteOpenHelper{

    private static final String CREATE_TABLE_Payment = "CREATE TABLE Payment (" +
            "payment_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "amount INTEGER NOT NULL," +
            "condition INTEGER NOT NULL " +")";

    private static final String CREATE_TABLE_Task = "CREATE TABLE  Task  (" +
            " task_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            " taskname  TEXT NOT NULL," +
            " taskdesc TEXT," +
            " price INTEGER," +
            " date TEXT," +
            " status TEXT," +
            " user_id INTEGER NOT NULL," +
            "FOREIGN KEY( user_id ) REFERENCES user_id" +
            ")";

    private static final String CREATE_TABLE_User = "CREATE TABLE  User  (" +
            " user_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            " use_name TEXT NOT NULL," +
            " password INTEGER NOT NULL," +
            " email TEXT," +
            " phone TEXT," +
            " location TEXT" +
            ")";


    public DataBaseHelper(Context context, String name,CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }
    // Called when no database exists in disk and the helper class needs
    // to create a new one.
    @Override
    public void onCreate(SQLiteDatabase _db)
    {
       // _db.execSQL(LoginDataBaseAdapter.DATABASE_CREATE);
        _db.execSQL(CREATE_TABLE_Payment);
        _db.execSQL(CREATE_TABLE_Task);
        _db.execSQL(CREATE_TABLE_User);
    //    copyDBToSDCard();
    }


    // Called when there is a database version mismatch meaning that the version
    // of the database on disk needs to be upgraded to the current version.
    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion)
    {
        // Log the version upgrade.
        Log.w("TaskDBAdapter", "Upgrading from version " +_oldVersion + " to " +_newVersion + ", which will destroy all old data");

        // Upgrade the existing database to conform to the new version. Multiple
        // previous versions can be handled by comparing _oldVersion and _newVersion
        // values.
        // The simplest case is to drop the old table and create a new one.
        _db.execSQL("DROP TABLE IF EXISTS " + "TEMPLATE");
        // Create a new one.
        onCreate(_db);
    }
//    public void copyDBToSDCard() {
//        try {
//            File sd = Environment.getExternalStorageDirectory();
//            File data = Environment.getDataDirectory();
//
//            if (sd.canWrite()) {
//                String currentDBPath = "//data//lhy.gounner//databases//database.db";
//                String backupDBPath = "backupname.db";
//                File currentDB = new File(data, currentDBPath);
//                File backupDB = new File(sd, backupDBPath);
//
//                if (currentDB.exists()) {
//                    FileChannel src = new FileInputStream(currentDB).getChannel();
//                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
//                    dst.transferFrom(src, 0, src.size());
//                    src.close();
//                    dst.close();
//                }
//
//            }
//
//        }  catch (Exception e) {
//
//            Log.i("FO","exception="+e);
//        }
//
//
//    }

}