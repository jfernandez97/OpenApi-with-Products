package com.swagger.service;

import com.swagger.model.Producto;

import java.util.List;

public interface ProductoService {
    Producto createProducto(Producto p);
    Producto findByNombre(String nombre);
    List<Producto> findAll();
    List<Producto> findByPrecioGreaterThan(double precio);
    List<Producto> findByPrecioOrderByStockDesc(double precio);
    Producto update(Long id,Producto p);
    Producto delete(Long id);

}
