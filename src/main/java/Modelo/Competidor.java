package model;

/**
 * Clase base que representa a un competidor del Mundial de Ciclismo de Pista.
 */
public class Competidor {

    // ─── Atributos ───────────────────────────────────────────────────────────
    private String nombre;
    private int    edad;
    private String pais;
    private int    rankingMundial;
    private double estatura;   // metros
    private double peso;       // kg

    // ─── Constructores ───────────────────────────────────────────────────────

    /** Constructor por defecto. */
    public Competidor() {}

    /** Constructor completo. */
    public Competidor(String nombre, int edad, String pais,
                      int rankingMundial, double estatura, double peso) {
        this.nombre        = nombre;
        this.edad          = edad;
        this.pais          = pais;
        this.rankingMundial = rankingMundial;
        this.estatura      = estatura;
        this.peso          = peso;
    }

    // ─── Getters y Setters ───────────────────────────────────────────────────

    public String getNombre()        { return nombre; }
    public void   setNombre(String n){ this.nombre = n; }

    public int  getEdad()            { return edad; }
    public void setEdad(int e)       { this.edad = e; }

    public String getPais()          { return pais; }
    public void   setPais(String p)  { this.pais = p; }

    public int  getRankingMundial()           { return rankingMundial; }
    public void setRankingMundial(int r)      { this.rankingMundial = r; }

    public double getEstatura()              { return estatura; }
    public void   setEstatura(double e)      { this.estatura = e; }

    public double getPeso()                  { return peso; }
    public void   setPeso(double p)          { this.peso = p; }

    // ─── Métodos de negocio ──────────────────────────────────────────────────

    /**
     * Actualiza el ranking del competidor al final del evento.
     * Fórmula: nuevo ranking = ranking actual - (puntos / 10)
     * Si el resultado es menor a 1, queda en 1 (primer lugar).
     *
     * @param puntos puntos obtenidos en el evento
     */
    public void actualizarRanking(int puntos) {
        int mejora = puntos / 10;
        this.rankingMundial = Math.max(1, this.rankingMundial - mejora);
    }

    // ─── toString ───────────────────────────────────────────────────────────

    @Override
    public String toString() {
        return String.format(
            "Competidor{nombre='%s', edad=%d, pais='%s', " +
            "rankingMundial=%d, estatura=%.2f m, peso=%.1f kg}",
            nombre, edad, pais, rankingMundial, estatura, peso);
    }
}