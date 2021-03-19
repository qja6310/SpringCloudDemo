package cn.com.newloading;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EurekaRibbonApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaRibbonApplication.class,args);
    }
}
