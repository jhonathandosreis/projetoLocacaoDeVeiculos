/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 23/11/2020 15:31:58 
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
import br.com.pi.model.Motoristas;
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
public class MotoristasDal {
  
    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    private Connection conexao;
    private Clientes cliente = null;
    private ClientesBll clienteBll = new ClientesBll();
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public MotoristasDal() throws Exception {
        this.conexao = Conexao.getConexao();
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- CREATE -------------------------------------------------------------------------------------->
    public void addMotoristas (Motoristas motorista) throws Exception {
       
        String sqlPessoaFisica = "INSERT INTO pessoas_fisicas (pfi_rg, pfi_cpf, pfi_numero_cnh, pfi_categoria_cnh, pfi_data_de_validade, pfi_cli_iden) values (?, ?, ?, ?, ?, ?)";        
        try{
        java.sql.Date dataValidade = new java.sql.Date(motorista.getDataValidade().getTime());
        PreparedStatement preparedStatement2 = conexao.prepareStatement(sqlPessoaFisica);
        preparedStatement2.setInt(1, motorista.getRg());
        preparedStatement2.setDouble(2, motorista.getCpf());
        preparedStatement2.setDouble(3, motorista.getNumeroCnh());
        preparedStatement2.setString(4, motorista.getCategoriaCnh());
        preparedStatement2.setDate(5, dataValidade);
        preparedStatement2.setInt(6, motorista.getCliente().getIden());
        preparedStatement2.executeUpdate();
        
        } catch (Exception error) {
            throw  error;
        }
    } 
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //
     
    //--- UPDATE -------------------------------------------------------------------------------------->
    public void updateMotoristas (Motoristas motorista) throws Exception {
        
        String sqlPessoaFisica = "UPDATE pessoas_fisicas SET pfi_rg=?, pfi_cpf=?, pfi_numero_cnh=?, pfi_categoria_cnh=?, pfi_data_de_validade=?, pfi_cli_iden=? WHERE pfi_iden=?";        
        try{
        PreparedStatement preparedStatement2 = conexao.prepareStatement(sqlPessoaFisica);
        java.sql.Date dataValidade = new java.sql.Date(motorista.getDataValidade().getTime());
        preparedStatement2.setInt(1, motorista.getRg());
        preparedStatement2.setDouble(2, motorista.getCpf());
        preparedStatement2.setDouble(3, motorista.getNumeroCnh());
        preparedStatement2.setString(4, motorista.getCategoriaCnh());
        preparedStatement2.setDate(5, dataValidade);
        preparedStatement2.setInt(6, motorista.getCliente().getIden());
        preparedStatement2.setInt(7, motorista.getIden());
        preparedStatement2.executeUpdate();
        
        } catch (Exception error) {
            throw  error;
        }
    }
    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //

    //--- DELETE -------------------------------------------------------------------------------------->
    public void deleteMotoristas (Motoristas motorista) throws Exception {
        
        try{
        int idMotorista = motorista.getIden();
        int idCliente = motorista.getCliente().getIden();
        
        PreparedStatement preparedStatement1 = conexao.prepareStatement("DELETE FROM clientes WHERE cli_iden =?");
        preparedStatement1.setInt(1, idCliente);
        preparedStatement1.executeUpdate();
        
        PreparedStatement preparedStatement2 = conexao.prepareStatement("DELETE FROM pessoas_fisicas WHERE pfi_iden =?");
        preparedStatement1.setInt(1, idMotorista);
        preparedStatement2.executeUpdate();
        } catch (Exception error) {
            throw  error;
        }
    }  
    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //
    
    //--- READ ---------------------------------------------------------------------------------------->
    public ArrayList<Motoristas> getAllMotoristas() throws Exception {
        
         String sql = "SELECT * FROM pessoas_fisicas WHERE pfi_numero_cnh IS NOT null";
        try{
         ArrayList<Motoristas> lista = new ArrayList<Motoristas>();
         Statement statement = conexao.createStatement();
         ResultSet rs = statement.executeQuery(sql);
         
           while(rs.next()){
                Motoristas motorista = new Motoristas();
                int cli_id = rs.getInt("pfi_cli_iden");
                cliente = clienteBll.getClienteById(cli_id);
                motorista.setIden(rs.getInt("pfi_iden"));
                motorista.setRg(rs.getInt("pfi_rg"));
                motorista.setCpf(rs.getLong("pfi_cpf"));
                motorista.setNumeroCnh(rs.getLong("pfi_numero_cnh"));
                motorista.setCategoriaCnh(rs.getString("pfi_categoria_cnh"));
                motorista.setDataValidade(rs.getDate("pfi_data_de_validade"));
                motorista.setCliente(cliente);
                lista.add(motorista);
           }

    return lista;
        } catch (Exception error) {
            throw  error;
        }
        
    }

    public Motoristas getMotoristasById(int mot_iden) throws Exception {
        
        String sql = "SELECT * FROM pessoas_fisicas WHERE pfi_iden=?";
        try{
        Motoristas motorista = new Motoristas();
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setInt(1, mot_iden);
        
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()){
        int cli_id = rs.getInt("pfi_cli_iden");
        cliente = clienteBll.getClienteById(cli_id);
        motorista.setIden(rs.getInt("pfi_iden"));
        motorista.setRg(rs.getInt("pfi_rg"));
        motorista.setCpf(rs.getLong("pfi_cpf"));
        motorista.setNumeroCnh(rs.getLong("pfi_numero_cnh"));
        motorista.setCategoriaCnh(rs.getString("pfi_categoria_cnh"));
        motorista.setDataValidade(rs.getDate("pfi_data_de_validade"));
        motorista.setCliente(cliente);
        }       
        return motorista;
        } catch (Exception error) {
            throw  error;
        }
    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //
}
