package sicis.manipuladores;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Manipulador implements Serializable{

	private static final long serialVersionUID = 1L;

	public String inserirHeader(String opcao) {

        switch (opcao) {
            case "cadastrar bairro":
                return "Deseja cadastrar esse bairro ?";
            case "cadastrar cidade":
                return "Deseja cadastrar essa cidade ?";
            case "cadastrar logradouro":
                return "Deseja cadastrar esse logradouro ?";            
          
        }
        return "Deseja realizar o cadastro";
    }
    
    public String padronizarData(Date date) {

        Calendar calendar;
        if (date != null) {
            calendar = convertDatetoCalendar(date);
            return Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)) + "/"
                    + Integer.toString((calendar.get(Calendar.MONTH)) + 1) + "/"
                    + Integer.toString(calendar.get(Calendar.YEAR));
        }
        return "";

    }
    
    private Calendar convertDatetoCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
    
    public String desabilitarInputText(String parametro, String[] condicao){

        for (String condicaoInicial : condicao) {
            if (parametro.equals(condicaoInicial)){
                return "true";
        }
        }
        return "false";
        }
    
    public String selectMask(String parametroBusca) {
        switch (parametroBusca) {
            case "cli_CNS":
                return "999.9999.9999.9999";
            case "cli_CPF":
                return "999.999.999-99";
        }
        return "";
    }
}
