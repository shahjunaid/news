package art.kashmir.com.newsapi;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by HP on 01-02-2018.
 */

public class noteadap extends RecyclerView.Adapter<noteadap.myclass> {
    private List<model> list;
    private Context context;

    public noteadap(List<model> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public noteadap.myclass onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlarge,parent,false);
        return new myclass(v);
    }

    @Override
    public void onBindViewHolder(final noteadap.myclass holder, int position) {
final model listitem=list.get(position);
holder.head.setText(listitem.getDescription());
holder.sub.setText(listitem.getImgurl());
holder.img.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT,listitem.getDescription()+"\n"+"Download Tribune app made by junaidshah to know whats trending");
        intent.setType("text/plain");
        v.getContext().startActivity(intent);
    }
});
holder.r.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Pair[] pairs=new Pair[2];
        pairs[0]=new Pair<View,String>(holder.head,"name");
        pairs[1]=new Pair<View,String>(holder.sub,"topic");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation((Activity) v.getContext(),pairs);
            Intent intent=new Intent(v.getContext(),Main3Activity.class);
            intent.putExtra("id",listitem.getHeading());
            intent.putExtra("name",listitem.getDescription());
            intent.putExtra("age",listitem.getImgurl());
            v.getContext().startActivity(intent,options.toBundle());
        }
        else{
            Intent intent=new Intent(v.getContext(),Main3Activity.class);
            intent.putExtra("id",listitem.getHeading());
            intent.putExtra("name",listitem.getDescription());
            intent.putExtra("age",listitem.getImgurl());
            v.getContext().startActivity(intent);
        }


    }
});
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class myclass extends RecyclerView.ViewHolder {
        private TextView head,sub;
        ImageView img;
        RelativeLayout r;
        public myclass(View itemView) {
            super(itemView);
            head=itemView.findViewById(R.id.heading);
            sub=itemView.findViewById(R.id.subtitle);
            img=itemView.findViewById(R.id.share);
            r=itemView.findViewById(R.id.rel);
        }

    }
}
