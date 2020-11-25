/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 23/11/2020 11:05:25 
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

package br.com.pi.dal;

import br.com.pi.bll.ClientesBll;
import br.com.pi.model.Clientes;
import br.com.pi.util.Conexao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author jhonlinux
 */
public class ClienteDal {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    private Connection conexao;
    private ClientesBll clienteBll;
    private Clientes cliente;

    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    public ClienteDal() throws Exception {
        this.conexao = Conexao.getConexao();
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- READ ---------------------------------------------------------------------------------------->
    public ArrayList<Clientes> getAllClientes() throws Exception {
        ArrayList<Clientes> lista = new ArrayList<Clientes>();
        
        String sql = "SELECT * FROM clientes";
        

            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {

                int cup_id = rs.getInt("usu_cup_iden");
                cupon = cupBll.getConsultaId(cup_id);
                Usuarios usuario = new Usuarios();
                usuario.setUsu_iden(rs.getInt("usu_iden"));
                usuario.setUsu_nome(rs.getString("usu_nome"));
                usuario.setUsu_cpf(rs.getString("usu_cpf"));
                usuario.setUsu_email(rs.getString("usu_email"));
                usuario.setUsu_senha(rs.getString("usu_senha"));
                usuario.setUsu_cup_iden(cupon);
                lista.add(usuario);
            }
        return lista;
        
    }

    public Clientes getClientesById(int cli_iden) throws Exception {
        
    }

    //--- FIM READ ------------------------------------------------------------------------------------|
    //

}
