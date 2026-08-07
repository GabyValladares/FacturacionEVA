/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import controlador.ClienteControlador;
import controlador.FacturaControlador;
import controlador.ProductoControlador;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import modelo.Cliente;
import modelo.DetalleFactura;
import modelo.Factura;
import modelo.Producto;

/**
 *
 * @author hp
 */
public class FacturaVista extends javax.swing.JFrame {

    private List<Producto> listaProductos;
    private java.util.List<modelo.Cliente> clientes = new java.util.ArrayList<>();
    private double subtotalParcialAcumulado = 0.0;
    private modelo.Cliente clienteSeleccionado;
    private java.util.List<modelo.DetalleFactura> listaDetallesTemporales = new java.util.ArrayList<>();
    private javax.swing.JTextField txtIdFactura;
    private double sumaSubtotal15 = 0.0;
    private double sumaSubtotal0 = 0.0;
    private double sumaIvaTotal = 0.0;
    /**
     * Creates new form FacturaVista
     */
    public FacturaVista() {
        initComponents();
        txtFecha.setText(LocalDate.now().toString());
        txtFecha.setEditable(false);
        cargarProductos();
        cargarClientes();
    }

    // Cargar productos en el combo box
    public void cargarProductos() {
        ProductoControlador pc = new ProductoControlador();
        this.listaProductos = pc.obtenerTodosProductos();
        
        DefaultComboBoxModel<String> modeloCombo = new DefaultComboBoxModel<>();
        modeloCombo.addElement("--- Seleccione un Producto ---");
        
        for (Producto prod : this.listaProductos) {
            modeloCombo.addElement(prod.getNombre());
        }
        
        cmbProductos.setModel(modeloCombo);
    }

  private void cargarClientes() {
    controlador.ClienteControlador clienteControlador = new controlador.ClienteControlador();
    
    // 1. Usamos el NUEVO método y guardamos el resultado en la lista global
    this.clientes = clienteControlador.listarClientesObjeto(); 
    
    // 2. Limpiamos y llenamos el ComboBox
    cmbClientes.removeAllItems();
    cmbClientes.addItem("--- Seleccione un Cliente ---");
    
    if (this.clientes != null) {
        for (modelo.Cliente c : this.clientes) {
            // Mostramos solo el nombre en la interfaz gráfica
            cmbClientes.addItem(c.getNombre()); 
        }
    }
}
    
    public void calcularSubtotal() {
        try {
            // Solo calculamos si hay texto en Precio y Cantidad
            if (!txtPrecio.getText().isEmpty() && !txtCantidad.getText().isEmpty()) {

                double precio = Double.parseDouble(txtPrecio.getText());
                int cantidad = Integer.parseInt(txtCantidad.getText());

                double subtotal = precio * cantidad;

                int indiceCliente = cmbClientes.getSelectedIndex();
                if (indiceCliente > 0) {
                    controlador.ClienteControlador cc = new controlador.ClienteControlador();
                    java.util.ArrayList<String[]> lista = cc.obtenerClientes();

                    String descuentoStr = lista.get(indiceCliente - 1)[5];

                    if (descuentoStr != null && !descuentoStr.isEmpty()) {
                        double descuentoVip = Double.parseDouble(descuentoStr);

                        double montoDescuento = subtotal * (descuentoVip / 100.0);
                        subtotal = subtotal - montoDescuento;
                    }
                }

                // Mostramos el resultado redondeado a 2 decimales
                txtSubTotal.setText(String.format("%.2f", subtotal));

            } else {
                txtSubTotal.setText(""); // Limpiamos si faltan datos
            }
        } catch (NumberFormatException e) {
            // Evita errores si el usuario tipea letras por accidente en la cantidad
            txtSubTotal.setText("");
        }
}
      public int productoSeleccionado(){
     if (listaProductos != null && !listaProductos.isEmpty()) {
        return cmbProductos.getSelectedIndex();
    }

    return -1;

    }
     public void limpiarDetalle(){
        txtPrecio.setText("");
        txtCantidad.setText("");
        txtSubTotal.setText("");
    }
     public int clienteSeleccionado(){
     if (clientes != null && !clientes.isEmpty()) {
        return cmbClientes.getSelectedIndex();
    }
    return -1;
     }
     
     private void limpiarFormularioCompleto() {
    this.clienteSeleccionado = null;
    this.listaDetallesTemporales.clear();
    
    // Resetear cajas de texto
    if (txtIdFactura != null) txtIdFactura.setText("");
    if (txtFecha != null) txtFecha.setText("");
     }

