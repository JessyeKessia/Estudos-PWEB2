package br.edu.ifpb.pweb2.validation.validator;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CodCursoValidator implements ConstraintValidator<CodCursoValid, String>{
	
	private String prefix;
	
	@Override
	public void initialize(CodCursoValid cc) {
		prefix = cc.value();
	}

	@Override
	public boolean isValid(String codigo, ConstraintValidatorContext context) {
		boolean resultado = false;
		if(codigo != null) {
			resultado = codigo.startsWith(prefix);
		}
		return resultado;
	}

}
