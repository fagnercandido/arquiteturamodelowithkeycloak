package br.com.my.utils;

import static org.dozer.loader.api.TypeMappingOptions.mapNull;

import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingBuilder;
import org.dozer.loader.api.TypeMappingOptions;
import org.springframework.stereotype.Component;

@Component
public class Conversor {

	public <S, T> T converter(S entidade, Class<T> tipoVO) {

		if (entidade == null) {

			return null;
		}

		DozerBeanMapper mapper = new DozerBeanMapper();

		return mapper.map(entidade, tipoVO);
	}

	public <S, T> T converter(S entidade, Class<T> tipoVO, BeanMappingBuilder builder) {

		if (entidade == null) {

			return null;
		}

		DozerBeanMapper mapper = new DozerBeanMapper();

		mapper.addMapping(builder);

		return mapper.map(entidade, tipoVO);
	}

	public <S, T> T converter(final S entidade, final Class<T> tipoVO, final String... excludeFields) {

		if (entidade == null) {

			return null;
		}

		BeanMappingBuilder builder = new BeanMappingBuilder() {

			@Override
			protected void configure() {

				TypeMappingBuilder file = mapping(entidade.getClass(), tipoVO, TypeMappingOptions.oneWay(),
						mapNull(true));

				for (String field : excludeFields) {

					file.exclude(field);
				}
			}
		};
		return converter(entidade, tipoVO, builder);
	}

}
