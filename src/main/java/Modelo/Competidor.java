package modelo;

public class Competidor {
    private String nombre;
    private int edad;
    private String pais;
    private int rankingMundial;
    private double estatura;
    private double peso;
    private int puntos;

    // Constructor
    public Competidor(String nombre, int edad, String pais, int rankingMundial, double estatura, double peso) {
        this.nombre = nombre;
        this.edad = edad;
        this.pais = pais;
        this.rankingMundial = rankingMundial;
        this.estatura = estatura;
        this.peso = peso;
        this.puntos = 0;
    }

    // Método original del diagrama
    public void actualizarRanking(int puntosObtenidos) {
        this.puntos += puntosObtenidos;
        this.rankingMundial -= (puntosObtenidos / 10);
        if (this.rankingMundial < 1) {
            this.rankingMundial = 1;
        }
    }

    // SOBRECARGA 1: actualizarRanking con dos parámetros
    public void actualizarRanking(int puntosObtenidos, boolean esTorneoMayor) {
        int factor = esTorneoMayor ? 2 : 1;
        actualizarRanking(puntosObtenidos * factor);
    }

    // SOBRECARGA 2 con ESTRUCTURA ANIDADA (clase interna local)
    public void actualizarRanking(int puntosObtenidos, String categoria, double coeficiente) {
        // Estructura anidada: clase interna local
        class CalculadorPuntos {
            private int puntosBase;
            
            public CalculadorPuntos(int puntos) {
                this.puntosBase = puntos;
            }
            
            public int calcularPuntosFinales() {
                int puntosFinales = (int)(puntosBase * coeficiente);
                if (categoria.equals("ELITE")) {
                    puntosFinales += 10;
                } else if (categoria.equals("JUNIOR")) {
                    puntosFinales += 5;
                }
                return puntosFinales;
            }
        }
        
        CalculadorPuntos calculador = new CalculadorPuntos(puntosObtenidos);
        int puntosFinales = calculador.calcularPuntosFinales();
        actualizarRanking(puntosFinales);
    }

    // Método del diagrama
    public String obtenerDatos() {
        return nombre + " | Edad: " + edad + " | País: " + pais + 
               " | Ranking Mundial: #" + rankingMundial + 
               " | Estatura: " + estatura + "m | Peso: " + peso + "kg | Puntos: " + puntos;
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

    // SOBREESCRITURA del método toString
    @Override
    public String toString() {
        return obtenerDatos();
    }
}