package com.mygdx.game;

public interface Movimiento {
	
	public void PorTeclado();

	void Seguir(int getXSpeed, int getySpeed);
	
	void Patron(int xspeed,int yspeed);

	void MovBoss(int xSpeed);
}
