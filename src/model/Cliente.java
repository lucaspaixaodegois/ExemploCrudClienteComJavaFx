package model;//nome do pacote 

//importa��es necessarias
import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//classe cliente
@Entity
public class Cliente implements Serializable{

private static final long serialVersionUID = 371369774565415403L;

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
//atributos de cliente e que tem na tela do arquivo.fxml
private Integer Id;
private String cpf;
private String nome;
private String endereco;
private String email;

@Column(columnDefinition="Date")
private LocalDate dpAniversario; 


public Cliente() {

}
//construtor da classe cliente
public Cliente(String cpf, String nome, String endereco, String email, LocalDate dpAniversario) {
super();
this.cpf = cpf;
this.nome = nome;
this.endereco = endereco;
this.email = email;
this.dpAniversario = dpAniversario;
}
//gets e sets
public Integer getId() {
return Id;
}

public void setId(Integer id) {
Id = id;
}

public String getCpf() {
return cpf;
}

public void setCpf(String cpf) {
this.cpf = cpf;
}

public String getNome() {
return nome;
}

public void setNome(String nome) {
this.nome = nome;
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
public LocalDate getDpAniversario() {
	return dpAniversario;
}
public void setDpAniversario(LocalDate dpAniversario) {
	this.dpAniversario = dpAniversario;
}

}