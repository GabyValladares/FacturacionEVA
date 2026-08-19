USE facturacion;

DROP PROCEDURE IF EXISTS sp_insertar_producto;

DELIMITER $$

CREATE PROCEDURE sp_insertar_producto(
    IN p_nombre VARCHAR(100),
    IN p_precio DOUBLE,
    IN p_stock INT,
    OUT p_id_producto INT
)
BEGIN
    INSERT INTO facturacion.productos (nombre, precio, stock)
    VALUES (p_nombre, p_precio, p_stock);

    SET p_id_producto = LAST_INSERT_ID();
END$$

DELIMITER ;
