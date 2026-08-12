package modelo;

public class Numero {

    private double valor;
    private String nombre;
    private char simbolo;

    public Numero() {
    }

    public Numero(double valor, String nombre, char simbolo) {
        this.valor = valor;
        this.nombre = nombre;
        this.simbolo = simbolo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public char getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(char simbolo) {
        this.simbolo = simbolo;
    }

    @Override
    public String toString() {
    return "Valor :"+valor;
    }

    
    //metodos regla del negocio 
    //recursividad, auto invocar la clase dentro de si misma 
    public double sumar(Numero n2){
        // sin recursividad 
//        double n1 =0;
//        double n1 =0;
//        return n1+n2;

// la recurisivdad es auto invocarse 
        return this.valor + n2.getValor();
                //getValor() + n2.getValor();
    }
    
}
