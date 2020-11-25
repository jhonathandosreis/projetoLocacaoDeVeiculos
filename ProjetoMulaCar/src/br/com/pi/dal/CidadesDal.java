/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dal;

import br.com.pi.model.Cidades;
import br.com.pi.model.Clientes;
import br.com.pi.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author miguelneto
 */
public class CidadesDal {
    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //

    private Connection conexao;

    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //
    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public CidadesDal() throws Exception {
        conexao = Conexao.getConexao();
    }

    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //
    //--- CREATE -------------------------------------------------------------------------------------->
    //
    public void addCidades(Cidades cidade) throws Exception {

        decidir se vai ter uf no banco de dados
        
        String sql = "INSERT INTO cidades(cli_nome , cli_telefone  , cli_email , cli_end_iden ) VALUES(?,?,?,?)";
        try {

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setDouble(2, cliente.getTelefone());
            preparedStatement.setString(3, cliente.getEmail());
            preparedStatement.setInt(4, cliente.getEnderecos().getIden());

            preparedStatement.executeUpdate();
        } catch (Exception error) {
            if (error.getMessage().contains("duplicate key value violates unique constraint")) {
                throw new RuntimeException("Não é possível cadastrar este CLiente!");
            }
        }

    }

    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //
    //--- DELETE -------------------------------------------------------------------------------------->
    //
    public void deleteClientes(int cli_iden) throws Exception {

        String sql = "DELETE FROM clientes WHERE cli_iden =?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, cli_iden);
            preparedStatement.executeUpdate();
        } catch (Exception error) {
            if (error.getMessage().contains("loc_data_prevista_devolucao")) {
                throw new RuntimeException("Não é possível deletar este cliente pois esta vinculado a uma locação!");
            }
        }

    }

    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //
    //--- UPDATE -------------------------------------------------------------------------------------->
    //
    public void updateClientes(Clientes cliente) throws Exception {

        String sql = "UPDATE clientes SET cli_nome=?, cli_telefone=?, cli_email=?, cli_end_iden=? WHERE cli_iden=?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setDouble(2, cliente.getTelefone());
            preparedStatement.setString(3, cliente.getEmail());
            preparedStatement.setInt(4, cliente.getEnderecos().getIden());
            preparedStatement.setInt(5, cliente.getIden());
            preparedStatement.executeUpdate();
        } catch (Exception error) {
            if (error.getMessage().contains("duplicate key value violates unique constraint")) {
                throw new RuntimeException("Não é possível alterar este cliente!");
            }
        }

    }

    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //
    //--- READ ---------------------------------------------------------------------------------------->
    //
    public ArrayList<Clientes> getAllClientes() throws Exception {

        ArrayList<Clientes> lista = new ArrayList<Clientes>();
        String sql = "SELECT * FROM clientes";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Clientes cliente = new Clientes();
                cliente.setIden(rs.getInt("cli_iden"));
                cliente.setNome(rs.getString("cli_nome"));
                cliente.setTelefone(rs.getDouble("cli_telefone"));

                EnderecosDal end = new EnderecosDal();
                cliente.setEnderecos(end.getEnderecosById(rs.getInt("cli_end_iden")));

                lista.add(cliente);
            }
        } catch (Exception error) {
            throw error;
        }
        return lista;
    }

    public Clientes getClientesById(int id) throws Exception {

        Clientes cliente = new Clientes();
        String sql = "SELECT * FROM clientes WHERE cli_iden=?";

        try {

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {

                cliente.setIden(rs.getInt("cli_iden"));
                cliente.setNome(rs.getString("cli_nome"));
                cliente.setTelefone(rs.getDouble("cli_telefone"));

                EnderecosDal end = new EnderecosDal();
                cliente.setEnderecos(end.getEnderecosById(rs.getInt("cli_end_iden")));

            }
        } catch (Exception error) {
            throw error;
        }
        return cliente;
    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //

}
