/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 23/11/2020 19:47:59 
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

import br.com.pi.bll.CategoriasBll;
import br.com.pi.bll.MarcasBll;
import br.com.pi.bll.TiposDeVeiculosBll;
import br.com.pi.model.Categorias;
import br.com.pi.model.Marcas;
import java.sql.PreparedStatement;
import br.com.pi.model.Modelos;
import br.com.pi.model.TiposDeVeiculos;
import br.com.pi.util.Conexao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author jhonlinux
 */
public class ModelosDal {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private Connection conexao;
    private Marcas marca = null;
    private MarcasBll marcaBll = new MarcasBll();
    private Categorias categoria = null;
    private CategoriasBll categoriaBll = new CategoriasBll();
    private TiposDeVeiculos tiposDeVeiculos = null;
    private TiposDeVeiculosBll tiposDeVeiculosBll = new TiposDeVeiculosBll();
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public ModelosDal() throws Exception {
        conexao = Conexao.getConexao();
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- CREATE -------------------------------------------------------------------------------------->
    //
    public void addModelos(Modelos modelo) throws Exception {

        String sql = "INSERT INTO modelos (mod_nome, mod_cat_iden, mod_mar_iden, mod_tve_iden) VALUES (?,?,?,?)";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, modelo.getNome());
            preparedStatement.setInt(2, modelo.getCategoria().getIden());
            preparedStatement.setInt(3, modelo.getMarcas().getIden());
            preparedStatement.setInt(4, modelo.getTiposDeVeiculos().getIden());
            preparedStatement.executeUpdate();
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //

    //--- UPDATE -------------------------------------------------------------------------------------->
    //
    public void updateModelos(Modelos modelo) throws Exception {

        String sql = "UPDATE modelos SET mod_nome=?, mod_cat_iden=?, mod_mar_iden=?, mod_tve_iden=? WHERE mod_iden=?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, modelo.getNome());
            preparedStatement.setInt(2, modelo.getCategoria().getIden());
            preparedStatement.setInt(3, modelo.getMarcas().getIden());
            preparedStatement.setInt(4, modelo.getTiposDeVeiculos().getIden());
            preparedStatement.setInt(5, modelo.getIden());
            preparedStatement.executeUpdate();
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //

    //--- DELETE -------------------------------------------------------------------------------------->
    //
    public void deleteModelos(int mod_iden) throws Exception {

        String sql = "DELETE FROM modelos WHERE mod_iden=?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, mod_iden);
            preparedStatement.executeUpdate();
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //

    //--- READ ---------------------------------------------------------------------------------------->
    //
    public ArrayList<Modelos> getAllModelos() throws Exception {

        ArrayList<Modelos> lista = new ArrayList<Modelos>();

        String sql = "SELECT * FROM modelos";

        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {

                Modelos modelo = new Modelos();

                modelo.setIden(rs.getInt("mod_iden"));
                modelo.setNome(rs.getString("mod_nome"));

                //Chave estrangeira
                marca = marcaBll.getMarcasById(rs.getInt("mod_mar_iden"));
                modelo.setMarcas(marca);
                
                categoria = categoriaBll.getCategoriasById(rs.getInt("mod_cat_iden"));
                modelo.setCategoria(categoria);
                
                tiposDeVeiculos = tiposDeVeiculosBll.getTiposDeVeiculosById(rs.getInt("mod_tve_iden"));
                modelo.setTiposDeVeiculos(tiposDeVeiculos);
                lista.add(modelo);
            }
        } catch (Exception error) {
            throw error;
        }
        return lista;
    }

    public Modelos getModelosById(int mod_iden) throws Exception {

        Modelos modelo = new Modelos();

        String sql = "SELECT * FROM modelos WHERE mod_iden=?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, mod_iden);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {

                modelo.setIden(rs.getInt("mod_iden"));
                modelo.setNome(rs.getString("mod_nome"));

                //Chave estrangeira
                marca = marcaBll.getMarcasById(rs.getInt("mod_mar_iden"));
                modelo.setMarcas(marca);
                
                categoria = categoriaBll.getCategoriasById(rs.getInt("mod_cat_iden"));
                modelo.setCategoria(categoria);
                
                tiposDeVeiculos = tiposDeVeiculosBll.getTiposDeVeiculosById(rs.getInt("mod_tve_iden"));
                modelo.setTiposDeVeiculos(tiposDeVeiculos);
            }
        } catch (Exception error) {
            throw error;
        }
        return modelo;
    }
    
    public Modelos getModelosByNome(String nome) throws Exception {
        
        Modelos modelo = new Modelos();
        
        String sql = "SELECT * FROM modelos WHERE mod_nome=?";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, nome);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                
                modelo.setIden(rs.getInt("mod_iden"));
                modelo.setNome(rs.getString("mod_nome"));

                //Chave estrangeira
                MarcasDal marcaDal = new MarcasDal();
                modelo.setMarcas(marcaDal.getMarcasById(rs.getInt("mod_mar_iden")));

                CategoriasDal categoriaDal = new CategoriasDal();
                modelo.setCategoria(categoriaDal.getCategoriasById(rs.getInt("mod_cat_iden")));
                
                TiposDeVeiculosDal tiposDeVeiculosDal = new TiposDeVeiculosDal();
                modelo.setTiposDeVeiculos(tiposDeVeiculosDal.getTiposDeVeiculosById(rs.getInt("mod_tve_iden")));
            }
        } catch (Exception error) {
            throw error;
        }
        return modelo;
    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //
}
