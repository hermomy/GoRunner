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
import android.widget.Toast;

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
    String task_id;
    String post_task_user;
String[] item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.review);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);


        //user_id = getIntent().getStringExtra("userID");
       // task_id = getIntent().getStringExtra("task_id");
        task_id = "2";
        user_id = "1" ;
        // get Instance  of Database Adapter
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();
        item = loginDataBaseAdapter.getRowItems(task_id);

        task_name = item[0];

         post_task_user = item[4];

        String img = loginDataBaseAdapter.getUserPic(post_task_user);
        String[] data = loginDataBaseAdapter.getUserItems(post_task_user);
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
        if(!sentence.matches("")) {
            SentimentAnalysis analysis1 = new SentimentAnalysis(getApplicationContext(), sentence);

            try {
                int score = analysis1.run();

//                loginDataBaseAdapter.createComment(sentence, score, post_task_user, user_id);  // * user_id and post_task_user is reversed in this activity
//
//
//                int rating = loginDataBaseAdapter.getReviewScore(post_task_user);
//                loginDataBaseAdapter.insertRating(post_task_user, rating);


//               // Intent i1 = new Intent(getApplication(), OtherProfileActivity.class);
//                i1.putExtra("otherUserID", post_task_user);
//                i1.putExtra("userID", user_id);
//
//                startActivity(i1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
            else {
            Toast.makeText(getApplicationContext(), "Please enter a valid content!", Toast.LENGTH_LONG).show();
            }



        }
    };

}
//            int score = 0;
//
//            try {
//
//                String[] splitted_word = sentence.split(" ");
//
//               // Set<String> same_occurrence = checkSameOccurrence(splitted_word);
//
//                List<String> generalized = generalizeSentence(splitted_word);              //generalize the splitted_word by putting only emotion word,booster word and emoji in the array
//
//
//                for (String s: generalized){
//                    score = score + getEmotionScore(s);                 //calculate the total score of all emotion words
//                }
//
//                for (String s: generalized){
//                    score = score + getEmojiScore(s);                   //calculate the total score of all emoji
//                }
//
//                score = score + getTotalBoosterScore(generalized);       //calculate the total score of all booster words
//
//
//                    Log.e("Total Score: ", String.valueOf(score));
//
////                    for (int n = 0; n < length; n++) {
////
////                        score = score + (getEmotionScore(splitted_word[n]));
////
////                    }
////
////                     if (same_occurrence.isEmpty()) {         //if no same occurrence then it will read through the whole sentence
////
////                         for (int n = 0; n < length; n++) {
////
////                             score = score + (getBoosterScore(splitted_word[n]));
////
////                         }
////                     }
////
////                else {                                       //if more than one same occurrence then it will send the same_occurrence array to getExtraScore to obtain the extra score
////
////                            score = score + getExtraScore(same_occurrence) ;
////
////                     }
////
////                    for (int n = 0; n < length; n++) {
////
////                        score = score + (getEmojiScore(splitted_word[n]));
////
////                    }
//
//// Log.e("Score: ", String.valueOf(score));
//
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//    };
//
//    public List<String> generalizeSentence(String[] splitted_word) throws IOException {
//        List<String> generalizedArray = new ArrayList<String>();
//
//        for (int n = 0; n < splitted_word.length; n++) {
//            String word = splitted_word[n];
//            if (checkEmotion(word) || checkBooster(word) || checkEmoji(word)) {
//                generalizedArray.add(splitted_word[n]);
//            }
//        }
//
//        return generalizedArray;
//    }
//
//    public Boolean checkEmotion(String word) throws IOException {
//        String file = "EmotionLookupTable.txt";
//        InputStream myFile = getResources().getAssets().open(file);
//        Scanner EmotionWord = new Scanner(myFile);
//        String compare;
//        Boolean check = false;
//
//        while (EmotionWord.hasNextLine()) {
//            compare = EmotionWord.next();
//
//            if (word.equals(compare)) {
//            check = true;
//            }
//            EmotionWord.nextLine();
//        }
//        return check;
//    }
//
//    public Boolean checkBooster(String word) throws IOException {       //check the existence of particular word in BoosterWordList.txt
//        String file = "BoosterWordList.txt";
//        InputStream myFile = getResources().getAssets().open(file);
//        Scanner BoosterWord = new Scanner(myFile);
//        String compare;
//        Boolean check = false;
//
//        while (BoosterWord.hasNextLine()) {
//            compare = BoosterWord.next();
//
//            if (word.equals(compare)) {
//                check = true;
//            }
//            BoosterWord.nextLine();
//        }
//        return check;
//    }
//
//    public Boolean checkEmoji(String word) throws IOException {
//        String file = "EmojiLookupTable.txt";
//        InputStream myFile = getResources().getAssets().open(file);
//        Scanner Emoji = new Scanner(myFile);
//        String compare;
//        Boolean check = false;
//
//        while (Emoji.hasNextLine()) {
//            compare = Emoji.next();
//
//            if (word.equals(compare)) {
//                check = true;
//            }
//            Emoji.nextLine();
//        }
//        return check;
//    }
//
//    public int getTotalBoosterScore(List<String> generalized) throws IOException {
//        List <String> BoosterArray = new ArrayList<String>();
//        List <Integer> ScoreArray = new ArrayList<Integer>();
//        List <Character> SignArray = new ArrayList<Character>();
//        List <String> CombinedArray = new ArrayList<String>();
//        int score = 0 ;
//        for(int n=0;n<generalized.size();n++){
//            if(checkBooster(generalized.get(n))){
//                BoosterArray.add(generalized.get(n));                   //Put all booster words in generalized array into the BoosterArray. e.g ["absolutely","really"]
//            }
//        }
//
//        for(int n=0;n<BoosterArray.size();n++){
//            ScoreArray.add(getBoosterScore(BoosterArray.get(n)));          //Calculate the score for each booster word. e.g [3,4,4,3]
//
//        }
//
//        for(Integer s : ScoreArray)
//            Log.e("Score Array: ", String.valueOf(s));
//
//        SignArray = getSign(generalized);                       //Sign Array. e.g ['+','-','-']
//
//        for(int n=0;n<ScoreArray.size();n++){
//            CombinedArray.add(String.valueOf(SignArray.get(n)) + String.valueOf(ScoreArray.get(n)));      //Combined two array: SignArray & Score Array to form an combined array. e.g [+3,-4,-1]
//
//
//        }
//
//        for(String s : CombinedArray)
//            Log.e("Combined Array: ", s);
//
//        for (int n=0;n<CombinedArray.size();n++) {
//            char[] temp = CombinedArray.get(n).toCharArray();                       //decompose the item in combine array. e.g ['+','3']
//            if(temp[0] == '+') {
//                score = score + Character.getNumericValue(temp[1]);
//            }
//            else {
//                score = score - Character.getNumericValue(temp[1]);
//            }
//        }
//        return score;                       //return the total score of booster words
//    }
//
//
//    public List<Character> getSign(List<String> generalized) throws IOException {
//        List <Character> sign = new ArrayList<Character>();
//
//        for (int n=0;n<generalized.size();n++){
//
//           if( checkBooster(generalized.get(n))){               //if current word is a booster word then it will check for next first occurrence of emotion word
//
//              for(int j=1;j<=generalized.size()-1;j++) {
//
//                  if(checkEmotion(generalized.get(n+1))){
//
//                      sign.add(getEmotionSignValue(generalized.get(n + 1)));
//
//                   }
//
//
//               }
//           }
//        }
//        for(Character s : sign)
//            Log.e("Sign Array: ", s.toString());
//        return sign;
//    }
//
//
//
//
//    public char getEmotionSignValue(String word) throws IOException {
//        String file = "EmotionLookupTable.txt";
//        InputStream myFile = getResources().getAssets().open(file);
//        Scanner EmotionWord = new Scanner(myFile);
//        String compare;
//        char sign = ' ';
//        while (EmotionWord.hasNextLine()) {
//            compare = EmotionWord.next();
//
//            if (word.equals(compare)) {
//                char[] temp =  EmotionWord.next().toCharArray();
//                if (temp.length == 1) {
//                    return '+';
//                }
//                else {
//                    return '-';
//                }
//
//            }
//            EmotionWord.nextLine();
//        }
//        return sign;
//    }
//
//
//    public int getExtraScore(Set<String> same_occurrence) throws IOException {
//        //in the case of only having one same occurrence [good] rather than [good,good] from same_occurrence array
//        //in the case of only booster word has more than one occurrence
//
//        int score=0;
//
//        for (String s : same_occurrence) {
//
//            score = score + getBoosterScore(s) + (getBoosterScore(s) / 2);
//
//        }
//
//        return score;
//    }
//
//    public Set<String> checkSameOccurrence(List<String> generalized) throws IOException {    // to check whether there is same occurrence (word) in one sentence. Return a list of same occurrence in array list.
//        int k=1;
//        List<String> same = new ArrayList<String>();
//
//        for(int n=0;n<generalized.size()-1;n++){
//
//            for(int j=0; j<generalized.size() - k;j++) {
//
//                if (generalized.get(n).equals(generalized.get(j + k))) {
//
//                    same.add(generalized.get(n));
//
//                }
//
//            }
//
//            k++;
//        }
//
//        Set<String> set = new HashSet<String>(same);            //Use HashSet is to remove all duplicate data in the arrayList
//
//        return set;
//    }
//
//    public int getEmotionScore(String word) throws IOException {            //search through the EmotionLookupTable.txt to check if particular word exists in the dictionary
//
//        String file = "EmotionLookupTable.txt";
//        InputStream myFile = getResources().getAssets().open(file);
//        Scanner EmotionWord = new Scanner(myFile);
//        String compare;
//        int score = 0;
//
//            while (EmotionWord.hasNextLine()) {
//                compare = EmotionWord.next();
//
//                if (word.equals(compare)) {
//                    char[] temp =  EmotionWord.next().toCharArray();
//                    if (temp.length == 1) {
//                        score = score + Character.getNumericValue(temp[0]);
//                    } else {
//                        score = score - Character.getNumericValue(temp[1]);
//                    }
//                break;
//                }
//                EmotionWord.nextLine();
//            }
//        return score;
//    }
//
//    public int getEmojiScore(String word) throws IOException {                 //search through the EmojiLookupTable.txt to check if particular word exists in the dictionary
//
//        String file = "EmojiLookupTable.txt";
//        InputStream myFile = getResources().getAssets().open(file);
//        Scanner Emoji = new Scanner(myFile);
//        String compare;
//        int score = 0;
//
//        while (Emoji.hasNextLine()) {
//            compare = Emoji.next();
//
//            if (word.equals(compare)) {
//                char[] temp =  Emoji.next().toCharArray();
//                if (temp.length == 1) {
//                    score = score + Character.getNumericValue(temp[0]);
//                } else {
//                    score = score - Character.getNumericValue(temp[1]);
//                }
//                break;
//            }
//            Emoji.nextLine();
//        }
//        return score;
//    }
//
//    public int getBoosterScore(String word) throws IOException {                //search through the BoosterWordList.txt to check if particular word exists in the dictionary
//        String compare;
//        String file = "BoosterWordList.txt";
//        InputStream myFile = getResources().getAssets().open(file);
//        int score=0;
//        Scanner BoosterWord = new Scanner(myFile);
//        while (BoosterWord.hasNextLine()) {
//
//            compare = BoosterWord.next();
//
//            if (word.equals(compare)) {
//                char[] temp = BoosterWord.next().toCharArray();             //decompose the word into char array. e.g -1 will be converted to ['-','1']
//                if (temp.length == 1) {                                       //if temp.length == 1 means it is a positive value
//                    score = score + Character.getNumericValue(temp[0]);
//                } else {
//                    score = score - Character.getNumericValue(temp[1]);
//                }
//                break;
//            }
//            BoosterWord.nextLine();
//        }
//        return score;
//    }



