import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;

public class PanelGraficzny extends JPanel
{
    //obiekt do przechowywania grafiki
    BufferedImage plotno;
    //konstruktor
    public PanelGraficzny() 
    {
        super();   
        setLayout(new GridLayout(2,1));
        ustawRozmiar(new Dimension(400,400));
        wyczysc();
    }
    //wczytanie obrazka z pliku
    public void wczytajPlikGraficzny(String sciezka) 
    {
        //obiekt reprezentujący plik graficzny o podanej ścieżce
        File plikGraficzny = new File(sciezka);
        //próba odczytania pliku graficznego do bufora
        try {
            plotno = ImageIO.read(plikGraficzny);  
            //odczytanie rozmiaru obrazka
            Dimension rozmiar = new Dimension(plotno.getWidth(), plotno.getHeight());        
            //ustalenie rozmiaru panelu zgodnego z rozmiarem obrazka
            setPreferredSize(rozmiar);
            setMaximumSize(rozmiar);
            //ustalenie obramowania
            setBorder(BorderFactory.createLineBorder(Color.black));    
            repaint();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Blad odczytu pliku: " + sciezka); 
            e.printStackTrace();
        }
    }  

    public void zapiszPlikGraficzny(String sciezka)
    {
        //obiekt reprezentujący plik graficzny o podanej ścieżce
        File plikGraficzny = new File(sciezka); 
        //próba zapisania pliku graficznego z bufora
        try {
            if(plotno != null)
            {
                if(!ImageIO.write(plotno, sciezka.substring(sciezka.lastIndexOf('.') + 1), new File(sciezka)))
                {
                    JOptionPane.showMessageDialog(null,"Nie udało sie zapisać pliku w " + sciezka);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Brak obrazu do zapisania");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Nie udało sie zapisać pliku w " + sciezka);
        } 
    }

    public void wyczysc()
    {
        //wyrysowanie białego tła
        Graphics2D g = (Graphics2D) plotno.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, plotno.getWidth(), plotno.getHeight());
        //ustalenie obramowania
        setBorder(BorderFactory.createLineBorder(Color.black)); 
        repaint();
    }

    public void ustawRozmiar(Dimension r)
    {
        plotno = new BufferedImage((int)r.getWidth(), (int)r.getHeight(), BufferedImage.TYPE_INT_RGB);
        setPreferredSize(r);     
        setMaximumSize(r);
    }

    public void kopiuj(BufferedImage wejscie)
    {
        ustawRozmiar(new Dimension(wejscie.getWidth(),wejscie.getHeight()));         
        Color ci;
        int czerw, ziel, nieb;

        for(int i=0; i<wejscie.getWidth(); i++)
            for(int j=0; j<wejscie.getHeight(); j++)
            {

                ci = new Color(wejscie.getRGB(i,j)); 
                czerw = ci.getRed();
                ziel = ci.getGreen();
                nieb = ci.getBlue();
                //jak ustawić kolor piksala w obiekcie BufferedImage
                plotno.setRGB(i,j,new Color(czerw,ziel,nieb).getRGB());
        }  
        repaint();
    }

    public void szaryRed(BufferedImage wejscie)
    {
        ustawRozmiar(new Dimension(wejscie.getWidth(),wejscie.getHeight()));         
        Color ci;
        int czerw, ziel, nieb;

        for(int i=0; i<wejscie.getWidth(); i++)
            for(int j=0; j<wejscie.getHeight(); j++)
            {

                ci = new Color(wejscie.getRGB(i,j)); 
                czerw = ci.getRed();
                ziel = ci.getRed();
                nieb = ci.getRed();
                plotno.setRGB(i,j,new Color(czerw,ziel,nieb).getRGB());
        }  
        repaint();
    }

    public void szaryBlue(BufferedImage wejscie)
    {
        ustawRozmiar(new Dimension(wejscie.getWidth(),wejscie.getHeight()));         
        Color ci;
        int czerw, ziel, nieb;

        for(int i=0; i<wejscie.getWidth(); i++)
            for(int j=0; j<wejscie.getHeight(); j++)
            {

                ci = new Color(wejscie.getRGB(i,j)); 
                czerw = ci.getBlue();
                ziel = ci.getBlue();
                nieb = ci.getBlue();
                plotno.setRGB(i,j,new Color(czerw,ziel,nieb).getRGB());
        }  
        repaint();
    } 

