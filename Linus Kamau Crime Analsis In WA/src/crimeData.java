import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/*
 * Group Project: Washington Crime Data Analysis
 * Team DVLD: David Bessex, Vy Nguyen, Linus Kamau, David Hohn
 */

public class crimeData {

	// map each year to its set of counties
	private Map<String, Set<Integer>> countyWithSetYears = new HashMap<>();
	private static Stack<String> listOfCounties = new Stack<>();
	private static Map<String, Map<Integer, Integer>> yearlyPopulation = new HashMap<>();
	private static Map<String, Map<Integer, Integer>> yearlyAssault = new HashMap<>();
	private static Map<String, Map<Integer, Integer>> yearlyArson = new HashMap<>();
	private static Map<String, Map<Integer, Integer>> yearlyBurglary = new HashMap<>();
	private static Map<String, Map<Integer, Integer>> yearlyDrugs = new HashMap<>();
	private static Map<String, Map<Integer, Integer>> yearlyMurder = new HashMap<>();
	private static Map<String, Map<Integer, Integer>> yearlyTheft = new HashMap<>();
	private static Map<String, Map<Integer, Integer>> yearlyRape = new HashMap<>();
	private static Map<String, Map<Integer, Integer>> yearlyRobbery = new HashMap<>();
	private static Map<String, Map<Integer, Integer>> yearlyTotalCrime = new HashMap<>();
	private static Set<String> counties = new TreeSet<>();

	private Map<String, Map<Integer, Map<String, Integer>>> yearWithMostCrime = new HashMap<>();
	private static String[] crimeTypes = new String[] { "population", "assault", "arson", "burglary", "drugs", "murder",
			"theft", "rape", "robbery", "total crime" };

	static int sizeOfStack = 0; // Stack size of counties for user
	static int compareYearOne;
	static int compareYearTwo;
	static String county;

