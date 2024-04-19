//Adding imports 
import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

public class MasterMind_Game {
	/**
	 * Student: Vishaal Gopalan
	 * Course: ICS3U
	 * Date: June 16, 2022
	 * Description of code: A variation of the game Mastermind in two differnet ways
	 */
	public static void main(String[] args) {
		
		//Setting up variables to run the game in the main
		boolean isGame = true;
		int tries = 4, length = 1;
		int level = 1;
		int setTries = 4;
		
		
		
		//creating/initializing the array that will store the secret code
		String actualCode[] = new String[length];
		
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------//
		//While loop which runs the whole game
		while (isGame) {
			
			int optionNum = mainMenu();
			
			//if statement if they want to play the classic Mastermind 
			if (optionNum == 1) {
				boolean isGame2 = true;
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println("Welcome to Mastermind, a game where the goal is to guess the secret code!");
				System.out.println("The possible letters to guess from are a, b , c and d to make it easier");
				System.out.println("Upon each guess, the player will receive feedback on what they have guessed:");
				System.out.println("\t" + ConsoleColors.GREEN +  "Green" + ConsoleColors.RESET + " → Player guessed the correct letter and in the correct location");
				System.out.println("\t" + ConsoleColors.YELLOW + "Yellow" + ConsoleColors.RESET +  "→ Player guessed the correct letter, but not in correct location");
				System.out.println("\t"  + "White" + " → Player guessed a letter not part of combination");
				System.out.println("---------------------------------------------------------------------------------");
				
				//While loop for repeating the game until user does not guess the correct code
				while (isGame2) {
					String combinationGuess[] = new String[length];
					
					actualCode = codeCombo(length);
				
	 
					for (int i = 0;i<combinationGuess.length;i++) {
						combinationGuess[i] = "-";
					}
					
				
					
					
					boolean winLose = gameBoard(combinationGuess, actualCode, length, tries, level, setTries);
					
					if (winLose) {
						length ++;
						tries ++;
						level ++;
						setTries ++;
					
					}
					
					if (winLose == false) {
						System.out.println("");
						System.out.println("Thank you for playing, you got to level " + level + " now you will be redirected to the home screen");
						System.out.println("");
						isGame2 = false;
					}
				
				}
					
					
				
			
			}
			
			//If the user wants to play Mastermind (Tic - Tac - Toe edition)
			else if (optionNum == 2 ) {
				char ticTacToeBoard[] = new char[9];
				char userGuessBoard[] = new char[9];
				
				//filling in the ticTacToe board with asterisks
				for (int i = 0;i<ticTacToeBoard.length;i++) {
					ticTacToeBoard[i] =  '*';
				}
				
				//filling in the userGuesses array with dashes
				for (int i = 0;i<userGuessBoard.length;i++) {
					userGuessBoard[i] =  '-';
				}
				ticTacToe(ticTacToeBoard, userGuessBoard);
			}
			
			//If the user wants to exit the game
			else if (optionNum == 3) {
				System.out.println("Goodbye, thank you for playing in Sweet Planet Games");
				isGame = false;
			}
			
		}
		

	}
	
	/**
	 * Displays main menu to the user
	 * Pre: Nothing
	 * Post: Returns option number from 1-3
	 */
	public static int mainMenu() {
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to Sweet Planet Games, an action - packed arcade where you can play an assortment of games");
		System.out.println("Today, you can play Mastermind (Wordle edition) or Mastermind (Tic-Tac-Toe edition)");
		System.out.println("To choose from the menu below, use the numbers 1 - 3");
		System.out.println("");
		System.out.println("Please pick from one of the options below:");
		System.out.println("1. Mastermind");
		System.out.println("2. Tic - Tac - Toe (Mastermind Edition)");
		System.out.println("3. Exit");
		int choice = input.nextInt();
		return choice;
		
	}
	
	
	
	
	/**
	 * Generates the code for user to guess
	 * Pre: the length of the code
	 * Post: Returns the secret combination 
	 */
	public static String[] codeCombo(int length) {
		Random rand = new Random();
		String[] secretComb = new String[length];
		for (int i = 0;i<secretComb.length;i++) {
			char letter = (char) (rand.nextInt(4) + 'a');
			secretComb[i] = Character.toString(letter); //fix this later if have time
		}
		
		return secretComb;
		
		
	}
	
