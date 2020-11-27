/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 23/11/2020 22:54:46 
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
public class CategoriasBll {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private CategoriasDal categoriasDal;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public CategoriasBll() throws Exception {
        categoriasDal = new CategoriasDal();
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- CREATE -------------------------------------------------------------------------------------->
    //
    public void addCategorias(Categorias categoria) throws Exception {
        
        try {
            categoriasDal.addCategorias(categoria);
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //
    
    //--- UPDATE -------------------------------------------------------------------------------------->
    //
    public void updateCategorias(Categorias categoria) throws Exception {
        
        try {
            categoriasDal.updateCategorias(categoria);
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //

    //--- DELETE -------------------------------------------------------------------------------------->
    //
    public void deleteCategorias(Categorias categoria) throws Exception {
        
        try {
            categoriasDal.deleteCategorias(categoria.getIden());
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //
    
    //--- READ ---------------------------------------------------------------------------------------->
    //
    public ArrayList<Categorias> getAllCategorias() throws Exception {
        
        try {
            return categoriasDal.getAllCategorias();
        } catch (Exception error) {
            throw error;
        }
    }
    
    public Categorias getCategoriasById(int cat_iden) throws Exception {
        
        try {
            return categoriasDal.getCategoriasById(cat_iden);
        } catch (Exception error) {
            throw error;
        }
    }
    
    public Categorias getCategoriaByNome(String cat_nome) throws Exception {
        
        try {
            return categoriasDal.getCategoriasByNome(cat_nome);
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //
}
