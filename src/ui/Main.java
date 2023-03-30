package ui;
import java.util.Scanner;
import model.Controller;

public class Main{
	public static Scanner reader=new Scanner(System.in);
	private Controller controller;
	Main(){
		controller=new Controller();
	}
	

	public static void main(String args[]){
		Main view=new Main();
		int x;
		while(true){
			System.out.println("Type 1 to add a project");
			x=reader.nextInt();
			System.out.println(view.controller.addProject());
		}


	}


}