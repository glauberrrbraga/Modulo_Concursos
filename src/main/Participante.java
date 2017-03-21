package main;

import java.util.Date;

public class Participante {
	private String nome;
	private String cpf;
	private int rg;
	private String orgaoEmissorRG;
	private Date dataNascimento;
	private String nomeMae;
	private String naturalidade;
	private boolean taxaPaga;
	private boolean isentoTaxa;
	private String endereco;
	private String email;
	private String telefone;
	private boolean aptidao;
	private String acessibilidade;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public int getRg() {
		return rg;
	}
	public void setRg(int rg) {
		this.rg = rg;
	}
	public String getOrgaoEmissorRG() {
		return orgaoEmissorRG;
	}
	public void setOrgaoEmissorRG(String orgaoEmissorRG) {
		this.orgaoEmissorRG = orgaoEmissorRG;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getNomeMae() {
		return nomeMae;
	}
	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}
	public String getNaturalidade() {
		return naturalidade;
	}
	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}
	public boolean isTaxaPaga() {
		return taxaPaga;
	}
	public void setTaxaPaga(boolean taxaPaga) {
		this.taxaPaga = taxaPaga;
	}
	public boolean isIsentoTaxa() {
		return isentoTaxa;
	}
	public void setIsentoTaxa(boolean isentoTaxa) {
		this.isentoTaxa = isentoTaxa;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public boolean isAptidao() {
		return aptidao;
	}
	public void setAptidao(boolean aptidao) {
		this.aptidao = aptidao;
	}
	public String getAcessibilidade() {
		return acessibilidade;
	}
	public void setAcessibilidade(String acessibilidade) {
		this.acessibilidade = acessibilidade;
	}
	public static boolean validaCPF (String CPF){
		boolean valido = false;
		boolean verifica = true;
		int[] cpfint = new int[11];
		int soma1 = 0, soma2 = 0, dig1, dig2;
		
		if(CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222") ||
			CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555") ||
			CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888") ||
			CPF.equals("99999999999")){
				verifica = false;
		}
		if(CPF.length() == 11 && verifica == true){
			for(int i = 0; i < 11; i++){
				cpfint[i] = (int)(CPF.charAt(i) - 48);
			}
			for(int j = 0; j < 10; j++){
				if(j < 9){
					soma1 += (cpfint[j] * (10 - j));
				}
				soma2 += (cpfint[j] * (11 - j));
			}
			dig1 = 11 - (soma1 % 11);
			dig2 = 11 - (soma2 % 11);
			if(dig1 == 10 || dig1 == 11) dig1 = 0;
			if(dig2 == 10 || dig2 == 11) dig2 = 0;
			if(dig1 == cpfint[9] && dig2 == cpfint[10]) valido = true;
		}
		return valido;
	}
}
