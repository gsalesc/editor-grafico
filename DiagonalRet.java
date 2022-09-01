
/**
 * Classe de armazenamento das diagonais de retângulos
 * 
 * @author Gabriel Sales 
 * @version 
 */
public class DiagonalRet
{
    Ponto p1, p2;
    int esp;
    
    DiagonalRet(Ponto p1, Ponto p2, int esp){
        setP1(p1);
        setP2(p2);
        setEsp(esp);
    }
    
    public void setP1(Ponto p){
        this.p1 = p;
    }
    
    public Ponto getP1(){
        return  this.p1;
    }
    
    public void setP2(Ponto p){
        this.p2 = p;
    }
    
    public Ponto getP2(){
        return  this.p2;
    }
    
    public void setEsp(int e){
        this.esp = e;
    }
    
    public int getEsp(){
        return  this.esp;
    }
}
