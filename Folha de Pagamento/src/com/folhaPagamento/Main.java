package com.folhaPagamento;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int opcao = 0, quantidade = 0, numero = 1, aux, i, horas;
		double auxD;
		Scanner user = new Scanner(System.in);
		Empregado [] empregado = new Empregado[100];	

		System.out.println("Sistema Folha de Pagamento\nDigite o dia\nEscolha a op��o desejada:\n");
		while(opcao!=12)
		{
			System.out.println("1: Adicionar um novo empregado\n2: Remover um empregado existente\n3: Lan�ar um cart�o de ponto");
			System.out.println("4: Lan�ar um resultado de venda\n5: Lan�ar uma taxa de servi�o\n6: Alterar detalhes de um empregado");
			System.out.println("7: Rodar folha de pagamento para hoje\n8: Desfazer ou refazer alguma altera��o\n9: Mostrar agenda de pagamento\n10: Criar nova agenda de pagamento");
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
				System.out.print("Digite o tipo do empregado: (1: Horista; 2: Assalariado; 3: Comissionado): ");
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
					System.out.print("O empregado j� possui horas trabalhadas? Se negativo, digite 0, caso contrario, digite o numero de horas trabalhadas: ");
					empregado[quantidade].horasTabalhadas = Integer.valueOf(user.next());
				}
				else if (empregado[quantidade].tipo == 3)
				{
					System.out.print("Funcionario Assalariado Comissionado\nPagamento realizando sempre no ultimo dia do m�s\nDigite o salario do funcionario: ");
					empregado[quantidade].salarioMensal = Double.valueOf(user.next());
					System.out.print("Digite o percentual de comiss�o que o funcionario recebe (Exemplo: 0.5%): ");
					empregado[quantidade].percentual = Double.valueOf(user.next());
					System.out.print("O empregado j� possui horas trabalhadas? Se negativo, digite 0, caso contrario, digite o numero de horas trabalhadas: ");
					empregado[quantidade].horasTabalhadas = Integer.valueOf(user.next());
				}
				System.out.print("Digite a forma como o funcionario prefere receber o salario (1: Cheque via Correios; 2: Cheque entregue em m�os; 3: Deposito bancario): ");
				empregado[quantidade].pagamento = Integer.valueOf(user.next());
				System.out.print("Quase pronto...\nCaso o funcionario perten�a a algum sindicato, digite a taxa descontada pelo sindicato, caso contrario, digite 0: ");
				empregado[quantidade].taxaSindicato = Double.valueOf(user.next());
				System.out.println("\nFuncionario adicionado com sucesso.\n");
				quantidade++; 
				numero++;
				
				
			}
			else if (opcao == 2) {
				System.out.print("Remover um funcionario do sistema. Digite o codigo do funcionario: ");
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
						System.out.println("Removido com sucesso.");
					}
				}

			}
			else if (opcao == 3) {
				System.out.print("Lan�ar cart�o de ponto\nDigite o codigo do funcion�rio: ");
				aux = Integer.valueOf(user.next());
				for (i = 0; i < quantidade; i++) {
					if (aux == empregado[i].numeroEmpregado) {

						System.out.printf("Nome do empregado: %s\n", empregado[i].nome);
						System.out.println("Horas extras trabalhadas at� o momento: " + empregado[i].horasExtras);
						if (empregado[i].tipo == 1) {
							System.out.print("Empregado Horista.\n Digite o numero de horas trabalhadas: ");
							horas = Integer.valueOf(user.next());
							if (horas > 8) {
								horas = horas - 8;
								empregado[i].horasExtras += horas;
								empregado[i].horasTabalhadas += 8;
							} else {
								empregado[i].horasTabalhadas += aux;
							}
							i = quantidade;
						}
						else{
							System.out.println("Empregado n�o horista, op��o n�o dispon�vel.\n");
						}
					}
				}
			} else if (opcao == 4) {
				System.out.print("Lan�ar uma venda\nDigite o codigo do funcion�rio: ");
				aux = Integer.valueOf(user.next());
				for (i = 0; i < quantidade; i++) {
					if (aux == empregado[i].numeroEmpregado) {
						System.out.printf("Nome do empregado: %s\n", empregado[i].nome);
						if (empregado[i].tipo == 3) {
							System.out.print("Empregado Assalariado Comissionado.\n Digite o valor da venda realizada (Ex.: 500.00): ");
							auxD = Double.valueOf(user.next());
							empregado[i].vendaMes += auxD;
							i = quantidade;
						}
						else{
							System.out.println("Empregado n�o comissionado, op��o n�o dispon�vel.\n");
						}
					}
					else{
						System.out.println("Funcionario n�o encontrado.");
					}
				}
			}
			else if (opcao == 6) {
				System.out.print("Alterar detalhes de um funcion�rio\nDigite o codigo do funcion�rio: ");
				aux = Integer.valueOf(user.next());
				for (i = 0; i < quantidade; i++) {
					if (aux == empregado[i].numeroEmpregado) {
						System.out.print("Nome do empregado: " + empregado[i].nome + "\nDigite o codigo da informa��o que deseja alterar: ");
						
					}
					else{
						System.out.println("Funcionario n�o encontrado.");
					}
				}
			}
			
			
			else if(opcao == 11)
			{
				System.out.print("Digite o codigo do funcionario: ");
				aux = Integer.valueOf(user.next());
				
				for(i = 0; i < quantidade; i++){
					if(aux == empregado[i].numeroEmpregado)
					{

						System.out.printf("Nome do empregado: %s\n", empregado[i].nome);
						System.out.printf("Endere�o do empregado: %s\n", empregado[i].endere�o);
						System.out.println("Tipo: " + empregado[i].tipo);
						System.out.println("Valor do Salario Mensal: R$" + empregado[i].salarioMensal);
						System.out.println("Percentual: "+empregado[i].percentual + "%");
						System.out.println("Taxa sindicato: " + empregado[i].taxaSindicato + "%");
						System.out.println("Horas trabalhadas: "+empregado[i].horasTabalhadas);
						System.out.println("Horas extras: " + empregado[i].horasExtras);
						System.out.println("\n");
					}
				}
			}
			

		}

}

}
