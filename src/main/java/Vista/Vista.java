package view;

import model.Competencia;
import model.Competidor;

/**
 * Capa de Vista – responsable de mostrar información por consola.
 */
public class Vista {

    // ─── Métodos de despliegue ────────────────────────────────────────────

    public void mostrarBienvenida() {
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║  MUNDIAL DE CICLISMO DE PISTA – Cali 2025        ║");
        System.out.println("║  Velódromo Alcides Nieto Patiño                  ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
    }

    public void mostrarCompetidor(Competidor c) {
        System.out.println("  → " + c.toString());
    }

    public void mostrarCompetencia(Competencia comp) {
        System.out.println("\n┌─ " + comp.toString());
        System.out.println("│  Competidores inscritos:");
        for (Competidor c : comp.getCompetidores()) {
            System.out.println("│    • " + c.toString());
        }
        System.out.println("└" + "─".repeat(60));
    }

    public void mostrarMensaje(String msg) {
        System.out.println("[INFO] " + msg);
    }

    public void mostrarError(String err) {
        System.err.println("[ERROR] " + err);
    }
}