 private void actualizarTotalesFactura() {
        // Calculamos el valor final a pagar sumando nuestros acumuladores
        double totalPagar = sumaSubtotal15 + sumaSubtotal0 + sumaIvaTotal;
        
        // Mostramos los resultados en los campos de texto
        txtSubtotal15.setText(String.format("%.2f", sumaSubtotal15));
        txtSubtotal0.setText(String.format("%.2f", sumaSubtotal0));
        txtIvaTotal.setText(String.format("%.2f", sumaIvaTotal));
        txtTotalGeneral.setText(String.format("%.2f", totalPagar));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        lblTitulo = new javax.swing.JLabel();
        lblNombres = new javax.swing.JLabel();
        cmbClientes = new javax.swing.JComboBox<>();
        lblFecha = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        cmbProductos = new javax.swing.JComboBox<>();
        lblDetalleProductos = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtADetalle = new javax.swing.JTextArea();
        lblProducto = new javax.swing.JLabel();
        lblCantidad = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        lblSubTotal = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JTextField();
        lblPrecio = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        btnPDF = new javax.swing.JButton();
        lblCedula = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        lblCorreo = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        lblTipoCliente = new javax.swing.JLabel();
        lblNumeroTelefono = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        lblDireccion = new javax.swing.JLabel();
        txtNumeroTelefono = new javax.swing.JTextField();
        btnGenerar = new javax.swing.JButton();
        rbtnVIP = new javax.swing.JRadioButton();
        rbtnRegular = new javax.swing.JRadioButton();
        barraProgreso = new javax.swing.JProgressBar();
        lblDatosComprador = new javax.swing.JLabel();
        lblDescuento = new javax.swing.JLabel();
        txtDescuento = new javax.swing.JTextField();
        lblSubTotal1 = new javax.swing.JLabel();
        chkIva = new javax.swing.JCheckBox();
        lblSubtotal15 = new javax.swing.JLabel();
        txtSubtotal15 = new javax.swing.JTextField();
        lblSubtotal0 = new javax.swing.JLabel();
        txtSubtotal0 = new javax.swing.JTextField();
        lblSubtotal1 = new javax.swing.JLabel();
        txtIvaTotal = new javax.swing.JTextField();
        lblTotal = new javax.swing.JLabel();
        txtTotalGeneral = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        lblTitulo.setText("GESTIÓN DE FACTURAS");

        lblNombres.setText("Clientes:");

        cmbClientes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbClientesActionPerformed(evt);
            }
        });

        lblFecha.setText("Fecha:");

        txtFecha.setEditable(false);

        cmbProductos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbProductosMouseClicked(evt);
            }
        });
        cmbProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProductosActionPerformed(evt);
            }
        });

        lblDetalleProductos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDetalleProductos.setText("DETALLE DE LOS PRODUCTOS");

        txtADetalle.setEditable(false);
        txtADetalle.setColumns(20);
        txtADetalle.setRows(5);
        jScrollPane1.setViewportView(txtADetalle);

        lblProducto.setText("PRODUCTO");

        lblCantidad.setText("CANTIDAD");

        txtCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadActionPerformed(evt);
            }
        });
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadKeyReleased(evt);
            }
        });

        lblSubTotal.setText("SUBTOTAL");

        txtSubTotal.setEditable(false);

        lblPrecio.setText("PRECIO");

        txtPrecio.setEditable(false);
        txtPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioActionPerformed(evt);
            }
        });

        btnAgregar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnAgregar.setText("+");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnPDF.setText("GENERAR PDF");
        btnPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFActionPerformed(evt);
            }
        });

        lblCedula.setText("Cédula");

        txtCedula.setEditable(false);
        txtCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCedulaActionPerformed(evt);
            }
        });

        lblCorreo.setText("Correo");

        txtCorreo.setEditable(false);
        txtCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoActionPerformed(evt);
            }
        });

        lblTipoCliente.setText("Tipo Cliente");

        lblNumeroTelefono.setText("Teléfono");

        txtDireccion.setEditable(false);
        txtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionActionPerformed(evt);
            }
        });

        lblDireccion.setText("Dirección");

        txtNumeroTelefono.setEditable(false);

        btnGenerar.setText("GUARDAR DATOS");
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtnVIP);
        rbtnVIP.setText("VIP");
        rbtnVIP.setEnabled(false);
        rbtnVIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnVIPActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtnRegular);
        rbtnRegular.setText("Regular");
        rbtnRegular.setEnabled(false);
        rbtnRegular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnRegularActionPerformed(evt);
            }
        });

        barraProgreso.setStringPainted(true);

        lblDatosComprador.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDatosComprador.setText("DATOS DEL COMPRADOR");

        lblDescuento.setText("DESCUENTO");

        txtDescuento.setEditable(false);

        lblSubTotal1.setText("Aplica IVA");

        chkIva.setText("Sí");

        lblSubtotal15.setText("Subtotal 15%: $");

        txtSubtotal15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSubtotal15ActionPerformed(evt);
            }
        });

        lblSubtotal0.setText("Subtotal  0%:");

        txtSubtotal0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSubtotal0ActionPerformed(evt);
            }
        });

        lblSubtotal1.setText("IVA 15%:");

        txtIvaTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIvaTotalActionPerformed(evt);
            }
        });

        lblTotal.setText("Total:");

        txtTotalGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalGeneralActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(128, 128, 128)
                                .addComponent(lblCantidad)
                                .addGap(46, 46, 46)
                                .addComponent(lblPrecio))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmbProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDescuento, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                            .addComponent(lblDescuento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblDatosComprador, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(lblNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(45, 45, 45)
                                        .addComponent(barraProgreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtCorreo))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtCedula)))
                                        .addGap(203, 203, 203)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(lblNumeroTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtNumeroTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblTipoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(rbtnVIP)
                                                .addGap(18, 18, 18)
                                                .addComponent(rbtnRegular))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblFecha)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(28, 28, 28)))))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSubTotal1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(chkIva)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAgregar)))
                .addGap(72, 72, 72))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(lblDetalleProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 743, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addComponent(lblTitulo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(btnPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblSubtotal15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtSubtotal15, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblSubtotal0)
                                            .addComponent(lblSubtotal1))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtIvaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtSubtotal0, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGap(88, 88, 88)
                                            .addComponent(txtTotalGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(lblTotal)))
                                .addGap(84, 84, 84))
                            .addComponent(btnGenerar))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtCedula, txtCorreo, txtDireccion});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(lblTitulo)
                .addGap(8, 8, 8)
                .addComponent(lblDatosComprador)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombres)
                    .addComponent(cmbClientes)
                    .addComponent(barraProgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFecha)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCedula))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCorreo)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTipoCliente)
                    .addComponent(rbtnVIP)
                    .addComponent(rbtnRegular))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumeroTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDireccion)
                    .addComponent(lblNumeroTelefono))
                .addGap(33, 33, 33)
                .addComponent(lblDetalleProductos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProducto)
                    .addComponent(lblCantidad)
                    .addComponent(lblPrecio)
                    .addComponent(lblDescuento)
                    .addComponent(lblSubTotal1)
                    .addComponent(lblSubTotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chkIva))
                    .addComponent(btnAgregar))
                .addGap(38, 38, 38)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSubtotal15)
                    .addComponent(txtSubtotal15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSubtotal0)
                    .addComponent(txtSubtotal0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSubtotal1)
                    .addComponent(txtIvaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPDF)
                    .addComponent(btnGenerar))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotalGeneral, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotal, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(61, 61, 61))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbClientesActionPerformed
     int index = cmbClientes.getSelectedIndex();
    
    if (index > 0) {
        // 1. Obtenemos el cliente seleccionado
        modelo.Cliente cliente = clientes.get(index - 1);
        
        // 2. Llenamos las cajas de texto básicas
        txtCedula.setText(cliente.getCedula());
        txtCorreo.setText(cliente.getEmail());
        txtNumeroTelefono.setText(cliente.getTelefono()); 
        txtDireccion.setText(cliente.getDireccion());
        
        // 3. POLIMORFISMO: Obtenemos el tipo y llenamos el Descuento
        String tipo = cliente.getTipoCliente();
        
        if ("VIP".equals(tipo)) {
            rbtnVIP.setSelected(true);
            txtDescuento.setText("10"); 
        } else if ("Regular".equals(tipo)) {
            rbtnRegular.setSelected(true);
            txtDescuento.setText("0");  
        } else if ("Frecuente".equals(tipo)) {
            // rbtnFrecuente.setSelected(true); 
            txtDescuento.setText("5");  
        }

        
        // 4. ACTUALIZACIÓN DE LA BARRA DE PROGRESO (50%)
        barraProgreso.setVisible(true);
        barraProgreso.setIndeterminate(false); // Apagamos la animación de lado a lado
        barraProgreso.setStringPainted(true);  // Permitimos que se vea el texto en la barra
        barraProgreso.setValue(50);            // Llenamos la barra hasta la mitad
        barraProgreso.setString("50% - Cliente Seleccionado"); 
        
    } else {
        // Si el usuario selecciona "--- Seleccione ---", limpiamos todo
        txtCedula.setText("");
        txtCorreo.setText("");
        txtNumeroTelefono.setText("");
        txtDireccion.setText("");
        txtDescuento.setText(""); 
        buttonGroup1.clearSelection(); 
        
        // Reiniciamos la barra de progreso a 0
        barraProgreso.setValue(0);
        barraProgreso.setString("");
        barraProgreso.setVisible(false);
    }
    }//GEN-LAST:event_cmbClientesActionPerformed

    private void cmbProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProductosActionPerformed
        int indice = cmbProductos.getSelectedIndex();

        // El índice 0 es "--- Seleccione un Producto ---"
        if (indice > 0) {
            // Obtenemos el producto de tu lista global listaProductos
            modelo.Producto productoSeleccionado = this.listaProductos.get(indice - 1);
            
            // Colocamos el precio en la caja de texto
            txtPrecio.setText(String.valueOf(productoSeleccionado.getPrecio()));
            
            // Si ya hay una cantidad escrita, calculamos de una vez
            calcularSubtotal(); 
        } else {
            // Si regresa a "Seleccione un producto", limpiamos
            txtPrecio.setText("");
            txtSubTotal.setText("");
        }
    }//GEN-LAST:event_cmbProductosActionPerformed

    private void btnPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPDFActionPerformed

    private void txtPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioActionPerformed
        
    }//GEN-LAST:event_txtPrecioActionPerformed

    private void cmbProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbProductosMouseClicked
        // 1. Obtenemos el índice del producto seleccionado
    int indiceSeleccionado = cmbProductos.getSelectedIndex();
    
    // 2. Si seleccionó un producto válido (índice mayor a 0)
    if (indiceSeleccionado > 0) {
        // Traemos el producto de la lista (restamos 1 por el mensaje por defecto)
        Producto prod = this.listaProductos.get(indiceSeleccionado - 1);
        
        // Escribimos su precio en la caja txtPrecio
        txtPrecio.setText(Double.toString(prod.getPrecio()));
    } else {
        // Si vuelve a seleccionar "--- Seleccione un Producto ---", limpiamos el precio
        txtPrecio.setText("");
        }
    }//GEN-LAST:event_cmbProductosMouseClicked

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed
        calcularSubtotal();     
    }//GEN-LAST:event_txtCantidadActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
