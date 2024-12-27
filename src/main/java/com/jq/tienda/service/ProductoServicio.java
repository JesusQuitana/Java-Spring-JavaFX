package com.jq.tienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jq.tienda.model.Producto;
import com.jq.tienda.service.repository.ProductoRepositorio;

@Service
public class ProductoServicio implements IProductoServicio{

    @Autowired
    ProductoRepositorio productoRepositorio;

    @Override
    public List<Producto> findAll() {
        List<Producto> productos = productoRepositorio.findAll();
        return productos;
    }

    @Override
    public Producto find(Integer id) {
        Producto producto = productoRepositorio.findById(id).orElse(null);
        return producto;
    }

    @Override
    public void save(Producto producto) {
        productoRepositorio.save(producto);
    }

    @Override
    public void delete(Producto producto) {
        productoRepositorio.delete(producto);
    }

}
