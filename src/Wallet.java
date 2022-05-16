import java.util.ArrayList;

//the bank -- handles deposits and transacts
public class Wallet {
    //shows balance as a num
    private double balance;

    //balance as an array
    private static ArrayList<String> arr = new ArrayList<String>();


    //Deposit methods for adding coins to wallet
    public void Deposit(String type, double amnt, int type_amnt){
        balance += amnt;

        for (int i = 0; i < type_amnt; i++) {
            arr.add(type);

        }

    }

    public void Deposit(ArrayList<String> types){
        arr.addAll(types);
    }



    //transaction methods for taking coins out of the wallet
    public void Transact(ArrayList<String> types, double amnt){
        arr.removeAll(types);

        balance -= amnt;

    }


    public void Transact(String type, double amnt, int type_amnt){
        //iterate over array and remove the specified coin the specified amount of times
        for (int i = 0; i < type_amnt; i++) {
            arr.remove(type);

        }

        balance -= amnt;

    }

    //encapsulation --> gives access to users balance
    public double get_balance(){
        return balance;
    }

    //gives access to balance in the array form
    public ArrayList<String> get_bank(){
        return arr;
    }
}
