import ListaDuplamenteLigada.*;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Funçao para remover formas
 * 
 * @author Gabriel Sales 
 * @version 
 */
public class Remover
{
    
    /**
     * Remove uma forma da tela
     * 
     * @param g - classe gráfica
     * @param apagar - lista com os pontos a serem apagados
     * @param cor - cor de fundo
     */
    public void removerForma(Graphics g, ListaDuplamenteLigadaCircular apagar, Color cor){
    
        No atual = apagar.getInicio();
        
        while(atual != apagar.getFim()){
            PontoGr p = (PontoGr)atual.getConteudo();
        
            int x = (int)p.getX();
            int y = (int)p.getY();
            int esp = p.getEsp();
            
            PontoGr pg = new PontoGr(x, y, cor, esp);
            pg.desenharPonto(g);
            
            atual = atual.getProximo();
        }     
    }
}