    public void szaryGreen(BufferedImage wejscie)
    {
        ustawRozmiar(new Dimension(wejscie.getWidth(),wejscie.getHeight()));         
        Color ci;
        int czerw, ziel, nieb;

        for(int i=0; i<wejscie.getWidth(); i++)
            for(int j=0; j<wejscie.getHeight(); j++)
            {

                ci = new Color(wejscie.getRGB(i,j)); 
                czerw = ci.getGreen();
                ziel = ci.getGreen();
                nieb = ci.getGreen();
                plotno.setRGB(i,j,new Color(czerw,ziel,nieb).getRGB());
        }  
        repaint();
    }

    public void szarySrednia(BufferedImage wejscie)
    {
        ustawRozmiar(new Dimension(wejscie.getWidth(),wejscie.getHeight()));         
        Color ci;
        int czerw, ziel, nieb, srednia;

        for(int i=0; i<wejscie.getWidth(); i++)
            for(int j=0; j<wejscie.getHeight(); j++)
            {

                ci = new Color(wejscie.getRGB(i,j)); 
                czerw = ci.getRed();
                ziel = ci.getGreen();
                nieb = ci.getBlue();
                srednia=(czerw+ziel+nieb)/3;
                plotno.setRGB(i,j,new Color(srednia,srednia,srednia).getRGB());
        }  
        repaint();
    }

    public void szarySredniaYUV(BufferedImage wejscie)
    {
        ustawRozmiar(new Dimension(wejscie.getWidth(),wejscie.getHeight()));         
        Color ci;
        int czerw, ziel, nieb, srednia;

        for(int i=0; i<wejscie.getWidth(); i++)
            for(int j=0; j<wejscie.getHeight(); j++)
            {

                ci = new Color(wejscie.getRGB(i,j)); 
                czerw = ci.getRed();
                ziel = ci.getGreen();
                nieb = ci.getBlue();
                srednia=(int)((0.299*(double)czerw)+(0.587*(double)ziel)+(0.114*(double)nieb));
                plotno.setRGB(i,j,new Color(srednia,srednia,srednia).getRGB());
        }  
        repaint();
    }

    public void linioweOneJ(BufferedImage wejscie,String t)
    {
        ustawRozmiar(new Dimension(wejscie.getWidth(),wejscie.getHeight()));         
        Color ci;
        int czerw, ziel, nieb, srednia,k;

        k=Integer.parseInt(t);
        for(int i=0; i<wejscie.getWidth(); i++)
            for(int j=0; j<wejscie.getHeight(); j++)
            {

                ci = new Color(wejscie.getRGB(i,j)); 
                czerw = ci.getRed()+k;
                ziel = ci.getGreen()+k;
                nieb = ci.getBlue()+k;
                if(czerw>255)
                {
                    czerw=255;
                }
                else if(czerw<0)
                {
                    czerw=0;
                }
                if(ziel>255)
                {
                    ziel=255;
                }
                else if(ziel<0)
                {
                    ziel=0;
                }
                if(nieb>255)
                {
                    nieb=255;
                }
                else if(nieb<0)
                {
                    nieb=0;
                }

                plotno.setRGB(i,j,new Color(czerw,ziel,nieb).getRGB());
        }  
        repaint();
    }

