package com.filamentdb.filamentdb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.filamentdb.filamentdb.internal.StandardEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Where;

import java.util.List;

@Data
@NoArgsConstructor
//@Table(name = "manufacturers")
@Entity(name = "manufacturers")
@Where(clause = "deleted=false")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, exclude = "plastics")
//@JsonView(JsonViews.ManufacturerView.class)
public class Manufacturer extends StandardEntity {

    @NonNull
//    @JsonUnwrapped      //????
//    @Column(name = "shotName")
//    @JsonView(JsonViews.Base.class)
    private String name;

    @NonNull
//    @Column(name = "fullName")
    private String fullName;

    @NonNull
//    @Column(name = "city")
    private String city;

    @NonNull
    @Column(unique = true)
    @Size(min = 10, max = 10, message = "ИНН состоит из 10 арабских цифр")
    @Digits(integer = 10, fraction = 0, message = "ИНН состоит из 10 арабских цифр")
//    @Column(name = "inn", unique = true)
    private String inn;

    @NonNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @OneToMany(mappedBy = "manufacturerId", cascade = CascadeType.ALL)
    private List<Plastic> plastics;

}
