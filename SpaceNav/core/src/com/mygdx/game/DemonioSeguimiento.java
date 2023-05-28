package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class DemonioSeguimiento extends Demonio {

	public DemonioSeguimiento(int x, int y, Texture tx, int vidas, int xSpeed, int ySpeed,int size) {
		super(x, y, tx, vidas, xSpeed, ySpeed, size);
	}
	
	public void update(int michx, int michy) {
		 seguir(michx,michy);
		 
	  }

}
