package lhy.gorunner;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by HAUYANG on 25/03/2016.
 */
public class RecyclerViewHolder5 extends RecyclerView.ViewHolder {

    TextView tv1, tv2,tv3;
    Button btn1;
    ImageView pic;

    public RecyclerViewHolder5(View itemView) {
        super(itemView);

        pic = (ImageView) itemView.findViewById(R.id.cp_image);
        tv1 = (TextView) itemView.findViewById(R.id.cp_name);
        tv2 = (TextView) itemView.findViewById(R.id.cp_content);
        btn1 = (Button) itemView.findViewById(R.id.select_runner_btn);


    }
}