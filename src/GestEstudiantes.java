import java.util.Scanner;

public class GestEstudiantes {
    // Campos
    private static final int TAM_ARR = 50; // final convierte a la variable en constante
    // Arreglo de objetos de tipo Estudiante
    private static Estudiante[] grupo = new Estudiante[TAM_ARR];
    // Número de estudiantes dados de alta
    private static int numEst = 0;
    
    // Constantes para los menús
    private static final int OPC_ALTA = 1;
    private static final int OPC_CONS = 2;
    private static final int OPC_MODI = 3;
    private static final int OPC_BAJA = 4;
    private static final int OPC_LIST = 5;
    private static final int OPC_SALI = 6;    

    public static void main(String[] args) {
      
        // Dar de alta por código a tres objetos de tipo estudiante
        grupo[numEst++] = new Estudiante("001", "Pedro", "Pérez", "González",
                                            21, 93.2);
        grupo[numEst++] = new Estudiante("002", "Martha", "Hernández", "Becerra",
                                            19, 96.3);
        grupo[numEst++] = new Estudiante("003", "Juan", "Rodríguez", "Ramírez",
                                            20, 89.7);
        
        // Variable para la opción del menú
        int opcion = 0;
        
        do {
            // Desplegar menú principal y recibir opción seleccionada
            opcion = desplegarMenuPrincipal();
            
            switch(opcion) {
                case OPC_ALTA: // Método para dar alta
                                System.out.println("ALTA DE ESTUDIANTES");
                                altaEstudiantes();
                                break;
                case OPC_CONS: // Método para la consulta
                                System.out.println("CONSULTA DE ESTUDIANTES");
                                consultarEstudiante();
                                break;
                case OPC_MODI: // TODO Método para modificación de datos
                                System.out.println("MODIFICACIÓN DE DATOS");
                                break;
                case OPC_BAJA: // Método para dar de baja
                                System.out.println("BAJA DE ESTUDIANTES");
                                bajaEstudiante();
                                break;
                case OPC_LIST: // Método para listar los estudiantes
                                listarEstudiantes();
                                System.out.println("LISTADO DE ESTUDIANTES");
                                break;
                case OPC_SALI: // Salir de la aplicación
                                System.out.println("Saliendo de la aplicación.");
                                break;
                default:        System.out.println("Opción inválida");
                                break;
            } // Fin de switch
            
            System.out.println(); // Salto de línea
            
        } while(opcion != 6); // Seguir en el ciclo mientras la opción != 6
        
    } // Fin de main
    
    /**
     * Método para dar de baja un estudiante. El estudiante se busca a partir
     * de su código, y si se encuentra se pide confirmación al usuario antes
     * de realizar la baja.
     */
    public static void bajaEstudiante() {
        Scanner entrada = new Scanner(System.in);
        String cod;
        String opc = "";
        
        do {
            System.out.println("Introduzca el código del estudiante por dar " +
                    "de baja:");
            cod = entrada.nextLine();
            
            int idx = buscarEstudiante(cod);
            
            if(idx >= 0) { // Estudiante encontrado
                // Recorrer las referencias un lugar a la izquierda a partir
                // del elemento siguiente al que se quiere eliminar
                for(int i=idx; i<numEst-1 ;i++) {
                    grupo[i] = grupo[i+1];
                }
                
                // Actualizar el número de estudiantes en el arreglo
                numEst--;
                
                // Borrar la referencia en la última posición ocupada
                // anteriormente
                grupo[numEst] = null;
                
            } else {
                System.out.println("No existe un estudiante con el código " 
                        + cod);
            }
            
            System.out.println("¿Desea dar de baja otro estudiante (s/n)?");
            opc = entrada.nextLine();
            
        } while(opc.equalsIgnoreCase("S"));
        
    } // Fin de bajaEstudiante
    
    /**
     * Método para realizar la consulta de un estudiante a partir de su
     * código.
     */
    public static void consultarEstudiante() {
        Scanner entrada = new Scanner(System.in);
        String cod;
        String opc="";
        
        do {
            System.out.println("Introduzca el código del estudiante por buscar:");
            cod = entrada.nextLine();
            
            int idx = buscarEstudiante(cod);
            
            if(idx >= 0) { // Estudiante encontrado
                System.out.println("Estudiante encontrado:");
                grupo[idx].desplegarDatos();
            } else {
                System.out.println("No existe un estudiante con el código " +
                        cod);
            }
            
            System.out.println(); // Salto de línea
            
            System.out.println("¿Desea realizar otra consulta (s/n)?");
            opc = entrada.nextLine();
            
        } while(opc.equalsIgnoreCase("S"));
        
    } // Fin de consultarEstudiante
    
    /**
     * Método para listar los datos de los estudiantes dados de alta.
     */
    public static void listarEstudiantes() {
        int cont = 0;
        
        // Verificar que haya estudiantes dados de alta
        if(numEst == 0) {
            System.out.println("No hay estudiantes dados de alta.");
            return; // Salir del método
        }
        
        for(int i=0; i<numEst; i++) {
            System.out.println("Estudiante " + ++cont);
            grupo[i].desplegarDatos();
            System.out.println(); // Salto de línea
        }
        
    } // Fin de listarEstudiantes
    
    
    /**
     * Método que busca a un estudiante por su código. Si lo encuentra, se
     * debe regresar el subíndice en el arreglo "grupo"; de lo contrario,
     * se debe regresar -1.
     */
    public static int buscarEstudiante(String codigo) {
        int idx = -1;
        
        // Recorrer el arreglo con todos los estudiantes dados de alta
        for(int i=0; i<numEst; i++) {
            // Verificar si el código buscado es igual al código del
            // i-ésimo estudiante
            if(codigo.equalsIgnoreCase(grupo[i].getCodigo())) {
                idx = i; // Se encontró el estudiante en el subíndice i
                break; // Salir del for
            }
        }
        // Regresar índice
        return idx;
    }
    