	public crimeData(Scanner file) {
		// discard the header
		file.nextLine();
		// loop through each row in the csv file

		while (file.hasNextLine()) {
			/*
			 * Vy Nguyen
			 */
			// read the line
			String line = file.nextLine();
			// split the line into an array of strings
			String[] row = line.split(",");
			// index 0: year
			int year = Integer.parseInt(row[0]);
			// index 1: county
			String county = row[1];
			// index 2: population
			int population = Integer.parseInt(row[2]);
			// index 3: assault
			int assault = Integer.parseInt(row[3]);
			// index 4: arson
			int arson = Integer.parseInt(row[4]);
			// index 5: burglary
			int burglary = Integer.parseInt(row[5]);
			// index 6: Drugs violation
			int drug = Integer.parseInt(row[6]);
			// index 7: murder
			int murder = Integer.parseInt(row[7]);
			// index 8: theft
			int theft = Integer.parseInt(row[8]);
			// index 9: rape
			int rape = Integer.parseInt(row[9]);
			// index 10: robbery
			int robbery = Integer.parseInt(row[10]);
			// index 11: total
			int total = Integer.parseInt(row[11]);
			/*
			 * David Hohn
			 */
			if (yearWithMostCrime.containsKey(county)) {
				Map<Integer, Map<String, Integer>> countyData = yearWithMostCrime.get(county);
				Map<String, Integer> crimeData = new HashMap<>();
				for (int i = 0; i < crimeTypes.length; i++) {
					crimeData.put(crimeTypes[i], Integer.parseInt(row[i + 2]));
				}
				countyData.put(year, crimeData);
			} else {
				Map<Integer, Map<String, Integer>> countyData = new HashMap<>();
				Map<String, Integer> crimeData = new HashMap<>();
				for (int i = 0; i < crimeTypes.length; i++) {
					crimeData.put(crimeTypes[i], Integer.parseInt(row[i + 2]));
				}
				countyData.put(year, crimeData);
				yearWithMostCrime.put(county, countyData);
			}
			/*
			 * David Bessex
			 */
			counties.add(county);
			// have we seen this county before?
			if (countyWithSetYears.containsKey(county)) {
				Set<Integer> years = countyWithSetYears.get(county);
				years.add(year);
			} else {
				Set<Integer> years = new HashSet<>();
				years.add(year);
				countyWithSetYears.put(county, years);
			}
			// adding county mapping to year and population
			if (!yearlyPopulation.containsKey(county)) {
				yearlyPopulation.put(county, new HashMap<>());
				Map<Integer, Integer> temp = yearlyPopulation.get(county);
				temp.put(year, population);
			} else if (yearlyPopulation.containsKey(county)) {
				if (!yearlyPopulation.get(county).containsKey(year)) {
					Map<Integer, Integer> temp = yearlyPopulation.get(county);
					temp.put(year, population);
				}
			}
			// adding county mapping to year and assault
			if (!yearlyAssault.containsKey(county)) {
				yearlyAssault.put(county, new HashMap<>());
				Map<Integer, Integer> temp = yearlyAssault.get(county);
				temp.put(year, assault);
			} else if (yearlyAssault.containsKey(county)) {
				if (!yearlyAssault.get(county).containsKey(year)) {
					Map<Integer, Integer> temp = yearlyAssault.get(county);
					temp.put(year, assault);
				}
			}
			// adding county mapping to year and Arson
			if (!yearlyArson.containsKey(county)) {
				yearlyArson.put(county, new HashMap<>());
				Map<Integer, Integer> temp = yearlyArson.get(county);
				temp.put(year, arson);
			} else if (yearlyArson.containsKey(county)) {
				if (!yearlyArson.get(county).containsKey(year)) {
					Map<Integer, Integer> temp = yearlyArson.get(county);
					temp.put(year, arson);
				}
			}
			// adding county mapping to year and Burglary
			if (!yearlyBurglary.containsKey(county)) {
				yearlyBurglary.put(county, new HashMap<>());
				Map<Integer, Integer> temp = yearlyBurglary.get(county);
				temp.put(year, burglary);
			} else if (yearlyBurglary.containsKey(county)) {
				if (!yearlyBurglary.get(county).containsKey(year)) {
					Map<Integer, Integer> temp = yearlyBurglary.get(county);
					temp.put(year, burglary);
				}
			}
			// adding county mapping to year and Drugs
			if (!yearlyDrugs.containsKey(county)) {
				yearlyDrugs.put(county, new HashMap<>());
				Map<Integer, Integer> temp = yearlyDrugs.get(county);
				temp.put(year, drug);
			} else if (yearlyDrugs.containsKey(county)) {
				if (!yearlyDrugs.get(county).containsKey(year)) {
					Map<Integer, Integer> temp = yearlyDrugs.get(county);
					temp.put(year, drug);
				}
			}
			// adding county mapping to year and Murder
			if (!yearlyMurder.containsKey(county)) {
				yearlyMurder.put(county, new HashMap<>());
				Map<Integer, Integer> temp = yearlyMurder.get(county);
				temp.put(year, murder);
			} else if (yearlyMurder.containsKey(county)) {
				if (!yearlyMurder.get(county).containsKey(year)) {
					Map<Integer, Integer> temp = yearlyMurder.get(county);
					temp.put(year, murder);
				}
			}
			// adding county mapping to year and Theft
			if (!yearlyTheft.containsKey(county)) {
				yearlyTheft.put(county, new HashMap<>());
				Map<Integer, Integer> temp = yearlyTheft.get(county);
				temp.put(year, theft);
			} else if (yearlyTheft.containsKey(county)) {
				if (!yearlyTheft.get(county).containsKey(year)) {
					Map<Integer, Integer> temp = yearlyTheft.get(county);
					temp.put(year, theft);
				}
			}
			// adding county mapping to year and Rape
			if (!yearlyRape.containsKey(county)) {
				yearlyRape.put(county, new HashMap<>());
				Map<Integer, Integer> temp = yearlyRape.get(county);
				temp.put(year, rape);
			} else if (yearlyRape.containsKey(county)) {
				if (!yearlyRape.get(county).containsKey(year)) {
					Map<Integer, Integer> temp = yearlyRape.get(county);
					temp.put(year, rape);
				}
			}
			// adding county mapping to year and Robbery
			if (!yearlyRobbery.containsKey(county)) {
				yearlyRobbery.put(county, new HashMap<>());
				Map<Integer, Integer> temp = yearlyRobbery.get(county);
				temp.put(year, robbery);
			} else if (yearlyRobbery.containsKey(county)) {
				if (!yearlyRobbery.get(county).containsKey(year)) {
					Map<Integer, Integer> temp = yearlyRobbery.get(county);
					temp.put(year, robbery);
				}
			}
			// adding county mapping to year and TotalCrime
			if (!yearlyTotalCrime.containsKey(county)) {
				yearlyTotalCrime.put(county, new HashMap<>());
				Map<Integer, Integer> temp = yearlyTotalCrime.get(county);
				temp.put(year, total);
			} else if (yearlyTotalCrime.containsKey(county)) {
				if (!yearlyTotalCrime.get(county).containsKey(year)) {
					Map<Integer, Integer> temp = yearlyTotalCrime.get(county);
					temp.put(year, total);
				}
			}
			/*
			 * David Bessex
			 */
			// list of Counties for user to see
			// Stacks are used to print out a list of country for user to choose from
			if (listOfCounties.empty()) {
				listOfCounties.push(county);
				sizeOfStack++;
			}
			if (!listOfCounties.peek().equals(county)) {
				listOfCounties.push(county);
				sizeOfStack++;
			}

		}
	}
	/*
	 * David Bessex
	 */
	// get method for yearlyPopulation
	public static int yearlyPopulation(String county, int year) {
		return yearlyPopulation.get(county).get(year);
	}

