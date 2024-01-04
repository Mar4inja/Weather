module com.example.project_try_01 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens com.example.project_try_01 to javafx.fxml;
    exports com.example.project_try_01;
}