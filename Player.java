
// Nupur Dave ncd2123
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
public class Player {
	
		
	private ArrayList<Card> hand; // the player's cards
	private double bankroll;
    private double bet;

	// you may choose to use more instance variables
	// create a player here		
	
	public Player(){		
	    hand = new ArrayList<Card>();
        bankroll = 50;

	}

    // add the card c to the player's hand
	public void addCard(Card c){
	    hand.add(c);
	}
    
    // remove the card c from the player's hand
	public void removeCard(Card c){
	    hand.remove(c);
    }
		
    // player makes a bet
    public void bets(double amt){
        bankroll = bankroll - amt;
        bet = amt;
    }

    //	adjust bankroll if player wins
    public void winnings(double odds){
            bankroll += odds;
    }

    // return current balance of bankroll
    public double getBankroll(){
            return bankroll;
    }

    //returns the player's hand
    public ArrayList<Card> getHand(){
        return hand;
    }
    
    //sorts the player's hand in inc order
    public void sortHand(){
        Collections.sort(hand);
    }
    
    //returns a specific card from the players hand
    public Card getCard(int n){
        return hand.get(n);
    }
    
    //displays the player's hand in the terminal
    public void displayHand(){
        System.out.println("Your hand is: ");
        for(Card c: getHand()){
            System.out.println(" " + c);
        }
        System.out.println();
    }
    
    //clears the player's hand
    public void resetHand(){
        hand.clear();
    }
}



