import java.io.*;
import javax.imageio.*;

import java.awt.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.BorderFactory;
import java.io.FileNotFoundException;

public class MojeOkno extends JFrame implements ActionListener , MouseListener 
{
    //Tworzymy panele okna głównego
    PanelGraficzny pgl = new PanelGraficzny();
    PanelGraficzny pgp = new PanelGraficzny();   
    Panel p= new Panel();
    Panel p1=new Panel(1);
    Panel p2= new Panel(2);
    Panel p3= new Panel(3);
    Panel p4= new Panel(4);
    Panel p5= new Panel(5);
    Panel p6= new Panel(6);
    Panel p7= new Panel(7);
    GridBagLayout gbl= new GridBagLayout();
    GridBagConstraints gbc= new GridBagConstraints();
    //Tworzymy menu okna 
    Menu menu = new Menu();
    //scieżka dostępu wraz z nazwą pliku
    String sciezkaDoPlik;    
    Punkt  tab[]= new Punkt[1];

    public MojeOkno() throws FileNotFoundException{
        super("Grafika komputerowa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable (false);

        setLayout(gbl);

        gbc.gridx=0;
        gbc.gridy=0;
        add(pgl,gbc);

        gbc.gridx=1;
        add(pgp,gbc);

        gbc.gridy=1;
        gbc.gridx=0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        add(p,gbc);
        // setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
        setJMenuBar(menu);
        ustawNasluchZdarzen();        
        dopasujSieDoZawartosci();
        setVisible(true);
    }

    public void ustawNasluchZdarzen()
    {
        p1.wczytaj.addActionListener(this);
        p1.zapisz.addActionListener(this);
        p2.zapisz.addActionListener(this);
        p1.red.addActionListener(this);
        p1.zamiana.addActionListener(this);
        p1.green.addActionListener(this);
        p1.blue.addActionListener(this);
        p1.srednia.addActionListener(this);
        p1.sredniaYUV.addActionListener(this);

        p2.tekst.addActionListener(this);
        p2.wczytaj.addActionListener(this);
        p2.zamianaj.addActionListener(this);
        p2.zamianak.addActionListener(this);

        p3.wczytaj.addActionListener(this);
        p3.zapisz.addActionListener(this);
        p3.zamiana.addActionListener(this);

        p4.wczytaj.addActionListener(this);
        p4.zapisz.addActionListener(this);
        p4.zamiana.addActionListener(this);
        p4.wczytajM.addActionListener(this);
        p4.odbmaska.addActionListener(this);

        p5.wczytaj.addActionListener(this);
        p5.zapisz.addActionListener(this);
        p5.zamiana.addActionListener(this);
        p5.maska3x3.addActionListener(this);
        p5.maska5x5.addActionListener(this);

        p6.zamiana.addActionListener(this);
        p6.progi.addActionListener(this);

        p7.rysuj.addActionListener(this);

        menu.wczytaj.addActionListener(this); 
        menu.zapisz.addActionListener(this); 
        menu.zamknij.addActionListener(this);  

        menu.metoda1.addActionListener(this);
        menu.metoda2.addActionListener(this);     
        menu.metoda3.addActionListener(this);  
        menu.metoda4.addActionListener(this);
        menu.metoda5.addActionListener(this);
        menu.metoda6.addActionListener(this);
        menu.krzywaB.addActionListener(this);
        menu.wyczyscl.addActionListener(this);
        menu.wyczyscp.addActionListener(this);

        pgl.addMouseListener(this);
    }
    int licznik=0;

    @Override
    public void mouseClicked(MouseEvent e)
    {

        Graphics gr= pgl.getGraphics();
        gr.setColor(Color.BLACK);
        gr.drawOval(e.getX(),e.getY(),1,1);
        if(licznik>=tab.length)
        {
            Punkt tab1[]=zwiekszTablice(tab);
            tab=tab1;
        }
        tab[licznik]=new Punkt (e.getX(),e.getY());

        licznik++;
        // gr.drawLine(20,20,200,200);
    }

    @Override
    public void mouseExited(MouseEvent e){}

    @Override
    public void mouseEntered(MouseEvent e){}

    @Override
    public void mouseReleased(MouseEvent e){}

    @Override
    public void mousePressed(MouseEvent e){}

    public Punkt[] zwiekszTablice(Punkt tab[])
    {
        Punkt temp[]= new Punkt[tab.length+1];
        for(int i=0;i<tab.length;i++)
        {
            temp[i]=tab[i];

        }
        return temp;
    }

    public double TextDouble(TextField t)
    {
        String x = t.getText();
        return Double.parseDouble(x);
    }

    public Punkt[] przemieszczenie(double x1, double y1)
    {
        double tabprzesuniecie[][] = new double [3][3];

        for(int i=0;i<tabprzesuniecie.length;i++)
        {
            for(int j=0;j<tabprzesuniecie.length;j++)
            {
                if((j==0 && i==2) )
                {
                    tabprzesuniecie[i][j]=x1; 
                }
                else if((j==1 && i==2))
                {
                    tabprzesuniecie[i][j]=y1; 
                }

                else if(j==0 && i==0 || j==1 && i==1 || j==2 && i==2)
                {
                    tabprzesuniecie[i][j]=1;
                }
                else
                {
                    tabprzesuniecie[i][j]=0;
                }
                System.out.print(tabprzesuniecie[i][j]+ ";");
            }
            System.out.println();
        }
        Punkt tabpunkt[]=new Punkt[tab.length];
        tabpunkt= tab;
        for(int i=0; i<tabpunkt.length; i++)

        {

            tabpunkt[i]=tabpunkt[i].mnozenieMacierzy(tabprzesuniecie);

        }
        return tabpunkt;
    }

    public Punkt[] skalowanie(double x1, double y1, Punkt tab2[])
    {
        double tabskalowanie[][] = new double [3][3];
        double x=tab2[0].getX();
        double y=tab2[0].getY();
        for(int i=0;i<tabskalowanie.length;i++)
        {
            for(int j=0;j<tabskalowanie.length;j++)
            {
                if((j==0 && i==0) )
                {
                    tabskalowanie[i][j]=x1; 
                }
                else if((j==1 && i==1))
                {
                    tabskalowanie[i][j]=y1; 
                }

                else if(j==2 && i==2 )
                {
                    tabskalowanie[i][j]=1;
                }
                else
                {
                    tabskalowanie[i][j]=0;
                }

            }

        }
        Punkt tabpunkt[]=new Punkt[tab.length];
        tabpunkt= tab;
        tabpunkt=przemieszczenie(-x,-y);
        for(int i=0; i<tabpunkt.length; i++)

        {

            tabpunkt[i]=tabpunkt[i].mnozenieMacierzy(tabskalowanie);

        }
        tabpunkt=przemieszczenie(x,y);
        return tabpunkt;
    }   

    public Punkt[]  obrot(TextField obrot, Punkt tab2[])
    {
        double tabobrot[][]= new double [3][3];
        double tab1[][]= new double [3][3];
        String k=obrot.getText();
        // System.out.println(k);
        double stopnie=Double.parseDouble(k);
        // System.out.println(stopnie);
        double radian=Math.toRadians(stopnie);
        double x=tab2[0].getX();
        double y=tab2[0].getY();
        //  System.out.println(radian);

        for(int i=0;i<tabobrot.length;i++)
        {
            for(int j=0;j<tabobrot.length;j++)
            {
                if((j==0 && i==0) ||   (j==1 && i==1))
                {
                    tabobrot[i][j]=Math.cos(radian); 
                }
                else if((j==1 && i==0))
                {
                    tabobrot[i][j]=Math.sin(radian); 
                }
                else if((j==0 && i==1))
                {
                    tabobrot[i][j]=-Math.sin(radian);
                }
                else if(j==2 && i==2)
                {
                    tabobrot[i][j]=1;
                }
                else
                {
                    tabobrot[i][j]=0;
                }

            }

        }
        Punkt tabpunkt[]=new Punkt[tab2.length];
        tabpunkt= tab2;
        tabpunkt=przemieszczenie(-x,-y);
        for(int i=0; i<tabpunkt.length; i++)

        {
            tabpunkt[i]=tabpunkt[i].mnozenieMacierzy(tabobrot);
            System.out.println(tabpunkt[i].getX()+";"+tabpunkt[i].getY());
        }
        tabpunkt=przemieszczenie(x,y);
        return tabpunkt;
    }
    /*
    public Punkt odejmij(Punkt punkt1,Punkt punkt2)
    {
        double x1=punkt1.getX()-punkt2.getX();
        double y1=punkt1.getY()-punkt2.getY();
        return new Punkt(x1,y1);
    }
    */
    public Punkt[] Krzywe(double dt,Punkt tab2[])
    {
        /*
        dt – skok parametru (dokładność rysowania)
        n – stopień wielomianu (liczba punktów wiodących -1)
        Pi – punkty wiodące
        m – stopień wielomianu zmniejszający się w każdej iteracji
        Ri – punkty pośrednie w każdej iteracji
        Qi – chwilowa wartość Ri
         */
        int n=(tab2.length)-1;
        int m=0;
        Punkt R[]= new Punkt[tab2.length];
        Punkt P[]= new Punkt[tab2.length];
        Punkt Q[]= new Punkt[tab2.length];
        for(double t=0;t<1;t+=dt)
        {
            for (int i=0;i<n;i++)
                R[i]=P[i];
            m=n;
            while(m>0)
            {
                for(int i=0;i<m-1;i++)
                    Q[i]=new Punkt(R[i].getX()+t*(R[i+1].getX()-R[i].getX()),R[i].getY()+t*(R[i+1].getY()-R[i].getY()));
                m--;
                for(int i=0;i<n;i++)
                    R[i]=Q[i];

            }
        }
        return P;
    }

    public double[][] mnozenieMacierzy(double tab[][],double tab1[][])
    {
        double tabwynik[][]= new double [tab.length][tab.length];

        for(int i=0;i<tab.length;i++)
        {
            for(int j=0;j<tab[0].length;j++)
            {
                tabwynik[i][j]+=tab[i][j]*tab1[j][i];
            }
        }

        return tabwynik;
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        //pobieramy etykietę z przycisku
        String label = e.getActionCommand();
        Object z= e.getSource();
        if(z==menu.wczytaj|| label.equals("Wczytaj"))
        {
            otworzPlik();
        }
        else if(z==p7.rysuj)
        {
            Graphics gr= pgl.getGraphics();
            if(p7.obrotT.getText().isEmpty()&& p7.przesunieciex.getText().isEmpty() && p7.przesunieciey.getText().isEmpty() && p7.skalowaniex.getText().isEmpty() && p7.skalowaniey.getText().isEmpty() ){
                for(int i=0;i<tab.length-1;i++)
                {

                    gr.drawLine((int)tab[i].getX(),(int)tab[i].getY(),(int)tab[i+1].getX(),(int)tab[i+1].getY());

                }
            }
            else  if(!p7.obrotT.getText().isEmpty())
            {
                Punkt tab2[]= new Punkt[tab.length];

                tab2=obrot(p7.obrotT,tab);

                tab=tab2;
                for(int i=0;i<tab.length-1;i++)
                {

                    gr.drawLine((int)tab[i].getX(),(int)tab[i].getY(),(int)tab[i+1].getX(),(int)tab[i+1].getY());

                }
            }else if(!p7.przesunieciex.getText().isEmpty() || !p7.przesunieciey.getText().isEmpty())
            {

                Punkt tab2[]= new Punkt[tab.length];
                if(p7.przesunieciex.getText().isEmpty()){
                    p7.przesunieciex.setText("0");
                    tab2=przemieszczenie(TextDouble(p7.przesunieciex),TextDouble(p7.przesunieciey));
                }else if(p7.przesunieciey.getText().isEmpty())
                {
                    p7.przesunieciey.setText("0");
                    tab2=przemieszczenie(TextDouble(p7.przesunieciex),TextDouble(p7.przesunieciey));
                }
                else
                {
                    tab2=przemieszczenie(TextDouble(p7.przesunieciex),TextDouble(p7.przesunieciey));
                }
                tab=tab2;

                for(int i=0;i<tab.length-1;i++)
                {

                    gr.drawLine((int)tab[i].getX(),(int)tab[i].getY(),(int)tab[i+1].getX(),(int)tab[i+1].getY());

                }
            }
            else if(!p7.skalowaniex.getText().isEmpty() || !p7.skalowaniey.getText().isEmpty())
            {
                Punkt tab2[]= new Punkt[tab.length];
                if(p7.skalowaniex.getText().isEmpty()){
                    p7.skalowaniex.setText("1");
                    tab2=skalowanie(TextDouble(p7.skalowaniex),TextDouble(p7.skalowaniey),tab);
                }else if(p7.skalowaniey.getText().isEmpty())
                {
                    p7.skalowaniey.setText("1");
                    tab2=skalowanie(TextDouble(p7.skalowaniex),TextDouble(p7.skalowaniey),tab);
                }
                else
                {
                    tab2=skalowanie(TextDouble(p7.skalowaniex),TextDouble(p7.skalowaniey),tab);
                }
                tab=tab2;

                for(int i=0;i<tab.length-1;i++)
                {

                    gr.drawLine((int)tab[i].getX(),(int)tab[i].getY(),(int)tab[i+1].getX(),(int)tab[i+1].getY());

                }
            }
        }
        else if(z==p4.wczytajM)
        {
            otworzPlikTekstowy();
        } 
        else if(z==menu.metoda1)
        {
            if(menu.metoda1.isSelected()){

                remove(p);
                remove(p2);
                remove(p3);
                remove(p4);
                remove(p5);
                remove(p6);
                remove(p7);
                add(pgp);
                gbc.gridy=1;
                gbc.gridx=0;
                gbc.gridwidth = GridBagConstraints.REMAINDER; 
                add(p1,gbc); 
                validate();
                dopasujSieDoZawartosci();
                repaint();

            }
        } 
        else if(z==menu.metoda2)
        {
            if(menu.metoda2.isSelected()){

                remove(p);
                remove(p1);
                remove(p3);
                remove(p4);
                remove(p5);
                remove(p6);
                remove(p7);
                add(pgp);
                gbc.gridy=1;
                gbc.gridx=0;
                gbc.gridwidth = GridBagConstraints.REMAINDER; 
                add(p2,gbc); 
                validate();
                dopasujSieDoZawartosci();
                repaint();

            }
        }  
        else if(z==menu.metoda3)
        {
            if(menu.metoda3.isSelected()){

                remove(p);
                remove(p1);
                remove(p2);
                remove(p4);
                remove(p5);
                remove(p6);
                remove(p7);
                add(pgp);
                gbc.gridy=1;
                gbc.gridx=0;
                gbc.gridwidth = GridBagConstraints.REMAINDER; 
                add(p3,gbc); 
                validate();
                dopasujSieDoZawartosci();
                repaint();

            }

        } 
        else if(z==menu.metoda4)
        {
            if(menu.metoda4.isSelected()){

                remove(p);
                remove(p1);
                remove(p2);
                remove(p3);
                remove(p5);
                remove(p6);
                remove(p7);
                add(pgp);
                gbc.gridy=1;
                gbc.gridx=0;
                gbc.gridwidth = GridBagConstraints.REMAINDER; 
                add(p4,gbc); 
                validate();
                dopasujSieDoZawartosci();
                repaint();

            }

        }
        else if(z==menu.metoda5)
        {
            if(menu.metoda5.isSelected()){

                remove(p);
                remove(p1);
                remove(p2);
                remove(p3);
                remove(p4);
                remove(p6);
                remove(p7);
                add(pgp);
                gbc.gridy=1;
                gbc.gridx=0;
                gbc.gridwidth = GridBagConstraints.REMAINDER; 
                add(p5,gbc); 
                validate();
                dopasujSieDoZawartosci();
                repaint();

            }

        }
        else if(z==menu.metoda6)
        {
            if(menu.metoda6.isSelected()){

                remove(p);
                remove(p1);
                remove(p2);
                remove(p3);
                remove(p4);
                remove(p5);
                remove(p7);
                add(pgp);
                gbc.gridy=1;
                gbc.gridx=0;
                gbc.gridwidth = GridBagConstraints.REMAINDER; 
                add(p6,gbc); 
                validate();
                dopasujSieDoZawartosci();
                repaint();

            }

        }
        else if(z==menu.krzywaB)
        {
            if(menu.krzywaB.isSelected()){
                remove(pgp);
                remove(p);
                remove(p1);
                remove(p2);
                remove(p3);
                remove(p4);
                remove(p5);
                remove(p6);
                gbc.gridy=1;
                gbc.gridx=0;
                gbc.gridwidth = GridBagConstraints.REMAINDER; 
                add(p7,gbc); 
                validate();
                dopasujSieDoZawartosci();
                repaint();

            }

        }
        else if(z==p4.odbmaska)
        {
            if(p4.odbmaska.isSelected())
            {
                p4.maska1.setEnabled(true);
                p4.maska2.setEnabled(true);
                p4.maska3.setEnabled(true);
                p4.maska4.setEnabled(true);
                p4.maska5.setEnabled(true);
                p4.maska6.setEnabled(true);
                p4.maska7.setEnabled(true);
                p4.maska8.setEnabled(true);
                p4.maska9.setEnabled(true);

            }else if(!p4.odbmaska.isSelected())
            {
                p4.maska1.setEnabled(false);
                p4.maska2.setEnabled(false);
                p4.maska3.setEnabled(false);
                p4.maska4.setEnabled(false);
                p4.maska5.setEnabled(false);
                p4.maska6.setEnabled(false);
                p4.maska7.setEnabled(false);
                p4.maska8.setEnabled(false);
                p4.maska9.setEnabled(false);
            }
        }
        else if(label.equals("Zapisz"))
        {
            zapiszPlik();
        } 
        else if(label.equals("Zamknij"))
        {
            System.exit(0);
        }    
        else if(z==p2.zamianaj)
        {
            int w = pgp.plotno.getWidth();
            int h = pgp.plotno.getHeight();
            String k= p2.tekst.getText();
            if(k.equals(""))
            {
                k="0";
                pgp.linioweOneJ(pgl.plotno,k);
                if(w != pgp.plotno.getWidth() || h != pgp.plotno.getHeight())
                    dopasujSieDoZawartosci();
            }
            else {
                pgp.linioweOneJ(pgl.plotno,k);
                if(w != pgp.plotno.getWidth() || h != pgp.plotno.getHeight())
                    dopasujSieDoZawartosci();
            }
        }
        else if(z==p2.zamianak)
        {
            int w = pgp.plotno.getWidth();
            int h = pgp.plotno.getHeight();
            String k= p2.tekst.getText();
            if(k.equals(""))
            {
                k="0";
                pgp.linioweOneK(pgl.plotno,k);
                if(w != pgp.plotno.getWidth() || h != pgp.plotno.getHeight())
                    dopasujSieDoZawartosci();
            }
            else {
                pgp.linioweOneK(pgl.plotno,k);
                if(w != pgp.plotno.getWidth() || h != pgp.plotno.getHeight())
                    dopasujSieDoZawartosci();
            }
        }
        else if(z==p1.zamiana )
        {
            if(p1.red.isSelected()){
                int w = pgp.plotno.getWidth();
                int h = pgp.plotno.getHeight();            
                pgp.szaryRed(pgl.plotno);
                if(w != pgp.plotno.getWidth() || h != pgp.plotno.getHeight())
                    dopasujSieDoZawartosci();
            }
            if(p1.green.isSelected()){
                int w = pgp.plotno.getWidth();
                int h = pgp.plotno.getHeight();            
                pgp.szaryGreen(pgl.plotno);
                if(w != pgp.plotno.getWidth() || h != pgp.plotno.getHeight())
                    dopasujSieDoZawartosci();
            }
            if(p1.blue.isSelected()){
                int w = pgp.plotno.getWidth();
                int h = pgp.plotno.getHeight();            
                pgp.szaryBlue(pgl.plotno);
                if(w != pgp.plotno.getWidth() || h != pgp.plotno.getHeight())
                    dopasujSieDoZawartosci();
            }
            if(p1.srednia.isSelected()){
                int w = pgp.plotno.getWidth();
                int h = pgp.plotno.getHeight();            
                pgp.szarySrednia(pgl.plotno);
                if(w != pgp.plotno.getWidth() || h != pgp.plotno.getHeight())
                    dopasujSieDoZawartosci();
            }
            if(p1.sredniaYUV.isSelected()){
                int w = pgp.plotno.getWidth();
                int h = pgp.plotno.getHeight();            
                pgp.szarySredniaYUV(pgl.plotno);
                if(w != pgp.plotno.getWidth() || h != pgp.plotno.getHeight())
                    dopasujSieDoZawartosci();
            }

        } 
        else if(z==p3.zamiana)
        {
            int w = pgp.plotno.getWidth();
            int h = pgp.plotno.getHeight();            
            pgp.linioweMetodaTrzy(pgl.plotno);
            if(w != pgp.plotno.getWidth() || h != pgp.plotno.getHeight())
                dopasujSieDoZawartosci(); 
        }
        else if(z==p4.zamiana)
        {
            int w = pgp.plotno.getWidth();
            int h = pgp.plotno.getHeight(); 
            TextField tablica[][]=new TextField [3][3];
            tablica[0][0]=p4.maska1;
            tablica[0][1]=p4.maska2;
            tablica[0][2]=p4.maska3;
            tablica[1][0]=p4.maska4;
            tablica[1][1]=p4.maska5;
            tablica[1][2]=p4.maska6;
            tablica[2][0]=p4.maska7;
            tablica[2][1]=p4.maska8;
            tablica[2][2]=p4.maska9;
            pgp.filtrMaska(pgl.plotno,tablica);
            if(w != pgp.plotno.getWidth() || h != pgp.plotno.getHeight())
                dopasujSieDoZawartosci(); 
        }
        else if(z==p5.zamiana)
        {
            if(p5.maska3x3.isSelected()){
                int w = pgp.plotno.getWidth();
                int h = pgp.plotno.getHeight();            
                pgp.filtrMedianowy(pgl.plotno,3);
                if(w != pgp.plotno.getWidth() || h != pgp.plotno.getHeight())
                    dopasujSieDoZawartosci();
            }
            else if(p5.maska5x5.isSelected()){
                int w = pgp.plotno.getWidth();
                int h = pgp.plotno.getHeight();            
                pgp.filtrMedianowy(pgl.plotno,5);
                if(w != pgp.plotno.getWidth() || h != pgp.plotno.getHeight())
                    dopasujSieDoZawartosci();
            }
        }

        else if(z==p6.zamiana)
        {
            if(!p6.progi.isSelected()){
                int w = pgp.plotno.getWidth();
                int h = pgp.plotno.getHeight(); 

                pgp.filtrGradient(pgl.plotno);
                if(w != pgp.plotno.getWidth() || h != pgp.plotno.getHeight())
                    dopasujSieDoZawartosci();
            }else 
            {
                int w = pgp.plotno.getWidth();
                int h = pgp.plotno.getHeight(); 
            }

        }
        else if(z==menu.wyczyscp)
        {
            pgp.wyczysc();          
        }  
        else if(z==menu.wyczyscl)
        {
            pgl.wyczysc(); 
            Punkt tab1[]=new Punkt[1];
            tab=tab1;
            licznik=0;
        }   
    }   

    public void otworzPlik()
    {
        JFileChooser otworz= new JFileChooser();            
        FileNameExtensionFilter filtr = new FileNameExtensionFilter("JPG & BMP & PNG Images", "jpg", "bmp", "png"); 
        otworz.setFileFilter(filtr); 
        otworz.setCurrentDirectory(new File("./obrazki"));
        int wynik = otworz.showOpenDialog(this);
        if (wynik == JFileChooser.APPROVE_OPTION)   
        {
            sciezkaDoPlik= otworz.getSelectedFile().getPath();    
            int w = pgl.plotno.getWidth();
            int h = pgl.plotno.getHeight();
            pgl.wczytajPlikGraficzny(sciezkaDoPlik);  
            if(w != pgl.plotno.getWidth() || h != pgl.plotno.getHeight())
                dopasujSieDoZawartosci();
        }
    } 

    public void otworzPlikTekstowy()
    {
        JFileChooser otworz= new JFileChooser();            
        FileNameExtensionFilter filtr = new FileNameExtensionFilter("txt", "txt"); 
        otworz.setCurrentDirectory(new File("./maski"));
        otworz.setFileFilter(filtr); 
        int wynik = otworz.showOpenDialog(this);
        if (wynik == JFileChooser.APPROVE_OPTION)   
        {
            sciezkaDoPlik= otworz.getSelectedFile().getPath();    

            p4.wczytajPliktekstowy(sciezkaDoPlik);  

        }
    } 

    public void zapiszPlik()
    {
        JFileChooser zapisz;
        if(sciezkaDoPlik != null)
            zapisz = new JFileChooser(sciezkaDoPlik);    
        else
            zapisz = new JFileChooser();
        FileNameExtensionFilter filtr = new FileNameExtensionFilter("JPG & BMP & PNG Images", "jpg", "bmp", "png");
        zapisz.setFileFilter(filtr);
        int wynik = zapisz.showSaveDialog(this);      
        if (wynik == JFileChooser.APPROVE_OPTION)   
        {
            sciezkaDoPlik= zapisz.getSelectedFile().getPath();    
            pgp.zapiszPlikGraficzny(sciezkaDoPlik);
        }        
    }

    public void dopasujSieDoZawartosci()
    {
        pack();   
        setLocationRelativeTo(null);           
    }
}
