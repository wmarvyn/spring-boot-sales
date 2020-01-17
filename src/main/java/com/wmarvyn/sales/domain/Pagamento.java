package com.wmarvyn.sales.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wmarvyn.sales.domain.enums.EstadoPagamento;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento implements Serializable {
	private static final long serialVersionUID = 1L;


	@Id
	private Integer id;
	private Integer estado;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "pedido_id")
	@MapsId
	private Pedido pedido;


	public Pagamento(){

	}

	@JsonIgnore
	public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
		this.id = id;
		this.estado = estado.getCod();
		this.pedido = pedido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EstadoPagamento getEstado() throws IllegalAccessException {
		return EstadoPagamento.toEnum(estado);
	}

	public void setEstado(EstadoPagamento estado) {
		this.estado = estado.getCod();
	}

	@JsonIgnore
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Pagamento)) return false;
		Pagamento pagamento = (Pagamento) o;
		return Objects.equals(getId(), pagamento.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}
}
