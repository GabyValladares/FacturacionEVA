USE facturacion;

DROP PROCEDURE IF EXISTS sp_validar_stock;

DELIMITER $$

CREATE PROCEDURE sp_validar_stock(
    IN  p_id_producto INT,
    IN  p_cantidad    INT,
    OUT p_codigo      INT,
    OUT p_stock_actual INT
)
BEGIN
    SELECT stock INTO p_stock_actual
    FROM productos
    WHERE id_producto = p_id_producto;

    IF p_stock_actual IS NULL THEN
        SET p_codigo = -1;  -- Producto no encontrado
    ELSEIF p_stock_actual < 5 THEN
        SET p_codigo = 1;   -- Stock bajo
    ELSEIF p_cantidad > p_stock_actual THEN
        SET p_codigo = 2;   -- Stock insuficiente
    ELSE
        SET p_codigo = 0;   -- Stock válido
    END IF;
END$$

DELIMITER ;
