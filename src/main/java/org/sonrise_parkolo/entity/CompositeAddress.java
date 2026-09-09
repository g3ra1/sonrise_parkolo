package org.sonrise_parkolo.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.sonrise_parkolo.enums.County;

@Entity
@Table(name = "composite_addresses")
@Data
public class CompositeAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    private String postCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull
    private County county;

    @Column(nullable = false)
    @NotEmpty
    private String city;

    @Column(nullable = false)
    @NotEmpty
    private String street;

    public String getFormattedAddress() {
        String countyStr = (county != null) ? county.getDisplayName() : "";
        return String.format("%s %s, %s %s",
                postCode != null ? postCode : "",
                countyStr,
                city != null ? city : "",
                street != null ? street : "").trim().replaceAll(" +", " ");
    }
}
