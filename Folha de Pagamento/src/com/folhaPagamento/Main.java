package com.folhaPagamento;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// Fazendo o menu
		System.out.println("Sistema Folha de Pagamento\n\nEscolha a op��o desejada:\n");
		System.out.println("1: Adicionar um novo empregado\n2: Remover um empregado existente\n3: Lan�ar um cart�o de ponto");
		System.out.println("4: Lan�ar um resultado de venda\n5: Lan�ar uma taxa de servi�o\n6: Alterar detalhes de um empregado");
		System.out.println("7: Rodar folha de pagamento para hoje\n8: Desfazer ou refazer alguma altera��o\n9: Mostrar agenda de pagamento\n10: Criar nova agenda de pagamento");
		// A classe "Scanner" � usada para receber dados do usuario
		Scanner user = new Scanner(System.in);
		int opcao = Integer.valueOf(user.next());
		if(opcao == 1)
		{
			System.out.println("Criar um novo empregado.\nDigite o nome:");
			Empregado empregado = new Empregado();		
			empregado.nome = user.nextLine();
			empregado.nome = user.nextLine();
			System.out.println("Digite o endere�o completo do empregado (Rua/Av, Num, Bairro e Cidade)");
			empregado.endere�o = user.nextLine();


			

			System.out.printf("Nome do empregado: %s\n", empregado.nome);
			System.out.printf("Endere�o do empregado: %s\n", empregado.endere�o);

		    
			
			
		}
		
		
		

	}

}
