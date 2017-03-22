package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Concurso {
	private static int concursosCadastrados;
	private String nome;
	private int id;
	private double valorInscricao;
	private String areaEstudo;
	private Servidor supervisor;
	private Date dataTerminoInscricao;
	private Date dataConcurso;
	private Date dataInicioInscricao;
	private String modalidade;
	private ArrayList<Docente> banca;
	private ArrayList<Participante> participantes;
	private String comissao;
	private Edital edital;

	/* Contrutor:
	 * Inicializa supervisor, banca e edital
	 * Incrementa a quantidade de concursos cadastrados */
	Concurso() {
		concursosCadastrados++;
		this.supervisor = new Servidor();
		this.participantes = new ArrayList<Participantes>();
		this.banca = new ArrayList<>();
		this.edital = new Edital();
	}

	// Gets e Sets
	public Edital getEdital() {
		return edital;
	}

	public void setEdital(Edital edital) {
		this.edital = edital;
	}

	public double getValorInscricao() {
		return valorInscricao;
	}

	public void setValorInscricao(double valorInscricao) {
		this.valorInscricao = valorInscricao;
	}

	public String getComissao() {
		return comissao;
	}

	public void setComissao(String comissao) {
		this.comissao = comissao;
	}

	public ArrayList<Participante> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(ArrayList<Participante> participantes) {
		this.participantes = participantes;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAreaEstudo() {
		return areaEstudo;
	}

	public void setAreaEstudo(String areaEstudo) {
		this.areaEstudo = areaEstudo;
	}

	public Servidor getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Servidor supervisor) {
		this.supervisor = supervisor;
	}

	public Date getDataInicioInscricao() {
		return dataInicioInscricao;
	}

	public boolean setDataInicioInscricao(String aux1, SimpleDateFormat format, Date dateaux) throws ParseException {
		Date newdate = new Date(format.parse(aux1).getTime());

		if (newdate.compareTo(dateaux) < 0) {
			System.out.println("Essa data ja passou");
		} else if (newdate.compareTo(this.getDataConcurso()) > 0) {
			System.out.println("Essa data e posterior a data de realizacao do concurso");
		} else if (newdate.compareTo(this.getDataTerminoInscricao()) > 0) {
			System.out.println("Essa data e posterior ao termino das inscricoes");
		} else {
			this.dataInicioInscricao = newdate;
			return true;
		}
		
		return false;
	}

	public Date getDataTerminoInscricao() {
		return dataTerminoInscricao;
	}

	public boolean setDataTerminoInscricao(String aux1, SimpleDateFormat format, Date dateaux) throws ParseException {
		Date newdate = new Date(format.parse(aux1).getTime());

		if (newdate.compareTo(this.getDataInicioInscricao()) < 0) {
			System.out.println("Essa data e anterior a data de inicio das inscricoes");
		} else if (newdate.compareTo(this.getDataInicioInscricao()) == 0) {
			System.out.println("Essa e a mesma data de inscricao");
		} else if (newdate.compareTo(this.getDataConcurso()) >= 0) {
			System.out.println("Essa data e posterior a data de realizacao do concurso");
		} else {
			this.dataTerminoInscricao = newdate;
			return true;
		}
		
		return false;
	}

	public Date getDataConcurso() {
		return dataConcurso;
	}

	public boolean setDataConcurso(String aux1, SimpleDateFormat format, Date dateaux) throws ParseException {
		Date date2 = new Date(format.parse(aux1).getTime());

		if (date2.compareTo(dateaux) < 0) {
			System.out.println("Essa data ja passou");
		} else if (date2.compareTo(dateaux) == 0) {
			System.out.println("Voce nao pode marcar o concurso para hoje");
		} else {
			this.dataConcurso = date2;
			return true;
		}
		
		return false;
	}

	public String getModalidade() {
		return modalidade;
	}

	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}

	public ArrayList<Docente> getBanca() {
		return banca;
	}

	public void addBanca(Docente docente) {
		this.banca.add(docente);
	}

	public void editarBanca(){
		System.out.println("");
	}
	
	public static void setConcursosCadastrados(int concursosCadastrados) {
		Concurso.concursosCadastrados = concursosCadastrados;
	}

	public static int getConcursosCadastrados() {
		return concursosCadastrados;
	}

	// Cria o edital em txt
	public void criarEdital() throws IOException, ParseException {
		Scanner s = new Scanner(System.in);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		// Cria um arquivo .txt
		FileWriter edital = new FileWriter(new File("Edital.txt"));
		
		System.out.println("Edital.");
		System.out.print("Digite detalhes do edital: ");
		this.edital.setDetalhes(s.nextLine());		
		GregorianCalendar data = new GregorianCalendar();
		String date = format.format(data.getTime());
		
		Date dateaux = new Date(format.parse(date).getTime());
		
		
		PrintWriter gravarArq = new PrintWriter(edital);
		
		// Escreve no arquivo aberto
		gravarArq.println("Retificado em: " + format.format(dateaux));
		gravarArq.println("Concurso" + this.getNome());
		gravarArq.println("Id: " + this.getId());
		gravarArq.println("Comissao organizadora: " + this.getComissao());
		gravarArq.println("Taxa de incriÃ§Ã£o: " + this.getValorInscricao());
		gravarArq.println(this.edital.getDetalhes());
		gravarArq.println("Data de realizacao do concurso: ");
		gravarArq.println(format.format(this.getDataConcurso()));
		gravarArq.println("Data de inicio das inscricoes: ");
		gravarArq.println(format.format(this.getDataInicioInscricao()));
		gravarArq.println("Data de termino das inscricoes: ");
		gravarArq.println(format.format(this.getDataTerminoInscricao()));
		
		// Fecha o arquivo .txt e o scanner
		edital.close();
		s.close();
	}

	// Edita datas ou banca do concurso
	public void editarConcurso(ArrayList<Docente> docentes) throws ParseException {
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		int entrada, aux = 0;
		String aux1;
		
		System.out.println("ID do concurso: " + this.getId());
		System.out.println("Digite a opcao desejada:\n1: Editar datas\n2: Editar banca");
		entrada = sc.nextInt();
		
		switch(entrada){
			case 1:
				// Usuario escolheu modificar a(s) data(s) do concurso
				
				// Pegando a data atual do sistema
				GregorianCalendar data = new GregorianCalendar();
				String date = format.format(data.getTime());
				Date dateaux = new Date(format.parse(date).getTime());
				
				do{
					System.out.println(
							"1: Editar data de realizacao do concurso\n2: Editar data de termino das inscricoes\n3: Editar data de inicio da inscricoes");
					entrada = sc.nextInt();
					sc.nextLine();
					
					switch(entrada){
						case 1:
							// Modificar a data da realizacao do concurso
							System.out.print("Nova data de realizacao do concurso(dd/mm/aaaa): ");
							try{
								aux1 = sc.nextLine();
								this.setDataConcurso(aux1, format, dateaux);
							} catch (Exception e){
								aux1 = sc.nextLine();
								System.out.print("Formato incorreto. Digite a data na forma (dd/mm/aaaa): ");
								this.setDataConcurso(aux1, format, dateaux);
							}
							break;
						case 2:
							// Modificar a data de termino das incricoes para o concurso
							System.out.print("Nova data de termino das inscricoes(dd/mm/aaaa): ");
							try{
								aux1 = sc.nextLine();
								this.setDataTerminoInscricao(aux1, format, dateaux);
							} catch (Exception e){
								System.out.print("Formato incorreto. Digite a data na forma (dd/mm/aaaa): ");
								aux1 = sc.nextLine();
								this.setDataTerminoInscricao(aux1, format, dateaux);
							}
							break;
						case 3:
							// Modificar a data de inicio das incricoes para o concurso
							System.out.print("Data de inicio das inscricoes(dd/mm/aaaa): ");
							try{
								aux1 = sc.nextLine();
								this.setDataInicioInscricao(aux1, format, dateaux);
							} catch (Exception e){
								aux1 = sc.nextLine();
								System.out.print("Formato incorreto. Digite a data na forma (dd/mm/aaaa): ");
								this.setDataInicioInscricao(aux1, format, dateaux);
							}
							break;
						default:
							System.out.println("Opcao Invalida. Deseja tentar novamente?\n1. Sim\n0. Não");
					}
				}while(entrada != 0);
				break;
			case 2:
				// Usuario escolheu modificar banca
				do{
					System.out.println("Editar a banca.");
					System.out.println("Digite a opcao desejada:\n1: Adicionar docentes a banca\n2: Remover docentes da banca\n");
					entrada = sc.nextInt();
					
					switch(entrada){
						case 1:
							// Adicionar docentes
							System.out.println("Adicionar docentes a banca.");
							
							// Informa quais docentes estao cadastrados no sistema
							for (int i = 0; i < docentes.size(); i++) {
								System.out.println(i + ": " + docentes.get(i).getNome());
							}
							System.out.print("Selecione o docente que deseja adicionar a banca: ");
							do{
								entrada = sc.nextInt();
								if (entrada < docentes.size() && !this.getBanca().contains(docentes.get(entrada))) {
									this.addBanca(docentes.get(entrada));
									aux = 1;
								} else if (entrada < docentes.size() && this.getBanca().contains(docentes.get(entrada))) {
									System.out.println("Voce ja associou esse docente a esse concurso.");
								} else {
									System.out.println("Nao foi encontrado o Docente correspondente. Por favor, tente novamente: ");
								}
							}while(aux != 1);
							aux = 0;
							break;
						case 2:
							// Informa os docentes cadastrados na banca do concurso
							for (int i = 0; i < this.getBanca().size(); i++) {
								System.out.println(i + ": " + docentes.get(i).getNome());
							}
							System.out.print("Digite o docente que deseja remover da banca: ");
							do{
								entrada = sc.nextInt();
								if (entrada < docentes.size()) {
									this.getBanca().remove(entrada);
									aux = 1;
								} else {
									System.out.println("Nao foi encontrado o docente correspondente. Por favor, tente novamente: ");
								}
							}while(aux != 1);
							aux= 0;
							break;
						default:
							System.out.println("Opcao invalida. Deseja tentar novamente?\n1. Sim\n0. Nao");
					}
				}while(entrada != 0);
				break;
			default:
				System.out.println("Opcao invalida. Por favor, tente novamente.");
		}
		sc.close();
	}

	public static Concurso agendamento(ArrayList<Docente> docentes, ArrayList<Servidor> servidores)
			throws ParseException, IOException {
		System.out.println("Agendamento.\nAntes, e necessario cadastrar um novo concurso");
		Concurso aux = new Concurso();
		int entrada;
		Scanner scan = new Scanner(System.in);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		boolean auxdate = false;
		String aux1;
		
		aux.setId(Concurso.getConcursosCadastrados());
		System.out.println("ID do Concurso: " + aux.getId());
		System.out.print("Nome: ");
		aux.setNome(scan.nextLine());
		System.out.print("Supervisor (servidor responsavel): ");
		System.out.println("Servidores Cadastrados: ");
		for (int i = 0; i < servidores.size(); i++) {
			System.out.println(i + ": " + servidores.get(i).getNome());
		}
		System.out.print("Digite o codigo correspondente ao servidor desejado: ");
		entrada = scan.nextInt();
		scan.nextLine();

		aux.supervisor = servidores.get(entrada);
		System.out.println(aux.getSupervisor().getNome());

		// Pega data atual do sistema
		GregorianCalendar data = new GregorianCalendar();
		String date = format.format(data.getTime());
		Date dateaux = new Date(format.parse(date).getTime());

		// Seta datas do concurso
		System.out.print("Data de inicio das inscricoes(dd/mm/aaaa): ");
		do {
			try {
				aux1 = scan.nextLine();
				auxdate = aux.setDataInicioInscricao(aux1, format, dateaux);
			} catch (Exception e) {
				aux1 = scan.nextLine();
				System.out.print("Formato incorreto. Digite a data na forma (dd/mm/aaaa): ");
				auxdate = aux.setDataInicioInscricao(aux1, format, dateaux);
			}
		} while (!auxdate);
		System.out.println(format.format(aux.getDataInicioInscricao()));
		System.out.print("Data de termino das inscricoes(dd/mm/aaaa): ");
		do {
			try {
				aux1 = scan.nextLine();
				auxdate = aux.setDataTerminoInscricao(aux1, format, dateaux);
			} catch (Exception e) {
				System.out.print("Formato incorreto. Digite a data na forma (dd/mm/aaaa): ");
				aux1 = scan.nextLine();
				auxdate = aux.setDataTerminoInscricao(aux1, format, dateaux);
			}
		} while (!auxdate);
		System.out.println(format.format(aux.getDataTerminoInscricao()));
		System.out.print("Data de realizacao do concurso(dd/mm/aaaa): ");
		do {
			try {
				aux1 = scan.nextLine();
				auxdate = aux.setDataConcurso(aux1, format, dateaux);
			} catch (Exception e) {
				System.out.print("Formato incorreto. Digite a data na forma (dd/MM/aaa): ");
				aux1 = scan.nextLine();
				auxdate = aux.setDataConcurso(aux1, format, dateaux);
			}
		} while (!auxdate);
		System.out.println(format.format(aux.getDataConcurso()));
		
		System.out.print("Modalidade do concurso: ");
		aux.setModalidade(scan.nextLine());
		System.out.print("Comissao organizadora: ");
		aux.setComissao(scan.nextLine());
		System.out.print("Valor da inscricao: ");
		aux.setValorInscricao(scan.nextDouble());
		System.out.println("Adicionar docentes a Banca.");
		
		// Imprime lista dos docentes cadastrados no sistema
		for (int i = 0; i < docentes.size(); i++) {
			System.out.println(i + ": " + docentes.get(i).getNome());
		}
		
		while (entrada != 99) {
			System.out.print("Digite o codigo referente ao docente que deseja adicionar: ");
			entrada = scan.nextInt();
			if (entrada < docentes.size() && !aux.getBanca().contains(docentes.get(entrada))) {
				aux.addBanca(docentes.get(entrada));
			} else if (entrada < docentes.size() && aux.getBanca().contains(docentes.get(entrada))) {
				System.out.println("Voce ja associou esse docente a esse concurso.");
			} else {
				System.out.println("Nao foi encontrado o Docente correspondente. Tente novamente.");
			}
			System.out.println("Para continuar, digite '10'. Para sair, digite '99'");
			entrada = scan.nextInt();
		}
		for (int i = 0; i < aux.getBanca().size(); i++) {
			System.out.println(i + ": " + aux.getBanca().get(i).getNome());
		}
		aux.criarEdital();
		// A adicao de participantes vai ficar em outra parte
		scan.close();
		
		return aux;
	}

	public void criarConviteBanca(ArrayList<Docente> docentes) throws IOException{
		Scanner scn = new Scanner(System.in);
		FileWriter convite = new FileWriter(new File("C:\\Users\\Glauber Braga\\Desktop\\Convite.txt"));
		PrintWriter gravarArq = new PrintWriter(convite);
		int entrada;
		boolean flag = false;
		
		System.out.println("Criar convite para composição de banca.");
		System.out.println("Digite o id do docente para o qual deseja enviar o convite para compor a banca");
		System.out.println("\nDocentes disponíveis:");
		for (int i = 0; i < docentes.size(); i++) {
			if(!this.getBanca().contains(docentes.get(i))){
				System.out.println(i + ": " + docentes.get(i).getNome());
			}
		}
		entrada = scn.nextInt();
		
		//Começa a escrita no arquivo
		gravarArq.println("Caro " + this.getBanca().get(entrada).getNome());
		
		System.out.println("Selecione a opcao desejada:\n1: Utilizar um convite preescrito\n2: Escrever um novo convite");
		entrada = scn.nextInt();
		
		do{
			switch(entrada){
			case 1:
				// Convite predeterminado
				gravarArq.print("\n 		O Instituto de Computacao (IC) da Universidade Federal de Alagoas (UFAL) tem o prazer de convidá-lo para compor a banca de correcao do concurso ");
				gravarArq.println(this.getNome() + " que será realizado no dia " + this.getDataConcurso());
				gravarArq.println(" 		Pedimos encarecidamente que nos dê uma confirmacao o mais breviamente possivel.");
				gravarArq.println("\n 										Atenciosamente");
				gravarArq.println("			 										Secretaria do Instituto de Computacao");
				flag = true;
				break;
			case 2:
				// Convite feito pela secretaria
				flag = true;
				break;
			default:
				System.out.println("Opcao invalida. Por favor, tente novamente.");
			}
		}while(!flag);
		
		convite.close();
		scn.close();
	}
		public void adicionarParticipante() {
		Scanner entrada = new Scanner(System.in);
		Participante novo = new Participante();
		System.out.println("Nome completo: ");
		novo.setNome(entrada.nextLine());
		System.out.println("CPF (sem pontos e sem traço):");
		String newCPF = entrada.nextLine();
		while (novo.validaCPF(newCPF) != true){
			System.out.println("CPF inválido! Por favor, digite um número de CPF válido");
			newCPF = entrada.nextLine();
		}
		novo.setCpf(newCPF);
		System.out.println("Registro Geral: ");
		novo.setRg(entrada.nextInt());
		entrada.nextLine();
		System.out.println("Órgão Emissor: ");
		novo.setOrgaoEmissorRG(entrada.nextLine());
		System.out.println("Data de Nascimento (dd/mm/aaaa): ");
		int valida = 0;
		SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
		GregorianCalendar data = new GregorianCalendar();
		String aux1 = format.format(data.getTime());
		while (valida == 0) {
			try {
				aux1 = entrada.nextLine();
				// obs: colocar a data no formato normal!
				novo.setDataNascimento(new Date(format.parse(aux1).getTime()));
				valida = 1;
			} catch (ParseException e) {
				System.out.print("Formato incorreto. Digite a data na forma (dd/mm/aaaa): ");
			}
		}
		System.out.println("Nome da mãe: ");
		novo.setNomeMae(entrada.nextLine());
		System.out.println("Naturalidade: ");
		novo.setNaturalidade(entrada.nextLine());
		System.out.println("Endereço: ");
		novo.setEndereco(entrada.nextLine());
		System.out.println("Email: ");
		novo.setEmail(entrada.nextLine());
		System.out.println("Telefone (com DDD): ");
		String telefone = entrada.nextLine();
		int valido = 0;
		while (valido == 0) {
			if (telefone.length() == 11){
				novo.setTelefone(telefone);
				valido = 1;
			} else {
				System.out.println("Número de telefone inválido!");
				telefone = entrada.nextLine();
			}
		}
		int aux = 0;
		System.out.println("Possui algum tipo de deficiência?\n1-Sim\n2-Não");
		while (aux == 0) {
			try {
				aux = entrada.nextInt();
				switch(aux){
				case 1:
					novo.setDeficiencia(true);
					entrada.nextLine();
					System.out.println("Qual(is) o(s) tipo(s) de deficiência do candidato?");
					novo.setAcessibilidade(entrada.nextLine());
					break;
				case 2:
					novo.setDeficiencia(false);
					break;
				default:
					System.out.println("Opção inválida!\n1-Sim\n2-Não");
					aux = 0;
					break;
				}
			} catch (InputMismatchException e) {
				System.err.println("Não é permitido inserir letras, informe apenas números inteiros!");
			}
		}
		aux = 0;
		System.out.println("Deseja solicitar isenção de taxa?\n1-Sim\n2-Não");
		while (aux == 0) {
			try {
				aux = entrada.nextInt();
				switch(aux){
				case 1:
					novo.setSolicitaIsencao(true);
					break;
				case 2:
					novo.setSolicitaIsencao(false);
					break;
				default:
					System.out.println("Opção inválida!\n1-Sim\n2-Não");
					aux = 0;
					break;
				}
			} catch (InputMismatchException e) {
				System.err.println("Não é permitido inserir letras, informe apenas números inteiros!");
				aux = entrada.nextInt();
			}
		}
		novo.setAptidao(false);
		novo.setIsentoTaxa(false);
		novo.setTaxaPaga(false);
		System.out.println("\n");
		novo.confirmacao();
		System.out.println("\nInscrição realizada com sucesso!\n");
		this.participantes.add(novo);
	}
	
	public void relatorioGeral() throws IOException {
		FileWriter arq = new FileWriter("C:\\Users\\leony\\Desktop\\Relatorio.txt");
		PrintWriter gravarArq = new PrintWriter(arq);
		gravarArq.println("Docentes na banca do concurso: " + this.banca.size() + "\n");
		gravarArq.println("Participantes inscritos do concurso: " + this.participantes.size() + "\n");
		arq.close();
	}
	
	public void relatorioBanca() throws IOException {
		FileWriter arq = new FileWriter("C:\\Users\\leony\\Desktop\\Banca.txt");
		PrintWriter gravarArq = new PrintWriter(arq);
		gravarArq.println("Docentes na banca do concurso\n");
		int i = 0;
		for (Docente docente : this.banca){
			gravarArq.println(i + " - " + docente.getNome());
			i++;
		}
		arq.close();
	}
	
	public void relatorioParticipantes() throws IOException {
		FileWriter arq = new FileWriter("C:\\Users\\leony\\Desktop\\Participantes.txt");
		PrintWriter gravarArq = new PrintWriter(arq);
		gravarArq.println("Participantes inscritos no concurso\n");
		for (Participante participante : this.participantes){
			gravarArq.println(participante.getCpf() + " - " + participante.getNome());
		}
		arq.close();
	}
	
	public void selecionaRelatorio() throws IOException {
		Scanner entrada = new Scanner(System.in);
		int escolha = entrada.nextInt();
		switch(escolha){
		case 1:
			this.relatorioGeral();
			break;
		case 2:
			this.relatorioBanca();
			break;
		case 3:
			this.relatorioParticipantes();
			break;
		default:
			System.out.println("Opção Inválida!");
		}
	}

}
