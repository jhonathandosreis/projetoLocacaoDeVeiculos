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

import java.util.Date;

/**
 *
 * @author jhonlinux
 */
public class Devolucoes {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private int iden = 0;
    private Date dataDevolucao = null;
    private double multaPorAtraso = 0;
    private double kmNaEntrega = 0;
    private Locacoes locacao = null;
 

    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //
    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public Devolucoes() {

    }

    public Devolucoes(int iden, Date dataDevolucao,double multaPorAtraso, String status, double kmNaEntrega, Locacoes locacao) {
        this.iden = iden;
        this.dataDevolucao = dataDevolucao;
        this.multaPorAtraso = multaPorAtraso;
        this.kmNaEntrega = kmNaEntrega;
        this.locacao = locacao;
       
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- GET ----------------------------------------------------------------------------------------->
    //
    public int getIden() {
        return iden;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public double getMultaPorAtraso() {
        return multaPorAtraso;
    }


    public double getKmNaEntrega() {
        return kmNaEntrega;
    }

    public Locacoes getLocacao() {
        return locacao;
    }
    //--- FIM GET -------------------------------------------------------------------------------------|
    //

    //--- SET ----------------------------------------------------------------------------------------->
    //
    public void setIden(int iden) {
        this.iden = iden;
    }

     
    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public void setMultaPorAtraso(double multaPorAtraso) {
        this.multaPorAtraso = multaPorAtraso;
    }

    public void setKmNaEntrega(double kmNaEntrega) {
        this.kmNaEntrega = kmNaEntrega;
    }

    public void setLocacao(Locacoes locacao) {
        this.locacao = locacao;
    }
    //--- FIM SET -------------------------------------------------------------------------------------|
    //

 
}
