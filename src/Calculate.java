import java.util.ArrayList;

public class Calculate {

    public static ArrayList<String> dues(ArrayList<String> arr, double amnt){

        ArrayList<String> dues = new ArrayList<>();

        for (int i = 0; i < arr.size(); i++) {

            double value = 0;
            switch (arr.get(i)) {
                case "Dime" -> value = 0.10;
                case "Quarter" -> value = 0.25;
                case "Nickle" -> value = 0.05;
                case "Toonie" -> value = 2.00;
                case "Loonie" -> value = 1.00;
                case "Penny" -> value = 0.01;

            }

            if(amnt>0){
            amnt -= value;
            dues.add(arr.get(i));
            }


        }

        return dues;

    }

    public static ArrayList<String> get_change(double price, ArrayList<String> payment){
        ArrayList<String> change = new ArrayList<>();

        for (int i = 0; i < payment.size(); i++) {
            double value = 0;
            switch (payment.get(i)) {
                case "Dime" -> value = 0.10;
                case "Quarter" -> value = 0.25;
                case "Nickle" -> value = 0.05;
                case "Toonie" -> value = 2.00;
                case "Loonie" -> value = 1.00;
                case "Penny" -> value = 0.01;

            }

            price -= value;
        }

        price = Math.abs(price);



        return to_coins(price);
    }

    //gets a num and turns to coins
    public static ArrayList<String> to_coins(double amnt){
        //set up new array to return the change
        ArrayList<String> change = new ArrayList<>();
        double dues = amnt;


        //checking if when the value is divisible by coin val and greater than zero, if quotient greater than zeroo that is num of coins that the fit into that value

        int Toonies = (amnt/2>0)? (int) Math.floor(amnt / 2) : 0;
        dues -= 2*Toonies;

        int Loonies = (dues>0)? (int) Math.floor(dues) : 0;
        dues -= Loonies;

        int Quarters = (dues/0.25 >0)? (int) Math.floor(dues/0.25) : 0;
        System.out.println(Math.floor(dues/0.25));
        dues -= Quarters*0.25;

        int Dimes = (dues/0.10 >0)? (int) Math.floor(dues/0.10) : 0;
        dues -= Dimes*0.10;

        int Nickles = (dues/0.05 >0)? (int) Math.floor(dues/0.05) : 0;
        dues -= Nickles*0.05;

        int Pennies = (dues/0.01 >0)? (int) Math.floor(dues/0.01) : 0;
        dues -= Pennies*0.01;

        //paralle arraw w types of coins and teh amtn of coins needed to meet val
        String[] types = {"Toonies","Loonies","Quarters","Nickles","Dimes","Pennies"};
        int[] nums = {Toonies,Loonies,Quarters,Nickles,Dimes,Pennies};

        //iterates over coins li, and if num of that coin is greater than 0 iteratee over nums list to add coins type to change li x amoutn of times
        for (int i = 0; i < types.length; i++) {
            if(nums[i]>0){
                for(int j = 0; j < nums[i]; j++){
                    change.add(types[i]);
                }
            }


        }


        return change;


    }

    //given a wallet array, will return teh largest coin
    public static String get_largest(ArrayList<String> wallet){
        //set the max coin as they first obj in arr
        String max = wallet.get(0);

        for(int i=0; i<wallet.size(); i++){
            //get obj and index x and get the value of that coin --? if val of coin is greater than val of max, make that coin the max

            String temp = wallet.get(i);
            if(get_val(temp)>get_val(max)){

                max = temp;
            }
        }

        return max;

    }


    //given a wallet array, will return the smallest coin
    public static String get_smallest(ArrayList<String> wallet){

        String min = wallet.get(0);

        for(int i=0; i<wallet.size(); i++){
            //get obj and index x and get the value of that coin --? if val of coin is less than val of min, make that coin the min
            String temp = wallet.get(i);

            if(get_val(temp)<get_val(min)){
                min = temp;
            }
        }


        return min;

    }


    //conversion of coin vals
    public static double get_val(String coin_name){

        double val = 0.00;

        switch (coin_name) {
            case "Dime" -> val = 0.10;
            case "Nickle" -> val = 0.05;
            case "Toonie" -> val = 2.00;
            case "Loonie" -> val = 1.00;
            case "Penny" -> val = 0.01;
        }
        return val;
    }

}
