package model;

import modelo.Competidor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CompetidorTest {

    private Competidor competidor;
    private CiclistaPista ciclistaPista;
    private CiclistaEquipo ciclistaEquipo;

    @BeforeEach
    void setUp() {
        competidor = new Competidor("Ana López", 24, "México", 10, 1.65, 58.0);

        ciclistaPista = new CiclistaPista(
            "Martina García", 25, "Colombia",
            3, 1.68, 62.0, "Sprint", 7
        );

        ciclistaEquipo = new CiclistaEquipo(
            "Carlos Ramírez", 30, "Colombia",
            5, 1.80, 74.0, "Team Colombia Track", 4
        );
    }

    // ─── Tests de Competidor ───────────────────────────────────────────────

    @Test
    void testConstructorCompetidor() {
        assertEquals("Ana López", competidor.getNombre());
        assertEquals(24,          competidor.getEdad());
        assertEquals("México",    competidor.getPais());
        assertEquals(10,          competidor.getRankingMundial());
        assertEquals(1.65,        competidor.getEstatura(), 0.001);
        assertEquals(58.0,        competidor.getPeso(),     0.001);
    }

    @Test
    void testConstructorPorDefecto() {
        Competidor c = new Competidor();
        assertNull(c.getNombre());
        assertEquals(0, c.getRankingMundial());
    }

    @Test
    void testSetters() {
        competidor.setNombre("Pedro");
        competidor.setEdad(30);
        competidor.setPais("Argentina");
        competidor.setRankingMundial(5);
        competidor.setEstatura(1.75);
        competidor.setPeso(70.0);

        assertEquals("Pedro",     competidor.getNombre());
        assertEquals(30,          competidor.getEdad());
        assertEquals("Argentina", competidor.getPais());
        assertEquals(5,           competidor.getRankingMundial());
        assertEquals(1.75,        competidor.getEstatura(), 0.001);
        assertEquals(70.0,        competidor.getPeso(),     0.001);
    }

    @Test
    void testActualizarRanking_mejoraConPuntos() {
        // ranking=10, puntos=50 → mejora=5 → nuevo ranking=5
        competidor.actualizarRanking(50);
        assertEquals(5, competidor.getRankingMundial());
    }

    @Test
    void testActualizarRanking_noBajaDeUno() {
        competidor.setRankingMundial(1);
        competidor.actualizarRanking(999);
        assertEquals(1, competidor.getRankingMundial());
    }

    @Test
    void testActualizarRanking_sinPuntos() {
        competidor.actualizarRanking(0);
        assertEquals(10, competidor.getRankingMundial());
    }

    @Test
    void testToStringCompetidor() {
        String s = competidor.toString();
        assertTrue(s.contains("Ana López"));
        assertTrue(s.contains("México"));
        assertTrue(s.contains("10"));
    }

    // ─── Tests de CiclistaPista ────────────────────────────────────────────

    @Test
    void testConstructorCiclistaPista() {
        assertEquals("Martina García", ciclistaPista.getNombre());
        assertEquals("Sprint",         ciclistaPista.getDisciplina());
        assertEquals(7,                ciclistaPista.getNumeroBicicleta());
        assertEquals(0,                ciclistaPista.getMedallas());
    }

    @Test
    void testCalcularPuntaje_sobrecarga1() {
        int puntaje = ciclistaPista.calcularPuntaje(2);
        assertEquals(200, puntaje);
        assertEquals(2, ciclistaPista.getMedallas());
    }

    @Test
    void testCalcularPuntaje_sobrecarga2_oro() {
        int puntaje = ciclistaPista.calcularPuntaje(1, "ORO");
        assertEquals(250, puntaje);
    }

    @Test
    void testCalcularPuntaje_sobrecarga2_oroDobleCampeon() {
        int puntaje = ciclistaPista.calcularPuntaje(2, "ORO");
        assertEquals(600, puntaje);
    }

    @Test
    void testCalcularPuntaje_sobrecarga2_plata() {
        int puntaje = ciclistaPista.calcularPuntaje(1, "PLATA");
        assertEquals(150, puntaje);
    }

    @Test
    void testCalcularPuntaje_sobrecarga2_bronce() {
        int puntaje = ciclistaPista.calcularPuntaje(1, "BRONCE");
        assertEquals(100, puntaje);
    }

    @Test
    void testCalcularPuntaje_sobrecarga2_categoriaDesconocida() {
        int puntaje = ciclistaPista.calcularPuntaje(1, "DIAMANTE");
        assertEquals(50, puntaje);
    }

    @Test
    void testCalcularPuntaje_sobrecarga2_ceroMedallas() {
        int puntaje = ciclistaPista.calcularPuntaje(0, "ORO");
        assertEquals(0, puntaje);
    }

    @Test
    void testToStringCiclistaPista_contieneSuper() {
        String s = ciclistaPista.toString();
        assertTrue(s.contains("Martina García"));
        assertTrue(s.contains("Colombia"));
        assertTrue(s.contains("Sprint"));
        assertTrue(s.contains("CiclistaPista"));
    }

    // ─── Tests de CiclistaEquipo ───────────────────────────────────────────

    @Test
    void testConstructorCiclistaEquipo() {
        assertEquals("Carlos Ramírez",      ciclistaEquipo.getNombre());
        assertEquals("Team Colombia Track", ciclistaEquipo.getNombreEquipo());
        assertEquals(4,                     ciclistaEquipo.getNumIntegrantes());
        assertEquals("Sin resultado",       ciclistaEquipo.getUltimoResultado());
    }

    @Test
    void testRegistrarResultado_sobrecarga1() {
        ciclistaEquipo.registrarResultado("Clasificado a semifinal");
        assertEquals("Clasificado a semifinal", ciclistaEquipo.getUltimoResultado());
    }

    @Test
    void testRegistrarResultado_sobrecarga2_posicionPodio() {
        String resumen = ciclistaEquipo.registrarResultado("Medalla de Oro", 1);
        assertEquals("Medalla de Oro", ciclistaEquipo.getUltimoResultado());
        assertTrue(resumen.contains("Primer lugar") || resumen.contains("1"));
    }

    @Test
    void testRegistrarResultado_sobrecarga2_posicionFueraPodio() {
        String resumen = ciclistaEquipo.registrarResultado("Cuarto lugar", 4);
        assertNotNull(resumen);
        assertTrue(resumen.contains("4"));
    }

    @Test
    void testToStringCiclistaEquipo_contieneSuper() {
        String s = ciclistaEquipo.toString();
        assertTrue(s.contains("Carlos Ramírez"));
        assertTrue(s.contains("Team Colombia Track"));
        assertTrue(s.contains("CiclistaEquipo"));
    }

    // ─── Tests de Competencia ──────────────────────────────────────────────

    @Test
    void testAgregarCompetidor() {
        Competencia comp = new Competencia("Sprint", "2025-10-05", "Individual");
        comp.agregarCompetidor(ciclistaPista);
        assertEquals(1, comp.totalCompetidores());
    }

    @Test
    void testAgregarCompetidorNulo() {
        Competencia comp = new Competencia("Sprint", "2025-10-05", "Individual");
        comp.agregarCompetidor(null);
        assertEquals(0, comp.totalCompetidores());
    }

    @Test
    void testBuscarCompetidor_encontrado() {
        Competencia comp = new Competencia("Sprint", "2025-10-05", "Individual");
        comp.agregarCompetidor(ciclistaPista);
        Competidor encontrado = comp.buscarCompetidor("Martina García");
        assertNotNull(encontrado);
        assertEquals("Martina García", encontrado.getNombre());
    }

    @Test
    void testBuscarCompetidor_noEncontrado() {
        Competencia comp = new Competencia("Sprint", "2025-10-05", "Individual");
        Competidor encontrado = comp.buscarCompetidor("Fantasma");
        assertNull(encontrado);
    }

    @Test
    void testEliminarCompetidor() {
        Competencia comp = new Competencia("Sprint", "2025-10-05", "Individual");
        comp.agregarCompetidor(ciclistaPista);
        boolean eliminado = comp.eliminarCompetidor("Martina García");
        assertTrue(eliminado);
        assertEquals(0, comp.totalCompetidores());
    }

    @Test
    void testToStringCompetencia() {
        Competencia comp = new Competencia("Sprint", "2025-10-05", "Individual");
        comp.agregarCompetidor(ciclistaPista);
        String s = comp.toString();
        assertTrue(s.contains("Sprint"));
        assertTrue(s.contains("1"));
    }
}