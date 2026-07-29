
package vista;

import controlador.ClienteControlador;
import controlador.MarcaControlador;
import controlador.ProductoControlador;
import java.time.LocalDate;
import java.util.ArrayList;
import modelo.DetalleFactura;
import modelo.Producto;





public class FacturaVista extends javax.swing.JFrame {
    
    private ArrayList<String[]> productos;
    private ArrayList<String[]> clientes;
    private ArrayList<String[]> marcas;
    private ArrayList<DetalleFactura> lDF = new ArrayList<>();
    

   
    public FacturaVista() {
        initComponents();
        txtFecha.setText(LocalDate.now().toString());
        txtFecha.setEditable(false);
        cargarProductos(1);
        cargarClientes();
        cargarMarcas();
  
        
        

    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        lblNombres = new javax.swing.JLabel();
        cmbClientes = new javax.swing.JComboBox<>();
        lblFecha = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        cmbProductos = new javax.swing.JComboBox<>();
        lblDetalle = new javax.swing.JLabel();
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        textCedula = new javax.swing.JTextField();
        TextTelefono = new javax.swing.JTextField();
        TextDireccion = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        cmbMarcas = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();

        jLabel6.setText("jLabel6");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));

        lblTitulo.setBackground(new java.awt.Color(0, 0, 0));
        lblTitulo.setFont(new java.awt.Font("Algerian", 1, 36)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(0, 102, 204));
        lblTitulo.setText("GESTIÓN DE FACTURAS");

        lblNombres.setBackground(new java.awt.Color(0, 0, 0));
        lblNombres.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        lblNombres.setForeground(new java.awt.Color(0, 0, 0));
        lblNombres.setText("Cliente:");

        cmbClientes.setForeground(new java.awt.Color(255, 255, 255));
        cmbClientes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbClientesActionPerformed(evt);
            }
        });

        lblFecha.setBackground(new java.awt.Color(0, 0, 0));
        lblFecha.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(0, 0, 0));
        lblFecha.setText("Fecha:");

        txtFecha.setEditable(false);
        txtFecha.setForeground(new java.awt.Color(153, 153, 153));
        txtFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaActionPerformed(evt);
            }
        });

        cmbProductos.setForeground(new java.awt.Color(255, 255, 255));
        cmbProductos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProductosActionPerformed(evt);
            }
        });

        lblDetalle.setBackground(new java.awt.Color(0, 0, 0));
        lblDetalle.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        lblDetalle.setForeground(new java.awt.Color(153, 153, 153));
        lblDetalle.setText("            DETALLE DE LOS PRODUCTOS");

        txtADetalle.setEditable(false);
        txtADetalle.setColumns(20);
        txtADetalle.setRows(5);
        jScrollPane1.setViewportView(txtADetalle);

        lblProducto.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        lblProducto.setForeground(new java.awt.Color(0, 0, 0));
        lblProducto.setText(" PRODUCTO");

        lblCantidad.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        lblCantidad.setForeground(new java.awt.Color(0, 0, 0));
        lblCantidad.setText("CANTIDAD");

        txtCantidad.setForeground(new java.awt.Color(0, 0, 0));
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

        lblSubTotal.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        lblSubTotal.setForeground(new java.awt.Color(0, 0, 0));
        lblSubTotal.setText("SUBTOTAL");

        txtSubTotal.setEditable(false);
        txtSubTotal.setForeground(new java.awt.Color(0, 0, 0));
        txtSubTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSubTotalActionPerformed(evt);
            }
        });

        lblPrecio.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        lblPrecio.setForeground(new java.awt.Color(0, 0, 0));
        lblPrecio.setText("  PRECIO");

        txtPrecio.setEditable(false);
        txtPrecio.setBackground(new java.awt.Color(255, 255, 255));
        txtPrecio.setForeground(new java.awt.Color(0, 0, 0));
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

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Email:");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Cédula:");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Teléfono:");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Dirección:");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Categoría:");

        textCedula.setEditable(false);
        textCedula.setForeground(new java.awt.Color(153, 153, 153));
        textCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textCedulaActionPerformed(evt);
            }
        });

        TextTelefono.setEditable(false);
        TextTelefono.setForeground(new java.awt.Color(153, 153, 153));

        TextDireccion.setEditable(false);
        TextDireccion.setForeground(new java.awt.Color(153, 153, 153));

        jTextField4.setEditable(false);
        jTextField4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextField4.setForeground(new java.awt.Color(153, 153, 153));

        jTextField5.setEditable(false);
        jTextField5.setForeground(new java.awt.Color(153, 153, 153));

        cmbMarcas.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        cmbMarcas.setForeground(new java.awt.Color(255, 255, 255));
        cmbMarcas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbMarcas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMarcasActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Marca");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(237, 237, 237)
                        .addComponent(btnPDF))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(lblTitulo)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(169, 169, 169))
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                                .addGap(10, 10, 10))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(17, 17, 17))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(33, 33, 33)
                                        .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFecha)
                                    .addComponent(TextTelefono)))
                            .addComponent(TextDireccion)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbClientes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbMarcas, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblProducto))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPrecio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnAgregar))
                            .addComponent(lblSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jScrollPane1)))
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombres)
                    .addComponent(cmbClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFecha)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(TextTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblDetalle)
                                .addGap(43, 43, 43))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblPrecio)
                                .addComponent(lblCantidad)
                                .addComponent(lblSubTotal)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAgregar)
                            .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(lblProducto))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbMarcas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnPDF)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbClientesActionPerformed
    int indice1 = this.clienteSeleccionado();
    if(indice1>=0){
    textCedula.setText(clientes.get(indice1)[2]);
    TextTelefono.setText(clientes.get(indice1)[4]);
    jTextField5.setText(clientes.get(indice1)[3]);
    TextDireccion.setText(clientes.get(indice1)[5]);
    jTextField4.setText(clientes.get(indice1)[6]);
    }
    }//GEN-LAST:event_cmbClientesActionPerformed

    private void cmbProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProductosActionPerformed
    int indice = this.productoSeleccionado();
    if(indice>=0){
    txtPrecio.setText(productos.get(indice)[2]);
    }

    }//GEN-LAST:event_cmbProductosActionPerformed

    private void txtPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioActionPerformed
            // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioActionPerformed

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed

        calcularSubtotal();           // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadActionPerformed

    private void txtSubTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSubTotalActionPerformed
    calcularSubtotal();         // TODO add your handling code here:
    }//GEN-LAST:event_txtSubTotalActionPerformed

    private void txtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyReleased
     double precio = Double.parseDouble(txtPrecio.getText());
        int cantidad = Integer.parseInt(txtCantidad.getText());
        if(!txtCantidad.getText().isEmpty())
        txtSubTotal.setText((precio * cantidad) + "");     // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadKeyReleased

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
    Producto p = new Producto();

    p.setId(Integer.parseInt(productos.get(this.productoSeleccionado())[0]));
    p.setNombre(productos.get(this.productoSeleccionado())[1]);
            
    p.setPrecio(Double.parseDouble(productos.get(this.productoSeleccionado())[2]));
    // objeto detalle factura
     //OBJETO DETALLE FACTURA
        DetalleFactura dF = new DetalleFactura();
        dF.setProducto(p);
        dF.setCantidad(Integer.parseInt(txtCantidad.getText()));
        dF.setSubtotal(Double.parseDouble(txtSubTotal.getText()));
        //Añado a la lista dinámica 
        lDF.add(dF);
        //muestro en la vista en el TextArea
        txtADetalle.append(dF.toString());
        this.limpiarDetalle();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void textCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCedulaActionPerformed

    private void txtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaActionPerformed

    private void btnPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_btnPDFActionPerformed

    private void cmbMarcasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMarcasActionPerformed
     int fila = cmbMarcas.getSelectedIndex();

    if (fila >= 0) {
        int idMarca = Integer.parseInt(marcas.get(fila)[0]);
        cargarProductos(idMarca);
    }
    }//GEN-LAST:event_cmbMarcasActionPerformed
    
    public void cargarProductos(int idMarca) {

     ProductoControlador pc = new ProductoControlador();

    productos = pc.obtenerProductos(idMarca);

    cmbProductos.removeAllItems();

    for (String[] producto : productos) {
        cmbProductos.addItem(producto[1]);
    }
    }
    
    public void cargarClientes() {

    ClienteControlador cc = new ClienteControlador();

    clientes = cc.obtenerCliente();

    cmbClientes.removeAllItems();

    for (String[] cliente : clientes) {
        cmbClientes.addItem(cliente[1]);
    }
}
    public void calcularSubtotal() {

    if (productos != null || !productos.isEmpty()) {
    

    double precio = Double.parseDouble(txtPrecio.getText());
    int cantidad = Integer.parseInt(txtCantidad.getText());

    double subtotal = precio * cantidad;

    txtSubTotal.setText(String.valueOf(subtotal));
    }
}
    
    public int productoSeleccionado(){
     if (productos != null && !productos.isEmpty()) {
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


    public void cargarMarcas() {
        MarcaControlador mc = new MarcaControlador();

        marcas = mc.obtenerMarca();

        cmbMarcas.removeAllItems();

        for (String[] marca : marcas) {
            cmbMarcas.addItem(marca[1]);
        }
    
    

    
    }
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
    private javax.swing.JTextField TextDireccion;
    private javax.swing.JTextField TextTelefono;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnPDF;
    private javax.swing.JComboBox<String> cmbClientes;
    private javax.swing.JComboBox<String> cmbMarcas;
    private javax.swing.JComboBox<String> cmbProductos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblDetalle;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblNombres;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblProducto;
    private javax.swing.JLabel lblSubTotal;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField textCedula;
    private javax.swing.JTextArea txtADetalle;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtSubTotal;
    // End of variables declaration//GEN-END:variables
}
