package retangulo;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import ListaDuplamenteLigada.*;
import ponto.*;
import reta.*;

/**
 * Retangulo gráfico
 * 
 * @author Gabriel Sales
 * @version 
 */
public class RetanguloGr extends Retangulo
{
    Color cor;
    int esp;
    static ArrayList listaRet = new ArrayList<RetanguloGr>();
    
    RetanguloGr(Ponto p1, Ponto p2){
        super(p1, p2);
    }
    
    RetanguloGr(Ponto p1, Ponto p2, Color c, int e){
        super(p1, p2);
        this.cor = c;
        this.esp = e;
    }

    /**
     * Desenha um retangulo
     * 
     * @param g - classe gráfica
     */
    public void desenharRetangulo(Graphics g){
        
        //desenhando retangulo a partir dos pontos da diagonal
        int x1, y1;
        int x2, y2;
        int x3, y3;
        int x4, y4;
    
        //x1, y1 -> ponta superior esquerda (ponto 1 da diagonal)
        x1 = (int)getPontoP1().getX();
        y1 = (int)getPontoP1().getY();
        //x4, y4 -> ponta inferior direita (ponto 2 da diagonal)
        x4 = (int)getPontoP2().getX();
        y4 = (int)getPontoP2().getY();
        
        /*//adiciona na lista de diagonais
        DiagonalRet dr = new DiagonalRet(getPontoP1(), getPontoP2(), this.esp, this.cor);
        listaRet.add(dr);*/
        
        //x2, y2 -> ponta superior direita
        x2 = x4;
        y2 = y1;
        //x3, y3 -> ponta inferior esquerda
        x3 = x1;
        y3 = y4;
        
        //ponta superior esquerda ->  ponta superior direita
        RetaGr r1 = new RetaGr(x1, y1, x2, y2, this.cor, this.esp);
        r1.desenharReta(g);
        
        //ponta superior direita -> ponta inferior direita
        RetaGr r2 = new RetaGr(x2, y2, x4, y4, this.cor, this.esp);
        r2.desenharReta(g);
        
        //ponta inferior direita -> ponta inferior esquerda
        RetaGr r3 = new RetaGr(x4, y4, x3, y3, this.cor, this.esp);
        r3.desenharReta(g);
        
        //ponta inferior esquerda -> ponto superior esquerda
        RetaGr r4 = new RetaGr(x3, y3, x1, y1, this.cor, this.esp);
        r4.desenharReta(g);   
    }
    
    /**
     * Desenha um retangulo
     * 
     * @param g - classe gráfica
     * @param r - classe do retângulo a ser armazenada
     */
    public void desenharRetangulo(Graphics g, RetanguloGr r){
        //desenhando retangulo a partir dos pontos da diagonal
        int x1, y1;
        int x2, y2;
        int x3, y3;
        int x4, y4;
    
        //x1, y1 -> ponta superior esquerda (ponto 1 da diagonal)
        x1 = (int)getPontoP1().getX();
        y1 = (int)getPontoP1().getY();
        //x4, y4 -> ponta inferior direita (ponto 2 da diagonal)
        x4 = (int)getPontoP2().getX();
        y4 = (int)getPontoP2().getY();
        
        //x2, y2 -> ponta superior direita
        x2 = x4;
        y2 = y1;
        //x3, y3 -> ponta inferior esquerda
        x3 = x1;
        y3 = y4;
        
        //ponta superior esquerda ->  ponta superior direita
        RetaGr r1 = new RetaGr(x1, y1, x2, y2, this.cor, this.esp);
        r1.desenharReta(g);
        
        //ponta superior direita -> ponta inferior direita
        RetaGr r2 = new RetaGr(x2, y2, x4, y4, this.cor, this.esp);
        r2.desenharReta(g);
        
        //ponta inferior direita -> ponta inferior esquerda
        RetaGr r3 = new RetaGr(x4, y4, x3, y3, this.cor, this.esp);
        r3.desenharReta(g);
        
        //ponta inferior esquerda -> ponto superior esquerda
        RetaGr r4 = new RetaGr(x3, y3, x1, y1, this.cor, this.esp);
        r4.desenharReta(g);   
        
        listaRet.add(r);
    }
 
