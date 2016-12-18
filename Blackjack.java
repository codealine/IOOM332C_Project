package blackjack;

import java.awt.*;
import java.io.File;

import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Blackjack extends JFrame {

    JPanel startOption;
    JPanel gameField;
    JPanel topDisplay;
    JPanel bottomButton;
    JPanel sideButton;
    JButton start;
    JButton [] bottom;
    JLabel display;
    JLabel [] stats;
    JLabel [] cards;
    JButton reset;
    
    JButton[] bet;
    int wallet;
    int betAmt,totalBet;
    int totalWin;
    BorderLayout layout;
    int row,col;
    int xz;
    int val,s;
    Random r = new Random();
   
    int playerScore, dealerScore;
    
    String values = "a23456789tjqk";
    String suits = "shdc";
    
    public static void main(String[] args) {
        
       Blackjack bjk = new Blackjack();
       
       bjk.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    void initialize(){
        wallet = 10000;
        totalWin=0;
        row=0;
        col=0;
        xz=6;
        totalBet=0;
    }
    
    Blackjack(){
        
      
        
        
           try {
            // select Look and Feel
            UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
            // start application
            
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        
       
     
        wallet = 10000;
        totalWin=0;
        row=0;
        col=0;
        xz=6;
        start = new JButton("START");
        display = new JLabel("WELCOME",JLabel.CENTER);
        bottom = new JButton[2];
        bottom[0] = new JButton("HIT");
        bottom[1] = new JButton("STAY");
        
        bet = new JButton[4];
        bet[0] = new JButton("$100");
        bet[1] = new JButton("$500");
        bet[2] = new JButton("$1000");
        bet[3] = new JButton("$10000");
        bottom[0].setEnabled(false);
        bottom[1].setEnabled(false);
       
        stats = new JLabel[5];
        stats[0] = new JLabel(" Last Hand Total: $0");
        stats[1] = new JLabel(" Bet Total: -");
        stats[2] = new JLabel(" Money left: $10000");
        stats[3] = new JLabel(" Total win: 0");
        cards = new JLabel[10];
        reset= new JButton("Reset");
        
        startOption = new JPanel();
        gameField = new JPanel();
        topDisplay = new JPanel();
        sideButton = new JPanel();
        bottomButton = new JPanel();
        
        startOption.setLayout(new GridLayout(1,1));
        topDisplay.setLayout(new GridLayout(1,1));
        gameField.setLayout(new GridLayout(2,5));
        sideButton.setLayout(new GridLayout(5,1));
        bottomButton.setLayout(new GridLayout(1,2));
        int red = 34;
        int green = 193;
        int blue = 13;
        Color myBlue = new Color(red,green,blue);
        gameField.setBackground(myBlue);
        
        layout = new BorderLayout();
   
        add(startOption, layout.WEST);
        add(topDisplay, layout.NORTH);
        add(bottomButton, layout.SOUTH);
        add(gameField, layout.CENTER);
        add(sideButton, layout.EAST);
        
        
        
        for(int xyz=0; xyz<10; xyz++){
        cards[xyz] = new JLabel("",JLabel.CENTER);
        gameField.add(cards[xyz]);
        }
        
         
            
        for(int i = 0 ; i < 2 ; i++) {
            bottomButton.add(bottom[i]);
        }
        for(int j = 0 ; j < 4 ; j++) {
            sideButton.add(stats[j]);
            
        }
       
        
        
        topDisplay.add(display);
        startOption.add(start);
        setTitle("21");
        setSize(1366,768);
        setVisible(true);
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });
        bottom[0].addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hitActionPerformed(evt);
            }
        });
        
        bottom[1].addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stayActionPerformed(evt);
            }
        });
        
         
        
        start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startActionPerformed(evt);
            }
        });
        
       
        
        bet[0].addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                betAmt = 100;
                totalBet+=betAmt;
                betActionPerformed(evt);
                }
        });
        
        bet[1].addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                betAmt = 500;
                totalBet+=betAmt;
                betActionPerformed(evt);
                
                }
        });
        
        bet[2].addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                betAmt = 1000;
                totalBet+=betAmt;
                betActionPerformed(evt);
                          
            }
        });
        
        bet[3].addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                betAmt = 10000;
                totalBet+=betAmt;
                betActionPerformed(evt);
                          
            }
        });
        
        
   
    
    }
    
    private void resetActionPerformed(java.awt.event.ActionEvent evt) { 
        initialize();
         stats[0].setText(" Last Hand Total: $0");
        stats[1].setText(" Bet Total: -");
        stats[2].setText(" Money left: $10000");
        stats[3].setText(" Total win: 0");
        start.setEnabled(true);
    }
    
    private void betActionPerformed(java.awt.event.ActionEvent evt) { 
       
         wallet-=betAmt;
        
        if(wallet<100)
            bet[0].setEnabled(false);
         if(wallet<500)
            bet[1].setEnabled(false);
          if(wallet<1000)
            bet[2].setEnabled(false);
          if(wallet<10000)
               bet[3].setEnabled(false);
       
        stats[2].setText(" Money Left: $"+wallet);
        stats[1].setText(" Bet Total: $"+totalBet);
    
    }
    
    private void hitActionPerformed(java.awt.event.ActionEvent evt) { 
       
        if(xz>9){
            bottom[0].setEnabled(false);
            bottom[1].doClick();
        }
            
        
               
            val = r.nextInt(13);
            s = r.nextInt(4);
            
          
               
            
            //gameField.add(cards[xz]);
            cards[xz].setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/Classic/"+suits.charAt(s)+values.charAt(val)+".png")).getImage().getScaledInstance(180, 250, Image.SCALE_SMOOTH)));
            if(val>8)
                val=10;
            else{
                if(val==0)
                       val=11;
                else
                    val++;
            }
            
            playerScore += val;
            
            display.setText("Dealer score: "+dealerScore+"  Player Score: "+playerScore);
            
          
                
            
            xz++;
            
            if(playerScore > 21){
                
                
                JOptionPane.showMessageDialog(null,"Bust!");
                totalWin-=totalBet;
                bottom[0].setEnabled(false);
                bottom[1].setEnabled(false);
                
                
                
                stats[0].setText(" Last Hand Total: $"+totalBet+" (Lost)");
                stats[3].setText(" Total Won: $"+totalWin);
                if(wallet>=100)
                start.setEnabled(true);
                if(wallet < 100){
                JOptionPane.showMessageDialog(null, "Live in a basement");
                
                JOptionPane.showMessageDialog(null, reset);
                }
                else
                    start.setEnabled(true);
                
            
            }
            
            }
    
    private void stayActionPerformed(java.awt.event.ActionEvent evt) { 
        
        int pos=1;
        int var1,var2;
        var1 = r.nextInt(13);
        var2 = r.nextInt(4);
        while(dealerScore<18)
        {
            cards[pos].setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/Classic/"+suits.charAt(var2)+values.charAt(var1)+".png")).getImage().getScaledInstance(180, 250, Image.SCALE_SMOOTH)));
        //cards[pos].setIcon(new javax.swing.ImageIcon(getClass().getResource("/cards/"+values.charAt(var1)+suits.charAt(var2)+".gif")));    
        pos++;
        if(pos==5){
            
            break;
        }
        if(var1>8)
                var1=10;
            else{
                if(var1==0)
                       var1=11;
                else
                    var1++;
            }
            
        dealerScore+=var1;
         display.setText("Dealer score: "+dealerScore+"  Player Score: "+playerScore);
         var1 = r.nextInt(13);
        var2 = r.nextInt(4);
        }
        
        bottom[0].setEnabled(false);
        bottom[1].setEnabled(false);
        
        if(dealerScore > 21 || dealerScore < playerScore)
        {   wallet+=(2*totalBet);
           // System.out.println(totalBet);
            if(playerScore == 21)
                wallet += (0.5*totalBet); 
            totalWin+=totalBet;
             stats[2].setText(" Money Left: $"+wallet);
             
             
             if(playerScore==21){
                 totalWin+=(0.5*totalBet);
                   stats[0].setText(" Last Hand Total: $"+(1.5*totalBet)+" (Won)");
             }
             else
                 stats[0].setText(" Last Hand Total: $"+totalBet+" (Won)");
             
                 stats[3].setText(" Total Won: $"+totalWin);
             if(playerScore==21)
            JOptionPane.showMessageDialog(null, "Blackjack! New Total: "+wallet);
             else
            JOptionPane.showMessageDialog(null, "Player wins! New Total: "+wallet);
            start.setEnabled(true);
                
        }
        else
            if(dealerScore == playerScore){
                
                wallet+=totalBet;
                 stats[2].setText(" Money Left: $"+wallet);
                 stats[0].setText(" Last Hand Total: Breakeven");
                 JOptionPane.showMessageDialog(null, "Push");
                 start.setEnabled(true);
            }else
        {   totalWin-=totalBet;
            stats[0].setText(" Last Hand Total: $"+totalBet+" (Lost)");
            stats[3].setText(" Total Won: $"+totalWin);
            JOptionPane.showMessageDialog(null, "Dealer wins!");
            
            if(wallet < 100){
                JOptionPane.showMessageDialog(null,"Game over");
                JOptionPane.showMessageDialog(null, reset);
           
            }
            else
                start.setEnabled(true);
        }
        
        
        
        
        
    }
    
    private void startActionPerformed(java.awt.event.ActionEvent evt) { 
        
        start.setEnabled(false);
        
        totalBet=betAmt=0;
        stats[1].setText(" Bet Total: $"+totalBet);
        
        bet[0].setEnabled(false);
        bet[1].setEnabled(false);
        bet[2].setEnabled(false);
        bet[3].setEnabled(false);
        
        if(wallet >= 100){
            
            bet[0].setEnabled(true);
        }
        
        if(wallet >= 500)
        {    bet[1].setEnabled(true);
            
        }
        
        if(wallet >= 1000)
        {   bet[2].setEnabled(true);
            
        }
        
        if(wallet >= 10000)
        {   bet[3].setEnabled(true);
            
        } 
        
        
        int var1 = r.nextInt(13);
        int var2 = r.nextInt(4);
        dealerScore = playerScore=0;
        display.setText("Dealer score: "+dealerScore+"  Player Score: "+playerScore);
        for(int i =0; i< 10;i++)
        {
            cards[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("")));
        }
        while(betAmt==0)
        JOptionPane.showMessageDialog(null, bet);
        
        cards[0].setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/Classic/"+suits.charAt(var2)+values.charAt(var1)+".png")).getImage().getScaledInstance(180, 250, Image.SCALE_SMOOTH)));
         
        if(var1>8)
                var1=10;
            else{
                if(var1==0)
                       var1=11;
                else
                    var1++;
            }
        dealerScore+=var1;
        var1 = r.nextInt(13);
        var2 = r.nextInt(4);
        
        
       cards[5].setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/Classic/"+suits.charAt(var2)+values.charAt(var1)+".png")).getImage().getScaledInstance(180, 250, Image.SCALE_SMOOTH)));
    cards[1].setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/Classic/b.png")).getImage().getScaledInstance(180, 250, Image.SCALE_SMOOTH)));
        //cards[1].setIcon(new javax.swing.ImageIcon(getClass().getResource("/cards/b.gif")));
        if(var1>8)
                var1=10;
            else{
                if(var1==0)
                       var1=11;
                else
                    var1++;
            }
        playerScore += var1;
        display.setText("Dealer score: "+dealerScore+"  Player Score: "+playerScore);
        xz=6;
        
        
        bottom[0].setEnabled(true);
        bottom[1].setEnabled(true);
        
        
        
        
    }

}