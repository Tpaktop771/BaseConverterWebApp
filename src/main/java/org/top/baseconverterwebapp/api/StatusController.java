package org.top.baseconverterwebapp.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.top.baseconverterwebapp.api.ApiMessages.*;

@RestController
@RequestMapping("api")
public class StatusController {

    @GetMapping
    public StringMessage root() { return new StringMessage("server is running"); }

    @GetMapping("ping")
    public StringMessage ping() { return new StringMessage("pong"); }
}
