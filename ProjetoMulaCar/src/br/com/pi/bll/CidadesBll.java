/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.bll;

import br.com.pi.dal.CidadesDal;
import br.com.pi.model.Cidades;
import br.com.pi.util.Valida;
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

        ArrayList<Cidades> lista = getAllCidades();

        for (Cidades cidadeObj : lista) {

            if (cidadeObj.getNome().equals(cidade.getNome())) {

                if (cidadeObj.getUf().getNome().equals(cidade.getUf().getNome())) {
                    throw new Exception("Existe uma Cidade com o mesmo UF cadastrado");

                }
            }

        }

        if (cidade.getNome().length() < 2) {
            throw new Exception("Nome da Cidade inválida\nNo mínimo 2 caracteres!");
        }

        if (cidade.getNome().length() > 50) {
            throw new Exception("Nome da Cidade inválida\nMáximo de caracteres excedido!");
        }
        
        
        if (cidade.getUf().getNome().equals("")) {
             throw new Exception("Selecione uma UF!");
        }
        
        
        Valida.campoVazio(cidade.getNome(), "Campo Cidade deve ser preenchido!");
        Valida.notNumber(cidade.getNome(), "");
        Valida.notSpecialCharacters(cidade.getNome(), "");
        
        try {
            cidadeDal.addCidades(cidade);
        } catch (Exception error) {
            throw error;
        }

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
