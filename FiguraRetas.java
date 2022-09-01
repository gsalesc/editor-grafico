import java.awt.Graphics;
import java.awt.Color;
import ListaDuplamenteLigada.*;
import java.util.ArrayList;

/**
 * Escreva uma descrição da classe FiguraRetas aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class FiguraRetas
{
    public static void desenharPontos(Graphics g, double x1, double y1, int x2, int y2, Color c, int esp){
        RetaGr p = new RetaGr((int)x1, (int)y1, (int)x2, (int)y2, c, esp);
        p.desenharReta(g);
    }
    
    public static void apagarReta(Graphics g, Color c){
        RetaGr.apagarReta(g, c);
    }
}
