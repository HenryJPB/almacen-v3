CREATE VIEW Sup05Exist_VIEW
AS SELECT c5_codigo as codItem, 
       c1_descripcion as descripcion,
       c1_cod_ubicacion as ubicacion,
       c1_um as um, 
       sum( decode(c5_tipo_mov,
            'S',-c5_unidades,
            c5_unidades ) ) as existencia
FROM sup01_dat, sup05_dat
WHERE c1_codigo = c5_codigo 
GROUP BY c5_codigo, c1_descripcion, c1_cod_ubicacion, c1_um;
