import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MineSweeper extends Application {
	public static int[][] mineMap = { { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, };
	private static int counter = MineButton.getCounter();
	public static Label time = new Label("" + counter);
	static MineButton buttons[][] = new MineButton[8][8];
	public static boolean firstClick = false;
	public static boolean stopClick = false;
	private static int firstClickX;
	private static int firstClickY;

	public static void main(String[] args) {
		launch(args);
		System.out.println(counter);
	}
	
	public static void setBombs(int firstX, int firstY){
		int bombCount = 0;
			while (bombCount != 10) {
				int bombX = (int) (Math.random() * 8);
				int bombY = (int) (Math.random() * 8);
				if (mineMap[bombX][bombY] == 9 || bombX == firstX && bombY == firstY) {
					System.out.println("Wrong Spot");
				} else if (mineMap[bombX][bombY] != 9 && bombX != firstX && bombY != firstY) {
					mineMap[bombX][bombY] = 9;
					bombCount++;
				}
			}
	}
	
	public static void setNumbers(){
		for (int i = 0; i < 8; i++) {
			for (int x = 0; x < 8; x++) {
				if (mineMap[i][x] == 9 && i > 0 && i < 7 && x > 0 && x < 7) {
					if (mineMap[i - 1][x] != 9)
						mineMap[i - 1][x] += 1;
					if (mineMap[i + 1][x] != 9)
						mineMap[i + 1][x] += 1;
					if (mineMap[i - 1][x - 1] != 9)
						mineMap[i - 1][x - 1] += 1;
					if (mineMap[i - 1][x + 1] != 9)
						mineMap[i - 1][x + 1] += 1;
					if (mineMap[i + 1][x + 1] != 9)
						mineMap[i + 1][x + 1] += 1;
					if (mineMap[i + 1][x - 1] != 9)
						mineMap[i + 1][x - 1] += 1;
					if (mineMap[i][x - 1] != 9)
						mineMap[i][x - 1] += 1;
					if (mineMap[i][x + 1] != 9)
						mineMap[i][x + 1] += 1;
					// mineMap[i + 1][x + 1] += 1;

				} else {
					// top left corner
					if (i == 0 && x == 0 && mineMap[i][x] == 9) {
						if (mineMap[i + 1][x] != 9)
							mineMap[i + 1][x] += 1;
						if (mineMap[i + 1][x + 1] != 9)
							mineMap[i + 1][x + 1] += 1;
						if (mineMap[i][x + 1] != 9)
							mineMap[i][x + 1] += 1;
						// bottom left corner
					}
					if (i == 7 && x == 0 && mineMap[i][x] == 9) {
						if (mineMap[i - 1][x] != 9)
							mineMap[i - 1][x] += 1;
						if (mineMap[i - 1][x + 1] != 9)
							mineMap[i - 1][x + 1] += 1;
						if (mineMap[i][x + 1] != 9)
							mineMap[i][x + 1] += 1;
						// top right corner
					}
					if (i == 0 && x == 7 && mineMap[i][x] == 9) {
						if (mineMap[i][x - 1] != 9)
							mineMap[i][x - 1] += 1;
						if (mineMap[i + 1][x - 1] != 9)
							mineMap[i + 1][x - 1] += 1;
						if (mineMap[i + 1][x] != 9)
							mineMap[i + 1][x] += 1;
						// bottom right corner;
					}
					if (i == 7 && x == 7 && mineMap[i][x] == 9) {
						if (mineMap[x][i - 1] != 9)
							mineMap[x][i - 1] += 1;
						if (mineMap[x - 1][i - 1] != 9)
							mineMap[x - 1][i - 1] += 1;
						if (mineMap[x - 1][i] != 9)
							mineMap[x - 1][i] += 1;
						// leftSide
					}
					if (x == 0 && i > 0 && i < 7 && mineMap[i][x] == 9) {
						if (mineMap[i - 1][x] != 9)
							mineMap[i - 1][x] += 1;
						if (mineMap[i - 1][x + 1] != 9)
							mineMap[i - 1][x + 1] += 1;
						if (mineMap[i][x + 1] != 9)
							mineMap[i][x + 1] += 1;
						if (mineMap[i + 1][x + 1] != 9)
							mineMap[i + 1][x + 1] += 1;
						if (mineMap[i + 1][x] != 9)
							mineMap[i + 1][x] += 1;
						// rightSide
					}
					if (x == 7 && i > 0 && i < 7 && mineMap[i][x] == 9) {
						if (mineMap[i + 1][x] != 9)
							mineMap[i + 1][x] += 1;
						if (mineMap[i + 1][x - 1] != 9)
							mineMap[i + 1][x - 1] += 1;
						if (mineMap[i][x - 1] != 9)
							mineMap[i][x - 1] += 1;
						if (mineMap[i - 1][x - 1] != 9)
							mineMap[i - 1][x - 1] += 1;
						if (mineMap[i - 1][x] != 9)
							mineMap[i - 1][x] += 1;
						// Top
					}
					if (i == 0 && x > 0 && x < 7 && mineMap[i][x] == 9) {
						if (mineMap[i][x - 1] != 9)
							mineMap[i][x - 1] += 1;
						if (mineMap[i + 1][x - 1] != 9)
							mineMap[i + 1][x - 1] += 1;
						if (mineMap[i + 1][x] != 9)
							mineMap[i + 1][x] += 1;
						if (mineMap[i + 1][x + 1] != 9)
							mineMap[i + 1][x + 1] += 1;
						if (mineMap[i][x + 1] != 9)
							mineMap[i][x + 1] += 1;
						// bottom
					}
					if (i == 7 && x > 0 && x < 7 && mineMap[i][x] == 9) {
						if (mineMap[i][x - 1] != 9)
							mineMap[i][x - 1] += 1;
						if (mineMap[i - 1][x - 1] != 9)
							mineMap[i - 1][x - 1] += 1;
						if (mineMap[i - 1][x] != 9)
							mineMap[i - 1][x] += 1;
						if (mineMap[i - 1][x + 1] != 9)
							mineMap[i - 1][x + 1] += 1;
						if (mineMap[i][x + 1] != 9)
							mineMap[i][x + 1] += 1;
					}
				}
			}
			for (int y = 0; y < 8; y++) {
				for (int c = 0; c < 8; c++) {
					System.out.print(mineMap[y][c]);
				}
				System.out.println();
			}
			System.out.println();
		}
	}

	public void start(Stage theStage) {
		GridPane paneTrain = new GridPane();
		HBox header = new HBox();
		TopButton topButton = new TopButton();
		VBox bp = new VBox();

		topButton.setOnAction(e -> {
			System.out.println("First CLick = " + getFirstClick());
			for (int i = 0; i < mineMap.length; i++) {
				for (int k = 0; k < mineMap.length; k++) {
					mineMap[i][k] = 0;
				}
			}
			MineSweeper.stopClick = false;
			resetFirstClick();
			MineButton.clickCounter = 10;
			MineSweeper.time.setText(MineButton.getCounter().toString());
			start(theStage);
		});

		header.getChildren().add(new Label("000"));
		header.getChildren().add(topButton);
		header.getChildren().add(time);

		header.setAlignment(Pos.TOP_CENTER);
		bp.getChildren().addAll(header, paneTrain);
		// bp.setCenter(paneTrain);

		MineButton.setB(topButton);

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				buttons[i][j] = new MineButton( i, j);
				// MineButton.setX(i);
				// MineButton.setY(j);
				//System.out.println("New MineButton X: " + buttons[i][j].getX() + " Y: " + buttons[i][j].getY());
				//buttons[i][j].setOnAction(buttons[i][j]);
				paneTrain.add(buttons[i][j], i, j);
			}
		}

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				//System.out.println("New MineButton X: " + buttons[i][j].getX() + " Y: " + buttons[i][j].getY());
			}
		}

		theStage.setTitle("MineSweeper");
		theStage.setScene(new Scene(bp));
		theStage.show();
	}

	public void checkFlag() {
		for (int i = 0; i < 8; i++) {
			for (int k = 0; k < 8; k++) {

			}
		}
	}

	public static boolean checkGame() {
		boolean win = true;

		for (int i = 0; i < 8; i++) {
			for (int y = 0; y < 8; y++) {
				System.out.print(buttons[i][y].state + "  ");
				if (mineMap[i][y] != 9 && buttons[i][y].state == 0)
					win = false;

			}
			System.out.println();
		}
		return win;
	}
	
	public static void initiateFirstClick(){
		firstClick = true;
	}
	
	public static void resetFirstClick(){
		firstClick = false;
	}
	
	public static boolean getFirstClick(){
		return firstClick;
	}
	
	public static void setFirstX(int firstX){
		firstClickX = firstX;
	}
	
	public static void setFirstY(int firstY){
		firstClickY = firstY;
	}
	
	public int getFirstX(){
		return firstClickX;
	}
	
	public int getFirstY(){
		return firstClickY;
	}
}

