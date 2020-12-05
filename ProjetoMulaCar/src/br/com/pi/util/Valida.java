/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 03/11/2020 15:44:59 
 *  Instituição: FACULDADE SENAI FATESG
 *  Curso      : Análise e Desenvolvimento de sistemas - Módulo 3 - 2020/2
 *  Disciplina : mbd - Modelagem de banco de dados
 *  Alunos     : Jhonathan dos Reis, Gustavo Gabriel e Miguel Neto
 *  Projeto    : PROJETO CAMADAS
 *  Exercício  : Torrentz Filmes
 *  ---------------------------------------------------------------------------------------------------
 *  Propósito do arquivo (Objetivo).
 *  ---------------------------------------------------------------------------------------------------| 
 */
package br.com.pi.util;

/**
 *
 * @author JHONATHAN
 */
public class Valida {

    /**
     * @default
     */
    private Valida() {
    }

    /**
     *
     * @param message
     * @param type
     */
    public static void jComboBox(String message, String type) {
        if (message.equals("--")) {
            throw new RuntimeException("Selecione " + type + "!");
        }
    }

    /**
     *
     * @param message
     * @param type
     */
    public static void notSpecialCharacters(String message, String type) {
        String vetor[] = new String[]{"!", "#", "$", "%", "\\", "&", "’", "(", ")", "*", "+", "\"", "-", ".", "/", ":", ";", "<", "=", ">", "?", "@", "[", "]", "^", "_", "`", "{", "|", "}"};
        String saida = type + "Campo não permite Carácter Especiais.";
        for (String string : vetor) {
            if (message.contains(string)) {
                throw new RuntimeException(saida);
            }
        }
    }

    /**
     *
     * @param message
     * @param type
     */
    public static void notNumber(String message, String type) {
        String saida;
        saida = type + "Campo não permite Números.";
        for (int i = 0; i < 10; i++) {
            if (message.contains(String.format("%d", i))) {
                throw new RuntimeException(saida);
            }
        }
    }

    /**
     *
     * @param message
     * @param type
     */
    public static void numberInteger(String message, String type) {
        String saida = type + "Campo só permite números do conjunto inteiro.\nEx: 3";
        if (!message.matches("[0-9]*")) {
            throw new RuntimeException(saida);
        }
    }

    /**
     *
     * @param message
     * @param type
     */
    public static void numberFloat(String message, String type) {
        String saida = type + "Campo só permite números do conjunto real.\nSe estiver usando \",\" troque por \".\".\nEx: 3.45";
        if (!message.matches("[0-9.]*")) {
            throw new RuntimeException(saida);
        }
    }

    /**
     *
     * @param message
     * @param type
     */
    public static void campoVazio(String message, String type) {
        String saida = type;
        if (message.trim().equals("")) {
            throw new RuntimeException(saida);
        }
    }

    public static void validarPlacaMercosul(String message, String type) {
        String saida = type + "\nPlaca invalida.";
        if (message.length() > 0) {
            if (message.length() < 7) {
                throw new RuntimeException(saida);
            } else {
                if (!message.matches("[A-Z]{3}[0-9][A-Z][0-9]{2}")) {
                    throw new RuntimeException(saida);
                }
            }
        }
    }

    public static void validaPlacaNacional(String message, String type) {

        String saida = type + "\nPlaca invalida.";
        if (message.length() > 0) {
            if (message.length() < 7) {
                throw new RuntimeException(saida);

            } else {

                if (!message.matches("[A-Z]{3}-\\d{4}")) {
                    throw new RuntimeException(saida);
                }
            }
        }
    }
    public static void SomenteNumero(String message, String type) { 
        long valor;
        String saida = type;
        if (message.length() != 0) {
            try {
                valor = Long.parseLong(message);
            } catch (NumberFormatException ex) {
                throw new RuntimeException(saida);
            }
        }
} 

}
