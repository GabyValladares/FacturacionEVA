/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author LENOVO
 */


import java.util.ArrayList;
import java.util.List;

import modelo.Cliente;
import modelo.ClienteRegular;
import modelo.ClienteVIP;

public class ClienteDAO {

    public List<Cliente> listarClientes() {

        List<Cliente> lista = new ArrayList<>();

        lista.add(new ClienteRegular(1, "Juan Pérez",
                "juan@gmail.com", "099111111"));

        lista.add(new ClienteVIP(2, "María López",
                "maria@gmail.com", "099222222", 10));

        return lista;
    }

    public Cliente buscarPorId(int id) {

        for (Cliente c : listarClientes()) {

            if (c.getId() == id) {
                return c;
            }

        }

        return null;
    }

}
