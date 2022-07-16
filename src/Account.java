import java.util.ArrayList;
public class Account {


    //The name of the Account

    private String name;


    //The account ID number

    private String uuid;


    //The user object that owns this account

    private User holder;


    //The list of transaction for this account

    private ArrayList<Transaction> transactions;

    /**
     * Creates a new Account
     * @param name      - the name of the account
     * @param holder    - the User object that holds the account
     * @param theBank   - the Bank that issued the account
     */
    public Account(String name, User holder, Bank theBank){

        // set the account name and holder
        this.name = name;
        this.holder = holder;

        //get new account UUID
        this.uuid = theBank.getNewAccountUUID();

        // initialize transactions
        this.transactions = new ArrayList<Transaction>();

        //add to holder and bank lists;

    }

    /**
     * Return the account's UUID
     * @return uuid
     */
    public String getUUID(){
        return this.uuid;
    }
}
