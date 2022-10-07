package poligono;
import ListaDuplamenteLigada.*;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;
import ponto.*;
import reta.*;
/**
 * Poligono gráfico
 * 
 * @author Gabriel Sales 
 * @version (um número da versão ou uma data)
 */
public class PoligonoGr extends Poligono
{
    //static ArrayList listaPoligono = new ArrayList<ListaDuplamenteLigadaCircular>();
    static ArrayList listaPoligono = new ArrayList<PoligonoGr>();

    public PoligonoGr(ListaDuplamenteLigadaCircular v, Color c, int e){
        super(v, c, e);
        listaPoligono.add(retornaP());
    }
    
    public void setLista(ListaDuplamenteLigadaCircular v){
        this.listaVertices = v;
    }
    
    public ListaDuplamenteLigadaCircular getLista(){
        return this.listaVertices;
    }
    
    public PoligonoGr retornaP(){
        return this;
    }
    
    public static void adicionarLista(ListaDuplamenteLigadaCircular c){
        listaPoligono.add(c);
    }
    
    /**
     * Deleta um poligono selecionado
     * 
     * @param g - classe gráfica
     * @param c - cor de fundo
     * @param x - coordenada x
     * @param y - coordenada y
     */    
    //apaga ao clicar em um dos vertices
    public static void deletarPoligono(Graphics g, Color c, int xMouse, int yMouse){
        boolean achou = false;
        int i = 0;
        int x, y;
        int x1, y1;
        int x2, y2;
        ListaDuplamenteLigadaCircular poligono;
        PoligonoGr pol;
        
        if(listaPoligono.size() > 0){
            //procurar o poligono            
            //apagar o poligono
            i = selecionarPoligono(xMouse, yMouse);
            
            if(i != -1){
                pol = (PoligonoGr)listaPoligono.get(i);
                ListaDuplamenteLigadaCircular apagarPoligono = pol.getLista();
                    
                No atual = apagarPoligono.getInicio();
                No prox = atual.getProximo();
                    
                do{
                    Ponto p1 = (Ponto)atual.getConteudo();
                    x1 = (int)p1.getX();
                    y1 = (int)p1.getY();
                    
                    Ponto p2 = (Ponto)prox.getConteudo();
                    x2 = (int)p2.getX();
                    y2 = (int)p2.getY();
                    
                    RetaGr r = new RetaGr(x1, y1, x2, y2, c, 10);
                    r.desenharReta(g);
                    
                    atual = atual.getProximo();
                    prox = atual.getProximo();
                }while(atual != apagarPoligono.getInicio());
                
                listaPoligono.remove(pol);
            }
        }
    }

    /**
     * Seleciona o poligono 
     * 
     * @param xMouse - posição em x
     * @param yMouse - posição em y
     */
    
    public static int selecionarPoligono(int xMouse, int yMouse){
        boolean achou = false;
        ListaDuplamenteLigadaCircular poligono;
        PoligonoGr pol;
        int i = -1;
        int x, y;
            do{
                pol = (PoligonoGr)listaPoligono.get(i+1);
                poligono = pol.getLista();
                
                No atual = poligono.getInicio();
                
                while(atual != poligono.getFim() && achou == false){
                    Ponto p = (Ponto)atual.getConteudo();
                    
                    x = (int)p.getX();
                    y = (int)p.getY();
                    
                    //RetaGr r = new RetaGr(x, y);
                    if((x >= xMouse-20 || x <= xMouse+20) && ((y >= yMouse-20 || y <= yMouse+20))){
                        achou = true;
                    }
                    
                    atual = atual.getProximo();
                }
                
                if(achou == false){
                    i++;
                }
                
            } while(achou == false && i < listaPoligono.size());   
        
            if(achou == true){
                i = listaPoligono.indexOf(pol);
            }
            else {
                i = -1;
            }
        
        return i;
    }    
    
    /**
     * Redesenha os poligonos armazenados na lista
     * 
     * @param g - classe gráfica
     */
    
    public static void redesenharPoligonos(Graphics g){
          if(listaPoligono.size() > 0){
            for(int i = 0; i < listaPoligono.size(); i++){
                PoligonoGr p = (PoligonoGr)listaPoligono.get(i);
                p.desenharPoligono(g, p.listaVertices, p.cor, p.esp);
            }
        } 
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
    public static void transladarPoligono(Graphics g, int i, int x1, int y1, int x2, int y2, Color c){
        Poligono pol = (PoligonoGr)listaPoligono.get(i);
        ListaDuplamenteLigadaCircular vertices = pol.getLista();
        Color cor = pol.getCor();
        
        pol.setCor(c);
        pol.desenharPoligono(g, vertices, pol.getCor(), pol.getEsp());
        
        No atual = vertices.getInicio();
        
        do{
            Ponto p = (Ponto)atual.getConteudo();
            
            p.setX(p.getX() + (x2-x1));
            p.setY(p.getY() + (y2-y1));          
        
            atual = atual.getProximo();
        }while(atual != vertices.getInicio());
        
        pol.setCor(cor);
        pol.desenharPoligono(g, vertices, pol.getCor(), pol.getEsp());
    }
}
