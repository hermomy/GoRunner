package lhy.gorunner;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by HAUYANG on 25/03/2016.
 */
public class RecyclerAdapter5 extends RecyclerView.Adapter<RecyclerViewHolder5>{
    String[] name ;
    String[] image;
    String[] content;
    Context context;
    LayoutInflater inflater;
    LoginDataBaseAdapter loginDataBaseAdapter;
    String user_id;
    int totalRow;


    public RecyclerAdapter5(Context context,String user_id) {
        this.context = context;
        this.user_id = user_id;
        inflater = LayoutInflater.from(context);
        loginDataBaseAdapter = new LoginDataBaseAdapter(context);
        loginDataBaseAdapter = loginDataBaseAdapter.open();


        totalRow = loginDataBaseAdapter.getReviewTotalRow(user_id);
    }

    @Override
    public RecyclerViewHolder5 onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_list_profile, parent, false);

        RecyclerViewHolder5 viewHolder = new RecyclerViewHolder5(v);
        String[][] array;
        array= loginDataBaseAdapter.getReviewItems(user_id);


        name = new String[totalRow];
        image = new String[totalRow];
        content = new String[totalRow];

        for(int n=0;n<totalRow;n++) {
            content[n] = array[n][0];
            String[]temp = loginDataBaseAdapter.getUserItems(array[n][1]);
            name[n] = temp[0];
            image[n] = temp[1];
        }

            return viewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerViewHolder5 holder, int position) {
        int id = context.getApplicationContext().getResources().getIdentifier(image[position] , "drawable", context.getApplicationContext().getPackageName());
        holder.pic.setImageResource(id);
        holder.tv1.setText(name[position]);
        holder.tv2.setText(content[position]);

    }


    @Override
    public int getItemCount() {
        return totalRow;
    }
}


