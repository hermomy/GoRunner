package lhy.gorunner;

/**
 * Created by HAUYANG on 14/03/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LoginDataBaseAdapter
{
    static final String DATABASE_NAME = "database.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    //static final String DATABASE_CREATE =


    // Variable to hold the database instance
    public  SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private DataBaseHelper dbHelper;
    public  LoginDataBaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    public  LoginDataBaseAdapter open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        db.close();
    }

    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    public boolean Login(String email, String password) throws SQLException
    {
        Cursor mCursor = db.rawQuery("SELECT * FROM user WHERE email=? AND password=?", new String[]{email,password});
        if (mCursor != null) {
            if(mCursor.getCount() > 0)
            {
                return true;
            }
        }
        return false;
    }


    public void createAccount(String username,String password,String email){

        ContentValues newValues = new ContentValues();
        newValues.put("user_name",username);
        newValues.put("password",password);
        newValues.put("email",email);
        newValues.put("phone","");
        newValues.put("location","");
        newValues.put("picture","user_pic");
        newValues.put("DOR", getDateTime());

        db.insert("User", null, newValues);
        Toast.makeText(context, "Your account has been created successfully", Toast.LENGTH_LONG).show();

    }

    public void insertNewTask(String title,String details,String address,String price,String user_id){

       ContentValues newValues = new ContentValues();
        newValues.put("taskname",title);
        newValues.put("taskdesc",details);
        newValues.put("price",price);
        newValues.put("date",getDateTime());
        newValues.put("location",address);
        newValues.put("status","OPEN");
        newValues.put("category","Home");
        newValues.put("user_id", user_id);

        db.insert("Task", null, newValues);
        //Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();

    }

    public void insertNewOffer(String user_id,String task_user_id,String comment){

        ContentValues newValues = new ContentValues();
        newValues.put("user_id",user_id);
        newValues.put("task_user_id",task_user_id);
        newValues.put("offer_date",getDateTime());
        newValues.put("offer_comment",comment);


        db.insert("Offer", null, newValues);
        Toast.makeText(context, "Offer inserted to database", Toast.LENGTH_LONG).show();

    }
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }



    public String[] getRowItems(String task_id){
    int n;
    String[] item = new String[6];
    Cursor cursor = db.rawQuery("SELECT * FROM Task WHERE task_id=" + task_id, null);
        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            item[0] = cursor.getString(cursor.getColumnIndex("taskname"));
            item[1] = cursor.getString(cursor.getColumnIndex("taskdesc"));
            item[2] = cursor.getString(cursor.getColumnIndex("price"));
            item[3] = cursor.getString(cursor.getColumnIndex("status"));
            item[4] = cursor.getString(cursor.getColumnIndex("user_id"));
            item[5] = cursor.getString(cursor.getColumnIndex("date"));
        }

    return item;
    }
    public String[] getRowItemsbyTitle(String taskname){
        int n;
        String[] item = new String[6];
        Cursor cursor = db.rawQuery("SELECT * FROM Task WHERE taskname= '" + taskname + "'", null);
        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            item[0] = cursor.getString(cursor.getColumnIndex("taskdesc"));
            item[1] = cursor.getString(cursor.getColumnIndex("price"));
            item[2] = cursor.getString(cursor.getColumnIndex("user_id"));
            item[3] = cursor.getString(cursor.getColumnIndex("date"));
        }

        return item;
    }



    public String getUserPic(String user_id){

        String item= null;
        Cursor cursor = db.rawQuery("SELECT * FROM User WHERE user_id=" + user_id, null);
        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            item = cursor.getString(cursor.getColumnIndex("picture"));

        }

        return item;
    }

    public String getUserID(String email){

        String item= null;
        Cursor cursor = db.rawQuery("SELECT * FROM User WHERE email='" + email + "'", null);
        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            item = cursor.getString(cursor.getColumnIndex("user_id"));

        }

        return item;
    }
    public String[] getUserItems(String user_id){

        String[] item= new String[8];
        Cursor cursor = db.rawQuery("SELECT * FROM User WHERE user_id='" + user_id + "'", null);
        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            item[0] = cursor.getString(cursor.getColumnIndex("user_name"));
            item[1] = cursor.getString(cursor.getColumnIndex("picture"));
            item[2] = cursor.getString(cursor.getColumnIndex("email"));
            item[3] = cursor.getString(cursor.getColumnIndex("location"));
            item[4] = cursor.getString(cursor.getColumnIndex("expertise"));
            item[5] = cursor.getString(cursor.getColumnIndex("posted"));
            item[6] = cursor.getString(cursor.getColumnIndex("completed"));
            item[7] = cursor.getString(cursor.getColumnIndex("review"));

        }

        return item;
    }

    public String[][] getAllTask(){
        int n,j;
        int totalRow = getTotalRow("Task");
        //String[][] item = new String[totalRow][5];

        Cursor c = db.rawQuery("SELECT * FROM Task",null);
        int cPos = 0; // for cursor position
        String[][] array = new String[c.getCount()][8]; // Dynamic string array

        if (c.getCount() > 0) { // If cursor has atleast one row
            c.moveToFirst();
            do { // always prefer do while loop while you deal with database
                cPos = c.getPosition();
                array[cPos][0] = c.getString(c.getColumnIndex("taskname"));
                array[cPos][1] = c.getString(c.getColumnIndex("category"));
                array[cPos][2] = c.getString(c.getColumnIndex("date"));
                array[cPos][3] = c.getString(c.getColumnIndex("price"));
                array[cPos][4] = c.getString(c.getColumnIndex("task_id")); //get task_id of each task
                array[cPos][5] = c.getString(c.getColumnIndex("location"));
                array[cPos][6] = c.getString(c.getColumnIndex("user_id"));
                array[cPos][7] = c.getString(c.getColumnIndex("status"));
                c.moveToNext();
            } while (!c.isAfterLast());

        }
        else {
            Log.e("SQL Query Error", "Cursor has no data");
        }
        return array;
    }

    public int getTotalRow(String tablename){
        Cursor mCount= db.rawQuery("select count(*) from " + tablename, null);
        mCount.moveToFirst();
        int count= mCount.getInt(0);
        mCount.close();

        return count;
    }
}