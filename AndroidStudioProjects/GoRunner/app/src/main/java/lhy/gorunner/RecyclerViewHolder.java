package lhy.gorunner;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by HAUYANG on 18/02/2016.
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;

    public RecyclerViewHolder(View itemView) {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.list_avatar);

    }

}
