package view;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import util.Logger;

public class StatisticsController implements Initializable {

	
//	@FXML private Button exitButton1;
	@FXML private BarChart<String, Number> barChart1; //바 차트용
	@FXML private CategoryAxis xAxis;
	//ObservableList<Data> list = null; //리스트에 추가 할경우
	@FXML private PieChart pieChart1; //파이차트용
	private int percent=0; // 파이차트에 추후 사용할 예정
	
	//시리즈 담을 자료들
	String[] categories = {"10대", "20대", "30대", "40대", "기타"};
	
	//담을 자료구조
	XYChart.Series<String, Number> series = null;
	private ObservableList<String> xLabels = FXCollections.observableArrayList();
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		System.out.println("FXML LOAD COMPLETE!!!"); // 필요할까? - x축에 담을 예정
		xLabels.addAll(Arrays.asList(categories));
		xAxis.setCategories(xLabels); // x축 세팅
		
		//exitButton1.setOnAction(event -> exitButton1Action(event));
		
	}
	/*
	public void exitButton1Action(ActionEvent event) //exitButton Setting
	{
		Platform.exit();
	}
	*/
	
	
	
	
	        //시작 버튼
	public void auto()
			{
				barChart1.getData().clear();
				
				String[] brands = {"남", "여", "기타"}; // 성별에 따라 나눔
				
				for(int i=0; i<brands.length; i++) // 시리즈 여러개 생성
				{
					series = new XYChart.Series<String, Number>();
					
					series.setName(brands[i]);
					
					//시리즈별 데이터 생성() = {"10대","20대", "30대", "40대", "기타"}
					
					for(int j=0; j<categories.length; j++)
					{
						//바를 생성 하기위한 부분?
						series.getData().add(new XYChart.Data<String, Number>(xLabels.get(j),i));// i 부분 다시 볼것
					
					}
					
					barChart1.getData().add(series);
					
				}
				/*
				pieChart1.getData().clear();
				ObservableList<Data>list = null;
				
				int a = 10; 
				int b = 20;
				int c = 30; 
				int d = 17;
				int e = 43;  
				  
				list = FXCollections.observableArrayList();
				list.add(new PieChart.Data("zehn",a));
				list.add(new PieChart.Data("zwanzig",b));
				list.add(new PieChart.Data("dreissig",c));
				list.add(new PieChart.Data("vierzig",d));
				list.add(new PieChart.Data("others",e));
				
				pieChart1.setTitle("Aufteilung nach Geschlecht und Alter");
				pieChart1.setLabelsVisible(true);
				pieChart1.setData(list);
				*/
				
			
				
				//	for(int j=0; j<categories.length; j++)
					
						
					
				
			}
	// 뒤로가기 버튼
	@FXML
	private Button backButton;

	public void BackButtonAction() {
		Stage stage = (Stage) backButton.getScene().getWindow(); // 스테이지2
		Logger.log("뒤로가기 버튼");
		try {

			Stage newStage = new Stage(); // 스테이지1
			// scene 에 레이아웃 추가할 경우
			Parent second = FXMLLoader.load(getClass().getResource("MainScene.fxml"));

			Scene sc = new Scene(second);

			// Scene를 스테이지에서 상영하게
			newStage.setScene(sc);
			newStage.show();

			// 기본 페이지 삭제함
			stage.hide();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private Button exitButton;
	
	public void exitButtonAction(ActionEvent event)
	{
		Logger.log("종료 버튼");
		Platform.exit();
	}

}
