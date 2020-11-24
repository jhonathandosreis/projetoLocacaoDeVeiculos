/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 23/11/2020 23:43:51 
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

import br.com.pi.dal.LocacoesDal;
import br.com.pi.model.Locacoes;
import java.util.ArrayList;

/**
 *
 * @author Gustavo Gabriel
 */
public class LocacoesBll {
    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    private LocacoesDal locacoesDal;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    public LocacoesBll() throws Exception {
        locacoesDal = new LocacoesDal();
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- CREATE -------------------------------------------------------------------------------------->
    public void addLocacoes(Locacoes locacao) throws Exception {
        
    }
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //
    
    //--- UPDATE -------------------------------------------------------------------------------------->
    public void updateLocacoes(Locacoes locacao) throws Exception {
        
    }
    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //

    //--- DELETE -------------------------------------------------------------------------------------->
    public void deleteLocacoes(Locacoes locacao) throws Exception {
        
    }
    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //
    
    //--- READ ---------------------------------------------------------------------------------------->
    public ArrayList<Locacoes> getAllLocacoes() throws Exception {
        
        return locacoesDal.getAllLocacoes();
    }
    
    public Locacoes getLocacoesBy(int loc_iden) throws Exception {
        
        return locacoesDal.getLocacoesById(loc_iden);
    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //
}
