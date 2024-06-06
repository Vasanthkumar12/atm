// --------------------------------------------- ATM PROJECT --------------------------------------------

import java.util.*;
public class ATM {

    // Intializing the variables to calculate total amount of each notes
    static int totalNoof500Notes = 500;
    static int totalNoof100Notes = 500;
    static int totalNoof50Notes = 500;
    static int totalNoof20Notes = 500;
    static int totalNoof10Notes = 500;

    static int totalAmtof500;
    static int totalAmtof100;
    static int totalAmtof50;
    static int totalAmtof20;
    static int totalAmtof10;

    static int totalAmtinAtm;
    static String withdrawnAmountstr;
    static int withdrawnAmount;
    static int amount_remains;

    static char cancel;
    static int turn = 1;
    static char yesNo = 'Y';
    static int option;

    static String depo_amountstr;
    public static void main(String[] args) {

        while(yesNo == 'Y' || yesNo == 'y') {
            Hashtable<Integer, Integer> atmAmountDistribution = new Hashtable<Integer, Integer>();

            // Create an Has table to store the amounts
            atmAmountDistribution.put(500, totalNoof500Notes);
            atmAmountDistribution.put(100, totalNoof100Notes);
            atmAmountDistribution.put(50, totalNoof50Notes);
            atmAmountDistribution.put(20, totalNoof20Notes);
            atmAmountDistribution.put(10, totalNoof10Notes);
            
            Scanner scan = new Scanner(System.in);
            
            // Total amount Calculation
            totalAmtof500 = 500 * totalNoof500Notes;
            totalAmtof100 = 100 * totalNoof100Notes;
            totalAmtof50 = 50 * totalNoof50Notes;
            totalAmtof20 = 20 * totalNoof20Notes;
            totalAmtof10 = 10 * totalNoof10Notes;
            totalAmtinAtm = totalAmtof500 + totalAmtof100 + totalAmtof50 + totalAmtof20 + totalAmtof10;

            if(turn > 1){
                System.out.println("If you want to Perform any other actions in ATM (Y/N): ");
                yesNo = scan.next().charAt(0);
            }
            if(yesNo == 'Y' || yesNo == 'y'){
                System.out.println("Amount Details In ATM Machine given below :");
                System.out.println("-------------------------------------------------------------------------------");
                System.out.println("|   Rupee Denomination            |    Number of Notes   |  Total amount        |");
                System.out.println("-------------------------------------------------------------------------------");
                System.out.printf("|         500                    |       %d            |       %d         |\n", totalNoof500Notes, totalAmtof500);
                System.out.printf("|         100                    |       %d            |       %d         |\n", totalNoof100Notes, totalAmtof100);
                System.out.printf("|         50                     |       %d            |       %d         |\n", totalNoof50Notes, totalAmtof50);
                System.out.printf("|         20                     |       %d            |       %d         |\n", totalNoof20Notes, totalAmtof20);
                System.out.printf("|         10                     |       %d            |       %d         |\n", totalNoof10Notes, totalAmtof10);
                System.out.println("--------------------------------------------------------------------------------");
                System.out.printf("|  Total Amount of cash in ATM  |                              %d          |\n", totalAmtinAtm);
                System.out.println("---------------------------------------------------------------------------------");
                
                update_atm_amount_distribution(scan);
            }
            else {
                System.out.println("Thank You for use our ATM.....");
                return;
            }
                    
        }
    }

