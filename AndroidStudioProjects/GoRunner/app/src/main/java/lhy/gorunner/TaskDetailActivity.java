package lhy.gorunner;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class TaskDetailActivity extends AppCompatActivity {
    LoginDataBaseAdapter loginDataBaseAdapter;
    String[] data = new String[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        String task_id = getIntent().getExtras().getString("task_id");

        loginDataBaseAdapter=new LoginDataBaseAdapter(this);

        loginDataBaseAdapter=loginDataBaseAdapter.open();

        String[]data = loginDataBaseAdapter.getRowItems(task_id);
        String taskname = data[0];
        String taskdesc = data[1];
        String price = data[2];
        String status = data[3];

        TextView title = (TextView)findViewById(R.id.task_name);
        TextView desc = (TextView)findViewById(R.id.task_desc);
        TextView taskprice = (TextView)findViewById(R.id.task_price);
        TextView info = (TextView)findViewById(R.id.column_word);
        TextView term = (TextView)findViewById(R.id.term);
        Button offerbutton = (Button)findViewById(R.id.offer_btn);
        ImageView img =  (ImageView)findViewById(R.id.pic_in_task_detail5);
        ImageView icon = (ImageView)findViewById(R.id.lock_img);
        LinearLayout layout = (LinearLayout)findViewById(R.id.column_color);

        if(status.equals("OPEN")){
            offerbutton.setText("Make Offer");
            info.setText("Paid with GoRunner MyPay");

        }
        else {
            layout.setBackgroundResource(R.drawable.rounded_cornergreen);
            img.setImageResource(R.drawable.circle1);
            offerbutton.setText("RELEASE PAYMENT");
            icon.setImageResource(R.drawable.lock);
            info.setText("Payment Secured");
            term.setText("* You must wait for the Runner to complete the task and request payment on completion in order to release the payment into their verified bank.");

        }

        title.setText(taskname);
        desc.setText(taskdesc);
        taskprice.setText(price);

        findViewById(R.id.offer_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MakeOfferActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if(id == R.id.action_home){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


}
