/**
 * 
 */
package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Niveles {


	public Niveles() {
		
	}

	public void levelOne(Michahel michahel, ArrayList<Bullet> balas, ArrayList<DemonioSeguimiento> balls1,ArrayList<DemonioAleatorio> balls2,  int score,
			SpriteBatch batch, SpaceNavigation game, PantallaJuego partida) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
		partida.dibujaEncabezado();
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
	    	  
		      for (DemonioSeguimiento ball : balls1) {
		          ball.update(michahel.getX(),michahel.getY());
		      }
		      for (DemonioAleatorio ball1 : balls2) {
		          ball1.update();
		      }
	      }
	      //dibujar balas
	     for (Bullet b : balas) {       
	          b.draw(batch);
	      }
	      michahel.draw(batch, partida );
	      //dibujar asteroides y manejar colision con nave
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
	      
	      if (michahel.estaMuerto()) {
			if (score > game.getHighScore())
				game.setHighScore(score);
	    	Screen ss = new PantallaGameOver(game);
			ss.resize(1200, 800);
			game.setScreen(ss);
			partida.dispose();
		  }
	      batch.end();
	      if (balls1.size()==0 && balls2.size()==0) { 
		    	
				Screen ss = new PantallaJuego(game,2, michahel.getVidas(), score);
				ss.resize(1200, 800);
				game.setScreen(ss);
				partida.dispose();
			  }
	}
	
	public void levelTwo(Michahel michahel, ArrayList<Bullet> balas, ArrayList<Bullet> balas2,  ArrayList<DemonioDisparo> balls3, int score,
			SpriteBatch batch, SpaceNavigation game, PantallaJuego partida) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
		partida.dibujaEncabezado();
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
		     }
		      //actualizar movimiento de asteroides dentro del area
	    	  
		      for (DemonioDisparo ball : balls3) {
		          ball.update();
		      }
	      }
		
	      //dibujar balas
	     for (Bullet b : balas) {       
	          b.draw(batch);
	      }
	     
	     
	   //  Random r = new Random();
	     
	     for (Bullet b : balas2) {       
	          b.draw(batch);
	      }
	     
	      michahel.draw(batch, partida);
	      //dibujar asteroides y manejar colision con nave
	      for (int i = 0; i < balls3.size(); i++) {
	    	  DemonioDisparo b = balls3.get(i);
	    	  	b.draw(batch,partida);
	    	  	
		          //perdió vida o game over
	              if (michahel.checkCollision(b)) {
		            //asteroide se destruye con el choque             
	            	 balls3.remove(i);
	            	 i--;
	              }   	  

          } 
	      
	      if (michahel.estaMuerto()) {
			if (score > game.getHighScore())
				game.setHighScore(score);
	    	Screen ss = new PantallaGameOver(game);
			ss.resize(1200, 800);
			game.setScreen(ss);
			partida.dispose();
		  }
	      batch.end();
	      if (balls3.size()==0) { 
		    	
				Screen ss = new PantallaJuego(game,3, michahel.getVidas(), score);
				ss.resize(1200, 800);
				game.setScreen(ss);
				partida.dispose();
			  }
			
		}
	
	public void levelFinal() {
		
			
		}

	
	
}
