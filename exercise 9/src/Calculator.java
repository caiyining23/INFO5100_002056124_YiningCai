import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Calculator extends Application {

    private TextField display = new TextField();
    private CalculatorLogic logic = new CalculatorLogic(); // 引入运算逻辑类
    private double num1 = 0;
    private String operator = "";

    @Override
    public void start(Stage primaryStage) {
        // TextField 显示输入和结果
        display.setEditable(false);
        display.setPrefHeight(50);
        display.setStyle("-fx-font-size: 18px;");

        // 数字按钮
        Button[] digitButtons = new Button[10];
        for (int i = 0; i <= 9; i++) {
            int digit = i;
            digitButtons[i] = new Button(String.valueOf(i));
            digitButtons[i].setPrefSize(60, 60);
            digitButtons[i].setOnAction(e -> display.appendText(String.valueOf(digit)));
        }

        // 操作符按钮
        Button addButton = createOperatorButton("+");
        Button subtractButton = createOperatorButton("-");
        Button multiplyButton = createOperatorButton("*");
        Button divideButton = createOperatorButton("/");

        // 等号按钮
        Button equalsButton = new Button("=");
        equalsButton.setPrefSize(60, 60);
        equalsButton.setOnAction(e -> calculateResult());

        // 清除按钮
        Button clearButton = new Button("C");
        clearButton.setPrefSize(60, 60);
        clearButton.setOnAction(e -> clear());

        // 布局
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        // 将控件添加到布局
        grid.add(display, 0, 0, 4, 1);

        // 添加数字按钮
        int row = 1;
        for (int i = 7; i <= 9; i++) grid.add(digitButtons[i], i - 7, row);
        row++;
        for (int i = 4; i <= 6; i++) grid.add(digitButtons[i], i - 4, row);
        row++;
        for (int i = 1; i <= 3; i++) grid.add(digitButtons[i], i - 1, row);
        grid.add(digitButtons[0], 1, row + 1);

        // 添加操作符按钮和功能按钮
        grid.add(addButton, 3, 1);
        grid.add(subtractButton, 3, 2);
        grid.add(multiplyButton, 3, 3);
        grid.add(divideButton, 3, 4);
        grid.add(equalsButton, 2, 4);
        grid.add(clearButton, 0, 4);

        // 设置场景和舞台
        Scene scene = new Scene(grid, 300, 400);
        primaryStage.setTitle("Four Function Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createOperatorButton(String op) {
        Button button = new Button(op);
        button.setPrefSize(60, 60);
        button.setOnAction(e -> {
            if (!display.getText().isEmpty()) {
                num1 = Double.parseDouble(display.getText());
                operator = op;
                display.clear();
            }
        });
        return button;
    }

    private void calculateResult() {
        if (!display.getText().isEmpty() && !operator.isEmpty()) {
            double num2 = Double.parseDouble(display.getText());
            double result = logic.performOperation(num1, num2, operator); // 调用逻辑类
            display.setText(String.valueOf(result));
            operator = "";
        }
    }

    private void clear() {
        display.clear();
        num1 = 0;
        operator = "";
    }

    public static void main(String[] args) {
        launch(args);
    }
}
