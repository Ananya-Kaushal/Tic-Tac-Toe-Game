module com.example.tictactoeapril {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tictactoeapril to javafx.fxml;
    exports com.example.tictactoeapril;
}