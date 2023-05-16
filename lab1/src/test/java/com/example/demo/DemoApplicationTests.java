package com.example.demo;

import com.example.demo.controller.ParamsController;
import com.example.demo.model.ParamsModel;
import com.example.demo.model.ResultModel;
import com.example.demo.service.AccessCounterService;
import com.example.demo.service.ExceptionService;
import com.example.demo.service.ParamsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.Charset;
import java.security.InvalidParameterException;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class DemoApplicationTests {

	@InjectMocks
	private ParamsController paramsController;

	@Mock
	private ParamsService paramsService;

	@Mock
	private AccessCounterService accessCounterService;

	@Mock
	private Map<String, String> mockCache;

	@Mock
	private ParamsModel paramsMock;

	@InjectMocks
	private ExceptionService exceptionService;

	@Test
	public void testCounting() {
		ParamsModel params = new ParamsModel();
		params.setSource("zxcv");
		params.setTarget('c');

		ResultModel expectedResult = new ResultModel();
		expectedResult.setResult(1);

		when(paramsService.getResult(params)).thenReturn(expectedResult);

		ResultModel result = paramsController.counting(params);

		assertEquals(expectedResult, result);
		verify(paramsService).getResult(params);
	}

	@Test
	public void testCorrectParams() throws InvalidParameterException{
		ParamsModel params = new ParamsModel();
		params.setTarget('c');
		params.setSource("zxcv");

		exceptionService.checkExceptions(params);
		assertDoesNotThrow(() -> exceptionService.checkExceptions(params));
	}

	@Test
	public void testIncorrectParams() {
		String randomStringValue = "";
		char randomCharValue = (char) 0;
		int num = new Random().nextInt(2) + 1;
		switch (num)
		{
			case 1:
				when(paramsMock.getSource()).thenReturn(randomStringValue);
				break;
			case 2:
				when(paramsMock.getTarget()).thenReturn(randomCharValue);
				break;
		}

		assertThrows(InvalidParameterException.class, () -> exceptionService.checkExceptions(paramsMock));
	}

}
