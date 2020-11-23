/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 23/11/2020 11:06:39 
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

import br.com.pi.model.Veiculos;
import br.com.pi.util.Conexao;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author jhonlinux
 */
public class VeiculosDal {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private Connection conexao;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public VeiculosDal() throws Exception {
        conexao = Conexao.getConexao();
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- CREATE -------------------------------------------------------------------------------------->
    //
    public void addVeiculos(Veiculos veiculos) throws Exception {
        
    }
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //

    //--- UPDATE -------------------------------------------------------------------------------------->
    //
    public void updateVeiculos(Veiculos veiculos) throws Exception {
        
    }
    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //
    
    //--- DELETE -------------------------------------------------------------------------------------->
    //
    public void deleteVeiculos(int iden) throws Exception {
        
    }
    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //
    
    //--- READ ---------------------------------------------------------------------------------------->
    //
    public ArrayList<Veiculos> getAllVeiculos() throws Exception {
        return null;
    }
    
    public Veiculos getVeiculosById(int iden) throws Exception {
        return null;
    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //
}
