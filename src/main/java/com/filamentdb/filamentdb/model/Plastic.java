package com.filamentdb.filamentdb.model;

import com.filamentdb.filamentdb.internal.StandardEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Where;

@Data
@NoArgsConstructor
//@Table(name = "plastics")
@Entity(name = "plastics")
@ToString(callSuper = true)
@Where(clause = "deleted=false")
@EqualsAndHashCode(callSuper = true)
//@JsonView(JsonViews.MaterialView.class)
public class Plastic extends StandardEntity {

    @NonNull
//    @Column(name = "fullName")
    private String typeName;

    @NotNull
    @ManyToOne
    private Manufacturer manufacturer;

    @ManyToOne
    private Color color;
}