	/**
	 * Displays the game and checks whether user is right or wrong
	 * Pre: User's guess array, secret combination array, length of both arrays, the number of tries, the level
	 * Post: Returns true if user guessed the secret code, and false if user did not 
	 */
	public static boolean gameBoard(String array[], String array2[],int length, int tries, int level, int actualTries) {
		
		System.out.println(ConsoleColors.WHITE_UNDERLINED + "Level " + ConsoleColors.WHITE_UNDERLINED + level);
		System.out.print(ConsoleColors.RESET);
		System.out.print("Tries: " + ConsoleColors.WHITE_BOLD + actualTries);
		System.out.println(ConsoleColors.RESET);
		System.out.print("Length: " + ConsoleColors.WHITE_BOLD + length);
		System.out.println(ConsoleColors.RESET);
		Scanner input = new Scanner(System.in);
		String combination = "";
		
		boolean toCheck = false;
		int counters = 0;
		while (tries != 0) {
			
			do {
					counters = 0;
					System.out.println("-----------------------------------------");
					System.out.println("Enter a combination followed by a space:");
					combination = input.nextLine();
					combination = combination.replaceAll(" ", "");
					for (int i = 0;i<combination.length();i++) {
						if (combination.charAt(i) == 'a' || combination.charAt(i) == 'b' || combination.charAt(i) == 'c' || combination.charAt(i) == 'd') {
							counters = 0;
	
						}
						else {
							counters = 1;
							break;
						}
						
					}
			
					
					
					if (counters == 0 && combination.length() == length) {
						counters = 0;
						break;
					}
					else if (counters == 0 && combination.length() != length) {
						System.out.println("Invalid Statement");
						counters = 1;
					}
					
					
			}while(counters != 0);
			
				
			
			
			for (int i = 0;i<combination.length();i++) {
				array[i] = Character.toString(combination.charAt(i));
			}
			
			toCheck = true;
			
			
			
			if (toCheck) {
				int numRightLetters = 0;
				System.out.println("");
				System.out.println(ConsoleColors.WHITE_BOLD + "Feedback:");
				System.out.print(ConsoleColors.RESET);
				for (int i = 0;i<array.length;i++) {
					if (array[i].equals(array2[i])) {
						System.out.print(ConsoleColors.GREEN + array[i] + " ");
						System.out.print(ConsoleColors.RESET + "");
						numRightLetters ++;
					}
					else if (array[i].equals(array2[i]) == false && Arrays.stream(array2).anyMatch(array[i] :: equals)){
						System.out.print(ConsoleColors.YELLOW + array[i]+ " ");
						System.out.print(ConsoleColors.RESET + "");
					}
					else if (!(array[i].equals(array2[i]) && Arrays.stream(array2).anyMatch(array[i] :: equals))) {
						System.out.print(array[i]+ " ");
					}
				}
				System.out.println("");
				System.out.print("-----------------------------------------");
				System.out.println("");
				
				if (numRightLetters == array2.length) {
					return true;
				}
				
				else {
					tries --;
					
				}
				System.out.println("Tries: " + ConsoleColors.WHITE_BOLD + tries);
				System.out.print(ConsoleColors.RESET);
				
				
			}
	
		}
		if (tries == 0) {
			return false;
		}
		return toCheck;
		
		
		
	}
	
