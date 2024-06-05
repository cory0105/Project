package PTR.PTR.controller;

import PTR.PTR.service.AdminService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
}
