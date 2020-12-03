/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 23/11/2020 23:33:18 
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

import br.com.pi.dal.FotosDal;
import br.com.pi.model.Fotos;
import java.util.ArrayList;

/**
 *
 * @author Gustavo Gabriel
 */
public class FotosBll {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    private FotosDal fotoDal;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    public FotosBll() throws Exception {
        fotoDal = new FotosDal();
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- CREATE -------------------------------------------------------------------------------------->
    public void addFotos(Fotos foto) throws Exception {

        try {
            fotoDal.addFotos(foto);
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //

    //--- UPDATE -------------------------------------------------------------------------------------->
    public void updateFotos(Fotos foto) throws Exception {

        try {
            fotoDal.updateFotos(foto);
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //

    //--- DELETE -------------------------------------------------------------------------------------->
    public void deleteFotos(Fotos foto) throws Exception {

        try {
            fotoDal.deleteFotos(foto);
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //

    //--- READ ---------------------------------------------------------------------------------------->
    public ArrayList<Fotos> getAllFotos() throws Exception {

        try {
            return fotoDal.getAllFotos();
        } catch (Exception error) {
            throw error;
        }
    }

    public Fotos getFotosBy(int fot_iden) throws Exception {
        try {
        return fotoDal.getFotosById(fot_iden);    
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //    
}
