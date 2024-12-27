package com.jq.tienda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.jq.tienda.view.ProductoFX;
import javafx.application.Application;
import javafx.stage.Stage;

@SpringBootApplication
public class TiendaApplication {

	public static void main(String[] args) {
		Application.launch(ProductoFX.class, args);
	}
}
