import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import ListaDuplamenteLigada.*;

/**
 * Escreva uma descrição da classe RetanguloGr aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class RetanguloGr extends Retangulo
{
    Color cor;
    int esp;
    static ArrayList listaRet = new ArrayList<DiagonalRet>();
    
    RetanguloGr(Ponto p1, Ponto p2){
        super(p1, p2);
    }
    
    RetanguloGr(Ponto p1, Ponto p2, Color c, int e){
        super(p1, p2);
        this.cor = c;
        this.esp = e;
    }
       
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
        
        DiagonalRet dr = new DiagonalRet(getPontoP1(), getPontoP2(), this.esp);
        listaRet.add(dr);
        
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
    
    public static void apagarRetangulo(Graphics g, Color c){
        DiagonalRet apagar = (DiagonalRet)listaRet.get(listaRet.size()-1);
        
        int x1, y1;
        int x2, y2;
        int x3, y3;
        int x4, y4;
        int esp = apagar.getEsp();
        
        x1 = (int)apagar.getP1().getX();
        y1 = (int)apagar.getP1().getY();
        x4 = (int)apagar.getP2().getX();
        y4 = (int)apagar.getP2().getY();
        
        //x2, y2 -> ponta superior direita
        x2 = x4;
        y2 = y1;
        //x3, y3 -> ponta inferior esquerda
        x3 = x1;
        y3 = y4;
        
        //ponta superior esquerda ->  ponta superior direita
        RetaGr r1 = new RetaGr(x1, y1, x2, y2, c, esp);
        r1.desenharReta(g);
        
        //ponta superior direita -> ponta inferior direita
        RetaGr r2 = new RetaGr(x2, y2, x4, y4, c, esp);
        r2.desenharReta(g);
        
        //ponta inferior direita -> ponta inferior esquerda
        RetaGr r3 = new RetaGr(x4, y4, x3, y3, c, esp);
        r3.desenharReta(g);
        
        //ponta inferior esquerda -> ponto superior esquerda
        RetaGr r4 = new RetaGr(x3, y3, x1, y1, c, esp);
        r4.desenharReta(g); 
        
        listaRet.remove(apagar);
    }
}
