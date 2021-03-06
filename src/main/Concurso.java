package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.lang.IndexOutOfBoundsException;

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
	 * Inicializa supervisor, participantes, banca e edital
	 * Incializa datas como nulo
	 * Incrementa a quantidade de concursos cadastrados */
	Concurso() {
		concursosCadastrados++;
		this.supervisor = new Servidor();
		this.participantes = new ArrayList<Participante>();
		this.banca = new ArrayList<>();
		this.edital = new Edital();
		this.dataConcurso = null;
		this.dataInicioInscricao = null;
		this.dataTerminoInscricao = null;
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

	public boolean setDataInicioInscricao(String aux1, Date dateaux) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date newdate = new Date(format.parse(aux1).getTime());

		if (newdate.compareTo(dateaux) < 0) {
			System.out.println("Essa data ja passou");
		} else if ((this.dataConcurso != null) && (newdate.compareTo(this.getDataConcurso()) > 0)) {
			System.out.println("Essa data e posterior a data de realizacao do concurso");
		} else if ((this.dataTerminoInscricao != null) &&newdate.compareTo(this.getDataTerminoInscricao()) > 0) {
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

	public boolean setDataTerminoInscricao(String aux1, Date dateaux) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date newdate = new Date(format.parse(aux1).getTime());

		if (newdate.compareTo(this.getDataInicioInscricao()) < 0) {
			System.out.println("Essa data e anterior a data de inicio das inscricoes");
		} else if ((this.dataInicioInscricao != null) && newdate.compareTo(this.getDataInicioInscricao()) == 0) {
			System.out.println("Essa e a mesma data de inscricao");
		} else if ((this.dataConcurso != null) && newdate.compareTo(this.getDataConcurso()) >= 0) {
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

	public boolean setDataConcurso(String aux1, Date dateaux) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
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
		gravarArq.println("Taxa de inscricao: " + this.getValorInscricao());
		gravarArq.println(this.edital.getDetalhes());
		gravarArq.println("Data de realizacao do concurso: ");
		gravarArq.println(format.format(this.getDataConcurso()));
		gravarArq.println("Data de inicio das inscricoes: ");
		gravarArq.println(format.format(this.getDataInicioInscricao()));
		gravarArq.println("Data de termino das inscricoes: ");
		gravarArq.println(format.format(this.getDataTerminoInscricao()));
		
		// Fecha o arquivo .txt e o scanner
		edital.close();
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
								this.setDataConcurso(aux1, dateaux);
							} catch (Exception e){
								aux1 = sc.nextLine();
								System.out.print("Formato incorreto. Digite a data na forma (dd/mm/aaaa): ");
								this.setDataConcurso(aux1, dateaux);
							}
							break;
						case 2:
							// Modificar a data de termino das incricoes para o concurso
							System.out.print("Nova data de termino das inscricoes(dd/mm/aaaa): ");
							try{
								aux1 = sc.nextLine();
								this.setDataTerminoInscricao(aux1, dateaux);
							} catch (Exception e){
								System.out.print("Formato incorreto. Digite a data na forma (dd/mm/aaaa): ");
								aux1 = sc.nextLine();
								this.setDataTerminoInscricao(aux1, dateaux);
							}
							break;
						case 3:
							// Modificar a data de inicio das incricoes para o concurso
							System.out.print("Data de inicio das inscricoes(dd/mm/aaaa): ");
							try{
								aux1 = sc.nextLine();
								this.setDataInicioInscricao(aux1, dateaux);
							} catch (Exception e){
								aux1 = sc.nextLine();
								System.out.print("Formato incorreto. Digite a data na forma (dd/mm/aaaa): ");
								this.setDataInicioInscricao(aux1, dateaux);
							}
							break;
						default:
							System.out.println("Opcao Invalida. Deseja tentar novamente?\n1. Sim\n0. N�o");
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
	}

	/* Agendamento de concursos
	 * Cria um concurso e seta todas as informa��es b�sicas */
	public static Concurso agendamento(ArrayList<Docente> docentes, ArrayList<Servidor> servidores)
			throws ParseException, IOException {
		System.out.println("Agendamento.\nAntes, e necessario cadastrar um novo concurso");
		Concurso aux = new Concurso();
		int entrada;
		Scanner scan = new Scanner(System.in);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		boolean auxdate = false;
		//String aux1;
		
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
				String aux1 = scan.nextLine();
				auxdate = aux.setDataInicioInscricao(aux1, dateaux);
			} catch (Exception e) {
				System.out.print("Formato incorreto. Digite a data na forma (dd/mm/aaaa): ");
				String aux1 = scan.nextLine();
				auxdate = aux.setDataInicioInscricao(aux1, dateaux);
			}
		} while (!auxdate);
		System.out.println(format.format(aux.getDataInicioInscricao()));
		System.out.print("Data de termino das inscricoes(dd/mm/aaaa): ");
		do {
			try {
				String aux1 = scan.nextLine();
				auxdate = aux.setDataTerminoInscricao(aux1, dateaux);
			} catch (Exception e) {
				System.out.print("Formato incorreto. Digite a data na forma (dd/mm/aaaa): ");
				String aux1 = scan.nextLine();
				auxdate = aux.setDataTerminoInscricao(aux1, dateaux);
			}
		} while (!auxdate);
		System.out.println(format.format(aux.getDataTerminoInscricao()));
		System.out.print("Data de realizacao do concurso(dd/mm/aaaa): ");
		do {
			try {
				String aux1 = scan.nextLine();
				auxdate = aux.setDataConcurso(aux1, dateaux);
			} catch (Exception e) {
				System.out.print("Formato incorreto. Digite a data na forma (dd/MM/aaa): ");
				String aux1 = scan.nextLine();
				auxdate = aux.setDataConcurso(aux1, dateaux);
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
		
		return aux;
	}

	// Cria��o de convite para composi��o da banca
	public void criarConviteBanca(ArrayList<Docente> docentes) throws IOException{
		Scanner scn = new Scanner(System.in);
		File arquivo = new File("C:\\Users\\Glauber Braga\\Desktop\\Convite.txt");
		FileWriter convite = new FileWriter(arquivo);
		PrintWriter gravarArq = new PrintWriter(convite);
		int entrada;
		boolean flag = false;
		
		System.out.println("Criar convite para composi��o de banca.");
		System.out.println("Digite o id do docente para o qual deseja enviar o convite para compor a banca");
		System.out.println("\nDocentes dispon�veis:");
		for (int i = 0; i < docentes.size(); i++) {
			if(!this.getBanca().contains(docentes.get(i))){
				System.out.println(i + ": " + docentes.get(i).getNome());
			}
		}
		entrada = scn.nextInt();
		
		//Come�a a escrita no arquivo
		gravarArq.println("Caro " + this.getBanca().get(entrada).getNome());
		
		System.out.println("Selecione a opcao desejada:\n1: Utilizar um convite preescrito\n2: Escrever um novo convite");
		entrada = scn.nextInt();
		scn.nextLine();
		
		do{
			switch(entrada){
			case 1:
				// Convite predeterminado
				gravarArq.print("\n 		O Instituto de Computacao (IC) da Universidade Federal de Alagoas (UFAL) tem o prazer de convid�-lo para compor a banca de correcao do concurso ");
				gravarArq.println(this.getNome() + " que ser� realizado no dia " + this.getDataConcurso());
				gravarArq.println(" 		Pedimos encarecidamente que nos d� uma confirmacao o mais breviamente possivel.");
				gravarArq.println("\n 										Atenciosamente");
				gravarArq.println("			 										Secretaria do Instituto de Computacao");
				convite.close();
				flag = true;
				break;
			case 2:
				// Convite feito pela secretaria
				System.out.println("Digite '10' para cancelar\nDigite '99' para terminar");
				while(!scn.hasNextInt()){
					gravarArq.println(scn.nextLine());
				}
				entrada = scn.nextInt();
				if(entrada == 10){
					arquivo.delete();
				}
				else{
					convite.close();
				}
				flag = true;
				break;
			default:
				System.out.println("Opcao invalida. Por favor, tente novamente.");
			}
		}while(!flag);
	}

	// Adiciona participante em determinado concurso
	public void adicionarParticipante() {
		Scanner entrada = new Scanner(System.in);
		Participante novo = new Participante();
		System.out.println("Nome completo: ");
		novo.setNome(entrada.nextLine());
		System.out.println("CPF (sem pontos e sem tra�o):");
		String newCPF = entrada.nextLine();
		while (novo.validaCPF(newCPF) != true){
			System.out.println("CPF invalido! Por favor, digite um numero de CPF valido");
			newCPF = entrada.nextLine();
		}
		novo.setCpf(newCPF);
		System.out.println("Registro Geral: ");
		novo.setRg(entrada.nextInt());
		entrada.nextLine();
		System.out.println("Orgao Emissor: ");
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
		System.out.println("Nome da mae: ");
		novo.setNomeMae(entrada.nextLine());
		System.out.println("Naturalidade: ");
		novo.setNaturalidade(entrada.nextLine());
		System.out.println("Endereco: ");
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
				System.out.println("Numero de telefone invalido!");
				telefone = entrada.nextLine();
			}
		}
		int aux = 0;
		System.out.println("Possui algum tipo de deficiencia?\n1-Sim\n2-Nao");
		while (aux == 0) {
			try {
				aux = entrada.nextInt();
				switch(aux){
				case 1:
					novo.setDeficiencia(true);
					entrada.nextLine();
					System.out.println("Qual(is) o(s) tipo(s) de defici�ncia do candidato?");
					novo.setAcessibilidade(entrada.nextLine());
					break;
				case 2:
					novo.setDeficiencia(false);
					break;
				default:
					System.out.println("Op�ao invalida!\n1-Sim\n2-Nao");
					aux = 0;
					break;
				}
			} catch (InputMismatchException e) {
				System.err.println("Nao e permitido inserir letras, informe apenas numeros inteiros!");
			}
		}
		aux = 0;
		System.out.println("Deseja solicitar isencao de taxa?\n1-Sim\n2-Nao");
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
					System.out.println("Opcao invalida!\n1-Sim\n2-Nao");
					aux = 0;
					break;
				}
			} catch (InputMismatchException e) {
				System.err.println("Nao e permitido inserir letras, informe apenas numeros inteiros!");
				aux = entrada.nextInt();
			}
		}
		novo.setAptidao(false);
		novo.setIsentoTaxa(false);
		novo.setTaxaPaga(false);
		System.out.println("\n");
		novo.confirmacao();
		System.out.println("\nInscricao realizada com sucesso!\n");
		this.participantes.add(novo);
	}
	
	// Gera num arquivo um relat�rio geral dos concursos cadastrados
	public static void relatorioGeral(ArrayList<Concurso> concursos) throws IOException {
		FileWriter arq = new FileWriter("C:\\Users\\leony\\Desktop\\Relatorio.txt");
		PrintWriter gravarArq = new PrintWriter(arq);
		gravarArq.println("Total de concursos: " + concursos.size() + "\n");
		for(Concurso concurso : concursos){
			gravarArq.println("Concurso n�: " + concurso.getId() + " - " + concurso.getNome());
			gravarArq.println("Docentes na banca do concurso: " + concurso.banca.size() + "\n");
			gravarArq.println("Participantes inscritos do concurso: " + concurso.participantes.size() + "\n\n");	
		}
		arq.close();
	}
	
	// Gera num arquivo a rela��o dos professores componentes da banca de determinado concurso
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
	
	// Gera num arquivo a rela��o dos participantes de determinado concurso
	public void relatorioParticipantes() throws IOException {
		FileWriter arq = new FileWriter("C:\\Users\\leony\\Desktop\\Participantes.txt");
		PrintWriter gravarArq = new PrintWriter(arq);
		gravarArq.println("Participantes inscritos no concurso\n");
		for (Participante participante : this.participantes){
			gravarArq.println(participante.getCpf() + " - " + participante.getNome());
		}
		arq.close();
	}
	
	// Faz a sele��o do relat�rio a ser gerado.
	public static void selecionaRelatorio(ArrayList<Concurso> concursos) throws IOException {
		Scanner entrada = new Scanner(System.in);
		Concurso selecionado = Main.selecionaConcurso(concursos);
		int escolha = entrada.nextInt();
		switch(escolha){
		case 1:
			relatorioGeral(concursos);
			break;
		case 2:
			if(selecionado != null) selecionado.relatorioBanca();
			break;
		case 3:
			if(selecionado != null) selecionado.relatorioParticipantes();
			break;
		default:
			System.out.println("Opcao Invalida!");
		}
	}
	
	// Confirma a taxa de pagamentos de um participante no sistema
	public void pagamentoTaxa(){
		Scanner entrada = new Scanner(System.in);
		int i = 1;
		Participante part = null;
		System.out.println("Escolha o participante ao qual sera associado o pagamento:");
		for (Participante partic : this.participantes){
			System.out.println(i + " - " + partic.getNome() + "\nCPF: " + partic.getCpf());
		}
		int selecionado = entrada.nextInt() - 1;
		try{
			part = this.participantes.get(selecionado);
		} catch (IndexOutOfBoundsException e){
			System.out.println("Participante invalido! Retornando ao menu principal...");
			return;
		}
		if (part.isTaxaPaga() == true) System.out.println("Este participante ja pagou a taxa");
		else if (part.isIsentoTaxa() == true) System.out.println("Este participante esta isento de taxa!");
		else {
			part.setTaxaPaga(true);
			part.setAptidao(true);
			System.out.println("Pagamento registrado com sucesso!");
		}
	}
	
	// Aplica a isen��o de taxa a um participante cuja solitita��o tenha sido aprovada
	public void isencaoTaxa() {
		Scanner entrada = new Scanner(System.in);
		int i = 1;
		Participante part = null;
		System.out.println("Escolha o participante ao qual sera associado o pagamento:");
		for (Participante partic : this.participantes){
			System.out.println(i + " - " + partic.getNome() + "\nCPF: " + partic.getCpf());
		}
		int selecionado = entrada.nextInt() - 1;
		try{
			part = this.participantes.get(selecionado);
		} catch (IndexOutOfBoundsException e){
			System.out.println("Participante invalido! Retornando ao menu principal...");
			return;
		}
		if (part.getSolicitaIsencao() == false) System.out.println("Este participante nao solicitou isencao!");
		else if (part.isIsentoTaxa() == true) System.out.println("Este participante ja foi isento da taxa!");
		else{
			part.setIsentoTaxa(true);
			part.setAptidao(true);
			System.out.println("Isencao registrada com sucesso!");
		}
	}
	
	// Imprime num arquivo os participantes que n�o pagaram a taxa, ou n�o est�o isentos
	public void desclassificaParticipantes() throws IOException{
		FileWriter arq = new FileWriter("C:\\Users\\leony\\Desktop\\Participantes Desclassificados.txt");
		PrintWriter gravarArq = new PrintWriter(arq);
		gravarArq.println("Participantes desclassificados do concurso\n");
		for (Participante participante : this.participantes){
			if(participante.isAptidao() == false) gravarArq.println(participante.getCpf() + " - " + participante.getNome());
		}
		arq.close();
	}

}