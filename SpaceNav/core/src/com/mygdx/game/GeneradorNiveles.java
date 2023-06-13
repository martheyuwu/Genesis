package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GeneradorNiveles {
	protected SpriteBatch batch;
	protected PantallaJuego partida;
	protected Michahel michahel;
	protected ArrayList<Bullet> balas;
	protected SpaceNavigation game;
	protected int score;
	

	public GeneradorNiveles() {	
	}
	
	public final void Nivel() {
		generarFondo(batch,partida);
        this.partida.dibujaEncabezado();
		colisionBalas(michahel, balas);
		dibujar();
		colisionMichahel(michahel, balas);
		finalizar(partida.getRonda());
		gameOver(game,score);
	}
	
	public abstract void finalizar(int rondas);
	
	public abstract void generarFondo(SpriteBatch batch, PantallaJuego partida);
	
	public abstract void movimientoDemonios();
	
	public abstract void dibujar();
	
	public abstract void generarDemonios();
	
	public abstract void colisionBalas(Michahel michahel, ArrayList<Bullet> balas);
		
	public abstract void colisionMichahel(Michahel michahel, ArrayList<Bullet> balas);
	public  void gameOver(SpaceNavigation game,int score) {
		if (michahel.estaMuerto()) {
			if (score > game.getHighScore())
				game.setHighScore(score);
	    	Screen ss = new PantallaGameOver(game);
			ss.resize(1200, 800);
			game.setScreen(ss);
			partida.dispose();
		}
		batch.end();
		
	}
}
