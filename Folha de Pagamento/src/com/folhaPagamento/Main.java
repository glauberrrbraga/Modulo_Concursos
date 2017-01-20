package com.folhaPagamento;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int opcao = 0, quantidade = 0, numero = 1;
		Scanner user = new Scanner(System.in);
		Empregado [] empregado = new Empregado[100];	

		System.out.println("Sistema Folha de Pagamento\n\nEscolha a op��o desejada:\n");
		while(opcao!=100)
		{
			System.out.println("1: Adicionar um novo empregado\n2: Remover um empregado existente\n3: Lan�ar um cart�o de ponto");
			System.out.println("4: Lan�ar um resultado de venda\n5: Lan�ar uma taxa de servi�o\n6: Alterar detalhes de um empregado");
			System.out.println("7: Rodar folha de pagamento para hoje\n8: Desfazer ou refazer alguma altera��o\n9: Mostrar agenda de pagamento\n10: Criar nova agenda de pagamento");
			System.out.println("11: Vizualizar funcionario");
			
			opcao = Integer.valueOf(user.next());
			if(opcao == 100)
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
				System.out.print("Digite o tipo do empregado: (1 -> Horista; 2 -> Assalariado; 3 -> Comissionado): ");
				empregado[quantidade].tipo = user.nextInt();
				if(empregado[quantidade].tipo == 1){
					System.out.println("Empregado Horista\nInforma��es Adicionais..");
					System.out.print("O empregado j� possui horas trabalhadas? Se negativo, digite 0, caso contrario, digite o numero de horas trabalhadas: ");
					empregado[quantidade].horasTabalhadas = Integer.valueOf(user.next());
					System.out.println("O empregado j� possui horas extras trabalhadas? Se negativo, digite 0, caso contrario, digite o numero de horas extras trabalhadas: ");
					empregado[quantidade].horasExtras = Integer.valueOf(user.next());
				}
				else if (empregado[quantidade].tipo == 2) {
					System.out.print("Funcionario Assalariado n�o comissionado\nDigite o salario do funcionario: ");
					empregado[quantidade].salarioMensal = Double.valueOf(user.next());
					System.out.print("O empregado j� possui horas trabalhadas? Se negativo, digite 0, caso contrario, digite o numero de horas trabalhadas: ");
					empregado[quantidade].horasTabalhadas = Integer.valueOf(user.next());
				}
				else if (empregado[quantidade].tipo == 3)
				{
					System.out.print("Funcionario Assalariado Comissionado\nDigite o salario do funcionario: ");
					empregado[quantidade].salarioMensal = Double.valueOf(user.next());
					System.out.print("Digite o percentual de comiss�o que o funcionario recebe (Exemplo: 0.5%): ");
					empregado[quantidade].percentual = Double.valueOf(user.next());
					System.out.print("O empregado j� possui horas trabalhadas? Se negativo, digite 0, caso contrario, digite o numero de horas trabalhadas: ");
					empregado[quantidade].horasTabalhadas = Integer.valueOf(user.next());
				}
				System.out.print("Quase pronto. Digite a forma como o funcionario prefere receber o salario (1: Cheque via Correios; 2: Cheque entregue em m�os; 3: Deposito bancario): ");
				empregado[quantidade].pagamento = Integer.valueOf(user.next());
				System.out.print("Caso o funcionario perten�a a algum sindicato, digite a taxa descontada pelo sindicato, caso contrario, digite 0: ");
				empregado[quantidade].taxaSindicato = Double.valueOf(user.next());
				System.out.println("Funcionario adicionado com sucesso.");
				quantidade++; 
				
			}
			else if(opcao == 11)
			{
				System.out.print("Digite o codigo do funcionario: ");
				int aux = Integer.valueOf(user.next());
				
				for(int i = 1; i < quantidade; i++){
					if(aux == empregado[i].numeroEmpregado)
					{

						System.out.printf("Nome do empregado: %s\n", empregado[i].nome);
						System.out.printf("Endere�o do empregado: %s\n", empregado[i].endere�o);
						System.out.println("Tipo: " + empregado[i].tipo);
						System.out.println("Valor do Salario Mensal: R$" + empregado[i].salarioMensal);
						System.out.println("Percentual: "+empregado[i].percentual + "%");
						System.out.println("Taxa sindicato: " + empregado[i].taxaSindicato + "%");
						System.out.println("\n");
					}
				}
			}
			

		}

	}

}
