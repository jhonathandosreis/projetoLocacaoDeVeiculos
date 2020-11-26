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

            ArrayList<Enderecos> lista = getConsulta();

            for (Enderecos end : lista) {
                if (end.getCep() == end.getCep()){
                    throw new RuntimeException("Cliente já possui um Endereço Cadastrado!");
                }
            }
            endDal.addEnderecos(endereco);

        } catch (Exception error) {

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


            endDal.updateEnderecos(endereco);

        } catch (Exception error) {
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

    //--- FIM READ ------------------------------------------------------------------------------------|
    //
}
