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

    private static final String CREATE_TABLE_Task = "CREATE TABLE  Task (" +
            " task_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            " taskname  TEXT NOT NULL," +
            " taskdesc TEXT," +
            " category TEXT," +
            " price INTEGER," +
            " date TEXT," +
            " location TEXT," +
            " status TEXT," +
            " user_id INTEGER NOT NULL," +
            "FOREIGN KEY( user_id ) REFERENCES user_id" +
            ")";

    private static final String CREATE_TABLE_Payment = "CREATE TABLE Payment (" +
            "payment_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "amount INTEGER NOT NULL," +
            "condition INTEGER NOT NULL " +")";

    private static final String CREATE_TABLE_User = "CREATE TABLE  User (" +
            " user_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            " user_name TEXT NOT NULL," +
            " password INTEGER NOT NULL," +
            " email TEXT," +
            " phone TEXT," +
            " location TEXT," +
            " picture TEXT," +
            " expertise TEXT," +
            " posted TEXT," +
            " completed TEXT," +
            " review TEXT," +
            " rating TEXT," +
            " DOR TEXT" +
            ")";

    private static final String CREATE_TABLE_Review = "CREATE TABLE  Review (" +
            " review_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            " content TEXT NOT NULL," +
            " score INTEGER NOT NULL," +
            " user_id INTEGER NOT NULL,"+
            " FOREIGN KEY( user_id ) REFERENCES user_id" +
            ")";

    private static final String CREATE_TABLE_Offer = "CREATE TABLE  Offer (" +
            " offer_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            " user_id INTEGER NOT NULL," +
            " task_user_id INTEGER NOT NULL," +
            " offer_date TEXT,"+
            " offer_comment TEXT" +
            ")";

    String INSERT_DATA_REVIEW = "INSERT INTO Review(content,score,user_id) VALUES ('This guy is a perfect guy.','2','1'),('I felt good working with him. He is a pretty nice guy.','7','1'),('A little bit happy cause he is awesome.','5','1')";

    String INSERT_DATA_USER = "INSERT INTO User(user_name,password,email,phone,location,picture,expertise,posted,completed,review,DOR) VALUES ('Hau Yang','8008','lhy@gmail.com','0167772348','Johor Bahru','hauyang','Mobile development passionate','4','5','2','22/10/2013')," +
            "('Min Hua','1234','meow@gmail.com','0167723512','Puchong','meow','Graphic designer','3','2','0','2/8/2014')," +
            "('Eric Tiew','1234','khcy3eric@nottingham.edu.my','0167743533','Cheras','eric','PHP expert','3','5','2','12/5/2013')," +
            "('Wei Jie','1234','khcy3wjie@nottingham.edu.my','0161123512','Batu Pahat','weijie','Cleaner','1','2','2','25/1/2013')," +
            "('Ann Pei','8078','khcy3annpei@nottingham.edu.my','0117723512','Batu Pahat','annpei','Educator','2','6','2','25/12/2012')," +
            "('Wei Sian','8078','khcy3wsian@nottingham.edu.my','0132523512','Semenyih','weisian','Graphic designer','5','4','2','2/10/2012');";

    String INSERT_DATA_PAYMENT = "INSERT INTO Payment(amount, condition) VALUES ('500','0'),('200','0'),('350',0),('400','0')";

    String INSERT_DATA_TASK = "INSERT INTO Task(taskname,taskdesc,category,price,date,location,status,user_id) VALUES ('Graphic design work','I need someone to help me with a company logo finish off business cards that had wrong details and also design a number of marketing stuff for me and email signatures as well as letter heads','Home','200','2016-03-17','Selangor','OPEN','6')," +
            "('Social media promoting','Hi, we need a lady with more than 800 friends on Facebook to help us promote our private sale (Handmade, Limited Edition Jewellery).We will give you a PRIVATE discount code of 35% Discount. This counpon code is valid for 1 week.','Home','150','2016-03-18','Cheras','ASSIGN','2')," +
            "('Data Entry','I need someone to help me with a company logo finish off business cards that had wrong details and also design a number of marketing stuff for me and email signatures as well as letter heads','Fitness','200','2016-03-18','Puchong','OPEN','3')," +
            "('Mcdonalds delivery','I need someone to help me with a company logo finish off business cards that had wrong details and also design a number of marketing stuff for me and email signatures as well as letter heads','Fitness','200','2016-03-18','Selangor','OPEN','4')," +
            "('150 Facebook Likes','I need someone to help me with a company logo finish off business cards that had wrong details and also design a number of marketing stuff for me and email signatures as well as letter heads','Photography','300','2016-03-18','Johor','OPEN','3')," +
            "('Write a resume','Hi, we need a lady with more than 800 friends on Facebook to help us promote our private sale (Handmade, Limited Edition Jewellery).We will give you a PRIVATE discount code of 35% Discount. This counpon code is valid for 1 week.','Home','150','2016-03-18','Johor','ASSIGN','5');";

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
        _db.execSQL(CREATE_TABLE_Offer);
        _db.execSQL(CREATE_TABLE_Review);
        _db.execSQL(INSERT_DATA_USER);
        _db.execSQL(INSERT_DATA_TASK);
        _db.execSQL(INSERT_DATA_PAYMENT);
        _db.execSQL(INSERT_DATA_REVIEW);
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


}