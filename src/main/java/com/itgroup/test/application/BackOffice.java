package com.itgroup.test.application;

import com.itgroup.test.utility.Utility;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

// 추상 클래스인 javafx.application.Application을 상속 받습니다.
public class BackOffice extends Application {
    // 깃허브 커밋 후 수정 할 경우


    @Override
    public void start(Stage stage) throws Exception {
        /*
        fxml 파일 불러오기
        1.String 객체 fxmlFile에 fxml파일 경로 + 파일명.확장자 까지 넣어준다.
        2.FXMLLoader 객체에 String -> url로 바꿔서 넣어준다. *getClass().getResource(fxmlFile)
        3.Parent 객체에 url담긴 FXMLLoader 객체를 .load() 시킨 값을 넣어준다.
        4.Parent 객체 container를 Scene객체에 담는다.
        5.Stage에 scene객체를 셋팅한다. stage.setScene(scene);
        6.stage.showAndWait()
         */




        String fxmlFile = Utility.FXML_PATH + "BackOffice.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent container = fxmlLoader.load(); //여기 예외처리로 throws Exception 추가
        Scene scene = new Scene(container);

        //스타일 시트 연결
        String myStyle = getClass().getResource(Utility.CSS_PATH + "backOfficeStyle.css").toString();
        scene.getStylesheets().add(myStyle); //스타일링 파일 지정하기

        stage.setScene(scene);
        stage.setTitle("상품 조회 화면");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
