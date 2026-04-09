package model;

/**
 * Subclase que representa a un ciclista de pista individual
 * (sprint, keirin, persecución, velocidad por equipos, etc.)
 */
public class CiclistaPista extends Competidor {

    // ─── Atributos propios ────────────────────────────────────────────────
    private String disciplina;      // ej. "Sprint", "Keirin", "Persecución"
    private int    numeroBicicleta;
    private int    medallas;        // medallas acumuladas en el evento

    // ─── Constructores ────────────────────────────────────────────────────

    public CiclistaPista() {
        super();
    }

    public CiclistaPista(String nombre, int edad, String pais,
                         int rankingMundial, double estatura, double peso,
                         String disciplina, int numeroBicicleta) {
        super(nombre, edad, pais, rankingMundial, estatura, peso);
        this.disciplina      = disciplina;
        this.numeroBicicleta = numeroBicicleta;
        this.medallas        = 0;
    }

    // ─── Getters y Setters ────────────────────────────────────────────────

    public String getDisciplina()           { return disciplina; }
    public void   setDisciplina(String d)   { this.disciplina = d; }

    public int  getNumeroBicicleta()        { return numeroBicicleta; }
    public void setNumeroBicicleta(int n)   { this.numeroBicicleta = n; }

    public int  getMedallas()               { return medallas; }
    public void setMedallas(int m)          { this.medallas = m; }

    // ─── Métodos con SOBRECARGA ───────────────────────────────────────────

    /**
     * Sobrecarga 1: calcula el puntaje básico del ciclista.
     * Fórmula: medallas * 100 + posición bonus (0 puntos extra)
     *
     * @param medallas número de medallas obtenidas
     * @return puntaje calculado
     */
    public int calcularPuntaje(int medallas) {
        this.medallas += medallas;
        return medallas * 100;
    }

    /**
     * Sobrecarga 2: calcula puntaje con factor por categoría.
     * Contiene ESTRUCTURA ANIDADA (switch dentro de if-else anidado).
     *
     * @param medallas  número de medallas obtenidas
     * @param categoria "ORO", "PLATA" o "BRONCE"
     * @return puntaje calculado según categoría
     */
    public int calcularPuntaje(int medallas, String categoria) {
        int puntajeBase = 0;

        if (medallas > 0) {
            if (categoria != null && !categoria.isEmpty()) {
                // Estructura anidada: switch dentro de if-else
                switch (categoria.toUpperCase()) {
                    case "ORO":
                        if (medallas >= 2) {
                            puntajeBase = medallas * 300;  // bonus doble campeón
                        } else {
                            puntajeBase = medallas * 250;
                        }
                        break;
                    case "PLATA":
                        if (medallas >= 2) {
                            puntajeBase = medallas * 180;
                        } else {
                            puntajeBase = medallas * 150;
                        }
                        break;
                    case "BRONCE":
                        if (medallas >= 2) {
                            puntajeBase = medallas * 120;
                        } else {
                            puntajeBase = medallas * 100;
                        }
                        break;
                    default:
                        puntajeBase = medallas * 50;  // participación
                        break;
                }
            } else {
                puntajeBase = medallas * 100;
            }
        }
        this.medallas += medallas;
        return puntajeBase;
    }

    // ─── toString con SOBREESCRITURA y uso de super ───────────────────────

    @Override
    public String toString() {
        return super.toString() +
               String.format(" | CiclistaPista{disciplina='%s', bicicleta=#%d, medallas=%d}",
                             disciplina, numeroBicicleta, medallas);
    }
}