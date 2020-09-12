package game;

import java.util.Scanner;

public class blackjack {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	boolean game = true;
	int goAgain;
	int repeat = 0;
	
		
		do {
			cards start = new cards();
			start.PlayGame();
			Scanner playagain = new Scanner (System.in);
			System.out.println("\n1. Play again.\n2. Quit.\nEnter your choice: ");
			goAgain = playagain.nextInt(); //Reads user input.
			
			while (goAgain != 1 && goAgain != 2) { //ensures user enters either '1' or '2'.
				System.out.println("Try again.");
				Scanner userchoice = new Scanner (System.in);
				System.out.println("\n1. Play again.\n2. Quit.\nEnter your choice: ");
				goAgain = userchoice.nextInt();
			}
			
			if (goAgain == 1) {
				game = true; //if the value input is '1', the game plays again.
			}
			else if (goAgain == 2) {
				System.out.println("Thank you for playing blackjack.");
				game = false; //if the value input is '2', the game ends.
				break;
			}
				
			
		}
		while (game = true);
		
		
	}

}
