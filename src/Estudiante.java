
public class Estudiante {

    // Campos 
    private String codigo;
    private String nombre;
    private String apellidoPat;
    private String apellidoMat;
    private int edad;
    private double promedio;
        
    // Constructores
    public Estudiante() { 
        super();
        
        codigo = "Sin código";
        nombre = "Sin nombre";
        apellidoPat = "Sin apellido paterno";
        apellidoMat = "Sin apellido materno";
        edad = 0;
        promedio = 0.0;
    }
    
    public Estudiante(String codigo, String nombre, String apellidoPat,
                        String apellidoMat, int edad, double promedio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellidoPat = apellidoPat;
        this.apellidoMat = apellidoMat;
        this.edad = edad;
        this.promedio = promedio;
        
    }
    
    
    // Métodos
    
    // Métodos de acceso
    // Método de lectura para el campo "codigo"
    public String getCodigo() {
        return codigo;
    }
    
    // Método de escritura para el campo "codigo"
    public void setCodigo(String codigo) {
       
        this.codigo = codigo;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nuevoNombre) {
        this.nombre = nuevoNombre;
    }
    
    public String getApellidoPat() {
        return apellidoPat;
    }
    
    public void setApellidoPat(String nuevoApPat) {
        apellidoPat = nuevoApPat;
    }

    public String getApellidoMat() {
        return apellidoMat;
    }
    
    public void setApellidoMat(String nuevoApMat) {
        apellidoMat = nuevoApMat;
    }
    
    public int getEdad() {
        return edad;
    }
    
    public void setEdad(int nuevaEdad) {
        edad = nuevaEdad;
    }
    
    public double getPromedio() {
        return promedio;
    }
    
    public void setPromedio(double nuevoProm) {
        promedio = nuevoProm;
    }
    
    /**
     * Método que despliega los valores de cada uno de los campos
     */
    public void desplegarDatos() {
        //this.auxiliar();
        auxiliar();
    }
    
    /**
     * Método privado auxiliar de un método público.
     */
    private void auxiliar() {
        System.out.println("Código: " + codigo);
        System.out.println("Nombre(s): " + nombre);
        System.out.println("Apellido paterno: " + apellidoPat);
        System.out.println("Apellido materno: " + apellidoMat);
        System.out.println("Edad: " +  edad);
        System.out.println("Promedio: " + promedio);        
    }
    
} 
