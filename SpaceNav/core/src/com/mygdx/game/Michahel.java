package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Michahel extends Personaje{
    private int tiempoHerido;
    private int tiempoHeridoMax = 25;
	
	public Michahel(int x, int y, Texture tx, int vidas,boolean herido,boolean muerto) {
		super (x,y,tx,vidas);
	}
	public boolean estaMuerto(){
		return !herido && muerto;
 	}
	public boolean estaHerido(){
	   return herido;
   }
	
	public int getVidas(){
		return vidas;
	}
    public  int getX() {
    	return (int) spr.getX(); 
    }
    public  int getY() {
    	return (int) spr.getY();
    }
    public  void setVidas(int vidas) {
		this.vidas = vidas;
	}
	
	public void draw(SpriteBatch batch, PantallaJuego juego){
        float x =  spr.getX();
        if (!herido) {
        	PorTeclado();
 		    spr.draw(batch);
        } else {
 		   spr.draw(batch); 
 		  spr.setX(x);
 		 tiempoHerido--;
		   if (tiempoHerido<=0) herido = false;
 		 }
        // Shoot
        PorTecladoD(juego);
    }
	
	public boolean checkCollision(Demonio b) {
        if(!herido && b.getArea().overlaps(spr.getBoundingRectangle())){
        	//actualizar vidas y herir
            vidas--;
            herido = true;
  		    tiempoHerido=tiempoHeridoMax;
  		    
            if (vidas<=0) 
          	    muerto = true; 
            return true;
        }
        return false;
    }

	public boolean checkCollision(Bullet b) {
        if(!herido && b.getArea().overlaps(spr.getBoundingRectangle())){
        	//actualizar vidas y herir
            vidas--;
            herido = true;
  		    tiempoHerido=tiempoHeridoMax;
  		    
            if (vidas<=0) 
          	    muerto = true; 
            return true;
        }
        return false;
    }
	
}

	