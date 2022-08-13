import java.awt.Graphics;

/**
 * Escreva uma descrição da classe FiguraRetas aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class FiguraRetas
{
    public static void desenharPontos(Graphics g, double x1, double y1, int x2, int y2, int esp){
            RetaGr p = new RetaGr((int)x1, (int)y1, (int)x2, (int)y2, esp);
            p.desenharReta(g);
    }
}
