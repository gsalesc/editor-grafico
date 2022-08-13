import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Escreva uma descrição da classe Espessura aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */

public class Espessura extends JFrame implements ChangeListener
{
    private JButton btnConfirmar;
    private JSlider slEspessura;
    GUI g;

    Espessura(){
        this.setSize(400, 150);
        this.setTitle("Espessura");
        this.setLayout(new GridLayout(2,1));
        
        slEspessura = criarJSlider();
        
        btnConfirmar = new JButton("Confirmar");
        
        this.add(slEspessura);
        this.add(btnConfirmar);
    }
    
   Espessura(GUI g){
        this.setSize(400, 150);
        this.setTitle("Espessura");
        this.setLayout(new GridLayout(2,1));
        this.setLocationRelativeTo(null);
        
        slEspessura = criarJSlider();
        
        btnConfirmar = new JButton("Confirmar");
        
        this.add(slEspessura);
        this.add(btnConfirmar);
        this.g = g;
        
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g.espessura = getValorEspessura();
                fechar();
            }
        });
    }
    
    private JSlider criarJSlider(){
        JSlider espessura = new JSlider(0, 100, 5);
        espessura.setMajorTickSpacing(5);
        espessura.setPaintTicks(true);
        espessura.setPaintTrack(true);
        espessura.setPaintLabels(true);
        espessura.setSnapToTicks(true);
        espessura.addChangeListener(this);       
        
        return espessura;
    }
    
    public void fechar(){
        this.setVisible(false);
    }
    public int getValorEspessura(){
        return slEspessura.getValue();
    }
    
    public void stateChanged(ChangeEvent e)
    {
        this.setTitle(String.valueOf(slEspessura.getValue()));
    }
}

