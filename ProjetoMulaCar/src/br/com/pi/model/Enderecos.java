/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 21/11/2020 03:58:41 
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
public class Enderecos {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private int iden = 0;
    private String rua = "";
    private String numero = "";
    private String logradouro = "";
    private String cep = "";
    private String complemento = "";
    private Cidades cidade = null;
    
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public Enderecos() {
    
    }
    
    public Enderecos(int iden, String rua, String numero, 
            String logradouro, String cep, String complemento , Cidades cidade) {
        this.iden = iden;
        this.rua = rua;
        this.numero = numero;
        this.logradouro = logradouro;
        this.cep = cep;
        this.complemento = complemento;
        this.cidade = cidade;
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- GET ----------------------------------------------------------------------------------------->
    //
    public int getIden() {
        return iden;
    }

    public String getRua() {
        return rua;
    }

    public String getNumero() {
        return numero;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getCep() {
        return cep;
    }

    public String getComplemento() {
        return complemento;
    }
    
     public Cidades getCidade() {
        return cidade;
    }
 
    

    //--- FIM GET -------------------------------------------------------------------------------------|
    //

    //--- SET ----------------------------------------------------------------------------------------->
    //
    public void setIden(int iden) {
        this.iden = iden;
    }

    public void setRua(String rua) {
        this.rua = rua.trim().toUpperCase();
    }

    public void setNumero(String numero) {
        this.numero = numero.trim();
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro.trim().toUpperCase();
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento.trim().toUpperCase();
    } 
    
      public void setCidade(Cidades cidade) {
        this.cidade = cidade;
    }
  
    //--- FIM SET -------------------------------------------------------------------------------------|
    //

  

    
}
