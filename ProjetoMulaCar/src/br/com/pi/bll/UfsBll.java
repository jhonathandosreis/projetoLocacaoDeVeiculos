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

    //--- CREATE -------------------------------------------------------------------------------------->
    public void addCidades(Ufs uf) throws Exception {
        
    }
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //
    
    //--- READ ---------------------------------------------------------------------------------------->
    public ArrayList<Ufs> getAllUfs() throws Exception {
        
        return ufDal.getAllUfs();
    }
    
    public Ufs getufsBy(int uf_iden) throws Exception {
        
        return ufDal.getUfsById(uf_iden);
    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //    
}