	/**
	 * Displays the TicTacToe board and checks if the user's guesses are right
	 * Pre: The ticTacToe board array and the user's guess array
	 * Post: N/A
	 */
	public static void ticTacToe(char ticTacBoard[],char userBoard[]) {
		Random rand = new Random();
		Scanner input = new Scanner(System.in);
		boolean threeInARow = false;
		boolean isLegal = false;
		boolean alrIn = true;
		int place = 0;
		int counter = 0;
		int tries = 5;
	
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("Welcome to Tic - Tac - Toe, a game where the goal is to guess the three locations");
		System.out.println("where the pattern may be, they may not be in order");
		System.out.println("The player who gets 3 in a row in any direction is the winner");
		System.out.println("You will have 5 tries to guess the pattern");
		System.out.println("---------------------------------------------------------------------------------");
		
		
	
		for (int i = 0;i<3;i++) {
			int num = rand.nextInt(9);
			if (ticTacBoard[num] == 'X') {
				ticTacBoard[rand.nextInt(9)] = 'X';
			}
			else {
				ticTacBoard[num] = 'X';
			}
		}
		
		
	
		
		
		while(tries != 0) {
			
			System.out.println("+---+---+---+");
	        System.out.println("| " + userBoard[0] + " | " + userBoard[1] + " | " + userBoard[2] + " |");
	        System.out.println("+---+---+---+");
	        System.out.println("| " + userBoard[3] + " | " + userBoard[4] + " | " + userBoard[5] + " |");
	        System.out.println("+---+---+---+");
	        System.out.println("| " + userBoard[6] + " | " + userBoard[7] + " | " + userBoard[8] + " |");
	        System.out.println("+---+---+---+");
	        System.out.println("Tries:" + tries);

		
			do {
				System.out.println("Pick a number from 0-8 to guess:");
				place = input.nextInt();
				if (place > 8) {
					System.out.println("Invalid Statement");
				}
				else if (place >= 0 && place <= 8){
					isLegal = true;
				}
			}while (!isLegal);
			
			
			
			do {
				if (isLegal && userBoard[place] == 'X') {
					System.out.println("You already put it in that positon");
					System.out.println("Pick a number from 0-8 to guess:");
					place = input.nextInt();
				}
				else {
					alrIn = false;
				}
			}while(alrIn);
			
			if (!alrIn) {
				userBoard[place] = 'X';
				
				
				for (int i = 0;i<9;i++) {
					if (userBoard[i] == ticTacBoard[i]) {					
						counter ++;
			
					}
				}
				if (counter == 3) {
					threeInARow = true;
					tries = 0;
				}
				else {
					tries --;
					counter = 0;
				}
			
			
			}
			
		}
		
		
		if (threeInARow) {
			System.out.println("You guessed the pattern good job");
			System.out.println("");
			System.out.println("Pattern:");
			System.out.println("+---+---+---+");
	        System.out.println("| " + ticTacBoard[0] + " | " + ticTacBoard[1] + " | " + ticTacBoard[2] + " |");
	        System.out.println("+---+---+---+");
	        System.out.println("| " + ticTacBoard[3] + " | " + ticTacBoard[4] + " | " + ticTacBoard[5] + " |");
	        System.out.println("+---+---+---+");
	        System.out.println("| " + ticTacBoard[6] + " | " + ticTacBoard[7] + " | " + ticTacBoard[8] + " |");
	        System.out.println("+---+---+---+");
	        
	        System.out.println("");
			

		}
		
		else if (tries == 0) {
			System.out.println("You did not guess the pattern, try again next time");
			System.out.println("");
			System.out.println("Pattern:");
			System.out.println("+---+---+---+");
	        System.out.println("| " + ticTacBoard[0] + " | " + ticTacBoard[1] + " | " + ticTacBoard[2] + " |");
	        System.out.println("+---+---+---+");
	        System.out.println("| " + ticTacBoard[3] + " | " + ticTacBoard[4] + " | " + ticTacBoard[5] + " |");
	        System.out.println("+---+---+---+");
	        System.out.println("| " + ticTacBoard[6] + " | " + ticTacBoard[7] + " | " + ticTacBoard[8] + " |");
	        System.out.println("+---+---+---+");
			
			System.out.println("");
		}
		
		
		
		
		
				
				
				
		
		
		
		
		
	}
	

		
	
	
	

		
	}

