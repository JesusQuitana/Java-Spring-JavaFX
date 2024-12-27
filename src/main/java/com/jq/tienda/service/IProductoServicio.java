package com.jq.tienda.service;

import java.util.List;
import com.jq.tienda.model.Producto;

public interface IProductoServicio {
    public List<Producto> findAll();
    public Producto find(Integer id);
    public void save(Producto producto);
    public void delete(Producto producto);
}
