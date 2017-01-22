package com.folhaPagamento;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int opcao = 0, quantidade = 0, numero = 1, aux, i, horas, cadastrados = 0, entrada, saida;
		double auxD;
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
		System.out.println(data.get(Calendar.DAY_OF_MONTH) + "/" + (data.get(Calendar.MONTH) + 1) + "/" + data.get(Calendar.YEAR));
	
		System.out.println("Sistema Folha de Pagamento\nEscolha a op��o desejada:\n");
		while(opcao!=12)
		{
			System.out.println("1: Adicionar um novo empregado\n2: Remover um empregado existente\n3: Lan�ar um cart�o de ponto");
			System.out.println("4: (beta)Lan�ar um resultado de venda\n5: (beta)Lan�ar uma taxa de servi�o\n6: Alterar detalhes de um empregado");
			System.out.println("7: (beta)Rodar folha de pagamento para hoje \n8: (em breve)Desfazer ou refazer alguma altera��o\n9: (beta)Mostrar agenda de pagamento \n10: (em breve)Criar nova agenda de pagamento");
			System.out.println("11: Vizualizar detalhes sobre um funcionario expecifico\n12: Sair");
			
			opcao = Integer.valueOf(user.next());
			if(opcao == 12)
			{
				System.out.println("At� Logo!");
				break;
			}
			else if(opcao == 1)
			{
				empregado[quantidade] = new Empregado();
				empregado[quantidade].numeroEmpregado = numero;						
				System.out.print("Criar um novo empregado.\nDigite o nome: ");										
				empregado[quantidade].nome = user.nextLine();
				empregado[quantidade].nome = user.nextLine();

				System.out.print("Digite o endere�o completo do empregado (Rua/Av, Num, Bairro e Cidade): ");
				empregado[quantidade].endere�o = user.nextLine();
				System.out.print("Digite o tipo do empregado (1: Horista; 2: Assalariado; 3: Comissionado): ");
				empregado[quantidade].tipo = user.nextInt();
				if(empregado[quantidade].tipo == 1){
					System.out.println("Empregado Horista\nPagamento � realizado todas �s sextas-feiras");
					empregado[quantidade].diaPagamento = 1;
					System.out.print("O empregado j� possui horas trabalhadas? Se negativo, digite 0, caso contrario, digite o numero de horas trabalhadas: ");
					empregado[quantidade].horasTabalhadas = Integer.valueOf(user.next());
					System.out.print("O empregado j� possui horas extras trabalhadas? Se negativo, digite 0, caso contrario, digite o numero de horas extras trabalhadas: ");
					empregado[quantidade].horasExtras = Integer.valueOf(user.next());
				}
				else if (empregado[quantidade].tipo == 2) {
					System.out.print("Funcionario Assalariado n�o comissionado\nPagamento � realizado a cada 2 sextas-feiras.\nDigite o salario do funcionario: ");
					empregado[quantidade].diaPagamento = 2;
					empregado[quantidade].salarioMensal = Double.valueOf(user.next());
				}
				else if (empregado[quantidade].tipo == 3)
				{
					System.out.print("Funcionario Assalariado Comissionado\nPagamento realizando sempre no ultimo dia do m�s\nDigite o salario do funcionario: ");
					empregado[quantidade].salarioMensal = Double.valueOf(user.next());
					System.out.print("Digite o percentual de comiss�o que o funcionario recebe (Exemplo: 0.5): ");
					empregado[quantidade].percentual = Double.valueOf(user.next());
					empregado[quantidade].diaPagamento = 3;
				}
				System.out.print("Digite a forma como o funcionario prefere receber o salario (1: Cheque via Correios; 2: Cheque entregue em m�os; 3: Deposito bancario): ");
				empregado[quantidade].pagamento = Integer.valueOf(user.next());
				System.out.print("Quase pronto...\nCaso o funcionario perten�a a algum sindicato, digite a taxa descontada pelo sindicato, caso contrario, digite 0 (Exemplo: 0.5): ");
				empregado[quantidade].taxaSindicato = Double.valueOf(user.next());
				System.out.println("\nFuncionario adicionado com sucesso.\n");
				quantidade++; 
				numero++;
				cadastrados++;
				
				
			}
			else if (opcao == 2) {
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
				}
				else{
					System.out.println("N�o h� funcionarios cadastrados. Op��o inv�lida.\n");
				}
				

			}
			else if (opcao == 3) {
				
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
								System.out.print("Empregado Horista.\nDigite a hora de entrada: ");
								entrada = Integer.valueOf(user.next());
								System.out.print("Digite a hora de sa�da: ");
								saida = Integer.valueOf(user.next());
								
								horas = saida - entrada;
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
								System.out.print("Empregado Assalariado Comissionado.\n Digite o valor da venda realizada (Ex.: 500.00): ");
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
						}
					}
					System.out.print("Digite o codigo do funcionario desejado: ");
					aux = Integer.valueOf(user.next());
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
							System.out.println("Nome do empregado: "+ empregado[i].nome);
							System.out.print("Digite o codigo da informa��o que deseja alterar (1: Nome; 2: Endere�o; 3: Tipo; 4: Forma de pagamento; 5: Sindicato): ");
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
								System.out.print("Digite o tipo do empregado (1: Horista; 2: Assalariado; 3: Comissionado): ");
								empregado[i].tipo = user.nextInt();
							} else if (horas == 4) {
								System.out.print("Digite a forma de pagamento preferida pelo funcionario (1: Cheque via Correios; 2: Cheque entregue em m�os; 3: Deposito bancario): ");
								empregado[i].pagamento = user.nextInt();
							}
							else if (horas == 5) {
								System.out.print("Se o funcionario pertence a um sindicato, digite a taxa que o mesmo cobra. Caso contr�rio, digite 0: ");
								empregado[i].taxaSindicato = Double.valueOf(user.next());
							}
						} else if(empregado[i].nome == null) {
							System.out.println("Funcionario n�o encontrado.");
						}
					}
				}
				else {
					System.out.println("N�o h� funcionarios cadastrados. Op��o inv�lida.\n");
				}
				
			}
			else if (opcao == 7) {
				System.out.println("Rodar a folha de pagamento");
				if (data.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {

					System.out.println("Sexta-feira\nEmpregados que recebem hoje:");
					for (i = 0; i < quantidade; i++) {
						if (empregado[i].tipo == 1 && empregado[i].nome != null) {
							System.out.println(empregado[i].numeroEmpregado + " : " + empregado[i].nome + "\n");
							System.out.println("Forma de pagamento: " + empregado[i].diaPagamento);
							System.out.println("Salario a ser recebido: ");
							empregado[i].pago++;
						}
						if (empregado[i].tipo == 2 && empregado[i].nome != null
								&& (data.get(Calendar.DAY_OF_MONTH) >= 8 || data.get(Calendar.DAY_OF_MONTH) <= 14)) {
							System.out.println("Nome do empregado: " + empregado[i].nome);

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
							}
						}
					} else if (aux == 2) {
						for (i = 0; i < quantidade; i++) {
							if (aux == empregado[i].diaPagamento && empregado[i].nome != null) {
								System.out.println(empregado[i].numeroEmpregado + " : " + empregado[i].nome + "\n");
							}
						}
					} else if (aux == 3) {
						for (i = 0; i < quantidade; i++) {
							if (aux == empregado[i].diaPagamento && empregado[i].nome != null) {
								System.out.println(empregado[i].numeroEmpregado + " : " + empregado[i].nome + "\n");
							}
						}
					} else if (aux == 4) {
						for (i = 0; i < quantidade; i++) {
							if (aux == empregado[i].diaPagamento && empregado[i].nome != null) {
								System.out.println(empregado[i].numeroEmpregado + " : " + empregado[i].nome + "\n");
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
						System.out.println("\n");
					}
				}
			} else {
				System.out.println("Op��o inv�lida. Tente novamente.\n");
			}

		}

	}
}

// public static Calendar checaFDS(Calendar data)
// {
// // se for domingo
// if (data.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
// {
// data.add(Calendar.DATE, 1);
// System.out.println("Eh domingo, mudando data para +1 dias");
// }
// // se for s�bado
// else if (data.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
// {
// data.add(Calendar.DATE, 2);
// System.out.println("Eh sabado, mudando data para +2 dias");
// }
// else if (data.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY)
// {
//
// System.out.println("Sexta-feira\nEmpregados que recebem hoje:");
// for (i = 0; i < quantidade; i++) {
// if (empregado[i].diaPagamento == 1 && empregado[i].nome != null) {
// System.out.println("Nome do empregado: "+ empregado[i].nome);
//
// }
// }
//
//
// }
// else
// {
// System.out.println("Eh dia de semana, mantem data");
// }
// return data;
//
//
//
// }
//
// }
