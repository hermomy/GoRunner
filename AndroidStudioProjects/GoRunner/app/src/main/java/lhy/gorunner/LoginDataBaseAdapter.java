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

    public void insertEntry(String userName,String password)
    {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("USERNAME", userName);
        newValues.put("PASSWORD", password);

        // Insert the row into your table
        db.insert("Task", null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }

    public void insertNewTask(String title,String details,String address,String price){

       ContentValues newValues = new ContentValues();
        newValues.put("taskname",title);
        newValues.put("taskdesc",details);
        newValues.put("price",price);
        newValues.put("date",getDateTime());
        newValues.put("location",address);
        newValues.put("status","OPEN");
        newValues.put("category","Home");
        newValues.put("user_id", "1");

        db.insert("Task",null,newValues);
        Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
//        String sql = "INSERT INTO Task(taskname) VALUES (?)";
//        db.execSQL(sql, new String[]{title});
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    public int deleteEntry(String UserName)
    {
        //String id=String.valueOf(ID);
        String where="USERNAME=?";
        int numberOFEntriesDeleted= db.delete("Task", where, new String[]{UserName}) ;
        // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }

    public String getSinlgeEntry(String userName)
    {
        Cursor cursor=db.query("Task", null, " USERNAME=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }


    public String[] getRowItems(String task_id){
    int n;
    String[] item = new String[4];
    Cursor cursor = db.rawQuery("SELECT * FROM Task WHERE task_id=" + task_id, null);
        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            item[0] = cursor.getString(cursor.getColumnIndex("taskname"));
            item[1] = cursor.getString(cursor.getColumnIndex("taskdesc"));
            item[2] = cursor.getString(cursor.getColumnIndex("price"));
            item[3] = cursor.getString(cursor.getColumnIndex("status"));
        }

    return item;
    }


    public String[][] getAllTask(){
        int n,j;
        int totalRow = getTotalRow("Task");
        //String[][] item = new String[totalRow][5];

        Cursor c = db.rawQuery("SELECT * FROM Task",null);
        int cPos = 0; // for cursor position
        String[][] array = new String[c.getCount()][5]; // Dynamic string array

        if (c.getCount() > 0) { // If cursor has atleast one row
            c.moveToFirst();
            do { // always prefer do while loop while you deal with database
                cPos = c.getPosition();
                array[cPos][0] = c.getString(c.getColumnIndex("taskname"));
                array[cPos][1] = c.getString(c.getColumnIndex("category"));
                array[cPos][2] = c.getString(c.getColumnIndex("date"));
                array[cPos][3] = c.getString(c.getColumnIndex("price"));
                array[cPos][4] = c.getString(c.getColumnIndex("task_id")); //get task_id of each task
                c.moveToNext();
            } while (!c.isAfterLast());

        }
        else {
            Log.e("SQL Query Error", "Cursor has no data");
        }
        return array;
    }

    public void  updateEntry(String userName,String password)
    {
        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
        updatedValues.put("USERNAME", userName);
        updatedValues.put("PASSWORD",password);

        String where="USERNAME = ?";
        db.update("Task",updatedValues, where, new String[]{userName});
    }

    public int getTotalRow(String tablename){
        Cursor mCount= db.rawQuery("select count(*) from " + tablename, null);
        mCount.moveToFirst();
        int count= mCount.getInt(0);
        mCount.close();

        return count;
    }
}