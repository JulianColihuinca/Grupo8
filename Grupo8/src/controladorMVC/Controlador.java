package controladorMVC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;


import vistaMVC.ventanaAgregarServicio;
import vistaMVC.ventanaAltaSocio;
import vistaMVC.ventanaPagar;
import vistaMVC.ventanaPrincipal;

public class Controlador implements ActionListener, Observer {
	private ventanaPrincipal vista;
	
	public Controlador() {
		vista= new ventanaPrincipal();
		vista.setActionlistener(this);
	}

	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	public void actionPerformed(ActionEvent arg0) {
		String comando= arg0.getActionCommand();
		if (comando.equalsIgnoreCase("AGREGAR SERVICIO")) {
			
		}
		else if (comando.equalsIgnoreCase("AGREGAR SOCIO")) {
			
		}
		else if (comando.equalsIgnoreCase("ABRIR AGREGAR SOCIO")) { // Ventana Principal
			
		}
		else if (comando.equalsIgnoreCase("ABRIR BUSCAR SOCIO")) { // Ventana Principal
			
		}
		else if (comando.equalsIgnoreCase("ABRIR QUITAR SOCIO")) { // Ventana Principal
			
		}
		else if (comando.equalsIgnoreCase("ABRIR AGREGAR SERVICIO")) { // Ventana Principal
			
		}
		else if (comando.equalsIgnoreCase("ABRIR MODIFICAR SERVICIO")){ // Ventana Principal
			
		}
		else if (comando.equalsIgnoreCase("ABRIR QUITAR SERVICIO")) { // Ventana Principal
			
		}
		else if (comando.equalsIgnoreCase("EPT")) { // Ventana Principal
			
		}
		else if (comando.contentEquals("ABRIR PAGAR")) { // Ventana Principal
			
		}
		else if (comando.contentEquals("MODIFICAR SERVICIO")) {
			
		}
		else if (comando.contentEquals("PAGAR")) {
			
		}
	}

}
