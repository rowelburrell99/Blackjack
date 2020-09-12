package game;

import java.util.Scanner;
import java.util.Random;

public class cards {
	String username;
	int dealerhandsize;
	int userhandsize;
	int dealerhandsum;
	int userhandsum;
	int choice;
	int[] userhand = new int[21];
	int[] dealerhand = new int[21];
	boolean userfinished = false;
	boolean dealerfinished = false;
	
	
	public void PlayGame() {
		Scanner name = new Scanner (System.in);
		System.out.println("Enter your name: ");
		username = name.next();
		
		System.out.println("\nWelcome to my blackjack game, " + username + ".");
		System.out.println("The aim of the game is to reach, or get as close as possible to the number 21.");
		System.out.println("If you go beyond 21, you lose.");
		System.out.println("\nStart:\n--------------------------------------------------\n");
	
		addUserCard();
		addUserCard();
		addDealerCard();
		addDealerCard();
		printHand();
		sumHands();
		
		if (dealerhandsum == 21) {
			System.out.println("Dealer has blackjack. Dealer wins.\n");
			return;
		}
		else if (userhandsum == 21) {
			System.out.println(username + " has blackack." + username + " wins.\n");
			return;
		}
		else if (dealerhandsum > 21) {
			System.out.println("The dealers hand has exceeded 21. The dealer has lost. " + username + " is the winner.\n");
			return;
		}
		else if (userhandsum > 21) {
			System.out.println(username + " hand exceeds 21." + username + " has lost.\n");
			return;
		}
		
		while (dealerfinished == false || userfinished == false) { //loops the game until either changes.
			if (userfinished == false) { //if the user is not finished, it will execute these statements.
				Scanner userchoice = new Scanner (System.in);
				System.out.println("\n1. Hit.\n2. Stand.\nEnter your choice: ");
				choice = userchoice.nextInt(); //user enters 1 or 2, and their input is saved to the variable 'choice'.
				
				switch (choice) {
					case 1:
						dealerfinished = false;
						userfinished = false;
						addUserCard(); // draws another card.
						if (dealerhandsum < 19) {
							addDealerCard(); //dealer draws if their hand total is under 19.
						}
						printHand(); //print value of the cards.
						sumHands(); //add all of the users numbers and totals it.
						WinCondition(); //check the win conditions.
						
						if (userhandsum > 21) {
							System.out.println("\n" + username + "s hand exceeds 21. " + username + " has lost.");
							return;
						}
						break;
					case 2: //if the choice is 2 (Stand)
						dealerfinished = false;
						userfinished = true; //user is finished and will no longer be able to draw cards.
						if (dealerhandsum > userhandsum) {
							dealerfinished = true;
							WinCondition(); //if the dealers current hand is more than the users, dealer wins.
						}
						break;
					default:
						dealerfinished = true;
						System.out.println("\nTry again."); //if user enters a value outside of 1 or 2.
						break;
				}
			}
			else if (dealerhandsum < 19 && dealerfinished == false) { //if the dealers hand total is less than 19 and is not finished.
				addDealerCard(); //dealer draws card.
				printHand(); //displays the dealers cards.
				sumHands(); //totals the sum of the dealers hands.
				WinCondition(); //Checks win conditions.
			}
			else if (dealerhandsum >= 19) { //dealer stops 'hitting' if hand total is more than or equal to 19.
				dealerfinished = true;
				WinCondition();
				return;
			}
		}
	}
	
	public void addUserCard() {
		Random rand = new Random();
		
		if (userhandsize <= 21) { //user can't have more than 21 cards.
			userhand[userhandsize] = 1 + (rand.nextInt(10) % 11); // Random value between 1 and 11.
			userhandsize++; //everytime a card is added it is stored in the array and takes up a slot.
		}
		else {
			System.out.println("\nYou have the maximum number of cards (21).");
			userfinished = true;
		}
	}
	
	public void addDealerCard() {
		Random rand = new Random();
		
		if (dealerhandsize <= 21) {
			dealerhand[dealerhandsize] = 1 + (rand.nextInt(10) % 11);
			dealerhandsize++;
		}
		else {
			dealerfinished = true;
		}
	}
	
	public void printHand() {
		for (int i = 0; i < userhandsize; i++) {
			System.out.println(username + " cards: [ " + userhand[i] + " ]  \n"); //displays users 'cards'.
		}
		for (int j = 0; j < dealerhandsize; j++) {
			System.out.println("Dealers cards: [ " + dealerhand[j] + " ]  \n"); //displays dealers 'cards'.
		}
		
	}
	
	public void sumHands() {
		userhandsum = 0;
		dealerhandsum = 0;
		
		for (int i = 0; i < userhandsize; i++) { 
			userhandsum += userhand[i]; //the card values get added to the sum variable.
		}
		
		for (int j = 0; j < dealerhandsize; j++) {
			dealerhandsum += dealerhand[j];
		}
		
		System.out.println(username + "s hand total is: " + userhandsum);
		System.out.println("Dealers current hand total is: " + dealerhandsum);
	}
	
	public void WinCondition() {
		if (userhandsum == 21 && dealerhandsum == 21) {
			System.out.println("\nBoth " + username + "s and the dealer have reached 21. The game is a draw.\n");
			userfinished = true;
			dealerfinished = true;
			return;
		}
		else if (userhandsum == 21) {
			System.out.println("\n" + username + "s has reached 21! " + username + " is the winner!\n" );
			userfinished = true;
			dealerfinished = true;
			return;
		}
		else if (dealerhandsum == 21) {
			System.out.println("\nThe dealer has reached 21. The dealer is the winner. Better luck next time!\n");
			userfinished = true;
			dealerfinished = true;
			return;
		}
		else if (dealerhandsum > 21 && userhandsum > 21) {
			System.out.println("\nThe dealer has a total of: " + dealerhandsum + " wthe total of your hand is: " + userhandsum + ", Both have lost.\n");
			userfinished = true;
			dealerfinished = true;
			return;
		}
		else if  (dealerhandsum > 21) {
			System.out.println("\nDealers hand has exceeded 21. The dealer has lost.");
			userfinished = true;
			dealerfinished = true;
			return;
		}
		else if ((userfinished == true && dealerfinished == true) || (userhandsize == 21 && dealerhandsize == 21)) {
			if (userhandsum < dealerhandsum) {
				System.out.println("\nThe total of your hand is lower than the dealers hand of " + dealerhandsum + ", You lose.\n");
				userfinished = true;
				dealerfinished = true;
				return;
			}
			else if (dealerhandsum == userhandsum) {
				System.out.println("\nThe dealer has a total of: " + dealerhandsum + " which is equal to the total of your hand at: " + userhandsum + ", Tie game.\n");
				userfinished = true;
				dealerfinished = true;
				return;
			}
			else if (userhandsum > dealerhandsum) {
				System.out.println("\nSum of " + username + "s hand exceeds the dealers hand sum of " + dealerhandsum + ", You win!\n");
				userfinished = true;
				dealerfinished = true;
				return;
			}
		}

	}
}
