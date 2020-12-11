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
    private double kmNaEntrega = 0;
    private Locacoes locacao = null;
    private double multaPorAtraso = 0;
    private double multaPorLitros = 0;
    private double diferencaLitros = 0;
    private int diferencaDias = 0;
    private double valorTotal = 0;
    private double multaTransito = 0;
    private double danosVeiculo =0;
 

    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //
    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public Devolucoes() {

    }

    public Devolucoes(int iden, Date dataDevolucao,double multaPorAtraso, String status, double kmNaEntrega, Locacoes locacao,
                      double multaLitro, double diferencaLitro , int diferencaDia , double valorfinal, double multaTransito, double danoVeiculo) {
        this.iden = iden;
        this.dataDevolucao = dataDevolucao;
        this.kmNaEntrega = kmNaEntrega;
        this.locacao = locacao;
        this.multaPorAtraso = multaPorAtraso;
        this.multaPorLitros = multaLitro;
        this.diferencaLitros = diferencaLitro;
        this.diferencaDias = diferencaDia;
        this.valorTotal = valorfinal;
        this.multaTransito = multaTransito;
        this.danosVeiculo = danoVeiculo;
       
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

    public double getKmNaEntrega() {
        return kmNaEntrega;
    }

    public Locacoes getLocacao() {
        return locacao;
    }
    public double getMultaPorAtraso() {
        return multaPorAtraso;
    }
    
    public double getMultaPorLitro() {
        return multaPorLitros;
    }
    
    public double getDiferencaLitros() {
        return diferencaLitros;
    }
    
    public int  getDiferencaDias() {
        return diferencaDias;
    }
    
    public double getValorTotal() {
        return valorTotal;
    }
    
    public double getMultaTransito() {
        return multaTransito;
    }
    
    public double getDanoVeiculo() {
        return danosVeiculo;
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


    public void setKmNaEntrega(double kmNaEntrega) {
        this.kmNaEntrega = kmNaEntrega;
    }

    public void setLocacao(Locacoes locacao) {
        this.locacao = locacao;
    }
    
    public void setMultaPorLitro(double multaLitro) {
        this.multaPorLitros = multaLitro;
    }
    
    public void setMultaPorAtraso(double multaAtraso) {
        this.multaPorAtraso = multaAtraso;
    }
    
    public void setDiferencaLitros(double diferencaLitro) {
        this.diferencaLitros = diferencaLitro;
    }
    
    public void setDiferencaDias(int diferencaDias) {
        this.diferencaDias = diferencaDias;
    }
    
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    public void setMultaTransito(double multaTransito) {
        this.multaTransito = multaTransito;
    }
    
    public void setDanosVeiculo(double danosVeiculo) {
        this.danosVeiculo = danosVeiculo;
    }
    
    //--- FIM SET -------------------------------------------------------------------------------------|
    //

 
}
