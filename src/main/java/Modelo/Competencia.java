package modelo;

import java.util.ArrayList;
import java.util.List;

public class Competencia {
    private String nombreEvento;
    private List<Equipo> equipos;

    // Constructor
    public Competencia(String nombreEvento) {
        this.nombreEvento = nombreEvento;
        this.equipos = new ArrayList<>();
    }

    // Método del diagrama
    public void agregarEquipo(Equipo e) {
        if (e != null) {
            equipos.add(e);
        }
    }

    // Método del diagrama
    public String generarReporte() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("\n");
        reporte.append("========================================\n");
        reporte.append("   ").append(nombreEvento).append("\n");
        reporte.append("========================================\n");
        reporte.append("Equipos participantes: ").append(equipos.size()).append("\n\n");
        
        for (Equipo e : equipos) {
            reporte.append(e.obtenerDatosEquipo()).append("\n");
        }
        
        reporte.append("Sede: Velódromo Alcides Nieto Patiño - Cali, Colombia\n");
        return reporte.toString();
    }

    // Getters y Setters
    public String getNombreEvento() { return nombreEvento; }
    public void setNombreEvento(String nombreEvento) { this.nombreEvento = nombreEvento; }
    public List<Equipo> getEquipos() { return equipos; }
}