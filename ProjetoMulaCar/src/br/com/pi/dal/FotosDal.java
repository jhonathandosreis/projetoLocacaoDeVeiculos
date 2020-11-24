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
    public void addFotos (Fotos foto)throws Exception {
        
    }

    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //
    
    
    //--- UPDATE -------------------------------------------------------------------------------------->
    public void updateFotos (Fotos foto)throws Exception {
        
    }
    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //

    //--- DELETE -------------------------------------------------------------------------------------->
    public void deleteFotos (Fotos foto)throws Exception {
        
    }
    
    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //
    
    //--- READ ---------------------------------------------------------------------------------------->
    // READ ALL
    public ArrayList<Fotos> getAllFotos() throws Exception {
        ArrayList<Fotos> lista = new ArrayList<Fotos>();
        return lista;
    }
    
    // READ BY ID
    public Fotos getFotosById(int fot_iden) throws Exception {
        Fotos foto = new Fotos();
        return foto;
    }

    //--- FIM READ ------------------------------------------------------------------------------------|
    //

}
