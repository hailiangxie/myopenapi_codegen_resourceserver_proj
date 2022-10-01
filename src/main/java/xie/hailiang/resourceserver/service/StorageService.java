package xie.hailiang.resourceserver.service;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
	
	boolean store(MultipartFile file) throws IOException;
	
	boolean createStorageLocationById(Long customerId) throws IOException;
}
