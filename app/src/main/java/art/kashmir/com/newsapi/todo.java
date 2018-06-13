package art.kashmir.com.newsapi;

import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import static art.kashmir.com.newsapi.mydb.table_name;
public class todo extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{
public mydb mydb;
Cursor query;
RecyclerView recyclerView;
RecyclerView.Adapter adapter;
public List<model>listitems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView=findViewById(R.id.noterecyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listitems=new ArrayList<>();
        getLoaderManager().initLoader(0,null,this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.abc, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:

        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        android.content.CursorLoader loader=null;
        if(id==0){
            loader=getresult();
        }
        return loader;
    }

    private  android.content.CursorLoader getresult() {
        return new android.content.CursorLoader(this) {
            @Override
            public Cursor loadInBackground() {
                SQLiteDatabase db;
                db = mydb.getReadableDatabase();
                //Cursor query = db.query(table_name, new String[]{"name", "des"}, null, null, null, null, null);
                return db.rawQuery("SELECT * FROM " + table_name, null);
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
if(loader.getId()==0){
    loadnotefinished(data);
}
    }

    private void loadnotefinished(Cursor data) {
        query=data;
        int a=query.getColumnIndex("ID");
        int b=query.getColumnIndex("des");
        int c=query.getColumnIndex("name");
        while(query.moveToNext()){
            String id=query.getString(a);
            String name=query.getString(b);
            String desc=query.getString(c);
            model m=new model(
                    id,name,desc
            );
            listitems.add(m);
        }

        adapter=new noteadap(listitems,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        if(loader.getId()==0){
            if(query!=null){
                query.close();
            }
        }
    }
}
