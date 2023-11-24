import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;
/**
 * Clase principal que contiene el método principal (main) del programa.
 */
public class Main {
    /**
     * Método principal que se ejecuta al iniciar el programa.
     *
     * @param args los argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        int tipoUsuario = 0,opcionMenuAdmin, opcionMenuCliente=0, dato = 0;
        boolean credenciales= false; 


        ArrayList <Piloto> pilotos = new ArrayList<Piloto>();
        ArrayList <Avion> aviones = new ArrayList<Avion>();
        ArrayList <Vuelo> vuelos = new ArrayList<Vuelo>();


        informacionBaseDatos(pilotos, aviones, vuelos);

            do {
                try {

                    tipoUsuario = preguntarPorUsuario();

                } catch (Exception e) {
                    System.out.println((char) +27 + "[31mTipo de dato introducido incorrecto" + (char) +27 + "[39m ");
                    dato = -1;
                }

                if (tipoUsuario == 1) {
                    credenciales = inicioSesionAdmin();

                    if (credenciales) {
                        do {
                            opcionMenuAdmin = mostrarMenuAdmin();
                            switchAdmin(opcionMenuAdmin, pilotos, aviones, vuelos);
                        } while (opcionMenuAdmin != 7);
                    }
                } else if (tipoUsuario == 2) {
                    do {
                        opcionMenuCliente = mostrarMenuCliente();
                        switchCliente(opcionMenuCliente, vuelos);
                    } while (opcionMenuCliente != 4);

                }  else if (tipoUsuario == 3) {
                    System.out.println("Saliendo del sistema...");
                }else {
                    System.out.println((char) +27 + "[31mError! Solo permite los números  1 / 2 / 3\n" + (char) +27 + "[39m ");
                }

            } while ((tipoUsuario != 3) && (tipoUsuario != 1 || tipoUsuario != 2 || !credenciales || dato < 0));


    }
    //SCANNER

    /**
     * Función de scanner para leer los int.
     * @return variable para leer int
     */
    private static int scannerInt (){
        int v1;
        Scanner sc1 = new Scanner(System.in);
        v1 = sc1.nextInt();

        return v1;
    }

    /**
     * Función scanner para leer los String.
     * @return variable para leer string
     */
    private static String scannerString (){
        String v2;
        Scanner sc2 = new Scanner(System.in);
        v2 = sc2.next();
        return v2;
    }

    /**
     * Función de scanner para leer char.
     * @return variable para leer char
     */
    private static char scannerChar (){
        char v3;
        Scanner sc3 = new Scanner(System.in);
        v3 = sc3.next().charAt(0);
        return v3;
    }

    //DATOS EXISTENTES

    /**
     * Función que crea pilotos para que ya haya registrados.
     * @param pilotos array
     */
    private static void pilotosExistentes(ArrayList <Piloto> pilotos){
        pilotos.add(new Piloto(1, "Marcos", "Aurelio Montecino", "24416605L",601040789, LocalDate.of(1990,6,21)));
        pilotos.add(new Piloto(2,"Malena", "Montecino Martínez", "25526604P", 658951236, LocalDate.of(2000,4,12)));
        pilotos.add(new Piloto(3,"Manuel", "García Torralba","14725836H",852146325,LocalDate.of(1999,11,3)));


    }

    /**
     *Función que crea aviones para que ya haya registrados.
     * @param aviones array
     */
    private static void avionesExistentes(ArrayList <Avion> aviones){
        aviones.add(new Avion("avion1", LocalDate.of(2023,4,21),250,75));
        aviones.add(new Avion("avion2", LocalDate.of(2023,4,15),1000,100));
        aviones.add(new Avion("avion3", LocalDate.of(2023,4,3),500,50));

    }

    /**
     * Función que crea vuelos para que ya haya registrados.
     * @param vuelos array
     * @param pilotos array
     * @param aviones array
     */

