package br.com.mailanalyzer.main;

import br.com.mailanalyzer.commands.SubjectNotFoundCommand;
import br.com.mailanalyzer.compose.ActiveReceiverService;
import br.com.mailanalyzer.dao.TermVariationDAO;
import br.com.mailanalyzer.dao.actions.ActionActiveReceiver;
import br.com.mailanalyzer.domain.ActiveReceiver;
import br.com.mailanalyzer.domain.TermVariation;
import br.com.mailanalyzer.fluxo.Fluxo;
import br.com.mailanalyzer.log.L;
import java.io.IOException;
import java.util.ArrayList;
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
    public static final String TAG = Base.class.getName();
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

    public static void LoadTermVariations(){
        L.d(TAG, "Iniciando Carregamento de variações de termos.");
        TermVariationDAO tdao = new TermVariationDAO();
        VARIACOES_DE_TERMOS = tdao.obterTodos();
        tdao.close();
        L.d(TAG, "Variações de termos carregadas com sucesso. Disponível em "+Base.class.getName()+".VARIACOES_DE_TERMOS como um List<TermVariation>");
    }
    
    private static List<ActiveReceiver> LISTA_ACTIVEs = null;
    public static List<ActiveReceiver> GET_ACTIVE_RECEIVERS(){
        if(LISTA_ACTIVEs==null){
            ActionActiveReceiver dao = new ActionActiveReceiver();
            LISTA_ACTIVEs = dao.showAll();
        }
        
        return LISTA_ACTIVEs;
    }


    public static final int RECEIVER_TYPE_EMAIL = 1;
    public static final int RECEIVER_TYPE_SMSC_GATEWAY = 2;

    //Tempo que todas as Threads irao dormir. Default 1 segundo.
    public static final int INTERVALO_SERVICO = 100;

    private static final String PROPERTIES_FILE = "/mailanalyzer.properties";
    public static Fluxo MAIN_FLUXO;
    public static boolean ACTIVE_RECEIVER;
    
    /**
     * Consulta mensagem a cada 10 segundos
     */
    public static long RANGE_CONSULT_MESSAGE = 10 * 1000;

    //NOME DOS CAMPOS DE COMPOSEFLOW
    public static final String FIELD_MESSAGE = "messageField";
    public static final String FIELD_ACTIVE_RECEIVER = "ActiveReceiverField";


    //NOME DOS FLUXOS
    public static final String ACTIVE_RECEIVER_FLOW = "activeReceiverFlow";
    public static final String INIT_SERVICE_RECEIVER_FLOW = "InitServiceReceiverFlow";
    public static final String TRATAR_MENSAGEM_FLOW = "TratarMensagemFlow";
    public static final String MAIN_FLOW = "mainFlow";
       
    
    public static final String FIELD_FILTRO_HTML = "filtroHtml_campos";
    public static final String FIELD_FILTRO_MINUSCULO = "filtroConverteMinusculo";
    public static final String FIELD_FILTRO_GIRIA = "Field_filtro_giria";
    public static final String FIELD_FILTRO_CARACTER = "Field_filtro_caracter";
    public static final String FIELD_FILTRO_ORTOGRAFIA = "Field_filtro_ortografia";
    public static final String FIELD_SAVE_MESSAGE = "Field_Save_Messagem_";
    public static final String MESSAGE_TEMP_ASSUNTO_IGNORADO = "MensagemTemporariaParaAssuntoIgnorado";
    public static List<TermVariation> VARIACOES_DE_TERMOS;
    public static final String TAG_PARA_IGNORAR_MENSAGEM = "[nobot]";
    public static SubjectNotFoundCommand NOT_FOUND_COMMAND;
    
    
    //Servicos de ActiveReceivers ativos no sistema
    public static ActiveReceiverService[] ACTIVE_SERVICES;
    
    
    public static boolean RUNNING = true;
}
