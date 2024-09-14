package uz.pdp.projectutils.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "users")
@NoArgsConstructor
public class User extends BaseEntity {
    private String name;
    private String username;
    private String password;
}