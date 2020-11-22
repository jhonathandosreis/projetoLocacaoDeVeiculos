/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 22/11/2020 16:26:23 
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

import java.util.ArrayList;

/**
 *
 * @author jhonlinux
 */
public class Motoristas extends Clientes {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private int iden =0;
    private double numeroCnh = 0;
    private ArrayList<Fotos> fotos = new ArrayList<>();
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public Motoristas() {
        
    }
    
    public Motoristas(int iden, String nome, double telefone, String email, Enderecos enderecos, double numeroCnh) {
        super(iden, nome, telefone, email, enderecos);
        this.iden = iden;
        this.numeroCnh = numeroCnh;
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- GET ----------------------------------------------------------------------------------------->
    //
    public int getIden() {
        return iden;
    }

    public double getNumeroCnh() {
        return numeroCnh;
    }

    public ArrayList<Fotos> getFotos() {
        return fotos;
    }
    //--- FIM GET -------------------------------------------------------------------------------------|
    //

    //--- SET ----------------------------------------------------------------------------------------->
    //
    public void setIden(int iden) {
        this.iden = iden;
    }

    public void setNumeroCnh(double numeroCnh) {
        this.numeroCnh = numeroCnh;
    }

    public void setFotos(ArrayList<Fotos> fotos) {
        this.fotos = fotos;
    }

    //--- FIM SET -------------------------------------------------------------------------------------|
    //
}