	// get method for yearlyAssault
	public static int yearlyAssault(String county, int year) {
		return yearlyAssault.get(county).get(year);
	}

	// get method for yearlyArson
	public static int yearlyArson(String county, int year) {
		return yearlyArson.get(county).get(year);
	}

	// get method for yearlyBurglary
	public static int yearlyBurglary(String county, int year) {
		return yearlyBurglary.get(county).get(year);
	}

	// get method for yearlyDrugs
	public static int yearlyDrugs(String county, int year) {
		return yearlyDrugs.get(county).get(year);
	}

	// get method for yearlyMurder
	public static int yearlyMurder(String county, int year) {
		return yearlyMurder.get(county).get(year);
	}

	// get method for yearlyTheft
	public static int yearlyTheft(String county, int year) {
		return yearlyTheft.get(county).get(year);
	}

	// get method for yearlyRape
	public static int yearlyRape(String county, int year) {
		return yearlyRape.get(county).get(year);
	}

	// get method for yearlyRobbery
	public static int yearlyRobbery(String county, int year) {
		return yearlyRobbery.get(county).get(year);
	}

	// get method for yearlyTotalCrime
	public static int yearlyTotalCrime(String county, int year) {
		return yearlyTotalCrime.get(county).get(year);
	}

	/*
	 * David Hohn
	 */

	// returns the year with the most of a specified crime in a county
	public int highestCrimeWithGivenYear(String county, String crimeType) {
		Map<Integer, Map<String, Integer>> yearlyCrimeData = yearWithMostCrime.get(county);
		int yearWithMost = 0;
		int crimeCount = 0;

		for (Map.Entry<Integer, Map<String, Integer>> entry : yearlyCrimeData.entrySet()) {
			int crime = entry.getValue().get(crimeType);
			if (crime > crimeCount) {
				crimeCount = crime;
				yearWithMost = entry.getKey();
			}
		}
		return yearWithMost;
	}

	/*
	 * Vy Nguyen
	 */
	// returns the crime rate per total population of a county in a specific year
	public double crimeRatePerTotalCrime(String county, int year, String crimeType) {
		// get the total crime of that county in the input year
		int totalCrime = yearlyTotalCrime(county, year);
		// get the crimeCount of that crime type
		int crimeCount = 0;
		if (crimeType.equals("assault")) {
			crimeCount = yearlyAssault(county, year);
		}
		if (crimeType.equals("arson")) {
			crimeCount = yearlyArson(county, year);
		}
		if (crimeType.equals("burglary")) {
			crimeCount = yearlyBurglary(county, year);
		}
		if (crimeType.equals("drugs")) {
			crimeCount = yearlyDrugs(county, year);
		}
		if (crimeType.equals("murder")) {
			crimeCount = yearlyMurder(county, year);
		}
		if (crimeType.equals("theft")) {
			crimeCount = yearlyTheft(county, year);
		}
		if (crimeType.equals("rape")) {
			crimeCount = yearlyRape(county, year);
		}
		if (crimeType.equals("robbery")) {
			crimeCount = yearlyRobbery(county, year);
		}
		double totalCrimeDouble = totalCrime;
		double crimeCountDouble = crimeCount;
		// calculate the total crime rate (in %)
		return (crimeCountDouble / totalCrimeDouble * 100.0);
	}

