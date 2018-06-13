package art.kashmir.com.newsapi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by HP on 03-02-2018.
 */

public class sql {
    public static String Key_id="_id";
    public static String name="first name";
    public static String age="age";
    private static String database_name="mdb";
    private static int database_version=1;
    private static String database_table="users";
    private db ourdb;
    private final Context mcontext;
    private SQLiteDatabase mdb;
    private static class db extends SQLiteOpenHelper implements BaseColumns {
        public db(Context context) {
            super(context, database_name, null, database_version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE "+database_table+
            "("+_ID+"PRIMARY KEY,"+
                    name +"TEXT NOT NULL, "+
                    age +"TEXT NOT NULL" +");"
            );

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS"+database_table);
            onCreate(db);
        }
    }
public sql(Context context){
        mcontext=context;
}
public sql open() throws SQLException{
   ourdb=new db(mcontext);
   mdb=ourdb.getWritableDatabase();
   return this;
}
public void close(){
 ourdb.close();
}
public void enter(String a,String b){
    ContentValues v=new ContentValues();
    v.put(name,a);
    v.put(age,b);
    mdb.insert(database_table,null,v);
}
    public String getdata() {
    SQLiteDatabase dt=ourdb.getReadableDatabase();
        String[] d = new String[]{name,age};
       Cursor c=dt.query(database_table,d,null,null,null,null,null);
        String result = "";
        int row = c.getColumnIndex(name);
        int rk = c.getColumnIndex(age);
        while (c.moveToNext()) {
            result = result + c.getString(row) + " "
                    + c.getString(rk)
                    + "\n";
            c.moveToNext();
        }
        c.close();
        return result;


    }
}
