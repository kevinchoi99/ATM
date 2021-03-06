import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.security.MessageDigest;
public class User {


    //First name of the user
    private String firstName;


    //Last name of the user
    private String lastName;


    //ID number of the user

    private String uuid;


    //Hash of the user's pin number

    private byte pinHash[];


    //The list of Accounts for the user
    private ArrayList<Account> accounts;

    /**
     * Create a new user
     * @param firstName -the user's first name
     * @param lastName  -the user's last name
     * @param pin       -the user's account pin number
     * @param theBank   -the bank (an object) that the user is a customer of
     */
    public User(String firstName, String lastName, String pin, Bank theBank){

        // set user's name
        this.firstName = firstName;
        this.lastName = lastName;

        // store the pin's hash only instead of the pin number  for security

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException");
            System.exit(1);
            throw new RuntimeException(e);
        }

        // get a new, unique universal ID (uuid) for the user
        this.uuid = theBank.getNewUserUUID();

        // create empty list of accounts
        this.accounts = new ArrayList<Account>();

        // print log message
        System.out.printf("New user %s, %s with ID %s created. \n", lastName, firstName, this.uuid);

    }

    /**
     * Add an account for the user
     * @param anAcct    -the account to be added
     */
    public void addAccount(Account anAcct){

        this.accounts.add(anAcct);

    }

    /**
     * Return the user's UUID
     * @return the uuid
     */
    public String getUUID() {
        return this.uuid;
    }

    /**
     * Check whether a given pin matches the true User pin
     * @param aPin  - the pin to check
     * @return      - whether the pin is valid or not
     */
    public boolean validatePin(String aPin){

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(aPin.getBytes()),this.pinHash);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException");
            System.exit(1);
            throw new RuntimeException(e);
        }


    }

    // returns the user's first name
    public String getFirstName(){

        return this.firstName;

    }

    public void printAccountSummary(){

        System.out.printf(" \n \n %s's accounts summary\n", this.firstName);
        for(int a = 0; a < this.accounts.size(); a++){
            System.out.printf("%d. %s \n", a+1, this.accounts.get(a).getSummaryLine());

        }
        System.out.println();
    }

    // get the number of accounts of the user
    public int numAccounts(){

        return this.accounts.size();

    }

    // print transaction history for account
    public void printAcctTransHistory(int acctIdx){

        this.accounts.get(acctIdx).printTransHistory();

    }

    // get the balance of the account
    public double getAcctBalance(int acctIdx){

        return this.accounts.get(acctIdx).getBalance();

    }

    // get the UUID of an account
    public String getAcctUUID(int acctIdx){

        return this.accounts.get(acctIdx).getUUID();

    }

    public void addAcctTransaction(int acctIdx, double amount, String memo){

        this.accounts.get(acctIdx).addTransaction(amount,memo);

    }
}
