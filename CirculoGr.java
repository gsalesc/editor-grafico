import java.awt.Graphics;
import java.awt.Color;

/**
 * Escreva uma descrição da classe CirculoGr aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class CirculoGr extends Circulo
{
    Color cor;
    int esp;
    public CirculoGr(int cX, int cY, int raio)
    {
        super(cX, cY, raio);
    }
    
    public CirculoGr(int cX, int cY, int rx, int ry, Color c, int esp)
    {
        super(cX, cY, rx, ry);
        this.cor = c;
        this.esp = esp;
    }
    
    public void desenharCirculo(Graphics g){
        int centroX = getCentroX();
        int centroY = getCentroY();
        double raio = Math.sqrt(Math.pow(getRaioX()-(double)centroX, 2) + Math.pow(getRaioY()-(double)centroY, 2));
                
        double x = centroX, y = centroY, passo = 0; 
        double angulo = 1;
        
        while(passo <= 360){
            x = centroX + raio*Math.cos(Math.toRadians(passo));
            y = centroY + raio*Math.sin(Math.toRadians(passo));
            PontoGr p = new PontoGr((int)x, (int)y, cor, esp);
            p.desenharPonto(g);
            passo += angulo;
        }
    
    }
}