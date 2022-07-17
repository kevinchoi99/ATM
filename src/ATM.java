import java.util.Scanner;
public class ATM {

    public static void main(String[] args) {

        // initialise Scanner
        Scanner sc = new Scanner(System.in);

        // initialise Bank
        Bank theBank = new Bank("Bank of Kevin");

        // add a user and create savings account simultaneously
        User aUser = theBank.addUser("Kevin","Choi","1234");

        // add a checking account for the user
        Account newAccount = new Account("Checking", aUser, theBank);
        aUser.addAccount(newAccount);
        theBank.addAccount(newAccount);

        User curUser;
        while(true){

            // stay in the login prompt until sucessful login
            curUser = ATM.mainMenuPrompt(theBank, sc);

            // stay in the main menu until user quits
            ATM.printUserMenu(curUser, sc);

        }

    }

    // Creating the Log In Menu
    public static User mainMenuPrompt(Bank theBank, Scanner sc){

        //initialise
        String userID;
        String pin;
        User authUser;

        // prompt the user for user ID and pin combo until correct
        do{

            System.out.printf("\n \n Welcome to %s \n \n", theBank.getName());
            System.out.print("Enter user ID: ");
            userID = sc.nextLine();
            System.out.print("Enter pin: ");
            pin = sc.nextLine();

            // try to get the user object corresponding to the ID and pin combo
            authUser = theBank.userLogin(userID,pin);
            if (authUser == null){
                System.out.println("Incorrect user ID or pin. " +
                        "Please try again.");
            }

        } while(authUser == null); //loops continuously until successful login

        return authUser;

    }

    public static void printUserMenu(User theUser, Scanner sc){

        // print a summary of the user's account
        theUser.printAccountSummary();

        // initialise
        int choice;

        // user menu
        do{
            System.out.printf("Welcome %s, please select a option.", theUser.getFirstName());
            System.out.println(" 1. Show the account transaction history");
            System.out.println(" 2. Make a Withdrawal");
            System.out.println(" 3. Make a Deposit");
            System.out.println(" 4. Make a Transfer");
            System.out.println(" 5. Exit");
            System.out.println();
            System.out.println("Enter choice: ");
            choice = sc.nextInt();

            if(choice < 1 || choice > 5){
                System.out.println("Invalid choice, Please choose 1 - 5");
            }

        }while(choice < 1 || choice > 5);

        //processing the choice
        switch(choice){

            case 1:
                ATM.showTransHistory(theUser,sc);
                break;

            case 2:
                ATM.withdrawFunds(theUser,sc);
                break;

            case 3:
                ATM.depositFunds(theUser,sc);
                break;

            case 4:
                ATM.transferFunds(theUser, sc);
                break;

        }

        // redisplay the menu unless the user wants to quit
        if (choice!=5){
            ATM.printUserMenu(theUser,sc);
        }
    }

    public static void showTransHistory(User theUser, Scanner sc){

        int theAcct;

        // get the account to look at its transaction history
        do{
            System.out.printf("Enter the number (1-%d) of the account" + "whose transactions you want to see", theUser.numAccounts());
            theAcct = sc.nextInt()-1;
            if(theAcct < 0 || theAcct >= theUser.numAccounts()){
                System.out.println("Invalid Account. Please try again.");
            }

        }while (theAcct < 0 || theAcct >= theUser.numAccounts());

        //print the transaction history
        theUser.printAcctTransHistory(theAcct);

    }
}
