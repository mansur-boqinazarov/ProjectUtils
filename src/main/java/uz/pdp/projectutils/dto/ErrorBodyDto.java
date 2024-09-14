package uz.pdp.projectutils.dto;


import java.time.LocalDateTime;


public record ErrorBodyDto(
    Integer status,
    String uri,
    String url,
    String reason,
    String message,
    LocalDateTime timestamp
){

}
