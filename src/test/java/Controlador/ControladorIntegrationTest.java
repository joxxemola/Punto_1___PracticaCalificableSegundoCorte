package controller;

import model.Competencia;
import model.CiclistaPista;
import model.CiclistaEquipo;
import model.Competidor;
import view.Vista;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests de INTEGRACIÓN – verifican que Controlador, Modelo y Vista
 * trabajen correctamente en conjunto.
 */
class ControladorIntegrationTest {

    private Controlador  controlador;
    private Competencia  competencia;
    private CiclistaPista cp;
    private CiclistaEquipo ce;

    @BeforeEach
    void setUp() {
        competencia = new Competencia("Keirin Masculino", "2025-10-06", "Individual");
        Vista vista = new Vista();
        controlador = new Controlador(competencia, vista);

        cp = new CiclistaPista(
            "Lena Schulz", 28, "Alemania",
            1, 1.72, 65.5, "Keirin", 14
        );
        ce = new CiclistaEquipo(
            "Tom Dupont", 27, "Francia",
            2, 1.78, 71.0, "Équipe de France Piste", 4
        );
    }

    // ─── Flujo completo: registro y consulta ──────────────────────────────

    @Test
    void testFlujoRegistroYConsulta() {
        controlador.agregarCompetidor(cp);
        controlador.agregarCompetidor(ce);

        assertEquals(2, competencia.totalCompetidores());

        Competidor encontrado = competencia.buscarCompetidor("Lena Schulz");
        assertNotNull(encontrado);
        assertInstanceOf(CiclistaPista.class, encontrado);
    }

    @Test
    void testAgregarCompetidorNulo_noRompeElSistema() {
        controlador.agregarCompetidor(null);
        assertEquals(0, competencia.totalCompetidores());
    }

    // ─── Flujo: calcular puntaje de CiclistaPista ─────────────────────────

    @Test
    void testFlujoCalcularPuntajePistaSimple() {
        controlador.agregarCompetidor(cp);
        int puntaje = controlador.calcularPuntajePista("Lena Schulz", 2);
        assertEquals(200, puntaje);
    }

    @Test
    void testFlujoCalcularPuntajePistaConCategoria() {
        controlador.agregarCompetidor(cp);
        int puntaje = controlador.calcularPuntajePistaCategoria("Lena Schulz", 1, "ORO");
        assertEquals(250, puntaje);
    }

    @Test
    void testFlujoCalcularPuntaje_competidorInexistente() {
        int puntaje = controlador.calcularPuntajePista("Fantasma", 3);
        assertEquals(0, puntaje);   // no debe lanzar excepción
    }

    @Test
    void testFlujoCalcularPuntaje_competidorTipoIncorrecto() {
        controlador.agregarCompetidor(ce);
        // ce es CiclistaEquipo, no CiclistaPista
        int puntaje = controlador.calcularPuntajePista("Tom Dupont", 2);
        assertEquals(0, puntaje);
    }

    // ─── Flujo: registrar resultado de CiclistaEquipo ─────────────────────

    @Test
    void testFlujoRegistrarResultadoEquipo() {
        controlador.agregarCompetidor(ce);
        String resumen = controlador.registrarResultadoEquipo("Tom Dupont", "Medalla de Plata", 2);
        assertNotNull(resumen);
        assertFalse(resumen.isEmpty());
        assertEquals("Medalla de Plata", ce.getUltimoResultado());
    }

    @Test
    void testFlujoRegistrarResultado_competidorInexistente() {
        String resumen = controlador.registrarResultadoEquipo("Fantasma", "Oro", 1);
        assertEquals("", resumen);  // no debe lanzar excepción
    }

    // ─── Flujo completo: actualización de ranking ─────────────────────────

    @Test
    void testFlujoActualizarRankingIntegrado() {
        controlador.agregarCompetidor(cp);
        // Lena: ranking=1, puntos=0 → sin cambio (ya está en 1)
        controlador.actualizarRanking("Lena Schulz", 0);
        assertEquals(1, cp.getRankingMundial());

        // Puntos que mejorarían su ranking (ya en 1, debe quedar en 1)
        controlador.actualizarRanking("Lena Schulz", 100);
        assertEquals(1, cp.getRankingMundial());
    }

    @Test
    void testFlujoActualizarRankingConMejora() {
        controlador.agregarCompetidor(ce);
        // Tom: ranking=2, puntos=10 → mejora=1 → nuevo=1
        controlador.actualizarRanking("Tom Dupont", 10);
        assertEquals(1, ce.getRankingMundial());
    }

    @Test
    void testFlujoActualizarRanking_competidorInexistente() {
        // No debe lanzar NullPointerException
        assertDoesNotThrow(() ->
            controlador.actualizarRanking("Nadie", 100)
        );
    }

    // ─── Flujo: polimorfismo toString ─────────────────────────────────────

    @Test
    void testToStringPolimorficoCiclistaPista() {
        String s = cp.toString();
        // debe contener info del padre (gracias a super.toString())
        assertTrue(s.contains("Lena Schulz"));
        assertTrue(s.contains("Alemania"));
        // y la info propia
        assertTrue(s.contains("Keirin"));
        assertTrue(s.contains("CiclistaPista"));
    }

    @Test
    void testToStringPolimorficoCiclistaEquipo() {
        String s = ce.toString();
        assertTrue(s.contains("Tom Dupont"));
        assertTrue(s.contains("Francia"));
        assertTrue(s.contains("Équipe de France Piste"));
        assertTrue(s.contains("CiclistaEquipo"));
    }

    // ─── Flujo completo end-to-end ────────────────────────────────────────

    @Test
    void testFlujoEndToEnd() {
        // 1. Registrar competidores
        controlador.agregarCompetidor(cp);
        controlador.agregarCompetidor(ce);
        assertEquals(2, competencia.totalCompetidores());

        // 2. Calcular puntaje y actualizar ranking de CiclistaPista
        int puntaje = controlador.calcularPuntajePistaCategoria("Lena Schulz", 2, "ORO");
        assertEquals(600, puntaje);
        controlador.actualizarRanking("Lena Schulz", puntaje);
        assertEquals(1, cp.getRankingMundial()); // ya era 1, no puede bajar

        // 3. Registrar resultado de CiclistaEquipo
        String resumen = controlador.registrarResultadoEquipo("Tom Dupont", "Medalla de Oro", 1);
        assertNotNull(resumen);
        assertEquals("Medalla de Oro", ce.getUltimoResultado());

        // 4. Actualizar ranking de CiclistaEquipo
        controlador.actualizarRanking("Tom Dupont", 150);
        // ranking 2 - (150/10)=15 → max(1, -13) = 1
        assertEquals(1, ce.getRankingMundial());

        // 5. Verificar toString final incluye cambios
        assertTrue(cp.toString().contains("Lena Schulz"));
        assertTrue(ce.toString().contains("Medalla de Oro"));
    }
}