try {
        // 1. Obtener valores básicos del producto
        String producto = cmbProductos.getSelectedItem().toString(); 
        double precio = Double.parseDouble(txtPrecio.getText().trim());
        int cantidad = Integer.parseInt(txtCantidad.getText().trim());
        
        // 2. Calcular el Subtotal Bruto
        double subtotalBruto = precio * cantidad;
        
        // 3. POLIMORFISMO: Obtenemos el cliente actual del ComboBox y le pedimos que calcule
        int indexCliente = cmbClientes.getSelectedIndex();
        modelo.Cliente clienteActual = clientes.get(indexCliente - 1);
        
        // ¡Magia! Si es VIP usa la fidelidad, si es Regular verifica si pasa de 1000.
        double valorDescontado = clienteActual.calcularDescuento(subtotalBruto);
        
        // Mostramos el descuento generado en la cajita
        txtDescuento.setText(String.format("%.2f", valorDescontado));

        // 4. Calcular el Subtotal Real de la fila
        double subtotalFila = subtotalBruto - valorDescontado;
        
        // 5. Lógica del CheckBox (IVA 15%)
        double ivaFila = 0.0;
        String aplicaIva = "NO";
        
        if (chkIva.isSelected()) {
            ivaFila = subtotalFila * 0.15; 
            aplicaIva = "SÍ";
            
            sumaSubtotal15 += subtotalFila;
            sumaIvaTotal += ivaFila;
        } else {
            sumaSubtotal0 += subtotalFila;
        }

        // 6. Escribir en el txtADetalle
        String lineaDetalle = String.format("%s \t| %d \t| $ %.2f \t| $ %.2f \t| $ %.2f \t| %s \t| $ %.2f\n", 
                                            producto, cantidad, precio, valorDescontado, subtotalFila, aplicaIva, ivaFila);
        txtADetalle.append(lineaDetalle);

        // 7. Limpiar campos
        txtPrecio.setText("");
        txtCantidad.setText("");
        chkIva.setSelected(false);
        
        // 8. Actualizar totales globales
        actualizarTotalesFactura();

    } catch (NumberFormatException e) {
        javax.swing.JOptionPane.showMessageDialog(this, 
            "Asegúrese de ingresar números válidos en Precio y Cantidad.", 
            "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
  }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void txtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyReleased
        calcularSubtotal();
    }//GEN-LAST:event_txtCantidadKeyReleased

    private void txtCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCedulaActionPerformed

    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionActionPerformed

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
 int indexCliente = cmbClientes.getSelectedIndex();

    // 1. Validar que se haya seleccionado un cliente (si la posición 0 es "--- Seleccione ---")
    if (indexCliente <= 0) {
        javax.swing.JOptionPane.showMessageDialog(this, "Debe seleccionar un cliente válido.", "Atención", javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }

    if (listaDetallesTemporales == null || listaDetallesTemporales.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, "Debe agregar al menos un producto al detalle.", "Atención", javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }

    try {
        java.time.LocalDate fechaLocalDate = java.time.LocalDate.parse(txtFecha.getText().trim());

        // 2. Obtener el objeto Cliente desde tu lista usando el índice
        modelo.Cliente clienteSeleccionado = clientes.get(indexCliente - 1); 

        // 3. Crear e insertar la factura
        Factura nuevaFactura = new Factura(0, fechaLocalDate, clienteSeleccionado, listaDetallesTemporales);

        FacturaControlador facturaControlador = new FacturaControlador();
        boolean guardadoExitoso = facturaControlador.guardarFactura(nuevaFactura);

        if (guardadoExitoso) {
            
            // 4. ACTUALIZACIÓN DE LA BARRA DE PROGRESO AL GUARDAR (100%)
          
            barraProgreso.setValue(100);
            barraProgreso.setString("100% - Factura Procesada");
            
            // El JOptionPane pausa la ejecución visualmente, así el cajero puede ver el 100%
            javax.swing.JOptionPane.showMessageDialog(this, "¡Factura guardada con éxito en MySQL!", "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            
            // Al hacer clic en "Aceptar" en el mensaje, limpiamos todo
            limpiarFormularioCompleto();
            
            // Si tu método limpiarFormularioCompleto() no oculta y reinicia la barra, agrégalo aquí:
            barraProgreso.setValue(0);
            barraProgreso.setString("");
            barraProgreso.setVisible(false);
            
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Error al guardar en la base de datos.", "Error BD", javax.swing.JOptionPane.ERROR_MESSAGE);
        }

    } catch (java.time.format.DateTimeParseException e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Use AAAA-MM-DD.", "Error de Fecha", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnGenerarActionPerformed

    private void rbtnVIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnVIPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtnVIPActionPerformed

    private void rbtnRegularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnRegularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtnRegularActionPerformed

    private void txtSubtotal15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSubtotal15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSubtotal15ActionPerformed

    private void txtSubtotal0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSubtotal0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSubtotal0ActionPerformed

    private void txtIvaTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIvaTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIvaTotalActionPerformed

    private void txtTotalGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalGeneralActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalGeneralActionPerformed

    private void txtCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FacturaVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FacturaVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FacturaVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FacturaVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FacturaVista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barraProgreso;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnGenerar;
    private javax.swing.JButton btnPDF;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chkIva;
    private javax.swing.JComboBox<String> cmbClientes;
    private javax.swing.JComboBox<String> cmbProductos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblCedula;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblDatosComprador;
    private javax.swing.JLabel lblDescuento;
    private javax.swing.JLabel lblDetalleProductos;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblNombres;
    private javax.swing.JLabel lblNumeroTelefono;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblProducto;
    private javax.swing.JLabel lblSubTotal;
    private javax.swing.JLabel lblSubTotal1;
    private javax.swing.JLabel lblSubtotal0;
    private javax.swing.JLabel lblSubtotal1;
    private javax.swing.JLabel lblSubtotal15;
    private javax.swing.JLabel lblTipoCliente;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JRadioButton rbtnRegular;
    private javax.swing.JRadioButton rbtnVIP;
    private javax.swing.JTextArea txtADetalle;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDescuento;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtIvaTotal;
    private javax.swing.JTextField txtNumeroTelefono;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtSubtotal0;
    private javax.swing.JTextField txtSubtotal15;
    private javax.swing.JTextField txtTotalGeneral;
    // End of variables declaration//GEN-END:variables
}
