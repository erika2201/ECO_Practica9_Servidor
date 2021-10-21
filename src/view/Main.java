package view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import events.onMessage;
import model.Food;
import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet implements onMessage {

	public static void main(String[] args) {
		PApplet.main(Main.class.getName());

	}

	private PImage bg;
	UDPConection UDP;
	private PImage juice, sandwich, yogurt, hotdog;

	// Para pintar el pedido del cliente
	private ArrayList<Food> food;
	private int x, y, numOrder;

	public void settings() {
		size(1200, 700);
	}

	public void setup() {
		bg = loadImage("res/Background.png");
		UDP = new UDPConection();
		UDP.setObserver(this);
		UDP.start();

		// Imagenes
		juice = loadImage("res/orangeJuice.png");
		sandwich = loadImage("res/sandwich.png");
		yogurt = loadImage("res/yogurt.png");
		hotdog = loadImage("res/hotDog.png");

		food = new ArrayList<Food>();
		x = 85;
		y = 130;
		numOrder = 0;
	}

	public void draw() {
		image(bg, 0, 0);

		// Inicializo el for
		for (int i = 0; i < food.size(); i++) {
			food.get(i).draw(x, y + (225 * i));
		}
	
	}

	// Enviar el mensaje cuando pedido está listo
	public void mousePressed() {
		for (int i = 0; i < food.size(); i++) {

			if (mouseX > x && mouseX < x + 140 && 
				mouseY > x + (130 * i) && mouseY < y + (130 * i) + 212) {
				UDP.sendMessage("Su " + food.get(i).getItem() + " ya está listo");
				
				numOrder=numOrder-1;
				
				food.remove(i);
			}

		}

	}

	// Interface de patron observer
	@Override
	public void onMessageReceived(String item) {
		
		numOrder++;
		int number = numOrder;
		
		Calendar c = Calendar.getInstance();
		Date date = c.getTime();
		
	//	String item, PImage image, PApplet app, int numOrder, String time
		
		switch (item) {
		case "JUICE":
			food.add(new Food(item, juice, this, number, date));
			break;
		case "SANDWICH":
			food.add(new Food(item, juice, this, number, date));
			break;
		case "YOGURT":
			food.add(new Food(item, juice, this, number, date));
			break;
		case "HOTDOG":
			food.add(new Food(item, juice, this, number, date));
			break;
		default:
			break;
		}
	}

}