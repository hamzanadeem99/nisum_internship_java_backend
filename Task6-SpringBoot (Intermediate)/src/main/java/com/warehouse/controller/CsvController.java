package com.warehouse.controller;

import com.warehouse.service.CsvService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/csv")
public class CsvController {

    private final CsvService service;

    public CsvController(CsvService service) {
        this.service = service;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadCSV() {
        // If you don't see this in IntelliJ, Postman isn't reaching your code.
        System.out.println("CONTROLLER: /csv/upload hit successfully");

        // We don't need to pass a path because the Service knows to look for "products.csv"
        String result = service.processCSV(null);

        return ResponseEntity.ok(result);
    }
}
