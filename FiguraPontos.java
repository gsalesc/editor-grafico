package ponto;

import java.awt.Color;
import java.awt.Graphics;


/**
 * Escreva uma descrição da classe FiguraPontos aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class FiguraPontos
{
    public static void desenharPonto(Graphics g, double x, double y, int diametro, Color cor){
            /*Color cor = new Color((int) (Math.random() * 256),  
                    (int) (Math.random() * 256),  
                    (int) (Math.random() * 256));*/
            PontoGr p = new PontoGr((int)x, (int)y, cor, diametro);
            p.desenharPonto(g);
    }
}
