import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
public class Punkt
{
    double x;
    double y;
    double w;
    Panel p= new Panel(7);
    public Punkt(double x, double y )
    {
        this.x=x;
        this.y=y;
        w=1;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public double getW()
    {
        return w;
    }

    public Punkt mnozenieMacierzy(double tab[][])
    {
       
        double tabp[][]= new double [1][3];
        double tabp1[][]= new double [1][3];
        tabp[0][0]=getX();
        tabp[0][1]=getY();
        tabp[0][2]=getW();
        
        for(int i=0;i<tabp[0].length;i++)
        {
            for(int j=0;j<tabp[0].length;j++)
            {
                tabp1[0][i]+=tabp[0][j]*tab[j][i];
            }
        }
        Punkt p= new Punkt(tabp1[0][0],tabp1[0][1]);
        System.out.println("1:"+p.getX()+";"+p.getY());
        return p;
    }

    
}
