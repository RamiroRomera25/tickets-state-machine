package rami.generic.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DummyDtoFilter {
    private Long id;
    private String dummy;
    private Boolean isActive;
}
