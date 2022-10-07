import java.io.FileWriter;
import java.io.PrintWriter;
import org.json.JSONObject;
import org.json.JSONException;
import java.util.ArrayList;
import ListaDuplamenteLigada.*;
import reta.*;
import circulo.*;

/**
 * Escreva uma descrição da classe Gravar aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class Gravar
{
    String caminho = "teste.json";
    JSONObject json = new JSONObject();
    JSONObject jsonRetas = new JSONObject();
    JSONObject jsonCirculos = new JSONObject();
    
    public void gravarArquivo(){
        try{
            jsonRetas(900, 700);
            //jsonCirculos(900, 700);
            FileWriter fl = new FileWriter(caminho);
            fl.write(json.toString());
            fl.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void jsonRetas(int xSize, int ySize){
        ArrayList listaRetas = RetaGr.salvarRetaCoordNorm(xSize, ySize);
        for(int i = 0; i < listaRetas.size(); i++){            
            ListaDuplamenteLigadaCircular retas = (ListaDuplamenteLigadaCircular)listaRetas.get(i);
            No atual = retas.getInicio();
            RetaGr r = (RetaGr)atual.getConteudo();
            jsonRetas.put("p1x" + i, r.getP1().getX());
            jsonRetas.put("p1y" + i, r.getP1().getY());
            jsonRetas.put("p2x" + i, r.getP2().getX());
            jsonRetas.put("p2y" + i, r.getP2().getY());
        }
        json.put("reta", jsonRetas);
    }
    
    /*public void jsonCirculos(int xSize, int ySize){
        ArrayList listaCirculos = CirculoGr.salvarCirculoCoordNorm(xSize, ySize);
        for(int i = 0; i < listaCirculos.size(); i++){            
            CirculoGr r = (CirculoGr)listaCirculos.get(i);
            jsonCirculos.put("cx" + i, r.getCentroX());
            jsonCirculos.put("cy" + i, r.getCentroY());
            jsonCirculos.put("rx" + i, r.getRaioX());
            jsonCirculos.put("ry" + i, r.getRaioY());
        }
        json.put("circulo", jsonCirculos);
    }*/
}
