/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 23/11/2020 22:28:12 
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

import br.com.pi.model.TiposDeVeiculos;
import br.com.pi.util.Conexao;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author jhonlinux
 */
public class TiposDeVeiculosDal {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private Connection conexao;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public TiposDeVeiculosDal() throws Exception {
        conexao = Conexao.getConexao();
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- CREATE -------------------------------------------------------------------------------------->
    //
    public void addTiposDeVeiculos(TiposDeVeiculos tiposDeVeiculos) throws Exception {
        
    }
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //
    
    //--- UPDATE -------------------------------------------------------------------------------------->
    //
    public void updateTiposDeVeiculos(TiposDeVeiculos tiposDeVeiculos) throws Exception {
        
    }
    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //

    //--- DELETE -------------------------------------------------------------------------------------->
    //
    public void deleteTiposDeVeiculos(int iden) throws Exception {
        
    }
    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //
    
    //--- READ ---------------------------------------------------------------------------------------->
    //
    public ArrayList<TiposDeVeiculos> getAllTiposDeVeiculos() throws Exception {
        return null;
    }
    
    public TiposDeVeiculos getTiposDeVeiculosById(int iden) throws Exception {
        return null;
    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //
}
