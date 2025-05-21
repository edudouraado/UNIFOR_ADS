public class Estudante {
	private String nome;
	private String identificacaoEstudante;
	private int idade;
	private String curso;
	private double media;
	
	public Estudante (String nome, String identificacaoEstudante, int idade, String curso, double media){
		this.nome = nome;
		this.identificacaoEstudante = identificacaoEstudante;
		this.idade = idade;
		this.curso = curso;
		this.media = media;
	}


	public String getNome () {
		return nome;
	}

	public void setNome (String nome){
		this.nome = nome;
	}


	public String getIdentificacaoEstudante() {
        	return identificacaoEstudante;
    	}

	public void setIdentificacaoEstudante(String identificacaoEstudante) {
        	this.identificacaoEstudante = identificacaoEstudante;
    	}

    	public int getIdade() {
        	return idade;
    	}

    	public void setIdade(int idade) {
        	if (idade > 0) {
            		this.idade = idade;
        	} else {
            		System.out.println("Idade inválida.");
        	}
    	}

    	public String getCurso() {
        	return curso;
    	}	

    	public void setCurso(String curso) {
        	this.curso = curso;
    	}

    	public double getMedia() {
        	return media;
    	}

    	public void setMedia(double media) {
        	if (media >= 0 && media <= 10) {
            		this.media = media;
        	} else {
            		System.out.println("Média inválida.");
        	}
    	}

	public void atualizarMedia(double novaMedia) {
		if (novaMedia >= 0 && novaMedia <= 10) {
            		this.media = novaMedia;
            		System.out.println("Média atualizada com sucesso!");
        	} else {
            		System.out.println("A média deve estar entre 0 e 10.");
        	}
    	}


	public void exibirInformacoes() {
        	System.out.println("Nome: " + nome);
        	System.out.println("Identificação: " + identificacaoEstudante);
        	System.out.println("Idade: " + idade);
        	System.out.println("Curso: " + curso);
			System.out.println("Média: " + getMedia());
    	}
}
	