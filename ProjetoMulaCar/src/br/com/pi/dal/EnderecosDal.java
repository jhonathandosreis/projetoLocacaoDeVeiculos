/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dal;

import br.com.pi.model.Cidades;
import br.com.pi.model.Enderecos;
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
public class EnderecosDal {
    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //

    private Connection conexao;

    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //
    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public EnderecosDal() throws Exception {
        conexao = Conexao.getConexao();
    }

    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //
    //--- CREATE -------------------------------------------------------------------------------------->
    //
    public void addEnderecos(Enderecos end) throws Exception {

        String sql = "INSERT INTO enderecos (end_rua , end_numero , end_logradouro , end_cep , end_complemento , end_cid_iden ) VALUES(?,?,?,?,?,?)";
        try {

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, end.getRua());
            preparedStatement.setFloat(2, end.getNumero());
            preparedStatement.setString(3, end.getLogradouro());
            preparedStatement.setInt(4, end.getCep());
            preparedStatement.setString(5, end.getComplemento());
            preparedStatement.setInt(6, end.getCidade().getValor());

            preparedStatement.executeUpdate();
        } catch (Exception error) {
            if (error.getMessage().contains("duplicate key value violates unique constraint")) {
                throw new RuntimeException("Não é possível cadastrar este Endereço!");
            }
        }

    }

    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //
    //--- DELETE -------------------------------------------------------------------------------------->
    //
    public void deleteEnderecos(int end_iden) throws Exception {

        String sql = "DELETE FROM enderecos WHERE end_iden =?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, end_iden);
            preparedStatement.executeUpdate();
        } catch (Exception error) {
            if (error.getMessage().contains("cli_end_iden")) {
                throw new RuntimeException("Não é possível deletar este endereço pois esta vinculado a um Cliente!");
            }
        }

    }

    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //
    //--- UPDATE -------------------------------------------------------------------------------------->
    //
    public void updateEnderecos(Enderecos end) throws Exception {

        String sql = "UPDATE enderecos SET end_rua=?, end_numero=?, end_logradouro=?, end_cep=? , end_complemento=? , end_cid_iden=? WHERE end_iden=?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, end.getRua());
            preparedStatement.setFloat(2, end.getNumero());
            preparedStatement.setString(3, end.getLogradouro());
            preparedStatement.setInt(4, end.getCep());
            preparedStatement.setString(5, end.getComplemento());
            preparedStatement.setInt(6, end.getCidade().getValor());
            preparedStatement.setInt(7, end.getIden());

            preparedStatement.executeUpdate();
        } catch (Exception error) {
            if (error.getMessage().contains("duplicate key value violates unique constraint")) {
                throw new RuntimeException("Não é possível alterar este endereço!");
            }
        }

    }

    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //
    //--- READ ---------------------------------------------------------------------------------------->
    //
    public ArrayList<Enderecos> getAllEnderecos() throws Exception {

        ArrayList<Enderecos> lista = new ArrayList<Enderecos>();
        String sql = "SELECT * FROM enderecos";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Enderecos end = new Enderecos();

                end.setIden(rs.getInt("end_iden"));
                end.setRua(rs.getString("end_rua"));
                end.setNumero(rs.getFloat("end_numero"));
                end.setLogradouro(rs.getString("end_logradouro"));
                end.setCep(rs.getInt("end_cep"));
                end.setComplemento(rs.getString("end_complemento"));

                
              
                CidadesDal cid = new CidadesDal();
                end.setCidade(cid.sById(rs.getInt("end_cid_iden")));
                


                lista.add(end);
            }
        } catch (Exception error) {
            throw error;
        }
        return lista;
    }

    public Enderecos getEnderecosById(int id) throws Exception {

        Enderecos end = new Enderecos();
        String sql = "SELECT * FROM enderecos WHERE end_iden=?";

        try {

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {

                end.setIden(rs.getInt("end_iden"));
                end.setRua(rs.getString("end_rua"));
                end.setNumero(rs.getFloat("end_numero"));
                end.setLogradouro(rs.getString("end_logradouro"));
                end.setCep(rs.getInt("end_cep"));
                end.setComplemento(rs.getString("end_complemento"));

                decidir se vai ter uf no banco de dados
                Cidades cid = new Cidades();
                end.setCidade(cid.getNome()

            }
        } catch (Exception error) {
            throw error;
        }
        return end;
    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //

}
