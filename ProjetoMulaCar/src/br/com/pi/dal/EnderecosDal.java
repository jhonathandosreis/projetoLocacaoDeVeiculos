/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dal;


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
    public void addEnderecos(Enderecos endereco) throws Exception {

        String sql = "INSERT INTO enderecos (end_rua , end_numero , end_logradouro , end_cep , end_complemento , end_cid_iden ) VALUES(?,?,?,?,?,?)";
        try {

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, endereco.getRua());
            preparedStatement.setFloat(2, endereco.getNumero());
            preparedStatement.setString(3, endereco.getLogradouro());
            preparedStatement.setInt(4, endereco.getCep());
            preparedStatement.setString(5, endereco.getComplemento());
            preparedStatement.setInt(6, endereco.getCidade().getIden());

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
    public void updateEnderecos(Enderecos endereco) throws Exception {

        String sql = "UPDATE enderecos SET end_rua=?, end_numero=?, end_logradouro=?, end_cep=? , end_complemento=? , end_cid_iden=? WHERE end_iden=?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, endereco.getRua());
            preparedStatement.setFloat(2, endereco.getNumero());
            preparedStatement.setString(3, endereco.getLogradouro());
            preparedStatement.setInt(4, endereco.getCep());
            preparedStatement.setString(5, endereco.getComplemento());
            preparedStatement.setInt(6, endereco.getCidade().getIden());
            preparedStatement.setInt(7, endereco.getIden());

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
                Enderecos endereco = new Enderecos();

                endereco.setIden(rs.getInt("end_iden"));
                endereco.setRua(rs.getString("end_rua"));
                endereco.setNumero(rs.getFloat("end_numero"));
                endereco.setLogradouro(rs.getString("end_logradouro"));
                endereco.setCep(rs.getInt("end_cep"));
                endereco.setComplemento(rs.getString("end_complemento"));

                CidadesDal cidade = new CidadesDal();
                endereco.setCidade(cidade.getCidadesById(rs.getInt("end_cid_iden")));

                lista.add(endereco);
            }
        } catch (Exception error) {
            throw error;
        }
        return lista;
    }

    public Enderecos getEnderecosById(int id) throws Exception {

        Enderecos endereco = new Enderecos();
        String sql = "SELECT * FROM enderecos WHERE end_iden=?";

        try {

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {

                endereco.setIden(rs.getInt("end_iden"));
                endereco.setRua(rs.getString("end_rua"));
                endereco.setNumero(rs.getFloat("end_numero"));
                endereco.setLogradouro(rs.getString("end_logradouro"));
                endereco.setCep(rs.getInt("end_cep"));
                endereco.setComplemento(rs.getString("end_complemento"));

                CidadesDal cidade = new CidadesDal();
                endereco.setCidade(cidade.getCidadesById(rs.getInt("end_cid_iden")));

            }
        } catch (Exception error) {
            throw error;
        }
        return endereco;
    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //

}
