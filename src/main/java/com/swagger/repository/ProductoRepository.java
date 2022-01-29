package com.swagger.repository;

import com.swagger.model.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends CrudRepository<Producto,Long> {
    Optional<Producto> findByNombre(String nombre);
    List<Producto> findByPrecioGreaterThan(double precio);
    List<Producto> findByPrecioOrderByStockDesc(double precio);

}
