package rb.pplmngmntAPI.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rb.pplmngmntAPI.enums.PhoneType;
import javax.persistence.*;


@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PhoneType phoneType;

    @Column(nullable = false)
    private String phoneNumber;


}
