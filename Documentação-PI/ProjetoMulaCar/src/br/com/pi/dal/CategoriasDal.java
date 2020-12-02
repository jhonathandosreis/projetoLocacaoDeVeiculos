/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 23/11/2020 22:16:34 
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

import br.com.pi.model.Categorias;
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

public class CategoriasDal {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private Connection conexao;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public CategoriasDal() throws Exception {
        conexao = Conexao.getConexao();
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- CREATE -------------------------------------------------------------------------------------->
    //
    public void addCategorias(Categorias categoria) throws Exception {
    
        String sql = "INSERT INTO categorias (cat_nome, cat_valor_diario_locacao) VALUES (?,?)";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, categoria.getNome());
            preparedStatement.setFloat(2, categoria.getValorDiarioLocacao());
            preparedStatement.executeUpdate();
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //
    
    //--- UPDATE -------------------------------------------------------------------------------------->
    //
    public void updateCategorias(Categorias categoria) throws Exception {
        
        String sql = "UPDATE categorias SET cat_nome=?, cat_valor_diario_locacao=? WHERE cat_iden=?";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, categoria.getNome());
            preparedStatement.setFloat(2, categoria.getValorDiarioLocacao());
            preparedStatement.setInt(3, categoria.getIden());
            preparedStatement.executeUpdate();
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //

    //--- DELETE -------------------------------------------------------------------------------------->
    //
    public void deleteCategorias(int cat_iden) throws Exception {
        
        String sql = "DELETE FROM categorias WHERE cat_iden=?";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, cat_iden);
            preparedStatement.executeUpdate();
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //
    
    //--- READ ---------------------------------------------------------------------------------------->
    //
    public ArrayList<Categorias> getAllCategorias() throws Exception {
        
        ArrayList<Categorias> lista = new ArrayList<Categorias>();
        
        String sql = "SELECT * FROM categorias";
        
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                
                Categorias categoria = new Categorias();
                
                categoria.setIden(rs.getInt("cat_iden"));
                categoria.setNome(rs.getString("cat_nome"));
                categoria.setValorDiarioLocacao(rs.getFloat("cat_valor_diario_locacao"));
                lista.add(categoria);
            }
        } catch (Exception error) {
            throw error;
        }
        return lista;
    }
    
    public Categorias getCategoriasById(int cat_iden) throws Exception {
        
        Categorias categoria = new Categorias();
        
        String sql = "SELECT * FROM categorias WHERE cat_iden=?";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, cat_iden);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                categoria.setIden(rs.getInt("cat_iden"));
                categoria.setNome(rs.getString("cat_nome"));
                categoria.setValorDiarioLocacao(rs.getFloat("cat_valor_diario_locacao"));
            }
        } catch (Exception error) {
            throw error;
        }
        return categoria;
    }
    
    public Categorias getCategoriasByNome(String cat_nome) throws Exception {
        
        Categorias categoria = new Categorias();
        
        String sql = "SELECT * FROM categorias WHERE cat_nome=?";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, cat_nome);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                categoria.setIden(rs.getInt("cat_iden"));
                categoria.setNome(rs.getString("cat_nome"));
                categoria.setValorDiarioLocacao(rs.getFloat("cat_valor_diario_locacao"));
            }
        } catch (Exception error) {
            throw error;
        }
        return categoria;
    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //
}
