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

    public String getSummaryLine(){

        // get the account balance
        double balance = this.getBalance();

        // format the summary line, depending on the balance is negative or not
        if(balance >= 0){
            return String.format("%s : RM%.02f : %s", this.uuid,balance,this.name);
        }else{
            return String.format("%s : RM(%.02f) : %s", this.uuid,balance,this.name);
        }
    }

    // get the balance of this account by adding the amounts of the transactions
    public double getBalance(){

        double balance = 0;
        for (Transaction t : this.transactions){
            balance += t.getAmount();
        }
        return balance;
    }

    // print the transaction history of the account
    public void printTransHistory(){
        System.out.printf("\n transaction histor for account %s\n", this.uuid);
        for (int t = this.transactions.size()-1; t >= 0; t--){
            System.out.printf(this.transactions.get(t).getSummaryLine());

        }
        System.out.println();
    }
}
