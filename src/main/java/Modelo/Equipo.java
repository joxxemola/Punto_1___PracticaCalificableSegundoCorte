package modelo;

import java.util.ArrayList;
import java.util.List;

public class Equipo {
    private String nombre;
    private String pais;
    private List<Competidor> competidores;

    public Equipo(String nombre, String pais) {
        this.nombre = nombre;
        this.pais = pais;
        this.competidores = new ArrayList<>();
    }

    public void agregarCompetidor(Competidor c) {
        competidores.add(c);
    }

    public String obtenerDatosEquipo() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Equipo: %s | País: %s\n", nombre, pais));
        for (Competidor c : competidores) {
            sb.append("  - ").append(c.obtenerDatos()).append("\n");
        }
        return sb.toString();
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getPais() { return pais; }
    public List<Competidor> getCompetidores() { return competidores; }
}