package rami.generic.dtos.clients;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTOPost {
    private String firstName;

    private String lastName;

    private String email;
}
