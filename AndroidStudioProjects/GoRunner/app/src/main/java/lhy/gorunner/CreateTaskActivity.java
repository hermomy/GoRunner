package lhy.gorunner;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class CreateTaskActivity extends AppCompatActivity {

    @InjectView(R.id.button3) Button _PostTask;
    public String title,details,address,price;
    LoginDataBaseAdapter loginDataBaseAdapter;
    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();
        Intent i = getIntent();
        user_id = i.getStringExtra("userID");
     //   Toast.makeText(this, "User ID = " + user_id, Toast.LENGTH_LONG).show();
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
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("userID",user_id);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveRecord(View view){
        TextView getBudget;
        getBudget = (TextView)findViewById(R.id.fragment_create).findViewById(R.id.budget);
        price = getBudget.getText().toString();
        loginDataBaseAdapter.insertNewTask(title,details,address,price,user_id);
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Posting task...");
            progressDialog.show();
             final Intent intent = new Intent(this, Browse_Activity.class);
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            // On complete call either onLoginSuccess or onLoginFailed
                            // onLoginFailed();
                            intent.putExtra("userID",user_id);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            progressDialog.dismiss();
                        }
                    }, 2000);

    }

    public void selectFrag(View view) {
        Fragment fr = null;
        TextView getAddress;
        Frag_Map fragmap = new Frag_Map();
        EditText editText1,editText2;
        ImageView img1 = (ImageView)findViewById(R.id.step2);
        ImageView img2 = (ImageView)findViewById(R.id.step3);


        if(view == findViewById(R.id.button1)) {
            fr = new Frag_Map();
            img1.setImageResource(R.drawable.step2);


        }
        else {
            getAddress = (TextView) findViewById(R.id.fragment_create).findViewById(R.id.address);
            address = getAddress.getText().toString();
            fr = new Frag_confirm(details,title,address);
            img2.setImageResource(R.drawable.step3);
        }

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        editText1 = (EditText)findViewById(R.id.fragment_create).findViewById(R.id.edit_details);
        editText2 = (EditText)findViewById(R.id.fragment_create).findViewById(R.id.edit_title);

        details = editText1.getText().toString();
        title = editText2.getText().toString();

        fragmentTransaction.replace(R.id.fragment_create, fr);
        fragmentTransaction.commit();

    }





}