    public static void update_atm_amount_distribution(Scanner scan){
        System.out.println("Please read the Instructions carefully : ");
        System.out.println("If you want to Withdrwn Amount then PRESS '1'.");
        System.out.println("If you want to Deposit Amount then PRESS '2'.");
        System.out.println("If you want leave then PRESS '5'.");
        option = scan.nextInt();
        scan.nextLine();
        switch(option) {
            case 1:
                System.out.println("Withdrawn Instructions : ");
                System.out.println("1.Enter amount less than 10,000 Rs. and in multiples of 10");
                System.out.println("2.Minimum Amount withdrawn is 100 Rs.");
                System.out.println("If you want to continue PRESS (Y/y) or want to cancel your transaction please enter (n/N) : ");
                cancel = scan.next().charAt(0);
                if(cancel == 'N' || cancel == 'n') {
                    System.out.println("Thank You for use our ATM.....");
                    return;
                }
                scan.nextLine();
                System.out.println("Enter Amount do you want to with drawn");
                withdrawnAmountstr = scan.nextLine();
            
                // Handling wrong Inputs
                try {
                    withdrawnAmount = Integer.parseInt(withdrawnAmountstr);
                    if(withdrawnAmount > totalAmtinAtm) {
                        System.out.println("Insufficient Balance.");
                        return;
                    }
                    if(withdrawnAmount <= 100 && withdrawnAmount % 10 != 0 && withdrawnAmount > 10000) {
                        System.out.println("Invalid Amount you entered please read the Instructions and enter valid Amount to withdrawn. Try again...");
                        return;
                    }
                }
                catch(NumberFormatException e) {
                    System.out.println("Invalid input. Please enter valid format of input that is positive Integer.");
                    return;
                }
            
                boolean transaction_result = withdraw(withdrawnAmount);
            
                if(transaction_result) {
                    System.out.println("WITHDRAWAL TRANSACTION WAS COMPLETED SUCCESSFULLY...........");
                    System.out.println("---------------------------------------------------------------------------------");
                }
                turn++;
                break;
            
            case 2:
                boolean add_transaction_result = deposit(scan);
                if(add_transaction_result) {
                    System.out.println("DEPOSIT TRANSACTION WAS COMPLETED SUCCESSFULLY...........");
                    System.out.println("---------------------------------------------------------------------------------");
                }
                turn++;
                break;

            case 5:
                System.out.println("Thank You for use our ATM.....");
                System.out.println("---------------------------------------------------------------------------------");
                return;
        }
    }

    public static boolean deposit(Scanner scan) {
        System.out.println("Enter Amount do you add to the ATM : ");
        depo_amountstr = scan.nextLine();
        int depo_amount;
        // Handling wrong Inputs
        try {
            depo_amount = Integer.parseInt(depo_amountstr);
            if(depo_amount > 340000) {
                System.out.println("Deposit Amount is reached their Limit. Please deposit less amount.");
                return false;
            }
            if(depo_amount % 10 != 0) {
                System.out.println("Invalid Amount you entered please read the Instructions and enter valid Amount to withdrawn. Try again...");
                return false;
            }
        }
        catch(NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid format of input that is positive Integer.");
            return false;
        }        
        
        System.out.println("Enter no of 500 notes do you add to the ATM : ");
        int no_of_500_notes = scan.nextInt();
        totalNoof500Notes += no_of_500_notes;
        if(totalNoof500Notes > 500) {
            System.out.println("The limit of notes has exceeded. So please add less 500 Rs. notes");
            return false;
        }

        System.out.println("Enter no of 100 notes do you add to the ATM : ");
        int no_of_100_notes = scan.nextInt();
        totalNoof100Notes += no_of_100_notes;
        if(totalNoof100Notes > 500) {
            System.out.println("The limit of notes has exceeded. So please add less 100 Rs. notes");
            return false;
        }

        System.out.println("Enter no of 50 notes do you add to the ATM : ");
        int no_of_50_notes = scan.nextInt();
        totalNoof50Notes += no_of_50_notes;
        if(totalNoof50Notes > 500) {
            System.out.println("The limit of notes has exceeded. So please add less 50 Rs. notes");
            return false;
        }

        System.out.println("Enter no of 20 notes do you add to the ATM : ");
        int no_of_20_notes = scan.nextInt();
        totalNoof20Notes += no_of_20_notes;
        if(totalNoof20Notes > 500) {
            System.out.println("The limit of notes has exceeded. So please add less 20 notes");
            return false;
        }

        System.out.println("Enter no of 10 notes do you add to the ATM : ");
        int no_of_10_notes = scan.nextInt();
        totalNoof10Notes += no_of_10_notes;
        if(totalNoof10Notes > 500) {
            System.out.println("The limit of notes has exceeded. So please add less 10 notes");
            return false;
        }

        System.out.println("Deposit Details :");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|   Rupee Denomination            |    Number of Notes   |  Total amount        |");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.printf("|         500                    |       %d            |       %d         |\n", no_of_500_notes, no_of_500_notes * 500);
        System.out.printf("|         100                    |       %d            |       %d         |\n", no_of_100_notes, no_of_100_notes * 100);
        System.out.printf("|         50                     |       %d            |       %d         |\n", no_of_50_notes, no_of_50_notes * 50);
        System.out.printf("|         20                     |       %d            |       %d         |\n", no_of_20_notes, no_of_20_notes * 20);
        System.out.printf("|         10                     |       %d            |       %d         |\n", no_of_10_notes, no_of_10_notes * 10);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("|  Total Amount of cash Add  |                              %d          |\n", depo_amount);
        System.out.println("---------------------------------------------------------------------------------");
        return true;
    }

