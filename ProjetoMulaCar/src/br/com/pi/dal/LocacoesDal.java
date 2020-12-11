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
import br.com.pi.bll.MotoristasBll;
import br.com.pi.bll.VeiculosBll;
import br.com.pi.model.Clientes;
import br.com.pi.model.Locacoes;
import br.com.pi.model.Motoristas;
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
    private Locacoes locacao = null;
    private Clientes cliente = null;
    private ClientesBll clienteBll = new ClientesBll();
    private Veiculos veiculo = null;
    private VeiculosBll veiculoBll = new VeiculosBll();
    private Motoristas motorista = null;
    private MotoristasBll motoristaBll = new MotoristasBll();
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
        
        String sql = "INSERT INTO locacoes (loc_data_locacao, loc_data_prevista_devolucao, loc_km_inicial,"
                + " loc_valor_locacao, loc_valor_caucao, loc_valor_seguro, loc_valor_total_pago,loc_cli_iden, loc_vei_iden, loc_status, loc_pfi_iden) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try{
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        
        java.sql.Date dataLocacao = new java.sql.Date(locacao.getDataDeLocacao().getTime());
        java.sql.Date dataPrevista = new java.sql.Date(locacao.getDataPrevistDeDevolucao().getTime());
        
        preparedStatement.setDate(1, dataLocacao);
        preparedStatement.setDate(2, dataPrevista);
        preparedStatement.setDouble(3, locacao.getKmInicial());
        preparedStatement.setDouble(4, locacao.getValorLocacao());
        preparedStatement.setDouble(5, locacao.getValorCaucao());
        preparedStatement.setDouble(6, locacao.getValorSeguro());
        preparedStatement.setDouble(7, locacao.getValorTotalPago());
        preparedStatement.setInt(8, locacao.getCliente().getIden());
        preparedStatement.setInt(9, locacao.getVeiculos().getIden());
        preparedStatement.setString(10, locacao.getStatus());
        preparedStatement.setInt(11, locacao.getMotoristas().getIden());
        preparedStatement.executeUpdate();
        
        } catch (Exception error) {
            throw  error;
        }
        
    }
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //
     
    //--- UPDATE -------------------------------------------------------------------------------------->
    public void updateLocacoes (Locacoes locacao) throws Exception {
        
        String sql = "UPDATE locacoes SET loc_data_locacao=?, loc_data_prevista_devolucao=?, loc_km_inicial=?,"
                + " loc_valor_locacao=?, loc_valor_caucao=?, loc_valor_seguro=?, loc_valor_total_pago=?, loc_cli_iden=?, loc_vei_iden=?, loc_status=?, loc_pfi_iden=? WHERE loc_codigo=?";
        try{
       PreparedStatement preparedStatement = conexao.prepareStatement(sql);
           preparedStatement.setInt(1, locacao.getIden());
        
        java.sql.Date dataLocacao = new java.sql.Date(locacao.getDataDeLocacao().getTime());
        java.sql.Date dataPrevista = new java.sql.Date(locacao.getDataPrevistDeDevolucao().getTime());
        
        preparedStatement.setDate(1, dataLocacao);
        preparedStatement.setDate(2, dataPrevista);
        preparedStatement.setDouble(3, locacao.getKmInicial());
        preparedStatement.setDouble(4, locacao.getValorLocacao());
        preparedStatement.setDouble(5, locacao.getValorCaucao());
        preparedStatement.setDouble(6, locacao.getValorSeguro());
        preparedStatement.setDouble(7, locacao.getValorTotalPago());
        preparedStatement.setInt(8, locacao.getCliente().getIden());
        preparedStatement.setInt(9, locacao.getVeiculos().getIden());
        preparedStatement.setString(10, locacao.getStatus());
         preparedStatement.setInt(11, locacao.getMotoristas().getIden());
        preparedStatement.setInt(12, locacao.getIden());
        preparedStatement.executeUpdate();
        
        } catch (Exception error) {
            throw  error;
        }
        
    }
    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //

    //--- DELETE -------------------------------------------------------------------------------------->
    public void deleteLocacoes (Locacoes locacao) throws Exception {
        
           String sql = "DELETE FROM locacoes WHERE loc_codigo=?";
        try{
            
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
        
         String sql = "SELECT * FROM locacoes";
         try{
         ArrayList<Locacoes> lista = new ArrayList<Locacoes>();
         Statement statement = conexao.createStatement();
         ResultSet rs = statement.executeQuery(sql);
         
           while(rs.next()){
                locacao = new Locacoes();
                int id_loc = (rs.getInt("loc_codigo"));
                locacao = getLocacoesById(id_loc);
                lista.add(locacao);
           } 
    return lista;
        } catch (Exception error) {
            throw  error;
        }
    }  
    
    public Locacoes getLocacoesById(int loc_iden) throws Exception {
        String sql = "SELECT * FROM locacoes WHERE loc_codigo=?";
        try{
        locacao = new Locacoes();
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setInt(1, loc_iden);
        ResultSet rs = preparedStatement.executeQuery();
         
           if(rs.next()){
                int cli_id = rs.getInt("loc_cli_iden");
                cliente = clienteBll.getClienteById(cli_id);
                int vei_id = rs.getInt("loc_vei_iden");
                veiculo = veiculoBll.getVeiculosById(vei_id);
                int mot_iden = rs.getInt("loc_pfi_iden");
                motorista = motoristaBll.getMotoristaById(mot_iden);
                locacao.setIden(rs.getInt("loc_codigo"));
                locacao.setDataDeLocacao(rs.getDate("loc_data_locacao"));
                locacao.setDataPrevistDeDevolucao(rs.getDate("loc_data_prevista_devolucao"));
                locacao.setKmInicial(rs.getDouble("loc_km_inicial"));
                locacao.setValorLocacao(rs.getDouble("loc_valor_locacao"));
                locacao.setValorCaucao(rs.getDouble("loc_valor_caucao"));
                locacao.setValorSeguro(rs.getDouble("loc_valor_seguro"));
                locacao.setValorTotalPago(rs.getDouble("loc_valor_total_pago"));
                locacao.setStatus(rs.getString("loc_status"));
                locacao.setCliente(cliente);
                locacao.setVeiculos(veiculo);
                locacao.setMotoristas(motorista);
            }
            return locacao;
        } catch (Exception error) {
            throw  error;
        }
    }
    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //
