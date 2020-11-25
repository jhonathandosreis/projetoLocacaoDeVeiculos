/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.bll;

import br.com.pi.dal.CidadesDal;
import br.com.pi.model.Cidades;
import java.util.ArrayList;

/**
 *
 * @author miguelneto
 */
public class CidadesBll {
    //--- ATRIBUTOS ----------------------------------------------------------------------------------->

    private CidadesDal cidadeDal;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    public CidadesBll() throws Exception {
        cidadeDal = new CidadesDal();
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- CREATE -------------------------------------------------------------------------------------->
    public void addCidades(Cidades cidade) throws Exception {

    }
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //

    //--- READ ---------------------------------------------------------------------------------------->
    public ArrayList<Cidades> getAllCidade() throws Exception {

        return cidadeDal.getAllCidades();
    }

    public Cidades getCidadesBy(int cid_iden) throws Exception {

        return cidadeDal.getCidadesById(cid_iden);
    }

    public Cidades getCidadeNome(String nome) throws Exception {

        return cidadeDal.getCidadesById(nome);

    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //    
}
