/*
 *  --------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan dos reis (jhonlinux) <jhonathan.rosa@maximatech.com.br>
 *  Criado em  : 10/10/2020 17:50:31 
 *  Instituição: FACULDADE SENAI FATESG
 *  Curso      : Análise e Desenvolvimento de sistemas - Módulo 3 - 2020/2
 *  Disciplina : APS - Arquitetura e Projeto de Software
 *  Aluno      : Jhonathan dos reis
 *  Projeto    : PROJETO CAMADAS
 *  Exercício  : Cadastro de miniaturas
 *  ----------------------------------------------------------------------------------------------------
 *  Realiza a conexão com o banco de dados.
 *  ---------------------------------------------------------------------------------------------------| 
 */
package br.com.pi.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author JHONATHAN Conexão com o banco
 */
public class Conexao {
    
    private static Connection conexao = null;
    
    public static Connection getConexao() throws  Exception {
        if(conexao == null){
        
            String driver = "org.postgresql.Driver";
            String url = "jdbc:postgresql://18.221.186.62:5432/inova_locacoes";
            String user = "postgres";
            String password = "affseilamano";
                  
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);  
        }
            return conexao;
        
        }
    }