    // Amount distribution logic
    public static boolean withdraw(int amount) {
        int tot_amt = amount;
        // Hashtable<Integer, Integer> withdrawInfo = new Hashtable<Integer, Integer>();
        int no_of_500_notes = amount/500;
        totalNoof500Notes -= no_of_500_notes;
        amount_remains = amount - (no_of_500_notes * 500);
        //System.out.println("amount_remains = "+amount_remains);
        
        int no_of_100_notes = (amount_remains/100);
        totalNoof100Notes -= no_of_100_notes;
        //System.out.println("no_of_100_notes = "+no_of_100_notes);
        amount_remains -= (no_of_100_notes * 100);
        //System.out.println("amount_remains = "+amount_remains);
        
        int no_of_50_notes = (amount_remains/50);
        totalNoof50Notes -= no_of_50_notes;
        // System.out.println("no_of_50_notes = "+no_of_50_notes);
        amount_remains -= (no_of_50_notes * 50);
        // System.out.println("amount_remains = "+amount_remains);
        
        int no_of_20_notes = (amount_remains/20);
        totalNoof20Notes -= no_of_20_notes;
        // System.out.println("no_of_20_notes = "+ no_of_20_notes);
        amount_remains -= (no_of_20_notes * 20);
        // System.out.println("amount_remains = "+ amount_remains);
        
        int no_of_10_notes = (amount_remains/10);
        totalNoof10Notes -= no_of_10_notes;
        // System.out.println("no_of_10_notes = "+no_of_10_notes);

        int no_of_total_notes = no_of_500_notes + no_of_100_notes + no_of_50_notes + no_of_20_notes + no_of_10_notes;
        if(no_of_total_notes > 40) {
            System.out.println("Sorry the Number of Notes exceed their limit 40. So please enter Small Amount");
            return false;
        }
        else {
            System.out.println("Withdrawn Details :");
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("|   Rupee Denomination            |    Number of Notes   |  Total amount        |");
            System.out.println("-------------------------------------------------------------------------------");
            System.out.printf("|         500                    |       %d            |       %d         |\n", no_of_500_notes, no_of_500_notes * 500);
            System.out.printf("|         100                    |       %d            |       %d         |\n", no_of_100_notes, no_of_100_notes * 100);
            System.out.printf("|         50                     |       %d            |       %d         |\n", no_of_50_notes, no_of_50_notes * 50);
            System.out.printf("|         20                     |       %d            |       %d         |\n", no_of_20_notes, no_of_20_notes * 20);
            System.out.printf("|         10                     |       %d            |       %d         |\n", no_of_10_notes, no_of_10_notes * 10);
            System.out.println("--------------------------------------------------------------------------------");
            System.out.printf("|  Total Amount of cash WithDrawn  |                              %d          |\n", tot_amt);
            System.out.println("---------------------------------------------------------------------------------");

            return true;
        }

    }
}

