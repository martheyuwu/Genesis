package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Lucifer extends Enemigos {

	public Lucifer(int x, int y, Texture tx, int vidas, int xSpeed, int ySpeed, int size) {
		super(x, y, tx, vidas, xSpeed, ySpeed);
		vidas = 12;
		x = Gdx.graphics.getWidth() / 2 ;
		y = 500;
		spr.setPosition(x, y);
	}
	
	public void draw(SpriteBatch batch, PantallaJuego juego){
        if (!herido) {
        	//Move
        	MovBoss(xSpeed);
 		    spr.draw(batch);
        }
        // Shoot
        Doble(); 
    }
}
