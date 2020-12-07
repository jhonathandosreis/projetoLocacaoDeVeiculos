/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dal;


import br.com.pi.bll.CidadesBll;
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
    private Cidades cidade = null;
    private CidadesBll cidadeBll = new CidadesBll();
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
            preparedStatement.setString(2, endereco.getNumero());
            preparedStatement.setString(3, endereco.getLogradouro());
            preparedStatement.setString(4, endereco.getCep());
            preparedStatement.setString(5, endereco.getComplemento());
            preparedStatement.setInt(6, endereco.getCidade().getIden());

            preparedStatement.executeUpdate();
       } catch (Exception error) {
            throw  error;
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
            throw  error;
        
        }

    }
    
    public void deleteLast() throws Exception {
        int id = 0;
        String sql = "SELECT *\n" +
                     "FROM enderecos\n" +
                     "ORDER by end_iden DESC limit 1";   
        String sql2 = "DELETE FROM enderecos WHERE end_iden =?";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {            
               id = (rs.getInt("end_iden"));
            }

            PreparedStatement preparedStatement = conexao.prepareStatement(sql2);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception error) {
            throw  error;
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
            preparedStatement.setString(2, endereco.getNumero());
            preparedStatement.setString(3, endereco.getLogradouro());
            preparedStatement.setString(4, endereco.getCep());
            preparedStatement.setString(5, endereco.getComplemento());
            preparedStatement.setInt(6, endereco.getCidade().getIden());
            preparedStatement.setInt(7, endereco.getIden());

            preparedStatement.executeUpdate();
       } catch (Exception error) {
            throw  error;
        
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
                endereco.setNumero(rs.getString("end_numero"));
                endereco.setLogradouro(rs.getString("end_logradouro"));
                endereco.setCep(rs.getString("end_cep"));
                endereco.setComplemento(rs.getString("end_complemento"));

                cidade = cidadeBll.getCidadesById(rs.getInt("end_cid_iden"));
                endereco.setCidade(cidade);

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
                endereco.setNumero(rs.getString("end_numero"));
                endereco.setLogradouro(rs.getString("end_logradouro"));
                endereco.setCep(rs.getString("end_cep"));
                endereco.setComplemento(rs.getString("end_complemento"));

                cidade = cidadeBll.getCidadesById(rs.getInt("end_cid_iden"));
                endereco.setCidade(cidade);

            }
        } catch (Exception error) {
            throw error;
        }
        return endereco;
    }
    
     public Enderecos getEnderecosByCEP(String cep) throws Exception {

        Enderecos endereco = new Enderecos();
        String sql = "SELECT * FROM enderecos WHERE end_cep=?";

        try {

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, cep);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {

                endereco.setIden(rs.getInt("end_iden"));
                endereco.setRua(rs.getString("end_rua"));
                endereco.setNumero(rs.getString("end_numero"));
                endereco.setLogradouro(rs.getString("end_logradouro"));
                endereco.setCep(rs.getString("end_cep"));
                endereco.setComplemento(rs.getString("end_complemento"));
                cidade = cidadeBll.getCidadesById(rs.getInt("end_cid_iden"));
                endereco.setCidade(cidade);
            }
        } catch (Exception error) {
            throw error;
        }
        return endereco;
    }
     
    //--- FIM READ ------------------------------------------------------------------------------------|
    //

}
