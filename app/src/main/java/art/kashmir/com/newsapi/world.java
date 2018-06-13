package art.kashmir.com.newsapi;



import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;


import android.os.Parcelable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import static art.kashmir.com.newsapi.mydb.table_name;


/**
 * A simple {@link Fragment} subclass.
 */
public class world extends Fragment implements android.app.LoaderManager.LoaderCallbacks<Cursor>{
TextView result;
    mydb mydb;
    Cursor query;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    private List<model> listitems;
    public world() {
        // Required empty public constructor
    }
 @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View v= inflater.inflate(R.layout.fragment_world, container, false);
      recyclerView=v.findViewById(R.id.rr);
      mydb=new mydb(getActivity());
      recyclerView.setHasFixedSize(true);
      recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
      getLoaderManager().initLoader(0,null,this);
     listitems=new ArrayList<>();
     if(savedInstanceState !=null){
         listitems=savedInstanceState.getParcelableArrayList("list");
         adapter=new noteadap(listitems,getActivity().getApplicationContext());
         recyclerView.setAdapter(adapter);
     }
    return v;
    }

    @Override
    public android.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {
        android.content.CursorLoader loader=null;
        if(id==0){
            loader=createnotes();
        }
        return loader;
    }

    private android.content.CursorLoader createnotes() {
        return new android.content.CursorLoader(getActivity()){
            @Override
            public Cursor loadInBackground() {
                SQLiteDatabase db;
                db = mydb.getReadableDatabase();
                //Cursor query = db.query(table_name, new String[]{"name", "des"}, null, null, null, null, null);
                return db.rawQuery("SELECT * FROM "+table_name,null);
            }
        };
    }

    @Override
    public void onLoadFinished(android.content.Loader<Cursor> loader, Cursor data) {
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
                    id,desc,name
            );
            listitems.add(m);
        }

        adapter=new noteadap(listitems,getActivity().getApplicationContext());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(android.content.Loader<Cursor> loader) {
        if(loader.getId()==0){
            if(query!=null){
                query.close();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) listitems);
        super.onSaveInstanceState(outState);
    }
}

