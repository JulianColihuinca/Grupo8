package controladorMVC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;
import ModeloMVC.SistemaContrataciones;
import excepciones.NoPuedeContratarException;
import excepciones.NoPuedeDarDeBajaException;
import excepciones.NoPuedePagarException;
import interfaces.I_Pago;
import mediospagos.PagoCheque;
import mediospagos.PagoEfectivo;
import mediospagos.PagoTarjeta;
import modelo.EmuladorPasoTiempo;
import modelo.GestorDeFacturacion;
import personas.Fisica;
import personas.Juridica;
import servicios.Domicilio;
import servicios.DomicilioCasa;
import servicios.DomicilioDepto;
import vistaMVC.ventanaAgregarServicio;
import vistaMVC.ventanaAltaSocio;
import vistaMVC.ventanaModificarServicio;
import vistaMVC.ventanaPagar;
import vistaMVC.ventanaPrincipal;
import vistaMVC.ventanaQuitarServicio;

public class Controlador implements ActionListener, Observer {
	private ventanaPrincipal vistaPrincipal;
	private ventanaAltaSocio vistaAltaSocio;
	private ventanaAgregarServicio vistaAgregarServicio;
	private ventanaModificarServicio vistaModificarServicio;
	private ventanaPagar vistaPagar;
	private ventanaQuitarServicio vistaQuitarServicio;
	private Observable ept;
	private SistemaContrataciones sistema= SistemaContrataciones.getInstancia();
	private GestorDeFacturacion gestor;
	
	public Controlador() {
		vistaPrincipal= new ventanaPrincipal();
		vistaPrincipal.setActionlistener(this);
		this.ept= sistema.getEmPasoTiempo();
		this.vistaPrincipal.setMesActual(1);
		this.ept.addObserver(this);
		this.gestor=new GestorDeFacturacion();
		this.gestor.setNombre("Controlador");
	}

	public void update(Observable arg0, Object arg1) {
		if (arg0==this.ept) {
			EmuladorPasoTiempo obj= (EmuladorPasoTiempo) arg0;
			this.vistaPrincipal.setMesActual(obj.getMesActual());
		}
	}

	public void actionPerformed(ActionEvent arg0) {
		String comando= arg0.getActionCommand();
		if (comando.equalsIgnoreCase("AGREGAR SERVICIO")) {
			this.vistaAgregarServicio.setVisible(false);
			String internet="";
			if (this.vistaAgregarServicio.cienIsSelected())
				  	internet= "Internet100";
			else if (this.vistaAgregarServicio.quinientosIsSelected())
					internet="Internet500";
			Domicilio domicilio=null;
			if (this.vistaAgregarServicio.casaIsSelected())
				domicilio= new DomicilioCasa(this.vistaAgregarServicio.getCalle(),this.vistaAgregarServicio.getAltura());
			else if (this.vistaAgregarServicio.departamentoIsSelected())
				domicilio=new DomicilioDepto(this.vistaAgregarServicio.getCalle(),this.vistaAgregarServicio.getAltura(),this.vistaAgregarServicio.getPiso(),this.vistaAgregarServicio.getDescripcion());
			try {
				sistema.agregarServicio(this.vistaAgregarServicio.getNombre(),internet,this.vistaAgregarServicio.getCantCelular(),this.vistaAgregarServicio.getCantFijo(),this.vistaAgregarServicio.getCantTV(), domicilio);
			} catch (NoPuedeContratarException e) {
				JOptionPane.showMessageDialog(null,e.getMessage(), "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
			}
			this.vistaAgregarServicio.dispose();
			
		}
		else if (comando.equalsIgnoreCase("AGREGAR SOCIO")) {
			this.vistaAltaSocio.setVisible(false);
			if (this.vistaAltaSocio.getFisica())
				 sistema.agregarAbonado(new Fisica(this.vistaAltaSocio.getNombre(),this.vistaAltaSocio.getDNI()));
			else if (this.vistaAltaSocio.getJuridica())
				 sistema.agregarAbonado(new Juridica(this.vistaAltaSocio.getNombre(),this.vistaAltaSocio.getCUIT()));
			this.vistaAltaSocio.dispose();
		}
		else if (comando.equalsIgnoreCase("ABRIR AGREGAR SOCIO")) { // Ventana Principal
			this.vistaAltaSocio =new ventanaAltaSocio();
			this.vistaAltaSocio.setActionlistener(this);
		}
		else if (comando.equalsIgnoreCase("AFIP")) { // Ventana Principal
			
		}
		else if (comando.equalsIgnoreCase("ABRIR AGREGAR SERVICIO")) { // Ventana Principal
			this.vistaAgregarServicio= new ventanaAgregarServicio();
			this.vistaAgregarServicio.setActionListener(this);
		}
		else if (comando.equalsIgnoreCase("ABRIR MODIFICAR SERVICIO")){ // Ventana Principal
			this.vistaModificarServicio=new ventanaModificarServicio();
			this.vistaModificarServicio.setActionlistener(this);
		}
		else if (comando.equalsIgnoreCase("ABRIR QUITAR SERVICIO")) { // Ventana Principal
			this.vistaQuitarServicio= new ventanaQuitarServicio();
			this.vistaQuitarServicio.setActionlistener(this);
		}
		else if (comando.equalsIgnoreCase("EPT")) { // Ventana Principal
			EmuladorPasoTiempo obj= (EmuladorPasoTiempo)this.ept;
			gestor.agregarObservable(obj);
			obj.avanzarMes();
			System.out.println(sistema.listarAbonados());
		}
		else if (comando.contentEquals("ABRIR PAGAR")) { // Ventana Principal
			this.vistaPagar= new ventanaPagar();
			this.vistaPagar.setActionlistener(this);
		}
		else if (comando.contentEquals("MODIFICAR SERVICIO")) {
			this.vistaModificarServicio.setVisible(false);
			sistema.modificarAgregado(this.vistaModificarServicio.getNombre(), this.vistaModificarServicio.getCalle() + " "+this.vistaModificarServicio.getAltura()	, this.vistaModificarServicio.getAccion(), this.vistaModificarServicio.getPack());
			this.vistaModificarServicio.dispose();
		}
		else if (comando.equalsIgnoreCase("QUITAR SERVICIO")) {
			this.vistaQuitarServicio.setVisible(false);
			try {
				sistema.eliminarContratacion(this.vistaQuitarServicio.getNombre(),this.vistaQuitarServicio.getDomicilio());
			} catch (NoPuedeDarDeBajaException e) {
				JOptionPane.showMessageDialog(null,e.getMessage(), "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
			}
			this.vistaQuitarServicio.dispose();
		}
		else if (comando.contentEquals("PAGAR")) {
			this.vistaPagar.setVisible(false);
			I_Pago tipo=null;
			if(this.vistaPagar.efectivoPago())
				tipo=new PagoEfectivo();
			else if(this.vistaPagar.chequePago())
				tipo=new PagoCheque();
			else if(this.vistaPagar.tarjetaPago())
				tipo=new PagoTarjeta();
			try {
				sistema.pagarFactura(this.vistaPagar.getNombre(),tipo,this.vistaPagar.getMes());
			} catch (NoPuedePagarException e) {
				JOptionPane.showMessageDialog(null,e.getMessage(), "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
			}
			this.vistaPagar.dispose();
		}
	}

}
