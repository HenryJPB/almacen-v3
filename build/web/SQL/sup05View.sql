CREATE VIEW SUP05_VIEW
AS SELECT c5_codigo as codItem, 
       c1_descripcion as descripcion,
       c5_fecha as fecha, 
       c5_tipo_mov as tipoMov, 
       c5_costo as costo, 
       c5_cant_requerida as cantRequerida, 
       c5_unidades as unidades, 
       c5_referencia as referencia, 
       c5_depart as centroCosto, 
       c5_cod_proceso as codProceso, 
       c5_cod_maq as codMaquina, 
       c5_origen as origen
FROM   sup01_dat, sup05_dat
WHERE c1_codigo = c5_codigo
/   
