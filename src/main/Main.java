package main;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.IndexOutOfBoundsException;

public static Concurso selecionaConcurso(ArrayList<Concurso> concursos){
		Scanner user = new Scanner(System.in);
		System.out.println("Por favor, escolha o concurso desejado:");
		for(Concurso concurso : concursos){
			System.out.println(concurso.getId() + " - " + concurso.getNome());
		}
		int escolhido = user.nextInt() - 1;
		Concurso selecionado = null;
		
		// Verifica se o concurso esta agendado
		try {
			selecionado = concursos.get(escolhido);
		} catch (IndexOutOfBoundsException e) {
			System.out.printf("\nErro: Concurso inexistente!! (%s).", e.getMessage());
		}
		return selecionado;
}

public class Main {
	static ArrayList<Servidor> servidores = new ArrayList<>();

	public static void main(String[] args) throws ParseException, IOException {
		ArrayList<Concurso> concursos = new ArrayList<>();
		ArrayList<Docente> docentes = new ArrayList<>();

		// Cria 3 servidores e 3 docentes aleatorios sÃ³ pra testar
		for (int i = 0; i < 3; i++) {
			Docente aux1 = new Docente();
			if (i == 0) {
				aux1.setNome("Glauber");
			} else if (i == 1) {
				aux1.setNome("Leony");
			} else {
				aux1.setNome("Vitória");
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
			System.out.println("1: Agendamento\n2: Editar concursos\n3: Retificar edital\n4: Sair\n5:Imprimir");
			System.out.print("Digite um numero com a opcao desejada: ");
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
				concursos.get(aux).editarConcurso(docentes);
				break;
			case 3:
				System.out.println("Retificar edital.");
				for(int i = 0; i < concursos.size(); i++){
					System.out.println(i + ": " + concursos.get(i).getNome());					
				}
				System.out.print("Selecione o concurso: ");
				aux = user.nextInt();
				concursos.get(aux).criarEdital();
				break;
				
			case 4:
				aux = 10;
				break;
			case 5:
				Concurso selecionado = selecionaConcurso(concursos);
				if (selecionado != null) {
					selecionado.adicionarParticipante();
					user.nextLine();
				}
				break;
			case 6:
				selecionado = selecionaConcurso(concursos);
				selecionado.selecionaRelatorio();
				break;
			default:
				System.out.println("Opcao invalida");
				break;

			}
		}
		user.close();
	}

}
