package xie.hailiang.resourceserver.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.FileInputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import xie.hailiang.resourceserver.MyOpenApiCodegenResourceServerApplication;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MyOpenApiCodegenResourceServerApplication.class)
class StorageServiceTest {
	
	@Autowired private StorageService storageService;
	
	@BeforeEach
	void setUpEach() {
	}
	
	@Test
    void test1_givenValidFile_whenUploadFile_returnOk() throws Exception {
		boolean isCreated = storageService.createStorageLocationById(1L);
		assertThat(isCreated).isTrue();
		
		String fileName = "sample-file-mock.txt";
		MockMultipartFile toUploadFile = new MockMultipartFile("file", fileName, null, "This is content.".getBytes());
		boolean isStored = storageService.store(toUploadFile);
		assertThat(isStored).isTrue();
	}
}
