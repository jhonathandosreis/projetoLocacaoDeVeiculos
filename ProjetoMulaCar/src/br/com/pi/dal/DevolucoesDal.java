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

import br.com.pi.bll.LocacoesBll;
import br.com.pi.bll.VeiculosBll;
import br.com.pi.model.Devolucoes;
import br.com.pi.model.Locacoes;
import br.com.pi.model.Veiculos;
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
public class DevolucoesDal {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private Connection conexao;
    private LocacoesBll locacoesBll = new LocacoesBll();
    private Locacoes locacao = null;
    private Veiculos veiculo = null;
    private VeiculosBll veiculoBll = new VeiculosBll();
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

        String sql = "INSERT INTO devolucoes (dev_data_devolucao, dev_multa_por_atraso , dev_km_na_entrega , dev_loc_iden, dev_dias_atraso, dev_diferenca_litros, dev_multa_por_litros, dev_valor_total, dev_multa_transito, dev_danos_veiculo) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {

            java.sql.Date dataDevolucao = new java.sql.Date(devolucao.getDataDevolucao().getTime());

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setDate(1, dataDevolucao);
            preparedStatement.setDouble(2, devolucao.getMultaPorAtraso());
            preparedStatement.setDouble(3, devolucao.getKmNaEntrega());
            preparedStatement.setInt(4, devolucao.getLocacao().getIden());
            preparedStatement.setInt(5, devolucao.getDiferencaDias());
            preparedStatement.setDouble(6, devolucao.getDiferencaLitros());
            preparedStatement.setDouble(7, devolucao.getMultaPorLitro());
            preparedStatement.setDouble(8, devolucao.getValorTotal());
            preparedStatement.setDouble(9, devolucao.getMultaTransito());
            preparedStatement.setDouble(10, devolucao.getDanoVeiculo());
            preparedStatement.executeUpdate();
        } catch (Exception error) {
            throw error;
        }

    }
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //

    //--- UPDATE -------------------------------------------------------------------------------------->
    public void updateDevolucoes(Devolucoes devolucao) throws Exception {

        String sql = "UPDATE devolucoes SET dev_data_devolucao=?, dev_multa_por_atraso=? , dev_km_na_entrega=? , dev_loc_iden=? , dev_dias_atraso=? , dev_diferenca_litros? , dev_multa_por_litros? , dev_valor_total=?, dev_multa_transito=?, dev_danos_veiculo=?  WHERE dev_iden=?";

        try {

            java.sql.Date dataDevolucao = new java.sql.Date(devolucao.getDataDevolucao().getTime());

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setDate(1, dataDevolucao);
            preparedStatement.setDouble(2, devolucao.getMultaPorAtraso());
            preparedStatement.setDouble(3, devolucao.getKmNaEntrega());
            preparedStatement.setInt(4, devolucao.getLocacao().getIden());
            preparedStatement.setInt(5, devolucao.getDiferencaDias());
            preparedStatement.setDouble(6, devolucao.getDiferencaLitros());
            preparedStatement.setDouble(7, devolucao.getMultaPorLitro());
            preparedStatement.setDouble(8, devolucao.getValorTotal());
            preparedStatement.setDouble(9, devolucao.getMultaTransito());
            preparedStatement.setDouble(10, devolucao.getDanoVeiculo());
            preparedStatement.setInt(11, devolucao.getIden());

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

        String sql = "SELECT * FROM devolucoes";
        try {
            ArrayList<Devolucoes> lista = new ArrayList<Devolucoes>();
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Devolucoes devolucao = new Devolucoes();
                int id = (rs.getInt("dev_iden"));
                devolucao = getDevolucoesById(id);
                lista.add(devolucao);
            }
            return lista;
        } catch (Exception error) {
            throw error;
        }

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
                devolucao.setMultaPorAtraso(rs.getDouble("dev_multa_por_atraso"));
                devolucao.setKmNaEntrega(rs.getDouble("dev_km_na_entrega"));
                devolucao.setDiferencaDias(rs.getInt("dev_dias_atraso"));
                devolucao.setDiferencaLitros(rs.getDouble("dev_diferenca_litros"));
                devolucao.setMultaPorLitro(rs.getDouble("dev_multa_por_litros"));
                devolucao.setValorTotal(rs.getDouble("dev_valor_total"));
                devolucao.setMultaTransito(rs.getDouble("dev_multa_transito"));
                devolucao.setDanosVeiculo(rs.getDouble("dev_danos_veiculo"));
                int loc_codigo = rs.getInt("dev_loc_iden");
                locacao = locacoesBll.getLocacoesById(loc_codigo);
                devolucao.setLocacao(locacao);

            }
        } catch (Exception error) {
            throw error;
        }
        return devolucao;
    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //

}
