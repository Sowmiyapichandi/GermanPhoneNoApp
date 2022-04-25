package com.navvis.german;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

public interface PhoneNumbersService {

	Long store(MultipartFile file);

	List<Long> loadAll();

	HashSet<String> load(Long taskID);

	void deletePhoneNumbers(Long taskID);

}
