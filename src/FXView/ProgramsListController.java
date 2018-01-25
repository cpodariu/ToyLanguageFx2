package FXView;

import java.io.Console;
import java.net.URL;
import java.util.ResourceBundle;

import Utils.PrimitiveADT.MyDictionary;
import View.Command;
import View.RunExampleCommand;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProgramsListController implements Initializable {
	@FXML
	private ListView<RunExampleCommand> listBoxMain;
	@FXML
	private Button startButton;
	
	final ObservableList<RunExampleCommand> listItems = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		listBoxMain.setEditable(true);
		listBoxMain.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2) {
					//Use ListView's getSelected Item
					try {
						startAction(new ActionEvent());
					} catch (Exception e) {
						e.printStackTrace();
					}
					//use this to do whatever you want to. Open Link etc.
				}
			}
		});
	}
	
	public void addPrograms(MyDictionary<String, Command> commands)
	{
		for(String c: commands.keySet())
		{
			if (commands.get(c) instanceof RunExampleCommand)
				listItems.add((RunExampleCommand) commands.get(c));
		}
		listBoxMain.setItems(listItems);
	}
	
	public void startAction(ActionEvent actionEvent) throws Exception {
		int selectedItem = listBoxMain.getSelectionModel().getSelectedIndex();
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("PrgStateUiFx.fxml"));
		
		Scene scene = new Scene(fxmlLoader.load());
		Stage stage = new Stage();
		
		PrgStateUiFxController controller = fxmlLoader.getController();
		controller.setProgramController(listItems.get(selectedItem).getCtrl());
		
		stage.setTitle("New Window");
		stage.setScene(scene);
		stage.show();
		
	}
}