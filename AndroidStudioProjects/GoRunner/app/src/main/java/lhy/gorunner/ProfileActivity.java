package lhy.gorunner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    String user_id;
    LoginDataBaseAdapter loginDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        Intent i = getIntent();

        user_id = i.getStringExtra("userID");

        // get Instance  of Database Adapter
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        String img = loginDataBaseAdapter.getUserPic(user_id);
        String[] data = loginDataBaseAdapter.getUserItems(user_id);
        TextView name = (TextView) findViewById(R.id.text1);
        TextView expertise = (TextView) findViewById(R.id.text3);
        TextView posted = (TextView) findViewById(R.id.text4);
        TextView completed = (TextView) findViewById(R.id.text5);
        TextView review = (TextView) findViewById(R.id.text6);
        TextView location = (TextView) findViewById(R.id.profile_location);


        name.setText(data[0]);
        location.setText(data[3]);
        expertise.setText(data[4]);
        posted.setText(data[5]);
        completed.setText(data[6]);
        review.setText(data[7]);
        ImageView profile_img = (ImageView)findViewById(R.id.image1);
        int id = getResources().getIdentifier(img, "drawable",getPackageName());
        profile_img.setImageResource(id);
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

}
