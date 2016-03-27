package lhy.gorunner;

import android.content.Context;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by HAUYANG on 27/03/2016.
 */
public class SentimentAnalysis {
    String sentence;
    private Context context;
    List <String> BoosterArray ;
    List <Integer> ScoreArray;
    List <Character> SignArray ;
    List <String> CombinedArray;
    int getResultEmotion=0;
    int getResultBooster=0;
    int getResultEmoji =0;
    int getResultTotalScore = 0;
    public SentimentAnalysis(Context context,String sentence){

        this.sentence = sentence;
        this.context = context;
    }

//    public int[] getResultData(){
//        int[] temp = new int[4];
//        temp[0] = this.getResultEmotion;
//        temp[1] = this.getResultBooster;
//        temp[2] = this.getResultEmoji;
//        temp[3] = this.getResultTotalScore;
//        return temp;
//    }

    public int run() throws IOException {

        int score = 0;

            String[] splitted_word = sentence.split(" ");

            List<String> generalized = generalizeSentence(splitted_word);              //generalize the splitted_word by putting only emotion word,booster word and emoji in the array


    for (String s: generalized){

        score = score + getEmotionScore(s);                 //calculate the total score of all emotion words

    }

            Log.e("Emotion Score: ", String.valueOf(score));

        this.getResultEmotion = score;       //return for result

    for (String s: generalized){

        score = score + getEmojiScore(s);                   //calculate the total score of all emoji

    }


        score = score + getTotalBoosterScore(generalized);       //calculate the total score of all booster words

        getResultTotalScore = score;
             Log.e("Total Score: ", String.valueOf(score));

    return score;
    }

    public List<String> generalizeSentence(String[] splitted_word) throws IOException {
        List<String> generalizedArray = new ArrayList<String>();

        for (int n = 0; n < splitted_word.length; n++) {
            String word = splitted_word[n];
            if (checkEmotion(word) || checkBooster(word) || checkEmoji(word)) {
                generalizedArray.add(splitted_word[n]);
            }
        }

        return generalizedArray;
    }

    public Boolean checkEmotion(String word) throws IOException {
        String file = "EmotionLookupTable.txt";
        InputStream myFile = context.getResources().getAssets().open(file);
        Scanner EmotionWord = new Scanner(myFile);
        String compare;
        Boolean check = false;

        while (EmotionWord.hasNextLine()) {
            compare = EmotionWord.next();

            if (word.equals(compare)) {
                check = true;
            }
            EmotionWord.nextLine();
        }
        return check;
    }

    public Boolean checkBooster(String word) throws IOException {       //check the existence of particular word in BoosterWordList.txt
        String file = "BoosterWordList.txt";
        InputStream myFile = context.getResources().getAssets().open(file);
        Scanner BoosterWord = new Scanner(myFile);
        String compare;
        Boolean check = false;

        while (BoosterWord.hasNextLine()) {
            compare = BoosterWord.next();

            if (word.equals(compare)) {
                check = true;
            }
            BoosterWord.nextLine();
        }
        return check;
    }

    public Boolean checkEmoji(String word) throws IOException {
        String file = "EmojiLookupTable.txt";
        InputStream myFile = context.getResources().getAssets().open(file);
        Scanner Emoji = new Scanner(myFile);
        String compare;
        Boolean check = false;

        while (Emoji.hasNextLine()) {
            compare = Emoji.next();

            if (word.equals(compare)) {
                check = true;
            }
            Emoji.nextLine();
        }
        return check;
    }

    public int getTotalBoosterScore(List<String> generalized) throws IOException {
        BoosterArray = new ArrayList<String>();
        ScoreArray = new ArrayList<Integer>();
        SignArray = new ArrayList<Character>();
        CombinedArray = new ArrayList<String>();
        int score = 0 ;
        for(int n=0;n<generalized.size();n++){
            if(checkBooster(generalized.get(n))){
                BoosterArray.add(generalized.get(n));                   //Put all booster words in generalized array into the BoosterArray. e.g ["absolutely","really"]
            }
        }

        for(int n=0;n<BoosterArray.size();n++){
            ScoreArray.add(getBoosterScore(BoosterArray.get(n)));          //Calculate the score for each booster word. e.g [3,4,4,3]

        }

        for(Integer s : ScoreArray)
            Log.e("Score Array: ", String.valueOf(s));

        SignArray = getSign(generalized);                       //Sign Array. e.g ['+','-','-']

        for(int n=0;n<ScoreArray.size();n++){

            CombinedArray.add(String.valueOf(SignArray.get(n)) + String.valueOf(ScoreArray.get(n)));      //Combined two array: SignArray & Score Array to form an combined array. e.g [+3,-4,-1]

        }

        for(String s : CombinedArray)
            Log.e("Combined Array: ", s);

        for (int n=0;n<CombinedArray.size();n++) {
            char[] temp = CombinedArray.get(n).toCharArray();                       //decompose the item in combine array. e.g ['+','3']
            if(temp[0] == '+') {
                score = score + Character.getNumericValue(temp[1]);
            }
            else if(temp[0] == '-') {
                score = score - Character.getNumericValue(temp[1]);
            }
            else {
                score = score + 0;                  //assume -> cannot find emotion word after booster word
            }
        }

        this.getResultBooster = score;
        Log.e("Booster Score: ", String.valueOf(score));
        return score;                                         //return the total score of booster words
    }


