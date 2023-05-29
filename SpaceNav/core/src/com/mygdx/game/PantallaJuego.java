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
	private Michahel michahel;
	private  ArrayList<DemonioSeguimiento> balls1 = new ArrayList<>();
	private  ArrayList<DemonioAleatorio> balls2 = new ArrayList<>();
	private  ArrayList<DemonioDisparo> balls3 = new ArrayList<>();
	private  ArrayList<Bullet> balas = new ArrayList<>();
	private  ArrayList<Bullet> balas2 = new ArrayList<>();
	Niveles level = new Niveles();
	
	


	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public PantallaJuego(SpaceNavigation game, int ronda, int vidas, int score) {
		this.game = game;
		this.ronda = ronda;
		this.score = score;
		setBatch(game.getBatch());
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
	     if (ronda == 1) {
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
	  	}else if (ronda == 2) {
	  		for (int i =0; i < 12; i++) {
	  			DemonioDisparo bb = new DemonioDisparo(((Gdx.graphics.getWidth()/12)*(i+1) - 50), Gdx.graphics.getHeight() - 100, new Texture(Gdx.files.internal("aGreyMedium4.png")),
	  					1, 5 , 1, 1,0);
	  			balls3.add(bb);
	  		}
	  		
	  	}
	     
	}
    
	public void dibujaEncabezado() {
		CharSequence str = "Vidas: "+michahel.getVidas()+" Ronda: "+ronda;
		game.getFont().getData().setScale(2f);		
		game.getFont().draw(getBatch(), str, 10, 30);
		game.getFont().draw(getBatch(), "Score:"+this.score, Gdx.graphics.getWidth()-150, 30);
		game.getFont().draw(getBatch(), "HighScore:"+game.getHighScore(), Gdx.graphics.getWidth()/2-100, 30);
	}
	@Override
	public void render(float delta) {
		
		
		
		if (ronda == 1) {
			level.levelOne(michahel, balas, balls1, balls2, getScore(), getBatch(),game, this);
		}
		if (ronda == 2) {
			level.levelTwo(michahel, balas, balas2, balls3, getScore(), getBatch(),game, this);
		}
		if (ronda == 3) {
			level.levelTwo(michahel, balas, balas2, balls3, getScore(), getBatch(),game, this);
		}
		  
		  
	    	 
	}
    
    public boolean agregarBala(Bullet bb) {
    	return balas.add(bb);
    }
    
    public boolean agregarBalaE(Bullet bb) {
    	return balas2.add(bb);
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

	public SpriteBatch getBatch() {
		return batch;
	}

	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}
   
}
