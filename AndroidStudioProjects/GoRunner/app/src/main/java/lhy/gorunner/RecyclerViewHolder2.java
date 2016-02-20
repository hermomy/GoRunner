package lhy.gorunner;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by HAUYANG on 18/02/2016.
 */
public class RecyclerViewHolder2 extends RecyclerView.ViewHolder {

    TextView tv1, tv2,tv3;
    ImageView imageView;

    public RecyclerViewHolder2(View itemView) {
        super(itemView);

        tv1 = (TextView) itemView.findViewById(R.id.list_title_browse);
        tv2 = (TextView) itemView.findViewById(R.id.list_desc_browse);
        tv3 = (TextView) itemView.findViewById(R.id.list_time);
        imageView = (ImageView) itemView.findViewById(R.id.list_avatar_browse);

    }
}