package modelo;

import java.util.ArrayList;
import java.util.List;

public class Competencia {
    private String nombreEvento;
    private List<Equipo> equipos;

    public Competencia(String nombreEvento) {
        this.nombreEvento = nombreEvento;
        this.equipos = new ArrayList<>();
    }

    public void agregarEquipo(Equipo e) {
        equipos.add(e);
    }

    public String generarReporte() {
        StringBuilder sb = new StringBuilder();
        sb.append("=".repeat(60)).append("\n");
        sb.append("REPORTE DE COMPETENCIA\n");
        sb.append("Evento: ").append(nombreEvento).append("\n");
        sb.append("=".repeat(60)).append("\n\n");
        
        int totalCompetidores = 0;
        for (Equipo equipo : equipos) {
            sb.append(equipo.obtenerDatosEquipo()).append("\n");
            totalCompetidores += equipo.getCompetidores().size();
        }
        
        sb.append("-".repeat(60)).append("\n");
        sb.append("Total equipos: ").append(equipos.size()).append("\n");
        sb.append("Total competidores: ").append(totalCompetidores).append("\n");
        
        return sb.toString();
    }
}