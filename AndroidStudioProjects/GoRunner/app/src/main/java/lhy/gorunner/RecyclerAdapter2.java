package lhy.gorunner;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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
    String[] picture;
    String[] pay ;
    String[] time ;
    String[] location;
    String[] comment ;
    String[] status;
    Context context;
    LayoutInflater inflater;
    int totalRow;
    String []getPic;
    String user_id;
    String[] temp;
    public RecyclerAdapter2(Context context,String user_id) {
        this.context = context;
        this.user_id = user_id;
        inflater = LayoutInflater.from(context);
        loginDataBaseAdapter=new LoginDataBaseAdapter(context);

        loginDataBaseAdapter=loginDataBaseAdapter.open();
        totalRow = loginDataBaseAdapter.getTotalRow("Task");
    }

    @Override
    public RecyclerViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_list_browse, parent, false);

        RecyclerViewHolder2 viewHolder = new RecyclerViewHolder2(v);

        int resId;
        array = loginDataBaseAdapter.getAllTask();

        name = new String[totalRow];
        pay = new String[totalRow];
        time = new String[totalRow];
        location = new String[totalRow];
        comment = new String[totalRow];
        picture = new String[totalRow];
        status = new String[totalRow];
        temp = new String[totalRow];
        for(int n=0;n<totalRow;n++) {
            name[n] = array[n][0];
            time[n] = array[n][2];
            pay[n] = "RM " + array[n][3];
            location[n] = array[n][5];
            comment[n] = "2 Comments, 0 Offers";
            temp[n] = array[n][6];
            picture[n] = loginDataBaseAdapter.getUserPic(temp[n]);
            status[n] = array[n][7];

        }
         //Toast.makeText(context, "picture = " + picture[1], Toast.LENGTH_LONG).show();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder2 holder, int position) {

        holder.tv1.setText(name[position]);
        if(status[position].equals("ASSIGN")){
            holder.tv2.setTextColor(R.color.assigned);
        }
        holder.tv2.setText(pay[position]);
        holder.tv3.setText(time[position]);
        holder.tv4.setText(location[position]);
        holder.tv5.setText(comment[position]);
        int id = context.getApplicationContext().getResources().getIdentifier(picture[position] , "drawable", context.getApplicationContext().getPackageName());
        holder.imageView.setImageResource(id);
        holder.imageView.setOnClickListener(clickListener2);
        holder.imageView.setTag(holder);
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
            i.putExtra("userID",user_id);
            context.startActivity(i);

        }
    };

    View.OnClickListener clickListener2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            RecyclerViewHolder2 vholder = (RecyclerViewHolder2) v.getTag();
            int position = vholder.getPosition();

            Intent i = new Intent(context,ProfileActivity.class);

            i.putExtra("userID",temp[position]);

            context.startActivity(i);

        }
    };
    @Override
    public int getItemCount() {

        return totalRow;
    }
}