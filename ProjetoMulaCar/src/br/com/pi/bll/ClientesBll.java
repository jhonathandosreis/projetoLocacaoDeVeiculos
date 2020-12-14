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
import br.com.pi.util.Valida;
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
    //--- CREATE -------------------------------------------------------------------------------------->
    public void addClientes(Clientes cliente) throws Exception {
        if(!cliente.getTipo().equals("JURIDICA")){
        Valida.campoVazio(cliente.getNome(), "Campo nome deve ser preenchido!"); 
        Valida.notNumber(cliente.getNome(), "Campo nome não deve conter números!");
        Valida.notSpecialCharacters(cliente.getNome(), "Campo nome não deve conter caracteres especiais!");
        }
        Valida.campoVazio(cliente.getEmail(), "Campo email deve ser preenchido!");
        if (!cliente.getEmail().contains("@") || !cliente.getEmail().contains(".")) {
             throw new RuntimeException("E-mail inválido!");
        }
          
        if (cliente.getTelefone().equals("(  )      -    ")) throw new RuntimeException ("Campo telefone vazio");
        if(cliente.getEnderecos().getIden() == 0) throw new RuntimeException ("Erro ao cadastrar endereço!"); // validando se a chave estrangeira esta persistindo
        try{
         clienteDal.addClientes(cliente);

        } catch (Exception error) {
            if(error.getMessage().contains("email_repetido")) throw new RuntimeException("E-mail ("+cliente.getEmail()+") já cadastrado em nosso sistema!");
            if(error.getMessage().contains("telefone_repetido")) throw new RuntimeException("Telefone ("+cliente.getTelefone()+") já cadastrado em nosso sistema!");
            
        }
    }
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //
    
    //--- UPDATE--------------------------------------------------------------------------------------->
    public void updateClientes(Clientes cliente) throws Exception { 
         if(!cliente.getTipo().equals("JURIDICA")){
        Valida.campoVazio(cliente.getNome(), "Campo nome deve ser preenchido!"); 
        Valida.notNumber(cliente.getNome(), "Campo nome não deve conter números!");
        Valida.notSpecialCharacters(cliente.getNome(), "Campo nome não deve conter caracteres especiais!");
         }
        Valida.campoVazio(cliente.getEmail(), "Campo email deve ser preenchido!");
        if (!cliente.getEmail().contains("@") || !cliente.getEmail().contains(".")) {
             throw new RuntimeException("E-mail inválido!");
        }
        if (cliente.getTelefone().equals("(  )      -    ")) throw new RuntimeException ("Campo telefone vazio");
         if(cliente.getEnderecos().getIden() == 0) throw new RuntimeException ("Erro ao cadastrar endereço!"); // validando se a chave estrangeira esta persistindo
         
        try{
 
         clienteDal.updateClientes(cliente);
        } catch (Exception error) {
            if(error.getMessage().contains("email_repetido")) throw new RuntimeException("E-mail ("+cliente.getEmail()+") já cadastrado em nosso sistema!");
            if(error.getMessage().contains("telefone_repetido")) throw new RuntimeException("Telefone ("+cliente.getTelefone()+") já cadastrado em nosso sistema!");
            
        }
    }
    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    
    //--- DELETE--------------------------------------------------------------------------------------->
    public void deleteClientes(Clientes cliente) throws Exception {
        try{
            clienteDal.deleteClientes(cliente);
         } catch (Exception error) {
            
        }
    }
    
    public void deleteLast() throws Exception {
       try{
           clienteDal.deleteLast();
        } catch (Exception error) {
            throw  error;
         }
    }
    //--- FIM DELET ----------------------------------------------------------------------------------|
    //--- READ ---------------------------------------------------------------------------------------->
    public ArrayList<Clientes> getAllClientes() throws Exception {
        return clienteDal.getAllClientes();
    }
    
    public Clientes getClienteById(int cliente_iden) throws Exception {
        return clienteDal.getClientesById(cliente_iden);
    }
    
    public Clientes getClienteByTelefone(String cliente_telefone) throws Exception {
        return clienteDal.getClientesByTelefone(cliente_telefone);
    }

    //--- FIM READ ------------------------------------------------------------------------------------|
    //
}
