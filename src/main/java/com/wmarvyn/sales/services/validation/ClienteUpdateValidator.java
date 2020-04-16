package com.wmarvyn.sales.services.validation;

import com.wmarvyn.sales.domain.Cliente;
import com.wmarvyn.sales.domain.enums.TipoCliente;
import com.wmarvyn.sales.dto.ClienteDTO;
import com.wmarvyn.sales.dto.ClienteNewDTO;
import com.wmarvyn.sales.repositores.ClienteRepository;
import com.wmarvyn.sales.resources.exception.FileMassage;
import com.wmarvyn.sales.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;


import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private HttpServletRequest request;

	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		@SuppressWarnings("unchecked")
		Map<String,String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

		Integer urlid = Integer.parseInt(map.get("id"));

		List<FileMassage> list = new ArrayList<>();

	    Cliente aux = clienteRepository.findByEmail(objDto.getEmail());

        if(aux !=null && !aux.getId().equals(urlid)){
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
