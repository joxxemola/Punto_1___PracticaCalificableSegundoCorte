package model;

/**
 * Subclase que representa al delegado / capitán de un equipo nacional
 * en las pruebas de equipo del Mundial de Pista.
 */
public class CiclistaEquipo extends Competidor {

    // ─── Atributos propios ────────────────────────────────────────────────
    private String nombreEquipo;
    private int    numIntegrantes;
    private String ultimoResultado;   // "1er lugar", "Eliminado en semifinal", etc.

    // ─── Constructores ────────────────────────────────────────────────────

    public CiclistaEquipo() {
        super();
    }

    public CiclistaEquipo(String nombre, int edad, String pais,
                          int rankingMundial, double estatura, double peso,
                          String nombreEquipo, int numIntegrantes) {
        super(nombre, edad, pais, rankingMundial, estatura, peso);
        this.nombreEquipo    = nombreEquipo;
        this.numIntegrantes  = numIntegrantes;
        this.ultimoResultado = "Sin resultado";
    }

    // ─── Getters y Setters ────────────────────────────────────────────────

    public String getNombreEquipo()              { return nombreEquipo; }
    public void   setNombreEquipo(String n)      { this.nombreEquipo = n; }

    public int  getNumIntegrantes()              { return numIntegrantes; }
    public void setNumIntegrantes(int n)         { this.numIntegrantes = n; }

    public String getUltimoResultado()           { return ultimoResultado; }
    public void   setUltimoResultado(String r)   { this.ultimoResultado = r; }

    // ─── Métodos con SOBRECARGA ───────────────────────────────────────────

    /**
     * Sobrecarga 1: registra el resultado del equipo con descripción.
     *
     * @param resultado descripción del resultado (ej. "Medalla de Oro")
     */
    public void registrarResultado(String resultado) {
        this.ultimoResultado = resultado;
    }

    /**
     * Sobrecarga 2: registra el resultado con posición y genera un mensaje.
     * Contiene ESTRUCTURA ANIDADA (for + if anidados).
     *
     * @param resultado  descripción del resultado
     * @param posicion   lugar obtenido (1, 2, 3, ...)
     * @return           mensaje formateado con historial de posición
     */
    public String registrarResultado(String resultado, int posicion) {
        this.ultimoResultado = resultado;

        // Estructura anidada: construcción de historial con for + if anidado
        StringBuilder historial = new StringBuilder();
        String[] podio = {"🥇 Primer lugar", "🥈 Segundo lugar", "🥉 Tercer lugar"};

        for (int i = 1; i <= posicion; i++) {
            if (i < posicion) {
                // posiciones anteriores al resultado actual
                historial.append(String.format("  Ronda %d: En competencia%n", i));
            } else {
                // posición final
                if (posicion <= 3 && posicion >= 1) {
                    historial.append(String.format("  Posición final: %s%n", podio[posicion - 1]));
                } else {
                    historial.append(String.format("  Posición final: %d°%n", posicion));
                }
            }
        }

        return String.format("Equipo '%s' - %s%nHistorial:%n%s",
                             nombreEquipo, resultado, historial.toString());
    }

    // ─── toString con SOBREESCRITURA y uso de super ───────────────────────

    @Override
    public String toString() {
        return super.toString() +
               String.format(" | CiclistaEquipo{equipo='%s', integrantes=%d, resultado='%s'}",
                             nombreEquipo, numIntegrantes, ultimoResultado);
    }
}