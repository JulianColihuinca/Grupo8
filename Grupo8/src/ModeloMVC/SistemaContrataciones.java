package ModeloMVC;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Observable;

import agregado.ContratableFactory;
import estados.ActualizadorDeEstado;
import excepciones.DomicilioInvalidoException;
import excepciones.NoPuedeContratarException;
import excepciones.NoPuedeDarDeBajaException;
import excepciones.NoPuedePagarException;
import excepciones.PersonaExistenteException;
import excepciones.ServicioInternetInvalidoException;
import interfaces.I_Sistema;
import modelo.EmuladorPasoTiempo;
import modelo.GestorDeFacturacion;
import interfaces.I_Pago;
import personas.Persona;
import servicios.Domicilio;

public class SistemaContrataciones implements I_Sistema {
	
	private static SistemaContrataciones  instancia= null; //APLICO EL PATRON SINGLETON PUES SE INSTANCIA POR UNICA VEZ
	private HashMap <String,Persona> listaAbonados=new HashMap<String,Persona>();
	private EmuladorPasoTiempo emPasoTiempo;
	private GestorDeFacturacion gestFact;
	private ActualizadorDeEstado actualizador;

    /**
     * Constructor privado de SistemaContrataciones  pues aplicamos el patron Singleton
     */
	private SistemaContrataciones () {
		this.emPasoTiempo= new EmuladorPasoTiempo();
		this.gestFact= new GestorDeFacturacion();
		this.gestFact.agregarObservable(this.emPasoTiempo);
		this.actualizador= new ActualizadorDeEstado();
		this.actualizador.agregarObservable(this.emPasoTiempo);		
	}
	
	/**
	 * Metodo estatico para instanciar por unica vez SistemaContrataciones 
	 * @return devuelve la instancia de tipo SistemaContrataciones 
	 */
	public static SistemaContrataciones  getInstancia() { 
		if(instancia==null)
			instancia= new SistemaContrataciones();
		return instancia;
	}

