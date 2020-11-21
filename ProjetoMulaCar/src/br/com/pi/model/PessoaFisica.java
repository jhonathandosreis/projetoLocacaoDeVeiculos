/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 21/11/2020 03:31:30 
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

public class PessoaFisica {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private int iden = 0;
    private int rg = 0;
    private double cpf = 0;
    private int numeroCnh = 0;
    private String categoriaCnh = "";
    private DateFormat dataValidade = null;
    private Clientes pfi_cli_iden = null;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public PessoaFisica() {
        
    }
    
    public PessoaFisica(int iden, int rg, double cpf, int numeroCnh, String categoriaCnh, 
            DateFormat dataValidade, Clientes pfi_cli_iden) {
        this.iden = iden;
        this.rg = rg;
        this.cpf = cpf;
        this.numeroCnh = numeroCnh;
        this.categoriaCnh = categoriaCnh;
        this.dataValidade = dataValidade;
        this.pfi_cli_iden = pfi_cli_iden;
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- GET ----------------------------------------------------------------------------------------->
    //
    public int getIden() {
        return iden;
    }

    public int getRg() {
        return rg;
    }

    public double getCpf() {
        return cpf;
    }

    public int getNumeroCnh() {
        return numeroCnh;
    }

    public String getCategoriaCnh() {
        return categoriaCnh;
    }

    public DateFormat getDataValidade() {
        return dataValidade;
    }

    public Clientes getPfi_cli_iden() {
        return pfi_cli_iden;
    }
    //--- FIM GET -------------------------------------------------------------------------------------|
    //

    //--- SET ----------------------------------------------------------------------------------------->
    //
    public void setIden(int iden) {
        this.iden = iden;
    }

    public void setRg(int rg) {
        this.rg = rg;
    }

    public void setCpf(double cpf) {
        this.cpf = cpf;
    }

    public void setNumeroCnh(int numeroCnh) {
        this.numeroCnh = numeroCnh;
    }

    public void setCategoriaCnh(String categoriaCnh) {
        this.categoriaCnh = categoriaCnh;
    }

    public void setDataValidade(DateFormat dataValidade) {
        this.dataValidade = dataValidade;
    }

    public void setPfi_cli_iden(Clientes pfi_cli_iden) {
        this.pfi_cli_iden = pfi_cli_iden;
    }
    //--- FIM SET -------------------------------------------------------------------------------------|
    //
}
