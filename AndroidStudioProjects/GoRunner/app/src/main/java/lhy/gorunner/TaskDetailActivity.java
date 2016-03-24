package lhy.gorunner;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import me.drakeet.materialdialog.MaterialDialog;

public class TaskDetailActivity extends AppCompatActivity {
    LoginDataBaseAdapter loginDataBaseAdapter;
    String[] data = new String[6];
    String user_id;
    String price;
    String taskname;
    String[] userData = new String[2];
    String tempID;
    AlertDialog.Builder alert ;
    MaterialDialog mMaterialDialog = new MaterialDialog(this);;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        alert= new AlertDialog.Builder(this);

        String task_id = getIntent().getExtras().getString("task_id");

        loginDataBaseAdapter=new LoginDataBaseAdapter(this);

        loginDataBaseAdapter=loginDataBaseAdapter.open();

        user_id = getIntent().getExtras().getString("userID");



        data = loginDataBaseAdapter.getRowItems(task_id);
         taskname = data[0];
        String taskdesc = data[1];
         price = data[2];
        String status = data[3];
       tempID = data[4];
        String date = data[5];

        userData = loginDataBaseAdapter.getUserItems(data[4]);
        String username = userData[0];
        String picture = userData[1];

        TextView date_time = (TextView) findViewById(R.id.task_hour);
        TextView user_name = (TextView)findViewById(R.id.task_poster_name);
        TextView title = (TextView)findViewById(R.id.task_name);
        TextView desc = (TextView)findViewById(R.id.task_desc);
        TextView taskprice = (TextView)findViewById(R.id.task_price);
        final TextView info = (TextView)findViewById(R.id.column_word);
        TextView term = (TextView)findViewById(R.id.term);
        final Button offerbutton = (Button)findViewById(R.id.offer_btn);
        ImageView user_pic = (ImageView)findViewById(R.id.pic_in_task_detail);
        ImageView img =  (ImageView)findViewById(R.id.pic_in_task_detail5);
        ImageView icon = (ImageView)findViewById(R.id.lock_img);
        LinearLayout layout = (LinearLayout)findViewById(R.id.column_color);

        if(status.equals("OPEN")){
            offerbutton.setText("Make Offer");
            info.setText("Paid with GoRunner MyPay");

        }
        else {
            img.setImageResource(R.drawable.circle1);
            offerbutton.setText("Assigned");
            offerbutton.setBackgroundColor(Color.parseColor("#dbdbdb"));
            info.setText("Paid with MyPay");
            term.setText("");

        }

        title.setText(taskname);
        desc.setText(taskdesc);
        taskprice.setText("RM " + price);
        user_name.setText(username);
        date_time.setText(date);
        int id = this.getResources().getIdentifier(picture, "drawable", getPackageName());
        user_pic.setImageResource(id);

        findViewById(R.id.offer_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(offerbutton.getText().equals("Make Offer")) {

                    Intent intent = new Intent(getApplicationContext(), MakeOfferActivity.class);

                    intent.putExtra("title",taskname);
                    intent.putExtra("price",price);
                    intent.putExtra("userID", user_id);
                    intent.putExtra("task_user_id",tempID);
                    startActivity(intent);
                }
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
            intent.putExtra("userID",user_id);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


}
