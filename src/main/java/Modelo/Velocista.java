package modelo;

public class Velocista extends Competidor {
    private double mejorTiempo200m; // en segundos

    public Velocista(String nombre, int edad, String pais, int rankingMundial, double estatura, double peso, double mejorTiempo200m) {
        super(nombre, edad, pais, rankingMundial, estatura, peso);
        this.mejorTiempo200m = mejorTiempo200m;
    }

    @Override
    public String obtenerDatos() {
        return super.obtenerDatos() + String.format(" | Tipo: Velocista | Mejor 200m: %.2f s", mejorTiempo200m);
    }

    // Getter y Setter
    public double getMejorTiempo200m() { return mejorTiempo200m; }
    public void setMejorTiempo200m(double mejorTiempo200m) { this.mejorTiempo200m = mejorTiempo200m; }
}