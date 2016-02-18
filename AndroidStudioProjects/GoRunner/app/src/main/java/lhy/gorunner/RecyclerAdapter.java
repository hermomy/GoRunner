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
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    int[] name = {R.drawable.test, R.drawable.test2, R.drawable.test3,R.drawable.test4,R.drawable.test5};
    Context context;
    LayoutInflater inflater;

    public RecyclerAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_list, parent, false);

        RecyclerViewHolder viewHolder = new RecyclerViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        holder.imageView.setImageResource(name[position]);
        holder.imageView.setOnClickListener(clickListener);
        holder.imageView.setTag(holder);
    }

    View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            RecyclerViewHolder vholder = (RecyclerViewHolder) v.getTag();
            int position = vholder.getPosition();

            Toast.makeText(context,"This is position "+position,Toast.LENGTH_LONG ).show();
            if(position==1) {
                Intent i1 = new Intent(context, Browse_Activity.class);
                context.startActivity(i1);
            }
        }
    };


    @Override
    public int getItemCount() {
        return name.length;
    }
}