    private static void vuelosExistentes(ArrayList <Vuelo> vuelos, ArrayList <Piloto> pilotos, ArrayList <Avion> aviones ){
        vuelos.add(new Vuelo(1,"Barcelona","Menorca", 30, LocalTime.of(9,30),25, pilotos.get(2),aviones.get(0)));
        vuelos.add(new Vuelo(2, "Madrid","Dubai", 250, LocalTime.of(6,15), 0, pilotos.get(1), aviones.get(1)));
        vuelos.add(new Vuelo(3, "Barcelona", "Noruega", 200, LocalTime.of(5,0),0,pilotos.get(0), aviones.get(2)));

    }

    /**
     * Función para añadir los vuelos, aviones y pilotos creados en las anteriores funciones.
     * @param pilotos array
     * @param aviones array
     * @param vuelos array
     */
    private static void informacionBaseDatos(ArrayList<Piloto> pilotos, ArrayList<Avion> aviones, ArrayList<Vuelo> vuelos){
       pilotosExistentes(pilotos);
       avionesExistentes(aviones);
       vuelosExistentes(vuelos,pilotos,aviones);

    }

    //PREGUNTA PRINCIPAL

    /**
     * Función para preguntar al usuario qué tipo de usuario quiere ser.
     * @return el tipo de usuario
     */
    private static int preguntarPorUsuario(){
        int usuario;
        System.out.println("\n¿Cómo quieres acceder a la aplicación?");
        System.out.println("1.- Administrador");
        System.out.println("2.- Cliente");
        System.out.println("3.- Exit");
        System.out.println("\nInserte el número: ");

        usuario= scannerInt();

        return usuario;
    }
    // ADMINISTRADOR

    /**
     * Función para iniciar sesion como Admin y comprobar credenciales.
     * @return credenciales
     */
    private static boolean inicioSesionAdmin(){
        String nombreUsuario, passwordUsuario;
        boolean usuarioCorrecto = false, passwordUsuarioCorrecta =false, credenciales = true;

        // pregunta
        System.out.println("\nNombre de usuario: ");
        nombreUsuario = scannerString();
        System.out.println("Contraseña: ");
        passwordUsuario = scannerString();

        // comprobación
        if (nombreUsuario.equals("Admin")){
            usuarioCorrecto = true;
        }

        if (passwordUsuario.equals("Contraseña0")) {
            passwordUsuarioCorrecta = true;
        }

        // respuesta
        if (!passwordUsuarioCorrecta || !usuarioCorrecto ){
            System.out.println("\n"+(char) + 27 + "[31mCredenciales incorrectas"+(char) + 27 + "[39m ");

            credenciales = false;
        } else {
            System.out.println("\nCredenciales correctas!");

        }
        return credenciales;
    }

    /**
     * Función que muestra el menú para el Admin y devuelve la opción seleccionada.
     * @return opción seleccionada
     */
    private static int mostrarMenuAdmin(){
        int opcionMenuAdmin =0;

        try {
            System.out.println("\nElige una opción");
            System.out.println("--------------------");
            System.out.println("1.- Crear vuelo");
            System.out.println("2.- Visualizar datos de vuelo");
            System.out.println("3.- Modificar datos de vuelo");
            System.out.println("4.- Borrar vuelo");
            System.out.println("5.- Mostrar aviones");
            System.out.println("6.- Mostrar pilotos");
            System.out.println("7.- Exit");
            System.out.println("\nElige una opción: ");
            opcionMenuAdmin = scannerInt();
        }catch (Exception e){

        }

        if (opcionMenuAdmin  <=0 || opcionMenuAdmin >7){
            System.out.println((char) + 27 + "[31mError!! Ese número no está en el menú :c"+(char) + 27 + "[39m ");
        }


        return opcionMenuAdmin;
    }

