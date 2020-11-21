/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 21/11/2020 05:06:21 
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

package br.com.pi.model;

import java.text.DateFormat;

/**
 *
 * @author jhonlinux
 */
public class Devolucao {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private int iden = 0;
    private DateFormat dataDevolucao = null;
    private int multaPorAtraso = 0;
    private String status = "";
    private int kmNaEntrega = 0;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public Devolucao() {
        
    }
    
    public Devolucao(int iden, DateFormat dataDevolucao, 
            int multaPorAtraso, String status, int kmNaEntrega) {
        this.iden = iden;
        this.dataDevolucao = dataDevolucao;
        this.multaPorAtraso = multaPorAtraso;
        this.status = status;
        this.kmNaEntrega = kmNaEntrega;
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- GET ----------------------------------------------------------------------------------------->
    //
    public int getIden() {
        return iden;
    }

    public DateFormat getDataDevolucao() {
        return dataDevolucao;
    }

    public int getMultaPorAtraso() {
        return multaPorAtraso;
    }

    public String getStatus() {
        return status;
    }

    public int getKmNaEntrega() {
        return kmNaEntrega;
    }
    //--- FIM GET -------------------------------------------------------------------------------------|
    //

    //--- SET ----------------------------------------------------------------------------------------->
    //
    public void setIden(int iden) {
        this.iden = iden;
    }

    public void setDataDevolucao(DateFormat dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public void setMultaPorAtraso(int multaPorAtraso) {
        this.multaPorAtraso = multaPorAtraso;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setKmNaEntrega(int kmNaEntrega) {
        this.kmNaEntrega = kmNaEntrega;
    }
    //--- FIM SET -------------------------------------------------------------------------------------|
    //
}
