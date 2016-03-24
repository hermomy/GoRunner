package lhy.gorunner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MakeOfferActivity extends AppCompatActivity {

    @InjectView(R.id.make_offer_continue_btn) Button _makeofferContinueButton;
    String user_id,price,title,tempID;
    String receive;
    EditText task_comment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_offer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        title = getIntent().getExtras().getString("title");
        price = getIntent().getExtras().getString("price");
        user_id = getIntent().getExtras().getString("userID");
        tempID = getIntent().getExtras().getString("task_user_id");
        ButterKnife.inject(this);


        TextView task_title = (TextView)findViewById(R.id.make_offer_title);
        TextView task_price = (TextView)findViewById(R.id.make_offer_price);
        TextView task_receive = (TextView)findViewById(R.id.make_offer_receive);
         task_comment = (EditText)findViewById(R.id.make_offer_comment);

        task_title.setText(title);
        task_price.setText("RM " + price);
         receive = "RM " + String.valueOf(Integer.parseInt(price) - 5);
        task_receive.setText("You'll receive RM " + receive);

        _makeofferContinueButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ConfirmOfferActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("price", price);
                intent.putExtra("receive",receive);
                intent.putExtra("userID", user_id);
                intent.putExtra("task_user_id", tempID);
                intent.putExtra("task_comment",task_comment.getText().toString());
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

        if (id == R.id.action_home){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("userID",user_id);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
