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
    private ClientesBll clienteBll;
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
        String sqlCliente ="INSERT INTO clientes (cli_telefone, cli_email, cli_end_iden) values (?, ?, ?)";
        PreparedStatement preparedStatement1 = conexao.prepareStatement(sqlCliente, Statement.RETURN_GENERATED_KEYS);
        preparedStatement1.setDouble(1, pessoaJuridica.getTelefone());
        preparedStatement1.setString(2, pessoaJuridica.getEmail());
        preparedStatement1.setInt(3, pessoaJuridica.getEnderecos().getIden());
        preparedStatement1.executeUpdate();
        
        try (ResultSet generatedKeys = preparedStatement1.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                pessoaJuridica.setCliente(clienteBll.getClienteById((generatedKeys.getInt(1))) );
            }
            else {
                throw new Exception("(ERROR DAL) Erro ao criar pessoa juridica cliente!");
            }
        }
        
        String sqlPessoaFisica = "INSERT INTO pessoas_fisicas (pju_cnpj, pju_nome_fantasia, pju_razao_social, pju_cli_iden) values (?, ?, ?, ?)";        
        PreparedStatement preparedStatement2 = conexao.prepareStatement(sqlPessoaFisica);
        preparedStatement2.setDouble(1, pessoaJuridica.getCnpj());
        preparedStatement2.setString(2, pessoaJuridica.getNomeFantasia());
        preparedStatement2.setString(3, pessoaJuridica.getRazaoSocial());
        preparedStatement2.setInt(4, pessoaJuridica.getCliente().getIden());
        preparedStatement2.executeUpdate();
        
        } catch (Exception e) {
            throw  e;
        }
    } 

    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //
    
    //--- UPDATE -------------------------------------------------------------------------------------->
    public void updatePessoasJuridicas (PessoasJuridicas pessoaJuridica) throws Exception {
        
        try{
       String sqlCliente ="UPDATE clientes SET cli_telefone=?, cli_email=?, cli_end_iden=?";
        PreparedStatement preparedStatement1 = conexao.prepareStatement(sqlCliente);
        preparedStatement1.setDouble(1, pessoaJuridica.getTelefone());
        preparedStatement1.setString(2, pessoaJuridica.getEmail());
        preparedStatement1.setInt(3, pessoaJuridica.getEnderecos().getIden());
        preparedStatement1.executeUpdate();
        
        String sqlPessoaFisica = "UPDATE pessoas_fisicas SET pju_cnpj=?, pju_nome_fantasia=?, pju_razao_social=?, pju_cli_iden=?";        
        PreparedStatement preparedStatement2 = conexao.prepareStatement(sqlPessoaFisica);
        preparedStatement2.setDouble(1, pessoaJuridica.getCnpj());
        preparedStatement2.setString(2, pessoaJuridica.getNomeFantasia());
        preparedStatement2.setString(3, pessoaJuridica.getRazaoSocial());
        preparedStatement2.setInt(4, pessoaJuridica.getCliente().getIden());
        preparedStatement2.executeUpdate();
        
        } catch (Exception e) {
            throw  e;
        }

    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //

    //--- DELETE -------------------------------------------------------------------------------------->
    //
    
    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //
    
    //--- READ ---------------------------------------------------------------------------------------->
    //

    //--- FIM READ ------------------------------------------------------------------------------------|
    //
}
}
