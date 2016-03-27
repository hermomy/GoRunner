package lhy.gorunner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class OtherProfileActivity extends AppCompatActivity {
    String user_id,post_task_user_id;
    LoginDataBaseAdapter loginDataBaseAdapter;
    int score;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        Intent i = getIntent();

        // user_id = i.getStringExtra("userID");

        user_id = i.getStringExtra("userID");

        post_task_user_id = i.getStringExtra("otherUserID");

        recyclerView= (RecyclerView) findViewById(R.id.profile_recycler_view);

        RecyclerAdapter5 adapter=new RecyclerAdapter5(this,post_task_user_id);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        //Layout manager for Recycler view
//        int viewHeight = adapter.getItemCount() * 90;
//        recyclerView.getLayoutParams().height = viewHeight;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // get Instance  of Database Adapter
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        String img = loginDataBaseAdapter.getUserPic(post_task_user_id);
        String[] data = loginDataBaseAdapter.getUserItems(post_task_user_id);
        TextView name = (TextView) findViewById(R.id.text1);
        TextView expertise = (TextView) findViewById(R.id.text3);
        TextView posted = (TextView) findViewById(R.id.text4);
        TextView completed = (TextView) findViewById(R.id.text5);
        TextView review = (TextView) findViewById(R.id.text6);
        TextView location = (TextView) findViewById(R.id.profile_location);
        TextView rating_score = (TextView)findViewById(R.id.rating_score);
        ImageView star1 = (ImageView)findViewById(R.id.star1);
        ImageView star2 = (ImageView)findViewById(R.id.star2);
        ImageView star3 = (ImageView)findViewById(R.id.star3);
        ImageView star4 = (ImageView)findViewById(R.id.star4);
        ImageView star5 = (ImageView)findViewById(R.id.star5);

        String[] temp = loginDataBaseAdapter.getUserItems(post_task_user_id);

        score = Integer.parseInt(temp[8]);
        rating_score.setText(String.valueOf(score));

        if( score>1 && score<=30){
            star1.setImageResource(R.drawable.filledstar);
        }

        else if (score>=31 && score<=50){
            star1.setImageResource(R.drawable.filledstar);
            star2.setImageResource(R.drawable.filledstar);
        }

        else if (score>=51 && score<=70){
            star1.setImageResource(R.drawable.filledstar);
            star2.setImageResource(R.drawable.filledstar);
            star3.setImageResource(R.drawable.filledstar);
        }

        else if (score>=71 && score<=90){
            star1.setImageResource(R.drawable.filledstar);
            star2.setImageResource(R.drawable.filledstar);
            star3.setImageResource(R.drawable.filledstar);
            star4.setImageResource(R.drawable.filledstar);
        }

        else {
            star1.setImageResource(R.drawable.filledstar);
            star2.setImageResource(R.drawable.filledstar);
            star3.setImageResource(R.drawable.filledstar);
            star4.setImageResource(R.drawable.filledstar);
            star5.setImageResource(R.drawable.filledstar);
        }
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
