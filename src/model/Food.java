package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import processing.core.PApplet;
import processing.core.PImage;

public class Food {

	private String item;
	private PApplet app;
	private PImage image;
	
	private int numOrder;
	private String time;
	
	Calendar c = Calendar.getInstance();
	Date date = c.getTime();

	public Food(String item, PImage image, PApplet app, int numOrder, Date date) {
		this.item = item;
		this.image = image;
		this.app = app;
		
		this.numOrder = numOrder;
		this.date = date;
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		time = sdf.format(date);
		

	}

	public void draw(int x, int y) {
		app.fill(255);
		app.textSize(18);
		app.text("Pedido #" +numOrder, x, y+240);
		app.text("Hora: " +time, x, y+260);
		
		app.image(image, x, y);

	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getNumOrder() {
		return numOrder;
	}

	public void setNumOrder(int numOrder) {
		this.numOrder = numOrder;
	}
	
	

}
