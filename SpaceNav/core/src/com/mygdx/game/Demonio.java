package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Demonio extends Enemigos{
	
    
	public Demonio(int x, int y, Texture tx, int vidas, int xSpeed, int ySpeed,int size) {
		super(x, y, tx, vidas, xSpeed, ySpeed);
		spr = new Sprite(tx);
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		
        //validate sphere border into x area
    	if (x-size < 0) x = x+size;
    	if (x+size > Gdx.graphics.getWidth()) x = x-size;
    	this.x = x;
         
        //validate sphere border into y area
    	if (y-size < 0) y = y+size;
    	if (y+size > Gdx.graphics.getHeight()) y = y-size;
    	this.y=y;
    	
    	spr.setPosition(x, y);
    	
	}


	public int getVidas() {
		return this.vidas;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
	

	 public Rectangle getArea() {
	    	return spr.getBoundingRectangle();
	    }
	 
	 public void update(int michahelx,int michahely, int ronda) {
		 //System.out.print(this.xSpeed);
		// System.out.print(this.ySpeed);
		 if (ronda ==1 )
			 Seguir(michahelx,michahely);
		 if (ronda == 2)
			 Patron(xSpeed, ySpeed);
	  }

	 public void draw(SpriteBatch batch, PantallaJuego juego) {

		 		spr.draw(batch);
	 }


	
}
