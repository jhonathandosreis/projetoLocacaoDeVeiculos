/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 25/11/2020 22:43:20 
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
import br.com.pi.model.PessoasJuridicas;
import br.com.pi.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Gustavo Gabriel
 */
public class PessoasJuridicasDal {
    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    private Connection conexao;
    private Clientes cliente = null;
    private ClientesBll clienteBll = new ClientesBll();
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    public PessoasJuridicasDal() throws Exception {
        this.conexao = Conexao.getConexao();
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //
    
    //--- CREATE -------------------------------------------------------------------------------------->
     public void addPessoasJuridicas(PessoasJuridicas pessoaJuridica) throws Exception {
       
        try{       
            String sqlPessoaFisica = "INSERT INTO pessoas_juridicas (pju_cnpj, pju_nome_fantasia, pju_razao_social, pju_cli_iden) values (?, ?, ?, ?)";        
            PreparedStatement preparedStatement2 = conexao.prepareStatement(sqlPessoaFisica);
            preparedStatement2.setDouble(1, pessoaJuridica.getCnpj());
            preparedStatement2.setString(2, pessoaJuridica.getNomeFantasia());
            preparedStatement2.setString(3, pessoaJuridica.getRazaoSocial());
            preparedStatement2.setInt(4, pessoaJuridica.getCliente().getIden());
            preparedStatement2.executeUpdate();
        
        } catch (Exception error) {
            throw  error;
        }
    } 

    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //
    
    //--- UPDATE -------------------------------------------------------------------------------------->
    public void updatePessoasJuridicas (PessoasJuridicas pessoaJuridica) throws Exception {
        
        try{
           
        
            String sqlPessoaFisica = "UPDATE pessoas_juridicas SET pju_cnpj=?, pju_nome_fantasia=?, pju_razao_social=?, pju_cli_iden=? WHERE pju_iden=?";        
            PreparedStatement preparedStatement2 = conexao.prepareStatement(sqlPessoaFisica);
            preparedStatement2.setDouble(1, pessoaJuridica.getCnpj());
            preparedStatement2.setString(2, pessoaJuridica.getNomeFantasia());
            preparedStatement2.setString(3, pessoaJuridica.getRazaoSocial());
            preparedStatement2.setInt(4, pessoaJuridica.getCliente().getIden());
            preparedStatement2.setInt(5, pessoaJuridica.getIden());
            preparedStatement2.executeUpdate();
        
        } catch (Exception error) {
            throw  error;
        }
    }

    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //

    //--- DELETE -------------------------------------------------------------------------------------->
    public void deletePessoasJuridicas (PessoasJuridicas pessoaJuridica) throws Exception { 
    try{
            int idPessoaJuridica = pessoaJuridica.getIden();
            int idCliente = pessoaJuridica.getCliente().getIden();
        
            PreparedStatement preparedStatement1 = conexao.prepareStatement("DELETE FROM clientes where cli_iden =?");
            preparedStatement1.setInt(1, idCliente);
            preparedStatement1.executeUpdate();
        
            PreparedStatement preparedStatement2 = conexao.prepareStatement("DELETE FROM pessoas_juridicas where pju_iden =?");
            preparedStatement1.setInt(1, idPessoaJuridica);
            preparedStatement2.executeUpdate();
        } catch (Exception error) {
            throw  error;
        
        } 
    }

    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //
    
    //--- READ ---------------------------------------------------------------------------------------->
    public ArrayList<PessoasJuridicas> getAllPessoasJuridicas() throws Exception {
        
        try{
            ArrayList<PessoasJuridicas> lista = new ArrayList<PessoasJuridicas>();
            String sql = "SELECT * FROM pessoas_juridicas";
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
         
           if(rs.next()){
                PessoasJuridicas pessoaJuridica = new PessoasJuridicas();
                int cli_id = rs.getInt("pju_cli_iden");
                cliente = clienteBll.getClienteById(cli_id);
                pessoaJuridica.setIden(rs.getInt("pju_iden"));
                pessoaJuridica.setCnpj(rs.getDouble("pju_cnpj"));
                pessoaJuridica.setNomeFantasia(rs.getString("pju_nome_fantasia"));
                pessoaJuridica.setRazaoSocial(rs.getString("pju_razao_social"));
                pessoaJuridica.setCliente(cliente);
                lista.add(pessoaJuridica);
           }
                 
    return lista;
        } catch (Exception error) {
            throw  error;
        }      
    }
    
    
    
    public PessoasJuridicas getPessoasJuridicasById(int pessoaJuridica_iden) throws Exception {
        
        try{
            PessoasJuridicas pessoaJuridica = new PessoasJuridicas();
            String sql = "SELECT * FROM pessoas_juridicas WHERE pju_iden=?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, pessoaJuridica_iden);
        
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()){
                int cli_id = rs.getInt("pju_cli_iden");
                cliente = clienteBll.getClienteById(cli_id);
                pessoaJuridica.setIden(rs.getInt("pju_iden"));
                pessoaJuridica.setCnpj(rs.getDouble("pju_cnpj"));
                pessoaJuridica.setNomeFantasia(rs.getString("pju_nome_fantasia"));
                pessoaJuridica.setRazaoSocial(rs.getString("pju_razao_social"));
                pessoaJuridica.setCliente(cliente);
        }       
        return pessoaJuridica;
        } catch (Exception error) {
            throw  error;
        }
    }  
    

    //--- FIM READ ------------------------------------------------------------------------------------|
    //
}

