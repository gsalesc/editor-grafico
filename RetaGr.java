package reta;

import java.awt.Color;
import java.awt.Graphics;
import ListaDuplamenteLigada.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import ponto.*;
/**
 * Reta gráfica
 * 
 * @author Gabriel Sales 
 */
public class RetaGr extends Reta
{
    Color corReta;    
    int esp;
    ListaDuplamenteLigadaCircular pontosReta;
    ListaDuplamenteLigadaCircular retas2;
    static ListaDuplamenteLigadaCircular pontosPoligono;
    static ArrayList listaReta = new ArrayList<ListaDuplamenteLigadaCircular>();
    static ArrayList listaReta2 = new ArrayList<ListaDuplamenteLigadaCircular>(); 
    
    public RetaGr(){
        
    }
    
    public RetaGr(Ponto p1, Ponto p2, Color cor){
        super(p1, p2);
        setCorReta(cor);
    }
    
    public RetaGr(Ponto p1, Ponto p2){
        super(p1, p2);
    }
    
    public RetaGr(int x1, int y1, int x2, int y2){
        super(x1, y1, x2, y2);
    }
    
    public RetaGr(int x1, int y1, int x2, int y2, int esp){
        super(x1, y1, x2, y2);
        setEsp(esp);
    }
    
    public RetaGr(int x1, int y1, int x2, int y2, Color c, int esp){
        super(x1, y1, x2, y2);
        setCorReta(c);
        setEsp(esp);
    }
    
    public void setCorReta(Color c){
        this.corReta = c;
    }
    
    public Color getCorReta(){
        return this.corReta;
    }
    
    public void setEsp(int e){
        this.esp = e;
    }
    
    public int getEsp(){
        return this.esp;
    }
    
    public void adicionarLista(ListaDuplamenteLigadaCircular c){
        listaReta.add(c);
    }
    
    public static ListaDuplamenteLigadaCircular getListaReta(int i){
        return (ListaDuplamenteLigadaCircular)listaReta.get(i-1);
    }
      
    public static ArrayList getLista(){
        return listaReta;
    }
    
    public static ArrayList getListaNorm(){
        return listaReta2;
    }
    
    /**
     * Desenha uma reta
     * 
     * @param g - classe gráfica
     */
    public void desenharReta(Graphics g){
        double x1 = p1.getX();
        double y1 = p1.getY();
        double x2 = p2.getX();
        double y2 = p2.getY();        
        double y = y1;
        
        pontosReta = new ListaDuplamenteLigadaCircular();
        
        if(x1 < x2){
            for(double x = x1; x <= x2; x++){
                y = calcularM()*x + calcularB();
                PontoGr p = new PontoGr((int)x, (int)y, getCorReta(), getEsp());
                p.desenharPonto(g);
            }
        }
        else if(x2 < x1){
            for(double x = x2; x <= x1; x++){
                y = calcularM()*x + calcularB();
                PontoGr p = new PontoGr((int)x, (int)y, getCorReta(), getEsp());
                p.desenharPonto(g);
            }           
        }
        else{
            double x = x1;
            if(y1 > y2){
                for(y = y2; y <= y1; y++){
                    PontoGr p = new PontoGr((int)x, (int)y, getCorReta(), getEsp());
                    p.desenharPonto(g);
                }    
            }
            else{
                for(y = y1; y <= y2; y++){
                    PontoGr p = new PontoGr((int)x, (int)y, getCorReta(), getEsp());
                    p.desenharPonto(g);
                }
            }
        }
    }
    
