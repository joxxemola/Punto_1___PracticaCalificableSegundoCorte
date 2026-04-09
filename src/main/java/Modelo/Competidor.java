package modelo;

public class Competidor {
    protected String nombre;
    protected int edad;
    protected String pais;
    protected int rankingMundial;
    protected double estatura;
    protected double peso;
    protected int puntos;

    public Competidor(String nombre, int edad, String pais, int rankingMundial, double estatura, double peso) {
        this.nombre = nombre;
        this.edad = edad;
        this.pais = pais;
        this.rankingMundial = rankingMundial;
        this.estatura = estatura;
        this.peso = peso;
        this.puntos = 0;
    }

    // Sobrecarga de método 1: actualizar ranking con solo puntos
    public void actualizarRanking(int puntosObtenidos) {
        this.puntos += puntosObtenidos;
        // Lógica simple: cada 100 puntos mejora 1 posición en ranking
        this.rankingMundial = Math.max(1, rankingMundial - (puntosObtenidos / 100));
    }

    // Sobrecarga de método 2: actualizar ranking con puntos y bono especial (estructura anidada)
    public void actualizarRanking(int puntosObtenidos, boolean esFinalMundial) {
        int bono = 0;
        if (esFinalMundial) {
            // Estructura anidada: switch dentro de if
            switch (puntosObtenidos / 50) {
                case 0 -> bono = 5;
                case 1 -> bono = 10;
                case 2 -> bono = 15;
                default -> bono = 20;
            }
        }
        actualizarRanking(puntosObtenidos + bono);
    }

    // Método que será sobreescrito
    public String obtenerDatos() {
        return String.format("Nombre: %s | Edad: %d | País: %s | Ranking: %d | Estatura: %.2f m | Peso: %.2f kg | Puntos: %d",
                nombre, edad, pais, rankingMundial, estatura, peso, puntos);
    }

    @Override
    public String toString() {
        return obtenerDatos();
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }
    public int getRankingMundial() { return rankingMundial; }
    public void setRankingMundial(int rankingMundial) { this.rankingMundial = rankingMundial; }
    public double getEstatura() { return estatura; }
    public void setEstatura(double estatura) { this.estatura = estatura; }
    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }
    public int getPuntos() { return puntos; }
    public void setPuntos(int puntos) { this.puntos = puntos; }
}