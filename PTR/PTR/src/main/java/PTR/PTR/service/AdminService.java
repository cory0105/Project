package PTR.PTR.service;

import PTR.PTR.repository.AdminRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
}
