/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mailanalyzer.log;

import br.com.mailanalyzer.dao.LogDAO;
import br.com.mailanalyzer.domain.Log;
import br.com.mailanalyzer.main.Config;
import br.com.mailanalyzer.utils.Converte;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas Israel
 * @see 09/10/11
 * @version 1.0
 * Contato: mcluck.ti@gmail.com
 *
 */
public class L implements Runnable {

    private static L instance;
    private static LogDAO ldao = new LogDAO();
    private static boolean run = true;
    public static long INTERVALO = 1000;

    public static String getColor(int tipo){
        switch(tipo){
            case INFO: return "#FFFAFA";
            case DEBUG: return "#ADD8E6";
            case AVISO: return "#87CEFA";
            case ERRO: return "#F08080";
            case FATAL: return "#CD5C5C";
            default : return "#FFFFFF";
        }
    }

    private static L getInstance() {
        if (instance == null) {
            instance = new L();
            run = true;
            //Inicia servico de salvar itens
            new Thread(instance).start();
        }
        return instance;
    }

    public static String GET_OCORRENCIA(int oco){
        switch(oco){
            case ERRO:return "ERRO";
            case INFO:return "INFO";
            case AVISO:return "AVISO";
            case FATAL:return "FATAL";
            case DEBUG:return "DEBUG";
        }
        return "";
    }

    public void printLog(Log l) {
        if(!Config.LOG.PRINT_IN_CONSOLE){
            return;
        }
        if (l.getDataRegistro() == 0) {
            l.setDataRegistro(new java.util.Date().getTime());
        }
        StringBuffer sb = new StringBuffer();
        sb.append(getTime(l.getDataRegistro()));
        sb.append(" ");
        sb.append(Config.LOG.TAG_CONSOLE);
        sb.append(" ");
        sb.append(GET_OCORRENCIA(l.getOcorrencia()));
        sb.append(" ");
        sb.append(l.getDetalhe());
        sb.append("   ");
        sb.append(l.getExceptionApp());

        if (l.getOcorrencia() == ERRO) {
            System.err.println(sb.toString());
        }else{
            System.out.println(sb.toString());
        }
    }

