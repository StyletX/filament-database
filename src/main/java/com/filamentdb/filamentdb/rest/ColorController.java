package com.filamentdb.filamentdb.rest;

import com.filamentdb.filamentdb.service.ColorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("colors")
public class ColorController {
    private final ColorService colorService;

    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @GetMapping
//    @JsonView(JsonViews.ColorView.class)
    public Respo
}