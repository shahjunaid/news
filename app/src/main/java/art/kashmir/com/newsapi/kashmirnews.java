package art.kashmir.com.newsapi;



import android.app.Dialog;
import android.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.baoyz.widget.PullRefreshLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class kashmirnews extends Fragment {
    @BindView(R.id.rel) RelativeLayout rel;
    @BindView(R.id.recy) RecyclerView recyclerView;
    @BindView(R.id.dd) RelativeLayout rr;
  RecyclerView.Adapter adapter;
  private List<mm>list;
  @BindView(R.id.pull) PullRefreshLayout pullRefreshLayout;
  public String url="http://newsapi.org/v2/top-headlines?sources=hacker-news&apiKey=3082c71abc2e474885f1b3580001dbee";
    public kashmirnews() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_kashmirnews, container, false);
        ButterKnife.bind(this,v);
       recyclerView.setHasFixedSize(true);
       rr=v.findViewById(R.id.dd);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
       list=new ArrayList<>();
       recyclerView.setNestedScrollingEnabled(false);
        if(savedInstanceState == null){
            getnews();
        }
        else{
            rel.setVisibility(View.GONE);
            list=savedInstanceState.getParcelableArrayList("list");
            adapter=new fragadap(list,getActivity().getApplicationContext());
            recyclerView.setAdapter(adapter);

        }
       pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
           @Override
           public void onRefresh() {
               getnews();
           }
       });

        return v;
    }

    public void getnews(){
        StringRequest stringRequest=new StringRequest(Request.Method.GET,
                "http://newsapi.org/v2/top-headlines?sources=hacker-news&apiKey=3082c71abc2e474885f1b3580001dbee", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pullRefreshLayout.setRefreshing(false);
                rel.setVisibility(View.GONE);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("articles");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject o = array.getJSONObject(i);
                        String title = o.getString("title");
                        String desc = o.getString("description");
                        String imgurl = o.getString("urlToImage");
                        String url=o.getString("url");
                        mm m = new mm(
                                title,
                                desc,
                                imgurl,
                                url
                        );
                        list.add(m);
                    }
                 if(list.size()!=0){
                     try {
                         adapter=new fragadap(list,getActivity().getApplicationContext());
                     } catch (Exception e) {
                         e.printStackTrace();

                     }
                     recyclerView.setAdapter(adapter);
                 }
                } catch (JSONException e) {
                    e.printStackTrace();


                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                rel.setVisibility(View.GONE);
                pullRefreshLayout.setRefreshing(false);
                if(error instanceof NetworkError){
                    Snackbar.make(rr,"Network error",Snackbar.LENGTH_LONG)
                            .setAction("Try again", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    pullRefreshLayout.setRefreshing(true);
                                    getnews();
                                }
                            }).show();
                }
                if(error instanceof TimeoutError){
                    Snackbar.make(rr,"Network error",Snackbar.LENGTH_LONG)
                            .setAction("Try again", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    pullRefreshLayout.setRefreshing(true);
                                    getnews();
                                }
                            }).show();
                }

            }
        });
        singletone.getInstance(getActivity()).addToRequestQueue(stringRequest);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) list);
        super.onSaveInstanceState(outState);
    }
}