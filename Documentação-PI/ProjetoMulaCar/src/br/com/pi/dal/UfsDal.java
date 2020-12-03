/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dal;

import br.com.pi.model.Ufs;
import br.com.pi.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Statement;

/**
 *
 * @author miguelneto
 */
public class UfsDal {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private Connection conexao;

    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //
    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public UfsDal() throws Exception {
        conexao = Conexao.getConexao();
    }

    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //
    //--- CREATE -------------------------------------------------------------------------------------->
    //
    public void addUfs(Ufs uf) throws Exception {

        String sql = "INSERT INTO ufs( uf_nome) VALUES(?)";

        try {

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, uf.getNome());

            preparedStatement.executeUpdate();

        } catch (Exception error) {   
                throw error;      
        }
    }
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //
    //--- DELETE -------------------------------------------------------------------------------------->
    //
    public void deleteUfs(int uf_iden) throws Exception {

        String sql = "DELETE FROM ufs WHERE uf_iden =?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, uf_iden);
            preparedStatement.executeUpdate();
        } catch (Exception error) {
            throw  error;
        }

    }

    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //
    //--- UPDATE -------------------------------------------------------------------------------------->
    //
    public void updateUfs(Ufs uf) throws Exception {

        String sql = "UPDATE ufs SET uf_nome=? WHERE uf_iden=?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, uf.getNome());
            preparedStatement.setInt(2, uf.getIden());
            preparedStatement.executeUpdate();
       } catch (Exception error) {
            throw  error;
        
        }

    }

    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //
    //--- READ ---------------------------------------------------------------------------------------->
    //
    public ArrayList<Ufs> getAllUfs() throws Exception {

        ArrayList<Ufs> lista = new ArrayList<Ufs>();
        String sql = "SELECT * FROM ufs";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Ufs uf = new Ufs();

                uf.setIden(rs.getInt("uf_iden"));
                uf.setNome(rs.getString("uf_nome"));

                lista.add(uf);
            }
        } catch (Exception error) {
            throw error;
        }
        return lista;
    }

    public Ufs getUfsById(int id) throws Exception {

        Ufs uf = new Ufs();
        String sql = "SELECT * FROM ufs WHERE uf_iden=?";

        try {

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {

                uf.setIden(rs.getInt("uf_iden"));
                uf.setNome(rs.getString("uf_nome"));

            }
        } catch (Exception error) {
            throw error;
        }
        return uf;
    }

    public Ufs getUfsByNome(String nome) throws Exception {

        Ufs uf = new Ufs();
        String sql = "SELECT * FROM ufs WHERE uf_nome=?";

        try {

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, nome);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {

                uf.setIden(rs.getInt("uf_iden"));
                uf.setNome(rs.getString("uf_nome"));

            }
        } catch (Exception error) {
            throw error;
        }
        return uf;
    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //

}
