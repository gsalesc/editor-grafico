import java.awt.Graphics;
import java.awt.Color;

/**
 * Escreva uma descrição da classe FiguraCirculo aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class FiguraCirculo
{
    /*public static void desenharPontos(Graphics g, int cX, int cY, int raio){
            CirculoGr c = new CirculoGr(cX, cY, raio);
            c.desenharCirculo(g);
    }*/
    
    
    public static void desenharPontos(Graphics g, int cX, int cY, int rx, int ry, Color cor, int esp){
            CirculoGr c = new CirculoGr(cX, cY, rx, ry, cor, esp);
            c.desenharCirculo(g);
    }
    
}
