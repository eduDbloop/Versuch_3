
// Ein Funktionenplotter
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

// Hauptfenster von Swing-Klasse JFrame ableiten
public class CFunkPlotter2 extends JFrame
{
int aktFunktion = 0;    // diese Variable bestimmt die 
                        // zu zeichnende Funktion;
                        // Startwert 0 = keine Funktion
CMeineCanvas m_malflaeche;

// Mein Code

int a = 0;
int b = 0;


public static void main(String[] args)
   {
   CFunkPlotter2 Fenster = 
                   new CFunkPlotter2("Funktionenplotter");
   Fenster.pack();
   Fenster.setSize(1200,900);
   Fenster.setResizable(false);
   Fenster.setVisible(true);
   }

// Im Konstruktor werden die Canvas-Malfläche und 
// Schalter zur Auswahl der Funktionen angelegt
CFunkPlotter2(String titel)
   {
   super(titel);

   // Einen Layout Manager einrichten
   getContentPane().setLayout(new FlowLayout());

   // Die Malfläche aufnehmen
   m_malflaeche = new CMeineCanvas();
   getContentPane().add(m_malflaeche);
 
   // Panel-Container für Schalter anlegen
   JPanel panel = new JPanel();
     // Gitter mit 2 Zeilen, 1 Spalte
     panel.setLayout(new GridLayout(2,1,20,20));     
  
     // Schalter anlegen und in Panel aufnehmen
     JButton f1 = new JButton("sin(x)");
     JButton f2 = new JButton("x^3");
     final JTextField tf1 = new JTextField("");
     final JTextField tf2 = new JTextField("");
     tf1.setUI(new JTextFieldHintUI("a", Color.GRAY));
     tf2.setUI(new JTextFieldHintUI("b", Color.GRAY));
     
     panel.add(f1);
     panel.add(f2);
     panel.add(tf1);
     panel.add(tf2);
  
   getContentPane().add(panel);


   class CMeinWindowAdapter extends WindowAdapter
      {
      public void windowClosing(WindowEvent e)
         {
         System.exit(0);
         }
      }
    
   // Das Event-Handling für die Schalter
   class CMeinActionLauscher implements ActionListener
      {
      public void actionPerformed(ActionEvent e)
         {
          String label;

          label = e.getActionCommand();
       
          if(label.equals("sin(x)"))
               aktFunktion = 1;
          else  {
        	  aktFunktion = 2;
        	  
        	  if(!tf1.getText().equals(""))
        		  a = Integer.parseInt(tf1.getText());
        	          	  
        	  if(!tf2.getText().equals(""))
        		  b = Integer.parseInt(tf2.getText());
        	 
          }
	       
         
         // Neuzeichnen veranlassen
         m_malflaeche.repaint();
         }
    }

   // Die Lausch-Objekte anlegen
   f1.addActionListener(new CMeinActionLauscher());
   f2.addActionListener(new CMeinActionLauscher());
   addWindowListener(new CMeinWindowAdapter());
   }


class CMeineCanvas extends Canvas
  {
  // Konstruktor
  CMeineCanvas() {
     // den Hintergrund auf schwarz setzen
     setBackground(Color.black);

     // Vordergrund (=Zeichenfarbe) auf blau setzen
     setForeground(Color.green);
     }

  // Die wichtigste Methode: hier wird gezeichnet!
  public void paint(Graphics g) {
    double x,y;
    int xpos,ypos;

    // Ursprung umsetzen
    g.translate(500,400);

    // Koordinatenachsen einzeichnen
    g.setColor(Color.red);
    g.drawLine(0,-400,0,400);
    g.drawLine(-500,0,500,0);
    
    // Gitterlinien Vertikal positiv
    g.drawLine(-50,-200,-50,200);
    g.drawLine(-100,-200,-100,200);
    g.drawLine(-150,-200,-150,200);
    g.drawLine(-200,-200,-200,200);
    g.drawLine(-250,-200,-250,200);

    // Gitterlinien Vertikal negativ
    g.drawLine(50,-200,50,200);
    g.drawLine(100,-200,100,200);
    g.drawLine(150,-200,150,200);
    g.drawLine(200,-200,200,200);
    g.drawLine(250,-200,250,200);
    
    // Gitterlinien Horizontal negativ
    g.drawLine(-250, 40, 250, 40);
    g.drawLine(-250, 80, 250, 80);
    g.drawLine(-250, 120, 250, 120);
    g.drawLine(-250, 160, 250, 160);
    g.drawLine(-250, 200, 250, 200);
    
    // Gitterlinien Horizontal positiv
    g.drawLine(-250, -40, 250, -40);
    g.drawLine(-250, -80, 250, -80);
    g.drawLine(-250, -120, 250, -120);
    g.drawLine(-250, -160, 250, -160);
    g.drawLine(-250, -200, 250, -200);
    
    g.drawString("-10",-487,12);
    g.drawString("-10",4,387);

    g.drawString("x",477,-12);
    g.drawString("y",-10,-387);
    
    g.drawString("+10",477,12);
    g.drawString("+10",4,-387);

    // Farbe zum Zeichnen der Funktion
    g.setColor(new Color(255,255,0));
        
    // Wenn keine Funktion ausgewählt ist, nichts tun
    if(aktFunktion == 0)
	return;

    for(x= -10.0; x<=10; x += 0.005) {
       if(aktFunktion == 1)
          y = Math.sin(x);
          else
          y = a*x+b;

       xpos = (int) (x*50); 
       ypos = (int) (-y*50); 
       
       g.fillOval(xpos,ypos,2,2);
       }  
    }

  // Diese Methode liefert die minimale Größe der Canvas
  public Dimension getMinimumSize() {
    return new Dimension(1000,800);
    }

  // Die Lieblingsgröße setzen wir auf die Minimalgröße
  public Dimension getPreferredSize() {
    return getMinimumSize();
    }
  }


} // Ende der Klasse CFunkPlotter
