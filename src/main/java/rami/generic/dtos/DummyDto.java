package rami.generic.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DummyDto {
    private Long id;
    private String dummy;
    private Boolean isActive;
}
