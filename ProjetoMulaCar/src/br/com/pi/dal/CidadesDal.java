/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dal;

import br.com.pi.model.Cidades;
import br.com.pi.model.Ufs;
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

        String sql = "INSERT INTO cidades( cid_nome , cid_ufs_iden ) VALUES(?,?)";
        try {

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, cidade.getNome());
            preparedStatement.setInt(2, cidade.getUf().getValor());
            preparedStatement.executeUpdate();

        } catch (Exception error) {
            if (error.getMessage().contains("duplicate key value violates unique constraint")) {
                throw new RuntimeException("Não é possível cadastrar esta Cidade!");
            }
        }

    }

    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //
    //--- DELETE -------------------------------------------------------------------------------------->
    //
    public void deleteCidades(int cid_iden) throws Exception {

        String sql = "DELETE FROM cidades WHERE cid_iden =?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, cid_iden);
            preparedStatement.executeUpdate();
        } catch (Exception error) {
            if (error.getMessage().contains("")) {
                throw new RuntimeException("Não é possível deletar esta cidade pois esta vinculado a uma Uf!");
            }
        }

    }

    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //
    //--- UPDATE -------------------------------------------------------------------------------------->
    //
    public void updateCidades(Cidades cidade) throws Exception {

        String sql = "UPDATE cidades SET cid_nome=?, cid_ufs_iden_=? WHERE cid_iden=?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, cidade.getNome());
            preparedStatement.setInt(2, cidade.getUf().getValor());
            preparedStatement.setInt(3, cidade.getIden());

            preparedStatement.executeUpdate();
        } catch (Exception error) {
            if (error.getMessage().contains("duplicate key value violates unique constraint")) {
                throw new RuntimeException("Não é possível alterar esta Cidade !");
            }
        }

    }

    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //
    //--- READ ---------------------------------------------------------------------------------------->
    //
    public ArrayList<Cidades> getAllCidades() throws Exception {

        ArrayList<Cidades> lista = new ArrayList<Cidades>();
        String sql = "SELECT * FROM cidades";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Cidades cidade = new Cidades();
                cidade.setIden(rs.getInt("cid_iden"));
                cidade.setNome(rs.getString("cid_nome"));

                cidade.setUf((rs.getInt("cid_ufs_iden")));

                lista.add(cidade);
            }
        } catch (Exception error) {
            throw error;
        }
        return lista;
    }

    public Cidades getCidadesById(int id) throws Exception {

        Cidades cidade = new Cidades();
        String sql = "SELECT * FROM cidades WHERE cid_iden=?";

        try {

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {

                cidade.setIden(rs.getInt("cid_iden"));
                cidade.setNome(rs.getString("cid_nome"));
                
                Ufs uf = new Ufs();
                cidade.setUf((rs.getInt("cid_ufs_iden")));

            }
        } catch (Exception error) {
            throw error;
        }
        return cidade;
    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //

}
