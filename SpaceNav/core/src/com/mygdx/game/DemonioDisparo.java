package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DemonioDisparo extends Demonio {
	private int xSpeed;
	private int disparar;
	public DemonioDisparo(int x, int y, Texture tx, int vidas, int xSpeed, int ySpeed, int size,int disparar) {
		super(x, y, tx, vidas, xSpeed, ySpeed, size);
		this.xSpeed = xSpeed;
		this.disparar = disparar;
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
		spr.draw(batch);
		if (disparar  == 60) {
			Normal(juego);
			disparar++;
		}            
		
		if (disparar <= 60) {
			disparar++;
		}
		
		if (disparar > 60) {
			disparar = 0;
		}
		
	}
	
	
}
