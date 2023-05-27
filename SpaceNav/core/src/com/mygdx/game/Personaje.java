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
	public void Seguir(int michx, int michy) {
		float speed = 1.3f;
		
		if (this.x < michx) 
            this.x += speed; 
		else if (this.x > michx) 
            this.x -= speed;
        
        if (this.y < michy) 
            this.y += speed;
        else if (this.y > michy) 
            this.y -= speed;
        
        
        spr.setPosition(this.x, this.y);
	}
	
	@Override
	public void Patron(int xSpeed, int ySpeed) {
	    x += xSpeed; 

	    if (x + spr.getWidth() > Gdx.graphics.getWidth()) {
	        xSpeed *= -1; 
	    }

	    if (x < 0) {
	        xSpeed *= -1; 
	    }
	    
	    spr.setPosition(x, 600); 
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
		if (Gdx.input.isKeyJustPressed(Input.Keys.L)) {         
	          Bullet  bala = new Bullet(spr.getX()+spr.getWidth()/2-5,spr.getY()+ spr.getHeight()-5,0,10,
	        		  new Texture(Gdx.files.internal("Rocket2.png")));
	          juego.agregarBala(bala);
	    }
	}
}
