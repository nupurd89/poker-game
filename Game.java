
// add your own banner here

import java.util.Scanner;
import java.util.ArrayList;

public class Game {
	
	private Player p;
	private Deck cards;
    ArrayList<Card> hand = new ArrayList<>();
	// you'll probably need some more here
	
	
	public Game(String[] testHand){
		// This constructor is to help test your code.
		// use the contents of testHand to
		// make a hand for the player
		// use the following encoding for cards
		// c = clubs
		// d = diamonds
		// h = hearts
		// s = spades
		// 1-13 correspond to ace-king
		// example: s1 = ace of spades
		// example: testhand = {s1, s13, s12, s11, s10} = royal flush
		
        
        //Makes a new player object and deck object 
		p = new Player();
        cards = new Deck();

        int rank = 0;
        
        //reads the first letter (char) of each input in the args[] array
        //and assings each one a suit
        
        for(String input: testHand){
            char s = input.charAt(0);
            int suit=0;
            switch(s){
                case 'c': 
                    suit = 1; break;
                case 'd':
                    suit = 2; break;
                case 'h':
                    suit = 3; break;
                case 's':
                    suit = 4; break;
                    
            }
        
        //assigns the card a value by reading the substring of the input and
        //turning it into an int
        rank = Integer.parseInt(input.substring(1));
          
        //creates a new card with the suit and rank assigned and adds
        //it to the players hand.
        Card c = new Card(suit, rank);
        p.addCard(c);
            
        }
	}
	
    // This no-argument constructor is to actually play a normal game
    // Creates a new Deck object and player object
	public Game(){
		cards = new Deck();
        p = new Player();
		
	}
	
	public void play(){
		// this method should play the game	
		
        boolean gameplay = true;
        boolean cont = true;
        
        //Instantiates a new Scanner object
        Scanner in = new Scanner(System.in);
        
        //while the player wants to play, the game keeps looping so you can 
        //play multiple rounds. The boolean gameplay is only turned off when
        //the player says they don't want to play anymore. 
        
        while(gameplay){
            
            boolean rightdigit = true;
            
            System.out.println("Do you want to play this round? (y/n)");

            
            //this loop ensures the player typed a y/n, or repeats the question
            while(rightdigit){
                String input = in.nextLine();
            
                if(input.equals("n")){
                    rightdigit = false;
                    gameplay = false;
                    break;
                }
            
                else if(input.equals("y")){
                    rightdigit = false;
                    gameplay = true;
                    break;
                    
                }
            
                else{
                    System.out.println("Please enter y or n.");
                 
                }
                
                
            }
            

            
            //checks if player has enough tokens to place a bet
            if(p.getBankroll() <= 0){ 
                System.out.println("You don't have enough tokens" +
                                   "to play this game");
                gameplay = false;
                
            }
            
            //if the player typed 'n', the game ends
            if(gameplay == false){
                    break;
            }
            
            cards.shuffle();
            
            //Reads the number of tokens the user wants to bet
            System.out.println("How many tokens do you want to bet?");
            int tokens = in.nextInt();
            

            //Checks if the tokens the player entered is between one and five.
            //Otherwise, it keeps prompting the user to enter a correct number.
            while(tokens < 1 || tokens > 5){
               System.out.println("You have entered an invalid "
                                  + "amount of tokens, please try again.");
               tokens = in.nextInt();
            }
            
            //deals 5 cards if the size of the hand is 0, which would happen
            //if the game is constructed without an String args parameter.
            if(p.getHand().size() == 0){
                for(int i = 0; i < 5; i++){
                    cards.shuffle();
                    dealToPlayer();
                }
            }
            
            //Sorts hand and prints it in terminal
            p.sortHand();
            p.displayHand();
            
            //Asks if you want to reject cards and takes string input
            boolean bool = true;
            System.out.println("Do you want to reject any cards?");
            String answer = in.nextLine();
            
            //If the player doesn't want to reject cards, skips the next loop
            if(answer == "n"){
                bool = false;
            }
            
            //Asks the player which cards they want to reject and subtracts
            //1 from it so instead of going from 1 to 5, the numbers go to 0
            //to 4 correlating with the placements in the array. 
            int i = 0;
            while(bool){
                System.out.println("Which card do you want to reject"+
                                   "[1,2,3,4,5] (or type 0 for done)?");

                int removedcard = in.nextInt() - 1;
                
                //If the player typed in 0 for 'done', then the program
                //displays the hand and evaluates the score.
                if(removedcard == -1){
                    bool = false;
                }
                else{
                    
                    //This ensures that the player entered a valid number
                    //between 1-5 (positions 0-4 in the arrayList.) 
                    if(removedcard > 4 || removedcard < 0){
                        System.out.println("You have entered an invalid" +
                                           "number, please try again.");
                        continue;
                          
                    }
                    //This ensures that the player can't reject a card that has
                    //already been exchanged. If the player rejected two (i=2)
                    //cards, then the last two cards in the list will be the 
                    //new cards. So, the player can't type 4 or 5
                    //into the terminal. 
                    else if(removedcard > 4 - i){
                        System.out.println("You cannot remove a card that" +
                                           "has already been exchanged." 
                                           + "Please try again.");
                    }

                    
                    //If the player entered a correct number, that card is
                    //removed from the deck and replaced with another card.
                    // Then, the hand is displayed again. 
                    else{
                        p.removeCard(p.getCard(removedcard));
                        p.addCard(cards.deal());
                        p.displayHand();
                        i++;
                    }
                }
                
            }
            //This checks the hand and prints out what the hand evaluated to
            String score = checkHand(p.getHand());
            int payout = (recievePayout(score))*tokens;
            System.out.println("You got a " + score + "!! You won " + 
                               payout + " tokens!");
            p.winnings(payout);
            
            //This checks if you have enough tokens left to play another round
            System.out.println("You have " + p.getBankroll() + " tokens left.");
            if(p.getBankroll() <= 0){
                System.out.println("You have no more tokens left to play.");
            }
            //resets player's hand for the next round
            p.resetHand();
        }
        
	}
	
