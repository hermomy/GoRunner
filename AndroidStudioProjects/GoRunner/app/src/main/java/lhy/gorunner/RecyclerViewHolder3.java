package lhy.gorunner;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by HAUYANG on 24/02/2016.
 */
public class RecyclerViewHolder3 extends RecyclerView.ViewHolder {

    TextView tv1, tv2,tv3;
    Button btn1;

    public RecyclerViewHolder3(View itemView) {
        super(itemView);

        tv1 = (TextView) itemView.findViewById(R.id.title_mytask);
        tv2 = (TextView) itemView.findViewById(R.id.location_mytask);
        tv3 = (TextView) itemView.findViewById(R.id.date_mytask);
        btn1 = (Button) itemView.findViewById(R.id.mytask_button);


    }
}