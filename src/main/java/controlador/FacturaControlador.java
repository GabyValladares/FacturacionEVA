/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.DetalleFactura;
import modelo.Factura;

/**
 *
 * @author hp
 */
public class FacturaControlador {

    ConexionBDD conexionBDD = new ConexionBDD();

    public int guardarFactura(Factura factura) {

        try {

            Connection conexion = conexionBDD.conectar();

            // Guardar la factura
            String sql = "INSERT INTO facturas(fecha, id_cliente, total) VALUES(?,?,?)";

            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setDate(1, java.sql.Date.valueOf(factura.getFecha()));
            ps.setInt(2, factura.getCliente().getId());
            ps.setDouble(3, factura.calcularTotalNeto());

            ps.executeUpdate();

            // Obtener el último id de la factura
            String consulta = "SELECT id_factura FROM facturas ORDER BY id_factura DESC LIMIT 1";

            PreparedStatement ps2 = conexion.prepareStatement(consulta);

            ResultSet rs = ps2.executeQuery();

            int idFactura = 0;

            if (rs.next()) {
                idFactura = rs.getInt("id_factura");
            }

            // Guardar los detalles de la factura
            DetalleFacturaControlador detalleControlador = new DetalleFacturaControlador();

            for (DetalleFactura detalle : factura.getListaArticulos()) {

                detalleControlador.guardarDetalle(idFactura, detalle);

            }

            rs.close();
            ps.close();
            ps2.close();
            conexion.close();

            return idFactura;

        } catch (SQLException e) {

            System.out.println("Error al guardar factura: " + e.getMessage());

            return -1;

        }

    }
}
