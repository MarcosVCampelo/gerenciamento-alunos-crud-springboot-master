package br.com.gerenciamento.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

public class PrintServiceTest {

    @Test
    public void testModelAndView() {

        PrintService printService = new PrintService();
        String viewName = "exampleView";
        Object object = new Object(); 
        String objectName = "exampleObject";

        ModelAndView modelAndView = printService.modelAndView(viewName, object, objectName);

        assertNotNull(modelAndView);
        assertEquals(viewName, modelAndView.getViewName());
        assertTrue(modelAndView.getModel().containsKey(objectName));
        assertEquals(object, modelAndView.getModel().get(objectName));
    }
}
