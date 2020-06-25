package vistaMVC;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class ventanaQuitarServicio extends JFrame implements KeyListener{

	private JPanel contentPane;
	private JTextField nombreTextField;
	private JTextField domicilioTextField;
	private JButton quitarBoton;
	private ActionListener actionlistener;
	
	public ventanaQuitarServicio() {
		setTitle("Quitar Servicio");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 401, 321);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 1, 0, 0));
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		
		JLabel nombreLabel = new JLabel("Nombre:");
		panel_4.add(nombreLabel);
		
		JPanel panel_5 = new JPanel();
		panel.add(panel_5);
		
		nombreTextField = new JTextField();
		panel_5.add(nombreTextField);
		nombreTextField.setColumns(15);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_1.add(panel_6);
		
		JLabel domicilioLabel = new JLabel("Domicilio:");
		panel_6.add(domicilioLabel);
		
		JPanel panel_7 = new JPanel();
		panel_1.add(panel_7);
		
		domicilioTextField = new JTextField();
		panel_7.add(domicilioTextField);
		domicilioTextField.setColumns(15);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setVgap(3);
		contentPane.add(panel_2);
		
		quitarBoton = new JButton("Quitar Socio");
		quitarBoton.setEnabled(false);
		panel_2.add(quitarBoton);
		this.quitarBoton.setActionCommand("QUITAR SERVICIO");
		
		this.setMinimumSize(getSize());
		this.setVisible(true);
		
		this.nombreTextField.addKeyListener(this);
		this.domicilioTextField.addKeyListener(this);
		
	}

	public void keyPressed(KeyEvent arg0) {}

	public void keyReleased(KeyEvent arg0) {
		String nombre = this.nombreTextField.getText();
		String domicilio= this.domicilioTextField.getText();
		boolean res= (!nombre.equals("") && !domicilio.equals(""));
		this.quitarBoton.setEnabled(res);
	}

	public void keyTyped(KeyEvent arg0) {}

	public void setActionlistener(ActionListener actionlistener) {
		this.quitarBoton.addActionListener(actionlistener);
		this.actionlistener = actionlistener;
	}
	
	public String getNombre() {
		return  this.nombreTextField.getText();
	}
	
	public String getDomicilio() {
		return  this.domicilioTextField.getText();
	}

}
