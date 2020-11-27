/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 23/11/2020 22:39:07 
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

import br.com.pi.bll.ClientesBll;
import br.com.pi.bll.LocacoesBll;
import br.com.pi.bll.VeiculosBll;
import br.com.pi.model.Clientes;
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
public class LocacoesDal {
    
    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    private Connection conexao;
    private Locacoes locacao;
    private LocacoesBll locacaoBll;
    private Clientes cliente;
    private ClientesBll clienteBll;
    private Veiculos veiculo;
    private VeiculosBll veiculoBll;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public LocacoesDal() throws Exception {
        this.conexao = Conexao.getConexao();
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- CREATE -------------------------------------------------------------------------------------->
    public void addLocacoes (Locacoes locacao) throws Exception {
        
        try{
        String sql = "INSERT INTO locacoes (loc_data_locacao=?, loc_data_prevista_devolucao=?, loc_km_inicial=?,"
                + " loc_valor_locacao=?, loc_valor_caucao=?, loc_valor_seguro=?, loc_valor_total_pago=?, loc_cli_iden=?, loc_vei_iden=?)";
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        
        java.sql.Date dataLocacao = new java.sql.Date(locacao.getDataDeLocacao().getTime());
        java.sql.Date dataPrevista = new java.sql.Date(locacao.getDataPrevistDeDevolucao().getTime());
        
        preparedStatement.setDate(1, dataLocacao);
        preparedStatement.setDate(2, dataPrevista);
        preparedStatement.setDouble(3, locacao.getKmInicial());
        preparedStatement.setFloat(4, locacao.getValorLocacao());
        preparedStatement.setFloat(5, locacao.getValorCaucao());
        preparedStatement.setFloat(6, locacao.getValorSeguro());
        preparedStatement.setFloat(7, locacao.getValorTotalPago());
        preparedStatement.setInt(8, locacao.getCliente().getIden());
        preparedStatement.setInt(9, locacao.getVeiculos().getIden());
        preparedStatement.executeUpdate();
        
        } catch (Exception error) {
            throw  error;
        }
        
    }
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //
     
    //--- UPDATE -------------------------------------------------------------------------------------->
    public void updateLocacoes (Locacoes locacao) throws Exception {
        
        try{
        String sql = "UPDATE locacoes SET loc_data_locacao=?, loc_data_prevista_devolucao=?, loc_km_inicial=?,"
                + " loc_valor_locacao=?, loc_valor_caucao=?, loc_valor_seguro=?, loc_valor_total_pago=?, loc_cli_iden=?, loc_vei_iden=?)";
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        
        java.sql.Date dataLocacao = new java.sql.Date(locacao.getDataDeLocacao().getTime());
        java.sql.Date dataPrevista = new java.sql.Date(locacao.getDataPrevistDeDevolucao().getTime());
        
        preparedStatement.setDate(1, dataLocacao);
        preparedStatement.setDate(2, dataPrevista);
        preparedStatement.setDouble(3, locacao.getKmInicial());
        preparedStatement.setFloat(4, locacao.getValorLocacao());
        preparedStatement.setFloat(5, locacao.getValorCaucao());
        preparedStatement.setFloat(6, locacao.getValorSeguro());
        preparedStatement.setFloat(7, locacao.getValorTotalPago());
        preparedStatement.setInt(8, locacao.getCliente().getIden());
        preparedStatement.setInt(9, locacao.getVeiculos().getIden());
        preparedStatement.setInt(10, locacao.getIden());
        preparedStatement.executeUpdate();
        
        } catch (Exception error) {
            throw  error;
        }
        
    }
    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //

    //--- DELETE -------------------------------------------------------------------------------------->
    public void deleteLocacoes (Locacoes locacao) throws Exception {
        
        try{
            
           String sql = "DELETE FROM locacoes WHERE loc_iden=?";
           PreparedStatement preparedStatement = conexao.prepareStatement(sql);
           preparedStatement.setInt(1, locacao.getIden());
           preparedStatement.executeUpdate();

        } catch (Exception error) {
            throw  error;
        }
    }  
    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //
    
    //--- READ ---------------------------------------------------------------------------------------->
    public ArrayList<Locacoes> getAllLocacoes() throws Exception {
        
         try{
         ArrayList<Locacoes> lista = new ArrayList<Locacoes>();
         String sql = "SELECT * FROM locacoes";
         Statement statement = conexao.createStatement();
         ResultSet rs = statement.executeQuery(sql);
         
           if(rs.next()){
                int cli_id = rs.getInt("pfi_cli_iden");
                cliente = clienteBll.getClienteById(cli_id);
                int vei_id = rs.getInt("loc_vei_iden");
                veiculo = veiculoBll.getVeiculosById(vei_id);
                locacao.setIden(rs.getInt("loc_iden"));
                locacao.setDataDeLocacao(rs.getDate("loc_data_locacao"));
                locacao.setDataPrevistDeDevolucao(rs.getDate("loc_data_prevista_devolucao"));
                locacao.setKmInicial(rs.getDouble("loc_km_inicial"));
                locacao.setValorLocacao(rs.getFloat("loc_valor_locacao"));
                locacao.setValorCaucao(rs.getFloat("loc_valor_caucao"));
                locacao.setValorSeguro(rs.getFloat("loc_valor_seguro"));
                locacao.setValorTotalPago(rs.getFloat("loc_valor_total_pago"));
                locacao.setCliente(cliente);
                locacao.setVeiculos(veiculo);
                lista.add(locacao);
           } 
    return lista;
        } catch (Exception error) {
            throw  error;
        }
    }  
    
    public Locacoes getLocacoesById(int loc_iden) throws Exception {
        try{
        locacao = new Locacoes();
        String sql = "SELECT * FROM locacoes WHERE loc_iden=?";
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setInt(1, loc_iden);
        ResultSet rs = preparedStatement.executeQuery();
         
           if(rs.next()){
                int cli_id = rs.getInt("pfi_cli_iden");
                cliente = clienteBll.getClienteById(cli_id);
                int vei_id = rs.getInt("loc_vei_iden");
                veiculo = veiculoBll.getVeiculosById(vei_id);
                locacao.setIden(rs.getInt("loc_iden"));
                locacao.setDataDeLocacao(rs.getDate("loc_data_locacao"));
                locacao.setDataPrevistDeDevolucao(rs.getDate("loc_data_prevista_devolucao"));
                locacao.setKmInicial(rs.getDouble("loc_km_inicial"));
                locacao.setValorLocacao(rs.getFloat("loc_valor_locacao"));
                locacao.setValorCaucao(rs.getFloat("loc_valor_caucao"));
                locacao.setValorSeguro(rs.getFloat("loc_valor_seguro"));
                locacao.setValorTotalPago(rs.getFloat("loc_valor_total_pago"));
                locacao.setCliente(cliente);
                locacao.setVeiculos(veiculo);
            }
            return locacao;
        } catch (Exception error) {
            throw  error;
        }
    }
    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //
