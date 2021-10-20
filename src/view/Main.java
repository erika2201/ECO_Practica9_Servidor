package view;

import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet {

	public static void main(String[] args) {
		PApplet.main(Main.class.getName());

	}
	
	private PImage bg;
	UDPConection UDP;

	public void settings() {
		size(1200, 700);
	}

	public void setup() {
		bg = loadImage("res/Background.png");
		UDP = new UDPConection();
		UDP.start();
	}

	public void draw() {
		image(bg, 0, 0);
	}
	
}