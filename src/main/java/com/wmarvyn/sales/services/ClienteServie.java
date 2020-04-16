package com.wmarvyn.sales.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.wmarvyn.sales.domain.Categoria;
import com.wmarvyn.sales.domain.Cidades;
import com.wmarvyn.sales.domain.Cliente;
import com.wmarvyn.sales.domain.Endereco;
import com.wmarvyn.sales.domain.enums.TipoCliente;
import com.wmarvyn.sales.dto.ClienteDTO;
import com.wmarvyn.sales.dto.ClienteNewDTO;
import com.wmarvyn.sales.repositores.ClienteRepository;
import com.wmarvyn.sales.repositores.EnderecoRepository;
import com.wmarvyn.sales.services.exception.DataIntegrityException;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ClienteServie {

	@Autowired
	private ClienteRepository repo;

	@Autowired
	private EnderecoRepository enderecoRepository;

	public Cliente find(Integer id) throws ObjectNotFoundException {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		
		}

	public Cliente insert(Cliente obj) {
		obj.setId((null));
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	public Cliente update(Cliente obj) throws ObjectNotFoundException {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) throws ObjectNotFoundException {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma cliente com produtos associados");
		}
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Page<Cliente> finPage(Integer page, Integer lineInteger, String orderBy, String direction) {

		@SuppressWarnings("deprecation")
		PageRequest pageRequest = new PageRequest(page, lineInteger, Sort.Direction.fromString(direction), orderBy);

		return repo.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}

	public Cliente fromDTO(ClienteNewDTO objDto) throws IllegalAccessException {
		Cliente cli = new Cliente(null, objDto.getNome(),objDto.getEmail(),objDto.getCpfCnpj(), TipoCliente.toEnum(objDto.getTipo()));
		Cidades cid = new Cidades(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null,objDto.getLogradouro(),objDto.getNumero(),objDto.getComplemento(),objDto.getBairro(),objDto.getCep(),cli,cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone());
		if (objDto.getTelefone2() !=null ){
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3() !=null ){
			cli.getTelefones().add(objDto.getTelefone3());
		}
	return cli;
	}
	
	private void updateData (Cliente newObj, Cliente obj){
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	}
