DELIMITER //

CREATE PROCEDURE sp_validar_login(
    IN p_nombre VARCHAR(50),
    IN p_clave VARCHAR(50),
    OUT p_mensaje VARCHAR(100),
    OUT p_es_valido BOOLEAN
)
BEGIN
    DECLARE v_conteo INT DEFAULT 0;

    -- Validar si existe un registro con el usuario, clave y cargo 'Cajero'
    SELECT COUNT(*) INTO v_conteo
    FROM facturacion.usuarios
    WHERE nombre = p_nombre 
      AND clave = p_clave 
      AND cargo = 'Cajero';

    -- Evaluación del resultado
    IF v_conteo > 0 THEN
        SET p_mensaje = 'Inicio de sesión exitoso. Bienvenido(a).';
        SET p_es_valido = TRUE;
    ELSE
        SET p_mensaje = 'No posee credenciales válidas';
        SET p_es_valido = FALSE;
    END IF;
END //

DELIMITER ;