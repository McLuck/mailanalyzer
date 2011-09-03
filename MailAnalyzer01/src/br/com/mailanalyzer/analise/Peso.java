/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mailanalyzer.analise;

/**
 *
 * @author mcluck
 */
public class Peso {

    /**
     * Para indicar elementos com relevancia extremas
     */
    public static final int MUITO_RELEVANTE = 13;
    /**
     * Para indicar elementos sem relevancia alguma
     */
    public static final int IRRELEVANTE = 0;
    /**
     * Para indicar elementos relevantes
     */
    public static final int RELEVANTE = 7;
    /**
     * Para indicar elementos com relevancia normal
     */
    public static final int NORMAL = 5;
    /**
     * Para indicar elementos com pouca relevancia
     */
    public static final int POUCO_RELEVANTE = 3;
    /**
     * Para indicar elementos com irrelevantes.<br>
     * Cuidado, isto ira remover muita pontuacao do elemento inclinando
     * ele para um outro assunto.<br>
     * Dependendo do ponto da analise, provavelmente, sera descartada toda
     * a analise e desconsiderado outros pontos.
     * <br>
     * Usar quando aparecer termos que "COM CERTEZA" nao pertence ao
     * assunto em questao.
     */
    public static final int INCORRETO = -1000;
}
