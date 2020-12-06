/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.model;

/**
 *
 * @author miguelneto
 */
public class Cidades {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private int iden = 0;
    private String nome = "";
    private Ufs uf = null;

    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //
    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public Cidades() {

    }

    public Cidades(String nome, Ufs uf , int iden) {
        this.nome = nome;
        this.uf = uf;
        this.iden = iden;

    }

    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //
    //--- GET ----------------------------------------------------------------------------------------->
    //
    public int getIden() {
        return iden;
    }

    public Ufs getUf() {
        return uf;
    }

    public String getNome() {
        return nome;
    }

    //--- FIM GET -------------------------------------------------------------------------------------|
    //
    //--- SET ----------------------------------------------------------------------------------------->
    //
    public void setNome(String nome) {
        this.nome = nome.trim().toUpperCase();
    }

     public void setUf(Ufs uf) {
        this.uf = uf;
    }
   
    public void setIden(int iden) {
        this.iden = iden;
    }

    //--- FIM SET -------------------------------------------------------------------------------------|
    //

}
