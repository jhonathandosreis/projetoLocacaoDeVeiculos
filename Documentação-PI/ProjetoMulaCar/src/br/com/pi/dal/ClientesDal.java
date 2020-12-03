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

import br.com.pi.bll.EnderecosBll;
import br.com.pi.model.Clientes;
import br.com.pi.model.Enderecos;
import br.com.pi.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    public void addClientes(Clientes cliente) throws Exception {
        String sqlCliente ="INSERT INTO clientes (cli_nome, cli_telefone, cli_email, cli_end_iden) values (?, ?, ?, ?)";
        try{    
        PreparedStatement preparedStatement1 = conexao.prepareStatement(sqlCliente);     
        preparedStatement1.setString(1, cliente.getNome());
        preparedStatement1.setDouble(2, cliente.getTelefone());
        preparedStatement1.setString(3, cliente.getEmail());
        preparedStatement1.setInt(4, cliente.getEnderecos().getIden());
        preparedStatement1.executeUpdate();
        
        } catch (Exception error) {
            throw  error;
        }
    }
    
    public void updateClientes(Clientes cliente) throws Exception { 
            String sqlCliente ="UPDATE clientes SET cli_telefone=?, cli_email=?, cli_end_iden=? WHERE cli_iden=?";
        try{    
            PreparedStatement preparedStatement1 = conexao.prepareStatement(sqlCliente);
            preparedStatement1.setDouble(1, cliente.getTelefone());
            preparedStatement1.setString(2, cliente.getEmail());
            preparedStatement1.setInt(3, cliente.getEnderecos().getIden());
            preparedStatement1.setInt(4, cliente.getIden());
            preparedStatement1.executeUpdate();
            } catch (Exception error) {
            throw  error;
        }
    }
    

    //--- READ ---------------------------------------------------------------------------------------->
    public ArrayList<Clientes> getAllClientes() throws Exception {
       
        ArrayList<Clientes> lista = new ArrayList<Clientes>();
        String sql = "SELECT * FROM clientes";
        try{
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Clientes cliente = new Clientes();
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
             } catch (Exception error) {
            throw  error;
        }
        return lista;
        
    }

    public Clientes getClientesById(int cli_iden) throws Exception {
       
        Clientes cliente = new Clientes();
        String sql = "SELECT * FROM clientes WHERE cli_iden=?";
        PreparedStatement preparedStatement1 = conexao.prepareStatement(sql);
        preparedStatement1.setInt(1, cli_iden);
        ResultSet rs = preparedStatement1.executeQuery();
        
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
    
    public Clientes getClientesByTelefone(double cli_telefone) throws Exception {
        Clientes cliente = new Clientes();
        String sql = "SELECT * FROM clientes WHERE cli_telefone=?";
        PreparedStatement preparedStatement1 = conexao.prepareStatement(sql);
        preparedStatement1.setDouble(1, cli_telefone);
        ResultSet rs = preparedStatement1.executeQuery();

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
