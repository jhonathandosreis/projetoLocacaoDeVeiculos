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

        if (categoria.getNome().length() < 3) {
            throw new Exception("Nome da categoria inválida\nNo mínimo 3 caracteres!");
        }

        if (categoria.getNome().length() > 30) {
            throw new Exception("Nome da categoria inválida\nMáximo de caracteres excedido!");
        }

        if (categoria.getNome().contains("Econômico") && categoria.getValorDiarioLocacao() != 70.00) {
            throw new Exception("O valor não pode ser maior que R$ 70,00 a diaria");
        }

        if (categoria.getNome().contains("Intermediário") && categoria.getValorDiarioLocacao() != 90.00) {
            throw new Exception("O valor não pode ser maior que R$ 90,00 a diaria");
        }

        if (categoria.getNome().contains("SUV") && categoria.getValorDiarioLocacao() != 120.00) {
            throw new Exception("O valor não pode ser maior que R$ 120,00 a diaria");
        }

        if (categoria.getNome().contains("Executivo") && categoria.getValorDiarioLocacao() != 160.00) {
            throw new Exception("O valor não pode ser maior que R$ 160,00 a diaria");
        }

        if (categoria.getNome().contains("Utilitário") && categoria.getValorDiarioLocacao() != 200.00) {
            throw new Exception("O valor não pode ser maior que R$ 200,00 a diaria");
        }

        try {
            categoriasDal.addCategorias(categoria);
        } catch (Exception error) {
            if (error.getMessage().contains("duplicate key value violates unique constraint")) {
                throw new Exception("Existe uma categoria com o mesmo nome cadastrada no banco de dados!");
            }
        }
    }
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //

    //--- UPDATE -------------------------------------------------------------------------------------->
    //
    public void updateCategorias(Categorias categoria) throws Exception {
        
        if (categoria.getNome().length() < 3) {
            throw new Exception("Nome da categoria inválida\nNo mínimo 3 caracteres!");
        }

        if (categoria.getNome().length() > 30) {
            throw new Exception("Nome da categoria inválida\nMáximo de caracteres excedido!");
        }

        if (categoria.getNome().contains("Econômico") && categoria.getValorDiarioLocacao() != 70.00) {
            throw new Exception("O valor não pode ser maior que R$ 70,00 a diaria");
        }

        if (categoria.getNome().contains("Intermediário") && categoria.getValorDiarioLocacao() != 90.00) {
            throw new Exception("O valor não pode ser maior que R$ 90,00 a diaria");
        }

        if (categoria.getNome().contains("SUV") && categoria.getValorDiarioLocacao() != 120.00) {
            throw new Exception("O valor não pode ser maior que R$ 120,00 a diaria");
        }

        if (categoria.getNome().contains("Executivo") && categoria.getValorDiarioLocacao() != 160.00) {
            throw new Exception("O valor não pode ser maior que R$ 160,00 a diaria");
        }

        if (categoria.getNome().contains("Utilitário") && categoria.getValorDiarioLocacao() != 200.00) {
            throw new Exception("O valor não pode ser maior que R$ 200,00 a diaria");
        }
        
        try {
            categoriasDal.updateCategorias(categoria);
        } catch (Exception error) {
            if (error.getMessage().contains("duplicate key value violates unique constraint")) {
                throw new Exception("Existe uma categoria com o mesmo nome cadastrada no banco de dados!");
            }
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
            if (error.getMessage().contains("modelos_mod_cat_iden_fkey")) {
                throw new Exception("Existe um veículo vinculado a esta categoria!");
            }
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
