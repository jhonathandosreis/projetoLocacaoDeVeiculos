/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */

package br.com.pi.util;

/**
 *
 * @author Gustavo Gabriel
 */
public class Factory_Ordenacao {

    public static TemplateOrdenaListaVeiculos OrdenarPor(String ordenaPor){
        TemplateOrdenaListaVeiculos obj = null;
        if (ordenaPor.equalsIgnoreCase("CATEGORIA")){
            obj = new TemplateOrdenadoPorCategoria();
        }
        else if (ordenaPor.equalsIgnoreCase("TIPO")){
            obj = new TemplateOrdenadoPorTipoVeiculo();
        }
         else if (ordenaPor.equalsIgnoreCase("MODELO")){
            obj = new TemplateOrdenadoPorModelo();
        }
         else if (ordenaPor.equalsIgnoreCase("MARCA")){
            obj = new TemplateOrdenadoPorMarca();
        }
        return obj;
    }
}
