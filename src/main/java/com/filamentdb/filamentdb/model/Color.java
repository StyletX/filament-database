package com.filamentdb.filamentdb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.filamentdb.filamentdb.internal.CustomAnnotations.ColorName;
import com.filamentdb.filamentdb.internal.StandardEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.hibernate.annotations.Where;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "colors")
@ToString(callSuper = true)
@Where(clause = "deleted=false")
@EqualsAndHashCode(callSuper = true)
public class Color extends StandardEntity {
    @ColorName
    private String colorName;

    @NonNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @OneToMany(mappedBy = "colorId", cascade = CascadeType.ALL)
    private List<Plastic> plastics;
}