    /**
     * Función que muestra que función se hace en cada opción del menú de Admin.
     * @param opcionMenuAdmin la opción elegida en la función que muestra las opciones del menú
     * @param pilotos la array de los pilotos
     * @param aviones la array de los aviones
     * @param vuelos la array de los vuelos
     */
    private static void switchAdmin( int opcionMenuAdmin, ArrayList <Piloto> pilotos, ArrayList <Avion> aviones,  ArrayList <Vuelo> vuelos){
        int vueloEspecifico;

        switch (opcionMenuAdmin){
            case 1:
                crearVuelo(vuelos, pilotos, aviones);
                break;
            case 2:
                buscarVuelo(vuelos);
                break;
            case 3:
                vueloEspecifico = buscarVuelo(vuelos);
                modificarVuelo(vueloEspecifico,vuelos, pilotos, aviones);

                break;
            case 4:
                vueloEspecifico=buscarVuelo(vuelos);
                borrarVuelo(vueloEspecifico,vuelos);
                break;
            case 5:
                System.out.println(aviones);
                break;
            case 6:
                System.out.println(pilotos);
                break;
            case 7:
                System.out.println("Saliendo...");
                break;
        }
    }

    /**
     * Función que crea un nuevo vuelo a partir de los datos ingresados por el usuario y lo agrega a la lista de vuelos.
     * @param vuelos array de vuelos
     * @param pilotos array de pilotos
     * @param aviones array de aviones
     */

    private static void crearVuelo(ArrayList <Vuelo> vuelos, ArrayList <Piloto> pilotos, ArrayList <Avion> aviones){
        int codigoVuelo=0, duracion=0, plazasDisponibles=0, porcentajeDevolucion=0, codigoPiloto,posicionPiloto=0,dato,posicionAvion=0;
        String origen = null, destino =null, nombreAvion;
        LocalTime horaSalida = null;
        boolean encontrado;
        boolean encontrado2=false;

        System.out.println("\u001B[33mVuelos ya existentes\n------------\u001B[0m");
        System.out.println(vuelos);

        System.out.println("\nProcede a introducir los datos del vuelo ");
        System.out.println("-----------------------------------------");


        do {
            encontrado=false;
            try {
                System.out.println("Código de vuelo: ");
                codigoVuelo = scannerInt();

                for (Vuelo v : vuelos) {
                    if (v.getCodigoVuelo() == codigoVuelo) {
                        encontrado = true;
                    }
                }

            } catch (NullPointerException e) {
                encontrado = false;

            } catch (InputMismatchException e) {
                System.out.println((char) +27 + "[31mTipo de dato introducido incorrecto" + (char) +27 + "[39m ");
                encontrado = false;
            }

            System.out.println("Malena: " + encontrado);
            if (encontrado) {
                System.out.println((char) +27 + "[31mERROR! El vuelo seleccionado ya existe" + (char) +27 + "[39m ");
                dato = -1;
            } else {
                dato = 1;
            }
        } while (dato < 0);
        

        do{
            try{
                System.out.println("Origen: ");
                origen = scannerString();
                dato=1;

            }catch (Exception e) {
                System.out.println((char) + 27 + "[31mTipo de dato introducido incorrecto"+(char) + 27 + "[39m ");
                dato =-1;
            }
        }while (dato<0);

        do {
            try {
                System.out.println("Destino: ");
                destino = scannerString();
                dato =1;
            } catch (Exception e) {
                System.out.println((char) +27 + "[31mTipo de dato introducido incorrecto" + (char) +27 + "[39m ");
                dato =-1;
            }
        }while (dato<0);
        do{
            try{
                System.out.println("Duración (minutos): ");
                duracion = scannerInt();
                dato=1;
            }catch (Exception e) {
                System.out.println((char) + 27 + "[31mTipo de dato introducido incorrecto"+(char) + 27 + "[39m ");
                dato =-1;
            }
        }while (dato<0);

        do {
            try {
                System.out.println("Hora de salida (hh:mm:ss): ");
                String hora = scannerString();
                horaSalida = LocalTime.parse(hora);
                dato =1;
            } catch (Exception e) {
                System.out.println((char) +27 + "[31mTipo de dato introducido incorrecto" + (char) +27 + "[39m ");
                dato = -1;
            }
        }while (dato<0);

        do {
            try {
                System.out.println("Plazas disponibles: ");
                plazasDisponibles = scannerInt();
                dato = 1;
            } catch (Exception e) {
                System.out.println((char) +27 + "[31mTipo de dato introducido incorrecto" + (char) +27 + "[39m ");
                dato = -1;
            }
        }while (dato<0);

        do {
            try {
                System.out.println("Porcentaje de devolución: ");
                porcentajeDevolucion = scannerInt();
                dato = 1;
            } catch (Exception e) {
                System.out.println((char) +27 + "[31mTipo de dato introducido incorrecto" + (char) +27 + "[39m ");
                dato = -1;
            }
        }while (dato<0);


        System.out.println("\u001B[33mPilotos ya existentes\n------------ \u001B[0m");

        System.out.println(pilotos);

        do {

            try {
                System.out.println("\nIntroduce el código del Piloto: ");
                codigoPiloto = scannerInt();
                for (Piloto x : pilotos) {
                    if (x.getCodigoPiloto() == codigoPiloto) {
                        posicionPiloto = pilotos.indexOf(x);

                        encontrado2 = true;
                    }
                }
            } catch (Exception e) {
                encontrado2 = false;
            }

            if (encontrado2) {
                System.out.println("Ha seleccionado usted el siguiente piloto: \n" + pilotos.get(posicionPiloto));
                dato=1;
            } else {
                System.out.println((char) +27 + "[31mEl piloto seleccionado no existe." + (char) +27 + "[39m ");
                dato=-1;
            }
        } while (dato<0);
        
        System.out.println("\u001B[33mAviones ya existentes\n------------ \u001B[0m");

        System.out.println(aviones);

        do {

            try {
                System.out.println("\nIntroduce el nombre del Avión: ");
                nombreAvion = scannerString();
                for (Avion y : aviones) {
                    if (Objects.equals(y.getNombreAvion(), nombreAvion)) {
                        posicionAvion = aviones.indexOf(y);
                        encontrado = true;
                    }
                }
            } catch (Exception e) {
                encontrado=false;
            }

            if (encontrado) {
                System.out.println("Ha seleccionado usted el siguiente avión: \n" + aviones.get(posicionAvion));
                dato = 1;
            } else {
                System.out.println((char) +27 + "[31mEl avión seleccionado no existe." + (char) +27 + "[39m ");
                dato = -1;
            }

        }while (dato<0);

        vuelos.add(new Vuelo(codigoVuelo,origen,destino,duracion, horaSalida, porcentajeDevolucion,pilotos.get(posicionPiloto), aviones.get(posicionAvion)));

        System.out.println("El vuelo ha sido creado con éxito!");
    }

