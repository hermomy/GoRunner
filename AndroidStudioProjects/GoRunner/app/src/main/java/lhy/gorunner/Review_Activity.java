package lhy.gorunner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);
        Intent i = getIntent();

        user_id = i.getStringExtra("userID");
        task_name = i.getStringExtra("taskname");
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
            String file = "EmotionLookupTable.txt";

            Scanner EmotionWord = null;

            int score = 0;
            try {
                EmotionWord = new Scanner(new File(file));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            String[] splitted_word = sentence.split(" ");
//            for ( String ss : splitted_word) {
//
//                System.out.println(ss);
//            }

            int length = splitted_word.length;
            String compare, extra;

            try {
                score = checkBoosterWord(splitted_word);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            for (int n = 0; n < length; n++) {
                while (EmotionWord.hasNext()) {
                    compare = EmotionWord.next();

                    if (splitted_word[n].equals(compare)) {
                        char[] temp = EmotionWord.next().toCharArray();
                        if (temp[0] == '+') {
                            score = score + Character.getNumericValue(temp[1]);
                        } else {
                            score = score - Character.getNumericValue(temp[1]);
                        }
                        break;
                    }
                }


            }


        }
    };

    public int checkBoosterWord(String[] splitted_word) throws FileNotFoundException {

        String file = "BoosterWordList.txt";
        Scanner BoosterWord = new Scanner(new File(file));
        String compare;
        int score = 0;

        for (int n = 0; n < splitted_word.length; n++) {
            while (BoosterWord.hasNext()) {

                compare = BoosterWord.next();

                if (splitted_word[n].equals(compare)) {
                    char[] temp = BoosterWord.next().toCharArray();
                    if (temp[0] == '+') {
                        score = score + Character.getNumericValue(temp[1]);
                    } else {
                        score = score - Character.getNumericValue(temp[1]);
                    }
                    break;
                }
            }

        }
        return score;
    }

//    public String[] remainBoosterWord(String[] splitted_word) throws FileNotFoundException {
//
//        String file = "BoosterWordList.txt";
//        Scanner BoosterWord = new Scanner(new File(file));
//        String compare;
//        int score = 0;
//        String[] remain = new String[splitted_word.length];
//
//        for (int n = 0; n < splitted_word.length; n++) {
//            while (BoosterWord.hasNext()) {
//
//                compare = BoosterWord.next();
//
//                if (splitted_word[n].equals(compare)) {
//                    char[] temp = BoosterWord.next().toCharArray();
//                    if (temp[0] == '+') {
//                        score = score + Character.getNumericValue(temp[1]);
//                    } else {
//                        score = score - Character.getNumericValue(temp[1]);
//                    }
//                    break;
//                }
//            }
//            remain[n] = splitted_word[n];
//        }
//        return remain;
//    }
}
