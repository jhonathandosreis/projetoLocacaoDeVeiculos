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
import java.util.Date;
/**
 *
 * @author jhonlinux
 */
public class Motoristas extends PessoaFisica {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private int iden =0;
    private double numeroCnh = 0;
    private String categoriaCnh = "";
    private Date dataValidade = null;
    private ArrayList<Fotos> fotos = new ArrayList<>();
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public Motoristas() {
        
    }
    
    public Motoristas( double numeroCnh, String categoriaCnh, Date dataValidade, ArrayList<Fotos> fotos ,
        int iden, int rg, double cpf, String nome, double telefone, String email, Enderecos enderecos, Clientes cliente) {
        super(iden, nome, telefone, email, enderecos, cliente, rg, cpf);
        
        this.iden = iden;
        this.numeroCnh = numeroCnh;
        this.categoriaCnh = categoriaCnh;
        this.dataValidade = dataValidade;
        this.fotos = fotos;
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
    
    public String getCategoriaCnh() {
        return categoriaCnh;
    }

    public ArrayList<Fotos> getFotos() {
        return fotos;
    }
    
    public Date getDataValidade() {
        return dataValidade;
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
    
    public void setCategoriaCnh(String categoriacnh) {
        this.categoriaCnh = categoriacnh;
    }

    public void setFotos(ArrayList<Fotos> fotos) {
        this.fotos = fotos;
    }
    
    public void setDataValidade(Date dataValidade) {
         this.dataValidade = dataValidade;
    }
    //--- FIM SET -------------------------------------------------------------------------------------|
    //
    
}
