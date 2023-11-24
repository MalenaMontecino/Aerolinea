
import java.time.LocalTime;

public class Vuelo {
    //Atributos
    private int codigoVuelo;
    private String origen;
    private String destino;
    private int duracion;
    private LocalTime horaSalida;
    private LocalTime horaLlegada;

    private int plazasDisponibles;

    private int porcentajeDevolucion;

    private Piloto piloto; //objeto

    private Avion  avion; //objeto
    private int billetes;


    //Constructores

    public Vuelo() {
    }

    public Vuelo(int codigoVuelo, String origen, String destino, int duracion, LocalTime horaSalida, int porcentajeDevolucion, Piloto piloto, Avion avion) {
        this.codigoVuelo = codigoVuelo;
        this.origen = origen;
        this.destino = destino;
        this.duracion = duracion;
        this.horaSalida = horaSalida;
        this.horaLlegada = calcularHoraLlegada(horaSalida,duracion);
        this.plazasDisponibles = avion.getNumPlazasTotales();
        this.porcentajeDevolucion = porcentajeDevolucion;
        this.piloto = piloto;
        this.avion = avion;
    }


    //Getter y Setters

    public int getBilletes() {
        return billetes;
    }

    public void setBilletes(int billetes) {
        this.billetes = billetes;
    }

    public int getCodigoVuelo() {
        return codigoVuelo;
    }

    public void setCodigoVuelo(int codigoVuelo) {
        this.codigoVuelo = codigoVuelo;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {

        this.horaSalida = horaSalida;
        this.horaLlegada = calcularHoraLlegada(horaSalida, duracion);
    }

    public LocalTime getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(LocalTime horaLlegada) {
        this.horaLlegada = horaLlegada;
    }


    public int getPlazasDisponibles() {
        return plazasDisponibles;
    }

    public void setPlazasDisponibles(int plazasDisponibles) {
        this.plazasDisponibles = plazasDisponibles;
    }

    public int getPorcentajeDevolucion() {
        return porcentajeDevolucion;
    }

    public void setPorcentajeDevolucion(int porcentajeDevolucion) {
        this.porcentajeDevolucion = porcentajeDevolucion;
    }

    public Piloto getPiloto() {
        return piloto;
    }

    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    //Métodos
    public LocalTime calcularHoraLlegada( LocalTime horaSalida, int duracion){
        this.horaLlegada = this.horaSalida.plusMinutes(this.duracion);
        return horaLlegada;
    }
    @Override
    public String toString() {
        return String.format(
                "+-------------------------------+\n" +
                        "| Datos del vuelo código:" +codigoVuelo+" \n" +
                        "+-------------------------------+\n" +
                        "| Origen: " +origen+" \n" +
                        "| Destino: " +destino+" \n" +
                        "| Duración: " +duracion+" \n" +
                        "| Hora de salida: " +horaSalida+" \n" +
                        "| Hora de llegada:" +horaLlegada+" \n" +
                        "| Plazas disponibles: " +plazasDisponibles+" \n" +
                        "| Porcentaje de devolución:" +porcentajeDevolucion+" \n" +
                        "| Piloto: " +piloto.getCodigoPiloto()+" \n" +
                        "| Avión: " +avion.getNombreAvion()+" \n" +
                        "+-------------------------------+\n"
        );
    }

}
