/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 21/11/2020 03:14:09 
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
public class Clientes {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private int iden = 0;
    private String nome = "";
    private double telefone = 0;
    private String email = "";
    private Enderecos enderecos = null;
    private String status = "";
    private float multa = 0;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public Clientes() {
        
    }
    
    public Clientes(int iden, String nome, double telefone, String email, Enderecos enderecos, String status, float multa) {
        
        this.iden = iden;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.enderecos = enderecos;
        this.status = status;
        this.multa = multa;
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- GET ----------------------------------------------------------------------------------------->
    //
    public int getIden() {
        return iden;
    }

    public String getNome() {
        return nome;
    }

    public double getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }
    
    public Enderecos getEnderecos() {
        return enderecos;
    }
    
    public String getStatus() {
        return status;
    }
    
    public float getMulta() {
        return multa;
    }
    //--- FIM GET -------------------------------------------------------------------------------------|
    //
    
    //--- SET ----------------------------------------------------------------------------------------->
    //
    public void setIden(int iden) {
        this.iden = iden;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(double telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setEnderecos(Enderecos enderecos) {
        this.enderecos = enderecos;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setMulta(float multa) {
        this.multa = multa;
    }
    //--- FIM SET -------------------------------------------------------------------------------------|
    //    
}
