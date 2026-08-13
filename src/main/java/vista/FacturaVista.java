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
    private java.util.ArrayList<modelo.Producto> listaProductosGlobal = new java.util.ArrayList<>();
    private List<Producto> listaProductos;
    private java.util.List<modelo.Cliente> clientes = new java.util.ArrayList<>();
    private double subtotalParcialAcumulado = 0.0;
    private modelo.Cliente clienteSeleccionado;
    private java.util.List<modelo.DetalleFactura> listaDetallesTemporales = new java.util.ArrayList<>();
    private javax.swing.JTextField txtIdFactura;
    private double sumaSubtotal15 = 0.0;
    private    double sumaSubtotal0 = 0.0;
    private double sumaIvaTotal = 0.0;
    javax.swing.table.DefaultTableModel modeloTablaDetalle;
    private java.util.ArrayList<modelo.Cliente> listaClientesGlobal = new java.util.ArrayList<>();
    
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
    controlador.ProductoControlador pc = new controlador.ProductoControlador();
    listaProductosGlobal = pc.listarProductosObjeto(); // Guarda en la lista global

    cmbProductos.removeAllItems();
    cmbProductos.addItem("--- Seleccione Producto ---");

    if (listaProductosGlobal != null) {
        for (modelo.Producto p : listaProductosGlobal) {
            cmbProductos.addItem(p.getNombre());
        }
    }
}

  public void cargarClientes() {
    controlador.ClienteControlador cc = new controlador.ClienteControlador();
//    listaClientesGlobal = cc.listarClientesObjeto();

    cmbClientes.removeAllItems();
    cmbClientes.addItem("--- Seleccione Cliente ---");

    if (listaClientesGlobal != null) {
        for (modelo.Cliente c : listaClientesGlobal) {
            // Se agrega únicamente el nombre al combo box
            cmbClientes.addItem(c.getNombre());
        }
    }
}
    
    public void calcularSubtotal() {
    try {
        String textoPrecio = txtPrecio.getText().trim();
        
        // Calculamos solo si el campo de precio contiene información
        if (!textoPrecio.isEmpty()) {

            // 1. Convertir comas a puntos para evitar que parseDouble falle
            double precio = Double.parseDouble(textoPrecio.replace(",", "."));
            int cantidad = (int) spnCantidad.getValue();

            double subtotal = precio * cantidad;

            // 2. Usar la lista global de clientes almacenada en memoria
            int indiceCliente = cmbClientes.getSelectedIndex();
            if (indiceCliente > 0 && listaClientesGlobal != null && (indiceCliente - 1) < listaClientesGlobal.size()) {
                modelo.Cliente cliente = listaClientesGlobal.get(indiceCliente - 1);
                
                double descuentoVip = cliente.getDescuentoVip();
                if (descuentoVip > 0) {
                    double montoDescuento = subtotal * (descuentoVip / 100.0);
                    subtotal -= montoDescuento;
                }
            }

            // 3. Formatear y asignar el subtotal calculado
            txtSubTotal.setText(String.format("%.2f", subtotal));

        } else {
            txtSubTotal.setText(""); 
        }
    } catch (NumberFormatException e) {
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
        spnCantidad.setValue(1);
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
    if (this.modeloTablaDetalle != null) {
        this.modeloTablaDetalle.setRowCount(0);
    }
    
    // Resetear cajas de texto
    if (txtIdFactura != null) txtIdFactura.setText("");
    if (txtFecha != null) txtFecha.setText("");
    // Adicionales para limpiar todo si es necesario
    txtCedula.setText("");
    txtCorreo.setText("");
    txtNumeroTelefono.setText("");
    txtDireccion.setText("");
    txtDescuento.setText("");
    limpiarDetalle();
     }

 private void actualizarTotalesFactura() {
        // 1. Reiniciar los acumuladores globales para no duplicar sumas
    sumaSubtotal15 = 0.0;
    sumaSubtotal0 = 0.0;
    sumaIvaTotal = 0.0;

    javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tblDetalle.getModel();

    // 2. Recorrer la tabla y calcular los valores según la columna Aplica IVA
    for (int i = 0; i < model.getRowCount(); i++) {
        double subtotalFila = Double.parseDouble(model.getValueAt(i, 4).toString().replace(",", "."));
        String aplicaIva = model.getValueAt(i, 5).toString();

        if (aplicaIva.equalsIgnoreCase("Sí") || aplicaIva.equalsIgnoreCase("Si")) {
            sumaSubtotal15 += subtotalFila;
            sumaIvaTotal += subtotalFila * 0.15;
        } else {
            sumaSubtotal0 += subtotalFila;
        }
    }

    // 3. Calcular el total final
    double totalPagar = sumaSubtotal15 + sumaSubtotal0 + sumaIvaTotal;

    // 4. Mostrar los resultados formateados en la interfaz
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
        tblDetalle = new javax.swing.JTable();
        lblProducto = new javax.swing.JLabel();
        lblCantidad = new javax.swing.JLabel();
        spnCantidad = new javax.swing.JSpinner();
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

        tblDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Producto", "Cantidad", "Precio Unit.", "Descuento", "Subtotal", "Aplica IVA", "IVA (15%)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblDetalle);

        lblProducto.setText("PRODUCTO");

        lblCantidad.setText("CANTIDAD");

        spnCantidad.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        spnCantidad.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnCantidadStateChanged(evt);
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
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
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
                                        .addComponent(spnCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtDescuento)
                                    .addComponent(lblDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
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
                                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(28, 28, 28)))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSubTotal1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(chkIva)
                                .addGap(48, 48, 48)
                                .addComponent(btnAgregar)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 15, Short.MAX_VALUE)
                                        .addComponent(lblDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtCorreo, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtCedula, javax.swing.GroupLayout.Alignment.TRAILING))))
                                .addGap(467, 467, 467))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDatosComprador, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(48, 48, 48)
                                        .addComponent(cmbClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(86, 86, 86)
                                        .addComponent(barraProgreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(72, 72, 72))))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spnCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkIva))
                        .addGap(48, 48, 48)
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
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(btnAgregar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbClientesActionPerformed
int index = cmbClientes.getSelectedIndex();

    if (index > 0 && listaClientesGlobal != null && (index - 1) < listaClientesGlobal.size()) {
        modelo.Cliente cliente = listaClientesGlobal.get(index - 1);

        txtCedula.setText(cliente.getCedula());
        txtCorreo.setText(cliente.getEmail());
        txtDireccion.setText(cliente.getDireccion());
        txtNumeroTelefono.setText(cliente.getTelefono());

        // Marcar el Radio Button según la instancia o tipo del cliente
        if (cliente instanceof modelo.ClienteVIP) {
            rbtnVIP.setSelected(true);
        } else {
            rbtnRegular.setSelected(true);
        }

    } else {
        // Limpieza de campos
        txtCedula.setText("");
        txtCorreo.setText("");
        txtDireccion.setText("");
        txtNumeroTelefono.setText("");

        // Desmarcar Radio Buttons si hay un ButtonGroup
        if (buttonGroup1 != null) {
            buttonGroup1.clearSelection();
        } else {
            rbtnVIP.setSelected(false);
            rbtnRegular.setSelected(false);
        }
    }
    }//GEN-LAST:event_cmbClientesActionPerformed

    private void cmbProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProductosActionPerformed
        int index = cmbProductos.getSelectedIndex();

    if (index > 0 && listaProductosGlobal != null && (index - 1) < listaProductosGlobal.size()) {
        modelo.Producto producto = listaProductosGlobal.get(index - 1);

        // Se asigna el precio formateado a texto
        txtPrecio.setText(String.format("%.2f", producto.getPrecio()));
        spnCantidad.setValue(1);

        // Se invoca el cálculo inmediato
        calcularSubtotal();
    } else {
        txtPrecio.setText("");
        txtSubTotal.setText("");
        spnCantidad.setValue(1);
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

    private void spnCantidadStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnCantidadStateChanged
        calcularSubtotal();     
    }//GEN-LAST:event_spnCantidadStateChanged

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
int index = cmbProductos.getSelectedIndex();

    // Valida que el combo no esté en el placeholder y que la lista no esté vacía
    if (index > 0 && listaProductosGlobal != null && (index - 1) < listaProductosGlobal.size()) {
        modelo.Producto producto = listaProductosGlobal.get(index - 1);

        int cantidad = (int) spnCantidad.getValue();
        double precio = Double.parseDouble(txtPrecio.getText().replace(",", "."));
        double descuento = txtDescuento.getText().isEmpty() ? 0.0 : Double.parseDouble(txtDescuento.getText().replace(",", "."));
        double subtotal = Double.parseDouble(txtSubTotal.getText().replace(",", "."));
        boolean aplicaIva = chkIva.isSelected();
        double ivaCalculado = aplicaIva ? (subtotal * 0.15) : 0.0;

        // Agregar fila a la tabla de la interfaz
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tblDetalle.getModel();
        model.addRow(new Object[]{
            producto.getNombre(),
            cantidad,
            precio,
            descuento,
            subtotal,
            aplicaIva ? "Sí" : "No",
            String.format("%.2f", ivaCalculado)
        });

    } else {
        javax.swing.JOptionPane.showMessageDialog(this, "Seleccione un producto válido de la lista.");
    }
    actualizarTotalesFactura();
    }//GEN-LAST:event_btnAgregarActionPerformed



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
    private javax.swing.JSpinner spnCantidad;
    private javax.swing.JTable tblDetalle;
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
