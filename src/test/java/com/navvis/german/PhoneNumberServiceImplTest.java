package com.navvis.german;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/** 
* PhoneNumberServiceImpl Tester. 
*
 *
* @author <Sowmiya>
* @since <pre>Apr 24, 2022</pre> 
* @version 1.0 
*/ 
public class PhoneNumberServiceImplTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
}

private static final PhoneNumberServiceImpl phoneNumberUtil = new PhoneNumberServiceImpl();

/** 
* 
* Method: isPhoneNumberValid(String phone) 
* 
*/ 
@Test
public void testIsPhoneNumberValid() throws Exception { 
    assertTrue(phoneNumberUtil.isPhoneNumberValid("+4612327383733"));
} 

/** 
* 
* Method: loadAll() 
* 
*/ 
@Test
public void testLoadAll() throws Exception { 
    assertEquals(phoneNumberUtil.loadAll().size(),0);
} 

/** 
* 
* Method: load(Long taskID) 
* 
*/ 
@Test
public void testLoad() throws Exception {
    File file = new File("src/test/input.txt");
    FileInputStream input = new FileInputStream(file);
    MultipartFile multipartFile = new MockMultipartFile("file",
            file.getName(), "text/plain", IOUtils.toByteArray(input));
    Long taskId = phoneNumberUtil.store(multipartFile);
    assertEquals(phoneNumberUtil.load(taskId).size(), 6);
} 

/** 
* 
* Method: deletePhoneNumbers(Long taskID) 
* 
*/ 
@Test
public void testDeletePhoneNumbers() throws Exception { 
//TODO: Test goes here... 
} 


} 
