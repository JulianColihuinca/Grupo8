package modelo;

import java.util.*;

import ModeloMVC.SistemaContrataciones;
import mediospagos.PagoCheque;
import mediospagos.PagoEfectivo;
import mediospagos.PagoTarjeta;
import personas.Fisica;
import personas.Juridica;
import personas.Persona;
import servicios.Domicilio;
import servicios.DomicilioCasa;
import servicios.DomicilioDepto;

public class Prueba {

	public static void main(String[] args) { 
		SistemaContrataciones sistema=SistemaContrataciones.getInstancia();
		@SuppressWarnings("resource")
		Scanner scanner= new Scanner(System.in);
		String cadena,domicilio,cadenaopcion;
		do {
			//MENU
			System.out.println("1.Crear una nueva factura.");
			System.out.println("2.Contratar plan de Internet.");
			System.out.println("3.Contratar Agregado.");
			System.out.println("4.Quitar Agregado.");
			System.out.println("5.Cambiar plan de Internet.");
			System.out.println("6.Listar una Factura.");
			System.out.println("7.Listar todas las facturas.");
			System.out.println("8.Dar de baja un servicio de Internet.");
			System.out.println("9.Duplicar Factura.");
			System.out.println("10.Abonar.");
			System.out.print("Opcion: ");
			cadena= scanner.nextLine();
			switch(Integer.parseInt(cadena)) {
			case 1:
				System.out.print("\n\nIngrese tipo de persona: (fisica/juridica) ");
				cadena=scanner.nextLine();
				cadena=cadena.toUpperCase();
				Persona persona=null;
				if (cadena.equalsIgnoreCase("JURIDICA")) {
					System.out.print("\n\nIngrese Nombre: ");
					cadena=scanner.nextLine();
					System.out.print("\n\nIngrese CUIT: ");
					String cuit= scanner.nextLine();
				    persona= new Juridica(cadena.toUpperCase(),Integer.parseInt(cuit));
				}	
				else  if (cadena.equalsIgnoreCase("FISICA")) {
					System.out.print("\n\nIngrese Nombre: ");
					cadena=scanner.nextLine();
					System.out.print("\n\nIngrese DNI: ");
					String dni= scanner.nextLine();
				    persona= new Fisica(cadena.toUpperCase(),Integer.parseInt(dni));
				}
				if (persona!=null) 
					sistema.agregarFacturas(persona);
			break;	
			case 2: 
					String nombrePersona;
					int cantTVs=0,cantCelulares=0,cantTelefonos=0;
					String plan=null;
					System.out.print("\nIngrese nombre de la persona: ");
					nombrePersona= scanner.nextLine();
					System.out.println("\n\nSeleccione tipo de departamento: ");
					System.out.println("1.Casa");
					System.out.println("2.Departamento");
					System.out.print("Opcion:");
					cadenaopcion= scanner.nextLine();
					Domicilio domicilioNuevo=null;
					String calle,altura;
					System.out.print("\n\nIngrese Calle: ");
					calle= scanner.nextLine();
					if(!calle.equals("")) {
						System.out.print("\n\nIngrese Altura: ");
						altura= scanner.nextLine();
						if (Integer.parseInt(cadenaopcion)==1) {
							domicilioNuevo= new DomicilioCasa(calle.toUpperCase(),Integer.parseInt(altura));
						}
						else if (Integer.parseInt(cadenaopcion)==2) {
							String piso, descripcion;
							System.out.print("\n\nIngrese Piso: ");
							piso= scanner.nextLine();
							System.out.print("\n\nIngrese Descripcion: ");
							descripcion= scanner.nextLine();
							domicilioNuevo= new DomicilioDepto(calle.toUpperCase(),Integer.parseInt(altura),Integer.parseInt(piso),descripcion.toUpperCase());
						}
						System.out.print("\n\nIngrese plan de internet: ");
						plan= scanner.nextLine().toUpperCase();
						if(plan.equals("INTERNET100") || plan.equals("INTERNET500")) {
							System.out.println("\n\nIngrese cantidad de servicios que desea agregar:");
							System.out.print("TvCable --> Cantidad: ");
							cadena= scanner.nextLine();
							cantTVs= Integer.parseInt(cadena);
							System.out.print("\nCelular --> Cantidad: ");
							cadena= scanner.nextLine();
							cantCelulares= Integer.parseInt(cadena);
							System.out.print("\nTelefono Fijo --> Cantidad: ");
							cadena= scanner.nextLine();
							cantTelefonos= Integer.parseInt(cadena);
						}
					}
					else
						domicilioNuevo=null;
					sistema.agregarServicio(nombrePersona.toUpperCase(), plan, cantCelulares, cantTelefonos, cantTVs, domicilioNuevo);
					
			break;	
			case 3:
				System.out.print("\n\nIngrese Nombre: ");
				cadena=scanner.nextLine();
				System.out.print("\n\nIngrese Domicilio: ");
				domicilio= scanner.nextLine();
				System.out.println("\n\nEscoja un servicio: ");
				System.out.println("1.TVCable");
				System.out.println("2.Celular");
				System.out.println("3.Telefono Fijo");
				System.out.print("Opcion: ");
				cadenaopcion=scanner.nextLine();
				if (Integer.parseInt(cadenaopcion)==1) 
					sistema.modificarAgregado(cadena.toUpperCase(), domicilio.toUpperCase(), "AGREGAR", "TVCABLE");
				else if (Integer.parseInt(cadenaopcion)==2) 
					sistema.modificarAgregado(cadena.toUpperCase(), domicilio.toUpperCase(), "AGREGAR", "CELULAR");
				else if(Integer.parseInt(cadenaopcion)==3) 
					sistema.modificarAgregado(cadena.toUpperCase(), domicilio.toUpperCase(), "AGREGAR", "TELEFONO");
				else {	}
			break;
			case 4:
				System.out.print("\n\nIngrese Nombre: ");
				cadena=scanner.nextLine();
				System.out.print("\n\nIngrese Domicilio: ");
				String domicilio1= scanner.nextLine();
				System.out.println("\n\nEscoja un servicio: ");
				System.out.println("1.TVCable");
				System.out.println("2.Celular");
				System.out.println("3.Telefono Fijo");
				System.out.print("Opcion: ");
				cadenaopcion=scanner.nextLine();
				if (Integer.parseInt(cadenaopcion)==1) 
					sistema.modificarAgregado(cadena.toUpperCase(), domicilio1.toUpperCase(), "QUITAR", "TVCABLE");
				else if (Integer.parseInt(cadenaopcion)==2) 
					sistema.modificarAgregado(cadena.toUpperCase(), domicilio1.toUpperCase(), "QUITAR", "CELULAR");
				else if(Integer.parseInt(cadenaopcion)==3) 
					sistema.modificarAgregado(cadena.toUpperCase(), domicilio1.toUpperCase(), "QUITAR", "TELEFONO");
				else {	}
			break;	
			case 5:
				System.out.print("\n\nIngrese Nombre: ");
				cadena=scanner.nextLine();
				System.out.print("\n\nIngrese Domicilio: ");
				domicilio= scanner.nextLine();
				System.out.println("\n\nEscoja un servicio: ");
				System.out.println("1.Internet100");
				System.out.println("2.Internet500");
				System.out.print("Opcion: ");
				cadenaopcion=scanner.nextLine();
				if (Integer.parseInt(cadenaopcion)==1) 
					sistema.modificarAgregado(cadena.toUpperCase(), domicilio.toUpperCase(), "CAMBIAR", "INTERNET100");
				else if (Integer.parseInt(cadenaopcion)==2) 
					sistema.modificarAgregado(cadena.toUpperCase(), domicilio.toUpperCase(), "CAMBIAR", "INTERNET500");
				else {
					
				}
			break;
			case 6:
				System.out.print("\n\nIngrese Nombre: ");
				cadena=scanner.nextLine();
				System.out.println(sistema.listarFactura(cadena.toUpperCase()));
			break;
			case 7:
				System.out.println("Listado de facturas:");
				System.out.println(sistema.listarFacturas());
			break;	
			case 8: 
				System.out.print("\n\nIngrese Nombre: ");
				cadena=scanner.nextLine();
				System.out.print("\n\nIngrese Domicilio: ");
				domicilio=scanner.nextLine();
				sistema.eliminarContratacion(cadena.toUpperCase(),domicilio.toUpperCase());
			break;	
			case 9:
				System.out.print("\n\nIngrese Nombre: ");
				cadena=scanner.nextLine();
				sistema.duplicarFactura(cadena.toUpperCase());
			break;	
			case 10:
				System.out.print("\n\nIngrese Nombre: ");
				cadena=scanner.nextLine();
				System.out.println("\n\nSeleciona forma de pago:");
				System.out.println("1.Efectivo");
				System.out.println("2.Cheque");
				System.out.println("3.Tarjeta");
				System.out.print("Opcion: ");
				cadenaopcion= scanner.nextLine();
				if (Integer.parseInt(cadenaopcion)==1) 
					sistema.abonar(cadena.toUpperCase(), new PagoEfectivo());
				else if (Integer.parseInt(cadenaopcion)==2)
					sistema.abonar(cadena.toUpperCase(), new PagoCheque());
				else if(Integer.parseInt(cadenaopcion)==3) 	
					sistema.abonar(cadena.toUpperCase(), new PagoTarjeta());
				else {}
			break;
		}
			
		System.out.print("\n\n\nDesea realizar otra accion? (S/N)");
		cadena=scanner.nextLine();
		cadena= cadena.toUpperCase();	
		}while (cadena.equalsIgnoreCase("S"));
		
	}

}
