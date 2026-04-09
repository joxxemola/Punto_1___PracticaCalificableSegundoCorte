package controller;

import model.Competencia;
import model.Competidor;
import model.CiclistaPista;
import model.CiclistaEquipo;
import view.Vista;

/**
 * Capa de Controlador – coordina Modelo y Vista.
 */
public class Controlador {

    private Competencia competencia;
    private Vista       vista;

    // ─── Constructor ──────────────────────────────────────────────────────

    public Controlador() {
        this.competencia = new Competencia("Sprint Masculino", "2025-10-05", "Individual");
        this.vista       = new Vista();
    }

    public Controlador(Competencia competencia, Vista vista) {
        this.competencia = competencia;
        this.vista       = vista;
    }

    // ─── Acciones del controlador ─────────────────────────────────────────

    /** Inicia la vista de bienvenida. */
    public void iniciar() {
        vista.mostrarBienvenida();
    }

    /** Agrega un competidor a la competencia y lo muestra. */
    public void agregarCompetidor(Competidor c) {
        if (c == null) {
            vista.mostrarError("Competidor nulo, no se puede agregar.");
            return;
        }
        competencia.agregarCompetidor(c);
        vista.mostrarMensaje("Competidor registrado: " + c.getNombre());
    }

    /** Muestra todos los datos de la competencia. */
    public void mostrarCompetencia() {
        vista.mostrarCompetencia(competencia);
    }

    /**
     * Actualiza el ranking de un competidor según sus puntos obtenidos.
     *
     * @param nombreCompetidor nombre del competidor
     * @param puntos           puntos obtenidos
     */
    public void actualizarRanking(String nombreCompetidor, int puntos) {
        Competidor c = competencia.buscarCompetidor(nombreCompetidor);
        if (c != null) {
            int rankingAnterior = c.getRankingMundial();
            c.actualizarRanking(puntos);
            vista.mostrarMensaje(String.format(
                "Ranking de %s actualizado: %d → %d",
                c.getNombre(), rankingAnterior, c.getRankingMundial()));
        } else {
            vista.mostrarError("Competidor no encontrado: " + nombreCompetidor);
        }
    }

    /** Calcula puntaje de un CiclistaPista con sobrecarga simple. */
    public int calcularPuntajePista(String nombre, int medallas) {
        Competidor c = competencia.buscarCompetidor(nombre);
        if (c instanceof CiclistaPista) {
            int puntaje = ((CiclistaPista) c).calcularPuntaje(medallas);
            vista.mostrarMensaje(nombre + " – puntaje básico: " + puntaje);
            return puntaje;
        }
        vista.mostrarError("No es un CiclistaPista: " + nombre);
        return 0;
    }

    /** Calcula puntaje de un CiclistaPista con sobrecarga por categoría. */
    public int calcularPuntajePistaCategoria(String nombre, int medallas, String categoria) {
        Competidor c = competencia.buscarCompetidor(nombre);
        if (c instanceof CiclistaPista) {
            int puntaje = ((CiclistaPista) c).calcularPuntaje(medallas, categoria);
            vista.mostrarMensaje(nombre + " – puntaje [" + categoria + "]: " + puntaje);
            return puntaje;
        }
        vista.mostrarError("No es un CiclistaPista: " + nombre);
        return 0;
    }

    /** Registra resultado de CiclistaEquipo con sobrecarga completa. */
    public String registrarResultadoEquipo(String nombre, String resultado, int posicion) {
        Competidor c = competencia.buscarCompetidor(nombre);
        if (c instanceof CiclistaEquipo) {
            String resumen = ((CiclistaEquipo) c).registrarResultado(resultado, posicion);
            vista.mostrarMensaje(resumen);
            return resumen;
        }
        vista.mostrarError("No es un CiclistaEquipo: " + nombre);
        return "";
    }

    // ─── Getters ──────────────────────────────────────────────────────────

    public Competencia getCompetencia() { return competencia; }
    public Vista       getVista()       { return vista; }
}