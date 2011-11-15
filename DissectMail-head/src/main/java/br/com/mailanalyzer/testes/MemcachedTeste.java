/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.testes;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import java.util.GregorianCalendar;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.spy.memcached.DefaultConnectionFactory;
import net.spy.memcached.MemcachedClient;

/**
 *
 * @author McLuck
 */
public class MemcachedTeste {
    protected static MemCachedClient mcc = new MemCachedClient();
    private static final String KEY = "chave_";
    public static void carregarBesteiras(){
        System.err.println("Iniciando carregamento de besteiras...");
        long ini = GregorianCalendar.getInstance().getTimeInMillis();
        for(long i=0;i<1000000;i++){
            mcc.set(KEY.concat(String.valueOf(i)), String.valueOf((Math.random() * i)));
        }

        ini = (GregorianCalendar.getInstance().getTimeInMillis()- ini)/1000;
        System.err.println("Besteiras carregadas em "+ini+" s");
    }
    public static void main(String[] args) {
//        init();
//        carregarBesteiras();
//        String input = "";
//
//        while(input!=null){
//            input = JOptionPane.showInputDialog("Informe a posicao:");
//            if(input!=null){
//                try{
//                    long ini = GregorianCalendar.getInstance().getTimeInMillis();
//                    String bar = (String) mcc.get( KEY.concat(input));
//                    ini = (GregorianCalendar.getInstance().getTimeInMillis()- ini)/1000;
//                    System.out.println(bar+"  -- EM "+ini+" segundos");
//                }catch(Exception e){
//                    e.printStackTrace();
//                }
//            }
//        }

        System.out.println(DefaultConnectionFactory.DEFAULT_OPERATION_TIMEOUT);
    }
    public static void init(){
        String[] servers = {"localhost:11211"};
        Integer[] weights = {3};
        SockIOPool pool = SockIOPool.getInstance();

        pool.setServers(servers);
        pool.setWeights(weights);

        pool.setInitConn(5);
        pool.setMinConn(5);
        pool.setMaxConn(250);
        pool.setMaxIdle(1000 * 60 * 60 * 6);

        // set some basic pool settings
        // 5 initial, 5 min, and 250 max conns
        // and set the max idle time for a conn
        // to 6 hours
        pool.setInitConn(5);
        pool.setMinConn(5);
        pool.setMaxConn(250);
        pool.setMaxIdle(1000 * 60 * 60 * 6);

        // set the sleep for the maint thread
        // it will wake up every x seconds and
        // maintain the pool size
        pool.setMaintSleep(30);

        // set some TCP settings
        // disable nagle
        // set the read timeout to 3 secs
        // and don?t set a connect timeout
        pool.setNagle(false);
        pool.setSocketTO(3000);
        pool.setSocketConnectTO(0);

        // initialize the connection pool
        pool.initialize();

        // lets set some compression on for the client
        // compress anything larger than 64k
//        mcc.setCompressEnable(false);
//        mcc.setCompressThreshold(64 * 1024);
    }
}
