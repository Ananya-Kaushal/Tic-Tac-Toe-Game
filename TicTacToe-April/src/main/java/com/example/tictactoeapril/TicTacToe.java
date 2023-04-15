package com.example.tictactoeapril;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
public class TicTacToe extends Application {

    private Button buttons[][]=new Button[3][3];
    private Label playerXScoreLabel, playerOScoreLabel;
    private boolean playerXTurn=true;
    private int playerXScore=0,playerOScore=0;
    static String name1,name2;
    public void end(Stage stage) {
        //Title
        TextField textField1 = new TextField();
        TextField textField2 = new TextField();
        Button button = new Button("Submit");
        button.setTranslateX(250);
        button.setTranslateY(75);
        //Creating labels
        Label label1 = new Label("Player 1: ");
        Label label2 = new Label("Player 2: ");
        button.setOnAction(e -> {
            //Retrieving data
             name1 = textField1.getText().toString();
             name2 = textField2.getText().toString();
            stage.hide();
            Scene scene = new Scene(createContent());
            stage.setTitle("Tic Tac Toe");
            stage.setScene(scene);
            stage.show();
        });
        //Adding labels for nodes
        HBox box = new HBox(5);
        box.setPadding(new Insets(25, 5 , 5, 50));
        box.getChildren().addAll(label1, textField1, label2, textField2);
        Group root = new Group(box, button);
        //Setting the stage
        Scene scene = new Scene(root, 595, 150, Color.BEIGE);
        stage.setTitle("Players");
        stage.setScene(scene);
        stage.show();

    }
    private BorderPane createContent()
    {
        BorderPane root=new BorderPane();
        root.setPadding(new Insets(20));
        //Title
        Label titleLabel=new Label("Tic Tac Toe");
        root.setTop(titleLabel);
        titleLabel.setStyle("-fx-font-size : 55pt;-fx-font-weight : bold");
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        //Game Board
        GridPane gridPane=new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        for(int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                Button button=new Button();
                button.setPrefSize(150,150);
                button.setStyle("-fx-font-size : 40pt;-fx-font-weight : bold");
                button.setOnAction(event->buttonClicked(button));
                buttons[i][j]=button;
                gridPane.add(button,i,j);
            }
        }
        root.setCenter(gridPane);
        BorderPane.setAlignment(gridPane, Pos.CENTER);
        //Score
        HBox scoreBoard=new HBox(20);
        scoreBoard.setAlignment(Pos.CENTER);
        playerXScoreLabel = new Label("Player "+name1+" : 0");
        //playerXScoreLabel = new Label("Player X : 0");
        playerXScoreLabel.setStyle("-fx-font-size : 20pt;-fx-font-weight : bold");
        playerOScoreLabel = new Label("Player "+name2+" : 0");
        //playerOScoreLabel = new Label("Player O : 0");
        playerOScoreLabel.setStyle("-fx-font-size : 20pt;-fx-font-weight : bold");
        scoreBoard.getChildren().addAll(playerXScoreLabel,playerOScoreLabel);

        root.setBottom(scoreBoard);

        return root;
    }
    private void buttonClicked(Button button)
    {
        if(button.getText().equals("")) {
            if (playerXTurn == true) {
                button.setText("X");
                button.setStyle("-fx-base: lightblue;-fx-font-size : 45pt;-fx-font-weight : bold");
            } else {
                button.setText("O");
                button.setStyle("-fx-base: pink;-fx-font-size : 45pt;-fx-font-weight : bold");
            }
            playerXTurn = !playerXTurn;
            checkWinner();
        }
    }
    private void checkWinner()
    {
        //row
        for(int i=0;i<3;i++)
        {
            if(buttons[i][0].getText().equals(buttons[i][1].getText()) &&  buttons[i][0].getText().equals(buttons[i][2].getText())
                    && !buttons[i][0].getText().isEmpty())
            {
                String winner = buttons[i][0].getText();
                System.out.println("Winner is "+winner);
                showWinnerDialog(winner);
                updateScore(winner);
                resetBoard();
                return;
            }
        }
        //column
        for(int i=0;i<3;i++)
        {
            if(buttons[0][i].getText().equals(buttons[1][i].getText()) &&  buttons[0][i].getText().equals(buttons[2][i].getText())
                    && !buttons[0][i].getText().isEmpty())
            {
                String winner = buttons[0][i].getText();
                System.out.println("Winner is "+winner);
                showWinnerDialog(winner);
                updateScore(winner);
                resetBoard();
                return;
            }
        }
        //diagonal
        if(buttons[0][0].getText().equals(buttons[1][1].getText()) &&  buttons[1][1].getText().equals(buttons[2][2].getText())
        && !buttons[0][0].getText().isEmpty())
        {
            String winner = buttons[0][0].getText();
            System.out.println("Winner is "+winner);
            showWinnerDialog(winner);
            updateScore(winner);
            resetBoard();
            return;
        }
        else if(buttons[2][0].getText().equals(buttons[1][1].getText()) &&  buttons[1][1].getText().equals(buttons[0][2].getText())
                && !buttons[2][0].getText().isEmpty())
        {
            String winner = buttons[2][0].getText();
            System.out.println("Winner is "+winner);
            showWinnerDialog(winner);
            updateScore(winner);
            resetBoard();
            return;
        }
        //tie
        boolean tie=true;
        for (Button[] row: buttons) {
            for (Button button:row) {
                if(button.getText().isEmpty()) {
                    tie = false;
                    break;
                }
            }
        }
        if(tie==true)
        {
            showTieDialog();
            resetBoard();
        }
    }
    private void showWinnerDialog(String winner)
    {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("WINNER");
        if(winner.equals("X"))
        {
            alert.setContentText("Congratulations "+name1+" You Won the game!!!");
        }
        else
        {
            alert.setContentText("Congratulations "+name2+" You Won the game!!!");
        }
        alert.setHeaderText("");
        alert.showAndWait();
    }
    private void showTieDialog()
    {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("TIE");
        alert.setContentText("Tie between Player 1 and Player 2 !!....Game Over");
        alert.setHeaderText("");
        alert.showAndWait();
    }
    private void updateScore(String winner)
    {
        if(winner.equals("X"))
        {
            playerXScore++;
            playerXScoreLabel.setText("Player "+name1+" : "+playerXScore);
        }
        else {
            playerOScore++;
            playerOScoreLabel.setText("Player "+name2+" : "+playerOScore);
        }
    }
    private void resetBoard()
    {
        for (Button[] row: buttons) {
            for (Button button:row) {
                button.setText("");
                button.setStyle("");
            }
        }
    }
   @Override
    public void start(Stage stage) throws IOException {
        end(new Stage());
    }

    public static void main(String[] args) {
        launch();
    }
}
