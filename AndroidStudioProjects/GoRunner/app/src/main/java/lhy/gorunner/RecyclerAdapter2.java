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

    LoginDataBaseAdapter loginDataBaseAdapter;

    String[][]  array;

    String[] name ;
    int[] picture = {R.drawable.user_pic,R.drawable.user3_pic,R.drawable.user2_pic,R.drawable.user_pic,R.drawable.user_pic,R.drawable.user_pic,};
    String[] pay ;
    String[] time = {"1 hours ago","2 hours ago","3 hours ago","5 hours ago","5 hours ago","6 hours ago"};
    String[] location = {"Perak","Selangor","Johor","Kajang","Puchong","Selangor"};
    String[] comment = {"2 Comments, 0 Offers","0 Comments, 1 Offers","2 Comments, 0 Offers","2 Comments, 0 Offers","2 Comments, 0 Offers","2 Comments, 0 Offers"};
    Context context;
    LayoutInflater inflater;

    public RecyclerAdapter2(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        loginDataBaseAdapter=new LoginDataBaseAdapter(context);

        loginDataBaseAdapter=loginDataBaseAdapter.open();

    }

    @Override
    public RecyclerViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_list_browse, parent, false);

        RecyclerViewHolder2 viewHolder = new RecyclerViewHolder2(v);

        int totalRow = loginDataBaseAdapter.getTotalRow("Task");
        array = loginDataBaseAdapter.getAllTask();
        name = new String[totalRow];
        pay = new String[totalRow];

        for(int n=0;n<totalRow;n++) {
            name[n] = array[n][0];
            pay[n] = array[n][3];
        }
        // Toast.makeText(context, "array number = " + loginDataBaseAdapter.getTotalRow("Task"), Toast.LENGTH_LONG).show();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder2 holder, int position) {

        holder.tv1.setText(name[position]);
        holder.tv2.setText(pay[position]);
        holder.tv3.setText(time[position]);
        holder.tv4.setText(location[position]);

        holder.tv5.setText(comment[position]);
        holder.imageView.setImageResource(picture[position]);
        holder.tv1.setOnClickListener(clickListener);
        holder.tv1.setTag(holder);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            RecyclerViewHolder2 vholder = (RecyclerViewHolder2) v.getTag();
            int position = vholder.getPosition();

            String temp = array[position][4];
            Intent i = new Intent(context,TaskDetailActivity.class);
            i.putExtra("task_id",temp);
            context.startActivity(i);

//            if(position==0){
//                Intent i1 = new Intent(context, TaskDetailActivity.class);
//                context.startActivity(i1);
//            }
//
//            if(position ==1){
//                Intent i1 = new Intent(context, TaskDetailActivity2.class);
//                context.startActivity(i1);
//            }



        }
    };

    @Override
    public int getItemCount() {

        return location.length;
    }
}