    public void linioweOneK(BufferedImage wejscie,String t)
    {
        ustawRozmiar(new Dimension(wejscie.getWidth(),wejscie.getHeight()));         
        Color ci;
        int czerw, ziel, nieb, srednia,k;

        k=Integer.parseInt(t);
        for(int i=0; i<wejscie.getWidth(); i++)
            for(int j=0; j<wejscie.getHeight(); j++)
            {

                ci = new Color(wejscie.getRGB(i,j)); 
                czerw = ci.getRed()*k;
                ziel = ci.getGreen()*k;
                nieb = ci.getBlue()*k;
                if(czerw>255)
                {
                    czerw=255;
                }
                else if(czerw<0)
                {
                    czerw=0;
                }
                if(ziel>255)
                {
                    ziel=255;
                }
                else if(ziel<0)
                {
                    ziel=0;
                }
                if(nieb>255)
                {
                    nieb=255;
                }
                else if(nieb<0)
                {
                    nieb=0;
                }

                plotno.setRGB(i,j,new Color(czerw,ziel,nieb).getRGB());
        }  
        repaint();
    }

    public void linioweMetodaTrzy(BufferedImage wejscie)
    {
        ustawRozmiar(new Dimension(wejscie.getWidth(),wejscie.getHeight()));         
        Color ci;
        int czerw, ziel, nieb, srednia,k;
        int Lmaxr=0;
        int Lmaxg=0;
        int Lmaxb=0;
        int Lminr=255;
        int Lming=255;
        int Lminb=255;
        for(int i=0; i<wejscie.getWidth(); i++)
            for(int j=0; j<wejscie.getHeight(); j++)
            {
                ci= new Color(wejscie.getRGB(i,j));
                czerw= ci.getRed();
                ziel=ci.getGreen();
                nieb=ci.getBlue();
                if(czerw>Lmaxr)
                {
                    Lmaxr=czerw;
                }
                if(czerw<Lminr)
                {
                    Lminr=czerw;
                }
                if(ziel>Lmaxg)
                {
                    Lmaxg=ziel;
                }
                if(ziel<Lming)
                {
                    Lming=ziel;
                }
                if(nieb>Lmaxb)
                {
                    Lmaxb=nieb;
                }
                if(nieb<Lminb)
                {
                    Lminb=nieb;
                }

        }  

        for(int i=0; i<wejscie.getWidth(); i++)
            for(int j=0; j<wejscie.getHeight(); j++)
            {

                ci = new Color(wejscie.getRGB(i,j)); 
                czerw = (255*(ci.getRed()-Lminr))/(Lmaxr-Lminr);
                ziel =(255*( ci.getGreen()-Lming))/(Lmaxg-Lming);
                nieb = (255*(ci.getBlue()-Lminb))/(Lmaxb-Lminb);
                if(czerw>255)
                {
                    czerw=255;
                }
                else if(czerw<0)
                {
                    czerw=0;
                }
                if(ziel>255)
                {
                    ziel=255;
                }
                else if(ziel<0)
                {
                    ziel=0;
                }
                if(nieb>255)
                {
                    nieb=255;
                }
                else if(nieb<0)
                {
                    nieb=0;
                }

                plotno.setRGB(i,j,new Color(czerw,ziel,nieb).getRGB());

        }  
        repaint();
    }

    public Color[][] powiekszenieTablicy(Color tabwej[][],int szer,int wys)
    {
        Color tab[][]= new Color[szer][wys];
        for(int i=0; i<tab.length; i++)
            for(int j=0; j<tab[i].length; j++)
            {
                if(i>=1 && i<=tab.length-2 && j>=1 && j<=tab[i].length-2)
                {
                    tab[i][j]=tabwej[i-1][j-1];
                }
                else if(i==0 && j==0)
                { 
                    tab[i][j]=tabwej[i][j];
                }
                else if(i==0 && j==tab[i].length-1)
                {
                    tab[i][j]=tabwej[i][j-2];
                }
                else if(i==tab.length-1 && j==0)
                {
                    tab[i][j]=tabwej[i-2][j];
                }
                else if(i==tab.length-1 && j==tab[i].length-1)
                {
                    tab[i][j]=tabwej[i-2][j-2];
                }
                else if(i==0 && j>=1 && j<=(tab[i].length)-2)
                {
                    tab[i][j]=tabwej[i][j-1];
                }
                else if(i>=1 && i<=tab.length-2 && j==0)
                {
                    tab[i][j]=tabwej[i-1][j];
                }
                else if(i>=1 && i<=tab.length-2 && j==tab[i].length-1)
                {
                    tab[i][j]=tabwej[i-1][j-2];
                }
                else if(i==tab.length-1 && j>=1 && j<=tab[i].length-2)
                {
                    tab[i][j]=tabwej[i-2][j-1];
                }
        }
        return tab;
    }

    

