package com.navvis.german;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PhoneNumberServiceImpl implements PhoneNumbersService {

	HashMap<Long, HashSet<String>> set = new HashMap<Long, HashSet<String>>();

	@Override
	public Long store(MultipartFile file) {
		Long uniqueId = new Long(System.currentTimeMillis() & 0xfffffff);
		try {
			if (file.isEmpty()) {
				throw new PhoneNumbersException("Failed to read empty file.");
			}

			List<String> phoneNumbers  = new ArrayList<>();;
			try (BufferedReader buffer = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
				phoneNumbers = buffer.lines()
						.collect(Collectors.toList());
			}
			HashSet<String> list = new HashSet<>();

				// iterating over each number to validate
				for (String phone : phoneNumbers) {
					String number = phone.replaceAll("\\s", "");
					if(!number.isBlank() && !number.isBlank() && number.length()<16) {
					if (isPhoneNumberValid(number)) {
						list.add(number);
					}}
				}

				set.put(uniqueId,list);

		}
		catch (IOException e) {
			throw new PhoneNumbersException("Failed to store file.", e);
		}
		return uniqueId;
	}


	// this method return true if the passed phone number is
	// valid as per the region specified
	public static boolean isPhoneNumberValid(String phone)
	{
		// creating an instance of PhoneNumber Utility class
		PhoneNumberUtil phoneUtil
				= PhoneNumberUtil.getInstance();

		// creating a variable of type PhoneNumber
		PhoneNumber phoneNumber = null;

		try {

			phoneNumber = phoneUtil.parse(phone,"DE");
		}
		catch (NumberParseException e) {

			System.out.println(
					"Unable to parse the given phone number: "
							+ phone);
			e.printStackTrace();
		}

		// return the boolean value of the validation
		// performed
		int length = String.valueOf(phoneNumber.getNationalNumber()).length();
		return phoneNumber.getCountryCode()==49
				&& (phone.startsWith("0049") || phone.startsWith("+49"))
				&& length==11;
	}

	@Override
	public List<Long> loadAll() {
			return set.keySet().stream().collect(Collectors.toList());
	}

	@Override
	public HashSet<String> load(Long taskID) {
		return set.get(taskID);
	}

	@Override
	public void deletePhoneNumbers(Long taskID) {
		if(set.containsKey(taskID))
			set.remove(taskID);
	}

}
