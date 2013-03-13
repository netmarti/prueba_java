// jugando con el ratón 2 NO FUNCIONA POR EL DICHOSO REPINTADO
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class GestorVentana extends WindowAdapter {
  public void windowClosing(WindowEvent e) { System.exit(0); }
}

class GestorRaton extends MouseAdapter {
  RatonSwing2 rs;
  GestorRaton(RatonSwing2 rs) { this.rs = rs; }
  public void mousePressed(MouseEvent e) {
    rs.x = e.getX(); rs.y = e.getY(); 
    rs.panelParaPinchar.repaint();   
  }
}

class GestorBotonSalir implements ActionListener {
  public void actionPerformed(ActionEvent e) { System.exit(0); }
}
class GestorBotonBorrar implements ActionListener {
  RatonSwing2 rs;
  GestorBotonBorrar(RatonSwing2 rs) { this.rs = rs; }
  public void actionPerformed(ActionEvent e) {
    Dimension d = rs.panelParaPinchar.getSize(null);
    rs.getGraphics().clearRect(0,0,d.width,d.height);
    
  }
}

class Panel extends JPanel {
  RatonSwing2 rs;
  Panel(RatonSwing2 rs) { this.rs = rs; }
  public void paintComponent (Graphics g) {     
    g.drawString(rs.x + "," + rs.y,rs.x,rs.y); 
  }
}

public class RatonSwing2 extends JFrame {
  int x,y; Panel panelParaPinchar;
  
  RatonSwing2(String titulo) {
    super(titulo);
    addWindowListener(new GestorVentana());
    addMouseListener(new GestorRaton(this));
  
    getContentPane().setLayout(new BorderLayout());
    
    panelParaPinchar = new Panel(this); 
    getContentPane().add(panelParaPinchar,BorderLayout.CENTER);
    panelParaPinchar.setBackground(Color.red);
    
    JPanel panelBotones = new JPanel();
    panelBotones.setLayout(new FlowLayout());
    JButton botonSalir = new JButton("Salir");
    botonSalir.addActionListener(new GestorBotonSalir());
    panelBotones.add(botonSalir);
    JButton botonBorrar = new JButton("Borrar");
    botonBorrar.addActionListener(new GestorBotonBorrar(this));
    panelBotones.add(botonBorrar);
    getContentPane().add(panelBotones,BorderLayout.SOUTH);
    
    setBounds(250,200,400,200); setVisible(true);  
  } 

  public static void main (String[] args) {
    try {
      new RatonSwing2("Jugando con el ratón ...");
    }
    catch (Exception e) { System.exit(0); }
  }
}
  