    //This method deals cards to the player by shuffling the deck, then creating
    //a card called 'dealt' which is set equal to a random card in the deck.
    //There is a for loop that checks if a card in the hand that 
    //is equal to card 'dealt' by invoking the compareTo method from Card class.
    //Then it adds that card to the hand if there are no repeats.
    public void dealToPlayer(){
        Card dealt = null;
        cards.shuffle();
        boolean notrepeat = true;
        while(notrepeat){
            dealt = cards.deal();
            notrepeat = false;
            for(int i = 0; i < p.getHand().size(); i++){
                Card c = p.getCard(i);
                if(c.compareTo(dealt) == 0){
                    notrepeat = true;
                }
            }
        }
        p.addCard(dealt);
    }
    
    // this method takes an ArrayList of cards
    // as input and then determine what evaluates to and
	// return that as a String
	public String checkHand(ArrayList<Card> hand){
		
        int matches = 0;
        String result = "";    
        
        p.sortHand();
        
        //This loop checks how many matches there are by going through 
        //each card and checking if the rank matches any other card's
        //rank. 
        for(int i = 0; i < hand.size()-1; i++){
            if(hand.get(i).getRank() == hand.get(i+1).getRank()){
                matches++;
            }
        }
        
        //If there is one match, there is only a single pair
        if(matches == 1){
            result = "One Pair";
        }
            
        //If there are two matches, there is either a two pair
        //or three of a kind. To check which one it is, we
        //invoke the checkPairs method and pass in the player's hand.
        int rankmatch = 0;
        if(matches == 2){
            if(checkPairs(hand) == 2){
                result = "Two Pairs";
            }
            else{
                result = "Three of a Kind";
            }
                
        }
        
        //If there are three matches, there is either a full house
        //or a four of a kind. To check which one it is, we
        //invoke the checkPairs method and pass in the player's hand.
        if(matches == 3){
            if(checkPairs(hand) == 3){
                result = "Four of a Kind";
            }
            else{
                result = "Full House";
            }
            
        }
        
        //This checks if the suits of all the cards in the hand are equal
        boolean suitequal = false;
            for(int i = 0; i < hand.size()-1; i++){
                if(hand.get(i).getSuit() == hand.get(i+1).getSuit()){
                    suitequal = true;
                }
                else{
                    suitequal = false;
                    break;
                }
            }
        
        
        //This checks if the ranks in the cards are inc by one
        boolean incbyone = false;
        for(int i = 0; i < hand.size() - 1; i++){
            if(hand.get(i).getRank() == (hand.get(i+1).getRank() -1)){
                incbyone = true;
            }
            else{
               incbyone = false;
                break;
            }
        }
        
        //If both conditions are true, then it is a straight flush.
        //Otherwise, it is one or the other
        if(suitequal == true && incbyone == true){
            result = "Striaght Flush";
        }
        else if(suitequal == true){
            result = "Flush";
        }
        else if(incbyone == true){
            result = "Straight";
        }
        
        //This checks if it is a Royal Flush by checking if the suits
        //are all the same and the ranks are 1,10,11,12, and 13
        if(hand.get(0).getRank() == 1 && hand.get(1).getRank() == 10 &&
           hand.get(2).getRank() == 11 && hand.get(3).getRank() == 12 &&
           hand.get(4).getRank() == 13 && suitequal == true){
            result = "Royal Flush";
        }
        
        return result;   

    }
    
    //Checks how many pairs there are in a hand
    public int checkPairs(ArrayList<Card> hand){
        int cardCount = 0;
        for(int i = 0; i < hand.size(); i++){
            Card card1 = hand.get(i);
            int rank1 = card1.getRank();
            for(int j = i+1; j < hand.size(); j++){
                Card card2 = hand.get(j);
                int rank2 = card2.getRank();
                if(rank1 == rank2){
                    cardCount++;
                }
            }
        }
        return cardCount;
    }
    
    //Takes in the string result and uses an if/else statement
    //to assign a payout value. Then, it returns the payout value.
    public int recievePayout(String result){
        int payout = 0;
        if(result == "One Pair"){
            payout = 1;
        }
        else if(result == "Two Pairs"){
            payout = 2;
        }
        else if(result == "Three of a Kind"){
            payout = 3;
        }
        else if(result == "Four of a Kind"){
            payout = 25;
        }
        else if(result == "Full House"){
            payout = 6;
        }
        else if(result == "Straight Flush"){
            payout = 50;
        }
        else if(result == "Straight"){
            payout = 4;
        }
        else if(result == "Flush"){
            payout = 5;
        }
        else if(result == "Royal Flush"){
            payout = 250;
        }
        else{
            payout = 0;
        }
        return payout;
    }

    
}
