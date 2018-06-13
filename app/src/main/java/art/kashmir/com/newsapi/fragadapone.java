package art.kashmir.com.newsapi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by HP on 01-02-2018.
 */

public class fragadapone extends RecyclerView.Adapter<fragadapone.myclass> {
    private List<model> list;
    private Context context;

    public fragadapone(List<model> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public fragadapone.myclass onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlarge,parent,false);
        return new myclass(v);
    }

    @Override
    public void onBindViewHolder(fragadapone.myclass holder, int position) {
final model listitem=list.get(position);
holder.head.setText(listitem.getHeading());
holder.sub.setText(listitem.getDescription());
holder.img.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT,listitem.getDescription()+"Download news app made by junaidshah to know whats trending");
        intent.setType("text/plain");
        v.getContext().startActivity(intent);
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
        public myclass(View itemView) {
            super(itemView);
            head=itemView.findViewById(R.id.heading);
            sub=itemView.findViewById(R.id.subtitle);
            img=itemView.findViewById(R.id.share);
        }

    }
}