    /**
     * Función que busca un vuelo en la lista de vuelos y devuelve la posición del vuelo encontrado.
     * @param vuelos
     * @return
     */
    private static int buscarVuelo(ArrayList <Vuelo> vuelos){
        int codigoIntroducido=0, posicionVuelo=0,dato = 0;
        boolean encontrado= false;

        System.out.println("\nVUELOS\n" + vuelos);
        do {

            try {
                System.out.println("\nIntroduce el código del vuelo: ");
                codigoIntroducido = scannerInt();
                for (Vuelo i : vuelos) {

                    if (i.getCodigoVuelo() == codigoIntroducido) {
                        posicionVuelo = vuelos.indexOf(i);
                        encontrado= true;
                    }
                }

            } catch (Exception e) {
                System.out.println("Ese vuelo no existe");

            }

            if(encontrado){
                System.out.println("Ha seleccionado usted el siguiente vuelo: \n"+ vuelos.get(posicionVuelo));
                dato=1;
            } else{
                System.out.println((char) + 27 + "[31mEl vuelo seleccionado no existe."+(char) + 27 + "[39m ");
                dato=-1;
            }
        } while (dato<0);


        return posicionVuelo;
    }

    /**
     * Función que modifica un vuelo específico con los datos proporcionados.
     * @param vueloEspecifico el vuelo específico seleccionado
     * @param vuelos array vuelos
     * @param pilotos array pilotos
     * @param aviones array aviones
     */
    private static void modificarVuelo(int vueloEspecifico,ArrayList <Vuelo> vuelos,  ArrayList <Piloto> pilotos, ArrayList <Avion> aviones){
        int codigoVuelo,duracion, plazasDisponibles, porcentajeDevolucion, dato, codigoPiloto, posicionPiloto=0,posicionAvion=0 ;
        String origen, destino, nombreAvion = null;
        LocalTime horaSalida;
        Boolean encontrado;

        System.out.println("\u001B[33m*IMPORTANTE*\nIntroduce '0' si no quieres modificar el dato\u001B[0m");

        codigoVuelo = vuelos.get(vueloEspecifico).getCodigoVuelo();
        vuelos.get(vueloEspecifico).setCodigoVuelo(codigoVuelo);

        do {
            try {
                System.out.println("Origen: ");
                origen = scannerString();

                if (origen.equals("0")){
                    origen = vuelos.get(vueloEspecifico).getOrigen();
                }
                vuelos.get(vueloEspecifico).setOrigen(origen);
                dato=1;

            } catch (Exception e) {
                System.out.println((char) +27 + "[31mTipo de dato introducido incorrecto" + (char) +27 + "[39m ");
                dato =-1;
            }
        }while (dato<0);


        do {
            try {
                System.out.println("Destino: ");
                destino= scannerString();

                if (destino.equals("0")){
                    destino = vuelos.get(vueloEspecifico).getDestino();
                }
                vuelos.get(vueloEspecifico).setDestino(destino);
                dato =1;
            } catch (Exception e) {
                System.out.println((char) +27 + "[31mTipo de dato introducido incorrecto" + (char) +27 + "[39m ");
                dato =-1;
            }
        }while (dato<0);

        do{
            try{
                System.out.println("Duración (minutos): ");
                duracion = scannerInt();

                if (duracion == 0){
                    duracion = vuelos.get(vueloEspecifico).getDuracion();
                }
                vuelos.get(vueloEspecifico).setDuracion(duracion);
                dato=1;
            }catch (Exception e) {
                System.out.println((char) + 27 + "[31mTipo de dato introducido incorrecto"+(char) + 27 + "[39m ");
                dato =-1;
            }
        }while (dato<0);


        do {
            try {
                System.out.println("Hora de salida (hh:mm:ss): ");
                String hora = scannerString();
                horaSalida = LocalTime.parse(hora);

                if (horaSalida.equals("00:00:00")) {
                    horaSalida = vuelos.get(vueloEspecifico).getHoraSalida();
                }
                vuelos.get(vueloEspecifico).setHoraSalida(horaSalida);
                dato =1;

            } catch (Exception e) {
                System.out.println((char) +27 + "[31mTipo de dato introducido incorrecto" + (char) +27 + "[39m ");
                dato = -1;
            }
        }while (dato<0);

        do {
            try {
                System.out.println("Plazas disponibles: ");
                plazasDisponibles = scannerInt();

                if (plazasDisponibles==0){
                    plazasDisponibles = vuelos.get(vueloEspecifico).getPlazasDisponibles();
                }
                vuelos.get(vueloEspecifico).setPlazasDisponibles(plazasDisponibles);
                dato = 1;
            } catch (Exception e) {
                System.out.println((char) +27 + "[31mTipo de dato introducido incorrecto" + (char) +27 + "[39m ");
                dato = -1;
            }
        }while (dato<0);

        do {
            try {
                System.out.println("Porcentaje de devolución: ");
                porcentajeDevolucion = scannerInt();
                if (porcentajeDevolucion==0){
                    porcentajeDevolucion = vuelos.get(vueloEspecifico).getPorcentajeDevolucion();
                }
                vuelos.get(vueloEspecifico).setPorcentajeDevolucion(porcentajeDevolucion);
                dato = 1;
            } catch (Exception e) {
                System.out.println((char) +27 + "[31mTipo de dato introducido incorrecto" + (char) +27 + "[39m ");
                dato = -1;
            }
        }while (dato<0);


        System.out.println("\u001B[33mPilotos ya existentes\n------------ \u001B[0m");

        System.out.println(pilotos);

        do {
            encontrado= false;
            try {
                System.out.println("\nIntroduce el código del Piloto: ");
                codigoPiloto = scannerInt();


                if (codigoPiloto == 0) {
                    codigoPiloto = vuelos.get(vueloEspecifico).getPiloto().getCodigoPiloto();
                } else{
                    for (Piloto x : pilotos) {
                        if (x.getCodigoPiloto() == codigoPiloto) {
                            posicionPiloto = pilotos.indexOf(x);

                            encontrado = true;
                        }
                    }
                }
                vuelos.get(vueloEspecifico).getPiloto().setCodigoPiloto(codigoPiloto);

            } catch (Exception e) {
                encontrado = false;
            }

            if (encontrado) {
                System.out.println("Ha seleccionado usted el siguiente piloto: \n" + pilotos.get(posicionPiloto));
                dato=1;
            } else {
                System.out.println((char) +27 + "[31mEl piloto seleccionado no existe." + (char) +27 + "[39m ");
                dato=-1;
            }
        } while (dato<0);

        vuelos.get(vueloEspecifico).setPiloto(pilotos.get(posicionPiloto));


        System.out.println("\u001B[33mAviones ya existentes\n------------ \u001B[0m");
        System.out.println(aviones);

        do {
            encontrado= false;
            try {
                System.out.println("\nIntroduce el nombre del Avión: ");
                nombreAvion= scannerString();
                if (nombreAvion.equals("0")){
                    nombreAvion = vuelos.get(vueloEspecifico).getAvion().getNombreAvion();
                } else{
                    for (Avion y : aviones) {
                        if (Objects.equals(y.getNombreAvion(), nombreAvion)) {
                            posicionAvion = aviones.indexOf(y);
                            encontrado = true;
                        }
                    }
                }


            } catch (Exception e) {
                encontrado = false;
            }

            if (encontrado) {
                System.out.println("Ha seleccionado usted el siguiente avión: \n"+aviones.get(posicionAvion));
                System.out.println("Vuelo modificado con éxito!");
                dato=1;
            } else {
                System.out.println((char) + 27 + "[31mEl avión seleccionado no existe."+(char) + 27 + "[39m ");
                dato=-1;
            }
        } while (dato<0);

        vuelos.get(vueloEspecifico).getAvion().setNombreAvion(nombreAvion);

    }

