/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 23/11/2020 22:49:58 
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

import br.com.pi.dal.CategoriasDal;
import br.com.pi.model.Categorias;
import java.util.ArrayList;

/**
 *
 * @author jhonlinux
 */
public class MarcasBll {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private CategoriasDal categoriasDal;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public MarcasBll() throws Exception {
        categoriasDal = new CategoriasDal();
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- CREATE -------------------------------------------------------------------------------------->
    //
    public void AddMarcas(Categorias categorias) throws Exception {
        
    }
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //
    
    //--- UPDATE -------------------------------------------------------------------------------------->
    //
    public void UpdateMarcas(Categorias categorias) throws Exception {
        
    }
    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //

    //--- DELETE -------------------------------------------------------------------------------------->
    //
    public void DeleteMarcas(Categorias categorias) throws Exception {
        
    }
    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //
    
    //--- READ ---------------------------------------------------------------------------------------->
    //
    public ArrayList<Categorias> getAllMarcas() throws Exception {
        return null;
    }
    
    public Categorias getMarcasById(int iden) throws Exception {
        return null;
    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //
}
