package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa una competencia dentro del Mundial de Ciclismo de Pista.
 * Gestiona la lista de competidores inscritos.
 */
public class Competencia {

    // ─── Atributos ────────────────────────────────────────────────────────
    private String            nombre;
    private String            fecha;
    private String            tipo;           // "Individual" o "Equipo"
    private List<Competidor>  competidores;

    // ─── Constructores ────────────────────────────────────────────────────

    public Competencia() {
        this.competidores = new ArrayList<>();
    }

    public Competencia(String nombre, String fecha, String tipo) {
        this.nombre       = nombre;
        this.fecha        = fecha;
        this.tipo         = tipo;
        this.competidores = new ArrayList<>();
    }

    // ─── Getters y Setters ────────────────────────────────────────────────

    public String getNombre()          { return nombre; }
    public void   setNombre(String n)  { this.nombre = n; }

    public String getFecha()           { return fecha; }
    public void   setFecha(String f)   { this.fecha = f; }

    public String getTipo()            { return tipo; }
    public void   setTipo(String t)    { this.tipo = t; }

    public List<Competidor> getCompetidores() { return competidores; }

    // ─── Métodos de negocio ───────────────────────────────────────────────

    /** Agrega un competidor a la competencia. */
    public void agregarCompetidor(Competidor c) {
        if (c != null) {
            competidores.add(c);
        }
    }

    /** Elimina un competidor por nombre. */
    public boolean eliminarCompetidor(String nombre) {
        return competidores.removeIf(c -> c.getNombre().equalsIgnoreCase(nombre));
    }

    /** Busca un competidor por nombre (retorna null si no existe). */
    public Competidor buscarCompetidor(String nombre) {
        return competidores.stream()
                           .filter(c -> c.getNombre().equalsIgnoreCase(nombre))
                           .findFirst()
                           .orElse(null);
    }

    /** Retorna el número de competidores inscritos. */
    public int totalCompetidores() {
        return competidores.size();
    }

    // ─── toString ─────────────────────────────────────────────────────────

    @Override
    public String toString() {
        return String.format(
            "Competencia{nombre='%s', fecha='%s', tipo='%s', inscritos=%d}",
            nombre, fecha, tipo, competidores.size());
    }
}