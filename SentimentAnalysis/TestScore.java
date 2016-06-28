import java.io.File;
import java.util.Scanner;
import java.io.IOException;
public class TestScore {

	public static void main(String[] args){
		
		
		Scanner input = new Scanner(System.in);
		System.out.println("Enter sentence: ");
	while(!input.equals("q")){ 
		try{
	
		int score= 0;

		SentimentAnalysis sentiment = new SentimentAnalysis(input.nextLine());

		score = sentiment.run();

		System.out.println("Score: " + String.valueOf(score));

		input = new Scanner(System.in);
		System.out.println("\nEnter sentence: ");

	}
	catch (IOException e) {
                e.printStackTrace();
            }

	}
}
}