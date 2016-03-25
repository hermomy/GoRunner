package lhy.gorunner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Review_Activity extends AppCompatActivity {
    LoginDataBaseAdapter loginDataBaseAdapter;
    String user_id;
    String task_name;
    EditText content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.review);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        Intent i = getIntent();

        // user_id = i.getStringExtra("userID");
        user_id = "1";
        //task_name = i.getStringExtra("taskname");
        task_name = "Graphic design work";
        // get Instance  of Database Adapter
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        String img = loginDataBaseAdapter.getUserPic(user_id);
        String[] data = loginDataBaseAdapter.getUserItems(user_id);
        TextView name = (TextView) findViewById(R.id.review_name);
        TextView title = (TextView) findViewById(R.id.review_title);
        ImageView profile_img = (ImageView) findViewById(R.id.review_pic);
        content = (EditText) findViewById(R.id.review_content);
        android.support.v7.widget.AppCompatButton btn = (android.support.v7.widget.AppCompatButton) findViewById(R.id.review_btn);
        btn.setOnClickListener(clickListener);
        name.setText(data[0]);
        title.setText(task_name);

        int id = getResources().getIdentifier(img, "drawable", getPackageName());

        profile_img.setImageResource(id);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String sentence = content.getText().toString();

            int score = 0;

            try {

                String[] splitted_word = sentence.split(" ");

                int length = splitted_word.length;
                String compare, extra;

                for (int n = 0; n < length; n++) {

                    score  =  score + (getBoosterScore(splitted_word[n]));

                }

                for (int n = 0; n < length; n++) {

                    score = score + (getEmotionScore(splitted_word[n]));
                    Log.e("Score: ", String.valueOf(score));

                }


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    };

    public int getEmotionScore(String word) throws IOException {

        String file = "EmotionLookupTable.txt";
        InputStream myFile = getResources().getAssets().open(file);
        Scanner EmotionWord = new Scanner(myFile);
        String compare;
        int score = 0;

            while (EmotionWord.hasNextLine()) {
                compare = EmotionWord.next();

                if (word.equals(compare)) {
                    char[] temp =  EmotionWord.next().toCharArray();
                    if (temp.length == 1) {
                        score = score + Character.getNumericValue(temp[0]);
                    } else {
                        score = score - Character.getNumericValue(temp[1]);
                    }

                }
                EmotionWord.nextLine();
            }
        return score;
    }

    public int getBoosterScore(String word) throws IOException {
        String compare;
        String file = "BoosterWordList.txt";
        InputStream myFile = getResources().getAssets().open(file);
        int score=0;
        Scanner BoosterWord = new Scanner(myFile);
        while (BoosterWord.hasNextLine()) {

            compare = BoosterWord.next();

            if (word.equals(compare)) {
                char[] temp = BoosterWord.next().toCharArray();
                if (temp.length == 1) {
                    score = score + Character.getNumericValue(temp[0]);
                } else {
                    score = score - Character.getNumericValue(temp[1]);
                }

            }
            BoosterWord.nextLine();
        }
        return score;
    }

}