package com.test.ambev.order_service.mapper;

import com.test.ambev.order_service.dto.PedidoDTO;
import com.test.ambev.order_service.model.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PedidoMapper {

    PedidoMapper INSTANCE = Mappers.getMapper(PedidoMapper.class);

    // Mapeia Pedido para PedidoDTO
    PedidoDTO pedidoToPedidoDTO(Pedido pedido);
}
