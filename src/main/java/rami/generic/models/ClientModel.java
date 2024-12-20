package rami.generic.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientModel {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;
}
