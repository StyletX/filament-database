package com.filamentdb.filamentdb.rest;

import com.filamentdb.filamentdb.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("colors")
public class ColorController {
    private final ColorService colorService;


//    @GetMapping
//    @JsonView(JsonViews.ColorView.class)
//    public Respo
}
