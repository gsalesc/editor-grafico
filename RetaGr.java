import java.awt.Color;
import java.awt.Graphics;

/**
 * Escreva uma descrição da classe RetaGr aint qui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class RetaGr extends Reta
{
    Color corReta;    
    int esp;
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
   
    public void desenharReta(Graphics g){
        double x1 = p1.getX();
        double y1 = p1.getY();
        double x2 = p2.getX();
        double y2 = p2.getY();        
        double y = y1;
        
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
        //g.drawLine(x1, y1, x2, y2);
    }
}
