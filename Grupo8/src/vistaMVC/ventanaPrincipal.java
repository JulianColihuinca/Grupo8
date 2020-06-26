package vistaMVC;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ventanaPrincipal extends JFrame {
	
	// LABEL CON EL MES ACTUAL
	private JLabel mesActualLabel;

	// BOTONES
	private JButton agregarSocioBoton;
	private JButton afipSocioBoton;
	private JButton agregarServicioBoton; 
	private JButton quitarServicioBoton;
	private JButton pagarBoton;
	private JButton eptBoton;
	private JButton modificaServicioBoton;
	private JButton finalizarJornadaBoton;
	// CONTROLADOR
	private ActionListener actionlistener;
	private JPanel contentPane;
	private JPanel panel_2;
	
	
	public ventanaPrincipal() {
		setTitle("Contrataciones de Servicio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 388, 397);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(9, 0, 0, 0));
		
		JPanel panel_8 = new JPanel();
		contentPane.add(panel_8);
		
		mesActualLabel = new JLabel("");
		panel_8.add(mesActualLabel);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		agregarSocioBoton = new JButton("Agregar Socio");
		panel.add(agregarSocioBoton);
		this.agregarSocioBoton.setActionCommand("ABRIR AGREGAR SOCIO");
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		
		agregarServicioBoton = new JButton("Agregar Servicio");
		panel_3.add(agregarServicioBoton);
		this.agregarServicioBoton.setActionCommand("ABRIR AGREGAR SERVICIO");
		
		JPanel panel_7 = new JPanel();
		contentPane.add(panel_7);
		
		modificaServicioBoton = new JButton("Modificar Servicio");
		panel_7.add(modificaServicioBoton);
		this.modificaServicioBoton.setActionCommand("ABRIR MODIFICAR SERVICIO");
		
		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4);
		
		quitarServicioBoton = new JButton("Quitar Servicio");
		panel_4.add(quitarServicioBoton);
		this.quitarServicioBoton.setActionCommand("ABRIR QUITAR SERVICIO");
		
		JPanel panel_5 = new JPanel();
		contentPane.add(panel_5);
		
		pagarBoton = new JButton("Pagar");
		panel_5.add(pagarBoton);
		this.pagarBoton.setActionCommand("ABRIR PAGAR");
		
		JPanel panel_6 = new JPanel();
		contentPane.add(panel_6);
		
		eptBoton = new JButton("EPT");
		panel_6.add(eptBoton);
		this.eptBoton.setActionCommand("EPT");
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		
		afipSocioBoton = new JButton("Afip");
		panel_1.add(afipSocioBoton);
		this.afipSocioBoton.setActionCommand("AFIP");
		
		panel_2 = new JPanel();
		contentPane.add(panel_2);
		
		finalizarJornadaBoton = new JButton("Finalizar Jornada");
		panel_2.add(finalizarJornadaBoton);
		this.finalizarJornadaBoton.setActionCommand("FINALIZAR JORNADA");
		
		this.setVisible(true);
		this.setMaximumSize(this.getSize());
		
	}


	public void setActionlistener(ActionListener actionlistener) {
		this.agregarServicioBoton.addActionListener(actionlistener);
		this.modificaServicioBoton.addActionListener(actionlistener);
		this.quitarServicioBoton.addActionListener(actionlistener);
		this.agregarSocioBoton.addActionListener(actionlistener);
		this.afipSocioBoton.addActionListener(actionlistener);
		this.eptBoton.addActionListener(actionlistener);
		this.pagarBoton.addActionListener(actionlistener);
		this.finalizarJornadaBoton.addActionListener(actionlistener);
		this.actionlistener = actionlistener;
	}
	
	public void setMesActual(int mes) {
		this.mesActualLabel.setText("Mes Actual: " + mes);
	}
	
	
}
