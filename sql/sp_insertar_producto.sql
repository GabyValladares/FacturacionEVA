USE facturacion;

DROP PROCEDURE IF EXISTS sp_insertar_producto;

DELIMITER $$

CREATE PROCEDURE sp_insertar_producto(
    IN p_nombre VARCHAR(100),
    IN p_precio DOUBLE,
    OUT p_id_producto INT
)
BEGIN
    INSERT INTO facturacion.productos (nombre, precio)
    VALUES (p_nombre, p_precio);

    SET p_id_producto = LAST_INSERT_ID();
END$$

DELIMITER ;
