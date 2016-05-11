import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class Panel extends JPanel 
{

    JRadioButton red= new JRadioButton("Red");
    JRadioButton green= new JRadioButton("Green");
    JRadioButton blue= new JRadioButton("Blue");
    JRadioButton srednia= new JRadioButton("Srednia");
    JRadioButton sredniaYUV= new JRadioButton("SredinaYUV");
    
    JRadioButton maska3x3= new JRadioButton("3x3");
    JRadioButton maska5x5= new JRadioButton("5x5");
    Button wczytaj= new Button("Wczytaj");
    Button zapisz= new Button("Zapisz");
    int numer;
    Button zamiana= new Button("Zamiana");
    Button zamianaj= new Button("ZamianaJ");
    Button zamianak= new Button("ZamianaK");
    Button wczytajM=new Button("WczytajM");
    Label k=new Label("k");
    Label wiekszy=new Label("wiekszy");
    Label mniejszy=new Label("mniejszy");
    TextField tekst=new TextField();
    TextField maska1=new TextField();
    TextField maska2=new TextField();
    TextField maska3=new TextField();
    TextField maska4=new TextField();
    TextField maska5=new TextField();
    TextField maska6=new TextField();
    TextField maska7=new TextField();
    TextField maska8=new TextField();
    TextField maska9=new TextField();
    JCheckBox odbmaska= new JCheckBox("Odblokowanie maska");
    JCheckBox progi= new JCheckBox("Progi");

    Label obrot= new Label("Obrót");
    TextField obrotT= new TextField();
    Label skalowanie= new Label("Skalowanie");
    TextField skalowaniex= new TextField();
    TextField skalowaniey= new TextField();
    Label przesuniecie = new Label ("Przesuniecie");
    TextField przesunieciex= new TextField();
    TextField przesunieciey= new TextField();
    
    Button rysuj= new Button("Rysuj");
    Button rysujB= new Button("Krzywa Beziera");
    public Panel() 
    {
        setPreferredSize(new Dimension(600,300));
        setBorder(BorderFactory.createLineBorder(Color.black));

    }

    public Panel(int numer)
    {
        if(numer==1){
            setPreferredSize(new Dimension(600,200));
            setBorder(BorderFactory.createLineBorder(Color.black)); 
            setLayout(null);
            ButtonGroup bg= new ButtonGroup();
            bg.add(red);
            bg.add(green);
            bg.add(blue);
            bg.add(srednia);
            bg.add(sredniaYUV); 
            red.setBounds(50, 50, 100,20);
            add(red);
            green.setBounds(50, 100, 100,20);
            add(green);
            blue.setBounds(50, 150, 100,20);
            add(blue);
            srednia.setBounds(150, 50, 100,20);
            add(srednia);
            sredniaYUV.setBounds(150,100, 100,20);
            add(sredniaYUV);
            wczytaj.setBounds(450, 100,100, 20);
            add(wczytaj);
            zapisz.setBounds(450, 150, 100, 20);
            add(zapisz);
            zamiana.setBounds(450, 50, 100, 20);
            add(zamiana);
        }
        else if(numer==2)
        {
            setPreferredSize(new Dimension(600,200));
            setBorder(BorderFactory.createLineBorder(Color.black)); 
            setLayout(null);
            k.setBounds(50,75,20,20);
            add(k);
            tekst.setBounds(70, 75, 50, 20);
            add(tekst);
            wczytaj.setBounds(450, 50,100, 20);
            add(wczytaj);
            zapisz.setBounds(450, 100, 100, 20);
            add(zapisz);
            zamianaj.setBounds(300, 50, 100, 20);
            add(zamianaj);
            zamianak.setBounds(300, 100, 100, 20);
            add(zamianak);
        }
        else if(numer==3)
        {
            setPreferredSize(new Dimension(600,200));
            setBorder(BorderFactory.createLineBorder(Color.black)); 
            setLayout(null);

            wczytaj.setBounds(450, 50,100, 20);
            add(wczytaj);
            zapisz.setBounds(450, 100, 100, 20);
            add(zapisz);
            zamiana.setBounds(300, 50, 100, 20);
            add(zamiana);

        }
        else if(numer==4)
        {
            setPreferredSize(new Dimension(600,200));
            setBorder(BorderFactory.createLineBorder(Color.black)); 
            setLayout(null);
            maska1.setBounds(50, 50, 50, 20);
            add(maska1);
            maska2.setBounds(100, 50, 50, 20);
            add(maska2);
            maska3.setBounds(150, 50, 50, 20);
            add(maska3);
            maska4.setBounds(50, 70, 50, 20);
            add(maska4);
            maska5.setBounds(100, 70, 50, 20);
            add(maska5);
            maska6.setBounds(150, 70, 50, 20);
            add(maska6);
            maska7.setBounds(50, 90, 50, 20);
            add(maska7);
            maska8.setBounds(100, 90, 50, 20);
            add(maska8);
            maska9.setBounds(150,90, 50, 20);
            add(maska9);
            odbmaska.setBounds(50,120,150,20);
            
            add(odbmaska);
            maska1.setEnabled(false);
            maska2.setEnabled(false);
            maska3.setEnabled(false);
            maska4.setEnabled(false);
            maska5.setEnabled(false);
            maska6.setEnabled(false);
            maska7.setEnabled(false);
            maska8.setEnabled(false);
            maska9.setEnabled(false);
            wczytaj.setBounds(450, 50,100, 20);
            add(wczytaj);
            zapisz.setBounds(450, 100, 100, 20);
            add(zapisz);
            zamiana.setBounds(300, 50, 100, 20);
            add(zamiana);
            wczytajM.setBounds(300,100,100,20);
            add(wczytajM);

        }
        else if(numer==5)
        {
             setPreferredSize(new Dimension(600,200));
            setBorder(BorderFactory.createLineBorder(Color.black)); 
            setLayout(null);
            ButtonGroup bg= new ButtonGroup();
            maska3x3.setBounds(50,50, 100, 20);
            maska3x3.setSelected(true);
            add(maska3x3);
            
            maska5x5.setBounds(50,70,100,20);
            add(maska5x5);
            bg.add(maska3x3);
            bg.add(maska5x5);
             wczytaj.setBounds(450, 50,100, 20);
            add(wczytaj);
            zapisz.setBounds(450, 100, 100, 20);
            add(zapisz);
            zamiana.setBounds(300, 50, 100, 20);
            add(zamiana);
        }
         else if(numer==6)
        {
             setPreferredSize(new Dimension(600,200));
            setBorder(BorderFactory.createLineBorder(Color.black)); 
            setLayout(null);
            mniejszy.setBounds(50, 50, 50, 20);
            add(mniejszy);
            wiekszy.setBounds(150, 50, 50, 20);
            add(wiekszy);
            progi.setBounds(50, 90, 100, 20);
            add(progi);
            maska4.setBounds(50, 70, 50, 20);
            maska4.setEnabled(false);
            add(maska4);
            maska6.setBounds(150, 70, 50, 20);
            maska6.setEnabled(false);
            add(maska6);
            wczytaj.setBounds(450, 50,100, 20);
            add(wczytaj);
            zapisz.setBounds(450, 100, 100, 20);
            add(zapisz);
            zamiana.setBounds(300, 50, 100, 20);
            add(zamiana);
        }
        
         else if(numer==7)
        {
             setPreferredSize(new Dimension(600,200));
            setBorder(BorderFactory.createLineBorder(Color.black)); 
            setLayout(null);
           obrot.setBounds(50,50,100,20);
           add(obrot);
           obrotT.setBounds(150,50,100,20);
           add(obrotT);
           przesuniecie.setBounds(50,90,100,20);
           add(przesuniecie);
           przesunieciex.setBounds(150,90,45,20);
           add(przesunieciex);
           przesunieciey.setBounds(205,90,45,20);
           add(przesunieciey);
           skalowanie.setBounds(50,130,100,20);
           add(skalowanie);
           skalowaniex.setBounds(150,130,45,20);
           add(skalowaniex);
           skalowaniey.setBounds(205,130,45,20);
           add(skalowaniey);
           rysuj.setBounds(300,50,100,20);
           add(rysuj);
           rysujB.setBounds(300,90,100,20);
           add(rysujB);
        }
    }

    public void wczytajPliktekstowy(String sciezka)
    {
        //obiekt reprezentujący plik graficzny o podanej ścieżce
        File plik= new File(sciezka);

        int i=0;
        try {
            Scanner in= new Scanner(plik);
            while(in.hasNext())
            {
                if(i==0)
                {    
                    String text=in.nextLine();
                    maska1.setText(text);
                }
                if(i==1)
                {    
                    String text=in.nextLine();
                    maska2.setText(text);
                }
                if(i==2)
                {    
                    String text=in.nextLine();
                    maska3.setText(text);
                }
                if(i==3)
                {    
                    String text=in.nextLine();
                    maska4.setText(text);
                }
                if(i==4)
                {    
                    String text=in.nextLine();
                    maska5.setText(text);
                }
                if(i==5)
                {    
                    String text=in.nextLine();
                    maska6.setText(text);
                }
                if(i==6)
                {    
                    String text=in.nextLine();
                    maska7.setText(text);
                }
                if(i==7)
                {    
                    String text=in.nextLine();
                    maska8.setText(text);
                }
                if(i==8)
                {    
                    String text=in.nextLine();
                    maska9.setText(text);
                }
                i++;
            }  
            repaint();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,"Blad odczytu pliku: " + sciezka); 

        }
    }  

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

    }    

}