	public void agregarAbonado(Persona persona) {
		if(!this.listaAbonados.containsKey(persona.getNombre()))
			this.listaAbonados.put(persona.getNombre(), persona);
		else
		{
			try {
				throw new PersonaExistenteException("Persona ya existente");
			} catch (PersonaExistenteException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	/**
	 * Agrega un servicio nuevo a la lista de contrataciones de factura<br>
	 * <b>Pre: </b>La persona debe ser distinto de cadena vacia y debe existir;Las cantidades como cantCel,cantTel,cantTv deben ser mayor o igual que cero; El domicilio no debe ser repetido<br>
	 * <b>Post: </b>Se agrega un servicio mas a la lista de contrataciones<br>
	 * @param persona: Parametro de tipo String que representa al titular de la factura
	 * @param internet: Parametro de tipo String que representa al servicio de internet que desea contratar
	 * @param cantCel: Parametro de tipo int que representa a la cantidad de celulares que desea agregar
	 * @param cantTel: Parametro de tipo int que representa a la cantidad de telefonos que desea agregar
	 * @param cantTV: Parametro de tipo int que representa a la cantidad de cables TV que desea agregar
	 * @param domicilio: Parametro de tipo Domicilio que representa al domicilio del serivicio asociado
	 * @throws NoPuedeContratarException 
	 * @throws ServicioInternetInvalidoException: Se lanza en el caso de que el servicio sea invalido
	 * @throws DomicilioInvalidoException: Se lanza en el caso de que el domicilio sea invalido
	 */
	public void agregarServicio(String persona,String internet, int cantCel, int cantTel, int cantTV, Domicilio domicilio) throws NoPuedeContratarException { 
		try {
			this.listaAbonados.get(persona).agregarContratacion(ContratableFactory.nuevoServicio(internet, cantCel, cantTel, cantTV, domicilio));
		} catch (ServicioInternetInvalidoException e) { //VACIO O NO EXISTE
			System.out.println(e.getMessage());
		} catch (DomicilioInvalidoException e) { //VACIO
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Realiza el cambio de agregado del servicio
	 * <b>Pre: </b> El parametro Direccion debe ser valido y que exista en la factura;El parametro Accion no debe ser una cadena vacia y debe existir; El parametro Servicio no debe ser una cadena vacia y debe existir<br>
	 * <b>Post: </b> Se realiza el cambio de agregado del servicio<br>
	 * @param direccion: Parametro de tipo String que representa a la direccion del servicio que desea cambiar
	 * @param accion: Parametro de tipo String que representa a la accion que quiera realizar
	 * @param servicio: Parametro de tipo String que representa al servicio que desea modificar
	 */
	public void modificarAgregado(String persona,String direccion,String accion,String servicio) { //PRECONDICON DIRECCION VALIDA Y QUE EXISTA EN LA FACTURA 
		int pos=this.listaAbonados.get(persona).buscaContratacion(direccion);
		this.listaAbonados.get(persona).modificaContratacion(pos, accion, servicio);
	}
			
	/**
	 * Metodo para abonar el total segun el medio de pago
	 * <b>Pre: </b> El parametro nombrePersona debe ser distinto de null o cadena vacia;El parametro tipo debe ser distinto de null y debe existir<br>
	 * <b>Post: </b> Devuelve el valor del total calculado con el porcentaje aplicado<br>
	 * @param nombrePersona: Parametro de tipo String que representa al abonado de la factura
	 * @param tipo: Parametro de tipo I_Pago que representa al medio de pago
	 */
	public void abonar(String nombrePersona,I_Pago tipo,int mes) {
		Persona persona=this.listaAbonados.get(nombrePersona);
		this.listaAbonados.get(nombrePersona).getColeccionDeFacturas().buscarFactura(mes);
	}
	
	/**
	 * Se elimina una contratacion de una lista de contrataciones de una factura que pertenece a un abonado.<br>
	 * <b>Pre: </b> Los parametros nombrePersona y domicilio debe ser distinto de null<br>
	 * <b>Post: </b> Se elimino una contratacion ; si la lista de contrataciones queda vacia, se elimina la factura  <br>
	 * @param nombrePersona : Parametro de tipo String que representa el nombre de una persona.
	 * @param nombrePersona : Parametro de tipo String que representa el domicilio de una persona.
	 * @throws NoPuedeDarDeBajaException 
	 */
	public void eliminarContratacion(String nombrePersona,String domicilio) throws NoPuedeDarDeBajaException {
		if(this.listaAbonados.containsKey(nombrePersona)) { 
			this.listaAbonados.get(nombrePersona).eliminarContratacion(domicilio);
		}
	}
	public Iterator<Persona> getPersonas(){
		return this.listaAbonados.values().iterator();
	}
	
	/**
	 * Duplica la factura de una persona
	 * <b>Pre: </b> El parametro persona debe ser distinto de cadena vacia<br>
	 * <b>Post: </b> Se muestra la factura duplicada<br>
	 * @param persona: Parametro de tipo String que representa al abonado de la factura a duplicar
	 * @throws CloneNotSupportedException: Se lanza en el caso de que no sea posible clonar
	 */
	public void duplicarFactura(String persona) {
		Persona facturaOriginal=this.listaAbonados.get(persona);
		Persona facturaDuplicada=null;
		try {
			facturaDuplicada=(Persona) facturaOriginal.clone();
			System.out.println("FACTURA DUPLICADA: \n"+this.listarFactura(facturaDuplicada.getNombre()));
			
		} catch (CloneNotSupportedException e) {
			System.out.println("Error al clonar, la persona es juridica");
		}
	}

/**
	 * @param persona: Parametro de tipo String que representa al abonado que desee listar su factura, distinto de cadena vacia
	 * @return Devuelve String que imprime la factura de un abonado
	 */
	public String listarFactura(String persona) {
		return "sifi";
	}
	/**
	 * @return Devuelve String de toda la informacion detallada de las facturas
	 */
	public String listarAbonados() {
		StringBuilder sb= new StringBuilder();
		for(HashMap.Entry<String,Persona> pair: listaAbonados.entrySet()) {
			sb.append("ABONADO: "+ pair.getKey()+"\n");
			sb.append(pair.getValue().listarFacturas());
			sb.append("---------------------------------------------------------------\n");
		}
		return sb.toString();
	}

	public void pagarFactura(String nombrePersona, I_Pago tipo,int mes) throws NoPuedePagarException {
		this.listaAbonados.get(nombrePersona).pagar(tipo, mes);
		
	}

	public EmuladorPasoTiempo getEmPasoTiempo() {
		return emPasoTiempo;
	}

	public void setEmPasoTiempo(EmuladorPasoTiempo emPasoTiempo) {
		this.emPasoTiempo = emPasoTiempo;
	}

	

	

}
