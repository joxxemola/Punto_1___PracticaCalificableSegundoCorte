package controlador;

import modelo.*;
import vista.VistaCompetencia;
import java.util.List;

public class ControladorCompetencia {
    private Competencia competencia;
    private VistaCompetencia vista;

    public ControladorCompetencia(Competencia competencia, VistaCompetencia vista) {
        this.competencia = competencia;
        this.vista = vista;
    }

    public void ejecutar() {
        vista.mostrarMensaje(competencia.generarReporte());
    }

    public void agregarEquipo(Equipo e) {
        competencia.agregarEquipo(e);
    }
}