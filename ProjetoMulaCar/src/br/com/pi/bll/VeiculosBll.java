/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 23/11/2020 22:41:51 
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

import br.com.pi.dal.VeiculosDal;
import br.com.pi.model.Veiculos;
import java.util.ArrayList;

/**
 *
 * @author jhonlinux
 */
public class VeiculosBll {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private VeiculosDal veiculosDal;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public VeiculosBll() throws Exception {
        veiculosDal = new VeiculosDal();
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- CREATE -------------------------------------------------------------------------------------->
    //
    public void AddVeiculos(Veiculos veiculos) throws Exception {
        
    }
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //
    
    //--- UPDATE -------------------------------------------------------------------------------------->
    //
    public void UpdateVeiculos(Veiculos veiculos) throws Exception {
        
    }
    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //

    //--- DELETE -------------------------------------------------------------------------------------->
    //
    public void DeleteVeiculos(Veiculos veiculos) throws Exception {
        
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
