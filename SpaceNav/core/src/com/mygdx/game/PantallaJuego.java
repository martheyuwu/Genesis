package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class PantallaJuego implements Screen {

	private SpaceNavigation game;
	private OrthographicCamera camera;	
	private SpriteBatch batch;
	private Music gameMusic;
	private int score;
	private int ronda;
	private int cantDemonios;


	private Michahel michahel;
	private  ArrayList<Demonio> balls1 = new ArrayList<>();
	private  ArrayList<Bullet> balas = new ArrayList<>();


	public PantallaJuego(SpaceNavigation game, int ronda, int vidas, int score) {
		this.game = game;
		this.ronda = ronda;
		this.score = score;
		batch = game.getBatch();
		camera = new OrthographicCamera();	
		camera.setToOrtho(false, 800, 640);
		//inicializar assets; musica de fondo y efectos de sonido
		gameMusic = Gdx.audio.newMusic(Gdx.files.internal("piano-loops.wav")); //
		
		gameMusic.setLooping(true);
		gameMusic.setVolume(0.5f);
		gameMusic.play();
		
	    // Load Michahel's image, 64x64   
	     michahel = new Michahel (Gdx.graphics.getWidth()/2-50, 30, 
	    		 new Texture(Gdx.files.internal("MainShip3.png")),3,false, false);
        // Create Demons
        Random r = new Random();
        if (ronda == 1)
        	cantDemonios = 3;
        if (ronda == 2)
        	cantDemonios = 7;
        if (ronda == 3)
        	cantDemonios = 12; 
	    for (int i = 0; i < cantDemonios; i++) {
	        Demonio bb = new Demonio(r.nextInt((int)Gdx.graphics.getWidth()),r.nextInt((int)Gdx.graphics.getHeight() - (int)Gdx.graphics.getHeight()/2 +1) + 
	        		(int)Gdx.graphics.getHeight()/2,
	        		new Texture(Gdx.files.internal("aGreyMedium4.png")),1, 2, 0,
	  	            20+r.nextInt(10));
	  	    balls1.add(bb);
	  	  //System.out.print(balls1.get(0).xSpeed);
	  	    
	  	}
	}
    
	public void dibujaEncabezado() {
		CharSequence str = "Vidas: "+michahel.getVidas()+" Ronda: "+ronda;
		game.getFont().getData().setScale(2f);		
		game.getFont().draw(batch, str, 10, 30);
		game.getFont().draw(batch, "Score:"+this.score, Gdx.graphics.getWidth()-150, 30);
		game.getFont().draw(batch, "HighScore:"+game.getHighScore(), Gdx.graphics.getWidth()/2-100, 30);
	}
	@Override
	public void render(float delta) {
		  Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
          batch.begin();
		  dibujaEncabezado();
	      if (!michahel.estaHerido()) {
		      // colisiones entre balas y asteroides y su destruccion  
	    	  for (int i = 0; i < balas.size(); i++) {
		            Bullet b = balas.get(i);
		            b.update();
		            for (int j = 0; j < balls1.size(); j++) { 
		              if (b.checkCollision(balls1.get(j))) {          
		            	 balls1.remove(j);
		            	 j--;
		            	 score +=10;
		              }   	  
		  	        }
		                
		         //   b.draw(batch);
		            if (b.isDestroyed()) {
		            	
		                balas.remove(b);
		                i--; //para no saltarse 1 tras eliminar del arraylist
		            }
		      }
		      //actualizar movimiento de asteroides dentro del area
	    	  
		      for (Demonio ball : balls1) {
		          ball.update(michahel.getX(),michahel.getY(),ronda);
		      }
	      }
	      //dibujar balas
	     for (Bullet b : balas) {
	          b.draw(batch);
	      }
	      michahel.draw(batch, this);
	      //dibujar asteroides y manejar colision con nave
	      for (int i = 0; i < balls1.size(); i++) {
	    	    Demonio b=balls1.get(i);
	    	    b.draw(batch,this);
		          //perdió vida o game over
	              if (michahel.checkCollision(b)) {
		            //asteroide se destruye con el choque             
	            	 balls1.remove(i);
	            	 i--;
              }   	  
  	        }
	      
	      if (michahel.estaMuerto()) {
	    	  
  			if (score > game.getHighScore())
  				game.setHighScore(score);
	    	Screen ss = new PantallaGameOver(game);
  			ss.resize(1200, 800);
  			game.setScreen(ss);
  			dispose();
  		  }
	      batch.end();
	      //nivel completado
	      if (balls1.size()==0) { 
	    	
			Screen ss = new PantallaJuego(game,ronda+1, michahel.getVidas(), score);
			ss.resize(1200, 800);
			game.setScreen(ss);
			dispose();
		  }
	    	 
	}
    
    public boolean agregarBala(Bullet bb) {
    	return balas.add(bb);
    }
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		gameMusic.play();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		this.gameMusic.dispose();
	}
	public int getRonda() {
		return ronda;
	}
}