    /**
     * Desenha uma reta
     * 
     * @param g - classe gráfica
     * @param r - classe da reta a ser armazenada
     */
    public void desenharReta(Graphics g, RetaGr r){
        double x1 = p1.getX();
        double y1 = p1.getY();
        double x2 = p2.getX();
        double y2 = p2.getY();        
        double y = y1;
    
        retas2 = new ListaDuplamenteLigadaCircular();
        
        if(x1 < x2){
            for(double x = x1; x <= x2; x++){
                y = calcularM()*x + calcularB();
                PontoGr p = new PontoGr((int)x, (int)y, getCorReta(), getEsp());
                p.desenharPonto(g);
            }
        }
        else if(x2 < x1){
            for(double x = x2; x <= x1; x++){
                y = calcularM()*x + calcularB();
                PontoGr p = new PontoGr((int)x, (int)y, getCorReta(), getEsp());
                p.desenharPonto(g);
            }           
        }
        else{
            double x = x1;
            if(y1 > y2){
                for(y = y2; y <= y1; y++){
                    PontoGr p = new PontoGr((int)x, (int)y, getCorReta(), getEsp());
                    p.desenharPonto(g);
                }    
            }
            else{
                for(y = y1; y <= y2; y++){
                    PontoGr p = new PontoGr((int)x, (int)y, getCorReta(), getEsp());
                    p.desenharPonto(g);
                }
            }
        }
        retas2.inserirInicio(r);
        adicionarLista(retas2);
        //JOptionPane.showMessageDialog(null, listaReta.size());
    }
    
    /**
     * Desenha uma reta com o algoritmo de Bresenham
     * 
     * @param g - classe gráfica
     * @param r - classe da reta a ser armazenada
     */
    public void desenharRetaMp(Graphics g, RetaGr r){
        int x1 = (int)p1.getX();
        int y1 = (int)p1.getY();
        int x2 = (int)p2.getX();
        int y2 = (int)p2.getY();
          
        int dx, dy, p, p2, xy2, x, y, xf;
        dx = x2-x1;
        dy = y2-y1;
        p = 2 * dy - dx;
        p2 = 2*dy;
        xy2 = 2*dy - 2*dx;
        
        if(x1 > x2){
            x = x2; y = y2; xf = x1;
        }
        else{
            x = x1; y = y1; xf = x2;
            p = -p;
            p2 = -p2;
            xy2 = -xy2;
        }
        
        PontoGr ponto = new PontoGr((int)x, (int)y, getCorReta(), getEsp());
        ponto.desenharPonto(g);
        
        while(x < xf){
            x++;
            
            if(p<0){ p += p2;}
            
            else{
                y++;
                p += xy2;
            }
            
            PontoGr ponto2 = new PontoGr(x, y, getCorReta(), getEsp());
            ponto2.desenharPonto(g);  
        }
    }
    
    
    /**
     * Apaga última reta desenhada
     * 
     * @param g - classe gráfica
     * @param c - cor de fundo
     */
    
    public static void apagarReta(Graphics g, Color c){
    
        ListaDuplamenteLigadaCircular retas = getListaReta(listaReta.size());
        
        /*Remover r = new Remover();
        r.removerForma(g, reta, c);*/
        
        No atual = retas.getInicio();
        RetaGr r = (RetaGr)atual.getConteudo();
        
        r.setCorReta(c);
        r.desenharReta(g);
            
        listaReta.remove(retas);
    }
    
    /**
     * Deleta uma reta selecionada
     * 
     * @param g - classe gráfica
     * @param c - cor de fundo
     * @param x1 - coordenada x
     * @param y1 - coordenada y
     */
    public static void deletarReta(Graphics g, Color c, int x1, int y1){
        if(listaReta.size() > 0){
            int i = selecionarReta(x1, y1);

            if(i != -1){
                ListaDuplamenteLigadaCircular retas = getListaReta(i+1);

                No atual = retas.getInicio();
                RetaGr r = (RetaGr)atual.getConteudo();
                
                r.setCorReta(c);
                r.desenharReta(g);
                    
                listaReta.remove(retas);            
            }
        }  
    
    }
 
    /**
     * Seleciona o poligono 
     * 
     * @param x1 - posição em x
     * @param y1 - posição em y
     */
     
