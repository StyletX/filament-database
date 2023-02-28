package com.filamentdb.filamentdb.model;

import com.filamentdb.filamentdb.internal.StandardEntity;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.Where;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "colors")
@Entity(name = "colors")
@ToString(callSuper = true)
@Where(clause = "deleted=false")
@EqualsAndHashCode(callSuper = true)
//@JsonView(JsonViews.ColorView.class)
public class Color extends StandardEntity {
    @NotBlank
//    @JsonView(JsonViews.Base.class)
    private String colorName;
}