    /**
     * Método para dar de alta a un número dado de estudiantes. Se le pedirá
     * al usuario que introduzca los datos de un estudiante; posteriormente
     * se le preguntará si desea introducir los datos de otro estudiante. No
     * se permite la duplicidad de códigos.
     */
    public static void altaEstudiantes() {
        String resp = "";
        Scanner entrada = new Scanner(System.in);
        
        // Verificar si el arreglo grupo ya está llegado
        if(numEst == TAM_ARR) {
            System.out.println("No es posible dar de alta más estudiantes.");
            System.out.println("Para dar de alta nuevos estudiantes, es necesario " +
                            "dar de baja a algún estudiante.");
            return; // Salir del método
        }
        
        do {
            System.out.println("Introduce los datos del estudiante.");
            System.out.println("Código: ");
            String cod = entrada.nextLine();
                        
            // Verificar que el código no se haya dado de alta previamente
            //boolean repetidos = true;
            int idx;
            
            while((idx = buscarEstudiante(cod)) != -1) {
                     System.out.println("El código " + cod + 
                             " ya fue dado de alta previamente.");
                     System.out.println("Por favor escriba un " +
                             "código diferente");
                     // Pedir de nuevo el código
                     System.out.println("Código: ");
                     cod = entrada.nextLine();
            }
            
            // Pedir el resto de los valores de los campos y recibir el
            // objeto de tipo Estudiante con los campos llenos
            Estudiante e = leerDatos(cod);
            
            // Agregar el objeto al arreglo
            grupo[numEst] = e;
            numEst++; // Incrementar el número de estudiantes dados de alta
            
            System.out.println("Estudiante dado de alta exitosamente.");
            System.out.println();
            
            // Verificar si hay espacio en el arreglo
            if(numEst == TAM_ARR) {
                System.out.println("No es posible dar de alta más estudiantes.");
                System.out.println("Para dar de alta nuevos estudiantes, es necesario " +
                                "dar de baja a algún estudiante.");
                return; // Salir del método
            }
            
            System.out.println("¿Desea dar de alta otro estudiante (s/n)?");
            resp = entrada.nextLine();
            
        } while(resp.equalsIgnoreCase("S")); // Continuar mientras la respuesta 
                                                // sea "S" o "s"
    }
    
    /**
     * Método para desplegar el menú principal y que regrese la opción
     * seleccionada. Se debe verificar que la opción esté en el rango 
     * correcta.
     */
    public static int desplegarMenuPrincipal() {
        int opc = 0;
        Scanner entrada = new Scanner(System.in);
        
        // Desplegar el menú
        System.out.println("MENÚ PRINCIPAL");
        System.out.println(OPC_ALTA + ") Alta de estudiantes");
        System.out.println(OPC_CONS + ") Consulta de estudiantes");
        System.out.println(OPC_MODI + ") Modificación de datos");
        System.out.println(OPC_BAJA + ") Baja de estudiantes");
        System.out.println(OPC_LIST + ") Listado de estudiantes");
        System.out.println(OPC_SALI + ") Salir");
        System.out.println();
        System.out.print("Opción: ");
        
        // Leer la opción del teclado
        opc = entrada.nextInt();
        
        // Verificar si la opción no está en el rango correcto
        while(opc < 1 || opc > 6) {
            // Mostrar mensaje de error
            System.out.println("Opción no válida.");
            System.out.println("El rango válido es entre 1 y " + OPC_SALI + ".");
            // Pedir de nuevo la opción
            System.out.print("Opción: ");
            opc = entrada.nextInt();
        }
        
        // Regresar la opción seleccionada
        return opc;
    }
    
    /**
     * Método para leer del teclado los datos de un estudiante. Los datos
     * serán leídos del teclado utilizando un objeto de la clase Scanner.
     * El método regresará un objeto de la clase Estudiante con los valores
     * de los campos capturados por el usuario.
     * Se recibe el código del estudiante
     */
    public static Estudiante leerDatos(String codigo) {
        Estudiante e = null; // Declarar la variable con un valor inicial
        Scanner entrada; // Declarar variable
        
        // Crear objeto de tipo Scanner para leer datos del teclado
        entrada = new Scanner(System.in);
        
        // Crear objeto de tipo Estudiante
        e = new Estudiante();
        
//        System.out.println("Temporal:");
//        e.desplegarDatos();
        
        // Leer los valores para cada uno de los campos
        //System.out.println("Código: ");
        //String cod = entrada.nextLine();
        System.out.println("Nombre(s): ");
        String nom = entrada.nextLine();
        System.out.println("Apellido paterno: ");
        String apPat = entrada.nextLine();
        System.out.println("Apellido materno: ");
        String apMat = entrada.nextLine();
        System.out.println("Edad: ");
        int ed = entrada.nextInt();
        System.out.println("Promedio: ");
        double prom = entrada.nextDouble();
        
        // Introducir los valores leídos en los campos del objeto de tipo
        // Estudiante
        e.setCodigo(codigo);
        e.setNombre(nom);
        e.setApellidoPat(apPat);
        e.setApellidoMat(apMat);
        e.setEdad(ed);
        e.setPromedio(prom);
       
        return e; // Regresar objeto con los valores capturados
    } 
    
    
} 