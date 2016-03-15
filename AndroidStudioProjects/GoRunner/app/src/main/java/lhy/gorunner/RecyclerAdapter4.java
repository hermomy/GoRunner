package lhy.gorunner;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by HAUYANG on 01/03/2016.
 */
public class RecyclerAdapter4 extends RecyclerView.Adapter<RecyclerViewHolder4>  {

    String[] name = {"Wei Sian", "Min Hua","Eric Tiew","Ann Pei","Wei Jie"};
    int[] image = {R.drawable.weisian,R.drawable.meow,R.drawable.eric,R.drawable.annpei,R.drawable.weijie};
    String[] content ={ "In the form of a business: Electricity and Electronic Technician Services\n\nDomestic services as Cleaner and others Handworks\nComputer maintenance (hardware e software); Internet access configuration; virus removal, etc;\n\nCompanies: Development of WEB applications (Java / PHP ) with MYSQL, MSSQL, POSTGRESS, ETC; and smartphone app.",
            "Hi\n\nI have a A.D in photoimaging and years of experience with photography , Photoshop and logo design.\n\nI also sketch realism portraits and transform people with special effects makeup.",
            "Hi\n\n20 Year old currently studying a bachelor of IT, with experience in many fields.\n\nIncluding Face to Face sales and marketing (To customers and businesses), Call centres (Sales, Debt Recovery), Data Entry,Admin Work, IT (Web Design, SEO, Repairs), and many more.",
            "Hello,\n\nMy name is Eric Tiew.I am an architect-engineer and a graphic designer currently based in Brisbane on an Australian working\nvisa. I am available to work casual or part-time and available to start immediately.\n\nI worked for more than 7 years as a project manager for an engineering firm \"Artech studio s.r.l.",
    "Worked in building industry for 12yrs then as a courier for 10yrs. Have bred and showed dogs in the past as a hobby.\n\nDrive a van. Currently attempting to renovate an inner city terrace."};
    Context context;
    LayoutInflater inflater;

    public RecyclerAdapter4(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolder4 onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_list_compare, parent, false);

        RecyclerViewHolder4 viewHolder = new RecyclerViewHolder4(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerViewHolder4 holder, int position) {

        holder.pic.setImageResource(image[position]);
        holder.tv1.setText(name[position]);
        holder.tv2.setText(content[position]);

        holder.btn1.setOnClickListener(clickListener);
        holder.btn1.setTag(holder);
    }

    View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
       public void onClick(View v) {

            RecyclerViewHolder4 vholder = (RecyclerViewHolder4) v.getTag();
            int position = vholder.getPosition();

                Intent i1 = new Intent(context, ViewOfferActivity.class);
                context.startActivity(i1);

        }
    };


    @Override
    public int getItemCount() {
        return name.length;
    }
}
