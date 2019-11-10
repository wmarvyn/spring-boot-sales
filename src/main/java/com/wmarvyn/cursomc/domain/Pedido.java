package com.wmarvyn.cursomc.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Date Instante;

	private Pagamento pagamento;
	private Cliente  cliente;
	private Endereco enderecoDeEntrega;

	public Pedido(){

	}

	public Pedido(Integer id, Date instante, Pagamento pagamento, Cliente cliente, Endereco enderecoDeEntrega) {
		this.id = id;
		Instante = instante;
		this.pagamento = pagamento;
		this.cliente = cliente;
		this.enderecoDeEntrega = enderecoDeEntrega;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstante() {
		return Instante;
	}

	public void setInstante(Date instante) {
		Instante = instante;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEnderecoDeEntrega() {
		return enderecoDeEntrega;
	}

	public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
		this.enderecoDeEntrega = enderecoDeEntrega;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Pedido)) return false;
		Pedido pedido = (Pedido) o;
		return getId().equals(pedido.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}
}
