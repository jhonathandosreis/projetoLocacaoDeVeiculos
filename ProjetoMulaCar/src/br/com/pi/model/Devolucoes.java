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
    private int multaPorAtraso = 0;
    private int kmNaEntrega = 0;
    private Locacoes locacao = null;
    private Veiculos veiculo = null;

    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //
    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public Devolucoes() {

    }

    public Devolucoes(int iden, Date dataDevolucao,int multaPorAtraso, String status, int kmNaEntrega, Locacoes locacao , Veiculos veiculo) {
        this.iden = iden;
        this.dataDevolucao = dataDevolucao;
        this.multaPorAtraso = multaPorAtraso;
        this.kmNaEntrega = kmNaEntrega;
        this.locacao = locacao;
        this.veiculo = veiculo;
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

    public int getMultaPorAtraso() {
        return multaPorAtraso;
    }

     public Veiculos getVeiculo() {
        return veiculo;
    }

    public int getKmNaEntrega() {
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

     public void setVeiculo(Veiculos veiculo) {
        this.veiculo = veiculo;
    }
     
    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public void setMultaPorAtraso(int multaPorAtraso) {
        this.multaPorAtraso = multaPorAtraso;
    }

    public void setKmNaEntrega(int kmNaEntrega) {
        this.kmNaEntrega = kmNaEntrega;
    }

    public void setLocacao(Locacoes locacao) {
        this.locacao = locacao;
    }
    //--- FIM SET -------------------------------------------------------------------------------------|
    //

 
}
