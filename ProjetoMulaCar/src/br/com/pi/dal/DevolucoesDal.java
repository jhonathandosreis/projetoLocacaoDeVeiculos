/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 23/11/2020 22:53:50 
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

package br.com.pi.dal;

import br.com.pi.model.Devolucoes;
import br.com.pi.util.Conexao;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author Gustavo Gabriel
 */
public class DevolucoesDal {
//--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private Connection conexao;

    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //

    public DevolucoesDal(Connection conexao) throws Exception {
        this.conexao = Conexao.getConexao();
    }
    

    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- CREATE -------------------------------------------------------------------------------------->
    public void addDevolucoes (Devolucoes devolucao)throws Exception {
        
    }

    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //
    
    
    //--- UPDATE -------------------------------------------------------------------------------------->
    public void updateDevolucoes (Devolucoes devolucao)throws Exception {
        
    }
    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //

    //--- DELETE -------------------------------------------------------------------------------------->
    public void deleteDevolucoes (Devolucoes devolucao)throws Exception {
        
    }
    
    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //
    
    //--- READ ---------------------------------------------------------------------------------------->
    // READ ALL
    public ArrayList<Devolucoes> getAllDevolucoes() throws Exception {
        ArrayList<Devolucoes> lista = new ArrayList<Devolucoes>();
        return lista;
    }
    
    // READ BY ID
    public Devolucoes getDevolucoesById(int dev_iden) throws Exception {
        Devolucoes devolucao = new Devolucoes();
        return devolucao;
    }

    //--- FIM READ ------------------------------------------------------------------------------------|
    //

}
