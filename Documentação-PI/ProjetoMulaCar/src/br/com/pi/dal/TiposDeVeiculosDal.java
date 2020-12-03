/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 23/11/2020 22:28:12 
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

import br.com.pi.model.TiposDeVeiculos;
import br.com.pi.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author jhonlinux
 */
public class TiposDeVeiculosDal {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private Connection conexao;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public TiposDeVeiculosDal() throws Exception {
        conexao = Conexao.getConexao();
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- CREATE -------------------------------------------------------------------------------------->
    //
    public void addTiposDeVeiculos(TiposDeVeiculos tiposDeVeiculo) throws Exception {

        String sql = "INSERT INTO tipos_de_veiculos (tve_nome) VALUES (?)";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, tiposDeVeiculo.getNome());
            preparedStatement.executeUpdate();
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //

    //--- UPDATE -------------------------------------------------------------------------------------->
    //
    public void updateTiposDeVeiculos(TiposDeVeiculos tiposDeVeiculo) throws Exception {

        String sql = "UPDATE tipos_de_veiculos SET tve_nome=? WHERE tve_iden=?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, tiposDeVeiculo.getNome());
            preparedStatement.setInt(2, tiposDeVeiculo.getIden());
            preparedStatement.executeUpdate();
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //

    //--- DELETE -------------------------------------------------------------------------------------->
    //
    public void deleteTiposDeVeiculos(int tve_iden) throws Exception {

        String sql = "DELETE FROM tipos_de_veiculos WHERE tve_iden=?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, tve_iden);
            preparedStatement.executeUpdate();
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //

    //--- READ ---------------------------------------------------------------------------------------->
    //
    public ArrayList<TiposDeVeiculos> getAllTiposDeVeiculos() throws Exception {

        ArrayList<TiposDeVeiculos> lista = new ArrayList<TiposDeVeiculos>();

        String sql = "SELECT * FROM tipos_de_veiculos";

        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {

                TiposDeVeiculos tiposDeVeiculos = new TiposDeVeiculos();

                tiposDeVeiculos.setIden(rs.getInt("tve_iden"));
                tiposDeVeiculos.setNome(rs.getString("tve_nome"));
                lista.add(tiposDeVeiculos);
            }
        } catch (Exception error) {
            throw error;
        }
        return lista;
    }

    public TiposDeVeiculos getTiposDeVeiculosById(int tve_iden) throws Exception {

        TiposDeVeiculos tiposDeVeiculos = new TiposDeVeiculos();

        String sql = "SELECT * FROM tipos_de_veiculos WHERE tve_iden=?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, tve_iden);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                tiposDeVeiculos.setIden(rs.getInt("tve_iden"));
                tiposDeVeiculos.setNome(rs.getString("tve_nome"));
            }
        } catch (Exception error) {
            throw error;
        }
        return tiposDeVeiculos;
    }

    public TiposDeVeiculos getTiposDeVeiculosByNome(String tve_nome) throws Exception {

        TiposDeVeiculos tiposDeVeiculos = new TiposDeVeiculos();

        String sql = "SELECT * FROM tipos_de_veiculos WHERE tve_nome=?";

        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setString(1, tve_nome);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            tiposDeVeiculos.setIden(rs.getInt("tve_iden"));
            tiposDeVeiculos.setNome(rs.getString("tve_nome"));
        }
        return tiposDeVeiculos;
    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //
}
