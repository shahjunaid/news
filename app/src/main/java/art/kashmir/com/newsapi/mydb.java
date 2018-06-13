package art.kashmir.com.newsapi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HP on 04-02-2018.
 */

public class mydb extends SQLiteOpenHelper {
    public static String db_name="jack";
    public static String table_name="notes";
    public static String col1="heading";
    public static String col2="des";
    public static String col3="name";

mydb mydb;
    public mydb(Context context) {
        super(context, db_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ table_name + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,des TEXT,name TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("DROP TABLE IF EXISTS"+table_name);
onCreate(db);
    }
    public boolean getdata(String name,String desc) throws SQLException{
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col2,name);
        contentValues.put(col3,desc);
        Long res=db.insert(table_name,null,contentValues);
        if(res==-1){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean update(String id,String name,String age) throws SQLException{
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col2,name);
        contentValues.put(col3,age);
        db.update(table_name,contentValues,"ID =?",new String[]{id});
        return true;
    }
    public boolean delete(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(table_name,"ID=?",new String[]{id});
       return true;
    }
}

