/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.DetalleFactura;
import modelo.Factura;

/**
 *
 * @author hp
 */
public class FacturaControlador {

    ConexionBDD conexionBDD = new ConexionBDD();

    public int guardarFactura(Factura factura) {

        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            conexion = conexionBDD.conectar();

            // Iniciar transacción
            conexion.setAutoCommit(false);

            String sql = "INSERT INTO facturas(fecha, id_cliente, total) VALUES(?,?,?)";

            ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setDate(1, java.sql.Date.valueOf(factura.getFecha()));
            ps.setInt(2, factura.getCliente().getId());
            ps.setDouble(3, factura.calcularSubTotal());

            ps.executeUpdate();

            rs = ps.getGeneratedKeys();

            int idFactura = 0;

            if (rs.next()) {
                idFactura = rs.getInt(1);
            }

            // Guardar detalles
            DetalleFacturaControlador detalleControlador
                    = new DetalleFacturaControlador();

            for (DetalleFactura detalle : factura.getListaArticulos()) {

                boolean ok = detalleControlador.guardarDetalle(
                        conexion,
                        idFactura,
                        detalle);

                if (!ok) {
                    throw new SQLException("No se pudo guardar un detalle");
                }

            }

            // Confirmar transacción
            conexion.commit();

            return idFactura;

        } catch (SQLException e) {

            try {

                if (conexion != null) {
                    conexion.rollback();
                }

            } catch (SQLException ex) {

                System.out.println(ex.getMessage());

            }

            System.out.println("Error: " + e.getMessage());

            return -1;

        } finally {

            try {

                if (rs != null) {
                    rs.close();
                }

                if (ps != null) {
                    ps.close();
                }

                if (conexion != null) {

                    conexion.setAutoCommit(true);
                    conexion.close();

                }

            } catch (SQLException e) {

                System.out.println(e.getMessage());

            }

        }

    }
}
