/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.bll;

import br.com.pi.dal.EnderecosDal;
import br.com.pi.model.Enderecos;
import java.util.ArrayList;

/**
 *
 * @author miguelneto
 */
public class EnderecosBll {
    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //

    private EnderecosDal endDal;

    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //
    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public EnderecosBll() throws Exception {
        endDal = new EnderecosDal();
    }

    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //
    public void AddEndereco(Enderecos endereco) throws Exception {

        try {

            if (endereco.getRua().length() < 2) {
                throw new Exception("Rua inválido\nNo mínimo 2 caracteres!");
            }
            if (endereco.getComplemento().length() < 10) {
                throw new Exception("Complemento inválido\nNo mínimo 10 caracteres!");
            }
            if (endereco.getLogradouro().length() < 2) {
                throw new Exception("Logradouro inválido\nNo mínimo 2 caracteres!");
            }
            if (endereco.getNumero() < 0) {
                throw new Exception("Número do Endereço não pode ser menor do que 0 !");
            }

            if (endereco.getCep() == 8) {
                throw new Exception("CEP é permitido apenas 8 caracteres!");
            }

            endDal.addEnderecos(endereco);

        } catch (Exception error) {
            if (error.getMessage().contains("cep_repetido")) {
                throw new RuntimeException("CEP " + endereco.getCep() + " já cadastrado em nosso sistema");
            }
            throw error;
        }

    }
//--- FIM CREATE ----------------------------------------------------------------------------------|
//
//--- DELETE -------------------------------------------------------------------------------------->
//

    public void deleteEndereco(Enderecos endereco) throws Exception {

        try {

            endDal.deleteEnderecos(endereco.getIden());

        } catch (Exception error) {
            throw error;
        }
    }

    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //
    //--- UPDATE -------------------------------------------------------------------------------------->
    //
    public void updateEndereco(Enderecos endereco) throws Exception {

        try {

            if (endereco.getRua().length() < 2) {
                throw new Exception("Rua inválido\nNo mínimo 2 caracteres!");
            }
            if (endereco.getComplemento().length() < 10) {
                throw new Exception("Complemento inválido\nNo mínimo 10 caracteres!");
            }
            if (endereco.getLogradouro().length() < 2) {
                throw new Exception("Logradouro inválido\nNo mínimo 2 caracteres!");
            }
            if (endereco.getNumero() < 0) {
                throw new Exception("Número do Endereço não pode ser menor do que 0 !");
            }

            if (endereco.getCep() == 8) {
                throw new Exception("CEP é permitido apenas 8 caracteres!");
            }

            endDal.updateEnderecos(endereco);

        } catch (Exception error) {
            if (error.getMessage().contains("cep_repetido")) {
                throw new RuntimeException("CEP " + endereco.getCep() + " já cadastrado em nosso sistema");
            }
            throw error;
        }
    }

    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //
    //--- READ ---------------------------------------------------------------------------------------->
    //
    public ArrayList<Enderecos> getConsulta() throws Exception {
        try {

            return endDal.getAllEnderecos();

        } catch (Exception error) {
            throw error;
        }
    }

    public Enderecos getConsultaPorId(int end_iden) throws Exception {
        try {
            return endDal.getEnderecosById(end_iden);
        } catch (Exception error) {
            throw error;
        }
    }

    public Enderecos getConsultaPorCEP(double cep) throws Exception {
        try {
            return endDal.getEnderecosByCEP(cep);
        } catch (Exception error) {
            throw error;
        }
    }

    //--- FIM READ ------------------------------------------------------------------------------------|
    //
}
