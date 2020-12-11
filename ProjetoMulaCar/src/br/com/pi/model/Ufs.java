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
    private String nome = "";

    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //
    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public Ufs() {

    }

    public Ufs(String nome, int iden) {
        this.nome = nome;
        this.iden = iden;

    }

    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //
    //--- GET ----------------------------------------------------------------------------------------->
    //
    public String getNome() {
        return nome;
    }

    public int getIden() {
        return iden;
    }

    //--- FIM GET -------------------------------------------------------------------------------------|
    //
    //--- SET ----------------------------------------------------------------------------------------->
    //
    public void setNome(String nome) {
        this.nome = nome.trim().toUpperCase();
    }

    public void setIden(int iden) {
        this.iden = iden;
    }

    //--- FIM SET -------------------------------------------------------------------------------------|
    //
}
