package uz.pdp.projectutils.dto;

import java.time.LocalDate;

public record TaskCreateDTO(
        Integer id,
        String name,
        String description,
        LocalDate deadLine
) {
}
