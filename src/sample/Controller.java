package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;

import java.util.Random;


public class Controller {
    @FXML
    private Canvas canv;
    @FXML
    private RadioButton radioEasy;
    @FXML
    private RadioButton radioMedium;
    @FXML
    private RadioButton radioHard;
    @FXML
    private TextArea ScoreBoard;



    @FXML
    private Button redBut,greenBut,blueBut,tealBut,magentaBut,yellowBut;

    private Random rand = new Random();
    private int[][] CellsColors;
    private int[][] CellsPlayer;
    private static int cellSize;
    private int diferent;
    private int MIN_HARD=10;private int MIN_CELLS=40;
    private int MED_HARD=20;private int MED_CELLS=20;
    private int MAX_HARD=40;private int MAX_CELLS=10;

    @FXML
    private void newGameBegin() {
        if (radioEasy.isSelected()) newGameStart(MIN_HARD,MIN_CELLS);
        if (radioMedium.isSelected()) newGameStart(MED_HARD,MED_CELLS);
        if (radioHard.isSelected()) newGameStart(MAX_HARD,MAX_CELLS);
        CellsPainting();
        printGameStatus(getGameStatus());
    }
    @FXML
    private void redButton() { buttonClick(1); }
    @FXML
    private void greenButton() {buttonClick(2); }
    @FXML
    private void blueButton() {buttonClick(3); }
    @FXML
    private void tealButton() {buttonClick(5); }
    @FXML
    private void magentaButton() {buttonClick(0); }
    @FXML
    private void yellowButton() {buttonClick(4); }




    private void newGameStart(int dif, int cellSize) {
        this.cellSize = cellSize;
        this.diferent = dif;
        CellsColors = new int[diferent][diferent];
        CellsPlayer = new int[diferent][diferent];
        for (int i = 0; i < dif; i++) {
            for (int j = 0; j < dif; j++) {
                CellsColors[i][j] = rand.nextInt(6);
                CellsPlayer[i][j] = 0;
            }
        }
        if (CellsColors[0][0] == CellsColors[dif - 1][dif - 1]) {
            CellsColors[0][0] = 1;
            CellsColors[dif - 1][dif - 1] = 0;
        }
        CellsPlayer[0][0] = 1;
        CellsPlayer[diferent - 1][diferent - 1] = 2;
        this.Move(CellsColors[0][0]);
        this.MoveComp(CellsColors[diferent - 1][diferent - 1]);
        buttonsDeactivate();
    }

    private void clearField() {
        for (int i = 0; i < diferent; i++) {
            for (int j = 0; j < diferent; j++) {
                CellsColors[i][j] = 0;
                CellsPlayer[i][j] = 0;
            }
        }
    }

    private int[] getGameStatus() {
        int[] result = new int[3];
        int a = 0, b = 0, c = 0;
        for (int i = 0; i < diferent; i++) {
            for (int j = 0; j < diferent; j++) {
                switch (CellsPlayer[i][j]) {
                    case 0: {
                        c++;
                        break;
                    }
                    case 1: {
                        a++;
                        break;
                    }
                    case 2: {
                        b++;
                        break;
                    }
                    default: {
                        System.out.println("этого быть недолжно. метод геймстатус. проверяй балбес");
                    }
                }
            }
            result[0] = a;
            result[1] = b;
            result[2] = c;
        }
        return result;
    }

