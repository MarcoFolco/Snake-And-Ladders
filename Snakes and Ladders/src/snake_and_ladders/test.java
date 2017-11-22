package snake_and_ladders;

import java.util.*;

public class test {

	
	public static void main(String[] args) {
		/*Map<Integer,Objeto> tablero = new HashMap<Integer, Objeto>();
		tablero.put(1,new Nulo(1));
		tablero.put(2, new SerpEsc(2,5,false));
		tablero.put(3, new SerpEsc(3,2,true));
		
		for (int i = 1; i < 5; i++) {
			System.out.println(tablero.get(i).getTipo());
			if(tablero.get(i).getTipo().equals(TipoObjeto.ESCALERA.getTipo()))
				System.out.println("voy a "+tablero.get(i).getDest());
			if(tablero.get(i).getTipo().equals(TipoObjeto.SERPIENTE.getTipo()))
				System.out.println("regresas a "+tablero.get(i).getDest());
		}*/
		/*Integer l1 = 99, l2 = 90, l3 = 80, l4 = 90;

		for (int i = 0; i < 5; i++) {
			for (int j = l1; j >= l2; j--) {
				System.out.print(j + " ");
			}
			System.out.println();
			for (int j = l3; j < l4; j++) {
				System.out.print(j + " ");
			}
			System.out.println();
			l1 -= 20;
			l2 -= 20;
			l3 -= 20;
			l4 -= 20;
		}*/
		Random rnd=new Random();
		Integer n=(int) (rnd.nextDouble()*6+1);
		System.out.println(n);
	}	

}
