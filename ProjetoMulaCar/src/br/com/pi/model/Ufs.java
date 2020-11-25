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
public class Ufs {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private int iden = 0;
    private String sigla = "";

    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //
    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public Ufs() {

    }

    public Ufs(String nome) {
        this.sigla = nome;

    }

    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //
    //--- GET ----------------------------------------------------------------------------------------->
    //
    public int getIden() {
        return iden;
    }

    public String getSigla() {
        return sigla;
    }

    //--- FIM GET -------------------------------------------------------------------------------------|
    //
    //--- SET ----------------------------------------------------------------------------------------->
    //
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public void setIden(int iden) {
        this.iden = iden;
    }

    //--- FIM SET -------------------------------------------------------------------------------------|
    //
}
