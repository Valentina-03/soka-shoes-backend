package com.example.demo.negocio;

import java.util.Date;

public class SokaShoes {
    
    public Date convertirFecha(String date, String separador){

        String fecha = date.split(" ")[0];
        String horaC = date.split(" ")[1];

        String vectorFecha[] = fecha.split(separador);
        String anio = vectorFecha[0];
        String mes = vectorFecha[1];
        String dia = vectorFecha[2];

        String vectorHora[] = horaC.split("\\:");
        String hora = vectorHora[0];
        String minuto = vectorHora[1];
        String segundo = vectorHora[2];

        Date fechaCompleta=new Date();
        fechaCompleta.setDate(Integer.parseInt(dia));
        fechaCompleta.setMonth(Integer.parseInt(mes));
        fechaCompleta.setYear(Integer.parseInt(anio)-1900);
        fechaCompleta.setHours(Integer.parseInt(hora));
        fechaCompleta.setMinutes(Integer.parseInt(minuto));
        fechaCompleta.setSeconds(Integer.parseInt(segundo));

        return fechaCompleta;
    }

}