class TopButton extends Button implements EventHandler<ActionEvent> {
	ImageView imageCover, deadCover;

	public TopButton() {
		double size = 50;
		setMinWidth(size);
		setMaxWidth(size);
		setMinHeight(size);
		setMaxHeight(size);

		imageCover = new ImageView(new Image("/res/face-smile.png"));
		deadCover = new ImageView(new Image("/res/face-dead.png"));

		imageCover.setFitWidth(size);
		imageCover.setFitHeight(size);

		setGraphic(imageCover);

	}

	@Override
	public void handle(ActionEvent arg0) {

	}
}

class MineButton extends Button implements EventHandler<ActionEvent> {
	int state; // 0 means blank, 1 means X, 2 means O
	ImageView original, clickedPlain, imageO, bomb, deadCover, winCover, flag, redBomb, afterBomb;
	static Button xButton = new Button();
	boolean clicked = false;
	int x;
	int y;
	boolean flagged;
	int value;
	static int clickCounter = 10;
	public static boolean dead = false;
	// private static MineButton change;

	public MineButton(int x, int y) {
		flagged = false;
		clicked = false;
	//	value = bomber;
		state = 0;
		double size = 30;
		setMinWidth(size);
		setMaxWidth(size);
		setMinHeight(size);
		setMaxHeight(size);

		this.x = x;
		this.y = y;

		String name = "/res/10.png";

		original = new ImageView(new Image("/res/10.png"));
		clickedPlain = new ImageView(new Image("/res/0.png"));
		// imageO = new ImageView(new Image("/res/flag.png"));
		//bomb = new ImageView(new Image("/res/" + value + ".png"));
		// afterBomb = new ImageView(new Image("/res/mine-red.png"));
		redBomb = new ImageView(new Image("/res/-2.png"));
		deadCover = new ImageView(new Image("/res/face-dead.png"));
		winCover = new ImageView(new Image("/res/face-win.png"));
		flag = new ImageView(new Image("/res/-1.png"));

		original.setFitWidth(size);
		original.setFitHeight(size);
		clickedPlain.setFitWidth(size);
		clickedPlain.setFitHeight(size);

		setGraphic(original);
		
		setOnMouseClicked(e -> {
			if(MineSweeper.stopClick == false){
			System.out.println("Button value is " + getValue());
			if (getValue() != 9) {
				if (e.getButton() == MouseButton.PRIMARY && flagged == false) {
					System.out.println("First Click = " + MineSweeper.getFirstClick());
					if(MineSweeper.getFirstClick() == false){
						MineSweeper.initiateFirstClick();
						MineSweeper.setFirstX(getX());
						MineSweeper.setFirstY(getY());
						MineSweeper.setBombs(getX(), getY());
						MineSweeper.setNumbers();
						for(int i = 0; i < 8; i++){
							for(int j = 0; j < 8; j++){
								MineSweeper.buttons[i][j].value = MineSweeper.mineMap[i][j];
							}
						}
						//MineSweeper.mineMap
					} 
					setGraphic(new ImageView(new Image("/res/" + value + ".png")));
					System.out.println("Buttons value: " + value);
					recursion(getX(), getY());
					System.out.println(getX() + ", " + getY());
					if (MineSweeper.checkGame() == true) {
						xButton.setGraphic(winCover);
						System.out.println("tehhee");
					}
					
				}
				if (e.getButton() == MouseButton.PRIMARY && flagged == true) {
					setGraphic(flag);
				}

				if (e.getButton() == MouseButton.SECONDARY && flagged == false) {
					setGraphic(flag);
					flagged = true;
					state = 2;
					minusCounter();
					MineSweeper.time.setText(getCounter().toString());

				} else if (e.getButton() == MouseButton.SECONDARY && flagged == true) {
					setGraphic(original);
					flagged = false;
					state = 0;
					addCounter();
					MineSweeper.time.setText(getCounter().toString());
				}
				if (e.getButton() == MouseButton.MIDDLE && getGraphic() != original && flagged == false) {
					// if (state == 1) {
					// MineButton newX = MineSweeper.buttons[getX()][getY()];
					int newX = getX();
					int newY = getY();
					System.out.println("X: " + newX + " Y: " + newY);
					checkFlags(newX, newY);
					//clearAround(newX, newY);
					// }
				}

			} else {
				if (e.getButton() == MouseButton.PRIMARY && flagged == true) {
					setGraphic(flag);
				} else if (e.getButton() == MouseButton.PRIMARY && flagged == false) {
					MineSweeper.stopClick = true;
					deathSequence();
				} else if (e.getButton() == MouseButton.SECONDARY && flagged == false) {
					System.out.println("Set a flag over a bomb");
					setGraphic(flag);
					flagged = true;
					state = 2;
					minusCounter();
					MineSweeper.time.setText(getCounter().toString());
				} else if (e.getButton() == MouseButton.SECONDARY && flagged == true) {
					setGraphic(original);
					flagged = false;
					addCounter();
					MineSweeper.time.setText(getCounter().toString());
				}

			}
			}
		});
	}
	
