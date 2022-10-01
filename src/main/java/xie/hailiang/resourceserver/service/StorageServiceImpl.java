package xie.hailiang.resourceserver.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageServiceImpl implements StorageService {
	
	@Value("${storage.service.base-path}")
	private String baseStorageLocation;
	
	private Path fileStorageLocation;

	@Override
	public boolean store(MultipartFile file) throws IOException {
		boolean isStored = false;
		Path targetLocation = fileStorageLocation.resolve(file.getOriginalFilename());
		try {
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			isStored = true;
		} catch (Exception e) {
			isStored = false;
			e.printStackTrace();
			throw new IOException("Failed to store file: " + e);
		}
		return isStored;
	}
	
	@Override
	public boolean createStorageLocationById(Long customerId) throws IOException {
		boolean isCreated = false;
		/**
		ClassPathResource cpr = new ClassPathResource(".");
		fileStorageLocation = Paths.get(cpr.getFile().getAbsolutePath(), "/uploads/files").toAbsolutePath().normalize();
		**/
		try {
			fileStorageLocation = Paths.get(baseStorageLocation, "/" + customerId);
			Files.createDirectories(fileStorageLocation);
			isCreated = true;
		} catch (Exception e) {
			isCreated = false;
			e.printStackTrace();
			throw new IOException("Failed to create path: " + e);
		}
		
		return isCreated;
	}
}
