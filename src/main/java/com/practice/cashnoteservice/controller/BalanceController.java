package com.practice.cashnoteservice.controller;

import com.practice.cashnoteservice.dto.BalanceDto;
import com.practice.cashnoteservice.service.BalanceService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/balance")
public class BalanceController {

    @Autowired
    BalanceService balanceService;

    @GetMapping
    public Page<BalanceDto> getAll(Pageable pageable) {
        return balanceService.getAll(pageable);
    }

    @GetMapping("/{id}")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class),
                    @ApiResponse(code = 404, message = "Not Found", response = ResponseEntity.class),
                    @ApiResponse(code = 400, message = "Bad Request", response = ResponseEntity.class),
                    @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseEntity.class)
            }
    )
    public BalanceDto getById(@PathVariable Long id) throws NotFoundException {
        return balanceService.findById(id);
    }

//    @PostMapping
//    public BalanceDto save(@RequestBody BalanceDto balanceDto) {
//        return balanceService.save(balanceDto);
//    }

    @PutMapping("/{id}")
    public BalanceDto update(Long id, @RequestBody BalanceDto balanceDto) throws NotFoundException {
        return balanceService.update(id, balanceDto);
    }
}
