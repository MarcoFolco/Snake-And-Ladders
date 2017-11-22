package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.util.*;

public class Ejemplo extends JFrame {

	private JPanel contentPane;
	JLabel lblMensaje;

	private Map<Integer,JLabel> tablero;
	
	public JLabel getLabel(Integer pos) {
		if(tablero.containsKey(pos))
			return tablero.get(pos);
		return null;
	}
	public void setLabel(String contenido)
	{
		String[] l = contenido.split(" ");
		if(l[0].equals("estadoJugador"))
		{
			tablero.get(Integer.parseInt(l[2])).setText(l[1]);
		}
		else
		{
			tablero.get(Integer.parseInt(l[1])).setText(l[2]);
		}
		/*
		 * line="estadoJugador "+jActual.getId()+" "+jActual.getPos();
			line="estado "+ posicionOrig +" "+tablero.get(posicionOrig).getTipo();
		 */
		
	}
	public void mostrarMensaje(String texto)
	{
		lblMensaje.setText(texto);
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ejemplo frame = new Ejemplo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ejemplo() {
		tablero =  new HashMap<Integer,JLabel>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("New buttonjhgkj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnNewButton.setBounds(252, 104, 146, 23);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("New label");
		tablero.put(1,lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Public\\Pictures\\Sample Pictures\\Desert.jpg"));
		lblNewLabel.setBounds(39, 56, 139, 134);
		panel.add(lblNewLabel);
		
		lblMensaje = new JLabel("...");
		lblMensaje.setBounds(33, 214, 293, 14);
		lblMensaje.setText("");
		panel.add(lblMensaje);
		
	}
}
