/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 23/11/2020 23:40:45 
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

import br.com.pi.dal.DevolucoesDal;
import br.com.pi.model.Devolucoes;
import java.util.ArrayList;

/**
 *
 * @author Gustavo Gabriel
 */
public class DevolucoesBll {
    
    
    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
  
    private DevolucoesDal devolucoesDal;
  
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
  
    public DevolucoesBll() throws Exception {
        devolucoesDal = new DevolucoesDal();
    }
  
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- CREATE -------------------------------------------------------------------------------------->
    public void addDevolucoes(Devolucoes devolucao) throws Exception {
        try {

            
            devolucoesDal.addDevolucoes(devolucao);

        } catch (Exception error) {

            throw error;
        }
    }
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //
    
  //--- DELETE -------------------------------------------------------------------------------------->
//

    public void deleteDevolucoes(Devolucoes devolucao) throws Exception {

        try {

            devolucoesDal.deleteDevolucoes(devolucao.getIden());

        } catch (Exception error) {
            throw error;
        }
    }

    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //
    //--- UPDATE -------------------------------------------------------------------------------------->
    //
    public void updateDevolucoes(Devolucoes devolucao) throws Exception {

        try {
            
            devolucoesDal.updateDevolucoes(devolucao);
            
        } catch (Exception error) {
            throw error;
        }
    }

    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //
    //--- READ ---------------------------------------------------------------------------------------->
    //
    public ArrayList<Devolucoes> getAllDevolucao() throws Exception {
        try {

            return devolucoesDal.getAllDevolucoes();

        } catch (Exception error) {
            throw error;
        }
    }

    public Devolucoes getDevolucaoById(int dev_iden) throws Exception {
        try {
            return devolucoesDal.getDevolucoesById(dev_iden);
        } catch (Exception error) {
            throw error;
        }
    }

    //--- FIM READ ------------------------------------------------------------------------------------|
    //
}