    public List<Character> getSign(List<String> generalized) throws IOException {
        List <Character> sign = new ArrayList<Character>();

        int arraySize = generalized.size();

        for (int n=0;n<generalized.size();n++){

            here:
            if( checkBooster(generalized.get(n))){               //if current word is a booster word then it will check for next first occurrence of emotion word

                if(n != arraySize-1) {

                    for (int j = 1; j <= generalized.size() - 1; j++) {

                        if (checkEmotion(generalized.get(j))) {

                            sign.add(getEmotionSignValue(generalized.get(n + 1)));   //get sign value of a particular emotion word which is either positive or negative. '-' or '+'
                            break here;
                        }

                    }
                }

                else {
                    sign.add('n');
                }

            }


        }

        if(sign.isEmpty()){
            sign.add('n');                                      //if there is no emotion word found after booster word then return neural ('n')
        }

        for(Character s : sign)
            Log.e("Sign Array: ", s.toString());
        return sign;
    }


    public char getEmotionSignValue(String word) throws IOException {
        String file = "EmotionLookupTable.txt";
        InputStream myFile =  context.getAssets().open(file);
        Scanner EmotionWord = new Scanner(myFile);
        String compare;
        char sign = ' ';
        while (EmotionWord.hasNextLine()) {
            compare = EmotionWord.next();

            if (word.equals(compare)) {
                char[] temp =  EmotionWord.next().toCharArray();
                if (temp.length == 1) {
                    return '+';
                }
                else {
                    return '-';
                }

            }
            EmotionWord.nextLine();
        }
        return sign;
    }


    public int getExtraScore(Set<String> same_occurrence) throws IOException {
        //in the case of only having one same occurrence [good] rather than [good,good] from same_occurrence array
        //in the case of only booster word has more than one occurrence

        int score=0;

        for (String s : same_occurrence) {

            score = score + getBoosterScore(s) + (getBoosterScore(s) / 2);

        }

        return score;
    }

    public Set<String> checkSameOccurrence(List<String> generalized) throws IOException {    // to check whether there is same occurrence (word) in one sentence. Return a list of same occurrence in array list.
        int k=1;
        List<String> same = new ArrayList<String>();

        for(int n=0;n<generalized.size()-1;n++){

            for(int j=0; j<generalized.size() - k;j++) {

                if (generalized.get(n).equals(generalized.get(j + k))) {

                    same.add(generalized.get(n));

                }

            }

            k++;
        }

        Set<String> set = new HashSet<String>(same);            //Use HashSet is to remove all duplicate data in the arrayList

        return set;
    }

    public int getEmotionScore(String word) throws IOException {            //search through the EmotionLookupTable.txt to check if particular word exists in the dictionary

        String file = "EmotionLookupTable.txt";
        InputStream myFile = context.getResources().getAssets().open(file);
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

    public int getEmojiScore(String word) throws IOException {                 //search through the EmojiLookupTable.txt to check if particular word exists in the dictionary

        String file = "EmojiLookupTable.txt";
        InputStream myFile = context.getResources().getAssets().open(file);
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
        this.getResultEmoji = score;
        return score;
    }

    public int getBoosterScore(String word) throws IOException {                //search through the BoosterWordList.txt to check if particular word exists in the dictionary
        String compare;
        String file = "BoosterWordList.txt";
        InputStream myFile = context.getResources().getAssets().open(file);
        int score=0;
        Scanner BoosterWord = new Scanner(myFile);
        while (BoosterWord.hasNextLine()) {

            compare = BoosterWord.next();

            if (word.equals(compare)) {
                char[] temp = BoosterWord.next().toCharArray();             //decompose the word into char array. e.g -1 will be converted to ['-','1']
                if (temp.length == 1) {                                       //if temp.length == 1 means it is a positive value
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