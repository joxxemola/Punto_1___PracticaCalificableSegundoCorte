import modelo.*;
import vista.VistaCompetencia;
import controlador.ControladorCompetencia;

public class Main {
    public static void main(String[] args) {
        // Crear competencia
        Competencia mundial = new Competencia("Mundial de Ciclismo de Pista - Cali 2024");
        
        // Crear equipos
        Equipo equipoCol = new Equipo("Selección Colombia", "Colombia");
        Equipo equipoNed = new Equipo("Selección Países Bajos", "Países Bajos");
        
        // Crear competidores (subclases)
        Velocista fabian = new Velocista("Fabián Puerta", 32, "Colombia", 5, 1.78, 78.5, 9.85);
        Velocista harrie = new Velocista("Harrie Lavreysen", 27, "Países Bajos", 1, 1.82, 82.0, 9.65);
        Fondista fernando = new Fondista("Fernando Gaviria", 29, "Colombia", 8, 1.80, 75.2, 45);
        Fondista yoeri = new Fondista("Yoeri Havik", 33, "Países Bajos", 3, 1.76, 74.5, 50);
        
        // Actualizar ranking con SOBRECARGA de métodos
        fabian.actualizarRanking(150);                      // método 1
        harrie.actualizarRanking(200, true);               // método 2 (con bono + estructura anidada)
        fernando.actualizarRanking(120);
        yoeri.actualizarRanking(180, false);
        
        // Agregar competidores a equipos
        equipoCol.agregarCompetidor(fabian);
        equipoCol.agregarCompetidor(fernando);
        equipoNed.agregarCompetidor(harrie);
        equipoNed.agregarCompetidor(yoeri);
        
        // Agregar equipos a competencia
        mundial.agregarEquipo(equipoCol);
        mundial.agregarEquipo(equipoNed);
        
        // MVC: Controlador + Vista
        VistaCompetencia vista = new VistaCompetencia();
        ControladorCompetencia controlador = new ControladorCompetencia(mundial, vista);
        
        // Mostrar reporte con los datos (usando SOBREESCRITURA en toString)
        System.out.println("\n" + "=".repeat(80));
        System.out.println("DESPLIEGUE DE DATOS DE COMPETIDORES (usando toString sobrescrito):");
        System.out.println("=".repeat(80));
        
        for (Competidor c : equipoCol.getCompetidores()) {
            System.out.println(c.toString());  // Polimorfismo + sobreescritura
        }
        for (Competidor c : equipoNed.getCompetidores()) {
            System.out.println(c.toString());
        }
        
        System.out.println("\n" + "=".repeat(80));
        controlador.ejecutar();
    }
}