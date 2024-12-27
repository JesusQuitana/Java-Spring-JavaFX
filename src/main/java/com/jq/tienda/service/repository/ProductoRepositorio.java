package com.jq.tienda.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jq.tienda.model.Producto;

public interface ProductoRepositorio extends JpaRepository<Producto, Integer>{

}
