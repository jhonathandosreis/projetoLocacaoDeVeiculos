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

import java.util.Date;

/**
 *
 * @author GUSTAVO
 */
public class Motoristas extends PessoasFisicas {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private int iden = 0;
    private String numeroCnh = "";
    private String categoriaCnh = "";
    private Date dataValidade = null;
    private Fotos foto = null;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES --------s------------------------------------------------------------------------>
    //
    public Motoristas() {

    }

    public Motoristas(String numeroCnh, String categoriaCnh, Date dataValidade, int iden, String rg, String cpf, Clientes cliente, Fotos foto) {
        super(iden, foto, rg, cpf, cliente);

        this.iden = iden;
        this.numeroCnh = numeroCnh;
        this.categoriaCnh = categoriaCnh;
        this.dataValidade = dataValidade;
        this.foto = foto;
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- GET ----------------------------------------------------------------------------------------->
    //
    public int getIden() {
        return iden;
    }

    public String getNumeroCnh() {
        return numeroCnh;
    }

    public String getCategoriaCnh() {
        return categoriaCnh;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public Fotos getFotos() {
        return foto;
    }
    //--- FIM GET -------------------------------------------------------------------------------------|
    //

    //--- SET ----------------------------------------------------------------------------------------->
    //
    public void setIden(int iden) {
        this.iden = iden;
    }

    public void setNumeroCnh(String numeroCnh) {
        this.numeroCnh = numeroCnh.trim();
    }

    public void setCategoriaCnh(String categoriacnh) {
        this.categoriaCnh = categoriacnh.trim().toUpperCase();
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    public void setFotos(Fotos fotos) {
        this.foto = fotos;
    }
    //--- FIM SET -------------------------------------------------------------------------------------|
    //

}
