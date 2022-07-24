import java.util.Scanner;

public class Bank {
	double checkingBalance;
	double savingBalance;

	Bank(double initialCheckingBalance, double initialSavingBalance) {
		checkingBalance = initialCheckingBalance;
		savingBalance = initialSavingBalance;
	}
	// outputs checking balance
	public double checkingBalance(double initialCheckingBalance) {
		checkingBalance = initialCheckingBalance;
		return checkingBalance;

	}
	// deposits to checking account
	public double deposit() {
		Scanner input = new Scanner(System.in);
		int i = 0;
		while (i == 0) {
			if (!(input.hasNextDouble())) {
				System.out.println("Invalid input. Please enter the numeric value you wish to deposit.");
				input.nextLine();
			} else {
				checkingBalance += input.nextDouble();
				i++;
			}
		}
		return checkingBalance;
	}
	// withdrawals from checking account
	public double withdrawFromChecking(Scanner s) {
		int i = 0;
		while (i == 0) {
			if (s.hasNextDouble()) {
				double input = s.nextDouble();
				if (input < checkingBalance) {
					checkingBalance -= input;
					i++;
					break;
				} else {
					System.out.println("Insufficient funds.");
					System.out.println("Please enter the amount you would like to withdraw again.");
				}
			} else {
				System.out.println("Invalid input. Please enter the numeric value you wish to withdraw.");
				s.nextLine();
			}
		}
		return checkingBalance;
	}
	// withdrawals from savings account
	public double withdrawFromSaving(Scanner d) {
		int i = 0;
		while (i == 0) {
			if (d.hasNextDouble()) {
				double input = d.nextDouble();
				if (input < savingBalance) {
					savingBalance -= input;
					i++;
					break;
				} else {
					System.out.println("Insufficient funds.");
					System.out.println("Please enter the amount you would like to withdraw again.");
				}
			} else {
				System.out.println("Invalid input. Please enter the numeric value you wish to withdraw.");
				d.nextLine();
			}
		}
		return savingBalance;
	}
	// transfers from checking to savings account
	public double transferToSaving(Scanner f) {
		int i = 0;
		while (i == 0) {
			if (f.hasNextDouble()) {
				double input = f.nextDouble();
				if (input <= checkingBalance) {
					checkingBalance -= input;
					savingBalance += input;
					i++;
					break;
				} else {
					System.out.println("Insufficient funds to transfer.");
					System.out.println("Please enter the amount you would like to transfer again.");
				}
			} else {
				System.out.println("Invalid input. Please enter the numeric value you wish to transfer.");
				f.nextLine();
			}
		}
		return savingBalance;
	}
	// transfers from savings to checking account
	public double transferToChecking(Scanner g) {
		int i = 0;
		while (i == 0) {
			if (g.hasNextDouble()) {
				double input = g.nextDouble();
				if (input <= savingBalance) {
					checkingBalance += input;
					savingBalance -= input;
					i++;
					break;
				} else {
					System.out.println("Insufficient funds to transfer.");
					System.out.println("Please enter the amount you would like to transfer again.");
				}
			} else {
				System.out.println("Invalid input. Please enter the numeric value you wish to transfer.");
				g.nextLine();
			}
		}
		return checkingBalance;
	}
}