    /**
     * Función para borrar un vuelo específico de la lista de vuelos.
     * @param vueloEspecifico el vuelo específico seleccionado
     * @param vuelos array vuelos
     */
    private static void borrarVuelo( int vueloEspecifico,ArrayList <Vuelo> vuelos){
        vuelos.remove(vueloEspecifico);
        System.out.println("El vuelo ha sido eliminado con éxito!");
    }

    //CLIENTE

    /**
     * Función que muestra el menú para el cliente y devuelve la opción seleccionada.
     * @return opción seleccionada
     */
    private static int mostrarMenuCliente(){
        int opcionMenuCliente=0;

        try {
            System.out.println("\nElige una opción");
            System.out.println("-----------------");
            System.out.println("1.- Visualizar información de vuelo");
            System.out.println("2.- Comprar billetes");
            System.out.println("3.- Anular billetes");
            System.out.println("4.- Exit");
            System.out.println("\nSelecciona una opción: ");
            opcionMenuCliente = scannerInt();
        }catch (Exception e){
        }
        if (opcionMenuCliente <=0 || opcionMenuCliente >4){
            System.out.println((char) + 27 + "[31mError!! Ese número no está en el menú :c"+(char) + 27 + "[39m ");
        }
        return opcionMenuCliente;


    }

