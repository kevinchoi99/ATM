import java.util.ArrayList;
import java.util.Random;
public class Bank {

    private String name;

    private ArrayList<User> users;

    private ArrayList<Account> accounts;

    /**
     * Create a Bank object with empty lists of users and accounts
     * @param name  - name of the bank
     */
    public Bank(String name){

        this.name = name;
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();

    }
    /**
     * Generate a new uuid for a user
     * @return uuid
     */
    public String getNewUserUUID(){

        //initialise
        String uuid;
        Random rng = new Random();
        int len = 6;
        boolean nonUnique;

        //continue generating numbers (looping) until we get a unique uuid
        do {
            //generate number
            uuid = "";
            for (int c = 0; c < len; c++){
                uuid +=((Integer)rng.nextInt(10)).toString();
            }

            // check to make sure the ID is unique
            nonUnique = false;
            for (User u : this.users){
                if (uuid.compareTo(u.getUUID()) == 0 ){
                    nonUnique = true;
                    break;
                }
            }
        } while(nonUnique);

        return uuid;

    }

    /**
     * Generate a new uuid for an account
     * @return uuid
     */
    public String getNewAccountUUID(){

        //initialise
        String uuid;
        Random rng = new Random();
        int len = 10;
        boolean nonUnique;

        //continue generating numbers (looping) until we get a unique uuid
        do {
            //generate number
            uuid = "";
            for (int c = 0; c < len; c++){
                uuid +=((Integer)rng.nextInt(10)).toString();
            }

            // check to make sure the ID is unique
            nonUnique = false;
            for (Account a : this.accounts){
                if (uuid.compareTo(a.getUUID()) == 0 ){
                    nonUnique = true;
                    break;
                }
            }
        } while(nonUnique);

        return uuid;

    }

    // adds account into 'accounts'
    public void addAccount(Account anAcct){
        this.accounts.add(anAcct);
    }

    // adds user details into 'user' and 'accounts'
    public User addUser(String firstName, String lastName, String pin){

        //create a new User object and add it to our list
        User newUser = new User(firstName,lastName,pin,this);
        this.users.add(newUser);

        //create a new savings account for the user and add to User and Bank Account lists
        Account newAccount = new Account("Savings", newUser, this);
        newUser.addAccount(newAccount);
        this.accounts.add(newAccount);

        return newUser;

    }

    // validates the login credentials
    public User userLogin(String userID, String pin){

        //search through list of users
        for (User u: this.users){

            //check user ID is correct
            if (u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)){
                return u;
            }
        }

        // if we haven't found the user or have incorrect pin
        return null;
    }

    public String getName(){
        return this.name;
    }
}
