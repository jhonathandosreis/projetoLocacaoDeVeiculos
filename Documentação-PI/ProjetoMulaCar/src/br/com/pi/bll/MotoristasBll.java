/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 23/11/2020 23:05:49 
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

import br.com.pi.dal.MotoristasDal;
import br.com.pi.model.Motoristas;
import java.util.ArrayList;

/**
 *
 * @author Gustavo Gabriel
 */
public class MotoristasBll {
    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    private MotoristasDal motoristaDal;  
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    public MotoristasBll() throws Exception {
        motoristaDal = new MotoristasDal();
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- CREATE -------------------------------------------------------------------------------------->
    public void addMotoristas(Motoristas motoristas) throws Exception {
        try{
         
         motoristaDal.addMotoristas(motoristas);
        } catch (Exception error) {
            if(error.getMessage().contains("cnh_repetida")) throw new RuntimeException("Número "+motoristas.getNumeroCnh()+" de cnh já cadastrado em nosso sistema");
             if(error.getMessage().contains("cpf_repetido")) throw new RuntimeException("Número "+motoristas.getCpf()+" de cpf já cadastrado em nosso sistema");
             if(error.getMessage().contains("rg_repetido")) throw new RuntimeException("Número "+motoristas.getRg()+" de rg já cadastrado em nosso sistema");
        }
    }
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //
    
    //--- UPDATE -------------------------------------------------------------------------------------->
    public void updateMotorista(Motoristas motoristas) throws Exception {
        try{
         motoristaDal.updateMotoristas(motoristas);
        } catch (Exception error) {
            throw  error;
        }
    }
    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //

    //--- DELETE -------------------------------------------------------------------------------------->
    public void deleteMotoristas(Motoristas motoristas) throws Exception {
        try{
         motoristaDal.deleteMotoristas(motoristas);
        } catch (Exception error) {
            throw  error;
        }
    }
    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //
    
    //--- READ ---------------------------------------------------------------------------------------->
    public ArrayList<Motoristas> getAllMotoristas() throws Exception {
        try{
         return motoristaDal.getAllMotoristas();
        } catch (Exception error) {
            throw  error;
        }
        
    }
    
    public Motoristas getMotoristaBy(int mot_iden) throws Exception {
         try{
         return motoristaDal.getMotoristasById(mot_iden);
        } catch (Exception error) {
            throw  error;
        }
        
    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //   
}
