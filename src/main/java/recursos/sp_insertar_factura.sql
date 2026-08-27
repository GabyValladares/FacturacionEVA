CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertar_cliente`(
    IN p_nombre VARCHAR(100),
    IN p_email VARCHAR(100),
    IN p_telefono VARCHAR(20),
    IN p_tipo_cliente VARCHAR(50),
    IN p_descuento_vip DOUBLE,
    IN p_cedula VARCHAR(20),
    IN p_direccion VARCHAR(200),
    OUT p_id_cliente INT
)
BEGIN
    -- Insertar el nuevo registro en la tabla clientes
    INSERT INTO facturacion.clientes (
        nombre, 
        email, 
        telefono, 
        tipo_cliente, 
        descuento_vip, 
        cedula, 
        direccion
    )
    VALUES (
        p_nombre, 
        p_email, 
        p_telefono, 
        p_tipo_cliente, 
        p_descuento_vip, 
        p_cedula, 
        p_direccion
    );
    
    -- Obtener y asignar el id_cliente generado automáticamente
    SET p_id_cliente = LAST_INSERT_ID();
END