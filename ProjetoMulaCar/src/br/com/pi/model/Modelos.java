/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 21/11/2020 04:57:49 
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
public class Modelos {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private int iden = 0;
    private String nome = "";
    private Marcas marcas = null;
    private Categorias categoria = null;
    private TiposDeVeiculos tiposDeVeiculos = null;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public Modelos() {
        
    }
    
    public Modelos(int iden, String nome, Marcas marcas,
            Categorias categoria, TiposDeVeiculos tiposDeVeiculos) {
        this.iden = iden;
        this.nome = nome;
        this.marcas = marcas;
        this.categoria = categoria;
        this.tiposDeVeiculos = tiposDeVeiculos;
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

    public Marcas getMarcas() {
        return marcas;
    }

    public Categorias getCategoria() {
        return categoria;
    }

    public TiposDeVeiculos getTiposDeVeiculos() {
        return tiposDeVeiculos;
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

    public void setMarcas(Marcas marcas) {
        this.marcas = marcas;
    }
    
    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

    public void setTiposDeVeiculos(TiposDeVeiculos tiposDeVeiculos) {
        this.tiposDeVeiculos = tiposDeVeiculos;
    }
    //--- FIM SET -------------------------------------------------------------------------------------|
    //   
}
