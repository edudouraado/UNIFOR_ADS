package model;

public class Genero {
    private int idGenero;
    private String descGenero;

    public Genero(int idGenero, String descGenero) {
        this.idGenero = idGenero;
        this.descGenero = descGenero;
    }

    public int getIdGenero() { return idGenero; }
    public void setIdGenero(int idGenero) { this.idGenero = idGenero; }

    public String getDescGenero() { return descGenero; }
    public void setDescGenero(String descGenero) { this.descGenero = descGenero; }

    public String toString() {
        return "Genero{" + "id=" + idGenero + ", descricao='" + descGenero + "'}";
    }

    public void mostrar() {
        System.out.println(toString());
    }
}