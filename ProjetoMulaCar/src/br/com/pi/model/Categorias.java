/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 21/11/2020 03:06:46 
 *  Instituição: FACULDADE SENAI FATESG
 *  Curso      : Análise e Desenvolvimento de sistemas - Módulo 3 - 2020/11
 *  Disciplina : Projeto Integrador
 *  Alunos     : Jhonathan dos Reis, Gustavo Gabriel e Miguel Neto
 *  Projeto    : Projeto Locação de Veículos
 *  Exercício  : Mula Car
 *  ---------------------------------------------------------------------------------------------------
 *  Propósito do arquivo: Classe responsavel pela categoria dos veículos
 *  ---------------------------------------------------------------------------------------------------| 
 */
package br.com.pi.model;

/**
 *
 * @author jhonlinux
 */
public class Categorias {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private int iden = 0;
    private String nome = "";
    private float valorDiarioLocacao = 0;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public Categorias() {

    }

    public Categorias(int iden, String nome, float valorDiarioLocacao) {

        this.iden = iden;
        this.nome = nome;
        this.valorDiarioLocacao = valorDiarioLocacao;
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

    public float getValorDiarioLocacao() {
        return valorDiarioLocacao;
    }
    //--- FIM GET -------------------------------------------------------------------------------------|
    //

    //--- SET ----------------------------------------------------------------------------------------->
    //
    public void setIden(int iden) {
        this.iden = iden;
    }

    public void setNome(String nome) {
        this.nome = nome.trim().toUpperCase();
    }

    public void setValorDiarioLocacao(float valorDiarioLocacao) {
        this.valorDiarioLocacao = valorDiarioLocacao;
    }
    //--- FIM SET -------------------------------------------------------------------------------------|
    //
}
