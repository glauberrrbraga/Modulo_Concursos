package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Concurso {
	private static int concursosCadastrados;
	private String nome;
	private int id;
	private String edital;
	private String areaEstudo;
	private Servidor supervisor;
	private Date dataTerminoInscricao;
	private Date dataConcurso;
	private Date dataInicioInscricao;
	private String modalidade;
	private ArrayList<Docente> banca;
	private ArrayList<Participante> participantes;
	private String comissao;

	Concurso() {
		concursosCadastrados++;
		this.supervisor = new Servidor();
		this.banca = new ArrayList<>();
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

	public String getEdital() {
		return edital;
	}

	public void setEdital(String edital) {
		this.edital = edital;
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

	public void setDataInicioInscricao(Date dataInicioInscricao) {
		this.dataInicioInscricao = dataInicioInscricao;
	}

	public Date getDataTerminoInscricao() {
		return dataTerminoInscricao;
	}

	public void setDataTerminoInscricao(Date dataTerminoInscricao) {
		this.dataTerminoInscricao = dataTerminoInscricao;
	}

	public Date getDataConcurso() {
		return dataConcurso;
	}

	public void setDataConcurso(Date dataConcurso) {
		this.dataConcurso = dataConcurso;
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

	public static void setConcursosCadastrados(int concursosCadastrados) {
		Concurso.concursosCadastrados = concursosCadastrados;
	}

	public static int getConcursosCadastrados() {
		return concursosCadastrados;
	}

	public void editarConcurso() throws ParseException {
		Scanner user = new Scanner(System.in);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("ID do concurso: " + this.getId());
		System.out.println("Digite a opção desejada:\n1: Editar datas\n2: Adicionar participantes ");
		int entrada = user.nextInt();
		String aux1;
		if (entrada == 1) {
			System.out.println(
					"1: Editar data de realização do concurso\n2: Editar data de termino das inscrições\n3: Editar data de inicio da inscrições");
			entrada = user.nextInt();
			user.nextLine();
			if (entrada == 1) {
				System.out.print("Data de realização do concurso(dd/MM/aaaa): ");
				try {
					aux1 = user.nextLine();
					this.setDataConcurso(new Date(format.parse(aux1).getTime()));
				} catch (Exception e) {
					System.out.print("Formato incorreto. Digite a data na forma (dd/MM/aaa): ");
					aux1 = user.nextLine();
					this.setDataConcurso(new Date(format.parse(aux1).getTime()));
				}

			} else if (entrada == 2) {
				System.out.print("Data de termino das inscrições(dd/MM/aaaa): ");
				try {
					aux1 = user.nextLine();
					this.setDataTerminoInscricao(new Date(format.parse(aux1).getTime()));
				} catch (Exception e) {
					System.out.print("Formato incorreto. Digite a data na forma (dd/MM/aaa): ");
					aux1 = user.nextLine();
					this.setDataTerminoInscricao(new Date(format.parse(aux1).getTime()));
				}

			}else if(entrada == 3){
				System.out.print("Data de inicio das inscrições(dd/MM/aaaa): ");
				try {
					aux1 = user.nextLine();
					this.setDataInicioInscricao(new Date(format.parse(aux1).getTime()));
				} catch (Exception e) {
					System.out.print("Formato incorreto. Digite a data na forma (dd/MM/aaa): ");
					aux1 = user.nextLine();
					this.setDataInicioInscricao(new Date(format.parse(aux1).getTime()));
				}
				
			}
		}
		user.close();
	}

	public static Concurso agendamento(ArrayList<Docente> docentes, ArrayList<Servidor> servidores)
			throws ParseException {
		System.out.println("Agendamento.\nAntes, é necessário cadastrar um novo concurso");
		Concurso aux = new Concurso();
		int entrada;
		Scanner user = new Scanner(System.in);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		aux.setId(Concurso.getConcursosCadastrados());
		System.out.println("ID do Concurso: " + aux.getId());
		System.out.print("Nome: ");
		aux.setNome(user.nextLine());
		System.out.print("Numero do edital correspondente: ");
		aux.setEdital(user.nextLine());
		System.out.print("Supervisor (servidor responsável): ");
		System.out.println("Servidores Cadastrados: ");
		for (int i = 0; i < servidores.size(); i++) {
			System.out.println(i + ": " + servidores.get(i).getNome());
		}
		System.out.print("Digite o código correspondente ao servidor desejado: ");
		entrada = user.nextInt();
		user.nextLine();
		aux.supervisor = servidores.get(entrada);
		System.out.println(aux.getSupervisor().getNome());

		String aux1;
		System.out.print("Data de inicio das inscrições(dd/MM/aaaa): ");
		try {
			aux1 = user.nextLine();
			aux.setDataInicioInscricao(new Date(format.parse(aux1).getTime()));
		} catch (Exception e) {
			System.out.print("Formato incorreto. Digite a data na forma (dd/MM/aaa): ");
			aux1 = user.nextLine();
			aux.setDataInicioInscricao(new Date(format.parse(aux1).getTime()));
		}
		System.out.println(format.format(aux.getDataInicioInscricao()));
		System.out.print("Data de termino das inscrições(dd/MM/aaaa): ");
		try {
			aux1 = user.nextLine();
			aux.setDataTerminoInscricao(new Date(format.parse(aux1).getTime()));
		} catch (Exception e) {
			System.out.print("Formato incorreto. Digite a data na forma (dd/MM/aaa): ");
			aux1 = user.nextLine();
			aux.setDataTerminoInscricao(new Date(format.parse(aux1).getTime()));
		}
		System.out.println(format.format(aux.getDataTerminoInscricao()));

		System.out.print("Data de realização do concurso(dd/MM/aaaa): ");
		try {
			aux1 = user.nextLine();
			aux.setDataConcurso(new Date(format.parse(aux1).getTime()));
		} catch (Exception e) {
			System.out.print("Formato incorreto. Digite a data na forma (dd/MM/aaa): ");
			aux1 = user.nextLine();
			aux.setDataConcurso(new Date(format.parse(aux1).getTime()));
		}
		System.out.println(format.format(aux.getDataConcurso()));
		System.out.print("Modalidade do concurso: ");
		aux.setModalidade(user.nextLine());
		System.out.print("Comissão organizadora: ");
		aux.setComissao(user.nextLine());
		System.out.println("Adicionar docentes a Banca.");
		for (int i = 0; i < docentes.size(); i++) {
			System.out.println(i + ": " + docentes.get(i).getNome());
		}
		while (entrada != 99) {
			System.out.print("Digite o codigo referente ao docente que deseja adicionar: ");
			entrada = user.nextInt();
			if (entrada < docentes.size() && !aux.getBanca().contains(docentes.get(entrada))) {
				aux.addBanca(docentes.get(entrada));
			} else if (entrada < docentes.size() && aux.getBanca().contains(docentes.get(entrada))) {
				System.out.println("Você já associou esse docente a esse concurso.");
			} else {
				System.out.println("Não foi encontrado o Docente correspondente. Tente novamente.");
			}
			System.out.println("Para sair, digite '99'.");
		}
		for (int i = 0; i < aux.getBanca().size(); i++) {
			System.out.println(i + ": " + aux.getBanca().get(i).getNome());
		}
		user.close();
		// A adição de participantes vai ficar em outra parte
		return aux;
	}

}
