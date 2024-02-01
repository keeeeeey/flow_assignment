package com.example.flow.controller;

import com.example.flow.dto.response.ExtensionListResponseDto;
import com.example.flow.service.ExtensionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ViewController {

    private final ExtensionService extensionService;

    @GetMapping()
    public String index(Model model) {
        ExtensionListResponseDto allExtension = extensionService.findAllExtension();
        allExtension.getFixExtensionList().forEach(f -> {
            model.addAttribute(f.getName(), f.getId());
        });
        model.addAttribute("extensionList", allExtension.getExtensionList());
        model.addAttribute("size", allExtension.getExtensionList().size());
        return "index";
    }
}
