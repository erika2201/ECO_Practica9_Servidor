package model;

import processing.core.PApplet;
import processing.core.PImage;

public class Food {

	private String item;
	private PApplet app;
	private PImage image;

	public Food(String item, PImage image, PApplet app) {
		this.item = item;
		this.image = image;
		this.app = app;
		

	}

	public void draw(int x, int y) {
		app.image(image, x, y);

	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

}
