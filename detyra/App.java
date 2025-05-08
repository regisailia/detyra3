
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.animation.TranslateTransition;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;

public class App extends Application {
    private Circle ball;
    private Rectangle target;
    private Label scoreLabel;
    private int score = 0;

    @Override
    public void start(Stage primaryStage) {
        // Create root pane
        Pane root = new Pane();
        root.setPrefSize(800, 600);
        root.setStyle("-fx-background-color: lightblue;");

        // Create ball
        ball = new Circle(20, Color.RED);
        ball.setTranslateX(100);
        ball.setTranslateY(300);

        // Create target
        target = new Rectangle(40, 40, Color.GREEN);
        target.setTranslateX(700);
        target.setTranslateY(300);

        // Create score label
        scoreLabel = new Label("Score: 0");
        scoreLabel.setTranslateX(20);
        scoreLabel.setTranslateY(20);

        // Create reset button
        Button resetButton = new Button("Reset Game");
        resetButton.setTranslateX(20);
        resetButton.setTranslateY(550);

        // Add event handlers
        ball.setOnMouseClicked(this::handleBallClick);
        resetButton.setOnAction(e -> resetGame());

        // Add nodes to root
        root.getChildren().addAll(ball, target, scoreLabel, resetButton);

        // Create scene
        Scene scene = new Scene(root);
        primaryStage.setTitle("Animation Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleBallClick(MouseEvent event) {
        // Create translate animation
        TranslateTransition translate = new TranslateTransition(Duration.seconds(1), ball);
        translate.setToX(target.getTranslateX());
        translate.setToY(target.getTranslateY());

        // Create fade animation for target
        FadeTransition fade = new FadeTransition(Duration.seconds(0.5), target);
        fade.setFromValue(1.0);
        fade.setToValue(0.2);
        fade.setAutoReverse(true);
        fade.setCycleCount(2);

        // Play animations
        translate.play();
        fade.play();

        // Update score when ball reaches target
        translate.setOnFinished(e -> {
            score++;
            scoreLabel.setText("Score: " + score);
            resetBallPosition();
        });
    }

    private void resetBallPosition() {
        ball.setTranslateX(100);
        ball.setTranslateY(300);
    }

    private void resetGame() {
        score = 0;
        scoreLabel.setText("Score: 0");
        resetBallPosition();
    }

    public static void main(String[] args) {
        launch(args);
    }
}