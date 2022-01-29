package com.swagger.controller;

import com.swagger.model.Producto;
import com.swagger.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/productos")
public class ProductoController {
//http://localhost:5000/openapi/swagger-ui/index.html#/
//http://localhost:5000/swagger-ui/index.html#/
    Logger log = LogManager.getLogger(ProductoController.class);

    @Autowired
    ProductoService productoService;


    @Operation(summary = "Método para obtener todos los productos", description = "Permite obtener todos los productos registrados", tags = {"productos"})
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Se obtuvieron todos los productos"),
                    @ApiResponse(responseCode = "400", description = "Hay un error en el request", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Ocurrió un error inesperado", content = @Content)
            }
    )
    @GetMapping("/")
    public List<Producto> getAll() {
        log.info("Obteniendo todos los productos");
        return productoService.findAll();
    }

    @Operation(summary = "Método para obtener un producto por su nombre", description = "Permite obtener un producto filtrado por su nombre", tags = {"producto"})
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Se obtuvo el producto"),
                    @ApiResponse(responseCode = "400", description = "Hay un error en el request", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Ocurrió un error inesperado", content = @Content)
            }
    )
    @GetMapping("/{nombre}")
    public Producto getByNombre(@PathVariable String nombre) {
        log.info("Econtrando un producto por su nombre");
        return productoService.findByNombre(nombre);
    }

    @Operation(summary = "Método para obtener todos los productos con precio mayor al puesto", description = "Permite obtener todos los productos filtrados por precio mayor al aclarado", tags = {"productos"})
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Se obtuvieron todos los productos"),
                    @ApiResponse(responseCode = "400", description = "Hay un error en el request", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Ocurrió un error inesperado", content = @Content)
            }
    )
    @GetMapping("/greaters/{precio}")
    public List<Producto> getByPrecioGreaterThan(@PathVariable double precio) {
        log.info("Econtrando productos con un precio mayor a " + precio);
        return productoService.findByPrecioGreaterThan(precio);
    }

    @Operation(summary = "Método para obtener todos los productos con un mismo precio ordenados por numero de stock", description = "Permite obtener todos los productos filtrados por su precio ordenados por stock", tags = {"productos"})
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Se obtuvieron todos los productos"),
                    @ApiResponse(responseCode = "400", description = "Hay un error en el request", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Ocurrió un error inesperado", content = @Content)
            }
    )
    @GetMapping("/order/{precio}")
    public List<Producto> findByPrecioOrderByStockDesc(@PathVariable double precio) {
        log.info("Encontrando productos por su precio ordenados por numero de stock");
        return productoService.findByPrecioOrderByStockDesc(precio);
    }

    @Operation(summary = "Método para crear un producto", description = "Permite crear productos", tags = {"producto"})
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Se creó a el producto"),
                    @ApiResponse(responseCode = "400", description = "Hay un error en el request", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Ocurrió un error inesperado", content = @Content)
            }
    )
    @PostMapping("/")
    public Producto createProducto(@RequestBody Producto p) {
        log.info("Creando un producto");
        return productoService.createProducto(p);
    }

    @Operation(summary = "Método para actualizar un producto", description = "Permite actualizar productos", tags = {"producto"})
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Se creó a el producto"),
                    @ApiResponse(responseCode = "400", description = "Hay un error en el request", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Ocurrió un error inesperado", content = @Content)
            }
    )
    @PutMapping("/{id}")
    public Producto update(@PathVariable Long id, @RequestBody Producto p) {
        log.info("Editando un producto");
        return productoService.update(id, p);
    }

    @Operation(summary = "Método para eliminar un producto", description = "Permite eliminar productos", tags = {"producto"})
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Se creó a el producto"),
                    @ApiResponse(responseCode = "400", description = "Hay un error en el request", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Ocurrió un error inesperado", content = @Content)
            }
    )
    @DeleteMapping("/{id}")
    public Producto delete(@PathVariable Long id) {
        log.info("Eliminando un producto");
        return productoService.delete(id);
    }
}