    public static int selecionarReta(int x1, int y1){
        boolean achou = false;
        int i = -1;
        ListaDuplamenteLigadaCircular retas = null;
        
        if(listaReta.size() > 0){
            do{
                //pegando ṕonto da lista
                retas = (ListaDuplamenteLigadaCircular)listaReta.get(i+1);
                No atual = retas.getInicio();
                RetaGr r = (RetaGr)atual.getConteudo();
            
                //montar equação da reta
                int m = (int)r.calcularM();
                int b = (int)r.calcularB();
                //y = m*x + b
        
                //int valor = (m*x1 + b - y1);
                int valor = (int)(r.calcularM()*x1 + r.calcularB() - y1);
                
                /*if((int)r.getP1().getX() > x1){
                    
                }*/
                //verificar se x1 e y1 pertencem à reta
                if(valor < 100 && valor > -100){ //valor aproximado
                    achou = true;
                }
                else {
                    i++;
                }
            }while(achou == false && i < listaReta.size());
            
            if(achou == true){
                i = listaReta.indexOf(retas);
            }
            else{
                i = -1;
            }
        }
        
        return i;
    }
    
    /**
     * Redesenha as retas criadas
     * 
     * @param g - classe gráfica
     */
    
    public static void redesenharReta(Graphics g){
        if(listaReta.size() > 0){
            for(int i = 0; i < listaReta.size(); i++){
                ListaDuplamenteLigadaCircular retas = (ListaDuplamenteLigadaCircular)listaReta.get(i);
                No atual = retas.getInicio();
                RetaGr r = (RetaGr)atual.getConteudo();
                r.desenharReta(g); 
            }
        }    
    }
    
    /**
     * Realiza a translação da reta
     * 
     * @param g - classe gráfica
     * @param i - posição do poligono na lista
     * @param x1 - primeira posição clicada em x
     * @param y1 - primeira posição clicada em y
     * @param x2 - nova posição em x
     * @param y2 - nova posição em y
     * @param c - cor de fundo
     */
    public static void transladarReta(Graphics g, int i, int x1, int y1, int x2, int y2, Color c){
        ListaDuplamenteLigadaCircular retas = (ListaDuplamenteLigadaCircular)listaReta.get(i);
        No atual = retas.getInicio();
    
        RetaGr reta = (RetaGr)atual.getConteudo();
        RetaGr reta2 = (RetaGr)atual.getConteudo();
        
        RetaGr nova = reta;
        Color cor = nova.getCorReta();
        reta.setCorReta(c);
        reta.desenharReta(g);
        reta.setCorReta(cor);
        
        nova.getP1().setX(nova.getP1().getX() + (x2-x1));
        nova.getP1().setY(nova.getP1().getY() + (y2-y1));
        nova.getP2().setX(nova.getP2().getX() + (x2-x1));
        nova.getP2().setY(nova.getP2().getY() + (y2-y1));
        nova.desenharReta(g);
    }
    
    /**
     * Realiza a conversão em coordenada normalizada
     * 
     * @param xSize - tamanho da tela em x
     * @param ySize - tamanho da tela em y
     */
    public static ArrayList salvarRetaCoordNorm(int xSize, int ySize){
        
        for(int i = 0; i < listaReta.size(); i++){
            ListaDuplamenteLigadaCircular retas = (ListaDuplamenteLigadaCircular)listaReta.get(i);
            No atual = retas.getInicio();
        
            RetaGr reta = (RetaGr)atual.getConteudo();
            RetaGr nova = reta;
            
            double xNorm1 = (100*(nova.getP1().getX()))/xSize;
            double yNorm1 = (100*(nova.getP1().getY()))/ySize;
            double xNorm2 = (100*(nova.getP2().getX()))/xSize;
            double yNorm2 = (100*(nova.getP2().getY()))/ySize;
            
            nova.getP1().setX(xNorm1);
            nova.getP1().setY(yNorm1);
            nova.getP2().setX(xNorm2);
            nova.getP2().setY(yNorm2);
            
            retas.removerInicio();
            retas.inserirInicio(nova);
            listaReta2.add(retas);
        }
        
        return listaReta2;
    }
}
    