
/**
 * Escreva uma descrição da classe Retangulo aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class Retangulo
{
    Ponto p1, p2; //pontos das diagonais
    
    Retangulo(Ponto p1, Ponto p2){
        setPontoP1(p1);
        setPontoP2(p2);
    }
    
    public void setPontoP1(Ponto p){
        this.p1 = p;
    }
    
    public Ponto getPontoP1(){
        return this.p1;
    }
    
    public void setPontoP2(Ponto p){
        this.p2 = p;
    }
    
    public Ponto getPontoP2(){
        return this.p2;
    }
}
