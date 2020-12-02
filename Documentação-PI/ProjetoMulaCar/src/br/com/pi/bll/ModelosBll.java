/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 23/11/2020 22:59:14 
 *  Instituição: FACULDADE SENAI FATESG
 *  Curso      : Análise e Desenvolvimento de sistemas - Módulo 3 - 2020/11
 *  Disciplina : Projeto Integrador
 *  Alunos     : Jhonathan dos Reis, Gustavo Gabriel e Miguel Neto
 *  Projeto    : Projeto Locação de Veículos
 *  Exercício  : Mula Car
 *  ---------------------------------------------------------------------------------------------------
 *  Propósito do arquivo: 
 *  ---------------------------------------------------------------------------------------------------| 
 */
package br.com.pi.bll;

import br.com.pi.dal.ModelosDal;
import br.com.pi.model.Modelos;
import java.util.ArrayList;

/**
 *
 * @author jhonlinux
 */
public class ModelosBll {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private ModelosDal modelosDal;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public ModelosBll() throws Exception {
        modelosDal = new ModelosDal();
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- CREATE -------------------------------------------------------------------------------------->
    //
    public void addModelos(Modelos modelo) throws Exception {
        
        if (modelo.getNome().length() < 3) {
            throw new Exception("Nome da categoria inválida\nNo mínimo 3 caracteres!");
        }

        if (modelo.getNome().length() > 30) {
            throw new Exception("Nome da categoria inválida\nMáximo de caracteres excedido!");
        }
        
        try {
            modelosDal.addModelos(modelo);
        } catch (Exception error) {  
            if (error.getMessage().contains("duplicate key value violates unique constraint")) {
                throw new Exception("Existe um modelo com o mesmo nome cadastrado no banco de dados!");
            }
        }
    }
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //

    //--- UPDATE -------------------------------------------------------------------------------------->
    //
    public void updateModelos(Modelos modelo) throws Exception {
        
        if (modelo.getNome().length() < 3) {
            throw new Exception("Nome da categoria inválida\nNo mínimo 3 caracteres!");
        }

        if (modelo.getNome().length() > 30) {
            throw new Exception("Nome da categoria inválida\nMáximo de caracteres excedido!");
        }
        
        try {
            modelosDal.updateModelos(modelo);
        } catch (Exception error) {   
            if (error.getMessage().contains("duplicate key value violates unique constraint")) {
                throw new Exception("Existe um modelo com o mesmo nome cadastrado no banco de dados!");
            }
        }
    }
    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //

    //--- DELETE -------------------------------------------------------------------------------------->
    //
    public void deleteModelos(Modelos modelo) throws Exception {

        try {
            modelosDal.deleteModelos(modelo.getIden());
        } catch (Exception error) {
            if (error.getMessage().contains("veiculos_vei_mod_iden_fkey")) {
                throw new Exception("Existe um veículo vinculado a este modelo!");
            }
        }
    }
    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //

    //--- READ ---------------------------------------------------------------------------------------->
    //
    public ArrayList<Modelos> getAllModelos() throws Exception {

        try {
            return modelosDal.getAllModelos();
        } catch (Exception error) {
            throw error;
        }
    }

    public Modelos getModelosById(int mod_iden) throws Exception {
        try {
            return modelosDal.getModelosById(mod_iden);
        } catch (Exception error) {
            throw error;
        }
    }
    
    public Modelos getModeloByNome(String mod_nome) throws Exception {
        
        try {
            return modelosDal.getModelosByNome(mod_nome);
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //
}
