import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.awt.event.MouseMotionListener;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.*;

/**
 * Escreva uma descrição da classe GUI aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class GUI extends JFrame implements MouseListener, MouseMotionListener
{
    private JPanel painel;
    private JLabel local, forma;
    private JMenu formasMenu, opcoesMenu;
    private JMenuItem retaItem, circuloItem;
    private JMenuItem corItem, espessuraItem;
    double xMouse, yMouse;
    int contarClique = 0;
    Graphics g;
    boolean primeiraVez = true;
    double x1, y1;
    double x2, y2;
    TiposPrimitivos tipo;
    int tipoDesenho;
    Color corPrincipal = Color.BLACK;
    int espessura = 5;
    
    GUI(){
        this.setSize(700, 700);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        JMenuBar menu = new JMenuBar();
        setJMenuBar(menu);
        
        formasMenu = new JMenu("Formas");
        opcoesMenu = new JMenu("Opções");
        
        menu.add(formasMenu);
        menu.add(opcoesMenu);
        
        retaItem = new JMenuItem("Reta");
        circuloItem = new JMenuItem("Círculo");
        corItem = new JMenuItem("Cor");
        espessuraItem = new JMenuItem("Espessura");
    
        formasMenu.add(retaItem);
        formasMenu.add(circuloItem);   
        opcoesMenu.add(corItem);
        opcoesMenu.add(espessuraItem);
        iniciarEventosMenu();
        iniciarEventoEspessura(this);
        
        painel = new JPanel();
        painel.setSize(500, 500);
        painel.addMouseListener(this);
        painel.addMouseMotionListener(this);
        
        local = new JLabel("Coordenada");
        this.add(local, BorderLayout.NORTH);
        
        forma = new JLabel("Forma");
        this.add(forma, BorderLayout.SOUTH);
         
        this.add(painel, BorderLayout.CENTER);
        
        tipo = TiposPrimitivos.RETAS;
        forma.setText("Reta");
        
    }
    

    public void paintComponent(Graphics g) {  
        if(!primeiraVez){
            FiguraPontos p = new FiguraPontos();
            p.desenharPonto(g, (int)xMouse, (int)yMouse, espessura); 
            
            if(tipo == TiposPrimitivos.RETAS){
                if(contarClique == 0){
                    x1 = xMouse;
                    y1 = yMouse;
                    contarClique++;
                }
                else if(contarClique == 1){
                    x2 = xMouse;
                    y2 = yMouse;
                    
                    FiguraRetas reta = new FiguraRetas();
                    reta.desenharPontos(g, (int)x1, (int)y1, (int)x2, (int)y2, espessura); 
                    contarClique = 0;
                }
            }   
            else if(tipo == TiposPrimitivos.CIRCULOS){
                if(contarClique == 0){
                    x1 = xMouse;
                    y1 = yMouse;
                    contarClique++;
                }
                else if(contarClique == 1){
                    x2 = xMouse;
                    y2 = yMouse;
                    
                    FiguraCirculo.desenharPontos(g, (int)x1, (int)y1, (int)x2, (int)y2, corPrincipal, espessura);
                    contarClique = 0;
                }
                // FiguraCirculo.desenharPontos(g, (int)xMouse, (int)yMouse, 20); //DESENHA CIRCULO    
            }
        }
    }
    
    // Capturando os Eventos com o mouse
    public void mousePressed(MouseEvent e) { 
        primeiraVez = false;
        xMouse = e.getX();
        yMouse = e.getY();  
        g = getGraphics();   
        paintComponent(g);
    } 
    
    public void mouseReleased(MouseEvent e) { 
    }           

    public void mouseClicked(MouseEvent e) {
        
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
        local.setText("x: " + e.getX() + " y: " + e.getY());
    }
    
    private void iniciarEventosMenu(){
    
        retaItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tipo = TiposPrimitivos.RETAS;
                forma.setText("Reta");
            }
        });
        
    
        circuloItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tipo = TiposPrimitivos.CIRCULOS;
                forma.setText("Círculo");
            }
        });
        
        corItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                corPrincipal = JColorChooser.showDialog(painel, "Selecione uma cor", Color.BLACK);
            }
        });
        

    }
    
    private void iniciarEventoEspessura(GUI g){
         espessuraItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Espessura es = new Espessura(g);
                es.setVisible(true);
            }
        });   
    }
}
