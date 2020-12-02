/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 25/11/2020 23:51:39 
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

import br.com.pi.dal.PessoasJuridicasDal;
import br.com.pi.model.PessoasJuridicas;
import java.util.ArrayList;

/**
 *
 * @author Gustavo Gabriel
 */
public class PessoasJuridicasBll {
    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    private PessoasJuridicasDal pessoaJuridicaDal;  
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    public PessoasJuridicasBll() throws Exception {
        pessoaJuridicaDal = new PessoasJuridicasDal();
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- CREATE -------------------------------------------------------------------------------------->
    public void addPessoasJuridicas(PessoasJuridicas pessoaJuridica) throws Exception {
        try{
         pessoaJuridicaDal.addPessoasJuridicas(pessoaJuridica);
        } catch (Exception error) {
           if(error.getMessage().contains("cpj_repetido")) throw new RuntimeException("Número "+pessoaJuridica.getCnpj()+" de cnpj já cadastrado em nosso sistema");
             if(error.getMessage().contains("nomefantasia_repetido")) throw new RuntimeException("Nome fantasia "+pessoaJuridica.getNomeFantasia()+" já cadastrado em nosso sistema");
             if(error.getMessage().contains("razaosocial_repetido")) throw new RuntimeException("Razão social "+pessoaJuridica.getRazaoSocial()+" já cadastrada em nosso sistema");
        }
    }
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //
    
    //--- UPDATE -------------------------------------------------------------------------------------->
    public void updatePessoasJuridicas(PessoasJuridicas pessoaJuridica) throws Exception {
        try{
         pessoaJuridicaDal.updatePessoasJuridicas(pessoaJuridica);
        } catch (Exception error) {
            throw  error;
        }
    }
    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //

    //--- DELETE -------------------------------------------------------------------------------------->
    public void deletePessoasJuridicas(PessoasJuridicas pessoaJuridica) throws Exception {
        try{
         pessoaJuridicaDal.deletePessoasJuridicas(pessoaJuridica);
        } catch (Exception error) {
            throw  error;
        }
    }
    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //
    
    //--- READ ---------------------------------------------------------------------------------------->
    public ArrayList<PessoasJuridicas> getAllPessoasJuridicas() throws Exception {
        try{
         return pessoaJuridicaDal.getAllPessoasJuridicas();
        } catch (Exception error) {
            throw  error;
        }
        
    }
    
    public PessoasJuridicas getPessoasJuridicasBy(int pessoaJuridica_iden) throws Exception {
         try{
         return pessoaJuridicaDal.getPessoasJuridicasById(pessoaJuridica_iden);
        } catch (Exception error) {
            throw  error;
        }
        
    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //   
}
