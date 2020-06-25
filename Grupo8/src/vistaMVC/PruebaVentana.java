package vistaMVC;

import controladorMVC.Controlador;

public class PruebaVentana {
	
	
	public static String texto() {
		String text="";
		String esp="                 \n";
		for (int i='A' ;i<='Z';i++) {
			char val= (char) i;
			text= text+ val+ esp  ;
		}
		return text;
	}

	public static void main(String[] args) {
		//ventanaAFIP vista= new ventanaAFIP();
		Controlador controlador= new Controlador();
	}

}
