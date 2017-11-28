package br.com.my.negocio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.my.utils.Conversor;

public class NegocioBase {

	@Autowired
	private Conversor conversor;

	protected <S, T> T converter(S entidade, Class<T> tipoVO, String... excludeFields) {
		return conversor.converter(entidade, tipoVO, excludeFields);
	}

	protected <S, T> T converter(S entidade, Class<T> tipoVO) {
		return conversor.converter(entidade, tipoVO);
	}

	protected <S, T> List<T> converter(List<S> from, Class<T> voType) {
		List<T> result = new ArrayList<>();
		if (from == null) {
			return result;
		}
		for (S s : from) {
			T t = converter(s, voType);
			result.add(t);
		}
		return result;
	}

}
