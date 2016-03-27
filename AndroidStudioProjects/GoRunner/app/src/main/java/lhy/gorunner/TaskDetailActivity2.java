package lhy.gorunner;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by HAUYANG on 01/03/2016.
 */
public class TaskDetailActivity2  extends AppCompatActivity {
    String title,btn_name;
    AlertDialog.Builder alert,alert2;
    LoginDataBaseAdapter loginDataBaseAdapter;
    String[]data = new String[5];
    String[]userData = new String[2];
    String price;
    String user_id;
    String tempID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        alert= new AlertDialog.Builder(this);
        alert2 = new AlertDialog.Builder(this);
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);

        loginDataBaseAdapter=loginDataBaseAdapter.open();

        user_id = getIntent().getStringExtra("userID") ;
        final Button offerbutton = (Button)findViewById(R.id.offer_btn);
        TextView task_name = (TextView)findViewById(R.id.task_name);
        ImageView img =  (ImageView)findViewById(R.id.pic_in_task_detail5);
        ImageView icon = (ImageView)findViewById(R.id.lock_img);
        LinearLayout layout = (LinearLayout)findViewById(R.id.column_color);
        final TextView info = (TextView)findViewById(R.id.column_word);
        TextView term = (TextView)findViewById(R.id.term);
        TextView desc = (TextView)findViewById(R.id.task_desc);
        TextView taskprice = (TextView)findViewById(R.id.task_price);
        TextView user_name = (TextView)findViewById(R.id.task_poster_name);
        TextView date_time = (TextView) findViewById(R.id.task_hour);
        ImageView user_pic = (ImageView)findViewById(R.id.pic_in_task_detail);
        title = getIntent().getStringExtra("value");
        btn_name = getIntent().getStringExtra("btn_name");

        data = loginDataBaseAdapter.getRowItemsbyTitle(title);
        String taskdesc = data[0];
        price = data[1];
        tempID = data[2];
        String date = data[3];

        userData = loginDataBaseAdapter.getUserItems(tempID);
        String username = userData[0];
        String picture = userData[1];

        task_name.setText(title);
        desc.setText(taskdesc);
        taskprice.setText("RM " + price);
        user_name.setText(username);
        date_time.setText(date);
        int id = this.getResources().getIdentifier(picture, "drawable", getPackageName());
        user_pic.setImageResource(id);

        if(btn_name.equals("RELEASE")){
            layout.setBackgroundResource(R.drawable.rounded_cornergreen);
            img.setImageResource(R.drawable.circle1);
            offerbutton.setText("RELEASE PAYMENT");
            icon.setImageResource(R.drawable.lock);
            info.setText("Payment Secured");
            term.setText("* You must release payment on completion in order to release the payment to third party bank.");

        }
        else if (btn_name.equals("REQUEST")){
            layout.setBackgroundResource(R.drawable.rounded_cornergreen);
            img.setImageResource(R.drawable.circle1);
            offerbutton.setText("REQUEST PAYMENT");
            icon.setImageResource(R.drawable.lock);
            info.setText("Payment Secured");
            term.setText("* You must request payment on completion in order to request the payment from MyPay.");
        }

        findViewById(R.id.offer_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(offerbutton.getText().equals("RELEASE PAYMENT")) {
                    final EditText edittext = new EditText(getApplicationContext());
                    alert.setMessage("Enter your verification code: ");
                    alert.setTitle("Release Payment");

                    edittext.setTextColor(R.color.black);
                    edittext.setMaxLines(1);
                    alert.setView(edittext);

                    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            Intent i1 = new Intent(getApplication(), Review_Activity.class);
                            i1.putExtra("task_id","2");
                            i1.putExtra("userID",user_id);
                            startActivity(i1);

                        }
                    });

                    alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            // what ever you want to do with No option.
                        }
                    });

                    alert.show();
                }

                else {
                    alert.setMessage("Are you sure to request payment? ");
                    alert.setTitle("Request Payment");

                    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            alert2.setTitle("Request Payment");
                            alert2.setMessage("Your request has been sent and you will receive the payment once employer release the payment.");

                            alert2.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    offerbutton.setText("REQUEST SENT");
                                    offerbutton.setBackgroundColor(Color.parseColor("#dbdbdb"));
                                    offerbutton.setEnabled(false);
                                }
                                //OR

                            });
                            alert2.show();
                        }
                    });

                    alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            // what ever you want to do with No option.
                        }
                    });

                    alert.show();
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
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


}
