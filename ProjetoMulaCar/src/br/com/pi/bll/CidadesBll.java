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
    //
    public void addCidades(Cidades cidade) throws Exception {

//         if (cidade.getNome().replaceAll(cidade.getUf())   ) {
//            throw new Exception("Existe uma Cidade com o mesmo nome cadastrada");
//        }
        
        if (cidade.getNome().length() < 2) {
            throw new Exception("Nome da Cidade inválida\nNo mínimo 2 caracteres!");
        }

        if (cidade.getNome().length() > 50) {
            throw new Exception("Nome da Cidade inválida\nMáximo de caracteres excedido!");
        }
        try {
            cidadeDal.addCidades(cidade);
        } catch (Exception error) {
                  throw error;
        }
    
//            if (error.getMessage().contains("duplicate key value violates unique constraint")) {
//                throw new Exception("Existe uma Cidade com o mesmo nome cadastrada no banco de dados!");
//            }
        
    }
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //

    //--- UPDATE -------------------------------------------------------------------------------------->
    //
    public void updateCidades(Cidades cidade) throws Exception {

        if (cidade.getNome().length() < 2) {
            throw new Exception("Nome da Cidade inválida\nNo mínimo 2 caracteres!");
        }

        if (cidade.getNome().length() > 50) {
            throw new Exception("Nome da Cidade inválida\nMáximo de caracteres excedido!");
        }
        try {
            cidadeDal.updateCidades(cidade);
        } catch (Exception error) {
                   throw error;
        }
            
//            if (error.getMessage().contains("duplicate key value violates unique constraint")) {
//                throw new Exception("Existe uma Cidade com o mesmo nome cadastrada no banco de dados!");
//            }
//        }
    }
    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //

    //--- DELETE -------------------------------------------------------------------------------------->
    //
    public void deleteCidades(Cidades cidade) throws Exception {

        try {
            cidadeDal.deleteCidades(cidade.getIden());
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //

    //--- READ ---------------------------------------------------------------------------------------->
    //
    public ArrayList<Cidades> getAllCidades() throws Exception {

        try {
            return cidadeDal.getAllCidades();
        } catch (Exception error) {
            throw error;
        }
    }

    public Cidades getCidadesById(int cid_iden) throws Exception {

        try {
            return cidadeDal.getCidadesById(cid_iden);
        } catch (Exception error) {
            throw error;
        }
    }

    public Cidades getCidadeNome(String nome) throws Exception {
        try {
            return cidadeDal.getCidadesByNome(nome);
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //    
}
