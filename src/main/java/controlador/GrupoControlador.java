package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;
import modelo.Grupo;
import vista.ClientesVista;

public class GrupoControlador implements ActionListener {
    
    private ConexionBDD conectar = new ConexionBDD();
    private ClienteControlador clienteControlador = new ClienteControlador();
    
    private ClientesVista vista;
    private ArrayList<Cliente> listaClientesMemoria = new ArrayList<>();
    private ArrayList<Grupo> listaGruposMemoria = new ArrayList<>();
    private boolean actualizando = false;

    public GrupoControlador() {
    }

    public GrupoControlador(ClientesVista vista) {
        this.vista = vista;
        this.vista.getComboGrupos().addActionListener(this);
        this.vista.getComboClientes().addActionListener(this);
    }

    public void iniciar() {
        actualizando = true;
        
        listaGruposMemoria = obtenerGrupos();
        ArrayList<Cliente> clientesBDD = clienteControlador.listarClientesObjeto();
        
        if (clientesBDD != null) {
            listaClientesMemoria = clientesBDD;
        }
        
        llenarComboGrupos();
        llenarComboClientes(listaClientesMemoria);
        
        actualizando = false;
    }

    public ArrayList<Grupo> obtenerGrupos() {
        ArrayList<Grupo> listaGrupos = new ArrayList<>();
        String sentenciaSQL = "SELECT * FROM grupos;";

        try {
            Connection conectado = conectar.conectar();
            PreparedStatement ejecutar = conectado.prepareStatement(sentenciaSQL);
            ResultSet res = ejecutar.executeQuery();

            while (res.next()) {
                Grupo grupo = new Grupo(
                    res.getInt("id_grupo"),
                    res.getString("nombre_grupo")
                );
                listaGrupos.add(grupo);
            }
            res.close();
            ejecutar.close();
            conectado.close();
        } catch (SQLException e) {
            // Error en BD (silenciado de consola)
        }
        return listaGrupos;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getComboGrupos()) {
            filtrarClientesPorGrupo();
        } else if (e.getSource() == vista.getComboClientes()) {
            sincronizarGrupoPorCliente();
        }
    }

    private void filtrarClientesPorGrupo() {
        if (actualizando || listaClientesMemoria == null || vista.getComboGrupos().getSelectedItem() == null) {
            return;
        }

        Object item = vista.getComboGrupos().getSelectedItem();
        
        if (item instanceof Grupo) {
            Grupo grupoSeleccionado = (Grupo) item;

            actualizando = true;
            vista.getComboClientes().removeAllItems();
            
            modelo.ClienteRegular placeholder = new modelo.ClienteRegular();
            placeholder.setId(0);
            placeholder.setNombre("--- Seleccione Cliente ---");
            vista.getComboClientes().addItem(placeholder);

            for (Cliente c : listaClientesMemoria) {
                if (grupoSeleccionado.getIdGrupo() == 0 || grupoSeleccionado.getIdGrupo() == c.getId()) {
                    vista.getComboClientes().addItem(c);
                }
            }
            actualizando = false;
        }
    }

    private void sincronizarGrupoPorCliente() {
        if (!actualizando && vista.getComboClientes().getSelectedIndex() > 0) {
            Cliente clienteSeleccionado = (Cliente) vista.getComboClientes().getSelectedItem();
            actualizando = true;

            for (int i = 0; i < vista.getComboGrupos().getItemCount(); i++) {
                Grupo g = (Grupo) vista.getComboGrupos().getItemAt(i);
                if (g != null && g.getIdGrupo() == clienteSeleccionado.getId()) {
                    vista.getComboGrupos().setSelectedIndex(i);
                    break;
                }
            }
            actualizando = false;
        }
    }

    private void llenarComboGrupos() {
        vista.getComboGrupos().removeAllItems();
        vista.getComboGrupos().addItem(new Grupo(0, "--- Seleccione Grupo ---"));
        for (Grupo g : listaGruposMemoria) {
            vista.getComboGrupos().addItem(g);
        }
    }

    private void llenarComboClientes(List<Cliente> clientes) {
        actualizando = true;
        vista.getComboClientes().removeAllItems();
        
        modelo.ClienteRegular placeholder = new modelo.ClienteRegular();
        placeholder.setId(0);
        placeholder.setNombre("--- Seleccione Cliente ---");
        vista.getComboClientes().addItem(placeholder);
        
        for (Cliente c : clientes) {
            vista.getComboClientes().addItem(c);
        }
        actualizando = false;
    }
}