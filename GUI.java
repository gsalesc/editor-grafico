import java.awt.BorderLayout;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Cursor;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JButton;
import java.util.ArrayList;
import ListaDuplamenteLigada.*;
import ponto.*;
import reta.*;
import circulo.*;
import retangulo.*;
import poligono.*;
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
    private JMenu desenhoMenu, opcoesMenu, arquivoMenu;
    private JMenuItem corItem, espessuraItem, corDeFundoItem, apagarItem, redesenharItem, limparItem;
    private JMenuItem selecionarForma, deletarFormaItem;
    private JMenuItem salvarItem;
    int xMouse, yMouse;
    int contarClique = 0;
    String formaAtual;
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
    ListaDuplamenteLigadaCircular listaPoligono = null; //desenhar as linhas do poligono 
    JButton btnPonto, btnReta, btnCir, btnRet, btnPoli, btnLinha;
    int indexPonto = 0;
    int indexReta = 0;
    int indexCirculo = 0;
    int indexRetangulo = 0;
    int indexPoligono = 0;
    boolean apagarDesenho = false;
    boolean selecionarDesenho = false;
    int selecaoForma = -1;
    
    GUI(){
        this.setSize(900, 700);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        menu = new JMenuBar();
        setJMenuBar(menu);
        
        desenhoMenu = new JMenu("Desenho");
        opcoesMenu = new JMenu("Opções");
        arquivoMenu = new JMenu("Arquivo");
        
        menu.add(desenhoMenu);
        menu.add(opcoesMenu);
        menu.add(arquivoMenu);
    
        apagarItem = new JMenuItem("Voltar");
        redesenharItem = new JMenuItem("Redesenhar");
        limparItem = new JMenuItem("Limpar");
        selecionarForma = new JMenuItem("Selecionar");
        deletarFormaItem = new JMenuItem("Deletar");
        corItem = new JMenuItem("Cor");
        espessuraItem = new JMenuItem("Espessura");
        corDeFundoItem = new JMenuItem("Cor de fundo");
        
        salvarItem = new JMenuItem("Salvar");

    
        desenhoMenu.add(selecionarForma);
        desenhoMenu.add(deletarFormaItem);
        desenhoMenu.add(apagarItem);
        desenhoMenu.add(limparItem);
        desenhoMenu.add(redesenharItem);
        opcoesMenu.add(corItem);
        opcoesMenu.add(espessuraItem);
        opcoesMenu.add(corDeFundoItem);
        arquivoMenu.add(salvarItem);
        
        JPanel painelFormas = new JPanel();  
        this.add(painelFormas, BorderLayout.NORTH);

        btnPonto = new JButton("Pontos");
        btnReta = new JButton("Reta");
        btnCir = new JButton("Circulo");
        btnRet = new JButton("Retangulo");
        btnPoli = new JButton("Poligono");
        btnLinha = new JButton("Linha Poligonal");
        
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
        Dimension d = menu.getSize();
        xMouse += 10;
        yMouse += 90;
        
        if(apagarDesenho == false && selecionarDesenho == false){
            if(!primeiraVez){
                if(tipo == TiposPrimitivos.PONTOS){
                    desenharPontos();
                }
                else if(tipo == TiposPrimitivos.RETAS){
                    desenharRetas();
                }   
                else if(tipo == TiposPrimitivos.CIRCULOS){
                    desenharCirculos();  
                }
                else if(tipo == TiposPrimitivos.RETANGULOS){
                    desenharRetangulos();  
                }
                //desenha as linhas do polígono
                else if(tipo == TiposPrimitivos.POLIGONOS){
                    desenharPoligonos();
                }
                //desenha as linhas da linha poligonal
                else if(tipo == TiposPrimitivos.LINHAS){
                    desenharLinhaPoligonal();
                }
            }
        }
        else if (apagarDesenho == true && selecionarDesenho == false){
            //apagarDesenho
            //JOptionPane.showMessageDialog(null, "X: " + xMouse + " Y: " + yMouse);
            if(tipo == TiposPrimitivos.RETAS){
                FiguraRetas.deletarReta(g, corDeFundo, xMouse, yMouse);
            }
            else if(tipo == TiposPrimitivos.CIRCULOS){
                CirculoGr.deletarCirculo(g, corDeFundo, xMouse, yMouse);
            }
            else if(tipo == TiposPrimitivos.RETANGULOS){
                RetanguloGr.deletarRetangulo(g, corDeFundo, xMouse, yMouse);
            }
            else if(tipo == TiposPrimitivos.POLIGONOS){
                PoligonoGr.deletarPoligono(g, corDeFundo, xMouse, yMouse);
            }
            else if(tipo == TiposPrimitivos.LINHAS){
                LinhaPoligonalGr.deletarLinhaPoligonal(g, corDeFundo, xMouse, yMouse);
            }
            apagarDesenho = false;
            painel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            //forma.setText(formaAtual);
        }
        
        else if(apagarDesenho == false && selecionarDesenho == true){
            
            if(tipo == TiposPrimitivos.RETAS){
                if(contarClique == 0){  
                    x1 = xMouse;
                    y1 = yMouse;
                    selecaoForma = RetaGr.selecionarReta(xMouse, yMouse);
                    contarClique++;
                }
                
                else if(contarClique == 1){
                    if(selecaoForma != -1){
                        x2 = xMouse;
                        y2 = yMouse;
                        RetaGr.transladarReta(g, selecaoForma, x1, y1, x2, y2, corDeFundo);
                    }
                    contarClique = 0;
                    selecionarDesenho = false;
                    painel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
                
            }
            else if(tipo == TiposPrimitivos.CIRCULOS){
                if(contarClique == 0){  
                    contarClique++;
                    x1 = xMouse;
                    y1 = yMouse;
                    selecaoForma = CirculoGr.selecionarCirculo(xMouse, yMouse);
                }
                
                else if(contarClique == 1){
                    contarClique = 0; 
                    if(selecaoForma != -1){
                        x2 = xMouse;
                        y2 = yMouse;
                        CirculoGr.transladarCirculo(g, selecaoForma, x1, y1, x2, y2, corDeFundo);
                    }

                    selecionarDesenho = false;
                    painel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
  
            }
            else if(tipo == TiposPrimitivos.RETANGULOS){
                if(contarClique == 0){  
                    x1 = xMouse;
                    y1 = yMouse;
                    selecaoForma = RetanguloGr.selecionarRetangulo(xMouse, yMouse);
                    contarClique++;
                }
                
                else if(contarClique == 1){
                    if(selecaoForma != -1){
                        x2 = xMouse;
                        y2 = yMouse;
                        RetanguloGr.transladarRetangulo(g, selecaoForma, x1, y1, x2, y2, corDeFundo);
                    }
                    contarClique = 0;
                    selecionarDesenho = false;
                    painel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }            
            
            }
            
            else if(tipo == TiposPrimitivos.POLIGONOS){
                if(contarClique == 0){  
                    x1 = xMouse;
                    y1 = yMouse;
                    selecaoForma = PoligonoGr.selecionarPoligono(xMouse, yMouse);
                    contarClique++;
                }
                
                else if(contarClique == 1){
                    if(selecaoForma != -1){
                        x2 = xMouse;
                        y2 = yMouse;
                        PoligonoGr.transladarPoligono(g, selecaoForma, x1, y1, x2, y2, corDeFundo);
                    }
                    contarClique = 0;
                    selecionarDesenho = false;
                    painel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }  
            }
            
            else if(tipo == TiposPrimitivos.LINHAS){
                if(contarClique == 0){  
                    x1 = xMouse;
                    y1 = yMouse;
                    selecaoForma = LinhaPoligonalGr.selecionarPoligono(xMouse, yMouse);
                    contarClique++;
                }
                
                else if(contarClique == 1){
                    if(selecaoForma != -1){
                        x2 = xMouse;
                        y2 = yMouse;
                        LinhaPoligonalGr.transladarPoligono(g, selecaoForma, x1, y1, x2, y2, corDeFundo);
                    }
                    contarClique = 0;
                    selecionarDesenho = false;
                    painel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
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
        if(tipo == TiposPrimitivos.RETAS){
            int indexRetaSelecionada;
        }
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
                //formaAtual = "Ponto";
                contarClique = 0;
            }
        });
        
        btnReta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tipo = TiposPrimitivos.RETAS;
                forma.setText("Retas");
                //formaAtual = "Retas";
                contarClique = 0;
            }
        });
        
        btnCir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tipo = TiposPrimitivos.CIRCULOS;
                forma.setText("Circulos");
                //formaAtual = "Circulo";
                contarClique = 0;
            }
        });
        
        btnRet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tipo = TiposPrimitivos.RETANGULOS;
                forma.setText("Retangulo");
                //formaAtual = "Retangulo";
                contarClique = 0;
            }
        });
        
        btnPoli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tipo = TiposPrimitivos.POLIGONOS;
                forma.setText("Poligono");
                //formaAtual = "Poligono";
                contarClique = 0;
            }
        });
        
        btnLinha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tipo = TiposPrimitivos.LINHAS;
                forma.setText("Linha Poligonal");
                //formaAtual = "Linha Poligonal";
                contarClique = 0;
            }
        });
    }
    
    private void iniciarEventosMenu(){  
        salvarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Gravar gr = new Gravar();
                gr.gravarArquivo();
            }
        });
        
        selecionarForma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selecionarDesenho = true;
                apagarDesenho = false;
                painel.setCursor(new Cursor(Cursor.MOVE_CURSOR));
            }
        });
        deletarFormaItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //forma.setText("Deletando " + formaAtual);
                apagarDesenho = true;
                selecionarDesenho = false;
                painel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
        
        limparItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                retornaPainel().removeAll();
                retornaPainel().paintAll(g);
                setJMenuBar(menu);
            }
        });
        
        
        redesenharItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                retornaPainel().removeAll();
                retornaPainel().paintAll(g);
                FiguraRetas.redesenharReta(g);
                FiguraCirculo.redesenharCirculo(g);
                FiguraRetangulo.redesenharRetangulo(g);
                PoligonoGr.redesenharPoligonos(g);
                LinhaPoligonalGr.redesenharPoligonos(g);
                setJMenuBar(menu);
            }
        });
        
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
    
    private JPanel retornaPainel(){
        return this.painel;
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
    
    private void desenharPontos(){
        FiguraPontos p = new FiguraPontos();
        p.desenharPonto(g, xMouse, yMouse, espessura, corPrincipal); 
    }
    
    private void desenharRetas(){
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
    
    private void desenharCirculos(){
        if(contarClique == 0){
            x1 = xMouse;
            y1 = yMouse;
            contarClique++;
        }
        else if(contarClique == 1){
            x2 = xMouse;
            y2 = yMouse;
            
            FiguraCirculo c = new FiguraCirculo();
            c.desenharCirculo(g, x1, y1, x2, y2, corPrincipal, espessura);
            contarClique = 0;
            indexCirculo++;
        }    
    
    }
    
    private void desenharRetangulos(){
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
    
    private void desenharPoligonos(){
    
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
                
                FiguraRetas.desenharRetas(g, x1, y1, xPontoAnterior, yPontoAnterior, corPrincipal, espessura);
                
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
                
                FiguraRetas.desenharRetas(g, x1, y1, xPrimeiroPonto, yPrimeiroPonto, corPrincipal, espessura);
                
                Poligono pl = new PoligonoGr(listaPoligono, corPrincipal, espessura);
                listaPoligono = null;
                fecharPoligono = false;
                indexPoligono++;
            }
        
        }   
    
    }
    
    private void desenharLinhaPoligonal(){
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
                    
                    FiguraRetas.desenharRetasPoligonos(g, x1, y1, xPontoAnterior, yPontoAnterior, corPrincipal, espessura);
                    
                    //adicionar à lista
                    Ponto ponto = new Ponto(x1, y1);
                    listaPoligono.inserirInicio(ponto);
                    indexPoligono++;
                }
                
                else{
                    Poligono pl = new LinhaPoligonalGr(listaPoligono, corPrincipal, espessura);
                    //PoligonoGr.adicionarLista(listaPoligono);
                    listaPoligono = null;
                    fecharPoligono = false;
                }
            
            }   
    
    }
}