	/*
	 * Vy Nguyen
	 */
	public static void getData(String county, int year) {
		System.out.println("Data for " + county + " county in " + year + " :");
		System.out.println("|Population: " + yearlyPopulation(county, year) + "|\n" + "|Assault: "
				+ yearlyAssault(county, year) + "|" + " |Arson: " + yearlyArson(county, year) + "|" + " |Burglary: "
				+ yearlyBurglary(county, year) + "|\n" + "|Drugs: " + yearlyDrugs(county, year) + "|" + " |Murder: "
				+ yearlyMurder(county, year) + "|" + " |Theft: " + yearlyTheft(county, year) + "|\n" + "|Rape: "
				+ yearlyRape(county, year) + "|" + " |Robbery: " + yearlyRobbery(county, year) + "|" + " |Total crime: "
				+ yearlyTotalCrime(county, year) + "|\n");

	}
	/*
	 * David Bessex
	 */
	public static void compareData(String data, String county, int year1, int year2) {
		int information1 = 0;
		int information2 = 0;
		// If-statements to find what type of data is needed
		if (data == "Population") {
			information1 = yearlyPopulation(county, year1);
			information2 = yearlyPopulation(county, year2);
		}
		if (data == "Assault") {
			information1 = yearlyAssault(county, year1);
			information2 = yearlyAssault(county, year2);
		}
		if (data == "Arson") {
			information1 = yearlyArson(county, year1);
			information2 = yearlyArson(county, year2);
		}
		if (data == "Burglary") {
			information1 = yearlyBurglary(county, year1);
			information2 = yearlyBurglary(county, year2);
		}
		if (data == "Drugs") {
			information1 = yearlyDrugs(county, year1);
			information2 = yearlyDrugs(county, year2);
		}
		if (data == "Murder") {
			information1 = yearlyMurder(county, year1);
			information2 = yearlyMurder(county, year2);
		}
		if (data == "Theft") {
			information1 = yearlyTheft(county, year1);
			information2 = yearlyTheft(county, year2);
		}
		if (data == "Rape") {
			information1 = yearlyRape(county, year1);
			information2 = yearlyRape(county, year2);
		}
		if (data == "Robbery") {
			information1 = yearlyRobbery(county, year1);
			information2 = yearlyRobbery(county, year2);
		}
		if (data == "TotalCrime") {
			information1 = yearlyTotalCrime(county, year1);
			information2 = yearlyTotalCrime(county, year2);
		}

		System.out.println("County: " + county + "\nData reported: " + data + "\n\nStarting year: " + year1
				+ "\nStarting year data: " + information1 + "\n\nEnding year: " + year2 + "\nEnding year data: "
				+ information2);
		System.out.println();
		if (year1 > year2) {
			int temp = information1 - information2;
			if (temp > 0) {
				System.out.println("The data of " + data + " has increased " + temp + " since " + year2);
			} else if (temp < 0) {
				temp = temp * -1;
				System.out.println("The data of " + data + " has declined " + temp + " since " + year2);
			} else {
				System.out.println("Comparing the 2 years, the data of " + data + " is the same!");
			}
		} else if (year1 < year2) {
			int temp = information2 - information1;
			if (temp > 0) {
				System.out.println("The data of " + data + " has increased " + temp + " since " + year1);
			} else if (temp < 0) {
				temp = temp * -1;
				System.out.println("The data of " + data + " has declined " + temp + " since " + year1);
			} else {
				System.out.println("Comparing the 2 years, the data of " + data + " is the same!");
			}
		} else {
			System.out.println("They are the same year! The data has not changed.");
		}
		System.out.println();
	}
	/*
	 * David Bessex
	 * David Hohn
	 * Vy Nguyen
	 * Linus Kamau
	 */
	public static int userInput(Scanner s, String prompt) {
		int value = 0;
		String user = "";
		System.out.println(prompt);
		if (prompt == "To start, please type a county") {
			// changed s.next to s.nextLine so spaces in counties can be read
			// and added .toUpperCase so user doesn't need to type all caps
			user = s.nextLine().toUpperCase();
			// value = 1 if input was correctly read and Saves user input to county
			// value = 0 if input wasnt valid
			if (counties.contains(user)) {
				county = user;
				value = 1;
			} else {
				value = 0;
			}
		}
		if (prompt == "Please pick one action and spell it correctly :)") {
			user = s.nextLine().toUpperCase();
			// value = 1 if input was correctly read
			// value = 0 if input wasnt valid
			if (user.equals("COMPARE DATA")) {
				value = 1;
			} else if (user.equals("YEAR WITH MOST")) {
				value = 2;
			} else if (user.equals("CRIME RATE PER TOTAL")) {
				value = 3;
			} else if (user.equals("GET DATA")) {
				value = 4;
			} else if (user.equals("QUIT")) {
				value = 5;
			} else {
				value = 0;
			}
		}
		// if value = 0 -> 9 then input was valid
		// and return index of listOfInfo[]
		// if value = 999 then input was invalid
		if (prompt == "Please choose the type of data:") {
			user = s.nextLine().toUpperCase();
			if (user.equals("POPULATION")) {
				value = 0;
			} else if (user.equals("ASSAULT")) {
				value = 1;
			} else if (user.equals("ARSON")) {
				value = 2;
			} else if (user.equals("BURGLARY")) {
				value = 3;
			} else if (user.equals("DRUGS")) {
				value = 4;
			} else if (user.equals("MURDER")) {
				value = 5;
			} else if (user.equals("THEFT")) {
				value = 6;
			} else if (user.equals("RAPE")) {
				value = 7;
			} else if (user.equals("ROBBERY")) {
				value = 8;
			} else if (user.equals("TOTAL CRIME")) {
				value = 9;
			} else {
				value = 999;
			}
		}
		// if user is a valid year, parseInt(user) and return year
		// else, invalid input
		if (prompt == "Please choose a starting year: (1990 - 2020)") {
			user = s.nextLine();
			int year = Integer.parseInt(user);
			if (year < 1990 || year > 2020) {
				value = 0;
			} else {
				value = year;
			}
		}
		// if user is a valid year, parseInt(user) and return year
		// else, invalid input
		if (prompt == "Please choose a year to compare: (1990 - 2020)") {
			user = s.nextLine();
			int year = Integer.parseInt(user);
			if (year < 1990 || year > 2020) {
				value = 0;
			} else {
				value = year;
			}
		}
		return value; // returns value of a given input
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner data = new Scanner(new File("WashingtonCrimeData.csv"));
		crimeData c = new crimeData(data);
		Scanner s = new Scanner(System.in);
		String[] listOfInfo = { "Population", "Assault", "Arson", "Burglary", "Drugs", "Murder", "Theft", "Rape",
				"Robbery", "TotalCrime" };
//		System.out.println(counties);
		String oops = "\nWrong input!\n";
		String prompt = "";
		// update because all caps and underscores are no longer needed
		System.out
				.println("Welcome to Team DVLDs final project! " + "\nHere you can look for Washington crime data.\n");
		System.out.println("Due to the amount of crime data that we originally recieved, "
				+ "\n'Total Crime' will always have a larger case count than what the other categories"
				+ "\nadd up too. This is because crime data, in the most recient years, is more detailed"
				+ "\nthan previous years, hense why we trimmed down the type of data USER can research."
				+ "\nWe kept 'Total Crime' data the same however so USER can see the total number of crimes"
				+ "\neven if we were unable to get the specific category name.\n");
		System.out.println("Below is the list of counties to choose from:\n");

		for (int i = 0; i < sizeOfStack; i += 4) {
			System.out.print("|" + listOfCounties.peek() + "| |");
			listOfCounties.pop();
			System.out.print(listOfCounties.peek() + "| |");
			listOfCounties.pop();
			System.out.print(listOfCounties.peek() + "| |");
			listOfCounties.pop();
			System.out.println(listOfCounties.peek() + "|");
			listOfCounties.pop();
		}
		System.out.println("--------------------------------------------");
		while (true) {
			prompt = "To start, please type a county";
			int input = userInput(s, prompt);
			if (input == 1) {
				break;
			}
			if (input == 0) {
				System.out.println(oops);
				continue;
			}
		}
		System.out.println("Here are a list of action you can take with the given data:\n"
				+ "- Compare the growth or decline (in crime or population) of a County between 2 different years (TYPE -> compare data)\n"
				+ "- Find the highest data, for a given crime, in a single year (TYPE -> year with most)\n"
				+ "- Find the crime rate per total crime for a given crime in a specific year (TYPE -> crime rate per total)\n"
				+ "- Get all the data for a county in a specific year (TYPE -> get data)\n"
				+ "- To exit the program (TYPE -> quit)\n");

		while (true) {
			// Pick an action
			prompt = "Please pick one action and spell it correctly :)";
			String compareData = "";
			int input = userInput(s, prompt);
			/*
			 * David Bessex
			 */
			if (input == 1) {
				// Action picked -> compareData
				try {
					prompt = "Please choose the type of data:";
					System.out.println("\n|Population| |Assault| |Arson|\n|Burglary| |Drugs| |Murder|"
							+ "\n|Theft| |Rape| |Robbery| |TotalCrime|\n");
					input = userInput(s, prompt);
					if (input == 999) {
						System.out.println(oops);
						continue;
					} else {
						// Grabs first piece of data needed for compareData
						compareData = listOfInfo[input];
						prompt = "Please choose a starting year: (1990 - 2020)";
						input = userInput(s, prompt);
						if (input == 0) {
							System.out.println(oops);
							continue;
						} else {
							// Grabs second piece of data needed for compareData
							compareYearOne = input;
							prompt = "Please choose a year to compare: (1990 - 2020)";
							input = userInput(s, prompt);
							if (input == 0) {
								System.out.println(oops);
								continue;
							} else {
								// Grabs third piece of data needed for compareData
								compareYearTwo = input;
							}
						}
					}
				} catch (NumberFormatException e) {
					System.out.println(oops);
					continue;

				}
				// Finally runs method to compareData
				compareData(compareData, county, compareYearOne, compareYearTwo);

			}
			/*
			 * David Hohn
			 */
			// input for year with the most of specified crime
			if (input == 2) {
				System.out.println("\n|Population| |Assault| |Arson|\n|Burglary| |Drugs| |Murder|"
						+ "\n|Theft| |Rape| |Robbery| |Total Crime|\n");
				System.out.println("Enter a data type");
				String typeOfCrime = s.nextLine().toLowerCase();
				int year = 0;
				for (String i : c.crimeTypes) {
					if (i.equals(typeOfCrime)) {
						year = c.highestCrimeWithGivenYear(county, typeOfCrime);
						break;
					}
				}
				if (year == 0) {
					System.out.println(oops);
				} else {
					System.out.println(
							"\nThe year with highest amount of " + typeOfCrime + " in " + county + " : " + year + "\n");
				}
			}
			/*
			 * Vy Nguyen
			 */
			// input for crime rate per total
			if (input == 3) {
				try {
					prompt = "Please choose a starting year: (1990 - 2020)";
					int year = userInput(s, prompt);
					if (year < 1990 || year > 2020) {
						System.out.println(oops);
						continue;
					}
					System.out.println("\nEnter a crime type: ");
					System.out.println("|Assault| |Arson||Burglary| |Drugs|" + "\n|Murder| |Theft| |Rape| |Robbery|\n");
					String temp = "";
					String typeOfCrime = s.nextLine().toLowerCase();
					for (int i = 1; i < 9; i++) {
						if (typeOfCrime.equals(crimeTypes[i])) {
							temp = crimeTypes[i];
						}
					}
					if (!typeOfCrime.equals(temp)) {
						System.out.println(oops);
						continue;
					} else {
						double crimeRate = c.crimeRatePerTotalCrime(county, year, typeOfCrime);
						System.out.println("\nThe crime rate per total " + typeOfCrime + " cases in " + county
								+ " county in " + year + ": " + crimeRate + "%\n");
					}
				} catch (NumberFormatException e) {
					System.out.println(oops);
					continue;
				}
			}
			/*
			 * Vy Nguyen
			 */
			// input for get data
			if (input == 4) {
				try {
					prompt = "Please choose a starting year: (1990 - 2020)";
					int year = userInput(s, prompt);
					if (year < 1990 || year > 2020) {
						System.out.println(oops);
						continue;
					}
					getData(county, year);
				} catch (NumberFormatException e) {
					System.out.println(oops);
					continue;
				}
			}
			/*
			 * Linus Kamau & David Hohn
			 */
			// exiting the program
			if (input == 5) {
				System.out.println(
						"\nAre you sure you would like to exit?\ntype 'yes' to quit or 'no' to select an action\n");
				String answer = s.nextLine().toLowerCase();
				if (answer.equals("yes")) {
					System.out.println("Thank you for using our program!");
					s.close();
					break;
				} else if (answer.equals("no")) {
					continue;
				} else {
					System.out.println("\n" + oops);
					continue;
				}
			}

			// end of while loop
			if (input == 0) {
				System.out.println(oops);
				continue;
			}
		}
	}
}
