package Java;

 // Throughout this project, the use of data structures are not permitted such as methods like .split and .toCharArray


 import java.util.Scanner;
 // More packages may be imported in the space below
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.util.ArrayList;
 
 
 class CustomerSystem{
	 
	 // list of the postal code loaded from the specified file
	 private ArrayList<String> postalCodes;
	 // list of the customer object 
	 private ArrayList<Customer> customers;
	 
	 // main method of the class
	 public static void main(String[] args){
		 // Please do not edit any of these variables
		 Scanner reader = new Scanner(System.in);
		 String userInput, enterCustomerOption, generateCustomerOption, exitCondition;
		 enterCustomerOption = "1";
		 generateCustomerOption = "2";
		 exitCondition = "9";
 
		 // More variables for the main may be declared in the space below
		 // declare a new object of the CustomerSystem
		 // which would operate on this object
		 CustomerSystem cs = new CustomerSystem();
		 
		 do{
			 printMenu();                                    // Printing out the main menu
			 userInput = reader.nextLine();                  // User selection from the menu
 
			 if (userInput.equals(enterCustomerOption)){
				 // Only the line below may be editted based on the parameter list and how you design the method return
				 // Any necessary variables may be added to this if section, but nowhere else in the code
				 cs.enterCustomerInfo();
			 }
			 else if (userInput.equals(generateCustomerOption)) {
				 // Only the line below may be editted based on the parameter list and how you design the method return
				 //generateCustomerDataFile();
				 cs.generateCustomerDataFile();
			 }
			 else{
				 System.out.println("Please type in a valid option (A number from 1-9)");
			 }
 
		 } while (!userInput.equals(exitCondition));         // Exits once the user types 
		 
		 reader.close();
		 System.out.println("Program Terminated");
	 }
	 public static void printMenu(){
		 System.out.println("Customer and Sales System\n"
		 .concat("1. Enter Customer Information\n")
		 .concat("2. Generate Customer data file\n")
		 .concat("3. Report on total Sales (Not done in this part)\n")
		 .concat("4. Check for fraud in sales data (Not done in this part)\n")
		 .concat("9. Quit\n")
		 .concat("Enter menu option (1-9)\n")
		 );
	 }
	 /*
	 * This method may be edited to achieve the task however you like.
	 * The method may not nesessarily be a void return type
	 * This method may also be broken down further depending on your algorithm
	 */
	 /*
	  * enterCustomerInfo method
	  * which would prompt the user type detailed info of the customer,
	  * and then valid the postal code and crredit card of the customer, 
	  * is passed, store this customer in the system, or else prompt the user 
	  * input these info again
	  * */
	 public void enterCustomerInfo() {
		 Scanner scanner = new Scanner(System.in);
		 
		 String firstName, lastName, city, postalCode, creditCard;
		 
		 System.out.println("Please input the customer first name:");
		 firstName = scanner.nextLine();
		 System.out.println("Please input the customer last  name: ");
		 lastName = scanner.nextLine();
		 System.out.println("Please input the customer loacted city: ");
		 city = scanner.nextLine();
		 System.out.println("Please input the customer postal code: ");
		 postalCode = scanner.nextLine();
		 // valid the postal code of the customer
		 while (validatePostalCode(postalCode)==false) {
			System.out.println("Your input for " + firstName + "'s postal code is not correct, please input again.");
			System.out.println("Please input the customer postal code: ");
			postalCode = scanner.nextLine();
			if (validatePostalCode(postalCode) == true){
				break;
			}
		}

		 
		 System.out.println("Please input the customer credit card: ");
		 creditCard = scanner.nextLine();
		 // valid the credit card info of the customer
		 while (!validateCreditCard(creditCard)) {
			 System.out.println("Your input for " + firstName + "'s credit card is not correct, please input again.");
			 System.out.println("Please input the customer credit card: ");
			 creditCard = scanner.nextLine();
		 }
		 // add the customer into the system
		 customers.add(new Customer(firstName, lastName, city, postalCode, creditCard));
		 System.out.println("Customer " + firstName + " had been successfully added into the system.");
	 
		 // close the scanner
		 scanner.close();

		}
	 /*
     	* Description: validate the Customer postal code
     	* validation rules: 1. Must be at least 3 characters in length
     	*                   2. The first 3 characters must match the postal codes loaded from the file “postal_codes.csv” 
     	* @author - Leo Shi
     	* @param - Pcode - the Customer Postal Code
     	* @return - boolean indicating is this Postal Code valid or no 
     	*/
	public static boolean validatePostalCode(String Pcode) {
        // check the length
        if (Pcode.length() < 3) {
            return false;
        }
        // check the code match or not
        try {
            File file = new File("C:/Users/Williamson Wang/Downloads/postal_codes (3).csv"); // file
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) { // read file line by line
                String code = line.substring(0, 3);
                if (code.equalsIgnoreCase(Pcode)) { // match
                    return true;
                }
            }
            br.close();
            fr.close();
            return false;
        } catch (Exception e) {
            System.out.println("There is an error");
        }
        return false;
    }
	/*
     	* Description: validate the Customer Credit Card number
     	* validation rules: 1.	Must be at least 9 digits in length
     	*                   2.	The digits must pass the Luhn algorithm.
     	* @author - Leo Shi
     	* @param - Pcode - the Customer Credit Card number
     	* @return - boolean indicating is this Credit Card number valid or no 
     	*/
	public static boolean validateCreditCard(String Cardnumber) {
        int digit;
        int sum1 = 0;
        int count = 0;
        // check the length
        if (Cardnumber.length() < 9) {
            return false;
        }
        // clear the space
        Cardnumber = Cardnumber.replaceAll("\\s", "");
        // reverse the cardnumber
        int lastdigit = Cardnumber.length();
        for (int i = 0; i < Cardnumber.length(); i++) {
            digit = Integer.parseInt(Cardnumber.substring(lastdigit - 1, lastdigit));
            lastdigit--;
            // sum the odd digits
            if (count % 2 == 0) {
                sum1 = sum1 + digit;
            }
            count++;
        }
        // reset the value
        lastdigit = Cardnumber.length();
        count = 0;
        int sum2 = 0;
        // reverse the cardnumber
        for (int i = 0; i < Cardnumber.length(); i++) {
            digit = Integer.parseInt(Cardnumber.substring(lastdigit - 1, lastdigit));
            lastdigit--;
            // sum the even digits
            if (count % 2 != 0) {
                digit = digit * 2;
                if (digit >= 10) { // greater than 9
                    digit = (digit % 10) + ((digit - (digit % 10)) / 10); // add 2 digits
                    sum2 = sum2 + digit;
                } else {
                    sum2 = sum2 + digit; // less than 10
                }
            }
            count++; 
        }
	 public void generateCustomerDataFile(){
		 String filename = "customer.txt";
		 try {
			 // get the file writer with buffer
			 BufferedWriter out = new BufferedWriter(new FileWriter(filename));
			 for (int i = 0; i < customers.size(); i++) {
				 // write the customer info into the file with \n at the end of the line
				 out.write(customers.get(i).toString());
				 out.newLine();
			 }
			 out.close();
			 System.out.println("All customer info had been saved to the " + filename + ".");
		 } catch (IOException e) {
			 // TODO: handle exception
			 e.printStackTrace();
		 }
	 }
	 
	 /*******************************************************************
	 *       ADDITIONAL METHODS MAY BE ADDED BELOW IF NECESSARY         *
	 *******************************************************************/
	 // constructor of the CustomerSystem class
	 // which would initialize the postalcodes and customers and load all postal code from the file
	 public CustomerSystem() {
		 postalCodes = new ArrayList<String>();
		 customers = new ArrayList<Customer>();
		 loadPostalCodesFromFile();
			 
	 }
	 
	 // load all postal code from the specified file
	 public void loadPostalCodesFromFile() {
		 String filename = "postal_codes.csv";
		 String line = "";
		 
		 try {
			 BufferedReader in = new BufferedReader(new FileReader(filename));
			 line = in.readLine();
			 while ((line = in.readLine()) != null) {
				 String tmp = "";
				 // just get the postal code from the file
				 for (int i = 0; i < line.length(); i++) {
					 if (line.charAt(i) != '|') tmp += line.charAt(i);
					 else break;
				 }
				 postalCodes.add(tmp);
			 }
			 
			 in.close();
		 } catch (IOException e) {
			 // TODO: handle exception
			 e.printStackTrace();
		 }
	 }
 }
	 
    

