
// add your own banner here
import java.util.Collections;

public class Card implements Comparable<Card>{
	
	private int suit; // use integers 1-4 to encode the suit
	private int rank; // use integers 1-13 to encode the rank
	
    //make a card with suit s and rank r
    //@param is suit and rank 
	public Card(int s, int r){
        suit = s;
        rank = r;
    }
    
    //make's a card with a rank and suit set to 0
    public Card(){
        suit = 0;
        rank = 0;
    }

    //compares two cards based on their suit and rank
    public int compareTo(Card c){
        if(rank == c.rank){
            if(suit == c.suit){
                return 0;
            }
            else if(suit > c.suit){
                return 1;
            }
            else{
                return -1;
            }
        }
        else{
            if(rank == c.rank){
                return 0;
            }
            else if(rank > c.rank){
                return 1;
            }
            else{
                return -1;
            }
        }
        
	}
    
    //returns the rank of a specific card
    public int getRank(){
        return rank;
    }
    
    //returns the suit of a specific card
    public int getSuit(){
        return suit;
    }
    
    //This method returns the name of a specific card by turning
    //the numbered suit and rank into a string.
    public String toString(){
        String r = "";
        String s = "";
        switch(rank){
            case 1: r = "Ace"; break;
            case 2: r = "Two"; break;
            case 3: r = "Three"; break;
            case 4: r = "Four"; break;
            case 5: r = "Five"; break;
            case 6: r = "Six"; break;
            case 7: r = "Seven"; break;
            case 8: r = "Eight"; break;
            case 9: r = "Nine"; break;
            case 10: r = "Ten"; break;
            case 11: r = "Jack"; break; 
            case 12: r = "Queen"; break;
            case 13: r = "King"; break;
        }
        
        switch(suit) {
            case 1: s = " of Clubs"; break;
            case 2: s = " of Diamonds"; break;
            case 3: s = " of Hearts"; break;
            case 4: s = " of Spades"; break;
        }
        return r + s; //returns the name of the card
    }
	
}