    public void filtrMaska(BufferedImage wejscie,TextField tablica[][])
    {
        ustawRozmiar(new Dimension(wejscie.getWidth(),wejscie.getHeight()));         
        Color ci;
        int czerw, ziel, nieb;
        int szer=wejscie.getWidth()+2;
        int wys=wejscie.getHeight()+2;
        Color tab[][]= new Color[szer][wys];
        Color tabwej[][]= new Color[wejscie.getWidth()][wejscie.getHeight()];
        for(int i=0;i<wejscie.getWidth();i++)
        {
            for(int j=0;j<wejscie.getHeight();j++)
            {
                ci= new Color(wejscie.getRGB(i,j));
                tabwej[i][j]=ci;
            }
        }
        tab=powiekszenieTablicy(tabwej,szer,wys);

        int sumaMaska=0;
        for(int i=0;i<tablica.length;i++)
        {
            for(int j=0;j<tablica[i].length;j++)
            {
                String maska=tablica[i][j].getText();
                int liczba=Integer.parseInt(maska);
                sumaMaska=sumaMaska+liczba;
            }
        }
        if(sumaMaska==0)
        {
            sumaMaska=1;
        }
        int tabl[][]=new int[tablica.length][tablica.length];
        for(int i=0;i<tabl.length;i++)
        {
            for(int j=0;j<tabl[i].length;j++)
            {
                String maska=tablica[i][j].getText();
                tabl[i][j]=Integer.parseInt(maska);

            }
        }
        int tabczerw[][]=new int[wejscie.getWidth()][wejscie.getHeight()];
        int tabziel[][]=new int[wejscie.getWidth()][wejscie.getHeight()];
        int tabnieb[][]=new int[wejscie.getWidth()][wejscie.getHeight()];
        for(int i=1;i<tab.length-1;i++)
        {
            for(int j=1;j<tab[i].length-1;j++)
            {

                int wynikr00=tabl[0][0]*tab[i-1][j-1].getRed();
                int wynikr01=tabl[0][1]*tab[i-1][j].getRed();
                int wynikr02=tabl[0][2]*tab[i-1][j+1].getRed();
                int wynikr10=tabl[1][0]*tab[i][j-1].getRed();
                int wynikr11=tabl[1][1]*tab[i][j].getRed();
                int wynikr12=tabl[1][2]*tab[i][j+1].getRed();
                int wynikr20=tabl[2][0]*tab[i+1][j-1].getRed();
                int wynikr21=tabl[2][1]*tab[i+1][j].getRed();
                int wynikr22=tabl[2][2]*tab[i+1][j+1].getRed();
                czerw=(wynikr00+wynikr01+wynikr02+wynikr10+wynikr11+wynikr12+wynikr20+wynikr21+wynikr22)/sumaMaska;
                int wynikg00=tabl[0][0]*tab[i-1][j-1].getGreen();
                int wynikg01=tabl[0][1]*tab[i-1][j].getGreen();
                int wynikg02=tabl[0][2]*tab[i-1][j+1].getGreen();
                int wynikg10=tabl[1][0]*tab[i][j-1].getGreen();
                int wynikg11=tabl[1][1]*tab[i][j].getGreen();
                int wynikg12=tabl[1][2]*tab[i][j+1].getGreen();
                int wynikg20=tabl[2][0]*tab[i+1][j-1].getGreen();
                int wynikg21=tabl[2][1]*tab[i+1][j].getGreen();
                int wynikg22=tabl[2][2]*tab[i+1][j+1].getGreen();
                ziel=(wynikg00+wynikg01+wynikg02+wynikg10+wynikg11+wynikg12+wynikg20+wynikg21+wynikg22)/sumaMaska;
                int wynikb00=tabl[0][0]*tab[i-1][j-1].getBlue();
                int wynikb01=tabl[0][1]*tab[i-1][j].getBlue();
                int wynikb02=tabl[0][2]*tab[i-1][j+1].getBlue();
                int wynikb10=tabl[1][0]*tab[i][j-1].getBlue();
                int wynikb11=tabl[1][1]*tab[i][j].getBlue();
                int wynikb12=tabl[1][2]*tab[i][j+1].getBlue();
                int wynikb20=tabl[2][0]*tab[i+1][j-1].getBlue();
                int wynikb21=tabl[2][1]*tab[i+1][j].getBlue();
                int wynikb22=tabl[2][2]*tab[i+1][j+1].getBlue();
                nieb=(wynikb00+wynikb01+wynikb02+wynikb10+wynikb11+wynikb12+wynikb20+wynikb21+wynikb22)/sumaMaska;

                if(czerw>255)
                {
                    czerw=255;
                    tabczerw[i-1][j-1]=czerw;
                }
                else if(czerw<0)
                {
                    czerw=0;
                    tabczerw[i-1][j-1]=czerw;
                }
                if(ziel>255)
                {
                    ziel=255;
                    tabziel[i-1][j-1]=ziel;
                }
                else if(ziel<0)
                {
                    ziel=0;
                    tabziel[i-1][j-1]=ziel;
                }
                if(nieb>255)
                {
                    nieb=255;
                    tabnieb[i-1][j-1]=nieb;
                }
                else if(nieb<0)
                {
                    nieb=0;
                    tabnieb[i-1][j-1]=nieb;
                }
                tabczerw[i-1][j-1]=czerw;
                tabziel[i-1][j-1]=ziel;
                tabnieb[i-1][j-1]=nieb;
            }
        }
        for(int i=0;i<tabczerw.length;i++)
        {
            for(int j=0;j<tabczerw[i].length;j++)
            {
                plotno.setRGB(i,j,new Color(tabczerw[i][j],tabziel[i][j],tabnieb[i][j]).getRGB());
            } 
        }
        repaint();
    }

