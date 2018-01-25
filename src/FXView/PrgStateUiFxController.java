package FXView;

import Controller.Controller;
import Model.PrgState;
import Utils.Interfaces.MyIDictionary;
import Utils.MyFileReader;
import Utils.MyHeap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PrgStateUiFxController implements Initializable {
	public Button oneStepButton;
	public Button allStepButton;
	
	private Controller programController = null;
	
	public Label statesCount;
	public TableView statesList;
	private final ObservableList<PrgState> statesListItems = FXCollections.observableArrayList();
	
	public TableView heapTableView;
	private final ObservableList<Pair<Integer, Integer>> heapListItems = FXCollections.observableArrayList();
	
	public TableView fileTableView;
	private final ObservableList<Pair<String, String>> fileTableItems = FXCollections.observableArrayList();
	
	public ListView exeStackView;
	public final ObservableList<String> exeStackViewItems = FXCollections.observableArrayList();
	
	public ListView outView;
	private final ObservableList<String> outputListItems = FXCollections.observableArrayList();
	
	public TableView symTableView;
	private final ObservableList<Pair<String, String>> symTableItems = FXCollections.observableArrayList();
	
	private int prevIndex;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		statesList.setEditable(true);
	}
	
	private void setStatesCount()
	{
		statesCount.setText("Number of prgState: " + String.valueOf(programController.getRepository().getPrgList().size()));
	}
	
	private void setStatesList()
	{
		prevIndex = statesList.getSelectionModel().getSelectedIndex();
		
		statesListItems.clear();
		statesListItems.addAll(programController.getRepository().getPrgList());
		
		TableColumn<PrgState, String> idColumn = new TableColumn<PrgState, String>("Id");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		TableColumn<PrgState, String> stackColumn = new TableColumn<PrgState, String>("Stack");
		stackColumn.setCellValueFactory(new PropertyValueFactory<>("stack"));
		
		statesList.getColumns().clear();
		statesList.getColumns().setAll(idColumn, stackColumn);
		
		statesList.setItems(statesListItems);
		
		statesList.getSelectionModel().select(prevIndex);
	}
	
	private void setHeapListItems()
	{
		heapTableView.setEditable(true);
		
		heapListItems.clear();
		MyHeap heap = (MyHeap) programController.getRepository().getPrgList().get(0).getHeap();
		for(Integer i: heap.getContent().keySet())
		{
			heapListItems.add(new Pair<Integer, Integer>(i, heap.get(i)));
		}
		
		TableColumn<Integer, Integer> keyColumn = new TableColumn<Integer, Integer>("Key");
		keyColumn.setCellValueFactory(new PropertyValueFactory<>("key"));
		
		TableColumn<Integer, Integer> valueColumn = new TableColumn<Integer, Integer>("Value");
		valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
		
		heapTableView.getColumns().clear();
		heapTableView.getColumns().setAll(keyColumn, valueColumn);
		
		heapTableView.setItems(heapListItems);
	}
	
	public void setFileTableView()
	{
		fileTableView.setEditable(true);
		
		fileTableItems.clear();
		
		MyIDictionary<Integer, MyFileReader> fileTable = programController.getRepository().getPrgList().get(0).getFileTable();
		for (Integer s: fileTable.keySet())
		{
			fileTableItems.add(new Pair<String, String>(s.toString(), fileTable.get(s).getFileName()));
		}
		
		TableColumn<String, String> keyColumn = new TableColumn<String, String>("Key");
		keyColumn.setCellValueFactory(new PropertyValueFactory<>("key"));
		
		TableColumn<String, String> valueColumn = new TableColumn<String, String>("Value");
		valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
		
		fileTableView.getColumns().clear();
		fileTableView.getColumns().setAll(keyColumn, valueColumn);
		
		fileTableView.setItems(fileTableItems);
		
	}
	
	private void setExeStackView()
	{
		exeStackView.setEditable(true);
		exeStackViewItems.clear();
		
		int index = statesList.getSelectionModel().getSelectedIndex();
		if (index == -1)
			index = 0;
		for (String s: programController.getRepository().getPrgList().get(index).getStack().getAllStatements())
		{
			exeStackViewItems.add(s);
		}
		
		exeStackView.setItems(exeStackViewItems);
	}
	
	private void setSymTableView()
	{
		symTableView.setEditable(true);
		symTableItems.clear();
		
		int index = statesList.getSelectionModel().getSelectedIndex();
		if (index == -1)
			index = 0;
		
		MyIDictionary<String, Integer> symTable = programController.getRepository().getPrgList().get(index).getSymTable();
		
		for(String s: symTable.keySet())
		{
			symTableItems.add(new Pair<String, String>(s, symTable.get(s).toString()));
		}
		
		TableColumn<String, String> keyColumn = new TableColumn<String, String>("Key");
		keyColumn.setCellValueFactory(new PropertyValueFactory<>("key"));
		
		TableColumn<String, String> valueColumn = new TableColumn<String, String>("Value");
		valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
		
		symTableView.getColumns().clear();
		symTableView.getColumns().setAll(keyColumn, valueColumn);
		
		symTableView.setItems(symTableItems);
	}
	
	private void setOutView()
	{
		outView.setEditable(true);
		outputListItems.clear();
		outputListItems.addAll((ArrayList<String>) programController.getRepository().getPrgList().get(0).getOut());
		outView.setItems(outputListItems);
	}
	
	private void updateAll()
	{
		setStatesCount();
		setStatesList();
		setHeapListItems();
		setFileTableView();
		setExeStackView();
		setOutView();
		setSymTableView();
	}
	
	public void setProgramController(Controller c)
	{
		this.programController = c;
		updateAll();
	}
	
	public void oneStepForAll(ActionEvent actionEvent) {
		try {
			programController.oneStepForAllPrg(programController.getRepository().getPrgList());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		this.updateAll();
		
	}
	
	public void onSelectionMade(MouseEvent mouseEvent) {
		setExeStackView();
		setSymTableView();
	}
	
	public void allStepForAll(ActionEvent actionEvent) {
		programController.allSteps(false);
		this.updateAll();
	}
}
