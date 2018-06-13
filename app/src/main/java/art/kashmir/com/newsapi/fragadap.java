package art.kashmir.com.newsapi;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by HP on 01-02-2018.
 */

public class fragadap extends RecyclerView.Adapter<fragadap.myclass> {
    private List<mm> list;
    private Context context;

    public fragadap(List<mm> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public fragadap.myclass onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.crd,parent,false);
        return new myclass(v);
    }

    @Override
    public void onBindViewHolder(fragadap.myclass holder, int position) {
final mm listitem=list.get(position);
holder.head.setText(listitem.getHeading());
holder.sub.setText(listitem.getDescription());
holder.read.setOnClickListener(new View.OnClickListener() {
    @Override
   public void onClick(View v) {
//        Intent intent=new Intent(Intent.ACTION_VIEW,
//                Uri.parse(listitem.getUrl()));
//            v.getContext().startActivity(intent);
        Intent intent=new Intent(context,webview.class);
       intent.putExtra("web",listitem.getUrl());
        v.getContext().startActivity(intent);
    }
});
holder.share.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT,listitem.getDescription()+"Download Tribune app made by junaidshah to know whats trending");
        intent.setType("text/plain");
        v.getContext().startActivity(intent);
    }
});
Glide.with(context).load(listitem.getImgurl()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class myclass extends RecyclerView.ViewHolder {
        private TextView head,sub,read;
        ImageView img;
        ImageButton share;
        public myclass(View itemView) {
            super(itemView);
            head=itemView.findViewById(R.id.name);
            sub=itemView.findViewById(R.id.message);
            img=itemView.findViewById(R.id.image);
            read=itemView.findViewById(R.id.readmore);
            share=itemView.findViewById(R.id.share);
        }

    }
}
