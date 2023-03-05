package com.filamentdb.filamentdb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.filamentdb.filamentdb.internal.CustomAnnotations.ManufacturerAnnotations.ManufacturerCity;
import com.filamentdb.filamentdb.internal.CustomAnnotations.ManufacturerAnnotations.ManufacturerFullName;
import com.filamentdb.filamentdb.internal.CustomAnnotations.ManufacturerAnnotations.ManufacturerInn;
import com.filamentdb.filamentdb.internal.CustomAnnotations.ManufacturerAnnotations.ManufacturerName;
import com.filamentdb.filamentdb.internal.StandardEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "manufacturers")
@Where(clause = "deleted=false")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, exclude = "plastics")
public class Manufacturer extends StandardEntity {
    @ManufacturerName
    private String name;

    @ManufacturerFullName
    private String fullName;

    @ManufacturerCity
    private String city;

    @ManufacturerInn
    @Column(unique = true)
    private Long inn;

    @NonNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @OneToMany(mappedBy = "manufacturerId", cascade = CascadeType.ALL)
    private List<Plastic> plastics;

}
