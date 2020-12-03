/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 21/11/2020 05:17:38 
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

public enum SituacaoLocacao {

    ABERTO(1, "Aberto"),
    FECHADA(2, "Fechada"),
    CANCELADA(3, "Cancelada");

    private int valor;
    private String descricao;

    SituacaoLocacao(int valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getValor() {
        return valor;
    }

    public static SituacaoLocacao valueOf(int valor) {
        SituacaoLocacao situacaoLocacaoEncontrada = null;
        for (SituacaoLocacao situacaoLocacao : SituacaoLocacao.values()) {
            if (situacaoLocacao.valor == valor) {
                situacaoLocacaoEncontrada = situacaoLocacao;
            }
        }
        return situacaoLocacaoEncontrada;
    }
}
