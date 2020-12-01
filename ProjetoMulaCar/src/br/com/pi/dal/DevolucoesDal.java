/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 23/11/2020 22:53:50 
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

import br.com.pi.model.Devolucoes;
import br.com.pi.util.Conexao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Gustavo Gabriel
 */
public class DevolucoesDal {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private Connection conexao;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public DevolucoesDal() throws Exception {
        this.conexao = Conexao.getConexao();
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- CREATE -------------------------------------------------------------------------------------->
    public void addDevolucoes(Devolucoes devolucao) throws Exception {

        String sql = "INSERT INTO devolucoes (dev_data_devolucao, dev_multa_por_atraso , dev_status , dev_km_na_entrega , dev_loc_iden ) VALEUS (?,?,?,?,?)";
        try {

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setDate(1, (Date) devolucao.getDataDevolucao());
            preparedStatement.setInt(2, devolucao.getMultaPorAtraso());
            preparedStatement.setString(3, devolucao.getStatus());
            preparedStatement.setInt(4, devolucao.getKmNaEntrega());
            preparedStatement.setInt(5, devolucao.getLocacao().getIden());

            preparedStatement.executeUpdate();
        } catch (Exception error) {
            throw error;
        }
        
    }
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //

    //--- UPDATE -------------------------------------------------------------------------------------->
    public void updateDevolucoes(Devolucoes devolucao) throws Exception {

        String sql = "UPDATE devolucoes SET dev_data_devolucao=?, dev_multa_por_atraso=? , dev_status=? , dev_km_na_entrega=? , dev_loc_iden=?  WHERE dev_iden=?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setDate(1, (Date) devolucao.getDataDevolucao());
            preparedStatement.setInt(2, devolucao.getMultaPorAtraso());
            preparedStatement.setString(3, devolucao.getStatus());
            preparedStatement.setInt(4, devolucao.getKmNaEntrega());
            preparedStatement.setInt(5, devolucao.getLocacao().getIden());
            preparedStatement.setInt(6, devolucao.getIden());

            preparedStatement.executeUpdate();
       
            } catch (Exception error) {
            throw error;
        
        }

    }
    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //

    //--- DELETE -------------------------------------------------------------------------------------->
    public void deleteDevolucoes(int dev_iden) throws Exception {

        String sql = "DELETE FROM devolucoes WHERE dev_iden =?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, dev_iden);
            preparedStatement.executeUpdate();
        
             } catch (Exception error) {
            throw error;
        
        }
    }
    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //

    //--- READ ---------------------------------------------------------------------------------------->
    public ArrayList<Devolucoes> getAllDevolucoes() throws Exception {
        ArrayList<Devolucoes> lista = new ArrayList<Devolucoes>();

        String sql = "SELECT * FROM devolucoes";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Devolucoes devolucao = new Devolucoes();

                devolucao.setIden(rs.getInt("dev_iden"));
                devolucao.setDataDevolucao(rs.getDate("dev_data_devolucao"));
                devolucao.setMultaPorAtraso(rs.getInt("dev_multa_por_atraso"));
                devolucao.setStatus(rs.getString("dev_status"));
                devolucao.setKmNaEntrega(rs.getInt("dev_km_na_entrega"));

                LocacoesDal locacao = new LocacoesDal();
                devolucao.setLocacao(locacao.getLocacoesById(rs.getInt(" dev_loc_iden")));

                lista.add(devolucao);
            }
        } catch (Exception error) {
            throw error;
        }

        return lista;
    }

    public Devolucoes getDevolucoesById(int dev_iden) throws Exception {
        
        Devolucoes devolucao = new Devolucoes();
       
        String sql = "SELECT * FROM devolucoes WHERE dev_iden=?";

        try {

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, dev_iden);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {

                devolucao.setIden(rs.getInt("dev_iden"));
                devolucao.setDataDevolucao(rs.getDate("dev_data_devolucao"));
                devolucao.setMultaPorAtraso(rs.getInt("dev_multa_por_atraso"));
                devolucao.setStatus(rs.getString("dev_status"));
                devolucao.setKmNaEntrega(rs.getInt("dev_km_na_entrega"));

                LocacoesDal locacao = new LocacoesDal();
                devolucao.setLocacao(locacao.getLocacoesById(rs.getInt(" dev_loc_iden")));

            }
        } catch (Exception error) {
            throw error;
        }
        return devolucao;
    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //

}
