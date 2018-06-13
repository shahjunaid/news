package art.kashmir.com.newsapi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class news extends Fragment {
    String url="https://newsapi.org/v2/top-headlines?sources=new-scientist&apiKey=7ccdea23d7dc4c07a44d732338ddc117";
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    private List<model> list;

    public news() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v= inflater.inflate(R.layout.fragment_news, container, false);
    recyclerView=v.findViewById(R.id.recycler);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Toast.makeText(getActivity(), "inside oncreate of news", Toast.LENGTH_SHORT).show();
    recyclerView.setHasFixedSize(true);
        list=new ArrayList<>();
        getnew();
       return v;
    }
    public void getnew()
    {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Toast.makeText(getActivity(), "inside try of news", Toast.LENGTH_SHORT).show();
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("articles");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject o = jsonArray.getJSONObject(i);
                        String title = o.getString("title");
                        String desc = o.getString("information");
                        String imgurl = o.getString("url");
                        model m = new model(
                                title,
                                desc,
                                imgurl
                        );
                        list.add(m);
                    }
                    adapter=new fragadapone(list,getActivity().getApplicationContext());
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "inside error", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error instanceof NetworkError){
                    Toast.makeText(getActivity(), "no connection", Toast.LENGTH_SHORT).show();
                }
                if(error instanceof NoConnectionError){
                    Toast.makeText(getActivity(), "internet not avavlable", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getActivity(), "inside volley error", Toast.LENGTH_SHORT).show();
            }
        });
        singletone.getInstance(getActivity()).addToRequestQueue(stringRequest);
    }

}
