package br.com.mailanalyzer.main;

import br.com.mailanalyzer.compose.ActiveReceiverService;
import br.com.mailanalyzer.domain.ActiveReceiver;
import br.com.mailanalyzer.fluxo.Fluxo;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas Israel
 * reviser Pedro Lobo
 */
public class Base {
    public static void LoadConfigs(){
        Properties props = new Properties();
        try {
            props.load(Base.class.getResourceAsStream(PROPERTIES_FILE));
            //For load configuration, use (String)props.getProperty("configID");
            ACTIVE_RECEIVER = Boolean.parseBoolean((String)props.getProperty("ACTIVE_RECEIVER"));
            long consultTime = Long.parseLong((String)props.getProperty("RANGE_CONSULT_MESSAGE"));
            consultTime *= 1000;
            RANGE_CONSULT_MESSAGE = consultTime;

            
        } catch (IOException ex) {
            Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<ActiveReceiver> GET_ACTIVE_RECEIVERS(){
        //retornar lista completa de active receivers
        return null;
    }


    public static final int RECEIVER_TYPE_EMAIL = 1;
    public static final int RECEIVER_TYPE_SMSC_GATEWAY = 2;

    //Tempo que todas as Threads irao dormir. Default 1 segundo.
    public static final int INTERVALO_SERVICO = 100;

    private static final String PROPERTIES_FILE = "/mailanalyzer.properties";
    public static Fluxo MAIN_FLUXO;
    public static boolean ACTIVE_RECEIVER;
    public static long RANGE_CONSULT_MESSAGE = 60 * 1000;

    //NOME DOS CAMPOS DE COMPOSEFLOW
    public static final String FIELD_MESSAGE = "messageField";
    public static final String FIELD_ACTIVE_RECEIVER = "ActiveReceiverField";


    //NOME DOS FLUXOS
    public static final String ACTIVE_RECEIVER_FLOW = "activeReceiverFlow";
    public static final String INIT_SERVICE_RECEIVER_FLOW = "InitServiceReceiverFlow";
    public static final String TRATAR_MENSAGEM_FLOW = "TratarMensagemFlow";
    public static final String MAIN_FLOW = "mainFlow";
    
    
    
    //Servicos de ActiveReceivers ativos no sistema
    public static ActiveReceiverService[] ACTIVE_SERVICES;

}
