import java.awt.Color;
import java.awt.Graphics;
import ListaDuplamenteLigada.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
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
    ListaDuplamenteLigadaCircular pontosReta;
    static ArrayList listaReta = new ArrayList<ListaDuplamenteLigadaCircular>();
    
    public RetaGr(){
        
    }
    
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
    
    public RetaGr(int x1, int y1, int x2, int y2, Color c, int esp){
        super(x1, y1, x2, y2);
        setCorReta(c);
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
    
    public void adicionarLista(ListaDuplamenteLigadaCircular c){
        listaReta.add(c);
    }
    
    public static ListaDuplamenteLigadaCircular getListaReta(int i){
        return (ListaDuplamenteLigadaCircular)listaReta.get(i-1);
    }
      
    public void desenharReta(Graphics g){
        double x1 = p1.getX();
        double y1 = p1.getY();
        double x2 = p2.getX();
        double y2 = p2.getY();        
        double y = y1;
        
        pontosReta = new ListaDuplamenteLigadaCircular();
        
        if(x1 < x2){
            for(double x = x1; x <= x2; x++){
                y = calcularM()*x + calcularB();
                PontoGr p = new PontoGr((int)x, (int)y, getCorReta(), getEsp());
                p.desenharPonto(g);
                
                PontoGr ponto = new PontoGr((int)x, (int)y, getCorReta(), getEsp());
                pontosReta.inserirInicio(ponto);
            }
        }
        else if(x2 < x1){
            for(double x = x2; x <= x1; x++){
                y = calcularM()*x + calcularB();
                PontoGr p = new PontoGr((int)x, (int)y, getCorReta(), getEsp());
                p.desenharPonto(g);
                
                PontoGr ponto = new PontoGr((int)x, (int)y, getCorReta(), getEsp());
                pontosReta.inserirInicio(ponto);
            }           
        }
        else{
            double x = x1;
            if(y1 > y2){
                for(y = y2; y <= y1; y++){
                    PontoGr p = new PontoGr((int)x, (int)y, getCorReta(), getEsp());
                    p.desenharPonto(g);
                    
                    PontoGr ponto = new PontoGr((int)x, (int)y, getCorReta(), getEsp());
                    pontosReta.inserirInicio(ponto);
                }    
            }
            else{
                for(y = y1; y <= y2; y++){
                    PontoGr p = new PontoGr((int)x, (int)y, getCorReta(), getEsp());
                    p.desenharPonto(g);
                    
                    PontoGr ponto = new PontoGr((int)x, (int)y, getCorReta(), getEsp());
                    pontosReta.inserirInicio(ponto);
                }
            }
        }
        adicionarLista(pontosReta);
        //JOptionPane.showMessageDialog(null, listaReta.size());
    }
    
    public static void apagarReta(Graphics g, Color c){
    
        ListaDuplamenteLigadaCircular reta = getListaReta(listaReta.size());
        
        Remover r = new Remover();
        r.removerForma(g, reta, c);
            
        listaReta.remove(reta);
    }
}
    