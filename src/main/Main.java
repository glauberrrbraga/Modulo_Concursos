package main;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.IndexOutOfBoundsException;

public class Main {
	static ArrayList<Servidor> servidores = new ArrayList<>();
	
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
			System.out.println("1: Agendamento\n2: Editar concursos\n3: Retificar edital\n4: Adicionar Participantes\n5: Imrpimir relatorios\n6: Associar taxa de pagamento ou isencao de taxa a um participante\n7: Criar convite para composicao de banca\n8: Exclusão de participantes\n9: Sair\n");
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
				Concurso selecionado = selecionaConcurso(concursos);
				if (selecionado != null) {
					selecionado.adicionarParticipante();
					user.nextLine();
				}
				break;
			case 5:
				selecionado = selecionaConcurso(concursos);
				if (selecionado != null) selecionado.selecionaRelatorio();
				break;
			case 6:
				selecionado = selecionaConcurso(concursos);
				if (selecionado != null) {
					System.out.println("Deseja:\n1-Validar uma taxa de pagamento\n2-Adicionar isencao\n");
					int opcao = user.nextInt();
					switch(opcao){
					case 1:
						selecionado.pagamentoTaxa();
						break;
					case 2:
						break;
					default:
						System.out.println("Opção invalida! Voltando ao menu principal...");
					}
				}
				break;
			case 7:
				for(int i = 0; i < concursos.size(); i++){
					System.out.println(i + ": " + concursos.get(i).getNome());					
				}
				System.out.print("Selecione o concurso: ");
				aux = user.nextInt();
				concursos.get(aux).criarConviteBanca(docentes);
				break;
			case 8:
				selecionado = selecionaConcurso(concursos);
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				GregorianCalendar data = new GregorianCalendar();
				String date = format.format(data.getTime());
				Date dateaux = new Date(format.parse(date).getTime());
				if(dateaux.after(selecionado.getDataTerminoInscricao())) selecionado.desclassificaParticipantes();
				break;
			case 9:
				aux = 10;
				break;
			default:
				System.out.println("Opcao invalida");
				break;

			}
		}
		user.close();
	}

}
