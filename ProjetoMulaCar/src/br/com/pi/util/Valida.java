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

import java.util.InputMismatchException;

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
        String vetor[] = new String[]{"!", "#", "$", "%", "\\", "&", "’", "(", ")", "*", "+", "\"", "-", ".", "/", ":", ";", "<", "=", ">", "?", "@", "[", "]", "^", "_", "`", "'", "{", "|", "}"};
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
    
     public static void isCPF(String CPF, String type) {
          String saida = type;
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") ||
            CPF.equals("11111111111") ||
            CPF.equals("22222222222") || CPF.equals("33333333333") ||
            CPF.equals("44444444444") || CPF.equals("55555555555") ||
            CPF.equals("66666666666") || CPF.equals("77777777777") ||
            CPF.equals("88888888888") || CPF.equals("99999999999") ||
            (CPF.length() != 11))
            throw new RuntimeException(saida);

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
        // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
        // converte o i-esimo caractere do CPF em um numero:
        // por exemplo, transforma o caractere '0' no inteiro 0
        // (48 eh a posicao de '0' na tabela ASCII)
            num = (int)(CPF.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

        // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
            num = (int)(CPF.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                 dig11 = '0';
            else dig11 = (char)(r + 48);

        // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))){
                
            }else throw new RuntimeException(saida);
                } catch (InputMismatchException erro) {
                throw new RuntimeException(saida);
            }
        }
}
