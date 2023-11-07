public class Contacto {
    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String ciudad;

    public Contacto(int id, String nombre, String apellido, String email, String telefono, String ciudad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.ciudad = ciudad;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCiudad() {
        return ciudad;
    }
}
