package modelo;

public class Grupo {
    private int idGrupo;
    private String nombreGrupo;

    public Grupo(int idGrupo, String nombreGrupo) {
        this.idGrupo = idGrupo;
        this.nombreGrupo = nombreGrupo;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    

    // Vital para que el JComboBox muestre el texto correcto
    @Override
    public String toString() {
        return this.nombreGrupo;
    }
}