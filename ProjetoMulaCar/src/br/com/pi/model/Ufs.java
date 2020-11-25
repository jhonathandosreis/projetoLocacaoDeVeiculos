/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 21/11/2020 04:05:22 
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
public enum Ufs {
    
    
    AC(1, "Acre"),
    AL(2, "Alagoas"),
    AP(3, "Amapá"),
    AM(4, "Amazonas"),
    BA(5, "Bahia"),
    CE(6, "Ceará"),
    DF(7, "Distrito Federal"),
    ES(8, "Espírito Santo"),
    GO(9, "Goiás"),
    MA(10, "Maranhão"),
    MT(11, "Mato Grosso"),
    MS(12, "Mato Grosso do Sul"),
    MG(13, "Minas Gerais"),
    PA(14, "Pará"),
    PB(15, "Paraíba"),
    PR(16, "Paraná"),
    PE(17, "Pernambuco"),
    PI(18, "Piauí"),
    RJ(19, "Rio de Janeiro"),
    RN(20, "Rio Grande do Norte"),
    RS(21, "Rio Grande do Sul"),
    RO(22, "Rondônia"),
    RR(23, "Roraima"),
    SC(24, "Santa Catarina"),
    SP(25, "São Paulo"),
    SE(26, "Sergipe"),
    TO(27, "Tocantins");

    private int valor;
    private String nome;

    Ufs(int valor, String nome) {
        this.valor = valor;
        this.nome = nome;
    }

    public int getValor() {
        return valor;
    }

    public String getNome() {
        return nome;
    }

    public static Ufs valueOf(int valor) {
        Ufs estadoEncontrado = null;
        for (Ufs estado : Ufs.values()) {
            if (estado.valor == valor) {
                estadoEncontrado = estado;
            }
        }
        return estadoEncontrado;
    }
}
