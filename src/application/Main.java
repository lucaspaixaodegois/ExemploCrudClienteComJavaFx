package application;//nome do pacote 

//importa��es necessarias

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//class principal da app
public class Main extends Application {
	// metodo principal que chama o launch "lan�ador"
	public static void main(String args[]) {
		launch(args);// "lan�ador"
	}

	// starta aplicacao
	@Override
	public void start(Stage primaryStage) throws Exception {
		// chama o arquivo.fxml que � a tela feita no scenebuilder
		AnchorPane root = FXMLLoader.load(getClass().getResource("/view/cadastroCliente.fxml"));
		// seta o tamanho da cena/janela
		Scene scene = new Scene(root, 600, 400);
		// seta o titulo da cena/janela
		primaryStage.setTitle("CRUD de Clientes");
		primaryStage.setScene(scene);// seta cena/janela
		primaryStage.show();// mostra a cena/janela

	}

}
