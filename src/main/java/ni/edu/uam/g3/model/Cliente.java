package ni.edu.uam.g3.model;

public class Cliente {
    private int id;
    private String nombreCompleto;
    private String email;
    private String telefono;
    private String pais;
    private String ciudad;
    private double ingresoAnual;
    private int creditScore;
    private String estado;

    public Cliente(int id, String nombreCompleto, String email, String telefono,
                   String pais, String ciudad, double ingresoAnual, int creditScore, String estado) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.telefono = telefono;
        this.pais = pais;
        this.ciudad = ciudad;
        this.ingresoAnual = ingresoAnual;
        this.creditScore = creditScore;
        this.estado = estado;
    }

    public int getId() { return id; }
    public String getNombreCompleto() { return nombreCompleto; }
    public String getEmail() { return email; }
    public String getTelefono() { return telefono; }
    public String getPais() { return pais; }
    public String getCiudad() { return ciudad; }
    public double getIngresoAnual() { return ingresoAnual; }
    public int getCreditScore() { return creditScore; }
    public String getEstado() { return estado; }
}
