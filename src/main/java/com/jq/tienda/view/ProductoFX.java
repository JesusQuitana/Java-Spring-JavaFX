package com.jq.tienda.view;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import com.jq.tienda.TiendaApplication;
import com.jq.tienda.controller.IndexController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProductoFX extends Application{

    private ConfigurableApplicationContext context;

    @Override
    public void init() {
        this.context = new SpringApplicationBuilder(TiendaApplication.class).run();
    }

    @Override
    public void start(Stage stage) throws Exception {
        /*  Importante  */
        FXMLLoader loader = new FXMLLoader(TiendaApplication.class.getResource("/templates/index.fxml"));
        loader.setControllerFactory(context::getBean);
        /*  Importante  */
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        this.context.close();
        Platform.exit();
    }
}
