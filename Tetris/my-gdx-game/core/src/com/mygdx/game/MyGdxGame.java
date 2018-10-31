package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;

	Texture cursor, cursorTest;
	float cursorX;
	float cursorY;
	float cursorA;
	float cursorB;
	float cursorC;
	float cursorD;
	float cursorE;
	float cursorF;
	
	BitmapFont scoreText;
	
	int score = 0;
	int difficulty = 0;
	
	double limit;
	
	int xcord, ycord, acord, bcord, ccord, dcord, ecord, fcord, currentLines;
	int lines = 0;
	
	int blockType, blockNumber;
	float accum, blockState;
	
	boolean[][] booleanGrid = new boolean[15][24];

	boolean moveLeft = false;
	boolean moveLeftOld = false;
	boolean moveRight = false;
	boolean moveRightOld = false;
	boolean moveDown = false;
	boolean moveDownOld = false;
	boolean rotateNow = false;
	boolean rotateNowOld = false;
	boolean spaceNow = false;
	boolean spaceOld = false;
	
	Texture block;
	float blockW = 20;
	float blockH = 20;
	
	Texture background;
	float backgroundW = 20;
	float backgroundH = 20;
	
	int gridW = 15;
	int gridH = 24;
	
	int counter = 0;
	
	Music mp3Sound;
	Music beep;
	
	Random rng = new Random();
	
	int[][] grid = new int[gridW][gridH];
	
	@Override
	public void create () {	// creates all the blocks, grids, sounds, cursors etc.
		
		for(int y = 0; y < gridH; y++) {
			for(int x = 0; x < gridW; x++) {
				if(x == 0 || x == gridW - 1 || y == 0 || y == gridH - 1) {
					grid[x][y] = 1;
				}
				else {
					grid[x][y] = 0;					
				}
			}
		}
		
		scoreText = new BitmapFont();
		
		cursorTest = new Texture("blueblock.png");
		
		mp3Sound = Gdx.audio.newMusic(Gdx.files.internal("song.mp3"));			// FantomenK - A Tiny Spaceship's Final Mission
		mp3Sound.setVolume((float) 0.3);
		mp3Sound.setLooping(true);
		mp3Sound.play();														// https://www.youtube.com/watch?v=GIN_cpdLyw4&list=UUMSBjXolfz29kxnXpBa7LJA
		
		beep = Gdx.audio.newMusic(Gdx.files.internal("beep.wav"));
		beep.setVolume((float) 0.1);
		
		blockNumber = rng.nextInt(6)+3; //generates a number between 3-8
		
		if (blockNumber == 1)	// ---
		{
			cursorX = 140;
			cursorY = 440;		
			cursorA = cursorX;
			cursorB = cursorY - 20;
			cursorC = cursorX;
			cursorD = cursorY - 40;
			blockType = 1;
			blockState = 1;
			cursor = new Texture("block1.jpg");
		}
		if (blockNumber == 2) 	// :.
		{
			cursorX = 140;
			cursorY = 440;		
			cursorA = cursorX;
			cursorB = cursorY - 20;
			cursorC = cursorX + 20;
			cursorD = cursorY - 20;
			blockType = 1;
			blockState = 1;
			cursor = new Texture("block2.jpg");
		}
		
		if (blockNumber == 3)	// ----
		{
			cursorX = 140;
			cursorY = 440;		
			cursorA = cursorX;
			cursorB = cursorY - 20;
			cursorC = cursorX;
			cursorD = cursorY - 40;
			cursorE = cursorX;
			cursorF = cursorY - 60;		
			blockType = 2;
			blockState = 1;
			cursor = new Texture("block3.jpg");
		}
		if (blockNumber == 4)	// ::
		{
			cursorX = 140;
			cursorY = 440;		
			cursorA = cursorX;
			cursorB = cursorY - 20;
			cursorC = cursorX + 20;
			cursorD = cursorY;
			cursorE = cursorX + 20;
			cursorF = cursorY - 20;		
			blockType = 2;
			blockState = 1;
			cursor = new Texture("block4.jpg");
		}
		if (blockNumber == 5)	// :..
		{
			cursorX = 140;
			cursorY = 440;		
			cursorA = cursorX + 20;
			cursorB = cursorY;
			cursorC = cursorX;
			cursorD = cursorY - 20;
			cursorE = cursorX;
			cursorF = cursorY - 40;		
			blockType = 2;
			blockState = 1;
			cursor = new Texture("block5.png");
		}
		if (blockNumber == 6)	// ":.
		{
			cursorX = 160;
			cursorY = 440;		
			cursorA = cursorX;
			cursorB = cursorY - 20;
			cursorC = cursorX - 20;
			cursorD = cursorY - 20;
			cursorE = cursorX - 20;
			cursorF = cursorY - 40;		
			blockType = 2;
			blockState = 1;
			cursor = new Texture("block6.png");
		}
		if (blockNumber == 7)	// ..:
		{
			cursorX = 140;
			cursorY = 440;		
			cursorA = cursorX + 20;
			cursorB = cursorY;
			cursorC = cursorX + 20;
			cursorD = cursorY - 20;
			cursorE =cursorX + 20;
			cursorF = cursorY - 40;		
			blockType = 2;
			blockState = 1;
			cursor = new Texture("block7.png");
		}
		if (blockNumber == 8)	// .:"
		{
			cursorX = 140;
			cursorY = 440;		
			cursorA = cursorX;
			cursorB = cursorY - 20;
			cursorC = cursorX + 20;
			cursorD = cursorY - 20;
			cursorE = cursorX + 20;
			cursorF = cursorY - 40;		
			blockType = 2;
			blockState = 1;
			cursor = new Texture("block8.png");
		}
	
		
		batch = new SpriteBatch();
		block = new Texture("border.jpg");
		background = new Texture("background.jpg");
	}
	
	 @Override
    public void dispose() {
        batch.dispose();
        cursor.dispose();
		block.dispose();
    }
	 

	@Override
	public void render () {	// handles user input and updates the game as the user plays
				
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
        	rotateNow = true;
        	if (rotateNow && !rotateNowOld && checkLegalRotate()){
        		rotate();
        		beep.play();
        	}
        	rotateNowOld = rotateNow;
        }
        rotateNowOld = (Gdx.input.isKeyPressed(Input.Keys.UP));
        
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
        	if (checkInsideGridY() == true){
        		if (checkDownCollision() == false){
		        	moveDown = true;
		        	if (moveDown && !moveDownOld){
		        		cursorY-=20;
		        		cursorB-=20;
		        		cursorD-=20;
		        		cursorF-=20;
		        	}
		        	moveDownOld = moveDown;
        		}
        	}
        	else{
        		beep.play();
        		updateBooleanGrid();
        		createBlock();
        	}
        }
        moveDownOld = Gdx.input.isKeyPressed(Input.Keys.DOWN); 
        
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
        	if (checkInsideGridXLeft() == true){
        		if (checkLeftCollision() == false){
		        	moveLeft = true;
		        	if (moveLeft && !moveLeftOld){
		        		cursorX-=20;
		        		cursorA-=20;
		        		cursorC-=20;
		        		cursorE-=20;
		        	}
		        	moveLeftOld = moveLeft;
        		}
        	}
        }
        moveLeftOld = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
        	if (checkInsideGridXRight() == true){
        		if (checkRightCollision() == false){
		        	moveRight = true;
			        if (moveRight && !moveRightOld){
			       		cursorX+=20;
			       		cursorA+=20;
			       		cursorC+=20;
			       		cursorE+=20;
			       	}
			       	moveRightOld = moveRight;
        		}
        	}
        }
        moveRightOld = Gdx.input.isKeyPressed(Input.Keys.RIGHT);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
	
		
		xcord = (int)cursorX/20;
		ycord = (int)cursorY/20;
		acord = (int)cursorA/20;
		bcord = (int)cursorB/20;
		ccord = (int)cursorC/20;
		dcord = (int)cursorD/20;
		ecord = (int)cursorE/20;
		fcord = (int)cursorF/20;
		
		if (checkLose() == true){
			mp3Sound.stop();
		}
		
		if (completeLine()){
			lines++;
			score += getScore();
			
		}
			
		difficulty = getDifficulty();

		for(int y = 0; y < gridH; y++) {		
			for(int x = 0; x < gridW; x++) {
				if(grid[x][y] == 1) {
					batch.draw(block, x * blockW, y * blockH);
					booleanGrid[x][y] = true;
				}
				if(grid[x][y] == 2) {
					batch.draw(cursor, x * blockW, y * blockH);
				}
			}
		}
		
		for (int y = 1; y < gridH-1; y++){
			for (int x = 1; x < gridW-1; x++){
				batch.draw(background, x * backgroundW, y * backgroundH);
			}
		}
		
		if (blockType == 1){
			batch.draw(cursor, cursorX, cursorY);
			batch.draw(cursor, cursorA, cursorB);
			batch.draw(cursor, cursorC, cursorD);
		}
		if (blockType == 2){
			batch.draw(cursor, cursorX, cursorY);
			batch.draw(cursor, cursorA, cursorB);
			batch.draw(cursor, cursorC, cursorD);
			batch.draw(cursor, cursorE, cursorF);
		}
		
		accum += Gdx.graphics.getDeltaTime();
		limit = getLimit();
		
		if (accum > limit)
		{
			if (checkInsideGridY() == true){
				cursorY-=20;
	    		cursorB-=20;
	    		cursorD-=20;
	    		cursorF-=20;
	    		accum -= limit;
			}
			else{
				updateBooleanGrid();
				createBlock();
			}
		}
		
		DrawUpdatedGrid();
				
		scoreText.draw(batch, "TETRIS", 440, 465);
		scoreText.draw(batch , "SCORE: " + score, 350, 400);
		scoreText.draw(batch , "LINES CLEARED: " + lines, 350, 350);
		scoreText.draw(batch , "LEVEL: " + getDifficulty(), 350, 375);
		scoreText.draw(batch, "CONTROLS", 425, 320);
		scoreText.draw(batch, "LEFT = MOVE LEFT", 315, 285);
		scoreText.draw(batch, "RIGHT = MOVE RIGHT", 315, 260);
		scoreText.draw(batch, "UP = ROTATE BLOCK", 315, 235);
		scoreText.draw(batch, "DOWN = INCREASE FALL SPEED", 315, 210);
		scoreText.draw(batch , "Tip: You can rotate blocks through other blocks", 320, 115);
		scoreText.draw(batch , "to get out of tight spots!", 390, 95);
		scoreText.draw(batch , "Music: FantomenK - A Tiny Spaceship's Final Mission", 300, 50);
		scoreText.draw(batch , "https://www.youtube.com/user/FantomenK", 340, 30);

		batch.end();
	}
	
	@Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
	
    // handles the rotation of the blocks, updating the coordinates of the new locations of the blocks
    public void rotate(){ 
    	
    	if (blockNumber == 1){
    		if (blockState/4 == 0.25){
    			cursorA+=20;
    			cursorB-=20;
    			cursorX+=40;
    			cursorY-=40;
    		}
    		
    		if (blockState/4 == 0.50){
    			cursorA-=20;
    			cursorB+=20;
    			cursorX-=40;
    			cursorY+=40;
    		}
    		
    		if (blockState/4 == 0.75){
    			cursorA+=20;
    			cursorB-=20;
    			cursorX+=40;
    			cursorY-=40;
    		}
    		if (blockState/4 == 1){
    			cursorA-=20;
    			cursorB+=20;
    			cursorX-=40;
    			cursorY+=40;
    			blockState -= 4;
    		}
    		blockState++;
    	}
  
    	if (blockNumber == 2){
    		if (blockState/4 == 0.25){
    			cursorB+=20;
    			cursorC-=20;
    			cursorX+=20;
    		}
    		
    		if (blockState/4 == 0.50){
    			cursorD+=20;
    			cursorA+=20;
    			cursorY-=20;
    		}
    		
    		if (blockState/4 == 0.75){
    			cursorC+=20;
    			cursorB-=20;
    			cursorX-=20;
    		}
    		if (blockState/4 == 1){
    			cursorA-=20;
    			cursorD-=20;
    			cursorY+=20;
    			blockState -= 4;
    		}
    		blockState++;
    	}
    	
    	if (blockNumber == 3){
	    	if (blockState/4 == 0.25){
				cursorA+=40;
				cursorB-=40;
				cursorC+=20;
				cursorD-=20;
				cursorX+=60;
				cursorY-=60;
			}
			
			if (blockState/4 == 0.50){
				cursorA-=40;
				cursorB+=40;
				cursorC-=20;
				cursorD+=20;
				cursorX-=60;
				cursorY+=60;
			}
			
			if (blockState/4 == 0.75){
				cursorA+=40;
				cursorB-=40;
				cursorC+=20;
				cursorD-=20;
				cursorX+=60;
				cursorY-=60;
			}
			if (blockState/4 == 1){
				cursorA-=40;
				cursorB+=40;
				cursorC-=20;
				cursorD+=20;
				cursorX-=60;
				cursorY+=60;
				blockState -= 4;
			}
			blockState++;
		}
    	
    	// nothing for block 4

    	if (blockNumber == 5){
    		if (blockState/4 == 0.25){
				cursorA+=20;
				cursorB-=60;
				cursorC+=20;
				cursorD-=20;
				cursorX+=40;
				cursorY-=40;
				
			}
			
			if (blockState/4 == 0.50){
				cursorA-=40;
				cursorB+=20;
				cursorD+=20;
				cursorE+=20;
				cursorF+=40;
				cursorX-=20;
			}
			
			if (blockState/4 == 0.75){
				cursorB+=40;
				cursorE+=20;
				cursorF-=20;
				cursorX-=20;
				cursorY+=20;
			}
			if (blockState/4 == 1){
				cursorA+=20;
				cursorC-=20;
				cursorE-=40;
				cursorF-=20;
				cursorY+=20;
				blockState -= 4;
			}
			blockState++;
    	}

    	if (blockNumber == 6){
    		if (blockState/4 == 0.25){
				cursorC+=20;
				cursorD+=20;
				cursorF+=40;
				cursorX+=20;
				cursorY-=20;
			}
			
			if (blockState/4 == 0.50){
				cursorC-=20;
				cursorD-=20;
				cursorF-=40;
				cursorX-=20;
				cursorY+=20;
			}
			
			if (blockState/4 == 0.75){
				cursorC+=20;
				cursorD+=20;
				cursorF+=40;
				cursorX+=20;
				cursorY-=20;
			}
			if (blockState/4 == 1){
				cursorC-=20;
				cursorD-=20;
				cursorF-=40;
				cursorX-=20;
				cursorY+=20;
				blockState -= 4;
			}
			blockState++;
    	}

    	if (blockNumber == 7){
    		if (blockState/4 == 0.25){
				cursorA+=20;
				cursorB-=20;
				cursorE-=20;
				cursorF+=20;
				cursorX+=40;
			}
			
			if (blockState/4 == 0.50){
				cursorA-=40;
				cursorB-=20;
				cursorC-=20;
				cursorF+=20;
				cursorX-=20;
				cursorY-=40;
			}
			
			if (blockState/4 == 0.75){
				cursorB+=40;
				cursorC+=20;
				cursorD+=20;
				cursorE+=40;
				cursorX-=20;
				cursorY+=20;
			}
			if (blockState/4 == 1){
				cursorA+=20;
				cursorD-=20;
				cursorE-=20;
				cursorF-=40;
				cursorY+=20;
				blockState -= 4;
			}
			blockState++;
    	}

    	if (blockNumber == 8){
    		if (blockState/4 == 0.25){
				cursorA+=20;
				cursorB+=20;
				cursorE-=20;
				cursorF+=20;
				cursorX+=40;
			}
			
			if (blockState/4 == 0.50){
				cursorA-=20;
				cursorB-=20;
				cursorE+=20;
				cursorF-=20;
				cursorX-=40;
			}
			
			if (blockState/4 == 0.75){
				cursorA+=20;
				cursorB+=20;
				cursorE-=20;
				cursorF+=20;
				cursorX+=40;
			}
			if (blockState/4 == 1){
				cursorA-=20;
				cursorB-=20;
				cursorE+=20;
				cursorF-=20;
				cursorX-=40;
				blockState -= 4;
			}
			blockState++;
    	}
    }
	
    // creates a new block once the old one reaches the bottom.
    public void createBlock(){	
    	blockNumber = rng.nextInt(6)+3;
    	if (blockNumber == 1)	// ---
		{
			cursorX = 140;
			cursorY = 440;		
			cursorA = cursorX;
			cursorB = cursorY - 20;
			cursorC = cursorX;
			cursorD = cursorY - 40;
			blockType = 1;
			blockState = 1;
			cursor = new Texture("block1.jpg");
		}
		if (blockNumber == 2) 	// :.
		{
			cursorX = 140;
			cursorY = 440;		
			cursorA = cursorX;
			cursorB = cursorY - 20;
			cursorC = cursorX + 20;
			cursorD = cursorY - 20;
			blockType = 1;
			blockState = 1;
			cursor = new Texture("block2.jpg");
		}
		
		if (blockNumber == 3)	// ----
		{
			cursorX = 140;
			cursorY = 440;		
			cursorA = cursorX;
			cursorB = cursorY - 20;
			cursorC = cursorX;
			cursorD = cursorY - 40;
			cursorE = cursorX;
			cursorF = cursorY - 60;		
			blockType = 2;
			blockState = 1;
			cursor = new Texture("block3.jpg");
		}
		if (blockNumber == 4)	// ::
		{
			cursorX = 140;
			cursorY = 440;		
			cursorA = cursorX;
			cursorB = cursorY - 20;
			cursorC = cursorX + 20;
			cursorD = cursorY;
			cursorE = cursorX + 20;
			cursorF = cursorY - 20;		
			blockType = 2;
			blockState = 1;
			cursor = new Texture("block4.jpg");
		}
		if (blockNumber == 5)	// :..
		{
			cursorX = 140;
			cursorY = 440;		
			cursorA = cursorX + 20;
			cursorB = cursorY;
			cursorC = cursorX;
			cursorD = cursorY - 20;
			cursorE = cursorX;
			cursorF = cursorY - 40;		
			blockType = 2;
			blockState = 1;
			cursor = new Texture("block5.png");
		}
		if (blockNumber == 6)	// ":.
		{
			cursorX = 160;
			cursorY = 440;		
			cursorA = cursorX;
			cursorB = cursorY - 20;
			cursorC = cursorX - 20;
			cursorD = cursorY - 20;
			cursorE = cursorX - 20;
			cursorF = cursorY - 40;		
			blockType = 2;
			blockState = 1;
			cursor = new Texture("block6.png");
		}
		if (blockNumber == 7)	// ..:
		{
			cursorX = 140;
			cursorY = 440;		
			cursorA = cursorX + 20;
			cursorB = cursorY;
			cursorC = cursorX + 20;
			cursorD = cursorY - 20;
			cursorE =cursorX + 20;
			cursorF = cursorY - 40;		
			blockType = 2;
			blockState = 1;
			cursor = new Texture("block7.png");
		}
		if (blockNumber == 8)	// .:"
		{
			cursorX = 140;
			cursorY = 440;		
			cursorA = cursorX;
			cursorB = cursorY - 20;
			cursorC = cursorX + 20;
			cursorD = cursorY - 20;
			cursorE = cursorX + 20;
			cursorF = cursorY - 40;		
			blockType = 2;
			blockState = 1;
			cursor = new Texture("block8.png");
		}
    }
    
    public boolean checkInsideGridY(){ // checks whether a block is inside the grid 
    	if (blockType == 1){
	    	if (ycord == 1 || bcord == 1 || dcord == 1 || fcord == 1 || booleanGrid[xcord][ycord-1] == true || booleanGrid[acord][bcord-1] == true || booleanGrid[ccord][dcord-1] == true){
	    		return false;
	    	}
   	}
    	if (blockType == 2){
	    	if (ycord == 1 || bcord == 1 || dcord == 1 || fcord == 1 || booleanGrid[xcord][ycord-1] == true || booleanGrid[acord][bcord-1] == true || booleanGrid[ccord][dcord-1] == true || booleanGrid[ecord][fcord-1] == true){
	    		return false;
	    	}
   	}
    	return true;
    }
    public boolean checkInsideGridXRight(){ // checks whether a block in inside the grid
    	if (xcord == 13 || acord == 13 || ccord == 13 || ecord == 13){
    		return false;
    	}
    	return true;
    }
    
    public boolean checkInsideGridXLeft(){ // checks whether a block in inside the grid
    	if (xcord == 1 || acord == 1 || ccord == 1 ||ecord == 1){
    		return false;
    	}
    	return true;
    }
    
    public boolean checkRightCollision(){ // checks whether a block collides with anything on its right when trying to move
    	if (blockType == 1){
	    	if (booleanGrid[xcord+1][ycord] == true || booleanGrid[acord+1][bcord] == true || booleanGrid[ccord+1][dcord] == true){
	    		return true;
	    	}
    	}
    	if (blockType == 2){
    		if (booleanGrid[xcord+1][ycord] == true || booleanGrid[acord+1][bcord] == true || booleanGrid[ccord+1][dcord] == true || booleanGrid[ecord+1][fcord] == true){
    			return true;
    		}
    	}
    	return false;
    }
    
    public boolean checkLeftCollision(){ // checks whether a block collides with anything on its left when trying to move
    	if (blockType == 1){
	    	if (booleanGrid[xcord-1][ycord] == true || booleanGrid[acord-1][bcord] == true || booleanGrid[ccord-1][dcord] == true){
	    		return true;
	    	}
    	}
    	if (blockType == 2){
    		if (booleanGrid[xcord-1][ycord] == true || booleanGrid[acord-1][bcord] == true || booleanGrid[ccord-1][dcord] == true || booleanGrid[ecord-1][fcord] == true){
    			return true;
    		}
    	}
    	return false;
    }

    public boolean checkDownCollision(){ // checks whether a block collides with anything on below it when trying to move
    	if (blockType == 1){
	    	if (booleanGrid[xcord][ycord -1] == true || booleanGrid[acord][bcord -1] == true || booleanGrid[ccord][dcord -1] == true){
	    		return true;
	    	}
    	}
    	if (blockType == 2){
    		if (booleanGrid[xcord][ycord -1] == true || booleanGrid[acord][bcord -1] == true || booleanGrid[ccord][dcord -1] == true || booleanGrid[ecord][fcord-1] == true){
    			return true;
    		}
    	}
    	return false;
	}
    
    public void updateBooleanGrid()	// updates the grid when a block reaches the bottom or collides with another block
    {
    	if (blockType == 1){
    		booleanGrid[xcord][ycord] = true;
    		booleanGrid[acord][bcord] = true;
    		booleanGrid[ccord][dcord] = true;
    		
    		
    	}
    	if (blockType == 2){
    		booleanGrid[xcord][ycord] = true;
    		booleanGrid[acord][bcord] = true;
    		booleanGrid[ccord][dcord] = true;
    		booleanGrid[ecord][fcord] = true;
    	}
    }
    public void DrawUpdatedGrid(){	// places a block icon where the grid is filled with blocks
    	for (int i=1; i<14; i++){
    		for (int j=1; j<23; j++){
    			if (booleanGrid[i][j] == true){
    				batch.draw(cursorTest, i*20, j*20);
    			}
    		}
    	}
    }
    public boolean completeLine(){	//checks whether a full line has been filled up, once checked brings the lines above it down
    	for (int j = 1; j < 22; j++){
    		if (booleanGrid[1][j] == true && booleanGrid[2][j] == true && booleanGrid[3][j] == true && booleanGrid[4][j] == true && booleanGrid[5][j] == true && booleanGrid[6][j] == true && booleanGrid[7][j] == true && booleanGrid[8][j] == true && booleanGrid[9][j] == true && booleanGrid[10][j] == true && booleanGrid[11][j] == true && booleanGrid[12][j] == true && booleanGrid[13][j] == true){
    			
    			booleanGrid[1][j] = false; booleanGrid[2][j] = false; booleanGrid[3][j] = false; 
    			booleanGrid[4][j] = false; booleanGrid[5][j] = false; booleanGrid[6][j] = false;
    			booleanGrid[7][j] = false; booleanGrid[8][j] = false; booleanGrid[9][j] = false;
    			booleanGrid[10][j] = false; booleanGrid[11][j] = false; booleanGrid[12][j] = false; booleanGrid[13][j] = false;
    			for (int x = 1; x < 14; x++){
    				for (int y = j; y < 22; y++){
    					if (booleanGrid[x][y+1] == true){
    						booleanGrid[x][y+1] = false;
    						booleanGrid[x][y] = true;
    					}
    				}
    				
    			}
    			return true;
    		}
    	}
    	return false;
    }
    public boolean checkLegalRotate(){ //checks whether a block can rotate, or is blocked by the edges of the grid
    	if (blockNumber == 3){
    		if (blockState == 1 || blockState == 3){
    			if (xcord <= 10){
    				return true;
    			}
    			else return false;
    		}
    	}
    	if (blockNumber == 5){
    		if (blockState == 1){
    			if (xcord <= 11){
    				return true;
    			}
    			else return false;
    		}
    		if (blockState == 3){
    			if (xcord <= 12){
    				return true;
    			}
    			else return false;
    		}
    	}
    	if (blockNumber == 6){
    		if (blockState == 1 || blockState == 3){
    			if (xcord <= 12){
    				return true;
    			}
    			else return false;
    		}
    	}
    	if (blockNumber == 7){
    		if (blockState == 1){
    			if (xcord <= 11){
    				return true;
    			}
    			else return false;
    		}
    		if (blockState == 3){
    			if (xcord <= 12){
    				return true;
    			}
    			else return false;
    		}
    	}
    	if (blockNumber == 8){
    		if (blockState == 1){
    			if (xcord <= 11){
    				return true;
    			}
    			else return false;
    		}
    	}
    	return true;
    }
    public double getLimit(){	// calculates the gravity timer, which gets faster as the difficulty level increases
    	return 0.6 - ((double)getDifficulty()/13);
    }
    public int getDifficulty(){	// calculates a game difficulty level based on current progress
    	return lines/5;		
    }
    public int getScore(){	// calculates score
    	int level = (int) ((float)getDifficulty()/10*200) + 200;
    	return lines*level;
    }
    public boolean checkLose(){	// checks whether the player has lost the game - whether a block has spawned and cannot move before it collides with another block
    	if (blockType == 1){
    		if (booleanGrid[xcord][ycord] == true || booleanGrid[acord][bcord] == true || booleanGrid[ccord][dcord] == true){
    			return true;
    		}
    	}
    	if (blockType == 2){
    		if (booleanGrid[6][22] == true || booleanGrid[7][22] == true ){
    			return true;
    		}
    	}
    	return false;
    }
}
	