    public int[] sort(int tablica[])
    {
        for(int q=0;q<tablica.length-1;q++)
        {
            for(int e=0;e<tablica.length-1;e++){
                if(tablica[e]>tablica[e+1])
                {
                    int temp=tablica[e+1];
                    tablica[e+1]=tablica[e];
                    tablica[e]=temp;
                }
            }
        }
        return tablica;
    }

    public void filtrMedianowy(BufferedImage wejscie,int m)
    {
        ustawRozmiar(new Dimension(wejscie.getWidth(),wejscie.getHeight()));         
        Color ci;
        int czerw, ziel, nieb;
        int szer=wejscie.getWidth()+2;
        int wys=wejscie.getHeight()+2;
        Color tab[][]= new Color[szer][wys];
        Color tabwej[][]= new Color[wejscie.getWidth()][wejscie.getHeight()];
        for(int i=0;i<wejscie.getWidth();i++)
        {
            for(int j=0;j<wejscie.getHeight();j++)
            {
                ci= new Color(wejscie.getRGB(i,j));
                tabwej[i][j]=ci;
            }
        }

        int tabczerw[][]=new int[wejscie.getWidth()][wejscie.getHeight()];
        int tabziel[][]=new int[wejscie.getWidth()][wejscie.getHeight()];
        int tabnieb[][]=new int[wejscie.getWidth()][wejscie.getHeight()];
        if(m==3){
            tab=powiekszenieTablicy(tabwej,szer,wys);
            for(int i=1;i<tab.length-1;i++)
            {
                for(int j=1;j<tab[i].length-1;j++)
                {
                    int tczerw[]= new int [9];
                    int tziel[]=new int[9];
                    int tnieb[]=new int[9];
                    int w=0;
                    for(int k=i-1;k<=i+1;k++)
                    {
                        for(int l=j-1;l<=j+1;l++)
                        {

                            tczerw[w]=tab[k][l].getRed();
                            tziel[w]=tab[k][l].getGreen();
                            tnieb[w]=tab[k][l].getBlue();
                            w++;

                        }
                    }
                    tczerw=sort(tczerw);
                    tziel=sort(tziel);
                    tnieb=sort(tnieb);
                    czerw=tczerw[4];
                    ziel=tziel[4];
                    nieb=tnieb[4];

                    if(czerw>255)
                    {
                        czerw=255;
                        tabczerw[i-1][j-1]=czerw;
                    }
                    else if(czerw<0)
                    {
                        czerw=0;
                        tabczerw[i-1][j-1]=czerw;
                    }
                    if(ziel>255)
                    {
                        ziel=255;
                        tabziel[i-1][j-1]=ziel;
                    }
                    else if(ziel<0)
                    {
                        ziel=0;
                        tabziel[i-1][j-1]=ziel;
                    }
                    if(nieb>255)
                    {
                        nieb=255;
                        tabnieb[i-1][j-1]=nieb;
                    }
                    else if(nieb<0)
                    {
                        nieb=0;
                        tabnieb[i-1][j-1]=nieb;
                    }
                    tabczerw[i-1][j-1]=czerw;
                    tabziel[i-1][j-1]=ziel;
                    tabnieb[i-1][j-1]=nieb;
                }
            }
        }
        else if(m==5)
        {
            Color tabb[][]= new Color[szer+2][wys+2];
            tab=powiekszenieTablicy(tabwej,szer,wys);
            tabb=powiekszenieTablicy(tab,szer+2,wys+2);
            for(int i=2;i<tabb.length-2;i++)
            {
                for(int j=2;j<tabb[i].length-2;j++)
                {
                    int tczerw[]= new int [25];
                    int tziel[]=new int[25];
                    int tnieb[]=new int[25];
                    int w=0;
                    for(int k=i-2;k<=i+2;k++)
                    {
                        for(int l=j-2;l<=j+2;l++)
                        {

                            tczerw[w]=tabb[k][l].getRed();
                            tziel[w]=tabb[k][l].getGreen();
                            tnieb[w]=tabb[k][l].getBlue();
                            w++;

                        }
                    }
                    for(int q=0;q<tczerw.length-1;q++)
                    {
                        for(int e=0;e<tczerw.length-1;e++){
                            if(tczerw[e]>tczerw[e+1])
                            {
                                int temp=tczerw[e+1];
                                tczerw[e+1]=tczerw[e];
                                tczerw[e]=temp;
                            }
                        }
                    }
                    for(int q=0;q<tziel.length-1;q++)
                    {
                        for(int e=0;e<tziel.length-1;e++){
                            if(tziel[q]>tziel[q+1])
                            {
                                int temp=tziel[e+1];
                                tziel[e+1]=tziel[e];
                                tziel[e]=temp;
                            }
                        }
                    }
                    for(int q=0;q<tnieb.length-1;q++)
                    {
                        for(int e=0;e<tnieb.length-1;e++){
                            if(tnieb[q]>tnieb[q+1])
                            {
                                int temp=tnieb[e+1];
                                tnieb[e+1]=tnieb[e];
                                tnieb[e]=temp;
                            }
                        }
                    }
                    czerw=tczerw[12];
                    ziel=tziel[12];
                    nieb=tnieb[12];
                    if(czerw>255)
                    {
                        czerw=255;
                        tabczerw[i-2][j-2]=czerw;
                    }
                    else if(czerw<0)
                    {
                        czerw=0;
                        tabczerw[i-2][j-2]=czerw;
                    }
                    if(ziel>255)
                    {
                        ziel=255;
                        tabziel[i-2][j-2]=ziel;
                    }
                    else if(ziel<0)
                    {
                        ziel=0;
                        tabziel[i-2][j-2]=ziel;
                    }
                    if(nieb>255)
                    {
                        nieb=255;
                        tabnieb[i-2][j-2]=nieb;
                    }
                    else if(nieb<0)
                    {
                        nieb=0;
                        tabnieb[i-2][j-2]=nieb;
                    }
                    tabczerw[i-2][j-2]=czerw;
                    tabziel[i-2][j-2]=ziel;
                    tabnieb[i-2][j-2]=nieb;
                }
            }
        }
        for(int i=0;i<tabczerw.length;i++)
        {
            for(int j=0;j<tabczerw[i].length;j++)
            {
                plotno.setRGB(i,j,new Color(tabczerw[i][j],tabziel[i][j],tabnieb[i][j]).getRGB());
            } 
        }
        repaint();
    }

