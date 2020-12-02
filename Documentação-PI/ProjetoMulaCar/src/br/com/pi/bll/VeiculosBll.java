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
    public void addVeiculos(Veiculos veiculo) throws Exception {

        try {
            veiculosDal.addVeiculos(veiculo);
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //

    //--- UPDATE -------------------------------------------------------------------------------------->
    //
    public void updateVeiculos(Veiculos veiculo) throws Exception {
        try {
            veiculosDal.updateVeiculos(veiculo);
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //

    //--- DELETE -------------------------------------------------------------------------------------->
    //
    public void deleteVeiculos(Veiculos veiculo) throws Exception {

        try {
            veiculosDal.deleteVeiculos(veiculo.getIden());
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //

    //--- READ ---------------------------------------------------------------------------------------->
    //
    public ArrayList<Veiculos> getAllVeiculos() throws Exception {

        try {
            return veiculosDal.getAllVeiculos();
        } catch (Exception error) {
            throw error;
        }
    }

    public Veiculos getVeiculosById(int vei_iden) throws Exception {

        try {
            return veiculosDal.getVeiculosById(vei_iden);
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //
}
