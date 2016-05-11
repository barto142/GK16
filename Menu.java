import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JSeparator;

import javax.swing.*;
public class Menu extends JMenuBar 
{
    JMenu menu1= new JMenu("Plik");
    JMenu menu2= new JMenu("Metoda");
    JMenu filtr= new JMenu("Filtr");
    JMenu krzywa= new JMenu("Krzywe");
    JMenu edycja= new JMenu("Edycja");

    JMenuItem wczytaj= new JMenuItem("Wczytaj");  
    JMenuItem zapisz= new JMenuItem("Zapisz");  
    JMenuItem zamknij= new JMenuItem("Zamknij"); 

    JMenuItem wyczyscl= new JMenuItem("WyczyśćL");
    JMenuItem wyczyscp= new JMenuItem("WyczyśćP");
    ButtonGroup bg= new ButtonGroup();
    JRadioButtonMenuItem metoda1= new JRadioButtonMenuItem("Metoda1");  
    JRadioButtonMenuItem metoda2= new JRadioButtonMenuItem("Metoda2");  
    JRadioButtonMenuItem metoda3= new JRadioButtonMenuItem("Metoda3"); 
    JRadioButtonMenuItem metoda4= new JRadioButtonMenuItem("Filtr maska");  
    JRadioButtonMenuItem metoda5= new JRadioButtonMenuItem("Filtr medianowy");  
    JRadioButtonMenuItem metoda6= new JRadioButtonMenuItem("Filtr gradientowy");
    JRadioButtonMenuItem krzywaB= new JRadioButtonMenuItem("Krzywa Beziera");
    public Menu()
    {
        menu1.add(wczytaj);
        menu1.add(new JSeparator());
        menu1.add(zapisz);
        menu1.add(new JSeparator());
        menu1.add(zamknij);
        add(menu1);
        menu2.add(metoda1);
        menu2.add(new JSeparator());
        menu2.add(metoda2);
        menu2.add(new JSeparator());
        menu2.add(metoda3);
        add(menu2);
        filtr.add(metoda4);
        filtr.add(new JSeparator());
        filtr.add(metoda5);
        filtr.add(new JSeparator());
        filtr.add(metoda6);
        add(filtr);
        krzywa.add(krzywaB);
        add(krzywa);
        edycja.add(wyczyscl);
        menu1.add(new JSeparator());
        edycja.add(wyczyscp);
        add(edycja);
        bg.add(metoda1);
        bg.add(metoda2);
        bg.add(metoda3);
        bg.add(metoda4);
        bg.add(metoda5);
        bg.add(metoda6);
        bg.add(krzywaB);
    }

}
