/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 23/11/2020 19:39:24 
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

import br.com.pi.model.Marcas;
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
public class MarcasDal {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private Connection conexao;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public MarcasDal() throws Exception {
        conexao = Conexao.getConexao();
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- CREATE -------------------------------------------------------------------------------------->
    //
    public void addMarcas(Marcas marca) throws Exception {

        String sql = "INSERT INTO marcas (mar_nome) VALUES (?)";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, marca.getNome());
            preparedStatement.executeUpdate();
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //

    //--- UPDATE -------------------------------------------------------------------------------------->
    //
    public void updateMarcas(Marcas marca) throws Exception {
        
        String sql = "UPDATE marcas SET mar_nome=? WHERE mar_iden=?";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, marca.getNome());
            preparedStatement.setInt(2, marca.getIden());
            preparedStatement.executeUpdate();
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //

    //--- DELETE -------------------------------------------------------------------------------------->
    //
    public void deleteMarcas(int mar_iden) throws Exception {
        
        String sql = "DELETE FROM marcas WHERE mar_iden=?";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, mar_iden);
            preparedStatement.executeUpdate();
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //

    //--- READ ---------------------------------------------------------------------------------------->
    //
    public ArrayList<Marcas> getAllMarcas() throws Exception {
        
        ArrayList<Marcas> lista = new ArrayList<Marcas>();
        
        String sql = "SELECT * FROM marcas";
        
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Marcas marca = new Marcas();
                
                marca.setIden(rs.getInt("mar_iden"));
                marca.setNome(rs.getString("mar_nome"));
                lista.add(marca);
            }
        } catch (Exception error) {
            throw error;
        }
        return lista;
    }

    public Marcas getMarcasById(int mar_iden) throws Exception {
        
        Marcas marca = new Marcas();
        
        String sql = "SELECT * FROM marcas WHERE mar_iden=?";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, mar_iden);
            ResultSet rs = preparedStatement.executeQuery(); 
            if (rs.next()) {
                
                marca.setIden(rs.getInt("mar_iden"));
                marca.setNome(rs.getString("mar_nome"));
            }
        } catch (Exception error) {
            throw error;
        }
        return marca;
    }
    
    public Marcas getMarcarByNome(String nome) throws Exception {
        
        Marcas marca = new Marcas();
        
        String sql = "SELECT * FROM marcas WHERE mar_nome=?";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, nome);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                marca.setIden(rs.getInt("mar_iden"));
                marca.setNome(rs.getString("mar_nome"));
            }
        } catch (Exception error) {
            throw error;
        }
        return marca;
    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //
}
