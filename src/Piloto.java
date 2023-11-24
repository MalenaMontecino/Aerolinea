import java.time.LocalDate;

public class Piloto {
    //Atributos
    private int codigoPiloto;
    private String nombrePiloto;
    private String apellidos;
    private String dni;
    private int telefono;
    private LocalDate fechaNacimiento;

    //Constructores

    public Piloto() {
    }

    public Piloto(int codigoPiloto, String nombrePiloto, String apellidos, String dni, int telefono, LocalDate fechaNacimiento) {
        this.codigoPiloto = codigoPiloto;
        this.nombrePiloto = nombrePiloto;
        this.apellidos = apellidos;
        this.dni = dni;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
    }


    //Getter y Setters

    public String getNombrePiloto() {
        return nombrePiloto;
    }

    public void setNombrePiloto(String nombrePiloto) {
        this.nombrePiloto = nombrePiloto;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getCodigoPiloto() {
        return codigoPiloto;
    }

    public void setCodigoPiloto(int codigoPiloto) {
        this.codigoPiloto = codigoPiloto;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    //Métodos

    @Override
    public String toString() {
        return String.format(
                "+-------------------------------------+\n" +
                        "| Datos del Piloto " + codigoPiloto +" |\n" +
                        "+-------------------------------------+\n" +
                        "| Nombre: " + nombrePiloto +" \n" +
                        "| Apellidos: " + apellidos +" \n" +
                        "| DNI: " + dni +" \n" +
                        "| Teléfono: " + telefono +" \n" +
                        "| Fecha de nacimiento: " + fechaNacimiento +" \n" +
                        "+-------------------------------------+\n"
        );
    }
}
