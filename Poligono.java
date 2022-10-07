package poligono;

import ListaDuplamenteLigada.*;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;
import ponto.*;
import reta.*;
/**
 * Escreva uma descrição da classe Poligono aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class Poligono
{
    ListaDuplamenteLigadaCircular listaVertices;
    Color cor;
    int esp;
    
    public Poligono(ListaDuplamenteLigadaCircular v, Color c, int e){
        setLista(v);
        this.cor = c;
        this.esp = e;
    }
    
    public void setLista(ListaDuplamenteLigadaCircular v){
        this.listaVertices = v;
    }
    
    public ListaDuplamenteLigadaCircular getLista(){
        return this.listaVertices;
    }
    
    public void setCor(Color c){
        this.cor = c;
    }
    
    public Color getCor(){
        return this.cor;
    }
    
    public void setEsp(int e){
        this.esp = e;
    }
    
    public int getEsp(){
        return this.esp;
    }
    
    /**
     * Desenha um poligono 
     * 
     * @param g - classe gráfica
     * @param v - lista de vertices
     * @param c - cor 
     */   
    public void desenharPoligono(Graphics g, ListaDuplamenteLigadaCircular v, Color c, int esp){
        
        No atual = v.getInicio();
        No prox = atual.getProximo();
            
        do{
            Ponto p1 = (Ponto)atual.getConteudo();
            int x1 = (int)p1.getX();
            int y1 = (int)p1.getY();
            
            Ponto p2 = (Ponto)prox.getConteudo();
            int x2 = (int)p2.getX();
            int y2 = (int)p2.getY();
            
            RetaGr reta = new RetaGr(x1, y1, x2, y2, c, esp);
            reta.desenharReta(g);
            
            atual = atual.getProximo();
            prox = atual.getProximo();
        }while(atual != v.getInicio());       
    
    }
}
