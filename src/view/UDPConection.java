package view;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.google.gson.Gson;

import model.Generic;
import model.Order;

public class UDPConection extends Thread {

	private DatagramSocket socket;
	private Order order;

	@Override
	public void run() {
		try {
			socket = new DatagramSocket(2021);

			while (true) {

				// Recibir mensaje
				byte[] buffer = new byte[100];
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

				// Esperando datagram
				System.out.println("Esperando datagram");
				socket.receive(packet);

				// Contructor de String para pasar de de bytes a string
				String msg = new String(packet.getData()).trim();

				// Deserialización
				Gson gson = new Gson();
				Generic generic = gson.fromJson(msg, Generic.class);
				System.out.println("Item recibido: " + generic.item);

				switch (generic.item) {
				case "JUICE":
					order = gson.fromJson(msg, Order.class);
					System.out.println("El cliente quiere: " + order.getItem());
					break;
				case "SANDWICH":
					order = gson.fromJson(msg, Order.class);
					System.out.println("El cliente quiere: " + order.getItem());
					break;
				case "YOGURT":
					order = gson.fromJson(msg, Order.class);
					System.out.println("El cliente quiere: " + order.getItem());
					break;
				case "HOTDOG":
					order = gson.fromJson(msg, Order.class);
					System.out.println("El cliente quiere: " + order.getItem());
					break;

				default:
					break;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(String msg) {
		new Thread(() -> {

			try {
				InetAddress ip = InetAddress.getByName("192.168.0.32");

				// Para enviar tiene 4 parametros
				// 1.buffer, 2. tamaño de arreglo, 3. ip, 4.Puerto del otro peer
				DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.getBytes().length, ip, 3021);

				socket.send(packet);

			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();

	}

}
