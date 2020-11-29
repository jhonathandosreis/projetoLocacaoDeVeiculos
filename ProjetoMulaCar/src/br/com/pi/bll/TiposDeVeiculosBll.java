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
    public void addTiposDeVeiculos(TiposDeVeiculos tiposDeVeiculo) throws Exception {
        
        if (tiposDeVeiculo.getNome().length() < 3) {
            throw new Exception("Nome do tipo do veículo inválido\nNo mínimo 3 caracteres!");
        }

        if (tiposDeVeiculo.getNome().length() > 30) {
            throw new Exception("Nome tipo do veículo inválido\nMáximo de caracteres excedido!");
        }
        
        try {
            tiposDeVeiculosDal.addTiposDeVeiculos(tiposDeVeiculo);
        } catch (Exception error) {
            if (error.getMessage().contains("duplicate key value violates unique constraint")) {
                throw new Exception("Existe um tipo de veículo com o mesmo nome cadastrado no banco de dados!");
            }
        }
    }
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //

    //--- UPDATE -------------------------------------------------------------------------------------->
    //
    public void updateTiposDeVeiculos(TiposDeVeiculos tiposDeVeiculo) throws Exception {
        
        if (tiposDeVeiculo.getNome().length() < 3) {
            throw new Exception("Nome do tipo do veículo inválido\nNo mínimo 3 caracteres!");
        }

        if (tiposDeVeiculo.getNome().length() > 30) {
            throw new Exception("Nome tipo do veículo inválido\nMáximo de caracteres excedido!");
        }
        
        try {
            tiposDeVeiculosDal.updateTiposDeVeiculos(tiposDeVeiculo);
        } catch (Exception error) {
            if (error.getMessage().contains("duplicate key value violates unique constraint")) {
                throw new Exception("Existe um tipo de veículo com o mesmo nome cadastrado no banco de dados!");
            }
        }
    }
    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //

    //--- DELETE -------------------------------------------------------------------------------------->
    //
    public void deleteTiposDeVeiculos(TiposDeVeiculos tiposDeVeiculo) throws Exception {
        
        try {
            tiposDeVeiculosDal.deleteTiposDeVeiculos(tiposDeVeiculo.getIden());
        } catch (Exception error) {
            if (error.getMessage().contains("modelos_mod_tve_iden_fkey")) {
                throw new Exception("Existe um veículo vinculado a este tipo de veículo!");
            }
        }
    }
    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //

    //--- READ ---------------------------------------------------------------------------------------->
    //
    public ArrayList<TiposDeVeiculos> getAllTiposDeVeiculos() throws Exception {
        
        try {
            return tiposDeVeiculosDal.getAllTiposDeVeiculos();
        } catch (Exception error) {
            throw error;
        }
    }

    public TiposDeVeiculos getTiposDeVeiculosById(int tve_iden) throws Exception {
        
        try {
            return tiposDeVeiculosDal.getTiposDeVeiculosById(tve_iden);
        } catch (Exception error) {
            throw error;
        }
    }
    
    public TiposDeVeiculos getTiposDeVeiculosByNome(String tve_nome) throws Exception {
        
        try {
            return tiposDeVeiculosDal.getTiposDeVeiculosByNome(tve_nome);
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //
}
