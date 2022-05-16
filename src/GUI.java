import javax.swing.*;

import java.awt.*;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI {
    public static JPanel container = new JPanel();

    //main frames for each new card layout
    public static JFrame frame = new JFrame();
    public static JPanel deposit_frame = new JPanel();
    public static JPanel transac_frame = new JPanel();
    public static JPanel bank_frame = new JPanel();
    public static JPanel buy_frame = new JPanel();



    public static JPanel pane = new JPanel(new GridBagLayout());
    public static JPanel panel = new JPanel(new GridLayout(4, 1, 10, 5));
    public static CardLayout cl = new CardLayout();





    public static double value = 0.00;
    public static int amnt = 0;
    public static Wallet wallet = new Wallet();


    public GUI() {

        //buttons that direct transiton between jpanels
        JButton bank = new JButton("View Bank");
        JButton deposit = new JButton("Deposit");
        JButton transac = new JButton("Withdraw");
        JButton buy_items = new JButton("Buy Items");

        //switch layout of main panel to cardlayout
        container.setLayout(cl);


        deposit_frame.add(Deposit());
        transac_frame.add(Transac());
        buy_frame.add(Buy());

        //add the diff screens to the cardlayout
        container.add(deposit_frame,"DEPO");
        container.add(transac_frame,"TRANSAC");
        container.add(bank_frame,"SHOW");
        container.add(buy_frame,"BUY");


        cl.show(container,"DEPO");



        //add action listeners to buttons to switch between panels

        deposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(container, "DEPO");

            }

        });

        transac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(container, "TRANSAC");

            }
        });

        bank.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bank_frame.removeAll();
                bank_frame.add(showBalance());
                cl.show(container,"SHOW");
            }
        });

        buy_items.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                cl.show(container,"BUY");
            }
        });


        panel.add(bank);
        panel.add(deposit);
        panel.add(transac);
        panel.add(buy_items);


        pane.add(panel);
        frame.getContentPane().add(pane, BorderLayout.LINE_START);
        frame.getContentPane().add(container, BorderLayout.CENTER);
        frame.setSize(600,400);
        frame.setVisible(true);
    }



    //the transaction frame
    public Component Transac(){
        //possibal coins to choose
        String[] options = {"Dime", "Nickel", "Toonie", "Loonie", "Penny", "Quarter"};


        JPanel main = new JPanel(new BorderLayout());
        JPanel label = new JPanel();


        JComboBox choose_coin = new JComboBox(options);
        JPanel trans1 = new JPanel(new GridBagLayout());
        JPanel trans = new JPanel(new GridLayout(1, 3, 10, 5));
        JPanel submit = new JPanel();


        JButton add = new JButton("+");
        JButton sub = new JButton("-");
        JButton subm = new JButton("Withdraw");

        JLabel sum = new JLabel(" "+Math.round(value*100.0)/100.0+" ");


        subm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String coin_name = (String) choose_coin.getSelectedItem();
                assert coin_name != null;

                Coins coin = new Coins(coin_name,amnt);
                double val = coin.value;

                //TRANSACT
                wallet.Transact(coin_name,val,amnt);
            }
        });


        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String chosen = (String) choose_coin.getSelectedItem();
                assert chosen != null;
                //GET VAL OF CHOSEN COINS
                value += Calculate.get_val(chosen);
                amnt ++;
                sum.setText(" "+Math.round(value*100.0)/100.0+" ");
            }
        });

        sub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String chosen = (String) choose_coin.getSelectedItem();
                assert chosen != null;
                //GET VAL OF CHOSEN COINS
                value -= Calculate.get_val(chosen);
                amnt--;
                //check if coin val and amount of coins is less than 0
                if(value<0){
                    value = 0;
                }
                if(amnt<0){
                    amnt = 0;
                }
                sum.setText(" "+value+" ");
            }
        });

        choose_coin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                value = 0.00;
                amnt = 0;
            }
        });

        //set up transactions
        trans.add(add);
        trans.add(choose_coin);
        trans.add(sub);
        trans1.add(trans);

        label.add(sum);

        submit.add(subm);

        main.add(label,BorderLayout.NORTH);
        main.add(trans1,BorderLayout.CENTER);
        main.add(submit,BorderLayout.SOUTH);
        JPanel n = new JPanel();
        n.add(main);


        return n;
    }

    //the deposit frame
    public Component Deposit(){
        //coin choices
        String[] options = {"Dime", "Nickel", "Toonie", "Loonie", "Penny", "Quarter"};


        JPanel main = new JPanel(new BorderLayout());
        JPanel label = new JPanel();


        JComboBox choose_coin = new JComboBox(options);
        JPanel deposit1 = new JPanel(new GridBagLayout());
        JPanel deposit = new JPanel(new GridLayout(1, 3, 10, 5));
        JPanel submit = new JPanel();


        JButton add = new JButton("+");
        JButton sub = new JButton("-");
        JButton subm = new JButton("Deposit");

        JLabel sum = new JLabel(" "+value+" ");


        subm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String coin_name = (String) choose_coin.getSelectedItem();
                assert coin_name != null;
                Coins coin = new Coins(coin_name,amnt);
                double val = coin.value;

                //DEPOSIT TO WALLET
                wallet.Deposit(coin_name,val,amnt);

            }
        });


        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String chosen = (String) choose_coin.getSelectedItem();
                assert chosen != null;
                value += Calculate.get_val(chosen);
                amnt ++;
                sum.setText(" "+Math.round(value*100.0)/100.0+" ");
            }
        });

        sub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String chosen = (String) choose_coin.getSelectedItem();
                assert chosen != null;
                value -= Calculate.get_val(chosen);
                amnt--;

                //check if coin val and amoutn of coins is less than 0
                if(value<0){
                    value = 0;
                }
                if(amnt<0){
                    amnt = 0;
                }
                sum.setText(" "+Math.round(value*100.0)/100.0+" ");
            }
        });

        choose_coin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                value = 0.00;
                amnt = 0;
            }
        });

        //HANDLES MAIN DEPOSITS
        deposit.add(add);
        deposit.add(choose_coin);
        deposit.add(sub);
        deposit1.add(deposit);

        label.add(sum);

        submit.add(subm);

        main.add(label,BorderLayout.NORTH);
        main.add(deposit,BorderLayout.CENTER);
        main.add(submit,BorderLayout.SOUTH);
        JPanel n = new JPanel();
        n.add(main);


        return n;
    }

    //show balance frame
    public Component showBalance(){

        JPanel main = new JPanel();

        JTextArea balance = new JTextArea("BALANCE:"+Math.round(wallet.get_balance()*100.0)/100.0+"\n" +
                "LARGEST COIN:"+Calculate.get_largest(wallet.get_bank())+"\n" +
                "SMALLEST COIN: "+Calculate.get_smallest(wallet.get_bank())+" ");

        main.add(balance);

        return main;
    }

    //for buying diff items
    public Component Buy(){
        JPanel main = new JPanel();

        //list of buyable items
        String[] items = {
                "Shoes","Stale Milk","Eraser",
                "Lambo","Watch","Singular pea",
                "Wig","Cookies","A Hat"};

        //para array for prices
        double[] prices = {
                1.09,0.87,3.56,
                100000.90,367.98,65.00,
                32.00,15.67,45.89
        };

        JPanel main2 = new JPanel(new BorderLayout());
        JPanel label = new JPanel();

        JPanel catalog = new JPanel(new GridBagLayout());
        JPanel catlog = new JPanel(new GridLayout(3, 3, 10, 5));

        //adds buttons for items to screen for user to click n buy
        for (int i = 0; i < items.length; i++) {
            JButton item = new JButton(" "+items[i]+"\n $"+prices[i]+"");

            int finalI = i;

            //on clcik of button purchase item
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    double cost = wallet.get_balance();

                    cost -= prices[finalI];

                    //if cost is less than 0 then tell user they need more money
                    if(cost<0){
                        JDialog dia = new JDialog();
                        dia.add(new JLabel("You're broke. Please aquire "+Math.round(Math.abs(cost)*100.0)/100.0+" "));
                        JButton b = new JButton ("OK");
                        b.addActionListener ( new ActionListener()
                        {
                            public void actionPerformed( ActionEvent e )
                            {
                                dia.setVisible(false);

                            }
                        });



                        dia.setSize(300,200);
                        dia.setVisible(true);
                    }

                    //if user has enough money
                    else {
                        //gets the coins we need to pay
                        ArrayList<String> payment = Calculate.dues(wallet.get_bank(),prices[finalI]);

                        //gets the change
                        ArrayList<String> change = Calculate.get_change(prices[finalI],payment);

                        wallet.Transact(payment, prices[finalI]);
                        wallet.Deposit(change);


                        JDialog dia = new JDialog();



                        dia.add(new JTextArea(" Sucess! Here is your change\n "+change+"\nYou payed with:\n "+payment+" "));
                        JButton b = new JButton ("OK");
                        b.addActionListener ( new ActionListener()
                        {
                            public void actionPerformed( ActionEvent e )
                            {
                                dia.setVisible(false);

                            }
                        });



                        dia.setSize(300,200);
                        dia.setVisible(true);
                    }
                }
            });
            catlog.add(item);


        }

        catalog.add(catlog);
        main2.add(catalog,BorderLayout.CENTER);
        main.add(main2);




        return main;
    }
}
