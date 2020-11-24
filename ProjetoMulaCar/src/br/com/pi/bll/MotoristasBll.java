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
        ///KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- CREATE -------------------------------------------------------------------------------------->
    public void addMotoristas(Motoristas motoristas) throws Exception {
        
    }
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //
    
    //--- UPDATE -------------------------------------------------------------------------------------->
    public void updateMotorista(Motoristas motoristas) throws Exception {
        
    }
    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //

    //--- DELETE -------------------------------------------------------------------------------------->
    public void deleteMotoristas(Motoristas motoristas) throws Exception {
        
    }
    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //
    
    //--- READ ---------------------------------------------------------------------------------------->
    public ArrayList<Motoristas> getAllMotoristas() throws Exception {
        
        return motoristaDal.getAllMotoristas();
    }
    
    public Motoristas getMotoristaBy(int mot_iden) throws Exception {
        
        return motoristaDal.getMotoristasById(mot_iden);
    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //   
}
