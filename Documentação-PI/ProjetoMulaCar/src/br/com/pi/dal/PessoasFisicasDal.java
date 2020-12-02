/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 25/11/2020 21:30:45 
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
import br.com.pi.model.PessoasFisicas;
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
public class PessoasFisicasDal {
//--- ATRIBUTOS ----------------------------------------------------------------------------------->
    private Connection conexao;
    private Clientes cliente = null;
    private ClientesBll clienteBll = new ClientesBll();    
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public PessoasFisicasDal() throws Exception {
        this.conexao = Conexao.getConexao();
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- CREATE -------------------------------------------------------------------------------------->
    public void addPessoasFisicas (PessoasFisicas pessoaFisica) throws Exception {
       
        String sqlPessoaFisica = "INSERT INTO pessoas_fisicas (pfi_rg, pfi_cpf, pfi_cli_iden) values (?, ?, ?)";        
        try{
        PreparedStatement preparedStatement2 = conexao.prepareStatement(sqlPessoaFisica);
        preparedStatement2.setInt(1, pessoaFisica.getRg());
        preparedStatement2.setDouble(2, pessoaFisica.getCpf());
        preparedStatement2.setInt(3, pessoaFisica.getCliente().getIden());
        preparedStatement2.executeUpdate();
        
        } catch (Exception error) {
            throw  error;
        }
    } 
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //
     
    //--- UPDATE -------------------------------------------------------------------------------------->
    public void updatePessoasFisicas (PessoasFisicas pessoaFisica) throws Exception {
        
        String sqlPessoaFisica = "UPDATE pessoas_fisicas SET pfi_rg=?, pfi_cpf=?, pfi_cli_iden=? WHERE pfi_iden=?";        
        try{
        PreparedStatement preparedStatement2 = conexao.prepareStatement(sqlPessoaFisica);
        preparedStatement2.setInt(1, pessoaFisica.getRg());
        preparedStatement2.setDouble(2, pessoaFisica.getCpf());
        preparedStatement2.setInt(3, pessoaFisica.getCliente().getIden());
        preparedStatement2.setInt(4, pessoaFisica.getIden());
        preparedStatement2.executeUpdate();
        
        } catch (Exception error) {
            throw  error;
        }
    }
    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //

    //--- DELETE -------------------------------------------------------------------------------------->
    public void deletePessoasFisicas (PessoasFisicas pessoaFisica) throws Exception {
        
        try{
        int idPessoaFisica = pessoaFisica.getIden();
        int idCliente = pessoaFisica.getCliente().getIden();
        
        PreparedStatement preparedStatement1 = conexao.prepareStatement("DELETE FROM clientes where cli_iden =?");
        preparedStatement1.setInt(1, idCliente);
        preparedStatement1.executeUpdate();
        
        PreparedStatement preparedStatement2 = conexao.prepareStatement("DELETE FROM pessoas_fisicas where pfi_iden =?");
        preparedStatement1.setInt(1, idPessoaFisica);
        preparedStatement2.executeUpdate();
        } catch (Exception error) {
            throw  error;
        }
    }  
    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //
    
    //--- READ ---------------------------------------------------------------------------------------->
    public ArrayList<PessoasFisicas> getAllPessoasFisicas() throws Exception {
        
         String sql = "SELECT * FROM pessoas_fisicas WHERE pfi_numero_cnh is null";
        try{
         ArrayList<PessoasFisicas> lista = new ArrayList<PessoasFisicas>();
         Statement statement = conexao.createStatement();
         ResultSet rs = statement.executeQuery(sql);
         
           while(rs.next()){
                PessoasFisicas pessoaFisica = new PessoasFisicas();
                int cli_id = rs.getInt("pfi_cli_iden");
                cliente = clienteBll.getClienteById(cli_id);
                pessoaFisica.setIden(rs.getInt("pfi_iden"));
                pessoaFisica.setRg(rs.getInt("pfi_rg"));
                pessoaFisica.setCpf(rs.getLong("pfi_cpf"));
                pessoaFisica.setCliente(cliente);
                lista.add(pessoaFisica);
           }
                 
    return lista;
        } catch (Exception error) {
            throw  error;
        }      
    }

    public PessoasFisicas getPessoasFisicasById(int pessoaFisica_iden) throws Exception {
        
        String sql = "SELECT * FROM pessoas_fisicas WHERE pfi_iden=?";
        try{
        PessoasFisicas pessoaFisica = new PessoasFisicas();
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setInt(1, pessoaFisica_iden);
        
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()){
        int cli_id = rs.getInt("pfi_cli_iden");
        cliente = clienteBll.getClienteById(cli_id);
        pessoaFisica.setIden(rs.getInt("pfi_iden"));
        pessoaFisica.setRg(rs.getInt("pfi_rg"));
        pessoaFisica.setCpf(rs.getLong("pfi_cpf"));
        pessoaFisica.setCliente(cliente);
        }       
        return pessoaFisica;
        } catch (Exception error) {
            throw  error;
        }
    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //
}
