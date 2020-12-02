/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 23/11/2020 11:06:39 
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

import br.com.pi.bll.ModelosBll;
import br.com.pi.model.Modelos;
import java.sql.PreparedStatement;
import br.com.pi.model.Veiculos;
import br.com.pi.util.Conexao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author jhonlinux
 */
public class VeiculosDal {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private Connection conexao;
    private Modelos modelo = null;
    private ModelosBll modeloBll = new ModelosBll();
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public VeiculosDal() throws Exception {
        conexao = Conexao.getConexao();
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- CREATE -------------------------------------------------------------------------------------->
    //
    public void addVeiculos(Veiculos veiculo) throws Exception {

        String sql = "INSERT INTO veiculos (vei_placa, vei_km,"
                + "vei_renavam, vei_status, vei_observacoes, vei_preco_compra, vei_ano_fabricacao,"
                + "vei_numero_passageiros, vei_tipo_combustivel, vei_mod_iden) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, veiculo.getPlaca());
            preparedStatement.setInt(2, veiculo.getQuilimetragem());
            preparedStatement.setString(3, veiculo.getRenavam());
            preparedStatement.setString(4, veiculo.getStatus());
            preparedStatement.setString(5, veiculo.getObservacoes());
            preparedStatement.setInt(6, veiculo.getPrecoDeCompra());
            preparedStatement.setInt(7, veiculo.getAnoFabricacao());
            preparedStatement.setInt(8, veiculo.getCapacidade());
            preparedStatement.setString(9, veiculo.getTipoDeCombustivel());
            preparedStatement.setInt(10, veiculo.getModelo().getIden());
            preparedStatement.executeUpdate();
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //

    //--- UPDATE -------------------------------------------------------------------------------------->
    //
    public void updateVeiculos(Veiculos veiculo) throws Exception {
        String sql = "UPDATE veiculos SET vei_placa=?, vei_km=?,"
                + "vei_renavam=?, vei_status=?, vei_observacoes=?, vei_preco_compra=?, vei_ano_fabricacao=?,"
                + "vei_numero_passageiros=?, vei_tipo_combustivel=?, vei_mod_iden=? WHERE vei_iden=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, veiculo.getPlaca());
            preparedStatement.setInt(2, veiculo.getQuilimetragem());
            preparedStatement.setString(3, veiculo.getRenavam());
            preparedStatement.setString(4, veiculo.getStatus());
            preparedStatement.setString(5, veiculo.getObservacoes());
            preparedStatement.setInt(6, veiculo.getPrecoDeCompra());
            preparedStatement.setInt(7, veiculo.getAnoFabricacao());
            preparedStatement.setInt(8, veiculo.getCapacidade());
            preparedStatement.setString(9, veiculo.getTipoDeCombustivel());
            preparedStatement.setInt(10, veiculo.getModelo().getIden());
            preparedStatement.setInt(11, veiculo.getIden());
            preparedStatement.executeUpdate();
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //

    //--- DELETE -------------------------------------------------------------------------------------->
    //
    public void deleteVeiculos(int vei_iden) throws Exception {

        String sql = "DELETE FROM veiculos WHERE vei_iden=?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, vei_iden);
            preparedStatement.executeUpdate();
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //

    //--- READ ---------------------------------------------------------------------------------------->
    //
    public ArrayList<Veiculos> getAllVeiculos() throws Exception {

        ArrayList<Veiculos> lista = new ArrayList<Veiculos>();

        String sql = "SELECT * FROM veiculos";

        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
               
                Veiculos veiculo = new Veiculos();
                
                veiculo.setIden(rs.getInt("vei_iden"));
                veiculo.setPlaca(rs.getString("vei_placa"));
                veiculo.setQuilimetragem(rs.getInt("vei_km"));
                veiculo.setRenavam(rs.getString("vei_renavam"));
                veiculo.setStatus(rs.getString("vei_status"));
                veiculo.setObservacoes(rs.getString("vei_observacoes"));
                veiculo.setPrecoDeCompra(rs.getInt("vei_preco_compra"));
                veiculo.setAnoFabricacao(rs.getInt("vei_ano_fabricacao"));
                veiculo.setCapacidade(rs.getInt("vei_numero_passageiros"));
                veiculo.setTipoDeCombustivel(rs.getString("vei_tipo_combustivel"));

                //Chave estrangeira
                modelo = modeloBll.getModelosById(rs.getInt("vei_mod_iden"));
                veiculo.setModelo(modelo);
                lista.add(veiculo);
            }
        } catch (Exception error) {
            throw error;
        }
        return lista;
    }

    public Veiculos getVeiculosById(int iden) throws Exception {

        Veiculos veiculo = new Veiculos();

        String sql = "SELECT * FROM veiculos WHERE vei_iden=?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, iden);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                
                veiculo.setIden(rs.getInt("vei_iden"));
                veiculo.setPlaca(rs.getString("vei_placa"));
                veiculo.setQuilimetragem(rs.getInt("vei_km"));
                veiculo.setRenavam(rs.getString("vei_renavam"));
                veiculo.setStatus(rs.getString("vei_status"));
                veiculo.setObservacoes(rs.getString("vei_observacoes"));
                veiculo.setPrecoDeCompra(rs.getInt("vei_preco_compra"));
                veiculo.setAnoFabricacao(rs.getInt("vei_ano_fabricacao"));
                veiculo.setCapacidade(rs.getInt("vei_numero_passageiros"));
                veiculo.setTipoDeCombustivel(rs.getString("vei_tipo_combustivel"));

                //Chave estrangeira
                modelo = modeloBll.getModelosById(rs.getInt("vei_mod_iden"));
                veiculo.setModelo(modelo);
            }
        } catch (Exception error) {
            throw error;
        }
        return veiculo;
    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //
}