    public void filtrGradient(BufferedImage wejscie)
    {
        ustawRozmiar(new Dimension(wejscie.getWidth(),wejscie.getHeight()));         
        Color ci;
        int czerw, ziel, nieb;
        Color tabwej[][]= new Color[wejscie.getWidth()][wejscie.getHeight()];
        for(int i=0;i<wejscie.getWidth();i++)
        {
            for(int j=0;j<wejscie.getHeight();j++)
            {
                ci= new Color(wejscie.getRGB(i,j));
                tabwej[i][j]=ci;
            }
        }
        for(int i=0;i<tabwej.length-1;i++)
        {
            for(int j=0;j<tabwej[i].length-1;j++)
            {
                czerw=Math.abs(tabwej[i][j].getRed()-tabwej[i][j+1].getRed())+Math.abs(tabwej[i][j].getRed()-tabwej[i+1][j].getRed());
                ziel=Math.abs(tabwej[i][j].getGreen()-tabwej[i][j+1].getGreen())+Math.abs(tabwej[i][j].getGreen()-tabwej[i+1][j].getGreen());
                nieb=Math.abs(tabwej[i][j].getBlue()-tabwej[i][j+1].getBlue())+Math.abs(tabwej[i][j].getBlue()-tabwej[i+1][j].getBlue());
                if(czerw>255)
                {
                    czerw=255;

                }
                else if(czerw<0)
                {
                    czerw=0;

                }
                if(ziel>255)
                {
                    ziel=255;

                }
                else if(ziel<0)
                {
                    ziel=0;

                }
                if(nieb>255)
                {
                    nieb=255;

                }
                else if(nieb<0)
                {
                    nieb=0;

                }
                plotno.setRGB(i,j,new Color(czerw,ziel,nieb).getRGB());
            }

        }
    }

