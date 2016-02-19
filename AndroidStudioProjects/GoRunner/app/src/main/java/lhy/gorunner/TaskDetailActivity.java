package lhy.gorunner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class TaskDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        findViewById(R.id.offer_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(TaskDetailActivity.this,"The Item Clicked is" , Toast.LENGTH_SHORT).show();

            }
        });
    }


}
