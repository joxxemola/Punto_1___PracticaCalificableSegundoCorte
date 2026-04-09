package modelo;

import java.util.ArrayList;
import java.util.List;

public class Equipo {
    private String nombre;
    private String pais;
    private List<Competidor> competidores;

    // Constructor
    public Equipo(String nombre, String pais) {
        this.nombre = nombre;
        this.pais = pais;
        this.competidores = new ArrayList<>();
    }

    // Método del diagrama
    public void agregarCompetidor(Competidor c) {
        if (c != null) {
            competidores.add(c);
        }
    }

    // Método del diagrama
    public String obtenerDatosEquipo() {
        StringBuilder sb = new StringBuilder();
        sb.append("EQUIPO: ").append(nombre).append(" (").append(pais).append(")\n");
        sb.append("  Competidores: ").append(competidores.size()).append("\n");
        for (int i = 0; i < competidores.size(); i++) {
            sb.append("    ").append(i + 1).append(". ").append(competidores.get(i).obtenerDatos()).append("\n");
        }
        return sb.toString();
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }
    public List<Competidor> getCompetidores() { return competidores; }
}