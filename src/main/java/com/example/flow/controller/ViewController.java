package com.example.flow.controller;

import com.example.flow.dto.response.ExtensionResponseDto;
import com.example.flow.service.ExtensionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ViewController {

    private final ExtensionService extensionService;

    @GetMapping()
    public String index(Model model) {
        List<ExtensionResponseDto> allExtension = extensionService.findAllExtension();
        if (allExtension.size() > 0) {
            allExtension.forEach((e) -> {
                model.addAttribute(e.getName(), e.getName());
            });
        }
        return "index";
    }
}
