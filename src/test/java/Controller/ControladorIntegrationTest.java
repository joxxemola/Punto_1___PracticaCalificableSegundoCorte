package Controller;

import modelo.Competencia;
import modelo.CiclistaPista;
import modelo.CiclistaEquipo;
import modelo.Competidor;
import vista.VistaCompetencia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ControladorIntegrationTest {

    private Controlador   controlador;
    private Competencia   competencia;
    private CiclistaPista cp;
    private CiclistaEquipo ce;

    @BeforeEach
    void setUp() {
        competencia = new Competencia("Keirin Masculino", "2025-10-06", "Individual");
        controlador = new Controlador(competencia, new Vista());

        cp = new CiclistaPista(
            "Lena Schulz", 28, "Alemania",
            1, 1.72, 65.5, "Keirin", 14
        );
        ce = new CiclistaEquipo(
            "Tom Dupont", 27, "Francia",
            2, 1.78, 71.0, "Équipe de France Piste", 4
        );
    }

    // ─── Registro y consulta ──────────────────────────────────────────────

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

    // ─── Puntaje CiclistaPista ─────────────────────────────────────────────

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
        assertEquals(0, puntaje);
    }

    @Test
    void testFlujoCalcularPuntaje_tipoIncorrecto() {
        controlador.agregarCompetidor(ce);
        int puntaje = controlador.calcularPuntajePista("Tom Dupont", 2);
        assertEquals(0, puntaje);
    }

    // ─── Resultado CiclistaEquipo ──────────────────────────────────────────

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
        assertEquals("", resumen);
    }

    // ─── Actualización de ranking ──────────────────────────────────────────

    @Test
    void testFlujoActualizarRankingYaEnUno() {
        controlador.agregarCompetidor(cp);
        controlador.actualizarRanking("Lena Schulz", 100);
        assertEquals(1, cp.getRankingMundial());
    }

    @Test
    void testFlujoActualizarRankingConMejora() {
        controlador.agregarCompetidor(ce);
        // ranking=2, puntos=10 → mejora=1 → nuevo=1
        controlador.actualizarRanking("Tom Dupont", 10);
        assertEquals(1, ce.getRankingMundial());
    }

    @Test
    void testFlujoActualizarRanking_competidorInexistente() {
        assertDoesNotThrow(() -> controlador.actualizarRanking("Nadie", 100));
    }

    // ─── toString polimórfico ──────────────────────────────────────────────

    @Test
    void testToStringCiclistaPista() {
        String s = cp.toString();
        assertTrue(s.contains("Lena Schulz"));
        assertTrue(s.contains("Alemania"));
        assertTrue(s.contains("Keirin"));
        assertTrue(s.contains("CiclistaPista"));
    }

    @Test
    void testToStringCiclistaEquipo() {
        String s = ce.toString();
        assertTrue(s.contains("Tom Dupont"));
        assertTrue(s.contains("Francia"));
        assertTrue(s.contains("Équipe de France Piste"));
        assertTrue(s.contains("CiclistaEquipo"));
    }

    // ─── Flujo end-to-end ─────────────────────────────────────────────────

    @Test
    void testFlujoEndToEnd() {
        controlador.agregarCompetidor(cp);
        controlador.agregarCompetidor(ce);
        assertEquals(2, competencia.totalCompetidores());

        int puntaje = controlador.calcularPuntajePistaCategoria("Lena Schulz", 2, "ORO");
        assertEquals(600, puntaje);

        controlador.actualizarRanking("Lena Schulz", puntaje);
        assertEquals(1, cp.getRankingMundial());

        String resumen = controlador.registrarResultadoEquipo("Tom Dupont", "Medalla de Oro", 1);
        assertNotNull(resumen);
        assertEquals("Medalla de Oro", ce.getUltimoResultado());

        controlador.actualizarRanking("Tom Dupont", 150);
        assertEquals(1, ce.getRankingMundial());

        assertTrue(cp.toString().contains("Lena Schulz"));
        assertTrue(ce.toString().contains("Medalla de Oro"));
    }
}