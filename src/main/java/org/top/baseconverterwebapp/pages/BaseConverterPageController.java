package org.top.baseconverterwebapp.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.top.baseconverterwebapp.converter.BaseConverter;

import java.util.List;

@Controller
@RequestMapping("converter")
public class BaseConverterPageController {

    private final BaseConverter converter;

    public BaseConverterPageController(BaseConverter converter) {
        this.converter = converter;
    }

    @GetMapping
    public String form(Model model) {
        List<Integer> bases = converter.supportedBases();
        model.addAttribute("bases", bases);
        return "converter-form";
    }

    @PostMapping
    public String doConvert(@RequestParam("fromBase") Integer fromBase,
                            @RequestParam("toBase") Integer toBase,
                            @RequestParam("value") String value,
                            RedirectAttributes ra) {
        String result;
        try {
            result = converter.convert(fromBase, toBase, value);
        } catch (RuntimeException ex) {
            // по-простому пробрасываем ошибку в UI как текст (как дилетант)
            ra.addFlashAttribute("error", ex.getMessage());
            ra.addFlashAttribute("fromBase", fromBase);
            ra.addFlashAttribute("toBase", toBase);
            ra.addFlashAttribute("value", value);
            return "redirect:/converter";
        }
        ra.addFlashAttribute("result", result);
        ra.addFlashAttribute("fromBase", fromBase);
        ra.addFlashAttribute("toBase", toBase);
        ra.addFlashAttribute("value", value);
        return "redirect:/converter";
    }
}
