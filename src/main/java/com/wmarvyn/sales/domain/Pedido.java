package com.wmarvyn.sales.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class  Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern = "dd/mm/yyyy HH:MM")
	private Date Instante;


	@OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
	private Pagamento pagamento;


	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente  cliente;

	@ManyToOne
	@JoinColumn(name = "endereco_de_entrega_id")
	private Endereco enderecoDeEntrega;


	@OneToMany(mappedBy = "id.pedido")
	private Set<ItemPedido> itens = new HashSet<>();

	public Pedido(){

	}

	public Pedido(Integer id, Date instante,  Cliente cliente, Endereco enderecoDeEntrega) {
		this.id = id;
		Instante = instante;
		this.cliente = cliente;
		this.enderecoDeEntrega = enderecoDeEntrega;
	}

	public double getValorTotalPedido(){
		double soma = 0.0;
		for (ItemPedido ip: itens
			 ) {soma = soma + ip.getSubTotal();
		}
		return soma;
	}
	
	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
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


	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Pedido{");
		sb.append("id=").append(id);
		sb.append(", pagamento=").append(getPagamento());
		sb.append(", cliente=").append(cliente);
		sb.append(", itens=").append(itens);
		sb.append('}');
		return sb.toString();
	}
}
