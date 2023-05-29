package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Personaje implements Movimiento, Disparar{
	protected int x;
	protected int y;
	protected int vidas;
	protected Sprite spr;
	protected Texture tx      ;
    protected boolean herido = false;
	protected boolean muerto = false;

    public Personaje(int x, int y, Texture tx, int vidas) {
    	this.vidas = vidas;
    	spr = new Sprite(tx);
    	this.x = x;
    	this.y=y;
    	spr.setPosition(x, y);
    }
    
    @Override
	public void PorTeclado() {
		if (Gdx.input.isKeyPressed(Input.Keys.A) && x>0)
    		x-=4;
        if (Gdx.input.isKeyPressed(Input.Keys.D) && x+spr.getWidth() < Gdx.graphics.getWidth()) 
        	x+=4;
    	if (Gdx.input.isKeyPressed(Input.Keys.S)  && y>0 ) 
    		y-=4;     
        if (Gdx.input.isKeyPressed(Input.Keys.W) && y+spr.getHeight() < Gdx.graphics.getHeight()) 
        	y+=4;
        spr.setPosition(x, y);   
	}
	@Override
	public void MovBoss(int xSpeed) {
		x += xSpeed;
		if (x+spr.getWidth() == Gdx.graphics.getWidth()) {xSpeed *= -1;}
		if (x == 0) {xSpeed *= -1;}
		
		if (vidas == 12) {xSpeed *= 1.5;}
		
		if (vidas == 9) {xSpeed *= 1.5;}
		
		if (vidas == 3) {xSpeed *= 1.5;}
		spr.setPosition(x, 600);
		
	}
	@Override
	public void Aleatorio(int getXSpeed, int getySpeed) {
		x += getXSpeed;
        y += getySpeed;


		if (x < 0) {
			x = Gdx.graphics.getWidth();
		}
		if (x > Gdx.graphics.getWidth()) {
			x = 0;
		}

		if (y < 0) {
			y = Gdx.graphics.getHeight();
		}
		if (y > Gdx.graphics.getHeight()) {
			y = 0;
		} 
		
		spr.setPosition(x, y);
		
	}
	@Override
	public void PorTecladoD(PantallaJuego juego) {
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {         
	          Bullet  bala = new Bullet(spr.getX()+spr.getWidth()/2-5,spr.getY()+ spr.getHeight()-5,0,10,
	        		  new Texture(Gdx.files.internal("Rocket2.png")));
	          juego.agregarBala(bala);
	        }
	}
	@Override
	public void Doble() {
		
	}
	@Override
	public void Normal(PantallaJuego juego) {
			Bullet bala = new Bullet( spr.getX() + spr.getOriginX(),spr.getY() + spr.getOriginY(),0,-10,new Texture(Gdx.files.internal("Rocket2.png")));
			juego.agregarBalaE(bala);
		
	}
	
	@Override
	public void seguir(int michx, int michy) {
		float speed = 2f;
		
		if (x < michx) 
            x += speed; 
		else if (this.x > michx) 
            x -= speed;
        
        if (this.y < michy) 
            y += speed;
        else if (this.y > michy) 
            y -= speed;
        
        
        spr.setPosition(this.x, this.y);
		
	}
	@Override
	public void patron(int x,int y, int xSpeed, DemonioDisparo disparo) {
		
		// Changes in x axis
		this.x += xSpeed*2;
		
		if (this.x + spr.getWidth() > Gdx.graphics.getWidth()) {
			this.x -= xSpeed;
			disparo.setxSpeed(-xSpeed);
			this.y -=50;
		}
		if (this.y < 0) {
			this.y = Gdx.graphics.getHeight() - 100;
		}
		// Changes in y axis
		if (this.x < 0) {
			this.x -= xSpeed;
			disparo.setxSpeed(-xSpeed);
			this.y -= 50;
		}
		
		spr.setPosition(this.x, this.y);
	}
}
