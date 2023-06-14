package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class PantallaJuego implements Screen {

	private SpaceNavigation game;
	private OrthographicCamera camera;	
	private SpriteBatch batch;
	private Music gameMusic;
	private int score;
	private int ronda = 1;
	private Michahel michahel;
	private ArrayList<Bullet> balas = new ArrayList<>();
	private ArrayList<Bullet> balasD = new ArrayList<>();
	private GeneradorNiveles generadorNiveles = null;
	
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
	    		 new Texture(Gdx.files.internal("MainShip3.png")),vidas,false, false);
	     
	    // Load levels 
	     if (ronda == 1) {
	    	 generadorNiveles = new LevelOne(getBatch(), this, michahel, balas, game, ronda);
	     } else if ( ronda == 2) {
	    	 generadorNiveles = new LevelTwo(getBatch(), this, michahel, balas, game, ronda, balasD);
	     }
	     generadorNiveles.generarDemonios();
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
		generadorNiveles.Nivel();
	}
    
	public boolean agregarBala(Bullet bb) {
    	return balas.add(bb);
    }
	public boolean agregarBalaD(Bullet bb) {
		return balasD.add(bb);
	}
	public int getRonda() {
		return ronda;
	}

	public void setRonda(int ronda) {
		this.ronda = ronda;
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
