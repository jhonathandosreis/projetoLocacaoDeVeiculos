/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 23/11/2020 23:03:09 
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

import br.com.pi.dal.TiposDeVeiculosDal;
import br.com.pi.model.TiposDeVeiculos;
import java.util.ArrayList;

/**
 *
 * @author jhonlinux
 */
public class TiposDeVeiculosBll {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private TiposDeVeiculosDal tiposDeVeiculosDal;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public TiposDeVeiculosBll() throws Exception {
        tiposDeVeiculosDal = new TiposDeVeiculosDal();
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
    public void deleteTiposDeVeiculos(TiposDeVeiculos tiposDeVeiculos) throws Exception {

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
