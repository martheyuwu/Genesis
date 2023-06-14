package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LevelTwo extends GeneradorNiveles {
	private Texture texture = new Texture(Gdx.files.internal("7.png"));
	private ArrayList<DemonioDisparo> balls3 = new ArrayList<>();
	
	
	public LevelTwo(SpriteBatch batch, PantallaJuego partida, Michahel michahel,ArrayList<Bullet> balas,SpaceNavigation game, int score, ArrayList<Bullet> balas2) {
		this.batch = batch;
		this.partida = partida;
		this.michahel = michahel;
		this.balas = balas;
		this.game = game;
		this.score = score;
		this.balas2 = balas2;
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
		for (DemonioDisparo ball : balls3) {
	          ball.update();
	      }
		
	}

	@Override
	public void dibujar() {
	     michahel.draw(batch, partida);
	     for (int i = 0; i < balls3.size(); i++) {
	    	  DemonioDisparo b = balls3.get(i);
		      b.draw(batch, partida);
       }
		
	}

	@Override
	public void generarDemonios() {
		for (int i =0; i < 12; i++) {
  			DemonioDisparo bb = new DemonioDisparo(((Gdx.graphics.getWidth()/12)*(i+1) - 50), Gdx.graphics.getHeight() - 100, new Texture(Gdx.files.internal("aGreyMedium4.png")),
  					1, 5 , 1, 1,0);
  			balls3.add(bb);
  		}
		
	}

	@Override
	public void colisionBalas(Michahel michahel, ArrayList<Bullet> balas) {
		if (!michahel.estaHerido()){
	    	  for (int i = 0; i < balas.size(); i++) {
		            Bullet b = balas.get(i);
		            b.update();
		            for (int j = 0; j < balls3.size() ; j++) { 
		              if (b.checkCollision(balls3.get(j))) {          
		            	 balls3.remove(j);
		            	 j--;
		            	 partida.setScore(partida.getScore()+10);
		              }   	  
		  	        }
		            for (int j = 0; j < balls3.size(); j++) { 
			              if (b.checkCollision(balls3.get(j))) {          
			            	 balls3.remove(j);
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

	    	  for (int i = 0; i < balas2.size(); i++) {
		            Bullet b = balas2.get(i);
		            b.update(); 
			         if (michahel.checkCollision(b)) {          
			            	balas2.remove(i);
			            	i--;
			         }  
			         b.draw(batch);
		     }
		      //actualizar movimiento de asteroides dentro del area
	    	  
	    	  movimientoDemonios();
	      }
		
	}

	@Override
	public void colisionMichahel(Michahel michahel) {
		for (int i = 0; i < balls3.size(); i++) {
	    	  DemonioDisparo b = balls3.get(i);
		          //perdió vida o game over
	              if (michahel.checkCollision(b)) {
		            //asteroide se destruye con el choque             
	            	 balls3.remove(i);
	            	 i--;
	              }   	  
        }
		
	}


	@Override
	public void finalizar(int rondas) {
		if (balls3.size()==0) { 
	    	
			Screen ss = new PantallaJuego(game,3, michahel.getVidas(), score);
			ss.resize(1200, 800);
			game.setScreen(ss);
			partida.dispose();
		  }
		
	}

	
}
