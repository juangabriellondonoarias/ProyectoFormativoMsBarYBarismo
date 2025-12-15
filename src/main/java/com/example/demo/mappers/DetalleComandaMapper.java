//package com.example.demo.mappers;
//
//import com.example.demo.dto.DetalleComandaDTO;
//import com.example.demo.models.DetalleComanda;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DetalleComandaMapper {
//
//    public DetalleComandaDTO toDTO(DetalleComanda entity) {
//        DetalleComandaDTO dto = new DetalleComandaDTO();
//        dto.setIdDetalleBarYBarismo(entity.getIdDetalleComanda());
//        dto.setIdReceta(entity.getIdReceta());
//        dto.setCantidad(entity.getCantidad());
//        dto.setNotas(entity.getNotas());
//        dto.setEstadoBebida(entity.getEstadoBebida().name());
//        return dto;
//    }
//
//    public DetalleComanda toEntity(DetalleComandaDTO dto) {
//        DetalleComanda entity = new DetalleComanda();
//        entity.setIdReceta(dto.getIdReceta());
//        entity.setCantidad(dto.getCantidad());
//        entity.setNotas(dto.getNotas());
//        entity.setEstadoBebida(DetalleComanda.EstadoBebida.valueOf(dto.getEstadoBebida()));
//        return entity;
//    }
//}
