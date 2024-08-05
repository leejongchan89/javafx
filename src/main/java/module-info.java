module com.itgroup.test {
    requires javafx.controls;
    requires javafx.fxml;
    // JDBC API가 포함된 모듈이 필요합니다.
    requires java.sql;
    // graphics 모듈이 application 폴더를 접근할 수 있도록 처리합니다.
    exports com.itgroup.test.application to javafx.graphics;

    // 모듈 com.itgroup.www가 com.itgroup.controller 패키지를 javafx.fxml 모듈에 공개합니다.
    opens com.itgroup.test.controller to javafx.fxml;

    opens com.itgroup.test.application to javafx.fxml;

    opens com.itgroup.test to javafx.fxml;

    opens com.itgroup.test.bean to javafx.base;

    exports com.itgroup.test;
}