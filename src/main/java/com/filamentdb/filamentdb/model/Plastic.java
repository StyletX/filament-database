package com.filamentdb.filamentdb.model;

import com.filamentdb.filamentdb.internal.CustomAnnotations.PlasticAnnotations.PlasticDiameter;
import com.filamentdb.filamentdb.internal.CustomAnnotations.PlasticAnnotations.PlasticTypeName;
import com.filamentdb.filamentdb.internal.StandardEntity;
import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.annotations.Where;

@Data
@NoArgsConstructor
@Entity(name = "plastics")
@ToString(callSuper = true)
@Where(clause = "deleted=false")
@EqualsAndHashCode(callSuper = true)
public class Plastic extends StandardEntity {

    @PlasticTypeName
    private String typeName;

    @PlasticDiameter
    private Float diameter;

    //    @ManyToOne
//    @JsonIgnore
//    private Manufacturer manufacturer;
    private Long manufacturerId;

    //    @ManyToOne(cascade = CascadeType.ALL)
//    private Color color;
    private Long colorId;
}
