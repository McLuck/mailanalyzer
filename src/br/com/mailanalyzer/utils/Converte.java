package br.com.mailanalyzer.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 29-04-2011
 *
 */
public class Converte {

    /**
     * Nao pode ser instanciada
     */
    protected Converte() {
        super();
    }
    java.util.Date data = new java.util.Date();
    private static String formato = "dd/MM/yyyy";
    private static String formatoSQL = "yyyy-MM-dd";
    private static SimpleDateFormat formatter = new SimpleDateFormat(formato);
    private static SimpleDateFormat formatterSQL = new SimpleDateFormat(formatoSQL);

    /**
     * Converte milisegundos de java.util.Date.getTime() para dias <br/>
     * Resultado = milisegundos/24/60/60/1000
     * @param milisegundos Milisegundos que a ser convertido
     * @return Um numero de dias no formato Integer
     */
    public static Integer MilisegundosToDias(Long milisegundos) {
        Integer res = 0;
        res = Integer.parseInt(Long.toString((milisegundos) / 24 / 60 / 60 / 1000));
        return res;
    }

    /**
     * Converte um numero de dias para long (para ser usado em java.util.Date.setTime(long);<br/>
     * Resultado=dias*24*60*60*1000
     * @param dias Numero de dias a ser convertido
     * @return Um numero do tipo long
     */
    public static Long DiastoMilisegundos(Integer dias) {
        Long diass = Long.parseLong(Integer.toString(dias));
        Long multp = Long.parseLong(Integer.toString(24 * 60 * 60 * 1000));
        return diass * multp;
    }

    public static String DateToStringTimer(Long milisegundos) {
        String horas = "";
        if (milisegundos == 0) {
            horas = " - ";
        } else {
            try {
                java.util.Date data = new java.util.Date();
                data.setTime(milisegundos);
                SimpleDateFormat ddt = new SimpleDateFormat("HH:mm");
                horas = ddt.format(data);
            } catch (Exception ex) {
                horas = " - ";
            }
        }
        return horas;
    }

    /**
     * Converte uma data do tipo java.util.Date para String formato banco de dados (yyyy-MM-dd)
     * @param data Um objeto nao nulo de java.util.Date
     * @return Uma String com a data formatada
     */
    public static String ToStringDataSql(java.util.Date data) {
        String datas = formatterSQL.format(data);
        return datas;
    }

    /**
     * Converte um objeto do tipo java.util.Date para uma String no formato (dd/MM/yyyy)
     * @param data Objeto do tipo java.util.Date nao nulo
     * @return Uma String com a data formatada
     */
    public static String ToStringDataVisual(java.util.Date data) {
        String datas = formatter.format(data);
        return datas;
    }

    /**
     * Converte um numero do tipo long em data numa representacao em String no formato (dd/MM/yyyy)
     * @param longg Um numero long para ser convertido
     * @return Uma String com a data formatada
     */
    public static String ToStringDataVisual(long longg) {
        java.util.Date data = new java.util.Date();
        String datas;
        if (longg == 0) {
            datas = "  ---  ";
        } else {
            try {
                data.setTime(longg);
                datas = formatter.format(data);
            } catch (Exception ex) {
                datas = "  ---  ";
            }
        }
        return datas;
    }

