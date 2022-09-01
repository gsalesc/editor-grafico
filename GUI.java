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
import java.awt.Dimension;
import javax.swing.JColorChooser;
import ListaDuplamenteLigada.*;
import javax.swing.JButton;
import java.util.ArrayList;

/**
 * Escreva uma descrição da classe GUI aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class GUI extends JFrame implements MouseListener, MouseMotionListener
{
    private JPanel painel, painelFormas;
    private JLabel local, forma;
    private JMenuBar menu;
    private JMenu desenhoMenu, opcoesMenu;
    private JMenuItem corItem, espessuraItem, corDeFundoItem, apagarItem;
    int xMouse, yMouse;
    int contarClique = 0;
    Graphics g;
    boolean primeiraVez = true;
    int x1, y1;
    int x2, y2;
    int xn, yn;
    TiposPrimitivos tipo;
    int tipoDesenho;
    Color corPrincipal = Color.BLACK;
    Color corDeFundo = Color.WHITE; 
    int espessura = 5;
    GUI gui = this;
    boolean fecharPoligono = false;
    boolean apagarRetaPoligono = false;
    ListaDuplamenteLigadaCircular listaPoligono = null;
    JButton btnPonto, btnReta, btnCir, btnRet, btnPoli, btnLinha;
    //ArrayList<ListaDuplamenteLigadaCircular> voltar = new ArrayList<ListaDuplamenteLigadaCircular>();
    //int indexDesenho = 0;
    int indexPonto = 0;
    int indexReta = 0;
    int indexCirculo = 0;
    int indexRetangulo = 0;
    int indexPoligono = 0;
    
    GUI(){
        this.setSize(900, 700);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        menu = new JMenuBar();
        setJMenuBar(menu);
        
        desenhoMenu = new JMenu("Desenho");
        opcoesMenu = new JMenu("Opções");
        
        menu.add(desenhoMenu);
        menu.add(opcoesMenu);
        
        
        apagarItem = new JMenuItem("Voltar");
        corItem = new JMenuItem("Cor");
        espessuraItem = new JMenuItem("Espessura");
        corDeFundoItem = new JMenuItem("Cor de fundo");
    
        desenhoMenu.add(apagarItem);
        opcoesMenu.add(corItem);
        opcoesMenu.add(espessuraItem);
        opcoesMenu.add(corDeFundoItem);
        
        JPanel painelFormas = new JPanel();  
        this.add(painelFormas, BorderLayout.NORTH);

        btnPonto = new JButton("Pontos");
        btnReta = new JButton("Reta");
        btnCir = new JButton("Circulo");
        btnRet = new JButton("Retangulo");
        btnPoli = new JButton("Poligono");
        btnLinha = new JButton("Linha");
        painelFormas.add(btnPonto);
        painelFormas.add(btnReta);
        painelFormas.add(btnCir);
        painelFormas.add(btnRet);
        painelFormas.add(btnPoli);
        painelFormas.add(btnLinha);

        iniciarEventosFormas();
        iniciarEventosMenu();
        iniciarEventosEspessura();
        
        painel = new JPanel();
        painel.setSize(500, 500);
        painel.setBackground(corDeFundo);
        painel.addMouseListener(this);
        painel.addMouseMotionListener(this);
        this.add(painel, BorderLayout.CENTER);

        local = new JLabel("Coordenada");
        this.add(local, BorderLayout.SOUTH);
        
        forma = new JLabel("Forma");
        this.add(forma, BorderLayout.SOUTH);
 
        tipo = TiposPrimitivos.PONTOS;
        forma.setText("Pontos");   
    }
    
    // Capturando os Eventos com o mouse
    public void mousePressed(MouseEvent e) { 
        primeiraVez = false;
        xMouse = e.getX();
        yMouse = e.getY();  
        g = getGraphics();   
        paintComponent(g);
    }     

    public void paintComponent(Graphics g) {  
        if(!primeiraVez){
            Dimension d = menu.getSize();
            xMouse += 10;
            yMouse += 90;
            
            if(tipo == TiposPrimitivos.PONTOS){
                FiguraPontos p = new FiguraPontos();
                p.desenharPonto(g, xMouse, yMouse, espessura, corPrincipal); 
            }
            else if(tipo == TiposPrimitivos.RETAS){
                if(contarClique == 0){
                    x1 = xMouse;
                    y1 = yMouse;
                    contarClique++;
                }
                else if(contarClique == 1){
                    x2 = xMouse;
                    y2 = yMouse;
                    
                    FiguraRetas reta = new FiguraRetas();
                    reta.desenharPontos(g, x1, y1, x2, y2, corPrincipal, espessura); 
                    contarClique = 0;
                    indexReta++;
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
                    
                    FiguraCirculo.desenharCirculo(g, x1, y1, x2, y2, corPrincipal, espessura);
                    contarClique = 0;
                    indexCirculo++;
                }  
            }
            else if(tipo == TiposPrimitivos.RETANGULOS){
                if(contarClique == 0){
                    x1 = xMouse;
                    y1 = yMouse;
                    contarClique++;
                }
                else if(contarClique == 1){
                    x2 = xMouse;
                    y2 = yMouse;
                    
                    Ponto p1 = new Ponto(x1, y1);
                    Ponto p2 = new Ponto(x2, y2);
                    
                    FiguraRetangulo.desenharRetangulo(g, p1, p2, corPrincipal, espessura);
                    contarClique = 0;
                    indexRetangulo++;
                }  
            
            }
            
            else if(tipo == TiposPrimitivos.POLIGONOS){
                if(listaPoligono == null){
                    listaPoligono =  new ListaDuplamenteLigadaCircular();
                    
                    //primeiro ponto do poligono
                    Ponto ponto = new Ponto(xMouse, yMouse);
                    listaPoligono.inserirInicio(ponto);
                }
                
                else{
                
                    if(fecharPoligono != true){
                        //ligar novo ponto ao anterior
                        x1 = xMouse;
                        y1 = yMouse;
                        
                        Ponto pontoAnterior = (Ponto)listaPoligono.getInicio().getConteudo();
                        int xPontoAnterior = (int)pontoAnterior.getX();
                        int yPontoAnterior = (int)pontoAnterior.getY();
                        
                        FiguraRetas.desenharPontos(g, x1, y1, xPontoAnterior, yPontoAnterior, corPrincipal, espessura);
                        
                        //adicionar à lista
                        Ponto ponto = new Ponto(x1, y1);
                        listaPoligono.inserirInicio(ponto);
                        indexPoligono++;
                    }
                    
                    else{
                        
                        //ligar primeiro no ultimo
                        Ponto primeiroPonto = (Ponto)listaPoligono.getFim().getConteudo();
                        int xPrimeiroPonto = (int)primeiroPonto.getX();
                        int yPrimeiroPonto = (int)primeiroPonto.getY();
                        
                        FiguraRetas.desenharPontos(g, x1, y1, xPrimeiroPonto, yPrimeiroPonto, corPrincipal, espessura);
                        
                        listaPoligono = null;
                        fecharPoligono = false;
                        indexPoligono++;
                    }
                
                }
            }
            
            else if(tipo == TiposPrimitivos.LINHAS){
                if(listaPoligono == null){
                    listaPoligono =  new ListaDuplamenteLigadaCircular();
                    
                    //primeiro ponto do poligono
                    Ponto ponto = new Ponto(xMouse, yMouse);
                    listaPoligono.inserirInicio(ponto);
                }
                
                else{
                
                    if(fecharPoligono != true){
                        //ligar novo ponto ao anterior
                        x1 = xMouse;
                        y1 = yMouse;
                        
                        Ponto pontoAnterior = (Ponto)listaPoligono.getInicio().getConteudo();
                        int xPontoAnterior = (int)pontoAnterior.getX();
                        int yPontoAnterior = (int)pontoAnterior.getY();
                        
                        FiguraRetas.desenharPontos(g, x1, y1, xPontoAnterior, yPontoAnterior, corPrincipal, espessura);
                        
                        //adicionar à lista
                        Ponto ponto = new Ponto(x1, y1);
                        listaPoligono.inserirInicio(ponto);
                        indexPoligono++;
                    }
                    
                    else{
                        listaPoligono = null;
                        fecharPoligono = false;
                    }
                
                }
            }
        }
    }
    
    public void mouseReleased(MouseEvent e) { 
    }           

    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2){
            if(tipo == TiposPrimitivos.POLIGONOS || tipo == TiposPrimitivos.LINHAS){
                fecharPoligono = true;  
                paintComponent(g);                
            }
        }
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
    
    private void iniciarEventosFormas(){
    
        btnPonto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tipo = TiposPrimitivos.PONTOS;
                forma.setText("Ponto");
                contarClique = 0;
            }
        });
        
        btnReta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tipo = TiposPrimitivos.RETAS;
                forma.setText("Retas");
                contarClique = 0;
            }
        });
        
        btnCir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tipo = TiposPrimitivos.CIRCULOS;
                forma.setText("Circulos");
                contarClique = 0;
            }
        });
        
        btnRet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tipo = TiposPrimitivos.RETANGULOS;
                forma.setText("Retangulo");
                contarClique = 0;
            }
        });
        
        btnPoli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tipo = TiposPrimitivos.POLIGONOS;
                forma.setText("Poligono");
                contarClique = 0;
            }
        });
        
        btnLinha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tipo = TiposPrimitivos.LINHAS;
                forma.setText("Linha");
                contarClique = 0;
            }
        });
    }
    private void iniciarEventosMenu(){
        apagarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tipo == TiposPrimitivos.RETAS){
                    if(indexReta != 0){
                        FiguraRetas.apagarReta(g, corDeFundo);
                        indexReta--;
                    }
                }
                else if(tipo == TiposPrimitivos.CIRCULOS){
                    if(indexCirculo != 0){
                        FiguraCirculo.apagarCirculo(g, corDeFundo);
                        indexCirculo--;
                    }
                }
                
                else if(tipo == TiposPrimitivos.RETANGULOS){
                    if(indexRetangulo != 0){
                        FiguraRetangulo.apagarRetangulo(g, corDeFundo);
                        indexRetangulo--;
                    }
                }
                
                else if (tipo == TiposPrimitivos.POLIGONOS || tipo == TiposPrimitivos.LINHAS){
                    if(indexPoligono != 0){
                        FiguraRetas.apagarReta(g, corDeFundo);
                        
                        if(listaPoligono != null){
                            listaPoligono.removerInicio();
                        }
                        indexPoligono--;
                    }
                }
            }
        });
        corItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                corPrincipal = JColorChooser.showDialog(null, "Selecione uma cor", corPrincipal);
            }
        });
        
        corDeFundoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                corDeFundo = JColorChooser.showDialog(null, "Selecione uma cor", corPrincipal);
                painel.setBackground(corDeFundo);
            }
        });
    }
    
    private GUI retornaGUI(){
        return this;
    }
    
    private void iniciarEventosEspessura(){
    
        espessuraItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Espessura esp = new Espessura(retornaGUI(), espessura);
                esp.setVisible(true);
            }
        });
    
    }
}