    /**
     * Apaga o último retangulo
     * 
     * @param g - classe gráfica
     * @param c - cor de fundo
     */
    public static void apagarRetangulo(Graphics g, Color c){

        RetanguloGr r = (RetanguloGr)listaRet.get(listaRet.size()-1);
        r.cor = c;
        r.desenharRetangulo(g);
        listaRet.remove(r);
    }
    
    /**
     * Redesenha os retângulos armazenados na lista
     * 
     * @param g - classe gráfica
     */
    public static void redesenharRetangulo(Graphics g){
        if(listaRet.size() > 0){
            for(int i = 0; i < listaRet.size(); i++){
                RetanguloGr r = (RetanguloGr)listaRet.get(i);
                r.desenharRetangulo(g);
            }
        } 
    }
    
    /**
     * Deleta um retangulo selecionado
     * 
     * @param g - classe gráfica
     * @param c - cor de fundo
     * @param xMouse - coordenada x
     * @param yMouse - coordenada y
     */
    public static void deletarRetangulo(Graphics g, Color c, int xMouse, int yMouse){
        
        if(listaRet.size() > 0){
            boolean achou = false;
            int i = selecionarRetangulo(xMouse, yMouse);
            
            if(i != 1){
                RetanguloGr ret = (RetanguloGr)listaRet.get(i);
                ret.cor = c;
                ret.desenharRetangulo(g);
                listaRet.remove(ret);
            }
        }
    }
    
    /**
     * Seleciona o retângulo 
     * 
     * @param xMouse - posição em x
     * @param yMouse - posição em y
     */
    public static int selecionarRetangulo(int xMouse, int yMouse){
        boolean achou = false;
        int i = -1;
        RetanguloGr ret;
        
        do{
            ret = (RetanguloGr)listaRet.get(i+1);
            int x1 = (int)ret.getPontoP1().getX();
            int y1 = (int)ret.getPontoP1().getY();
            int x4 = (int)ret.getPontoP2().getX();
            int y4 = (int)ret.getPontoP2().getY();
            int x2 = x4;
            int y2 = y1;
            int x3 = x1;
            int y3 = y4;
            
            if(((x1-20 <= xMouse && xMouse <= x1+20) &&  (y1-20 <= yMouse && yMouse <= y1+20)) || 
                ((x2-20 <= xMouse && xMouse <= x2+20) && (y2-20 <= yMouse && yMouse <= y2+20)) || 
                ((x3-20 <= xMouse && xMouse <= x3+20) && (y3-20 <= yMouse && yMouse <= y3+20)) || 
                ((x4-20 <= xMouse && xMouse <= x4+20) && (y4-20 <= yMouse && yMouse <= y4+20)) ){
                achou = true;  
            }
            else{
                i++;
            }
        
        }  while(achou == false && i < listaRet.size());
    

        if(achou = true){
            i = listaRet.indexOf(ret);
        }
        else{
            i = -1;
        }
        return i;
    }
    
    /**
     * Realiza a translação do retângulo
     * 
     * @param g - classe gráfica
     * @param i - posição do poligono na lista
     * @param x1 - primeira posição clicada em x
     * @param y1 - primeira posição clicada em y
     * @param x2 - nova posição em x
     * @param y2 - nova posição em y
     * @param c - cor de fundo
     */
    public static void transladarRetangulo(Graphics g, int i, int x1, int y1, int x2, int y2, Color c){
        RetanguloGr novo = (RetanguloGr)listaRet.get(i);    
        
        Color cor = novo.cor;
        novo.cor = c;
        novo.desenharRetangulo(g);
        novo.cor = cor;
        novo.getPontoP1().setX(novo.getPontoP1().getX() + (x2-x1));
        novo.getPontoP1().setY(novo.getPontoP1().getY() + (y2-y1));
        novo.getPontoP2().setX(novo.getPontoP2().getX() + (x2-x1));
        novo.getPontoP2().setY(novo.getPontoP2().getY() + (y2-y1));
        novo.desenharRetangulo(g);
    }
}
