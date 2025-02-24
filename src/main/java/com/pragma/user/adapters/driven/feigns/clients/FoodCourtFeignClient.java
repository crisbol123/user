package com.pragma.user.adapters.driven.feigns.clients;




import com.pragma.user.adapters.driven.feigns.FeignClientConfig;
import com.pragma.user.adapters.driven.feigns.dto.NewEmployeeRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "food-court", url = "${food-court.service.url}", configuration = FeignClientConfig.class)
public interface FoodCourtFeignClient {
    @PostMapping("/restaurant/create-employee")
    void createEmployee(@RequestBody NewEmployeeRequest newEmployeeRequest);


}