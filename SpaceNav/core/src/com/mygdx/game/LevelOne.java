package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LevelOne extends GeneradorNiveles {
	private Texture texture = new Texture(Gdx.files.internal("demo01_PixelSky_1920x1080.png"));
	private ArrayList<DemonioSeguimiento> balls1 = new ArrayList<>();
	private ArrayList<DemonioAleatorio> balls2 = new ArrayList<>();

	public LevelOne(SpriteBatch batch, PantallaJuego partida, Michahel michahel,ArrayList<Bullet> balas,SpaceNavigation game, int score) {
		this.batch = batch;
		this.partida = partida;
		this.michahel = michahel;
		this.balas = balas;
		this.game = game;
		this.score = score;
	}

	@Override
	public void generarFondo(SpriteBatch batch, PantallaJuego partida) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.batch.begin();
        this.batch.draw(this.texture, 0, 0, this.texture.getWidth(), this.texture.getHeight());
	}

	@Override
	public void movimientoDemonios() {
		for (DemonioSeguimiento ball : balls1) {
	        ball.update(michahel.getX(),michahel.getY());
	    }
	    for (DemonioAleatorio ball1 : balls2) {
	        ball1.update();
	    }
	}
	
	@Override
	public void dibujar() {
		for (Bullet b : balas) {       
	          b.draw(batch);
	      }
	      michahel.draw(batch, partida);
	}
	
	@Override
	public void colisionBalas(Michahel michahel, ArrayList<Bullet> balas){
		if (!michahel.estaHerido()) { 
	    	  for (int i = 0; i < balas.size(); i++) {
		            Bullet b = balas.get(i);
		            b.update();
		            for (int j = 0; j < balls1.size() ; j++) { 
		              if (b.checkCollision(balls1.get(j))) {          
		            	 balls1.remove(j);
		            	 j--;
		            	 
		            	 partida.setScore(partida.getScore()+10);
		              }   	  
		  	        }
		            for (int j = 0; j < balls2.size(); j++) { 
			              if (b.checkCollision(balls2.get(j))) {          
			            	 balls2.remove(j);
			            	 j--;
			            	 
			            	 partida.setScore(partida.getScore()+10);
			              }   	  
			  	        }
		                
		            b.draw(batch);
		            if (b.isDestroyed()) {
		                balas.remove(b);
		                i--; //para no saltarse 1 tras eliminar del arraylist
		            }
		      }
		      //actualizar movimiento de asteroides dentro del area
	    	  
	    	  movimientoDemonios();
	      }
	}
	@Override
	public void colisionMichahel(Michahel michahel, ArrayList<Bullet> balas) {
		for (int i = 0; i < balls1.size(); i++) {
    	    DemonioSeguimiento b=balls1.get(i);
    	    b.draw(batch);
	          //perdió vida o game over
              if (michahel.checkCollision(b)) {
	            //asteroide se destruye con el choque             
            	 balls1.remove(i);
            	 i--;
              }   	  
        }
      for (int i = 0; i < balls2.size(); i++) {
    	    DemonioAleatorio b=balls2.get(i);
    	    b.draw(batch);
	          //perdió vida o game over
              if (michahel.checkCollision(b)) {
	            //asteroide se destruye con el choque             
            	 balls2.remove(i);
            	 i--;
              }   	  
       }
	}
	
	@Override
	public void generarDemonios() {
		Random r = new Random();
 	    for (int i = 0; i < (8); i++) {
 	        if ((i)%2 == 0) {
 	        	DemonioSeguimiento bb = new DemonioSeguimiento(r.nextInt((int)Gdx.graphics.getWidth()), r.nextInt(Gdx.graphics.getHeight()+1 - (Gdx.graphics.getHeight()/2)) + (Gdx.graphics.getHeight()/2) ,
	 	        		new Texture(Gdx.files.internal("aGreyMedium4.png")),1,0,0,0);
 	        	balls1.add(bb);
	 	  	    
 	        }else {
 	        	DemonioAleatorio bb = new DemonioAleatorio(r.nextInt((int)Gdx.graphics.getWidth()),r.nextInt(Gdx.graphics.getHeight()+1 - (Gdx.graphics.getHeight()/2)) + (Gdx.graphics.getHeight()/2),
	 	        		new Texture(Gdx.files.internal("aGreyMedium4.png")),1, 1+r.nextInt(4+4) + 1, 1+r.nextInt(4+4)-10,
	 	  	            20+r.nextInt(10));
 	        	
 	        	balls2.add(bb);
 	        }      
 	    }
	}

	@Override
	public void finalizar(int rondas) {
		if (balls1.size()==0 && balls2.size()==0) { 
			Screen ss = new PantallaJuego(game,2, michahel.getVidas(), score);
			ss.resize(1200, 800);
			game.setScreen(ss);
			partida.dispose();
      }
	}
}

	
	