    /**
     *Función que muestra que función se hace en cada opción del menú Cliente.
     * @param opcionMenuCliente la opción elegida en la función que muestra las opciones del menú
     * @param vuelos array vuelos
     */
    private static void switchCliente(int opcionMenuCliente ,  ArrayList <Vuelo> vuelos){
        int vueloSeleccionado = 0, numPasajeros, dato;

        switch (opcionMenuCliente){
            case 1:
                do {
                    try {
                        vueloSeleccionado = buscarVuelo(vuelos);
                        System.out.println(vuelos.get(vueloSeleccionado).getPiloto());
                        dato=1;
                    } catch (Exception e) {
                        System.out.println((char) +27 + "[31mEl vuelo seleccionado no existe" + (char) +27 + "[39m ");
                        dato = -1;
                    }
                } while (dato <0);
                break;
            case 2:
                do {
                    try {
                        do{
                            vueloSeleccionado = buscarVuelo(vuelos);
                            numPasajeros= comprarBilletes(vueloSeleccionado,vuelos);
                        } while(numPasajeros > vuelos.get(vueloSeleccionado).getPlazasDisponibles());
                        dato=1;
                    } catch (Exception e) {
                        System.out.println((char) +27 + "[31mEl vuelo seleccionado no existe" + (char) +27 + "[39m ");
                        dato = -1;
                    }
                } while (dato <0);
                break;
            case 3:
                do {
                    try {
                        anularBilletes(vuelos);
                        dato=1;
                    } catch (Exception e) {
                        System.out.println((char) +27 + "[31mEl vuelo seleccionado no existe" + (char) +27 + "[39m ");
                        dato = -1;
                    }
                } while (dato < 0);
                break;
            case 4:
                System.out.println("Saliendo...");
                break;

        }
    }

