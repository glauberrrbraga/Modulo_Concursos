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

	Concurso() {
		concursosCadastrados++;
		this.supervisor = new Servidor();
		this.banca = new ArrayList<>();
		this.edital = new Edital();
	}

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

	public void publicarEdital() throws IOException {
		Scanner user = new Scanner(System.in);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Publicar Edital.");
		System.out.print("Digite detalhes do edital: ");
		this.edital.setDetalhes(user.nextLine());
		FileWriter edital = new FileWriter(new File("C:\\Users\\Glauber Braga\\Desktop\\Edital.txt"));
		// Ele cria um arquivo TXT
		PrintWriter gravarArq = new PrintWriter(edital);
		// Ele agora vai escrever no arquivo aberto
		gravarArq.println(this.getNome());
		gravarArq.println("Id: " + this.getId());
		gravarArq.println("Comissao organizadora: " + this.getComissao());
		gravarArq.println("Taxa de incri��o: " + this.getValorInscricao());
		gravarArq.println(this.edital.getDetalhes());
		gravarArq.println("Data de realizacao do concurso: ");
		gravarArq.println(format.format(this.getDataConcurso()));
		gravarArq.println("Data de inicio das inscricoes: ");
		gravarArq.println(format.format(this.getDataInicioInscricao()));
		gravarArq.println("Data de termino das inscricoes: ");
		gravarArq.println(format.format(this.getDataTerminoInscricao()));
		edital.close();
	}

	public void editarConcurso(ArrayList<Docente> docentes) throws ParseException {
		Scanner user = new Scanner(System.in);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("ID do concurso: " + this.getId());
		System.out.println("Digite a opção desejada:\n1: Editar datas\n2: Editar banca");
		int entrada = user.nextInt();
		String aux1;
		if (entrada == 1) {
			System.out.println(
					"1: Editar data de realização do concurso\n2: Editar data de termino das inscrições\n3: Editar data de inicio da inscrições");
			entrada = user.nextInt();
			user.nextLine();

			GregorianCalendar data = new GregorianCalendar();
			String date = format.format(data.getTime());
			Date dateaux = new Date(format.parse(date).getTime());

			if (entrada == 1) {
				System.out.print("Data de realização do concurso(dd/MM/aaaa): ");
				try {
					aux1 = user.nextLine();
					Date date2 = new Date(format.parse(aux1).getTime());

					if (dateaux.compareTo(date2) > 0) {
						System.out.println("Essa data já passou");
					} else if (dateaux.compareTo(date2) == 0) {
						System.out.println("Você não pode marcar o concurso para hoje");
					} else {
						this.setDataConcurso(date2);
					}
				} catch (Exception e) {
					System.out.print("Formato incorreto. Digite a data na forma (dd/MM/aaa): ");
					aux1 = user.nextLine();
					Date date2 = new Date(format.parse(aux1).getTime());

					if (dateaux.compareTo(date2) > 0) {
						System.out.println("Essa data já passou");
					} else if (dateaux.compareTo(date2) == 0) {
						System.out.println("Você não pode marcar o concurso para hoje");
					} else {
						this.setDataConcurso(date2);
					}
				}

			} else if (entrada == 2) {
				System.out.print("Data de termino das inscrições(dd/MM/aaaa): ");
				try {
					aux1 = user.nextLine();
					Date newdate = new Date(format.parse(aux1).getTime());

					if (newdate.compareTo(this.getDataInicioInscricao()) < 0) {
						System.out.println("Essa data é anterior à data de início de inscrição");
					} else if (newdate.compareTo(this.getDataInicioInscricao()) == 0) {
						System.out.println("Essa é a mesma data de inscrição");
					} else if (newdate.compareTo(this.getDataConcurso()) >= 0) {
						System.out.println("essa data é posterior a data de realização do concurso");
					} else {
						this.setDataTerminoInscricao(newdate);
					}
				} catch (Exception e) {
					System.out.print("Formato incorreto. Digite a data na forma (dd/MM/aaa): ");
					aux1 = user.nextLine();
					Date newdate = new Date(format.parse(aux1).getTime());

					if (newdate.compareTo(this.getDataInicioInscricao()) < 0) {
						System.out.println("Essa data é anterior à data de início de inscrição");
					} else if (newdate.compareTo(this.getDataInicioInscricao()) == 0) {
						System.out.println("Essa é a mesma data de inscrição");
					} else if (newdate.compareTo(this.getDataConcurso()) >= 0) {
						System.out.println("essa data é igual/posterior a data de realização do concurso");
					} else {
						this.setDataTerminoInscricao(newdate);
					}
				}

			} else if (entrada == 3) {
				System.out.print("Data de inicio das inscrições(dd/MM/aaaa): ");
				try {
					aux1 = user.nextLine();
					Date newdate = new Date(format.parse(aux1).getTime());

					if (newdate.compareTo(dateaux) < 0) {
						System.out.println("essa data já passou");
					} else if (newdate.compareTo(this.getDataConcurso()) > 0) {
						System.out.println("essa data é posterior à data de realização do concruso");
					} else if (newdate.compareTo(this.getDataTerminoInscricao()) > 0) {
						System.out.println("essa data é posterior ao termino das inscrições");
					} else {
						this.setDataInicioInscricao(newdate);
					}

				} catch (Exception e) {
					System.out.print("Formato incorreto. Digite a data na forma (dd/MM/aaa): ");
					aux1 = user.nextLine();
					Date newdate = new Date(format.parse(aux1).getTime());

					if (newdate.compareTo(dateaux) < 0) {
						System.out.println("essa data já passou");
					} else if (newdate.compareTo(this.getDataConcurso()) > 0) {
						System.out.println("essa data é posterior à data de realização do concruso");
					} else if (newdate.compareTo(this.getDataTerminoInscricao()) > 0) {
						System.out.println("essa data é posterior ao termino das inscrições");
					} else {
						this.setDataInicioInscricao(newdate);
					}

				}

			}

		} else if (entrada == 2) {
			System.out.println("Editar a banca. \n");
			System.out.println("1: Adicionar docentes a banca\n2: Remover docentes da banca\n");
			System.out.print("Opção deejada: ");
			entrada = user.nextInt();
			if (entrada == 1) {
				System.out.println("Adicionar docentes a banca.");
				for (int i = 0; i < docentes.size(); i++) {
					System.out.println(i + ": " + docentes.get(i).getNome());
				}
				System.out.print("Selecione o docente que deseja adicionar a banca: ");
				entrada = user.nextInt();

				if (entrada < docentes.size() && !this.getBanca().contains(docentes.get(entrada))) {
					this.addBanca(docentes.get(entrada));
				} else if (entrada < docentes.size() && this.getBanca().contains(docentes.get(entrada))) {
					System.out.println("Você associou esse docente a esse concurso.");
				} else {
					System.out.println("Não foi encontrado o Docente correspondente. Tente novamente.");
				}

			} else if (entrada == 2) {
				for (int i = 0; i < this.getBanca().size(); i++) {
					System.out.println(i + ": " + docentes.get(i).getNome());
				}
				System.out.print("Digite o docente que deseja remover da banca: ");
				entrada = user.nextInt();

				if (entrada < docentes.size()) {
					this.getBanca().remove(entrada);
				} else {
					System.out.println("Não foi encontrado o Docente correspondente. Tente novamente.");
				}

			}
		}
	}

	public static Concurso agendamento(ArrayList<Docente> docentes, ArrayList<Servidor> servidores)
			throws ParseException, IOException {
		System.out.println("Agendamento.\nAntes, é necessário cadastrar um novo concurso");
		Concurso aux = new Concurso();
		int entrada;
		Scanner user = new Scanner(System.in);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		aux.setId(Concurso.getConcursosCadastrados());
		System.out.println("ID do Concurso: " + aux.getId());
		System.out.print("Nome: ");
		aux.setNome(user.nextLine());
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

		GregorianCalendar data = new GregorianCalendar();
		String date = format.format(data.getTime());
		Date dateaux = new Date(format.parse(date).getTime());
		String aux1;
		boolean auxdate = false;

		System.out.print("Data de inicio das inscrições(dd/MM/aaaa): ");
		do {
			try {
				aux1 = user.nextLine();
				Date newdate = new Date(format.parse(aux1).getTime());
				if (newdate.compareTo(dateaux) < 0) {
					System.out.println("essa data já passou");
					auxdate = false;
				} else {
					aux.setDataInicioInscricao(newdate);
					auxdate = true;
				}
			} catch (Exception e) {
				System.out.print("Formato incorreto. Digite a data na forma (dd/MM/aaa): ");
				aux1 = user.nextLine();
				Date newdate = new Date(format.parse(aux1).getTime());
				if (newdate.compareTo(dateaux) < 0) {
					System.out.println("essa data já passou");
					auxdate = false;
				} else {
					aux.setDataInicioInscricao(newdate);
					auxdate = true;
				}
			}
		} while (!auxdate);
		System.out.println(format.format(aux.getDataInicioInscricao()));
		System.out.print("Data de termino das inscrições(dd/MM/aaaa): ");
		do {
			try {
				aux1 = user.nextLine();
				Date newdate = new Date(format.parse(aux1).getTime());
				if (newdate.compareTo(aux.getDataInicioInscricao()) <= 0) {
					System.out.println("essa data é anterior/igual a de inicio das inscricoes");
					auxdate = false;
				} else {
					aux.setDataTerminoInscricao(newdate);
					auxdate = true;
				}
			} catch (Exception e) {
				System.out.print("Formato incorreto. Digite a data na forma (dd/MM/aaa): ");
				aux1 = user.nextLine();
				Date newdate = new Date(format.parse(aux1).getTime());
				if (newdate.compareTo(aux.getDataInicioInscricao()) <= 0) {
					System.out.println("essa data é anterior/igual a de inicio das inscricoes");
					auxdate = false;
				} else {
					aux.setDataTerminoInscricao(newdate);
					auxdate = true;
				}
			}
		} while (!auxdate);
		System.out.println(format.format(aux.getDataTerminoInscricao()));
		System.out.print("Data de realização do concurso(dd/MM/aaaa): ");
		do {
			try {
				aux1 = user.nextLine();
				Date newdate = new Date(format.parse(aux1).getTime());
				if (newdate.compareTo(aux.getDataTerminoInscricao()) <= 0) {
					System.out.println("essa data é anterior/igual a data de termino das inscricoes");
					auxdate = false;
				} else {
					aux.setDataConcurso(newdate);
					auxdate = true;
				}
			} catch (Exception e) {
				System.out.print("Formato incorreto. Digite a data na forma (dd/MM/aaa): ");
				aux1 = user.nextLine();
				Date newdate = new Date(format.parse(aux1).getTime());
				if (newdate.compareTo(aux.getDataTerminoInscricao()) <= 0) {
					System.out.println("essa data é anterior/igual a data de termino das inscricoes");
					auxdate = false;
				} else {
					aux.setDataConcurso(newdate);
					auxdate = true;
				}
			}
		} while (!auxdate);
		System.out.println(format.format(aux.getDataConcurso()));
		System.out.print("Modalidade do concurso: ");
		aux.setModalidade(user.nextLine());
		System.out.print("Comissao organizadora: ");
		aux.setComissao(user.nextLine());
		System.out.print("Valor da inscrição: ");
		aux.setValorInscricao(user.nextDouble());
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
				System.out.println("Você associou esse docente a esse concurso.");
			} else {
				System.out.println("Não foi encontrado o Docente correspondente. Tente novamente.");
			}
			System.out.println("Para sair, digite '99'.");
			entrada = user.nextInt();
		}
		for (int i = 0; i < aux.getBanca().size(); i++) {
			System.out.println(i + ": " + aux.getBanca().get(i).getNome());
		}
		aux.publicarEdital();
		// A adição de participantes vai ficar em outra parte
		return aux;
	}

}
