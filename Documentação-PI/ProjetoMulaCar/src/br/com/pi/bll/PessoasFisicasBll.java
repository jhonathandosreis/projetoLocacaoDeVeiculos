/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 25/11/2020 21:32:56 
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

import br.com.pi.dal.PessoasFisicasDal;
import br.com.pi.model.PessoasFisicas;
import java.util.ArrayList;

/**
 *
 * @author Gustavo Gabriel
 */
public class PessoasFisicasBll {
    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    private PessoasFisicasDal pessoaFisicaDal;  
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    public PessoasFisicasBll() throws Exception {
        pessoaFisicaDal = new PessoasFisicasDal();
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- CREATE -------------------------------------------------------------------------------------->
    public void addPessoasFisicas(PessoasFisicas pessoaFisica) throws Exception {
        try{
         pessoaFisicaDal.addPessoasFisicas(pessoaFisica);
        } catch (Exception error) {
            throw  error;
        }
    }
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //
    
    //--- UPDATE -------------------------------------------------------------------------------------->
    public void updatePessoasFisicas(PessoasFisicas pessoaFisica) throws Exception {
        try{
         pessoaFisicaDal.updatePessoasFisicas(pessoaFisica);
        } catch (Exception error) {
            throw  error;
        }
    }
    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //

    //--- DELETE -------------------------------------------------------------------------------------->
    public void deletePessoasFisicas(PessoasFisicas pessoaFisica) throws Exception {
        try{
         pessoaFisicaDal.deletePessoasFisicas(pessoaFisica);
        } catch (Exception error) {
            throw  error;
        }
    }
    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //
    
    //--- READ ---------------------------------------------------------------------------------------->
    public ArrayList<PessoasFisicas> getAllPessoasFisicas() throws Exception {
        try{
         return pessoaFisicaDal.getAllPessoasFisicas();
        } catch (Exception error) {
            throw  error;
        }
        
    }
    
    public PessoasFisicas getPessoasFisicasBy(int pessoaFisica_iden) throws Exception {
         try{
         return pessoaFisicaDal.getPessoasFisicasById(pessoaFisica_iden);
        } catch (Exception error) {
            throw  error;
        }
        
    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //   
}
