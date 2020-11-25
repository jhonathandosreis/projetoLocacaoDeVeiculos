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
import br.com.pi.bll.EnderecosBll;
import br.com.pi.model.Clientes;
import br.com.pi.model.Enderecos;
import br.com.pi.util.Conexao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author jhonlinux
 */
public class ClientesDal {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    private Connection conexao;
    private ClientesBll clienteBll;
    private Clientes cliente;
    private EnderecosBll enderecoBll = new EnderecosBll();
    private Enderecos endereco = null;

    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    public ClientesDal() throws Exception {
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

                int endereco_id = rs.getInt("cli_end_iden");
                endereco = enderecoBll.getConsultaPorId(endereco_id);
                cliente = new Clientes();
                cliente.setIden(rs.getInt("cli_iden"));
                cliente.setNome(rs.getString("cli_nome"));
                cliente.setTelefone(rs.getDouble("cli_telefone"));
                cliente.setEmail(rs.getString("cli_email"));
                cliente.setEnderecos(endereco);
                lista.add(cliente);
            }
        return lista;
        
    }

    public Clientes getClientesById(int cli_iden) throws Exception {
        
        String sql = "SELECT * FROM clientes";
        

            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {

                int endereco_id = rs.getInt("cli_end_iden");
                endereco = enderecoBll.getConsultaPorId(endereco_id);
                cliente = new Clientes();
                cliente.setIden(rs.getInt("cli_iden"));
                cliente.setNome(rs.getString("cli_nome"));
                cliente.setTelefone(rs.getDouble("cli_telefone"));
                cliente.setEmail(rs.getString("cli_email"));
                cliente.setEnderecos(endereco);
                
            }
        return cliente;
    }

    //--- FIM READ ------------------------------------------------------------------------------------|
    //

}