    @Override
    public void run() {
        while (run) {
            salvar();
            try {
                Thread.sleep(INTERVALO);
            } catch (InterruptedException ex) {
                Logger.getLogger(L.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void StartLogService(){
        getInstance();
        if(run){
            System.err.println("LOG ja esta rodando!");
            return;
        }
        run = true;
        new Thread(getInstance()).start();
        System.out.println("Log iniciado");
    }

    public static void StopLogService() {
        run = false;
        System.out.println("Log parado");
    }

    private String getTime(long time) {
        StringBuffer sb = new StringBuffer();

        java.util.Date dt = new java.util.Date();
        dt.setTime(time);
        sb.append(Converte.ToStringDataVisual(dt));
        sb.append(" ");
        sb.append(Converte.DateToStringTimer(time));

        return sb.toString();
    }
    /**
     * Ocorrencias de nivel debug, mais utilizada por desenvolvedor
     */
    public final static int DEBUG = 0;
    /**
     * Ocorrencia de nivel informacional, utilizada apenas para lancar informacoes no log
     */
    public final static int INFO = 1;
    /**
     * Ocorrencia de nivel aviso, utilizada para lancar avisos do sistema no log
     */
    public final static int AVISO = 2;
    /**
     * Ocorrencia de nivel erro, utilizada para lancar todos os erros no log
     */
    public final static int ERRO = 3;
    /**
     * Ocorrencia de nivel fatal, utilizada para erros fatais no sistemas. Erros que impedem o funcionamento do sistema.
     */
    public final static int FATAL = 4;

    /**
     * Log completo. Por favor, de preferencia em utilizar os metodos reduzidos para evitar erro de configuracao do LOG
     * @param tag
     * @param classe
     * @param ocorrencia
     * @param msg
     * @param thr
     * @param nivel
     */
    public static void LOG(String tag, Class classe, int ocorrencia, String msg, Throwable thr, int nivel) {
        getInstance().log(tag, classe, ocorrencia, msg, thr, nivel);
    }

    /**
     * Log para DEBUG.
     * @param tag
     * @param classe
     * @param msg
     */
    public static void d(String tag, Class classe, String msg) {
        LOG(tag, classe, DEBUG, msg, null, Config.LOG.NIVEL);
    }

    /**
     * Log para DEBUG.
     * @param tag
     * @param classe
     * @param msg
     */
    public static void d(String tag, Object classe, String msg) {
        LOG(tag, classe.getClass(), DEBUG, msg, null, Config.LOG.NIVEL);
    }

    /**
     * Log para avisos do sistema. Nivel estabelecido em Config.LOG.NIVEL
     * @param tag
     * @param classe
     * @param msg
     */
    public static void a(String tag, Class classe, String msg) {
        LOG(tag, classe, AVISO, msg, null, Config.LOG.NIVEL);
    }
    /**
     * Log para avisos do sistema. Nivel estabelecido em Config.LOG.NIVEL
     * @param tag
     * @param classe
     * @param msg
     */
    public static void a(String tag, Object classe, String msg) {
        LOG(tag, classe.getClass(), AVISO, msg, null, Config.LOG.NIVEL);
    }

    /**
     * LOG para INFO.
     * @param tag
     * @param classe
     * @param msg
     */
    public static void i(String tag, Class classe, String msg) {
        LOG(tag, classe, INFO, msg, null, Config.LOG.NIVEL);
    }
    /**
     * LOG para INFO.
     * @param tag
     * @param classe
     * @param msg
     */
    public static void i(String tag, Object classe, String msg) {
        LOG(tag, classe.getClass(), INFO, msg, null, Config.LOG.NIVEL);
    }

    /**
     * LOG para ERRO. Nivel padrao estabelecido em Config.LOG.NIVEL
     * @param tag
     * @param classe
     * @param msg
     * @param ex
     * 
     */
    public static void e(String tag, Class classe, String msg, Exception ex) {
        LOG(tag, classe, ERRO, msg, ex, Config.LOG.NIVEL);
    }

    /**
     * LOG para ERRO. Nivel padrao estabelecido em Config.LOG.NIVEL
     * @param tag
     * @param classe
     * @param msg
     * @param ex
     */
    public static void e(String tag, Object classe,String msg, Exception ex) {
        LOG(tag, classe.getClass(), ERRO, msg, ex, Config.LOG.NIVEL);
    }

    /**
     * LOG para ERRO. Nivel padrao estabelecido em parametro
     * @param tag
     * @param classe
     * @param msg
     * @param ex
     * @param nivel
     */
    public static void e(String tag, Class classe, String msg, Exception ex, int nivel) {
        LOG(tag, classe, ERRO, msg, ex, nivel);
    }
    
    /**
     * LOG para ERRO. Nivel padrao estabelecido em parametro
     * @param tag
     * @param classe
     * @param msg
     * @param ex
     * @param nivel
     */
    public static void e(String tag, Object classe, String msg, Exception ex, int nivel) {
        LOG(tag, classe.getClass(), ERRO, msg, ex, nivel);
    }

    /**
     * LOG para ERROS FATAIS do sistema. Nivel Config.LOG.NIVEL_MAXIMO
     * @param tag
     * @param classe
     * @param msg
     * @param ex
     */
    public static void f(String tag, Class classe, String msg, Exception ex) {
        LOG(tag, classe, INFO, msg, ex, Config.LOG.NIVEL_MAXIMO);
    }
    /**
     * LOG para ERROS FATAIS do sistema. Nivel Config.LOG.NIVEL_MAXIMO
     * @param tag
     * @param classe
     * @param msg
     * @param ex
     */
    public static void f(String tag, Object classe, String msg, Exception ex) {
        LOG(tag, classe.getClass(), INFO, msg, ex, Config.LOG.NIVEL_MAXIMO);
    }

    private ConcurrentLinkedQueue<Log> listaLog = new ConcurrentLinkedQueue<Log>();

    public void log(String tag, Class classe, int ocorrencia, String msg, Throwable thr, int nivel) {
        Log log = new Log();
        log.setDetalhe(msg);
        log.setOcorrencia(ocorrencia);
        log.setNivel((nivel == -1) ? Config.LOG.NIVEL : nivel);
        log.setReferencia(classe.getName());
        log.setTagApp(tag);
        StringBuffer sb = new StringBuffer();

        if (thr != null) {
            switch (log.getNivel()) {
                case Config.LOG.NIVEL_BAIXO: {
                    sb.append(thr.getLocalizedMessage());
                    break;
                }
                case Config.LOG.NIVEL_MEDIO: {
                    sb.append(thr.getMessage());
                    break;
                }
                case Config.LOG.NIVEL_MAXIMO: {
                    for (StackTraceElement st : thr.getStackTrace()) {
                        if (st != null) {
                            sb.append(st.toString());
                        }
                    }
                }
            }
        }
        log.setExceptionApp(sb.toString());
        listaLog.add(log);
        printLog(log);
    }

    private void salvar() {
        synchronized (this) {
            for (Log l : listaLog) {
                ldao.salvar(l);
            }
            ldao.commit();
            listaLog.clear();
        }
    }
}
