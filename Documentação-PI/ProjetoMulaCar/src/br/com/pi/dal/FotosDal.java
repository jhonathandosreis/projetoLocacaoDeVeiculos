/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 23/11/2020 22:47:24 
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

import br.com.pi.model.Fotos;
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
public class FotosDal {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private Connection conexao;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public FotosDal() throws Exception {
        this.conexao = Conexao.getConexao();
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- CREATE -------------------------------------------------------------------------------------->
    public void addFotos(Fotos foto) throws Exception {

        String sql = "INSERT INTO fotos(fot_caminho, fot_pfi_iden) VALUES(?,?)";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, foto.getFot_caminho());
            preparedStatement.setInt(2, foto.getPessoasFisicas().getIden());
            preparedStatement.executeUpdate();
        } catch (Exception error) {
            throw error;
        }

    }
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //

    //--- UPDATE -------------------------------------------------------------------------------------->
    public void updateFotos(Fotos foto) throws Exception {

        String sql = "UPDATE fotos SET fot_caminho=?, fot_pfi_iden=? WHERE fot_iden=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, foto.getFot_caminho());
            preparedStatement.setInt(2, foto.getPessoasFisicas().getIden());
            preparedStatement.setInt(3, foto.getFot_iden());
            preparedStatement.executeUpdate();
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //

    //--- DELETE -------------------------------------------------------------------------------------->
    public void deleteFotos(Fotos foto) throws Exception {

        String sql = "DELETE FROM fotos WHERE fot_iden=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, foto.getFot_iden());
            preparedStatement.executeUpdate();
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //

    //--- READ ---------------------------------------------------------------------------------------->
    public ArrayList<Fotos> getAllFotos() throws Exception {
        
        ArrayList<Fotos> lista = new ArrayList<Fotos>();

        String sql = "SELECT * FROM fotos";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Fotos foto = new Fotos();
                foto.setFot_iden(rs.getInt("fot_iden"));
                foto.setFot_caminho(rs.getString("fot_caminho"));

                //chave estrangeira
                PessoasFisicasDal pessoaFisicaDal = new PessoasFisicasDal();
                foto.setPessoasFisicas(pessoaFisicaDal.getPessoasFisicasById(rs.getInt("fot_pfi_iden")));
            }
        } catch (Exception error) {
            throw error;
        }
        return lista;
    }

    public Fotos getFotosById(int fot_iden) throws Exception {

        Fotos foto = new Fotos();

        String sql = "SELECT * FROM fotos";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                foto.setFot_iden(rs.getInt("fot_iden"));
                foto.setFot_caminho(rs.getString("fot_caminho"));

                //chave estrangeira
                PessoasFisicasDal pessoaFisicaDal = new PessoasFisicasDal();
                foto.setPessoasFisicas(pessoaFisicaDal.getPessoasFisicasById(rs.getInt("fot_pfi_iden")));
            }
        } catch (Exception error) {
            throw error;
        }
        return foto;
    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //
}
