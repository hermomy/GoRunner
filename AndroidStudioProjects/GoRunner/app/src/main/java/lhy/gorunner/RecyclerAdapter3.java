package lhy.gorunner;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by HAUYANG on 24/02/2016.
 */
public class RecyclerAdapter3 extends RecyclerView.Adapter<RecyclerViewHolder3> {


String user_id;
    String[] name = {"Graphic design work", "Social media promoting","150 Facebook Likes",};
    String[] time = {"2 hours ago","2 hours ago","2 hours ago","3 hours ago"};
    String[] button_name = {"VIEW OFFERS","RELEASE","REQUEST"};
    String[] location = {"Selangor","Kuala Lumpur","Ipoh","Perak"};
    //String[] comment = {"2 Comments, 3 Offers","3 Comments, 0 Offers","1 Comments, 1 Offers","5 Comments, 1 Offers","2 Comments, 0 Offers","0 Comments, 0 Offers"};
    Context context;
    LayoutInflater inflater;

    public RecyclerAdapter3(Context context,String user_id) {
        this.context = context;
        this.user_id = user_id;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolder3 onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_list_mytask, parent, false);

        RecyclerViewHolder3 viewHolder = new RecyclerViewHolder3(v);



        return viewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerViewHolder3 holder, int position) {

        holder.tv1.setText(name[position]);
        holder.tv2.setText(location[position]);
        holder.tv3.setText(time[position]);
        holder.btn1.setText(button_name[position]);
        holder.btn1.setOnClickListener(clickListener);
        holder.btn1.setTag(holder);
    }

    View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            RecyclerViewHolder3 vholder = (RecyclerViewHolder3) v.getTag();
            int position = vholder.getPosition();

            if(button_name[position].equals("VIEW OFFERS")) {
                Intent i1 = new Intent(context, CompareActivity.class);
                i1.putExtra("userID",user_id);
                context.startActivity(i1);
            }

            else if (button_name[position].equals("RELEASE")) {
                Intent i1 = new Intent(context, TaskDetailActivity2.class);
                i1.putExtra("task_id","2");
                i1.putExtra("value",name[position]);
                i1.putExtra("btn_name","RELEASE");
                i1.putExtra("userID",user_id);
                context.startActivity(i1);
            }

            else {
                Intent i1 = new Intent(context, TaskDetailActivity2.class);
                i1.putExtra("task_id","2");
                i1.putExtra("value",name[position]);
                i1.putExtra("btn_name","REQUEST");
                i1.putExtra("userID",user_id);
                context.startActivity(i1);
            }
        }
    };


    @Override
    public int getItemCount() {
        return name.length;
    }
}
