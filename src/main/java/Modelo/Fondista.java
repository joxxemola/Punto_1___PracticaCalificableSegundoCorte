package modelo;

public class Fondista extends Competidor {
    private int resistenciaMaxima; // en minutos

    public Fondista(String nombre, int edad, String pais, int rankingMundial, double estatura, double peso, int resistenciaMaxima) {
        super(nombre, edad, pais, rankingMundial, estatura, peso);
        this.resistenciaMaxima = resistenciaMaxima;
    }

    @Override
    public String obtenerDatos() {
        return super.obtenerDatos() + String.format(" | Tipo: Fondista | Resistencia máxima: %d min", resistenciaMaxima);
    }

    // Getter y Setter
    public int getResistenciaMaxima() { return resistenciaMaxima; }
    public void setResistenciaMaxima(int resistenciaMaxima) { this.resistenciaMaxima = resistenciaMaxima; }
}