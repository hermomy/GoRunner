package lhy.gorunner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.zip.Inflater;

/**
 * Created by HAUYANG on 18/02/2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    int[] name = {R.drawable.test, R.drawable.test2, R.drawable.test3,R.drawable.test4,R.drawable.test5_1};
    Context context;
    LayoutInflater inflater;
    View v2;

    public RecyclerAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_list, parent, false);
        v2 = inflater.inflate(R.layout.browse_task,parent,false);

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

            if(position <=4 ) {
                Intent i1 = new Intent(context, Browse_Activity.class);
                i1.putExtra("EXTRA_IMAGEVIEW_URL", position);
                context.startActivity(i1);

            }
        }
    };

    @Override
    public int getItemCount() {
        return name.length;
    }
}
