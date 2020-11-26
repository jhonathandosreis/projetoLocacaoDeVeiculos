/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.bll;

import br.com.pi.dal.UfsDal;
import br.com.pi.model.Ufs;
import java.util.ArrayList;

/**
 *
 * @author miguelneto
 */
public class UfsBll {
    //--- ATRIBUTOS ----------------------------------------------------------------------------------->

    private UfsDal ufDal;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    public UfsBll() throws Exception {
        ufDal = new UfsDal();
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    public void addUfs(Ufs uf) throws Exception {

        try {
            ufDal.addUfs(uf);
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //

    //--- UPDATE -------------------------------------------------------------------------------------->
    //
    public void updateUfs(Ufs uf) throws Exception {

        try {
            ufDal.updateUfs(uf);
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //

    //--- DELETE -------------------------------------------------------------------------------------->
    //
    public void deleteUfs(Ufs uf) throws Exception {

        try {
            ufDal.deleteUfs(uf.getIden());
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //

    //--- READ ---------------------------------------------------------------------------------------->
    //
    public ArrayList<Ufs> getAllUfs() throws Exception {

        try {
            return ufDal.getAllUfs();
        } catch (Exception error) {
            throw error;
        }
    }

    public Ufs getUfById(int uf_iden) throws Exception {

        try {
            return ufDal.getUfsById(uf_iden);
        } catch (Exception error) {
            throw error;
        }
    }

    public Ufs getUfsNome(String sigla) throws Exception {
        try {
            return ufDal.getUfsByNome(sigla);
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //    
}
