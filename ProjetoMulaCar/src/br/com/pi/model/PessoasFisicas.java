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

/**
 *
 * @author jhonlinux
 */

public class PessoasFisicas {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private int iden = 0;
    private String rg = "";
    private String cpf = "";
    private Clientes cliente = null;
    private Fotos fotos = null;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public PessoasFisicas() {
        
    }

    public PessoasFisicas(int iden, Fotos foto, String rg, String cpf, Clientes cliente) {
       
        this.iden = iden;
        this.rg = rg;
        this.cpf = cpf;
        this.cliente = cliente;
        this.fotos = foto;
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- GET ----------------------------------------------------------------------------------------->
    //
    public int getIden() {
        return iden;
    }

    public Fotos getFotos() {
        return fotos;
    }
    
    public String getRg() {
        return rg;
    }

    public String getCpf() {
        return cpf;
    }

    public Clientes getCliente() {
        return cliente;
    }
    //--- FIM GET -------------------------------------------------------------------------------------|
    //

    //--- SET ----------------------------------------------------------------------------------------->
    //
    public void setIden(int iden) {
        this.iden = iden;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public void setFotos(Fotos fotos) {
        this.fotos = fotos;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }
    
    //--- FIM SET -------------------------------------------------------------------------------------|
    //
}
