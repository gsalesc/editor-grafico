
package circulo;
import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import ListaDuplamenteLigada.*;
import ponto.*;

/**
 * Circulo gráfico
 * 
 * @author Gabriel Sales
 * @version 
 */

public class CirculoGr extends Circulo
{
    Color cor;
    int esp;
    ListaDuplamenteLigadaCircular circulo;
    static ArrayList listaCirculo = new ArrayList<ListaDuplamenteLigadaCircular>();
    static ArrayList listaCirculo2 = new ArrayList<CirculoGr>();
    
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
    
    public void adicionarLista(ListaDuplamenteLigadaCircular c){
        listaCirculo.add(c);
    }
    
    public static ListaDuplamenteLigadaCircular getListaCirculo(int i){
        return (ListaDuplamenteLigadaCircular)listaCirculo.get(i);
    }

    /**
     * Desenha um círculo
     * 
     * @param g - classe gráfica
     * @param c - classe do circulo a ser armazenada
     */
    
    public void desenharCirculo(Graphics g, CirculoGr c){
        int centroX = getCentroX();
        int centroY = getCentroY();
        double raio = Math.sqrt(Math.pow(getRaioX()-(double)centroX, 2) + Math.pow(getRaioY()-(double)centroY, 2));
        c.setRaio((int)raio);      
        double x = centroX, y = centroY, passo = 0; 
        double angulo = 1;
        
        circulo = new ListaDuplamenteLigadaCircular();
        
        while(passo <= 360){
            x = centroX + raio*Math.cos(Math.toRadians(passo));
            y = centroY + raio*Math.sin(Math.toRadians(passo));
            PontoGr p = new PontoGr((int)x, (int)y, cor, esp);
            p.desenharPonto(g);           
            passo += angulo;
        }
    
        circulo.inserirInicio(c);
        listaCirculo.add(circulo);
        listaCirculo2.add(c);
    }
    
    /**
     * Desenha um círculo
     * 
     * @param g - classe gráfica
     */
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

    /**
     * Apaga o último círculo criado
     * 
     * @param g - classe gráfica
     * @param c - cor de fundo
     */
    public static void apagarCirculo(Graphics g, Color c){
        
        //ListaDuplamenteLigadaCircular circulo = getListaCirculo(listaCirculo.size());
        
        //No atual = circulo.getInicio();
        CirculoGr cr = (CirculoGr)listaCirculo2.get(listaCirculo.size()-1);
        cr.cor = c;
        cr.desenharCirculo(g);
        
        listaCirculo2.remove(cr);
    }
    
    /**
     * Redesenha os circulos criados
     * 
     * @param g - classe gráfica
     */
    
    public static void redesenharCirculo(Graphics g){
        if(listaCirculo.size() > 0){
            for(int i = 0; i < listaCirculo2.size(); i++){
                CirculoGr cr = (CirculoGr)listaCirculo2.get(i);
                cr.desenharCirculo(g);
            }
        }    
    }
    
    /**
     * Deleta um círculo selecionado
     * 
     * @param g - classe gráfica
     * @param c - cor de fundo
     * @param xMouse - coordenada x
     * @param yMouse - coordenada y
     */
    
    public static void deletarCirculo(Graphics g, Color c, int xMouse, int yMouse){
        if(listaCirculo.size() > 0){
            int i = selecionarCirculo(xMouse, yMouse);
            if(i != -1){
                CirculoGr circulo = (CirculoGr)listaCirculo2.get(i);
                circulo.cor = c;
                circulo.desenharCirculo(g);
            }
        } 
    }
    
    /**
     * Seleciona o círculo 
     * 
     * @param xMouse - posição em x
     * @param yMouse - posição em y
     */
    public static int selecionarCirculo(int xMouse, int yMouse){
        boolean achou = false;
        int i = -1;
        CirculoGr circulo;
        do{
            circulo = (CirculoGr)listaCirculo2.get(i+1);
            //CirculoGr circulo = cir.              
            int raio = circulo.getRaio();
            int xCentro = circulo.getCentroX();
            int yCentro = circulo.getCentroY();
            
            int eqCir = xMouse*xMouse + yMouse*yMouse - 2*xCentro*xMouse - 2*yCentro*yMouse + xCentro*xCentro + yCentro*yCentro - raio*raio;
        
            if(eqCir > -10000 && eqCir < 10000){
                achou = true;
            }
            else{
                i++;
            }
        
        } while(achou == false && i < listaCirculo.size());   
        
        if(achou == true){
            i = listaCirculo2.indexOf(circulo);
        }
        else{
            i = -1;
        }
        
        return i;
    }

    /**
     * Realiza a translação do poligono
     * 
     * @param g - classe gráfica
     * @param i - posição do poligono na lista
     * @param x1 - primeira posição clicada em x
     * @param y1 - primeira posição clicada em y
     * @param x2 - nova posição em x
     * @param y2 - nova posição em y
     * @param c - cor de fundo
     */
    public static void transladarCirculo(Graphics g, int i, int x1, int y1, int x2, int y2, Color c){
        CirculoGr novo = (CirculoGr)listaCirculo2.get(i);    
        
        Color cor = novo.cor;
        novo.cor = c;
        novo.desenharCirculo(g);
        novo.cor = cor;
        int raio = novo.getRaio();
        novo.setCentroX(novo.getCentroX() + (x2-x1));
        novo.setCentroY(novo.getCentroY() + (y2-y1));
        novo.setRaio(raio);
        novo.desenharCirculo(g);
    }
    
    /**
     * Realiza a conversão em coordenada normalizada
     * 
     * @param xSize - tamanho da tela em x
     * @param ySize - tamanho da tela em y
     */
    public static ArrayList salvarCirculoCoordNorm(int xSize, int ySize){
        ArrayList listaCirculo2 = new ArrayList<CirculoGr>();
        
        for(int i = 0; i < listaCirculo.size(); i++){
        
            CirculoGr novo = (CirculoGr)listaCirculo.get(i);
            
            double xNorm1 = (100*(novo.getCentroX()))/xSize;
            double yNorm1 = (100*(novo.getCentroY()))/ySize;
            double xNorm2 = (100*(novo.getRaioX()))/xSize;
            double yNorm2 = (100*(novo.getRaioY()))/ySize;
            
            novo.setCentroX((int)xNorm1);
            novo.setCentroY((int)yNorm1);
            novo.setRaioX((int)xNorm2);
            novo.setRaioY((int)yNorm2);
            
            listaCirculo2.add(novo);
        }
        
        return listaCirculo2;
    }
}
