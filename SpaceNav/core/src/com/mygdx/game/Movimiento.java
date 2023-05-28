package com.mygdx.game;

public interface Movimiento {
	
	public void PorTeclado();

	public void Aleatorio(int getXSpeed, int getySpeed);

	public void MovBoss(int xSpeed);

	public void seguir(int michx, int michy);

	void patron(int x, int y, int xSpeed, DemonioDisparo disparo);

}
