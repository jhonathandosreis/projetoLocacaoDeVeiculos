/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 15/11/2020 04:14:32 
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

package br.com.pi.bll;

import br.com.pi.dal.ClientesDal;
import br.com.pi.model.Clientes;
import java.util.ArrayList;

/**
 *
 * @author jhonlinux
 */
public class ClientesBll {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    private ClientesDal clienteDal;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    public ClientesBll() throws Exception {
        clienteDal = new ClientesDal();
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- READ ---------------------------------------------------------------------------------------->
    public ArrayList<Clientes> getAllClientes() throws Exception {
        return clienteDal.getAllClientes();
    }
    
    public Clientes getClienteById(int cliente_iden) throws Exception {
        return clienteDal.getClientesById(cliente_iden);
    }

    //--- FIM READ ------------------------------------------------------------------------------------|
    //
}