    public void filtrGradientProgi(BufferedImage wejscie,TextField a,TextField b)
    {
        ustawRozmiar(new Dimension(wejscie.getWidth(),wejscie.getHeight()));         
        Color ci;
        int czerw, ziel, nieb;
        Color tabwej[][]= new Color[wejscie.getWidth()][wejscie.getHeight()];
        for(int i=0;i<wejscie.getWidth();i++)
        {
            for(int j=0;j<wejscie.getHeight();j++)
            {
                ci= new Color(wejscie.getRGB(i,j));
                tabwej[i][j]=ci;
            }
        }
        int tempa=Integer.parseInt(a.getText());
        int tempb=Integer.parseInt(b.getText());
        for(int i=0;i<tabwej.length-1;i++)
        {
            for(int j=0;j<tabwej[i].length-1;j++)
            {
                czerw=Math.abs(tabwej[i][j].getRed()-tabwej[i][j+1].getRed())+Math.abs(tabwej[i][j].getRed()-tabwej[i+1][j].getRed());
                ziel=Math.abs(tabwej[i][j].getGreen()-tabwej[i][j+1].getGreen())+Math.abs(tabwej[i][j].getGreen()-tabwej[i+1][j].getGreen());
                nieb=Math.abs(tabwej[i][j].getBlue()-tabwej[i][j+1].getBlue())+Math.abs(tabwej[i][j].getBlue()-tabwej[i+1][j].getBlue());

                if(tempa>czerw)
                {
                    czerw=tempa;
                }
                if(czerw>255)
                {
                    czerw=255;

                }
                else if(czerw<0)
                {
                    czerw=0;

                }
                if(ziel>255)
                {
                    ziel=255;

                }
                else if(ziel<0)
                {
                    ziel=0;

                }
                if(nieb>255)
                {
                    nieb=255;

                }
                else if(nieb<0)
                {
                    nieb=0;

                }
                plotno.setRGB(i,j,new Color(czerw,ziel,nieb).getRGB());
            }

        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(plotno, 0, 0, this);
    }    
}