    /**
     * Funcion que permite al cliente comprar billetes para un vuelo específico.
     * @param vueloSeleccionado el vuelo seleccionado
     * @param vuelos array de vuelos
     * @return numero de billetes comprados
     */
    private static  int comprarBilletes (int vueloSeleccionado,ArrayList <Vuelo> vuelos){
        int numPasajeros=0, dato;

        do {
            try {
                System.out.println("Por favor introduzca el número de pasajeros: ");
                numPasajeros = scannerInt();
                dato = 1;
            } catch (Exception e) {
                System.out.println((char) +27 + "[31mTipo de dato introducido incorrecto" + (char) +27 + "[39m ");
                dato = -1;
            }
        }while (dato<0);

        if (numPasajeros > vuelos.get(vueloSeleccionado).getPlazasDisponibles()) {
            System.out.println((char) + 27 + "[31mEl vuelo seleccionado no está disponible, solo quedan " + vuelos.get(vueloSeleccionado).getPlazasDisponibles() + " plazas.\n"+(char) + 27 + "[39m ");
            System.out.println("A continuación le muestro los vuelos adaptados a su número de pasajeros: ");

            if (vuelos == null || vuelos.isEmpty()) {
                System.out.println((char) + 27 + "[31mNo hay vuelos disponibles\n"+(char) + 27 + "[39m ");

            }
        }else{
            confirmarCompraBilletes(numPasajeros, vueloSeleccionado, vuelos);
        }

        return numPasajeros;
    }

