package lhy.gorunner;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by HAUYANG on 18/02/2016.
 */
public class RecyclerAdapter2 extends RecyclerView.Adapter<RecyclerViewHolder2>{

    String[] name = {"Graphic design work", "Social media promoting","Data Entry","Mcdonald's delivery","150 Facebook Likes","Write a resume"};
    int[] picture = {R.drawable.user_pic,R.drawable.user3_pic,R.drawable.user2_pic,R.drawable.user_pic,R.drawable.user_pic,R.drawable.user_pic,};
    String[] pay = {"RM100","RM50","RM300","RM250","RM450","RM150"};
    String[] time = {"2 hours ago","2 hours ago","2 hours ago","3 hours ago","4 hours ago","4 hours ago"};
    Context context;
    LayoutInflater inflater;

    public RecyclerAdapter2(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_list_browse, parent, false);

        RecyclerViewHolder2 viewHolder = new RecyclerViewHolder2(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder2 holder, int position) {

        holder.tv1.setText(name[position]);
        holder.tv2.setText(pay[position]);
        holder.tv3.setText(time[position]);
        holder.imageView.setImageResource(picture[position]);
        holder.tv1.setOnClickListener(clickListener);
        holder.tv1.setTag(holder);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            RecyclerViewHolder2 vholder = (RecyclerViewHolder2) v.getTag();
            int position = vholder.getPosition();

            if(position==0){
                Intent i1 = new Intent(context, TaskDetailActivity.class);
                context.startActivity(i1);
            }





        }
    };

    @Override
    public int getItemCount() {

        return name.length;
    }
}