package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public abstract class Enemigos extends Personaje{
	protected int xSpeed;
	protected int ySpeed;

	public Enemigos(int x, int y, Texture tx, int vidas, int xSpeed, int ySpeed) {
		super(x, y, tx, vidas);
	}
}
