package com.folhaPagamento;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int opcao = 0, quantidade = 0, numero = 1, aux, i, cadastrados = 0;
		double auxD, horas, minutos;
		Scanner user = new Scanner(System.in);
		Empregado[] empregado = new Empregado[100];
		System.out.println("Antes de come�ar, digite o dia, m�s e o ano, separados por uma quebra de linha:");
		int dia, mes, ano;

		System.out.print("Dia: ");
		dia = Integer.valueOf(user.next());

		System.out.print("M�s: ");
		mes = Integer.valueOf(user.next());
		mes--;
		System.out.print("Ano: ");
		ano = Integer.valueOf(user.next());

		Calendar data = new GregorianCalendar(ano, mes, dia);

		System.out.print("Data atual: ");
		System.out.println(
				data.get(Calendar.DAY_OF_MONTH) + "/" + (data.get(Calendar.MONTH) + 1) + "/" + data.get(Calendar.YEAR));

		System.out.println("Sistema Folha de Pagamento\nEscolha a op��o desejada:\n");
		while (opcao != 12) {
			System.out.println(
					"1: Adicionar um novo empregado\n2: Remover um empregado existente\n3: Lan�ar um cart�o de ponto");
			System.out.println(
					"4: Lan�ar um resultado de venda\n5: Lan�ar uma taxa de servi�o\n6: Alterar detalhes de um empregado");
			System.out.println(
					"7: Rodar folha de pagamento para hoje \n8: (em breve)Desfazer ou refazer alguma altera��o\n9: Mostrar agenda de pagamento \n10: Criar nova agenda de pagamento");
			System.out.println("11: Mostras dados de um usu�rio expecifico\n12: Sair");

			opcao = Integer.valueOf(user.next());
			if (opcao == 12) {
				System.out.println("At� Logo!");
				break;
			} else if (opcao == 1) {
				empregado[quantidade] = new Empregado();
				empregado[quantidade].numeroEmpregado = numero;
				System.out.print("Criar um novo empregado.\nDigite o nome: ");
				empregado[quantidade].nome = user.nextLine();
				empregado[quantidade].nome = user.nextLine();

				System.out.print("Digite o endere�o completo do empregado (Rua/Av, Num, Bairro e Cidade): ");
				empregado[quantidade].endere�o = user.nextLine();
				System.out.print("Digite o tipo do empregado (1: Horista; 2: Assalariado; 3: Comissionado): ");
				empregado[quantidade].tipo = user.nextInt();
				if (empregado[quantidade].tipo == 1) {
					System.out.println(
							"Empregado Horista\nPagamento � realizado todas �s sextas-feiras.\nQuanto ele recebe por hora trabalhada?");
					empregado[quantidade].salarioHorario = Double.valueOf(user.next());
					empregado[quantidade].diaPagamento = 1;
					System.out.print(
							"O empregado j� possui horas trabalhadas? Se negativo, digite 0, caso contrario, digite o numero de horas trabalhadas: ");
					empregado[quantidade].horasTabalhadas = Double.valueOf(user.next());
					System.out.print(
							"O empregado j� possui horas extras trabalhadas? Se negativo, digite 0, caso contrario, digite o numero de horas extras trabalhadas: ");
					empregado[quantidade].horasExtras = Double.valueOf(user.next());
				} else if (empregado[quantidade].tipo == 2) {
					System.out.print(
							"Funcionario Assalariado n�o comissionado\nPagamento � realizado no ultimo dia do m�s.\nDigite o salario do funcionario: ");
					empregado[quantidade].diaPagamento = 2;
					empregado[quantidade].salarioMensal = Double.valueOf(user.next());
				} else if (empregado[quantidade].tipo == 3) {
					System.out.print(
							"Funcionario Assalariado Comissionado\nPagamento realizando a cada 2 sextas-feiras.\nDigite o salario do funcionario: ");
					empregado[quantidade].salarioMensal = Double.valueOf(user.next());
					empregado[quantidade].salarioMensal = empregado[quantidade].salarioMensal / 2;

					System.out.print("Digite o percentual de comiss�o que o funcionario recebe (Exemplo: 0.5): ");
					empregado[quantidade].percentual = Double.valueOf(user.next());
					empregado[quantidade].diaPagamento = 3;
				}
				System.out.print(
						"Digite a forma como o funcionario prefere receber o salario (1: Cheque via Correios; 2: Cheque entregue em m�os; 3: Deposito bancario): ");
				empregado[quantidade].pagamento = Integer.valueOf(user.next());
				System.out.print(
						"Quase pronto...\nCaso o funcionario perten�a a algum sindicato, digite a taxa descontada pelo sindicato, caso contrario, digite 0 (Exemplo: 0.5): ");
				empregado[quantidade].taxaSindicato = Double.valueOf(user.next());
				System.out.println("\nFuncionario adicionado com sucesso.\n");
				quantidade++;
				numero++;
				cadastrados++;

			} else if (opcao == 2) {
				if (cadastrados > 0) {
					System.out.println("Remover um funcionario do sistema.");
					System.out.println("Funcionarios cadastrados atualmente:");
					for (i = 0; i < quantidade; i++) {
						if (empregado[i].nome != null) {
							System.out.println(empregado[i].numeroEmpregado + " : " + empregado[i].nome + "\n");
						}
					}
					System.out.print("Digite o codigo do funcionario desejado: ");
					aux = Integer.valueOf(user.next());
					for (i = 0; i < quantidade; i++) {
						if (aux == empregado[i].numeroEmpregado) {
							empregado[i].nome = null;
							empregado[i].endere�o = null;
							empregado[i].tipo = 0;
							empregado[i].salarioMensal = 0;
							empregado[i].percentual = 0;
							empregado[i].taxaSindicato = 0;
							empregado[i].salarioComissao = 0;
							empregado[i].salarioHorario = 0;
							empregado[i].horasTabalhadas = 0;
							empregado[i].diaPagamento = 0;
							empregado[i].pagamento = 0;
							cadastrados--;
							System.out.println("Removido com sucesso.");
						}
					}
				} else {
					System.out.println("N�o h� funcionarios cadastrados. Op��o inv�lida.\n");
				}

			} else if (opcao == 3) {

				if (cadastrados > 0) {
					System.out.println("Lan�ar um cart�o de ponto.");
					System.out.println("Funcionarios cadastrados atualmente:");
					for (i = 0; i < quantidade; i++) {
						if (empregado[i].nome != null) {
							System.out.println(empregado[i].numeroEmpregado + " : " + empregado[i].nome + "\n");
						}
					}
					System.out.print("Digite o codigo do funcionario desejado: ");
					aux = Integer.valueOf(user.next());
					for (i = 0; i < quantidade; i++) {
						if (aux == empregado[i].numeroEmpregado) {

							System.out.printf("Nome do empregado: %s\n", empregado[i].nome);
							if (empregado[i].tipo == 1) {
								System.out.print("Empregado Horista.\nDigite a quantidade de horas trabalhadas: ");
								horas = Double.valueOf(user.next());
								System.out.print("Digite a quantidade de minutos trabalhadas: ");
								minutos = Double.valueOf(user.next());

								horas += (minutos / 60);
								if (horas > 8) {
									horas = horas - 8;
									empregado[i].horasExtras += horas;
									empregado[i].horasTabalhadas += 8;
								} else {
									empregado[i].horasTabalhadas += aux;
								}
								i = quantidade;
							} else {
								System.out.println("Empregado n�o horista, op��o n�o dispon�vel.\n");
							}
						}
					}
				} else {
					System.out.println("N�o h� funcionarios cadastrados. Op��o inv�lida.\n");
				}

			} else if (opcao == 4) {
				if (cadastrados > 0) {
					System.out.println("Lan�ar uma venda.");
					System.out.println("Funcionarios cadastrados atualmente:");
					for (i = 0; i < quantidade; i++) {
						if (empregado[i].nome != null) {
							System.out.println(empregado[i].numeroEmpregado + " : " + empregado[i].nome + "\n");
						}
					}
					System.out.print("Digite o codigo do funcionario desejado: ");
					aux = Integer.valueOf(user.next());

					for (i = 0; i < quantidade; i++) {
						if (aux == empregado[i].numeroEmpregado) {
							if (empregado[i].tipo == 3) {
								System.out.print(
										"Empregado Assalariado Comissionado.\n Digite o valor da venda realizada (Ex.: 500.00): ");
								auxD = Double.valueOf(user.next());
								empregado[i].vendaMes += auxD;
								i = quantidade;
							} else {
								System.out.println("Empregado n�o comissionado, op��o n�o dispon�vel.\n");
							}
						}
					}
				} else {
					System.out.println("N�o h� funcionarios cadastrados. Op��o inv�lida.\n");
				}
			}

			else if (opcao == 5) {
				if (cadastrados > 0) {
					System.out.println("Lan�ar uma taxa de servi�o.");
					System.out.println("Funcionarios cadastrados atualmente:");
					for (i = 0; i < quantidade; i++) {
						if (empregado[i].nome != null) {
							System.out.println(empregado[i].numeroEmpregado + " : " + empregado[i].nome + "\n");
							System.out.print("Digite o codigo do funcionario desejado: ");
							aux = Integer.valueOf(user.next());
							System.out.print(
									"Descontar uma taxa de servi�o.\nDigite o valor em reais que deve ser descontado do salario do funcionario: ");
							auxD = Double.valueOf(user.next());
							empregado[i].salarioTotal -= auxD;
						}

					}
				}
			}

			else if (opcao == 6) {
				if (cadastrados > 0) {
					System.out.println("Alterar detalhes de um funcion�rio cadastrado.");
					System.out.println("Funcionarios cadastrados atualmente:");
					for (i = 0; i < quantidade; i++) {
						if (empregado[i].nome != null) {
							System.out.println(empregado[i].numeroEmpregado + " : " + empregado[i].nome + "\n");
						}
					}
					System.out.print("Digite o codigo do funcionario desejado: ");
					aux = Integer.valueOf(user.next());
					for (i = 0; i < quantidade; i++) {
						if (aux == empregado[i].numeroEmpregado && empregado[i].nome != null) {
							System.out.println("Nome do empregado: " + empregado[i].nome);
							System.out.print(
									"Digite o codigo da informa��o que deseja alterar (1: Nome; 2: Endere�o; 3: Tipo; 4: Forma de pagamento; 5: Sindicato): ");
							horas = Integer.valueOf(user.next());
							if (horas == 1) {
								System.out.print("Digite o nome do funcionario: ");
								empregado[i].nome = user.nextLine();
								empregado[i].nome = user.nextLine();
							} else if (horas == 2) {
								System.out.print("Digite o endere�o do funcionario: ");
								empregado[i].endere�o = user.nextLine();
								empregado[i].endere�o = user.nextLine();
							} else if (horas == 3) {
								System.out.print(
										"Digite o tipo do empregado (1: Horista; 2: Assalariado; 3: Comissionado): ");
								empregado[i].tipo = user.nextInt();
								if (empregado[i].tipo == 3) {
									empregado[i].horasTabalhadas = 0;
									empregado[i].horasExtras = 0;
									empregado[i].vendaMes = 0;
									empregado[i].salarioMensal = 0;
									System.out.println(
											"Assalariado Comissionado. Algumas informa��es precisam ser adicionadas:");
									System.out.print(
											"Pagamento agora ser� realizando a cada 2 sextas-feiras.\nDigite o salario do funcionario: ");
									empregado[i].salarioMensal = Double.valueOf(user.next());
									empregado[i].salarioMensal = empregado[i].salarioMensal / 2;

									System.out.print(
											"Digite o percentual de comiss�o que o funcionario recebe (Exemplo: 0.5): ");
									empregado[i].percentual = Double.valueOf(user.next());
									empregado[i].diaPagamento = 3;
								} else if (empregado[i].tipo == 2) {
									empregado[i].horasTabalhadas = 0;
									empregado[i].horasExtras = 0;
									empregado[i].vendaMes = 0;
									empregado[i].salarioMensal = 0;
									System.out.println("Assalariado. Algumas informa��es precisam ser adicionadas:");
									System.out.print(
											"Pagamento agora ser� realizado no ultimo dia util do m�s.\nDigite o salario do funcionario: ");
									empregado[i].salarioMensal = Double.valueOf(user.next());
									empregado[i].diaPagamento = 2;
								} else if (empregado[i].tipo == 1) {
									empregado[i].horasTabalhadas = 0;
									empregado[i].horasExtras = 0;
									empregado[i].vendaMes = 0;
									empregado[i].salarioMensal = 0;
									System.out.println("Horista. Algumas informa��es precisam ser adicionadas:");
									System.out.print(
											"Pagamento agora ser� realizado toda sexta-feira.\nDigite quanto o funcionario recebe por hora trabalhada: ");
									empregado[quantidade].salarioHorario = Double.valueOf(user.next());
									empregado[quantidade].diaPagamento = 1;
								}

							} else if (horas == 4) {
								System.out.print(
										"Digite a forma de pagamento preferida pelo funcionario (1: Cheque via Correios; 2: Cheque entregue em m�os; 3: Deposito bancario): ");
								empregado[i].pagamento = user.nextInt();
							} else if (horas == 5) {
								System.out.print(
										"Se o funcionario pertence a um sindicato, digite a taxa que o mesmo cobra. Caso contr�rio, digite 0: ");
								empregado[i].taxaSindicato = Double.valueOf(user.next());
							}
						} else if (empregado[i].nome == null) {
							System.out.println("Funcionario n�o encontrado.");
						}
					}
				} else {
					System.out.println("N�o h� funcionarios cadastrados. Op��o inv�lida.\n");
				}

			} else if (opcao == 7) {
				System.out.println("Rodar a folha de pagamento");
				if (data.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
					System.out.println("Sexta-feira\nEmpregados que recebem hoje:");
					for (i = 0; i < quantidade; i++) {
						if (empregado[i].tipo == 1 && empregado[i].nome != null) {
							System.out.println(empregado[i].numeroEmpregado + " : " + empregado[i].nome + "\n");
							empregado[i].salarioTotal += ((empregado[i].salarioHorario * empregado[i].horasTabalhadas
									+ empregado[i].horasExtras * 1.5 * empregado[i].salarioHorario));
							empregado[i].salarioTotal += ((empregado[i].vendaMes * empregado[i].percentual / 100
									+ empregado[i].salarioMensal));
							empregado[i].salarioTotal -= (empregado[i].salarioTotal * empregado[i].taxaSindicato) / 100;
							System.out.printf("Salario a ser recebido: R$ %.1f\n", empregado[i].salarioTotal);
							System.out.println("Valor cobrada pelo sindicato: R$ " + (empregado[i].salarioTotal * empregado[i].taxaSindicato) / 100 + "\n");

							empregado[i].salarioTotal = 0;
							empregado[i].horasTabalhadas = 0;
							empregado[i].horasExtras = 0;
							empregado[i].salarioComissao = 0;
							empregado[i].vendaMes = 0;
							if (empregado[i].pagamento == 1) {
								System.out.println("O pagamento deve ser feito com cheque via correios.");
							} else if (empregado[i].pagamento == 2) {
								System.out.println("O pagamento deve ser feito com cheque em m�os.");
							} else if (empregado[i].pagamento == 3) {
								System.out.println("O pagamento deve ser feito via deposito bancario.");
							}
						}
						if (empregado[i].tipo == 3 && empregado[i].nome != null
								&& (data.get(Calendar.DAY_OF_MONTH) >= 8 && data.get(Calendar.DAY_OF_MONTH) <= 14)) {
							System.out.println("Segunda sexta feira do m�s");
							System.out.println(empregado[i].numeroEmpregado + " : " + empregado[i].nome);
							empregado[i].salarioTotal += ((empregado[i].salarioHorario * empregado[i].horasTabalhadas
									+ empregado[i].horasExtras * 1.5 * empregado[i].salarioHorario));
							empregado[i].salarioTotal += ((empregado[i].vendaMes * empregado[i].percentual / 100
									+ empregado[i].salarioMensal));
							empregado[i].salarioTotal -= (empregado[i].salarioTotal * empregado[i].taxaSindicato) / 100;
							System.out.printf("Salario a ser recebido: R$ %.1f\n", empregado[i].salarioTotal);
							System.out.println("Valor cobrada pelo sindicato: R$ "
									+ (empregado[i].salarioTotal * empregado[i].taxaSindicato) / 100 + "\n");
							empregado[i].salarioTotal = 0;
							empregado[i].horasTabalhadas = 0;
							empregado[i].horasExtras = 0;
							empregado[i].salarioComissao = 0;
							empregado[i].vendaMes = 0;
							if (empregado[i].pagamento == 1) {
								System.out.println("O pagamento deve ser feito com cheque via correios.");
							} else if (empregado[i].pagamento == 2) {
								System.out.println("O pagamento deve ser feito com cheque em m�os.");
							} else if (empregado[i].pagamento == 3) {
								System.out.println("O pagamento deve ser feito via deposito bancario.");
							}

						} else if (empregado[i].tipo == 3 && empregado[i].nome != null
								&& (data.get(Calendar.DAY_OF_MONTH) >= 25 && data.get(Calendar.DAY_OF_MONTH) <= 31)) {
							System.out.println("Quarta sexta feira do m�s.\n");
							System.out.println(empregado[i].numeroEmpregado + " : " + empregado[i].nome);
							empregado[i].salarioTotal += ((empregado[i].salarioHorario * empregado[i].horasTabalhadas
									+ empregado[i].horasExtras * 1.5 * empregado[i].salarioHorario));
							empregado[i].salarioTotal += ((empregado[i].vendaMes * empregado[i].percentual / 100
									+ empregado[i].salarioMensal));
							empregado[i].salarioTotal -= (empregado[i].salarioTotal * empregado[i].taxaSindicato) / 100;
							System.out.printf("Salario a ser recebido: R$ %.1f\n", empregado[i].salarioTotal);
							System.out.println("Valor cobrada pelo sindicato: R$ "
									+ (empregado[i].salarioTotal * empregado[i].taxaSindicato) / 100 + "\n");

							empregado[i].salarioTotal = 0;
							empregado[i].horasTabalhadas = 0;
							empregado[i].horasExtras = 0;
							empregado[i].salarioComissao = 0;
							empregado[i].vendaMes = 0;
							if (empregado[i].pagamento == 1) {
								System.out.println("O pagamento deve ser feito com cheque via correios.");
							} else if (empregado[i].pagamento == 2) {
								System.out.println("O pagamento deve ser feito com cheque em m�os.");
							} else if (empregado[i].pagamento == 3) {
								System.out.println("O pagamento deve ser feito via deposito bancario.");
							}

						}

					}
				}
				
				else if (data.get(Calendar.DAY_OF_MONTH) == 31 && data.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY
						&& data.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
					System.out.println("Ultimo dia util do m�s.\nEmpregados que recebem hoje:");
					for (i = 0; i < quantidade; i++) {
						if (empregado[i].tipo == 2 && empregado[i].nome != null) {
							System.out.println(empregado[i].numeroEmpregado + " : " + empregado[i].nome);
							empregado[i].salarioTotal += ((empregado[i].salarioHorario * empregado[i].horasTabalhadas
									+ empregado[i].horasExtras * 1.5 * empregado[i].salarioHorario));
							empregado[i].salarioTotal += ((empregado[i].vendaMes * empregado[i].percentual / 100
									+ empregado[i].salarioMensal));
							empregado[i].salarioTotal -= (empregado[i].salarioTotal * empregado[i].taxaSindicato) / 100;
							System.out.printf("Salario a ser recebido: R$ %.1f\n", empregado[i].salarioTotal);
							System.out.println("Valor cobrada pelo sindicato: R$ "
									+ (empregado[i].salarioTotal * empregado[i].taxaSindicato) / 100 + "\n");

							empregado[i].salarioTotal = 0;
							empregado[i].horasTabalhadas = 0;
							empregado[i].horasExtras = 0;
							empregado[i].salarioComissao = 0;
							empregado[i].vendaMes = 0;
							
							if (empregado[i].pagamento == 1) {
								System.out.println("O pagamento deve ser feito com cheque via correios.");
							} else if (empregado[i].pagamento == 2) {
								System.out.println("O pagamento deve ser feito com cheque em m�os.");
							} else if (empregado[i].pagamento == 3) {
								System.out.println("O pagamento deve ser feito via deposito bancario.");
							}
						}

					}
				} else if(data.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY){
					for (i = 0; i < quantidade; i++) {
						if (empregado[i].diaPagamento == 4 && empregado[i].dia == data.get(Calendar.DAY_OF_MONTH)) {
							System.out.println("Como hoje � dia " + data.get(Calendar.DAY_OF_MONTH)
									+ ", o empregado deve receber pagamento.");
							System.out.println(empregado[i].numeroEmpregado + " : " + empregado[i].nome);
							empregado[i].salarioTotal += ((empregado[i].salarioHorario * empregado[i].horasTabalhadas
									+ empregado[i].horasExtras * 1.5 * empregado[i].salarioHorario));
							empregado[i].salarioTotal += ((empregado[i].vendaMes * empregado[i].percentual / 100
									+ empregado[i].salarioMensal));
							empregado[i].salarioTotal -= (empregado[i].salarioTotal * empregado[i].taxaSindicato) / 100;
							System.out.printf("Salario a ser recebido: R$ %.1f\n", empregado[i].salarioTotal);
							System.out.println("Valor cobrada pelo sindicato: R$ "
									+ (empregado[i].salarioTotal * empregado[i].taxaSindicato) / 100 + "\n");
							empregado[i].salarioTotal = 0;
							empregado[i].horasTabalhadas = 0;
							empregado[i].horasExtras = 0;
							empregado[i].salarioComissao = 0;
							empregado[i].vendaMes = 0;
							if (empregado[i].pagamento == 1) {
								System.out.println("O pagamento deve ser feito com cheque via correios.");
							} else if (empregado[i].pagamento == 2) {
								System.out.println("O pagamento deve ser feito com cheque em m�os.");
							} else if (empregado[i].pagamento == 3) {
								System.out.println("O pagamento deve ser feito via deposito bancario.");
							}

						}

					}

				}
			}

			else if (opcao == 9) {
				if (cadastrados > 0) {
					System.out.println("Agenda de Pagamento");
					System.out.print(
							"Digite a op�ao desejada (1: Funcionarios pagos toda sexta feira; 2: Funcionarios pagos a cada 2 sextas-feiras;\n3: Funcionarios pagos no ultimo dia util do m�s; 4: Funcionarios pagos em outras datas): ");
					aux = Integer.valueOf(user.next());
					if (aux == 1) {
						for (i = 0; i < quantidade; i++) {
							if (aux == empregado[i].diaPagamento && empregado[i].nome != null) {
								System.out.println(empregado[i].numeroEmpregado + " : " + empregado[i].nome + "\n");
								if (empregado[i].pagamento == 1) {
									System.out.println("O pagamento deve ser feito com cheque via correios.");
								} else if (empregado[i].pagamento == 2) {
									System.out.println("O pagamento deve ser feito com cheque em m�os.");
								} else if (empregado[i].pagamento == 3) {
									System.out.println("O pagamento deve ser feito via deposito bancario.");
								}
							}
						}
					} else if (aux == 2) {
						for (i = 0; i < quantidade; i++) {
							if (aux == empregado[i].diaPagamento && empregado[i].nome != null) {
								System.out.println(empregado[i].numeroEmpregado + " : " + empregado[i].nome + "\n");
								if (empregado[i].pagamento == 1) {
									System.out.println("O pagamento deve ser feito com cheque via correios.");
								} else if (empregado[i].pagamento == 2) {
									System.out.println("O pagamento deve ser feito com cheque em m�os.");
								} else if (empregado[i].pagamento == 3) {
									System.out.println("O pagamento deve ser feito via deposito bancario.");
								}
							}
						}
					} else if (aux == 3) {
						for (i = 0; i < quantidade; i++) {
							if (aux == empregado[i].diaPagamento && empregado[i].nome != null) {
								System.out.println(empregado[i].numeroEmpregado + " : " + empregado[i].nome + "\n");
								if (empregado[i].pagamento == 1) {
									System.out.println("O pagamento deve ser feito com cheque via correios.");
								} else if (empregado[i].pagamento == 2) {
									System.out.println("O pagamento deve ser feito com cheque em m�os.");
								} else if (empregado[i].pagamento == 3) {
									System.out.println("O pagamento deve ser feito via deposito bancario.");
								}
							}
						}
					} else if (aux == 4) {
						for (i = 0; i < quantidade; i++) {
							if (aux == empregado[i].diaPagamento && empregado[i].nome != null) {
								System.out.println(empregado[i].numeroEmpregado + " : " + empregado[i].nome + "\n");
								System.out.println("Dia de pagamento: " + empregado[i].dia);
								if (empregado[i].pagamento == 1) {
									System.out.println("O pagamento deve ser feito com cheque via correios.");
								} else if (empregado[i].pagamento == 2) {
									System.out.println("O pagamento deve ser feito com cheque em m�os.");
								} else if (empregado[i].pagamento == 3) {
									System.out.println("O pagamento deve ser feito via deposito bancario.");
								}
								
							}
						}
					}
				} else {
					System.out.println("N�o h� funcionarios cadastrados. Op��o inv�lida.\n");
				}
			}else if (opcao == 10) {
				if (cadastrados > 0) {
					System.out.println("Alterar Pagamento");
					System.out.println("Funcionarios cadastrados atualmente:");
					for (i = 0; i < quantidade; i++) {
						if (empregado[i].nome != null) {
							System.out.println(empregado[i].numeroEmpregado + " : " + empregado[i].nome + "\n");
						}
					}
					System.out.print("Digite o codigo do funcionario desejado: ");
					aux = Integer.valueOf(user.next());
					for (i = 0; i < quantidade; i++) {
						if (aux == empregado[i].numeroEmpregado && empregado[i].nome != null) {
							System.out.println("Nome do empregado: " + empregado[i].nome);
							System.out.print(
									"Digite a op��o desejada (1: Alterar para recebimento toda sexta-feira; 2: Alterar para recebimento a cada duas sextas-feiras;\n3: Alterar para recebimento no ultimo dia util do m�s; 4: Alterar para qualquer dia do m�s):");
							empregado[i].diaPagamento = Integer.valueOf(user.next());
							if (empregado[i].diaPagamento == 4) {
								System.out.print("Digite o dia em que o funcionario deve ser pago: ");
								empregado[i].dia = Integer.valueOf(user.next());
							}
						}
					}

				} else {
					System.out.println("N�o h� funcionarios cadastrados. Op��o inv�lida.\n");
				}
			}
			
			else if (opcao == 11) {
				if (cadastrados > 0) {
					System.out.println("Visualizar funcionarios cadastrados.");
					System.out.println("Funcionarios cadastrados atualmente:");
					for (i = 0; i < quantidade; i++) {
						if (empregado[i].nome != null) {
							System.out.println(empregado[i].numeroEmpregado + " : " + empregado[i].nome + "\n");
						}
					}
					System.out.print("Digite o codigo do funcionario desejado: ");
					aux = Integer.valueOf(user.next());
					for (i = 0; i < quantidade; i++) {
						if (aux == empregado[i].numeroEmpregado) {

							System.out.printf("Nome do empregado: %s\n", empregado[i].nome);
							System.out.printf("Endere�o do empregado: %s\n", empregado[i].endere�o);
							if (empregado[i].tipo == 1) {
								System.out.println("Funcionario Horista.");
								System.out.println("Horas trabalhadas: " + empregado[i].horasTabalhadas);
								System.out.println("Horas extras trabalhadas: " + empregado[i].horasExtras);
								System.out.println("Taxa descontada pelo sindicato: " + empregado[i].taxaSindicato + "%");
								
							} else if (empregado[i].tipo == 2) {
								System.out.println("Funcionario Assalariado.");
								System.out.println("Valor do Salario Mensal: R$" + empregado[i].salarioMensal);
								System.out.println("Taxa descontada pelo sindicato: " + empregado[i].taxaSindicato + "%");
							} else if (empregado[i].tipo == 3) {
								System.out.println("Funcionario Assalariado Comissionado.");
								System.out.println("Valor do Salario Mensal: R$" + empregado[i].salarioMensal);
								System.out.println("Taxa descontada pelo sindicato: " + empregado[i].taxaSindicato + "%");
								System.out.println("Percentual recebido por cada venda: " + empregado[i].percentual + "%");
							}
							
							if (empregado[i].pagamento == 1) {
								System.out.println("O pagamento deve ser feito com cheque via correios.\n");
							} else if (empregado[i].pagamento == 2) {
								System.out.println("O pagamento deve ser feito com cheque em m�os.\n");
							} else if (empregado[i].pagamento == 3) {
								System.out.println("O pagamento deve ser feito via deposito bancario.\n");
							}
							
							
							
						}
					}
					
					

				} else {
					System.out.println("N�o h� funcionarios cadastrados. Op��o inv�lida.\n");
				}
			}

			else if (opcao == 11) {
				System.out.print("Digite o codigo do funcionario: ");
				aux = Integer.valueOf(user.next());
				for (i = 0; i < quantidade; i++) {
					if (aux == empregado[i].numeroEmpregado) {

						System.out.printf("Nome do empregado: %s\n", empregado[i].nome);
						System.out.printf("Endere�o do empregado: %s\n", empregado[i].endere�o);
						System.out.println("Tipo: " + empregado[i].tipo);
						System.out.println("Valor do Salario Mensal: R$" + empregado[i].salarioMensal);
						System.out.println("Percentual: " + empregado[i].percentual + "%");
						System.out.println("Taxa sindicato: " + empregado[i].taxaSindicato + "%");
						System.out.println("Horas trabalhadas: " + empregado[i].horasTabalhadas);
						System.out.println("Horas extras: " + empregado[i].horasExtras);
						System.out.println("Salario Total: " + empregado[i].salarioTotal);
						System.out.println("\n");
					}
				}
			}
			else {
				System.out.println("Op��o inv�lida. Tente novamente.\n");
			}
		}
	}
}