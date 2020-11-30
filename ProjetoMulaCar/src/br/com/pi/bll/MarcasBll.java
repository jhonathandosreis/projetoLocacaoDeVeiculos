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

import br.com.pi.dal.MarcasDal;
import br.com.pi.model.Marcas;
import java.util.ArrayList;

/**
 *
 * @author jhonlinux
 */
public class MarcasBll {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private MarcasDal marcasDal;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public MarcasBll() throws Exception {
        marcasDal = new MarcasDal();
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- CREATE -------------------------------------------------------------------------------------->
    //
    public void addMarcas(Marcas marca) throws Exception {
        
        if (marca.getNome().length() < 3) {
            throw new Exception("Nome da categoria inválida\nNo mínimo 3 caracteres!");
        }

        if (marca.getNome().length() > 30) {
            throw new Exception("Nome da categoria inválida\nMáximo de caracteres excedido!");
        }
        
        try {
            marcasDal.addMarcas(marca);
        } catch (Exception error) {
            if (error.getMessage().contains("duplicate key value violates unique constraint")) {
                throw new Exception("Existe uma marca com o mesmo nome cadastrada no banco de dados!");
            }
        }
    }
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //
    
    //--- UPDATE -------------------------------------------------------------------------------------->
    //
    public void updateMarcas(Marcas marca) throws Exception {
        
        if (marca.getNome().length() < 3) {
            throw new Exception("Nome da categoria inválida\nNo mínimo 3 caracteres!");
        }

        if (marca.getNome().length() > 30) {
            throw new Exception("Nome da categoria inválida\nMáximo de caracteres excedido!");
        }
        
        try {
            marcasDal.updateMarcas(marca);
        } catch (Exception error) {
            if (error.getMessage().contains("duplicate key value violates unique constraint")) {
                throw new Exception("Existe uma marca com o mesmo nome cadastrada no banco de dados!");
            }
        }
    }
    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //

    //--- DELETE -------------------------------------------------------------------------------------->
    //
    public void deleteMarcas(Marcas marca) throws Exception {
        
        try {
            marcasDal.deleteMarcas(marca.getIden());
        } catch (Exception error) {
            if (error.getMessage().contains("modelos_mod_mar_iden_fkey")) {
                throw new Exception("Existe um veículo vinculado a esta marca!");
            }
        }
    }
    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //
    
    //--- READ ---------------------------------------------------------------------------------------->
    //
    public ArrayList<Marcas> getAllMarcas() throws Exception {
        
        try {
            return marcasDal.getAllMarcas();
        } catch (Exception error) {
            throw error;
        }
    }
    
    public Marcas getMarcasById(int mar_iden) throws Exception {
        
        try {
            return marcasDal.getMarcasById(mar_iden);
        } catch (Exception error) {
            throw error;
        }
    }
    
    public Marcas getMarcasByNome(String mar_nome) throws Exception {
        
        try {
            return marcasDal.getMarcarByNome(mar_nome);
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //
}
