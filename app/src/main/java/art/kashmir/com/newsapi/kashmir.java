package art.kashmir.com.newsapi;



import android.app.Fragment;
import android.os.Bundle;


import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.baoyz.widget.PullRefreshLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class kashmir extends Fragment {
    @BindView(R.id.recy) RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<mm> listitems;
    @BindView(R.id.pull) PullRefreshLayout pullRefreshLayout;
    @BindView(R.id.rel) RelativeLayout rel;
    @BindView(R.id.abc) RelativeLayout ba;
    public kashmir() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View v= inflater.inflate(R.layout.fragment_kashmir, container, false);
        ButterKnife.bind(this,v);
      recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
      recyclerView.setHasFixedSize(true);
      listitems=new ArrayList<>();
      if(savedInstanceState == null){getnews();}
      if(savedInstanceState != null){
          rel.setVisibility(View.GONE);
          listitems=savedInstanceState.getParcelableArrayList("listkashmir");
          adapter=new fragadap(listitems,getActivity().getApplicationContext());
          recyclerView.setAdapter(adapter);


      }
      recyclerView.setNestedScrollingEnabled(false);
      pullRefreshLayout.setRefreshing(false);
      pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
          @Override
          public void onRefresh() {
              getnews();
          }
      });
      return v;

    }


    public void getnews(){


        String url = "http://newsapi.org/v2/top-headlines?country=in&apiKey=3082c71abc2e474885f1b3580001dbee";
        StringRequest stringRequest=new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pullRefreshLayout.setRefreshing(false);

                try {
                    rel.setVisibility(View.GONE);
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
                        listitems.add(m);
                    }
                    if(listitems.size()!=0){
                        try {
                            adapter=new fragadap(listitems,getActivity().getApplicationContext());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        recyclerView.setAdapter(adapter);
                    }



                } catch (JSONException e) {
                    rel.setVisibility(View.VISIBLE);
                    e.printStackTrace();
//                    Toast.makeText(getActivity(), "something went wrong"+e.getMessage(), Toast.LENGTH_SHORT).show();
                  Snackbar.make(ba,"something went wrong",Snackbar.LENGTH_LONG).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                rel.setVisibility(View.GONE);
                pullRefreshLayout.setRefreshing(false);
                if(error instanceof NetworkError ){
//                    Toast.makeText(getActivity(), "no internet connection"+ error.getMessage(), Toast.LENGTH_SHORT).show();
                    Snackbar.make(ba,"no internet connection",Snackbar.LENGTH_LONG)
                            .setAction("Try again", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    pullRefreshLayout.setRefreshing(true);
                                    getnews();
                                }
                            })
                            .show();

                }
                if(error instanceof TimeoutError){
                    Snackbar.make(ba,"no internet connection",Snackbar.LENGTH_LONG)
                    .setAction("Try again", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            pullRefreshLayout.setRefreshing(true);
                            getnews();
                        }
                    })
                    .show();

                }
            }
        });
        singletone.getInstance(getActivity()).addToRequestQueue(stringRequest);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("listkashmir", (ArrayList<? extends Parcelable>) listitems);
        super.onSaveInstanceState(outState);


    }
}
