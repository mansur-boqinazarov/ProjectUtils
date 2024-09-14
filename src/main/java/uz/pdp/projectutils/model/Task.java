package uz.pdp.projectutils.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name ="task")
@NoArgsConstructor
public class Task extends BaseEntity{
    private String name;
    @Column(columnDefinition = "text")
    private LocalDate deadline;

    @Column(name = "dead_line")
    private String description;

    @Builder
    public Task(String name, String description, LocalDate deadline){
        this.name = name;
        this.description = description;
        this.deadline = deadline;
    }
}
