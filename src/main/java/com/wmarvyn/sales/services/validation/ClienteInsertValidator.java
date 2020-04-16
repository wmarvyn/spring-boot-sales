package com.wmarvyn.sales.services.validation;

import com.wmarvyn.sales.domain.Cliente;
import com.wmarvyn.sales.domain.enums.TipoCliente;
import com.wmarvyn.sales.dto.ClienteNewDTO;
import com.wmarvyn.sales.repositores.ClienteRepository;
import com.wmarvyn.sales.resources.ClienteResources;
import com.wmarvyn.sales.resources.exception.FileMassage;
import com.wmarvyn.sales.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FileMassage> list = new ArrayList<>();

		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfCnpj())){

		    list.add(new FileMassage("CPFouCNPJ", "CPF Invalido"));
        }
        if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfCnpj())){

            list.add(new FileMassage("CPFouCNPJ", "CNPJ Invalido"));
        }
		Cliente aux = clienteRepository.findByEmail(objDto.getEmail());

        if(aux !=null){
			list.add(new FileMassage("email", "Email já cadastrado"));
		}

		for (FileMassage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
