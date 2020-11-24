/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 23/11/2020 22:39:07 
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

import br.com.pi.model.Locacoes;
import br.com.pi.model.Motoristas;
import br.com.pi.util.Conexao;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author Gustavo Gabriel
 */
public class LocacoesDal {
    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private Connection conexao;

    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //

    public LocacoesDal(Connection conexao) throws Exception {
        this.conexao = Conexao.getConexao();
    }
    

    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- CREATE -------------------------------------------------------------------------------------->
    public void addLocacoes (Locacoes locacao)throws Exception {
        
    }

    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //
    
    
    //--- UPDATE -------------------------------------------------------------------------------------->
    public void updateLocacoes (Locacoes locacao)throws Exception {
        
    }
    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //

    //--- DELETE -------------------------------------------------------------------------------------->
    public void deleteLocacoes (Locacoes locacao)throws Exception {
        
    }
    
    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //
    
    //--- READ ---------------------------------------------------------------------------------------->
    // READ ALL
    public ArrayList<Locacoes> getAllLocacoes() throws Exception {
        ArrayList<Locacoes> lista = new ArrayList<Locacoes>();
        return lista;
    }
    
    // READ BY ID
    public Locacoes getLocacoesById(int loc_iden) throws Exception {
        Locacoes locacao = new Locacoes();
        return locacao;
    }

    //--- FIM READ ------------------------------------------------------------------------------------|
    //

}
