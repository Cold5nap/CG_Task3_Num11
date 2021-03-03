package CG_Task3_Num11;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class App extends Application {

    void createContent(Scene scene){
        StackPane container = (StackPane) scene.lookup("#container");
        Slider zoom = (Slider) scene.lookup("#zoom");

        Picture picture = new Picture(zoom);

        picture.drawSun();
        container.getChildren().add(picture);

        AtomicInteger numberSun= new AtomicInteger();
        List<Picture> pictures = new ArrayList<>();
        pictures.add(picture);

        Button addSun = (Button) scene.lookup("#addSun") ;
        addSun.setOnMouseClicked(e->{
            Picture pictureOnClick = new Picture(zoom);
            pictureOnClick.drawSun();
            container.getChildren().remove(pictures.get(numberSun.get()));
            container.getChildren().add(pictureOnClick);
            pictures.add(pictureOnClick);
            numberSun.getAndIncrement();
        });

        Button prev = (Button) scene.lookup("#prev");
        prev.setOnMouseClicked(e->{
            if (numberSun.get()>0) {
                container.getChildren().remove(pictures.get(numberSun.getAndDecrement()));
                container.getChildren().add(pictures.get(numberSun.get()));
            }
        });

        Button next = (Button) scene.lookup("#next");
        next.setOnMouseClicked(e->{
            if (pictures.size()>numberSun.get()+1){
                container.getChildren().remove(pictures.get(numberSun.get()));
                container.getChildren().add(pictures.get(numberSun.incrementAndGet()));

            }
        });

    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Main" + ".fxml"));
        Scene scene = new Scene(fxmlLoader.load());



        createContent(scene);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}