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

        if (veiculo.getRenavam().length() < 0) {
            throw new Exception("Renavam inválido\nUm renavam possui 11 números!");
        }

        if (veiculo.getRenavam().length() > 11) {
            throw new Exception("Renavam inválido\nMáximo de caracteres excedido!");
        }

        if (veiculo.getAnoFabricacao() < 2012) {
            throw new Exception("Ano invalido\nNossa frota só possui veículos com ano de fabricação superior a 2012!");
        }

        if (veiculo.getAnoFabricacao() > 2020) {
            throw new Exception("Ano invalido\nNossa frota só possui veículos com ano de fabricação até 2020!");
        }

        if (veiculo.getQuilometragem() < 0) {
            throw new Exception("Quilometragem inválida!\nNão existe veículo com KM negativo!");
        }

        if (veiculo.getQuilometragem() > 300000) {
            throw new Exception("Quilometragem inválida!\nNão existe veículo com KM 300000!");
        }

        if (veiculo.getPrecoDeCompra() < 0) {
            throw new Exception("Valor inválido!\nNão existe veículo com valores negativo!");
        }

        if (veiculo.getPrecoDeCompra() > 80000) {
            throw new Exception("Valor inválido!\nNossa frota não possui veículo com valor acima de 80.000!");
        }
        
        if (veiculo.getCapacidadeCombustivel() > 80) {
            throw new Exception("Capacidade inválido!\nNossa frota não possui veículo com capacidade de combustivel acima de 90 litros!");
        }
        
        if (veiculo.getCapacidadeCombustivel() < 50) {
            throw new Exception("Capacidade inválido!\nNossa frota não possui veículo com capacidade de combustivel abaixo de 70 litros!");
        }

        if (veiculo.getCapacidade() < 5) {
            throw new Exception("Capacidade inválida!\nMínimo 5 passageiros!");
        }

        if (veiculo.getCapacidade() > 7) {
            throw new Exception("Capacidade inválida!\nMáximo 7 passageiros!");
        }

        try {
            veiculosDal.addVeiculos(veiculo);
        } catch (Exception error) {
            if (error.getMessage().contains("duplicate key value violates unique constraint")) {
                throw new Exception("Existe um veículo com o mesmo nome cadastrada no banco de dados!");
            }
        }
    }
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //

    //--- UPDATE -------------------------------------------------------------------------------------->
    //
    public void updateVeiculos(Veiculos veiculo) throws Exception {

        if (veiculo.getPlaca().length() < 3) {
            throw new Exception("Placa inválida\nEx: Mercosul BRA2E19\n Nacional PHL-4508");
        }

        if (veiculo.getPlaca().length() > 7) {
            throw new Exception("Placa inválida\nEx: Mercosul BRA2E19\n Nacional PHL-4508");
        }

        if (veiculo.getRenavam().length() < 0) {
            throw new Exception("Renavam inválido\nUm renavam possui 11 números!");
        }

        if (veiculo.getRenavam().length() > 11) {
            throw new Exception("Renavam inválido\nMáximo de caracteres excedido!");
        }

        if (veiculo.getAnoFabricacao() < 2012) {
            throw new Exception("Ano invalido\nNossa frota só possui veículos com ano de fabricação superior a 2012!");
        }

        if (veiculo.getAnoFabricacao() > 2020) {
            throw new Exception("Ano invalido\nNossa frota só possui veículos com ano de fabricação até 2020!");
        }

        if (veiculo.getQuilometragem() < 0) {
            throw new Exception("Quilometragem inválida!\nNão existe veículo com KM negativo!");
        }

        if (veiculo.getQuilometragem() > 300000) {
            throw new Exception("Quilometragem inválida!\nNão existe veículo com KM 300000 em nossa frota!!");
        }

        if (veiculo.getPrecoDeCompra() < 0) {
            throw new Exception("Valor inválido!\nNão existe veículo com valores negativo!");
        }

        if (veiculo.getPrecoDeCompra() > 80000) {
            throw new Exception("Valor inválido!\nNossa frota não possui veículo com valor acima de 80.000!");
        }

        if (veiculo.getCapacidade() < 5) {
            throw new Exception("Capacidade inválida!\nMínimo 5 passageiros!");
        }

        if (veiculo.getCapacidade()> 7) {
            throw new Exception("Capacidade inválida!\nMáximo 7 passageiros!");
        }

        try {
            veiculosDal.updateVeiculos(veiculo);
        } catch (Exception error) {
            if (error.getMessage().contains("duplicate key value violates unique constraint")) {
                throw new Exception("Existe um veículo com o mesmo nome cadastrada no banco de dados!");
            }
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
            if (error.getMessage().contains("locacao_loc_vei_iden_fkey")) {
                throw new Exception("Existe um veículo vinculado a esta locação!");
            }
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
