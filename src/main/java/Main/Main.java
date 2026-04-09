package Main;

import model.CiclistaEquipo;
import model.CiclistaPista;
import model.Competidor;
import controller.Controlador;

/**
 * Clase principal del sistema de gestión del
 * Mundial de Ciclismo de Pista – Cali 2025.
 *
 * Requisitos cubiertos:
 *  ✔ Almacena objetos de las 2 subclases (CiclistaPista y CiclistaEquipo).
 *  ✔ Invoca toString() de cada subclase (con super).
 *  ✔ Usa el Controlador (MVC).
 */
public class Main {

    public static void main(String[] args) {

        Controlador controlador = new Controlador();
        controlador.iniciar();

        // ── 1. Crear objetos de CiclistaPista ──────────────────────────────
        CiclistaPista cp1 = new CiclistaPista(
            "Martina García",  25, "Colombia",
            3, 1.68, 62.0,
            "Sprint", 7
        );

        CiclistaPista cp2 = new CiclistaPista(
            "Lena Schulz", 28, "Alemania",
            1, 1.72, 65.5,
            "Keirin", 14
        );

        // ── 2. Crear objetos de CiclistaEquipo ────────────────────────────
        CiclistaEquipo ce1 = new CiclistaEquipo(
            "Carlos Ramírez", 30, "Colombia",
            5, 1.80, 74.0,
            "Team Colombia Track", 4
        );

        CiclistaEquipo ce2 = new CiclistaEquipo(
            "Tom Dupont", 27, "Francia",
            2, 1.78, 71.0,
            "Équipe de France Piste", 4
        );

        // ── 3. Registrar todos los competidores ───────────────────────────
        controlador.agregarCompetidor(cp1);
        controlador.agregarCompetidor(cp2);
        controlador.agregarCompetidor(ce1);
        controlador.agregarCompetidor(ce2);

        // ── 4. Mostrar competencia completa ───────────────────────────────
        controlador.mostrarCompetencia();

        // ── 5. Desplegar toString de cada objeto (sobreescritura + super) ─
        System.out.println("\n=== toString de los competidores ===");
        Competidor[] todos = { cp1, cp2, ce1, ce2 };
        for (Competidor c : todos) {
            // Polimorfismo: invoca el toString de la subclase correspondiente
            System.out.println(c.toString());
            System.out.println();
        }

        // ── 6. Demostrar métodos sobrecargados de CiclistaPista ───────────
        System.out.println("=== Sobrecarga – CiclistaPista ===");
        controlador.calcularPuntajePista("Martina García", 2);
        controlador.calcularPuntajePistaCategoria("Martina García", 2, "ORO");
        controlador.calcularPuntajePistaCategoria("Lena Schulz", 1, "PLATA");

        // ── 7. Demostrar métodos sobrecargados de CiclistaEquipo ──────────
        System.out.println("\n=== Sobrecarga – CiclistaEquipo ===");
        ce1.registrarResultado("Clasificado a semifinal");
        System.out.println("Resultado simple: " + ce1.getUltimoResultado());
        controlador.registrarResultadoEquipo("Carlos Ramírez", "Medalla de Oro", 1);
        controlador.registrarResultadoEquipo("Tom Dupont", "Medalla de Plata", 2);

        // ── 8. Actualizar rankings al final del evento ────────────────────
        System.out.println("\n=== Actualización de Rankings ===");
        controlador.actualizarRanking("Martina García", 250);
        controlador.actualizarRanking("Lena Schulz", 100);
        controlador.actualizarRanking("Carlos Ramírez", 200);
        controlador.actualizarRanking("Tom Dupont", 150);

        // ── 9. Estado final ───────────────────────────────────────────────
        System.out.println("\n=== Estado final de los competidores ===");
        for (Competidor c : todos) {
            System.out.println(c.toString());
            System.out.println();
        }
    }
}