    private void Move(int a) {
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0; i < diferent; i++) {
                for (int j = 0; j < diferent; j++) {
                    if (CellsPlayer[i][j] == 1) {
                        CellsColors[i][j] = a;

                        if ((j < diferent - 1) && (CellsPlayer[i][j + 1] == 0) && (CellsColors[i][j + 1] == a)) {
                            CellsPlayer[i][j + 1] = 1;
                            flag = true;
                        }
                        if ((i < diferent - 1) && (CellsPlayer[i + 1][j] == 0) && (CellsColors[i + 1][j] == a)) {
                            CellsPlayer[i + 1][j] = 1;
                            flag = true;
                        }
                        if ((i > 0) && (CellsPlayer[i - 1][j] == 0) && (CellsColors[i - 1][j] == a)) {
                            CellsPlayer[i - 1][j] = 1;
                            flag = true;
                        }
                        if ((j > 0) && (CellsPlayer[i][j - 1] == 0) && (CellsColors[i][j - 1] == a)) {
                            CellsPlayer[i][j - 1] = 1;
                            flag = true;
                        }

                    }

                }

            }
        }

    }

    private void MoveComp() {
        int a = goodMove();
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = CellsPlayer.length - 1; i != -1; i--) {
                for (int j = diferent - 1; j != -1; j--) {
                    if (CellsPlayer[i][j] == 2) {
                        CellsColors[i][j] = a;

                        if ((j < diferent - 1) && (CellsPlayer[i][j + 1] == 0) && (CellsColors[i][j + 1] == a)) {
                            CellsPlayer[i][j + 1] = 2;
                            flag = true;
                        }
                        if ((i < diferent - 1) && (CellsPlayer[i + 1][j] == 0) && (CellsColors[i + 1][j] == a)) {
                            CellsPlayer[i + 1][j] = 2;
                            flag = true;
                        }
                        if ((i > 0) && (CellsPlayer[i - 1][j] == 0) && (CellsColors[i - 1][j] == a)) {
                            CellsPlayer[i - 1][j] = 2;
                            flag = true;
                        }
                        if ((j > 0) && (CellsPlayer[i][j - 1] == 0) && (CellsColors[i][j - 1] == a)) {
                            CellsPlayer[i][j - 1] = 2;
                            flag = true;
                        }

                    }

                }

            }
        }

    }

    private void MoveComp(int a) {
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = diferent - 1; i != -1; i--) {
                for (int j = diferent - 1; j != -1; j--) {
                    if (CellsPlayer[i][j] == 2) {
                        CellsColors[i][j] = a;

                        if ((j < diferent - 1) && (CellsPlayer[i][j + 1] == 0) && (CellsColors[i][j + 1] == a)) {
                            CellsPlayer[i][j + 1] = 2;
                            flag = true;
                        }
                        if ((i < diferent - 1) && (CellsPlayer[i + 1][j] == 0) && (CellsColors[i + 1][j] == a)) {
                            CellsPlayer[i + 1][j] = 2;
                            flag = true;
                        }
                        if ((i > 0) && (CellsPlayer[i - 1][j] == 0) && (CellsColors[i - 1][j] == a)) {
                            CellsPlayer[i - 1][j] = 2;
                            flag = true;
                        }
                        if ((j > 0) && (CellsPlayer[i][j - 1] == 0) && (CellsColors[i][j - 1] == a)) {
                            CellsPlayer[i][j - 1] = 2;
                            flag = true;
                        }

                    }

                }

            }
        }

    }

    private int goodMove() {

        int[] Count_colors_cells = new int[6];

        int[][] buffCellsColors = new int[diferent][diferent];
        int[][] buffCellsPlayer = new int[diferent][diferent];

        for (int k = 0; k < 6; k++) {

            Count_colors_cells[k] = 0;

            for (int i = 0; i < diferent; i++) {                                      //Натерпелся с этим место. Изначально было так
                for (int j = 0; j < diferent; j++) {                                  //buffCellsColors=getCellsColors();
                    buffCellsColors[i][j] = CellsColors[i][j];                  //Долго думал почему CellsColors меняется.
                    buffCellsPlayer[i][j] = CellsPlayer[i][j];                  //СОздавалась не копия а ссылка. так что теперь вот так.
                }

            }

            if ((buffCellsColors[0][0] != k) && (buffCellsColors[diferent - 1][diferent - 1] != k)) {

                boolean flag = true;
                while (flag) {
                    flag = false;
                    for (int i = diferent - 1; i != -1; i--) {
                        for (int j = diferent - 1; j != -1; j--) {
                            if (buffCellsPlayer[i][j] == 2) {
                                buffCellsColors[i][j] = k;

                                if ((j < diferent - 1) && (buffCellsPlayer[i][j + 1] == 0) && (buffCellsColors[i][j + 1] == k)) {
                                    buffCellsPlayer[i][j + 1] = 2;
                                    Count_colors_cells[k]++;
                                    flag = true;
                                }
                                if ((i < diferent - 1) && (buffCellsPlayer[i + 1][j] == 0) && (buffCellsColors[i + 1][j] == k)) {
                                    buffCellsPlayer[i + 1][j] = 2;
                                    Count_colors_cells[k]++;
                                    flag = true;
                                }
                                if ((i > 0) && (buffCellsPlayer[i - 1][j] == 0) && (buffCellsColors[i - 1][j] == k)) {
                                    buffCellsPlayer[i - 1][j] = 2;
                                    Count_colors_cells[k]++;
                                    flag = true;
                                }
                                if ((j > 0) && (buffCellsPlayer[i][j - 1] == 0) && (buffCellsColors[i][j - 1] == k)) {
                                    buffCellsPlayer[i][j - 1] = 2;
                                    Count_colors_cells[k]++;
                                    flag = true;
                                }

                            }

                        }

                    }
                }
            }
        }
        /*Это кусок использовался для отслеживания правильности работы метода*/
//        System.out.println("\n" + "ATENTION");
//        for (int i = 0; i < 6; i++) {
//            System.out.print("   " + Count_colors_cells[i]);
//        }
        int max_val = Integer.MIN_VALUE, index_val = 0, sum = 0;
        for (int i = 0; i < 6; i++) {
            sum = sum + Count_colors_cells[i];
            if (Count_colors_cells[i] > max_val) {
                max_val = Count_colors_cells[i];
                index_val = i;
            }
        }
        if (sum == 0) {
            index_val = genRandColor();
        }
//        System.out.println("returned int - "+index_val);
        return index_val;
    }

    private int genRandColor() {
        int result = rand.nextInt(6);
        while ((result == CellsColors[0][0]) || (result == CellsColors[diferent - 1][diferent - 1])) {
            result = rand.nextInt(6);
        }
        return result;

    }

    private void CellsPainting(){
        GraphicsContext g = canv.getGraphicsContext2D();

        for (int i = 0; i < diferent; i++) {
            for (int j = 0; j < diferent; j++) {
                switch (CellsColors[i][j]) {
                    case 1:
                        g.setFill(Color.RED);
                        break;
                    case 2:
                        g.setFill(Color.SPRINGGREEN);
                        break;
                    case 3:
                        g.setFill(Color.BLUE);
                        break;
                    case 5:
                        g.setFill(Color.CYAN);
                        break;
                    case 0:
                        g.setFill(Color.MAGENTA);
                        break;
                    case 4:
                        g.setFill(Color.YELLOW);
                        break;
                }
                g.fillRect(i * cellSize, j * cellSize, (i + 1) * cellSize, (j + 1) * cellSize);
            }
        }
    }

    private void printGameStatus(int[] a) {
        if (a[2] == 0) {
            if (a[0] > a[1]) {
                ScoreBoard.setText("Вы победили");
                clearField();
                deactiebut();
            }
            if (a[0] < a[1]) {
                ScoreBoard.setText("Вы проиграли");
                clearField();
                deactiebut();
            }
        } else {
            ScoreBoard.setText("Краткая статистика игры" + "\n"
                    + "Игрок : " + a[0] + "\n"
                    + "Опонент : " + a[1] + "\n"
                    + "Свободно полей : " + a[2]);
        }
    }

    private void buttonClick(int a){
        Move(a);
        MoveComp();
        CellsPainting();
        buttonsDeactivate();
        printGameStatus(getGameStatus());
    }

    private void buttonsDeactivate() {
        redBut.setDisable(false);
        greenBut.setDisable(false);
        blueBut.setDisable(false);
        tealBut.setDisable(false);
        yellowBut.setDisable(false);
        magentaBut.setDisable(false);
        switch (CellsColors[0][0]) {
            case 0:
                magentaBut.setDisable(true);
                break;
            case 1:
                redBut.setDisable(true);
                break;
            case 2:
                greenBut.setDisable(true);
                break;
            case 3:
                blueBut.setDisable(true);
                break;
            case 4:
                yellowBut.setDisable(true);
                break;
            case 5:
                tealBut.setDisable(true);
                break;

        }
        switch (CellsColors[diferent-1][diferent-1]) {
            case 0:
                magentaBut.setDisable(true);
                break;
            case 1:
                redBut.setDisable(true);
                break;
            case 2:
                greenBut.setDisable(true);
                break;
            case 3:
                blueBut.setDisable(true);
                break;
            case 4:
                yellowBut.setDisable(true);
                break;
            case 5:
                tealBut.setDisable(true);
                break;

        }
    }

    private void deactiebut(){
        redBut.setDisable(true);
        greenBut.setDisable(true);
        blueBut.setDisable(true);
        tealBut.setDisable(true);
        yellowBut.setDisable(true);
        magentaBut.setDisable(true);
    }


}
