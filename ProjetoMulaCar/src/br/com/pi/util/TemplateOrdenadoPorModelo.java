/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */

package br.com.pi.util;

import br.com.pi.model.Veiculos;

/**
 *
 * @author Gustavo Gabriel
 */
public class TemplateOrdenadoPorModelo extends TemplateOrdenaListaVeiculos{

    @Override
    public boolean ordenarVeiculos(Veiculos veiculo1, Veiculos veiculo2) {
        if(veiculo1.getModelo().getNome().compareToIgnoreCase(veiculo2.getModelo().getNome()) <= 0) return true;
        else return false;
    }

}
