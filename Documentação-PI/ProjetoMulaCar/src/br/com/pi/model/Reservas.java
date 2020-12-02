/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 21/11/2020 04:16:03 
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
public class Reservas {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private int iden = 0;
    private DateFormat dataReserva = null;
    private String status = "";
    private int caucao = 0;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public Reservas() {

    }
    
    public Reservas(int iden, DateFormat dataReserva, String status, int caucao) {
        this.iden = iden;
        this.dataReserva = dataReserva;
        this.status = status;
        this.caucao = caucao;
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- GET ----------------------------------------------------------------------------------------->
    //
    public int getIden() {
        return iden;
    }

    public DateFormat getDataReserva() {
        return dataReserva;
    }

    public String getStatus() {
        return status;
    }

    public int getCaucao() {
        return caucao;
    }
    //--- FIM GET -------------------------------------------------------------------------------------|
    //

    //--- SET ----------------------------------------------------------------------------------------->
    //
    public void setIden(int iden) {
        this.iden = iden;
    }

    public void setDataReserva(DateFormat dataReserva) {
        this.dataReserva = dataReserva;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCaucao(int caucao) {
        this.caucao = caucao;
    }
    //--- FIM SET -------------------------------------------------------------------------------------|
    //
}
