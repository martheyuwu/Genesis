package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DemonioDisparo extends Demonio {
	private int xSpeed;

	public DemonioDisparo(int x, int y, Texture tx, int vidas, int xSpeed, int ySpeed, int size) {
		super(x, y, tx, vidas, xSpeed, ySpeed, size);
		this.xSpeed = xSpeed;
	}

	@Override
	public void update() {
		 patron(x,y,this.getxSpeed(), this);
	  }

	public int getxSpeed() {
		return xSpeed;
	}

	public void setxSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}
	
	
	public void draw(SpriteBatch batch, PantallaJuego juego) {
		Normal(juego);
	}
	
	
}
