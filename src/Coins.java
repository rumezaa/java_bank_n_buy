//handles coin conversion
public class Coins {
    public double value;

    public Coins(String coin_name, int amnt) {

        switch (coin_name) {
            case "Dime" -> value = 0.10 * amnt;
            case "Quarter" -> value = 0.25 * amnt;
            case "Nickle" -> value = 0.05 * amnt;
            case "Toonie" -> value = 2.00 * amnt;
            case "Loonie" -> value = 1.00 * amnt;
            case "Penny" -> value = 0.01 * amnt;
        }
    }










}
