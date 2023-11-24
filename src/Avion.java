import java.time.LocalDate;

public class Avion {
    //Atributos
    private String nombreAvion;
    private LocalDate fechaCreacion;
    private int capacidadCombustible;
    private int numPlazasTotales; //puesto aquí porque sino en el constructor me lo pediria en el main


    //Constructores

    public Avion() {
    }

    public Avion(String nombreAvion, LocalDate fechaCreacion, int capacidadCombustible, int numPlazasTotales) {
        this.nombreAvion = nombreAvion;
        this.fechaCreacion = fechaCreacion;
        this.capacidadCombustible = capacidadCombustible;
        this.numPlazasTotales = numPlazasTotales;

    }


    //Getter y Setters

    public String getNombreAvion() {
        return nombreAvion;
    }

    public void setNombreAvion(String nombreAvion) {
        this.nombreAvion = nombreAvion;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getCapacidadCombustible() {
        return capacidadCombustible;
    }

    public void setCapacidadCombustible(int capacidadCombustible) {
        this.capacidadCombustible = capacidadCombustible;
    }

    public int getNumPlazasTotales() {
        return numPlazasTotales;
    }

    //numPlazasTotales no tiene setter porque como no va a poder modificarse no tendría sentido.

    //Métodos

    @Override
    public String toString() {
        return String.format(
                "+------------------------------------+\n" +
                        "| Datos del avión " + nombreAvion +" |\n" +
                        "+------------------------------------+\n" +
                        "| Fecha de creación: " + fechaCreacion +" \n" +
                        "| Capacidad de combustible: " + capacidadCombustible +" \n" +
                        "| Plazas totales: " + numPlazasTotales +" \n" +
                        "+------------------------------------+\n"
        );
    }

}
