 // Throughout this project, the use of data structures are not permitted such as methods like .split and .toCharArray


 import java.util.Scanner;
 // More packages may be imported in the space below
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
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
		 while (!validatePostalCode(postalCode)) {
			 System.out.println("Your input for " + firstName + "'s postal code is not correct, please input again.");
			 System.out.println("Please input the customer postal code: ");
			 postalCode = scanner.nextLine();
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
	 * This method may be edited to achieve the task however you like.
	 * The method may not nesessarily be a void return type
	 * This method may also be broken down further depending on your algorithm
	 */
	 public boolean validatePostalCode(String code){
		 // if the length of the code is less than 3, return false
		 if (code.length() < 3) return false;
		 String str = code.substring(0, 3);
		 // get the first 3 characters from the code and validate it is in the postal codes stored in the system
		 for (int i = 0; i < postalCodes.size(); i++) {
			 // if found the substr in the postal array, return true
			 if (postalCodes.get(i).indexOf(str) != -1) {
				 return true;
			 }
		 }
		 
		 return false;
	 }
	 
	 /*
	 * This method may be edited to achieve the task however you like.
	 * The method may not nesessarily be a void return type
	 * This method may also be broken down further depending on your algorithm
	 */
	 /* validateCreditCard method which validate the credit card info according to the 
	  * Lunh Algorithm.
	  * if the credit card is conform the Lunh Algorithm, return true, 
	  * else return false
	  */
	 
	 public boolean validateCreditCard(String card){
		 if (card.length() < 9) return false;
		 // if card length less than 9, return false directly
		 int sum1 = 0, sum2 = 0, tmp = 0;
		 String reverse = "";
		 // get the reverse string of the card
		 for (int i = card.length() - 1; i >= 0; i--) {
			 if (card.charAt(i) >= '0' && card.charAt(i) <= '9') {
				 reverse += card.charAt(i);
			 }
			 else {
				 return false;
			 }
		 }
				 
		 // get the sum of the odd digits of the reverse string
		 for (int i = 0; i < reverse.length(); i += 2) {
			 sum1 += (reverse.charAt(i) - '0');
		 }
		 // get the sum of the double even digits of the reverse string
		 for (int i = 1; i < reverse.length(); i += 2) {
			 tmp = (reverse.charAt(i) - '0') * 2;
			 if (tmp >= 10) {
				 sum2 += ((tmp % 10) + (tmp / 10));
			 }
			 else {
				 sum2 += tmp;
			 }
		 }
		 // check whether the end digit is 0, if so return true
		 return (sum1 + sum2) % 2 == 0;
	 }
	 /*
	 * This method may be edited to achieve the task however you like.
	 * The method may not nesessarily be a void return type
	 * This method may also be broken down further depending on your algorithm
	 */
	 
	 /*
	  * generateCustomerDataFile method which would save all customers info 
	  * into the specified file
	  * */
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
	 
    

