import java.util.Scanner;

public class TeamProjectPart2 {
	// starting checking and saving balance
	static Bank BankObject = new Bank(5000, 5000);

	public static void main(String[] args) {
		while (true) {
			// menu screen
			mainOptionScreen();
			Scanner in = new Scanner(System.in);
			if (!(in.hasNextInt())) {
				System.err.println("Not A Valid Option. Please Try Options 1-7!");
				in.nextLine();
				continue;
			}
			switch (in.nextInt()) {
			case 1:
				System.out.println("You Picked Option 1: Checking Balance");
				System.out.println(BankObject.checkingBalance);
				break;

			case 2:
				System.out.println("You Picked Option 2: Savings Balance");
				System.out.println(BankObject.savingBalance);
				break;

			case 3:
				System.out.println("You Picked Option 3: Deposit To Checking");
				System.out.println("Please enter the amount you want to deposit to your Checking Account: ");
				BankObject.deposit();
				System.out.println("Your current balance in your Checking Account is: $" + BankObject.checkingBalance);
				break;

			case 4:
				Scanner f = new Scanner(System.in);
				System.out.println("You Picked Option 4: Transfer From Checking To Savings");
				System.out.println("Please enter the amount you want to transfer to your Savings Account: ");
				BankObject.transferToSaving(f);
				System.out.println("Your current balance in your Savings Account is: $" + BankObject.savingBalance);
				System.out.println("Your current balance in your Checking Account is: $" + BankObject.checkingBalance);
				break;

			case 5:
				Scanner g = new Scanner(System.in);
				System.out.println("You Picked Option 5: Transfer From Savings To Checking");
				System.out.println("Please enter the amount you want to transfer to your Checking Account: ");
				BankObject.transferToChecking(g);
				System.out.println("Your current balance in your Savings Account is: $" + BankObject.savingBalance);
				System.out.println("Your current balance in your Checking Account is: $" + BankObject.checkingBalance);
				break;

			case 6:
				Scanner s = new Scanner(System.in);
				System.out.println("You Picked Option 6: Withdraw From Checking");
				System.out.println("Please enter the amount you want to withdraw from your Checking Account: ");
				BankObject.withdrawFromChecking(s);
				System.out.println("Your current balance in your Checking Account is: $" + BankObject.checkingBalance);
				break;

			case 7:
				Scanner d = new Scanner(System.in);
				System.out.println("You Picked Option 7: Withdraw From Savings");
				System.out.println("Please enter the amount you want to withdraw from your Savings Account: ");
				BankObject.withdrawFromSaving(d);
				System.out.println("Your current balance in your Savings Account is: $" + BankObject.savingBalance);
				break;

			default:
				System.err.println("Not A Valid Option. Please Try Options 1-7!");
				break;
			}
			
			in.nextLine();

			System.out.println("Would you like to make another transaction?");
			System.out.println("Press 1 for 'yes' and press 2 for 'no'");
			// check for invalid inputs at the end of the loop
			int input = 0;
			while (input != 1 && input != 2) {
				if (in.hasNextInt()) {
					input = in.nextInt();
					if (input == 1) {
					} else if (input == 2) {
						System.out.println("Thank you for using HTJL Bank.");
						break;
					} else {
						System.out.println("Invalid input. Please enter either '1' or '2'");
					}
					in.nextLine();
				} else {
					System.out.println("Invalid input. Please enter either '1' or '2'");
					in.nextLine();
				}
			}
			if (input == 2) {
				break;
			}
		}
	}

	public static void mainOptionScreen() {
		System.out.println(
				"(1: Checking Balance) (2: Savings Balance) (3: Deposit To Checking) (4: Transfer From Checking To Savings)"
						+ " (5: Transfer From Savings To Checking) (6: Withdraw From Checking) (7: Withdraw From Savings) ");

		System.out.print("Selection: ");
	}
}
