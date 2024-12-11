package com.test.ambev.order_service.mapper;

import com.test.ambev.order_service.dto.PedidoDTO;
import com.test.ambev.order_service.model.Pedido;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-11T11:49:32-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.12 (Amazon.com Inc.)"
)
public class PedidoMapperImpl implements PedidoMapper {

    @Override
    public PedidoDTO pedidoToPedidoDTO(Pedido pedido) {
        if ( pedido == null ) {
            return null;
        }

        PedidoDTO pedidoDTO = new PedidoDTO();

        pedidoDTO.setStatus( pedido.getStatus() );
        pedidoDTO.setValorTotal( pedido.getValorTotal() );

        return pedidoDTO;
    }
}
