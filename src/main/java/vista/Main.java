
package vista;

import controlador.ConexionBDD;

public class Main {
    public static void main(String[] args) {
        ConexionBDD c=new ConexionBDD();
        c.conectar();
    }
    
}