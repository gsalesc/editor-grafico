import java.awt.Graphics;
import java.awt.Color;

/**
 * Escreva uma descrição da classe FiguraRetangulo aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class FiguraRetangulo
{
    public static void desenharRetangulo(Graphics g, Ponto p1, Ponto p2, Color cor, int esp){
        RetanguloGr r = new RetanguloGr(p1, p2, cor, esp);
        r.desenharRetangulo(g);
    }
        
    public static void apagarRetangulo(Graphics g, Color c){
        RetanguloGr.apagarRetangulo(g, c);
    }
}
