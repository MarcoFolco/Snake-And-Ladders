package cliente;

import imagenes.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;
import javax.sound.midi.ControllerEventListener;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.Panel;
import java.awt.Rectangle;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class JFJuego extends JFrame {

	String mapa;
	ChatClient cli;
	String[] icos;
	private JPanel contentPane;
	private JLabel[] labels;
	private String[] peones;
	JLabel j1;
	JLabel j2;
	JLabel ja;
	JLabel turno;
	JLabel dado;
	JButton BtnJugar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFJuego frame = new JFJuego();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void generarGrafico()
	{
		int cont=100;
		int contH=0;
		int contV=0;
		int inicio=10;
		int desplazamientoH=inicio-80;
		int desplazamientoV=inicio;
		int tamanioH=85;
		int tamanioV=85;
		int pos=1;
		mapa=cli.getMapa();
		peones=cli.getPeones();
		while (contV<=9) {
			JLabel aux=new JLabel();
			aux.setBorder(new MatteBorder(5,5,5,5,Color.black));
			if(contV%2==0)
			{
				if(contH<=9)
				{
					desplazamientoH+=80;
					aux.setBounds(desplazamientoH,desplazamientoV,tamanioH,tamanioV);
				}else
				{
					desplazamientoV+=80;
					aux.setBounds(desplazamientoH, desplazamientoV, tamanioH, tamanioV);
					contH=0;
					contV++;
				}
			}else
			{
				if(contH<=9)
				{
					desplazamientoH-=80;
					aux.setBounds(desplazamientoH,desplazamientoV,tamanioH,tamanioV);
				}else
				{
					desplazamientoV+=80;
					aux.setBounds(desplazamientoH, desplazamientoV, tamanioH, tamanioV);
					contH=0;
					contV++;
				}
			}
			pos++;
			contH++;
			if(cont>=1)
			{
				aux.setIcon(new ImageIcon(getClass().getResource("/"+mapa+"/"+icos[cont])));
				aux.setText(""+cont);
				contentPane.add(aux);
				labels[cont]=aux;
			}
			cont--;
		}
		j1.setBorder(labels[1].getBorder());
		j1.setIcon(new ImageIcon(getClass().getResource("/"+mapa+"/"+peones[1])));
		j1.setText("1-n");
		j1.setBounds(labels[1].getBounds());
		j2.setBorder(labels[1].getBorder());
		j2.setIcon(new ImageIcon(getClass().getResource("/"+mapa+"/"+peones[2])));
		j2.setText("1-n");
		j2.setBounds(labels[1].getBounds());
		ja.setBorder(labels[1].getBorder());
		ja.setBounds(labels[1].getBounds());
		ja.setText("1-s");
		ja.setIcon(new ImageIcon(getClass().getResource("/"+mapa+"/"+peones[3])));
		contentPane.add(ja);
		contentPane.setComponentZOrder(ja, 3);
		contentPane.updateUI();
		/*for (int k = 1; k < labels.length; k++) {
			contentPane.remove(labels[k]);
			labels[k].setIcon(new ImageIcon("C:/Users/marco/Pictures/dragones/7427.jpg"));
			contentPane.add(labels[k]);
		}*/
	}
	public void finalizar(String line)
	{
		BtnJugar.setEnabled(false);
		JButton BtnSalir = new JButton("Salir");
		BtnSalir.setVisible(true);
		BtnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cli.enviarExit();
			}
		});
		BtnSalir.setBounds(826, 769, 89, 23);
		contentPane.add(BtnSalir);
		contentPane.setComponentZOrder(BtnSalir, 3);
		JLabel aux=new JLabel();
		Rectangle aux2=new Rectangle();
		aux2=labels[100].getBounds();
		aux2.width=80*10;
		aux2.height=80*10;
		aux.setBounds(aux2);
		aux.setBorder(labels[1].getBorder());
		if(line.equals("ganaste"))
		{
			aux.setIcon(new ImageIcon(getClass().getResource("/"+mapa+"/"+"victoria.jpg")));
		}else
		{
			aux.setIcon(new ImageIcon(getClass().getResource("/"+mapa+"/"+"derrota.jpg")));
		}
		contentPane.add(aux);
		contentPane.setComponentZOrder(aux, 1);
		contentPane.updateUI();
	}
	public void setLabelJugadores(Integer numJ,Integer pos)
	{
		String[] lineCont1=j1.getText().split("-");
		String[] lineCont2=j2.getText().split("-");
		String[] lineCont3=ja.getText().split("-");
		if(numJ==1)
		{
			if(pos==Integer.parseInt(lineCont2[0]))
			{
				if(lineCont3[1].equals("s"))
				{
					contentPane.remove(ja);
					ja.setText(lineCont3[0]+"-n");
				}
				if(lineCont1[1].equals("s"))
				{
					contentPane.remove(j1);
					j1.setText(lineCont1[0]+"-n");
				}
				if(lineCont2[1].equals("s"))
				{
					contentPane.remove(j2);
					j2.setText(lineCont2[0]+"-n");
				}
				j1.setText(pos+"-n");
				j1.setBounds(labels[pos].getBounds());
				ja.setBounds(labels[pos].getBounds());
				ja.setText(pos+"-s");
				contentPane.add(ja);
				contentPane.setComponentZOrder(ja, 4);
				contentPane.updateUI();
			}else
			{
				if(pos!=Integer.parseInt(lineCont2[0]))
				{
					if(lineCont1[1].equals("s"))
					{
						contentPane.remove(j1);
						j1.setText(lineCont1[0]+"-n");
					}
					if(lineCont3[1].equals("s"))
					{
						contentPane.remove(ja);
						ja.setText(lineCont3[0]+"-n");
					}
					if(lineCont2[1].equals("n"))
					{
						contentPane.add(j2);
						contentPane.setComponentZOrder(j2, 4);
						j2.setText(lineCont2[0]+"-s");
					}
					j1.setText(pos+"-s");
					j1.setBounds(labels[pos].getBounds());
					contentPane.add(j1);
					contentPane.setComponentZOrder(j1, 4);
					contentPane.updateUI();
				}
			}
		}else
		{
			if(pos==Integer.parseInt(lineCont1[0]))
			{
				if(lineCont3[1].equals("s"))
				{
					contentPane.remove(ja);
					ja.setText(lineCont3[0]+"-n");
				}
				if(lineCont1[1].equals("s"))
				{
					contentPane.remove(j1);
					j1.setText(lineCont1[0]+"-n");
				}
				if(lineCont2[1].equals("s"))
				{
					contentPane.remove(j2);
					j2.setText(lineCont2[0]+"-n");
				}
				j2.setText(pos+"-n");
				j2.setBounds(labels[pos].getBounds());
				ja.setBounds(labels[pos].getBounds());
				ja.setText(pos+"-s");
				contentPane.add(ja);
				contentPane.setComponentZOrder(ja, 4);
				contentPane.updateUI();
			}else
			{
				if(pos!=Integer.parseInt(lineCont1[0]))
				{
					if(lineCont2[1].equals("s"))
					{
						contentPane.remove(j2);
						j2.setText(lineCont1[0]+"-n");
					}
					if(lineCont3[1].equals("s"))
					{
						contentPane.remove(ja);
						ja.setText(lineCont3[0]+"-n");
					}
					if(lineCont1[1].equals("n"))
					{
						contentPane.add(j1);
						contentPane.setComponentZOrder(j1, 4);
						j1.setText(lineCont2[0]+"-s");
					}
					j2.setText(pos+"-s");
					j2.setBounds(labels[pos].getBounds());
					contentPane.add(j2);
					contentPane.setComponentZOrder(j2, 4);
					contentPane.updateUI();
				}
			}
		}
	}
	public void setLabelTurno(Integer t)
	{
		JLabel aux;
		if(t==1)
		{
			contentPane.remove(turno);
			aux=turno;
			aux.setVisible(true);
			contentPane.add(aux);
			turno=aux;
		}else
		{
			contentPane.remove(turno);
			aux=turno;
			aux.setVisible(false);
			contentPane.add(aux);
			turno=aux;
		}
		contentPane.updateUI();
	}
	public void setLabelDado(Integer numero)
	{
		JLabel aux;
		contentPane.remove(dado);
		aux=dado;
		aux.setText("Sacaste "+numero);
		contentPane.add(aux);
		dado=aux;
	}
	public void agregarPeon()
	{
		
	}
	/**
	 * Create the frame.
	 */
	public JFJuego() {
		labels=new JLabel[101];
		cli=new ChatClient(this);
		peones=new String[4];
		icos=cli.getImgs();
		j1=new JLabel();
		j2=new JLabel();
		ja=new JLabel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 934, 862);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);	
		BtnJugar = new JButton("Jugar");
		BtnJugar.setBounds(826, 411, 89, 23);
		contentPane.add(BtnJugar);
		JLabel lblTurno = new JLabel("Tu turno!");
		lblTurno.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTurno.setForeground(Color.RED);
		lblTurno.setBounds(836, 445, 66, 29);
		lblTurno.setVisible(false);
		turno=lblTurno;
		contentPane.add(lblTurno);
		
		JLabel lblValorDado = new JLabel("");
		lblValorDado.setForeground(Color.RED);
		lblValorDado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblValorDado.setBounds(837, 485, 76, 23);
		dado=lblValorDado;
		contentPane.add(lblValorDado);
		
		BtnJugar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(contentPane.getComponentCount()>1)
				{
					cli.enviarServer("jugar");
				}
			}
		});
	}
}
