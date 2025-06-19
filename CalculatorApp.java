import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CalculatorApp extends Application {
    private Label display;
    private String currentInput = "";
    private double operand1 = 0;
    private String operator = "";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        display = new Label("0");
        display.setFont(new Font("Arial", 24));

        GridPane buttonsGrid = new GridPane();
        buttonsGrid.setAlignment(Pos.CENTER);
        buttonsGrid.setHgap(10);
        buttonsGrid.setVgap(10);

        String[][] buttonTexts = {
                {"7", "8", "9", "/"},
                {"4", "5", "6", "*"},
                {"1", "2", "3", "-"},
                {"C", "0", "=", "+"}
        };

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Button btn = new Button(buttonTexts[i][j]);
                btn.setMinSize(50, 50);
                btn.setOnAction(e -> handleButtonClick(btn.getText()));
                buttonsGrid.add(btn, j, i);
            }
        }

        VBox layout = new VBox(20, display, buttonsGrid);
        layout.setAlignment(Pos.CENTER);

        primaryStage.setScene(new Scene(layout, 250, 350));
        primaryStage.setTitle("JavaFX Calculator");
        primaryStage.show();
    }

    private void handleButtonClick(String text) {
        switch (text) {
            case "C":
                currentInput = "";
                operand1 = 0;
                operator = "";
                display.setText("0");
                break;
            case "=":
                calculateResult();
                break;
            case "+": case "-": case "*": case "/":
                if (!currentInput.isEmpty()) {
                    operand1 = Double.parseDouble(currentInput);
                    operator = text;
                    currentInput = "";
                    display.setText(operator);
                }
                break;
            default:
                currentInput += text;
                display.setText(currentInput);
        }
    }

    private void calculateResult() {
        if (!currentInput.isEmpty() && !operator.isEmpty()) {
            double operand2 = Double.parseDouble(currentInput);
            double result = switch (operator) {
                case "+" -> operand1 + operand2;
                case "-" -> operand1 - operand2;
                case "*" -> operand1 * operand2;
                case "/" -> operand2 != 0 ? operand1 / operand2 : 0;
                default -> 0;
            };
            display.setText(String.valueOf(result));
            currentInput = "";
            operator = "";
        }
    }
}