	public void checkFlags(int x, int y){
		int flagCounter = 0;
		for(int i = -1; i < 2; i++){
			for(int k = -1; k < 2; k++){
				try{
				if(MineSweeper.buttons[x + i][y + k].flagged == true){
					flagCounter++;
				}
				} catch(IndexOutOfBoundsException e) {
					
				}
			}
		}
		System.out.println("Flags: " + flagCounter);
		if(flagCounter == MineSweeper.mineMap[x][y])
		clearAround(x, y);
	}

	public void clearAround(int x, int y) {
		boolean cracken = false;
		int cracken_x = 0;
		int cracken_y = 0;
		System.out.println("Clear around " + x + ", " + y);
		int spawnX = x;
		int spawnY = y;
		ImageView temp = new ImageView(new Image("/res/-1.png"));
		try{
		for (int k = 0; k < 8; k++) {
			for (int i = -1; i < 2; i++) {
				for (int z = -1; z < 2; z++)
		
					
		if (spawnX + i <= MineSweeper.mineMap.length && spawnX + i >= 0 && spawnY + z <= MineSweeper.mineMap.length && spawnY + z >= 0
						&& MineSweeper.mineMap[spawnX + i][spawnY + z] != 9 && MineSweeper.mineMap[spawnX + i][spawnY + z] != 0 && MineSweeper.buttons[spawnX + i][spawnY + z].state != 2) {
					MineSweeper.buttons[spawnX + i][spawnY + z].setGraphic(new ImageView(new Image("/res/" + MineSweeper.mineMap[spawnX + i][spawnY + z] + ".png")));
							MineSweeper.buttons[spawnX + i][spawnY + z].state = 1;
						} else {
							if (spawnX + i <= MineSweeper.mineMap.length && spawnX + i >= 0 && spawnY + z <= MineSweeper.mineMap.length && spawnY + z >= 0 && MineSweeper.mineMap[spawnX + i][spawnY + z] == 9
									&& MineSweeper.buttons[spawnX + i][spawnY + z].state != 2) {
								deathSequence();
							
							}
							if (spawnX + i <= MineSweeper.mineMap.length && spawnX + i >= 0 && spawnY + z <= MineSweeper.mineMap.length && spawnY + z >= 0 && MineSweeper.mineMap[spawnX + i][spawnY + z] == 0
									&& MineSweeper.buttons[spawnX + i][spawnY + z].state != 2) {
								//cracken = true;
								//cracken_x = spawnX + i;
								//cracken_y = spawnY + z;
							}
							
						}
					}
			}
		if(cracken == true){
			System.out.println("Release the Cracken!" + cracken_x + ", " + cracken_y);
			//recursion(cracken_x , cracken_y);
		}
		}catch(IndexOutOfBoundsException e){
			
		}
		}
	
	
	void recursion(int startX, int startY) {
		 if (startX < 0 || startY < 0 || startX > MineSweeper.mineMap.length - 1 || startY > MineSweeper.mineMap.length - 1)
		    	return;
		 System.out.println("Start X: " + startX + ", Start Y: " + startY);
		 if (MineSweeper.buttons[startX][startY].state == 1)
		    	return;
		    if (MineSweeper.mineMap[startX][startY] != 0){
		    	MineSweeper.buttons[startX][startY].setGraphic(new ImageView("/res/" + MineSweeper.mineMap[startX][startY] + ".png"));
		    	MineSweeper.buttons[startX][startY].state = 1;
		    	return; 
		    }
		System.out.println("Recursion start");
		MineSweeper.buttons[startX][startY].state = 1;
		MineSweeper.buttons[startX][startY].setGraphic(new ImageView("/res/" + value + ".png"));
		System.out.println("Recursion set graphic");
	    System.out.println("Recursion calling start");
	    MineSweeper.buttons[startX][startY].state = 1;
	    if(startX + 1 <= MineSweeper.mineMap.length){
	    recursion(startX + 1, startY);
	    }else{
	    	return;
	    }
	    if(startX - 1 >= 0){
	    recursion(startX - 1, startY);
	    } else {
	    	return;
	    }
	    if(startY + 1 <= MineSweeper.mineMap.length){
	    recursion(startX, startY + 1);
	    } else {
	    	return;
	    }
	    if(startY - 1 >= 0){
	    recursion(startX, startY - 1);
	    } else { 
	    	return;
	    }
	    if(startX - 1 >= 0 && startY - 1 >= 0){
	    recursion(startX - 1, startY - 1);
	    } else {
	    	return;
	    }
	    if(startX + 1 <= MineSweeper.mineMap.length && startY - 1 >=0){
	    	recursion(startX + 1, startY - 1);
	    } else {
	    	return;
	    }
	    if(startX + 1 <= MineSweeper.mineMap.length && startY + 1 <= MineSweeper.mineMap.length){
	    	recursion(startX + 1, startY + 1);
	    } else {
	    	return;
	    }
	    if(startX - 1 >= 0 && startY + 1 <= MineSweeper.mineMap.length){
	    	recursion(startX - 1, startY + 1);
	    } else {
	    	return;
	    }
	    System.out.println("Recursion end");
	}
	