    /**
     * Función para confirmar la compra de billetes para un vuelo específico.
     * @param numPasajeros el número de billetes a comprar
     * @param vueloSeleccionado el vuelo específico
     * @param vuelos array vuelos
     * @return el vuelo seleccionado
     */
    private static int confirmarCompraBilletes(int numPasajeros,int vueloSeleccionado, ArrayList <Vuelo> vuelos){ // wl vuelo selecionado
        int precioTotal, billetes=0;
        char confirmacionCompra;

        precioTotal=calcularPrecioTotal(numPasajeros);
        do {
            System.out.println("Perfecto! " + numPasajeros + " billetes!El PRECIO TOTAL será:" + precioTotal + "€\nQuiere usted seguir con la compra? (y/n)");
            confirmacionCompra = scannerChar();

            if (confirmacionCompra == 'y') {
                System.out.println("La compra se ha realizado correctamente!\n");
                billetes = numPasajeros;


                vuelos.get(vueloSeleccionado).setPlazasDisponibles(vuelos.get(vueloSeleccionado).getPlazasDisponibles()-billetes);
                vuelos.get(vueloSeleccionado).setBilletes(vuelos.get(vueloSeleccionado).getBilletes()+billetes);
                System.out.println("Ha comprado usted: "+billetes+" billetes\n");


            }else if(confirmacionCompra == 'n'){
                System.out.println((char) + 27 + "[31mCompra cancelada\n"+(char) + 27 + "[39m ");

            }else {
                System.out.println((char) + 27 + "[31mERROR - Solo se permiten los caracteres y/n\n"+(char) + 27 + "[39m ");
            }

        } while (confirmacionCompra != 'y'&& confirmacionCompra != 'n');

        return vueloSeleccionado;
    }

    /**
     * Función para calcular el precio de los billetes comprados para el vuelo específico.
     * @param numPasajeros número de billetes comprados
     * @return el precio total de los billetes comprados
     */
    private static int calcularPrecioTotal(int numPasajeros){
        int precioTotal;
        precioTotal = numPasajeros * 25;
        return precioTotal;
    }

    /**
     * Función que permite al cliente anular billetes de un vuelo específico.
     * @param vuelos array vuelos
     * @return la posición para saber el vuelo específico
     */
    private static int anularBilletes(ArrayList <Vuelo> vuelos){
        int posicionVuelo, numBilletesAnular=0, dato, billetes;
        double descuentoXbillete,dineroAnulacion;

        System.out.println("-Indique los siguientes datos para poder anular billetes-");
        posicionVuelo= buscarVuelo(vuelos);
        do {
            try {
                System.out.println("Número de billetes a anular: ");
                numBilletesAnular = scannerInt();
                dato=1;
            } catch (Exception e) {
                System.out.println((char) +27 + "[31mTipo de dato introducido incorrecto" + (char) +27 + "[39m ");
                dato = -1;
            }
        }while (dato<0);

        if (vuelos.get(posicionVuelo).getBilletes()<numBilletesAnular){
            System.out.println((char) + 27 + "[31mERROR"+(char) + 27 + "[39m ");

        }

        billetes= vuelos.get(posicionVuelo).getBilletes();

        if(vuelos.get(posicionVuelo) != null){
            System.out.println("Tienes: "+billetes+" billetes en el vuelo que acabas de seleccionar, y quieres anular: "+numBilletesAnular);

            if (billetes>0) {
                descuentoXbillete = precioDevolucion(posicionVuelo, vuelos);
                dineroAnulacion = numBilletesAnular * descuentoXbillete;
                System.out.println("\nPorcentaje de devolución del vuelo: " + vuelos.get(posicionVuelo).getPorcentajeDevolucion() + "%");
                System.out.println("\nDinero que será devuelto: " + dineroAnulacion + "€\n");

                billetes= billetes-numBilletesAnular ;
                System.out.println("Billetes anulados con éxito! Le quedan "+billetes+" billetes");
                vuelos.get(posicionVuelo).setBilletes(billetes);
            }

        } else {
            System.out.println((char) + 27 + "[31mEl vuelo seleccionado no existe.\n"+(char) + 27 + "[39m ");
        }
        return posicionVuelo;

    }

    /**
     * Función para calcular el precio de devolución al anular billetes de un vuelo en específico.
     * @param posicionVuelo posición de vuelo para saber el vuelo específico
     * @param vuelos array de vuelos
     * @return el descuento por cada billete
     */
    private static double precioDevolucion(int posicionVuelo,ArrayList <Vuelo> vuelos){
        double descuento1billete, descuento;

        descuento= vuelos.get(posicionVuelo).getPorcentajeDevolucion()/(double)100;
        descuento1billete = descuento*25;
        return descuento1billete;
    }


}