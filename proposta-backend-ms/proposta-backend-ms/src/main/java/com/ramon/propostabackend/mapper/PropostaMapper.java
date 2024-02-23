package com.ramon.propostabackend.mapper;

import com.ramon.propostabackend.dto.PropostaRequestDto;
import com.ramon.propostabackend.dto.PropostaResponseDto;
import com.ramon.propostabackend.entity.Proposta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.text.NumberFormat;
import java.util.List;

@Mapper
public interface PropostaMapper {

    PropostaMapper INSTANCE = Mappers.getMapper(PropostaMapper.class);

    @Mapping(target = "user.nome", source = "nome")
    @Mapping(target = "user.sobrenome", source = "sobrenome")
    @Mapping(target = "user.cpf", source = "cpf")
    @Mapping(target = "user.telefone", source = "telefone")
    @Mapping(target = "user.renda", source = "renda")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "aprovada", ignore = true)
    @Mapping(target = "integrada", constant = "true")
    @Mapping(target = "observacao", ignore = true)
    Proposta convertDtoToProposta(PropostaRequestDto propostaRequestDto);

    @Mapping(target ="nome", source =  "user.nome")
    @Mapping(target ="sobrenome", source =  "user.sobrenome")
    @Mapping(target ="telefone", source =  "user.cpf")
    @Mapping(target ="cpf", source =  "user.nome")
    @Mapping(target ="renda", source =  "user.renda")
    @Mapping(target ="valorSolicitadoFmt", expression = "java(setValorSolicitadoFmt(proposta))")
    PropostaResponseDto convertEntityToDto(Proposta proposta);

    List<PropostaResponseDto>converterListEntityToListDto(Iterable<Proposta>propostas);

    default String setValorSolicitadoFmt(Proposta proposta) {
        return NumberFormat.getCurrencyInstance().format(proposta.getValorSolicitado());
    }
}
