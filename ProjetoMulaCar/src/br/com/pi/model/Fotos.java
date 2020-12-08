/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 22/11/2020 16:50:43 
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
public class Fotos {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private int fot_iden = 0;
    private String fot_caminho = "";
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public Fotos() {
        
    }

    public Fotos(int fot_iden, String fot_caminho) {
        this.fot_iden = fot_iden;
        this.fot_caminho = fot_caminho;
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- GET ----------------------------------------------------------------------------------------->
    //
    public int getFot_iden() {
        return fot_iden;
    }

    public String getFot_caminho() {
        return fot_caminho;
    }
    //--- FIM GET -------------------------------------------------------------------------------------|
    //

    //--- SET ----------------------------------------------------------------------------------------->
    //
    public void setFot_iden(int fot_iden) {
        this.fot_iden = fot_iden;
    }

    public void setFot_caminho(String fot_caminho) {
        this.fot_caminho = fot_caminho;
    }
    //--- FIM SET -------------------------------------------------------------------------------------|
    //
}
