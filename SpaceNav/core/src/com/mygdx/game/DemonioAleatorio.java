package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class DemonioAleatorio extends Demonio {

	public DemonioAleatorio(int x, int y, Texture tx, int vidas, int xSpeed, int ySpeed, int size) {
		super(x, y, tx, vidas, xSpeed, ySpeed, size);
	}

	@Override
	public void update() {
		 Aleatorio(xSpeed, ySpeed);
	  }


}
