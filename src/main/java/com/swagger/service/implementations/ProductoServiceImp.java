package com.swagger.service.implementations;

import com.swagger.model.Producto;
import com.swagger.repository.ProductoRepository;
import com.swagger.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImp implements ProductoService {

    @Autowired
    ProductoRepository productoRepository;


    @Override
    public Producto createProducto(Producto p) {
        Optional<Producto> optional = productoRepository.findByNombre(p.getNombre());
        Producto toCreate = null;
        if(!optional.isPresent()){
            toCreate = p;
            productoRepository.save(p);
       }
        return toCreate;
    }

    @Override
    public Producto findByNombre(String nombre) {
        Optional<Producto> optional = productoRepository.findByNombre(nombre);
        Producto found = null;
        if(optional.isPresent()){
            found = optional.get();
        }
        return found;
    }

    @Override
    public List<Producto> findAll() {
        return (List<Producto>) productoRepository.findAll();
    }

    @Override
    public List<Producto> findByPrecioGreaterThan(double precio) {
        return productoRepository.findByPrecioGreaterThan(precio);
    }

    @Override
    public List<Producto> findByPrecioOrderByStockDesc(double precio) {
        return productoRepository.findByPrecioOrderByStockDesc(precio);
    }


    @Override
    public Producto update(Long id, Producto p) {
        Optional<Producto> optional = productoRepository.findById(id);
        Producto toUpdate = null;
        if(optional.isPresent()){
            toUpdate = optional.get();
            toUpdate.setNombre(p.getNombre());
            toUpdate.setPrecio(p.getPrecio());
            toUpdate.setStock(p.getStock());
            productoRepository.save(toUpdate);
        }
        return toUpdate;
    }

    @Override
    public Producto delete(Long id) {
        Optional<Producto> optional = productoRepository.findById(id);
        Producto toDelete = null;
        if(optional.isPresent()){
            toDelete = optional.get();
            productoRepository.delete(toDelete);
        }
        return toDelete;
    }
}
