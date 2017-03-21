package main;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static ArrayList<Servidor> servidores = new ArrayList<>();

	public static void main(String[] args) throws ParseException {
		ArrayList<Concurso> concursos = new ArrayList<>();
		ArrayList<Docente> docentes = new ArrayList<>();

		// Cria 3 servidores e 3 docentes aleatorios só pra testar
		for (int i = 0; i < 3; i++) {
			Docente aux1 = new Docente();
			if (i == 0) {
				aux1.setNome("Glauber");
			} else if (i == 1) {
				aux1.setNome("Leony");
			} else {
				aux1.setNome("Vit�ria");
			}
			docentes.add(aux1);
		}
		for (int i = 0; i < 3; i++) {
			Servidor aux1 = new Servidor();
			if (i == 0) {
				aux1.setNome("Michael");
			} else if (i == 1) {
				aux1.setNome("Rodrigo");
			} else {
				aux1.setNome("Mariana");
			}
			servidores.add(aux1);
		}

		int aux = 0;
		Scanner user = new Scanner(System.in);
		while (aux != 10) {
			System.out.println("1: Agendamento\n2: Editar concursos");
			System.out.print("Digite um numero com a op��o desejada: ");
			aux = user.nextInt();
			switch (aux) {
			case 1:
				concursos.add(Concurso.agendamento(docentes, servidores));
				break;
			case 2:
				System.out.println("Editar concurso.");
				for(int i = 0; i < concursos.size(); i++){
					System.out.println(i + ": " + concursos.get(i).getNome());					
				}
				System.out.print("Selecione o concurso que deseja editar: ");
				aux = user.nextInt();
				concursos.get(aux).editarConcurso();
				break;
			default:
				System.out.println("Op��o inv�lida");
				break;

			}
		}
		user.close();
	}

}
