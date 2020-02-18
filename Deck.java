import java.util.Random;
import java.util.ArrayList;
import java.util.Collections; //can we implement collections here?
// add your own banner here


public class Deck{
	
	Card[] cards = new Card[52]; //creates a new array of size 52
	private int top = 0; // the index of the top of the deck
    int cardCount = 0;
	
    //This method instantiates a deck by assiging each card a suit and rank
    //so it has all the combinations of suits and ranks
	public Deck(){
        for(int suit = 1; suit <= 4; suit++){
            for(int rank=1; rank<= 13; rank++){
                 cards[cardCount] = new Card(suit,rank);
                 cardCount++;
            }
        }
    }
    
    //this method shuffles the cards by picking a random location in the deck
    //and switching the cards using a temp card c. It does this 52 times and
    //moves each card at least once
	public void shuffle(){
		// shuffle the deck here
		int temp = 0;
        Card c = new Card();
		Random r = new Random();
        for(int i = 0; i < cards.length-1; i++){
            int randLoc = r.nextInt(52);
            c = cards[randLoc];
            cards[randLoc] = cards[i];
            cards[i] = c;
        }
	}
	
    //This returns the card on the top of the deck
	public Card deal(){
		//deal the top card in the deck
		Card a = cards[top];
        top++;
		return a;
	}
	
	// this resets the top and sets it equal to 0
	public void reset(){
        top = 0;
        shuffle();
    }

}

