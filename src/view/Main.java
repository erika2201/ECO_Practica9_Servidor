package view;

import java.util.ArrayList;

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
	PImage juice, sandwich, yogurt, hotdog;

	// Para pintar el pedido del cliente
	private ArrayList<Food> food;
	private int x, y;

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
				
				food.remove(i);
			}

		}

	}

	// Interface de patron observer
	@Override
	public void onMessageReceived(String item) {
		switch (item) {
		case "JUICE":
			food.add(new Food(item, juice, this));
			break;
		case "SANDWICH":
			food.add(new Food(item, sandwich, this));
			break;
		case "YOGURT":
			food.add(new Food(item, yogurt, this));
			break;
		case "HOTDOG":
			food.add(new Food(item, hotdog, this));
			break;
		default:
			break;
		}
	}

}