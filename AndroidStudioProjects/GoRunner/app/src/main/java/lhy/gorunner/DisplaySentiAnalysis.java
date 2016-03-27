package lhy.gorunner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;

public class DisplaySentiAnalysis extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.displaysentianalysis);




        Button btn  =  (Button)findViewById(R.id.submit);


        btn.setOnClickListener(clickListener);


    }

    View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final TextView EmotionScore = (TextView)findViewById(R.id.emotionscore);
            final TextView BoosterScore = (TextView)findViewById(R.id.boosterscore);
            final TextView EmojiScore = (TextView)findViewById(R.id.emojiscore);
            final TextView TotalScore = (TextView)findViewById(R.id.totalscore);

            EditText input = (EditText)findViewById(R.id.user_input);
            final SentimentAnalysis analysis1 = new SentimentAnalysis(getApplicationContext(),input.getText().toString());
            try {
                analysis1.run();
            } catch (IOException e) {
                e.printStackTrace();
            }
            int[] array;
//            array = analysis1.getResultData();
//            EmotionScore.setText(array[0]);
//            BoosterScore.setText(array[1]);
//            EmojiScore.setText(array[2]);
//            TotalScore.setText(array[3]);

        }
    };
}
