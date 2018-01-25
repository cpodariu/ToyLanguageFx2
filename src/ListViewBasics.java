import FXView.ProgramsListController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ListViewBasics extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXView/ListViewUI.fxml"));
		
		Parent root = (Parent)fxmlLoader.load();
		
		ProgramsListController controller = fxmlLoader.getController();
		controller.addPrograms(Interpreter.getTestPrograms());
		
		Scene scene = new Scene(root);
		
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}