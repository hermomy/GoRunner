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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

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

                Set<String> same_occurrence = checkSameOccurrence(splitted_word);

                for (String s : same_occurrence)

                    Log.e("same occurence: ", s);

                int length = splitted_word.length;

                    for (int n = 0; n < length; n++) {

                        score = score + (getEmotionScore(splitted_word[n]));

                    }

                     if (same_occurrence.isEmpty()) {         //if no same occurrence then it will read through the whole sentence

                         for (int n = 0; n < length; n++) {

                             score = score + (getBoosterScore(splitted_word[n]));

                         }
                     }

                else {                                       //if more than one same occurrence then it will send the same_occurrence array to getExtraScore to obtain the extra score

                            score = score + getExtraScore(same_occurrence) ;

                     }

                    for (int n = 0; n < length; n++) {

                        score = score + (getEmojiScore(splitted_word[n]));

                    }


                Log.e("Score: ", String.valueOf(score));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    };

    public int getExtraScore(Set<String> same_occurrence) throws IOException {
        //in the case of only having one same occurrence [good] rather than [good,good] from same_occurrence array
        //in the case of only booster word has more than one occurrence

        int score=0;

        for (String s : same_occurrence) {

            score = score + getBoosterScore(s) + (getBoosterScore(s) / 2);

        }

        return score;
    }

    public Set<String> checkSameOccurrence(String[] splitted_word) throws IOException {    // to check whether there is same occurrence (word) in one sentence. Return a list of same occurrence in array list.
        int extra = 0;
        int length = splitted_word.length -1;
        int k=1;
        List<String> same = new ArrayList<String>();

        for(int n=0;n<length;n++){
            for(int j=0; j<splitted_word.length-k;j++) {
                if (splitted_word[n].equals(splitted_word[j + k])) {

                    same.add(splitted_word[n]);

                }

            }

            k++;
        }

        Set<String> set = new HashSet<String>(same);            //Use HashSet is to remove all duplicate data in the arrayList

        return set;
    }

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
                break;
                }
                EmotionWord.nextLine();
            }
        return score;
    }

    public int getEmojiScore(String word) throws IOException {

        String file = "EmojiLookupTable.txt";
        InputStream myFile = getResources().getAssets().open(file);
        Scanner Emoji = new Scanner(myFile);
        String compare;
        int score = 0;

        while (Emoji.hasNextLine()) {
            compare = Emoji.next();

            if (word.equals(compare)) {
                char[] temp =  Emoji.next().toCharArray();
                if (temp.length == 1) {
                    score = score + Character.getNumericValue(temp[0]);
                } else {
                    score = score - Character.getNumericValue(temp[1]);
                }
                break;
            }
            Emoji.nextLine();
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
                break;
            }
            BoosterWord.nextLine();
        }
        return score;
    }

}