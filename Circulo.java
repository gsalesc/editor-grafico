
/**
 * Escreva uma descrição da classe Circulo aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class Circulo
{
    private int centroX;
    private int centroY;
    private double raioX;
    private double raioY;
    private int raio;
    
    /**
     * Construtor para objetos da classe Circulo
     */
    public Circulo(int cX, int cY, int raio)
    {
        setCentroX(cX);
        setCentroY(cY);
        setRaio(raio);
    }
    
    public Circulo(int cX, int cY, int rx, int ry)
    {
        setCentroX(cX);
        setCentroY(cY);
        setRaioX(rx);
        setRaioY(ry);
    }

    public void setCentroX(int c){
        this.centroX = c;
    }
    
    public int getCentroX(){
        return this.centroX;
    }
    
    public void setCentroY(int c){
        this.centroY = c;
    }
    
    public int getCentroY(){
        return this.centroY;
    }
    
    public void setRaio(int r){
        this.raio = r;
    }
    
    public int getRaio(){
        return this.raio;
    }
    
    public void setRaioX(int r){
        this.raioX = r;
    }
    
    public double getRaioX(){
        return this.raioX;
    }
    
    public void setRaioY(int r){
        this.raioY = r;
    }
    
    public double getRaioY(){
        return this.raioY;
    }
}
