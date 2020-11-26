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
import br.com.pi.bll.PessoasFisicasBll;
import br.com.pi.model.Clientes;
import br.com.pi.model.PessoaFisica;
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
    private PessoasFisicasBll pessoaFisicaBll;
    private PessoaFisica pessoaFisica;
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
    public void addPessoasFisicas (PessoaFisica pessoaFisica) throws Exception {
       
        try{
        String sqlCliente ="INSERT INTO clientes (cli_nome, cli_telefone, cli_email, cli_end_iden) values (?, ?, ?, ?)";
        PreparedStatement preparedStatement1 = conexao.prepareStatement(sqlCliente, Statement.RETURN_GENERATED_KEYS);
        
        preparedStatement1.setString(1, pessoaFisica.getNome());
        preparedStatement1.setDouble(2, pessoaFisica.getTelefone());
        preparedStatement1.setString(3, pessoaFisica.getEmail());
        preparedStatement1.setInt(4, pessoaFisica.getEnderecos().getIden());
        preparedStatement1.executeUpdate();
        
        try (ResultSet generatedKeys = preparedStatement1.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                pessoaFisica.setCliente  (clienteBll.getClienteById((generatedKeys.getInt(1))) );
            }
            else {
                throw new Exception("Erro ao criar motorista cliente!");
            }
        }
        
        String sqlPessoaFisica = "INSERT INTO pessoas_fisicas (pfi_rg, pfi_cpf, pfi_cli_iden) values (?, ?, ?)";        
        PreparedStatement preparedStatement2 = conexao.prepareStatement(sqlPessoaFisica);
        preparedStatement2.setInt(1, pessoaFisica.getRg());
        preparedStatement2.setDouble(2, pessoaFisica.getCpf());
        preparedStatement2.setInt(3, pessoaFisica.getCliente().getIden());
        preparedStatement2.executeUpdate();
        
        } catch (Exception e) {
            throw  e;
        }
    } 
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //
     
    //--- UPDATE -------------------------------------------------------------------------------------->
    public void updatePessoasFisicas (PessoaFisica pessoaFisica) throws Exception {
        
        try{
        String sqlCliente ="UPDATE clientes SET cli_nome=?, cli_telefone=?, cli_email=?, cli_end_iden=?";
        PreparedStatement preparedStatement1 = conexao.prepareStatement(sqlCliente, Statement.RETURN_GENERATED_KEYS);
        
        preparedStatement1.setString(1, pessoaFisica.getNome());
        preparedStatement1.setDouble(2, pessoaFisica.getTelefone());
        preparedStatement1.setString(3, pessoaFisica.getEmail());
        preparedStatement1.setInt(4, pessoaFisica.getEnderecos().getIden());
        preparedStatement1.executeUpdate();
        
        String sqlPessoaFisica = "UPDATE pessoas_fisicas SET pfi_rg=?, pfi_cpf=?, pfi_cli_iden=?";        
        PreparedStatement preparedStatement2 = conexao.prepareStatement(sqlPessoaFisica);
        preparedStatement2.setInt(1, pessoaFisica.getRg());
        preparedStatement2.setDouble(2, pessoaFisica.getCpf());
        preparedStatement2.setInt(3, pessoaFisica.getCliente().getIden());
        preparedStatement2.executeUpdate();
        
        } catch (Exception e) {
            throw  e;
        }
    }
    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //

    //--- DELETE -------------------------------------------------------------------------------------->
    public void deletePessoasFisicas (PessoaFisica pessoaFisica) throws Exception {
        
        try{
        int idPessoaFisica = pessoaFisica.getIden();
        int idCliente = pessoaFisica.getCliente().getIden();
        
        PreparedStatement preparedStatement1 = conexao.prepareStatement("DELETE FROM clientes where cli_iden =?");
        preparedStatement1.setInt(1, idCliente);
        preparedStatement1.executeUpdate();
        
        PreparedStatement preparedStatement2 = conexao.prepareStatement("DELETE FROM pessoas_fisicas where pfi_iden =?");
        preparedStatement1.setInt(1, idPessoaFisica);
        preparedStatement2.executeUpdate();
        } catch (Exception e) {
            throw  e;
        }
    }  
    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //
    
    //--- READ ---------------------------------------------------------------------------------------->
    public ArrayList<PessoaFisica> getAllPessoasFisicas() throws Exception {
        
        try{
         ArrayList<PessoaFisica> lista = new ArrayList<PessoaFisica>();
         String sql = "SELECT * FROM pessoas_fisicas";
         Statement statement = conexao.createStatement();
         ResultSet rs = statement.executeQuery(sql);
         
           if(rs.next()){
                int cli_id = rs.getInt("pfi_cli_iden");
                cliente = clienteBll.getClienteById(cli_id);
                pessoaFisica.setIden(rs.getInt("pfi_iden"));
                pessoaFisica.setRg(rs.getInt("pfi_rg"));
                pessoaFisica.setCpf(rs.getLong("pfi_cpf"));
                pessoaFisica.setCliente(cliente);
                lista.add(pessoaFisica);
           }
                 
    return lista;
        } catch (Exception e) {
            throw  e;
        }      
    }

    public PessoaFisica getPessoasFisicasById(int pessoaFisica_iden) throws Exception {
        
        try{
        PessoaFisica pessoaFisica = new PessoaFisica();
        String sql = "SELECT * FROM pessoas_fisicas WHERE pfi_iden=?";
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
        } catch (Exception e) {
            throw  e;
        }
    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //
}
