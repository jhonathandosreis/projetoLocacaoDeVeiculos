/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package br.com.pi.util;

import br.com.pi.bll.VeiculosBll;
import br.com.pi.model.Veiculos;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Gustavo Gabriel
 */
public abstract class TemplateOrdenaListaVeiculos {
   
    private VeiculosBll veiculoBll;

    public abstract boolean ordenarVeiculos(Veiculos veiculo1, Veiculos veiculo2);

    public ArrayList<Veiculos> OrdenarListaVeiculos() throws Exception {
        try {
            veiculoBll = new VeiculosBll();
            ArrayList<Veiculos> listaVeiculos = veiculoBll.getAllVeiculos();
            
             for (int i = 0; i < listaVeiculos.size(); i++) {
                for (int j = i; j < listaVeiculos.size(); j++) {
                    
                    if (!ordenarVeiculos(listaVeiculos.get(i),listaVeiculos.get(j))) {    //aplicacao TemplatMethod
                        Veiculos temp = listaVeiculos.get(j);
                        listaVeiculos.set(j, listaVeiculos.get(i));
                        listaVeiculos.set(i, temp);
                    }
                }
            }

            return listaVeiculos;
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
}