	public void deathSequence() {
		System.out.println("test");
		xButton.setGraphic(deadCover);
		ImageView temp = new ImageView(new Image("/res/-2.png"));
		setGraphic(temp);
		for (int i = 0; i < MineSweeper.mineMap.length; i++) {
			for (int k = 0; k < MineSweeper.mineMap.length; k++) {
				if (MineSweeper.mineMap[i][k] == 9 && MineSweeper.buttons[i][k].getGraphic() != temp) {
					MineSweeper.buttons[i][k].setGraphic(new ImageView(new Image("/res/9.png")));
				}
			}
		}
	}

	public void setX(int hex) {
		x = hex;
	}

	public void setY(int why) {
		y = why;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

	public boolean getClicked() {
		return clicked;
	}

	public ImageView getName() {
		return original;
	}

	public static void minusCounter() {
		clickCounter--;
	}

	public static void addCounter() {
		clickCounter++;
	}

	public static Integer getCounter() {
		return clickCounter;
	}

	public static void setB(Button y) {
		xButton = y;
	}

	public static void setDead(boolean death) {
		dead = death;
	}

	public static boolean getDead() {
		return dead;
	}

	public void setValue(int newValue) {
		value = newValue;
	}

	public int getValue() {
		return value;
	}

	@Override
	public void handle(ActionEvent e) {
		state++;
		state %= 2;
		switch (state) {
		case 0:
			setGraphic(original);
			break;
		case 1:
			setGraphic(original);
			break;
		case 2:
			setGraphic(clickedPlain);
			break;
		}
	}
}