package com.wmarvyn.sales.dto;

import java.io.Serializable;
import com.wmarvyn.sales.domain.Cliente;
import com.wmarvyn.sales.services.validation.ClienteUpdate;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@ClienteUpdate
public class ClienteDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    private Integer id;
	@NotEmpty(message = "Campo obeigatorio")
	@Length(min = 5, max = 80, message = "O tamanho deve ser no minimo 5 cadacteres e no maximo 80 caracteres")
	private String nome;

	@NotEmpty(message = "Campo obeigatorio")
	@Email(message = "e-mail invalido")
	private String email;
	
	public  ClienteDTO(){
    }

    public ClienteDTO(Cliente obj){
        id =  obj.getId();
        nome = obj.getNome();
        email = obj.getEmail();
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