    /**
     * Converte uma String no formato yyyy-MM-dd para um objeto do tipo java.util.Date
     * @param data String nao nula com uma data no formato yyyy-MM-dd
     * @return Um objeto de java.util.Date com a data convertida
     */
    public static java.util.Date SqlToDate(String data) {
        DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date datas = null;
        try {
            datas = f.parse(data);
        } catch (ParseException ex) {
            Logger.getLogger(Converte.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datas;
    }

    /**
     * Converte uma String no formato dd/MM/yyyy para um objeto do tipo java.util.Date
     * @param ddMMyyyy String nao nula com uma data no formato dd/MM/yyyy
     * @return Um objeto de java.util.Date com a data Convertida
     * @throws Exception Possiveis excessoes geradas. Normalmente por entrada nula ou por entrada com formato incorreto
     */
    public static java.util.Date ToDate(String ddMMyyyy) throws Exception {
        DateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date datas = null;

        datas = f.parse(ddMMyyyy);

        return datas;
    }

    /**
     * Converte uma String no formato dd/MM/yyyy hh:mm para um objeto do tipo java.util.Date
     * @param ddMMyyyy_hhmm String nao nula com uma data no formato dd/MM/yyyy hh:mm
     * @return Um objeto de java.util.Date com a data Convertida
     * @throws Exception Possiveis excessoes geradas. Normalmente por entrada nula ou por entrada com formato incorreto
     */
    public static java.util.Date StringDtTimeToDate(String ddMMyyyy_hhmm) throws Exception {
        DateFormat f = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        java.util.Date datas = null;
        datas = f.parse(ddMMyyyy_hhmm);
        return datas;
    }

    /**
     * Converte uma String no formato yyyy/MM/dd para um objeto do tipo java.util.Date
     * @param data String nao nula com uma data no formato yyyy/MM/dd
     * @return Um objeto de java.util.Date com a data Convertida
     * @throws ParseException Possiveis excessoes geradas. Normalmente por entrada nula ou por entrada com formato incorreto
     */
    public static java.util.Date VisualToDate(String data) throws ParseException {
        DateFormat f = new SimpleDateFormat("yyyy/MM/dd");
        java.util.Date datas = null;
        datas = f.parse(data);
        return datas;
    }

    /**
     * Converte um objeto do tipo java.util.GregorianCalendar para uma String no formato (dd/MM/yyyy)
     * @param data Objeto do tipo java.util.GregorianCalendar nao nulo
     * @return Uma String com a data formatada
     */
    public static String ToStringDataVisual(GregorianCalendar data) {
        java.util.Date dtAux = new java.util.Date();
        dtAux = data.getTime();//DataAux.getTime();

        return Converte.ToStringDataVisual(dtAux);
    }

    /**
     * Converte um objeto do tipo java.util.GregorianCalendar para um objeto do tipo java.util.Date
     * @param data Objeto do tipo java.util.GregorianCalendar nao nulo
     * @return Um objeto do tipo java.util.Date convertido
     */
    public static java.util.Date GregorianToDate(GregorianCalendar data) {
        java.util.Date dtAux = new java.util.Date();
        dtAux = data.getTime();//DataAux.getTime();
        return dtAux;
    }

    /**
     * Converte um objeto do tipo java.util.Date para um objeto do tipo java.util.GregorianCalendar
     * @param data Um objeto de java.util.Date nao nulo
     * @return Um objeto do tipo java.util.GregorianCalendar convertido
     */
    public static GregorianCalendar DateToGregorian(java.util.Date data) {
        GregorianCalendar dg = new GregorianCalendar();
        dg.setTime(data);
        return dg;
    }

    /**
     * Converte uma String para double.<br/>
     * Exemplo: x=245,67 => double xc=245.67 <br/>
     * Usado para substituir virgulas e previnir erros em Strings que precisam ser convertidas para Double.
     * @param x Uma String nao nula com um valor numerico a ser convertido.
     * @return Um objeto do tipo Double convertido
     */
    public static Double StringToDouble(String x) {
        Double valor = 0.0;
        int tam = x.length();
        String xx = null;
        String valors = "";
        for (int i = 0; i < tam; i++) {
            xx = x.substring(i, i + 1);
            if (xx.equals(",")) {
                xx = ".";
            }
            valors += xx;
        }
        valor = Double.parseDouble(valors);
        return valor;
    }

    /**
     * Converte um objeto do tipo Double para String numa representacao com virgula e 2 casas decimais<br/>
     * Exemplo: x=245.3 => String xc = 245,30
     * @param x Um objeto do tipo Double nao nulo
     * @return Uma String com o valor convertido
     */
    public static String DoubleToString(Double x) {
        String valor = x.toString();
        int tam = valor.length();
        int cont = 0;
        String xx = null;
        String valors = "";
        boolean t = false;
        int v = 0;
        for (int i = 0; i < tam; i++) {
            xx = valor.substring(i, i + 1);
            if (xx.equals(".")) {
                xx = ",";
                cont = 1;
                t = true;
            }
            if (cont > 0) {
                cont++;
            }
            if (!t) {
                valors += xx;
            } else {
                if (v <= 2) {
                    v++;
                    valors += xx;
                }
            }
        }
        if (cont == 3) {
            valors += "0";
        }
        return valors;
    }

}
