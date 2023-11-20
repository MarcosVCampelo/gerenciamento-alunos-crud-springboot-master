package br.com.gerenciamento.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

public class PrintServiceTest {

    @Test
    public void testModelAndView() {
        // Configuração
        PrintService printService = new PrintService();
        String viewName = "exampleView";
        Object object = new Object();  // Pode ser qualquer objeto de exemplo
        String objectName = "exampleObject";

        // Execução
        ModelAndView modelAndView = printService.modelAndView(viewName, object, objectName);

        // Verificações
        assertNotNull(modelAndView);
        assertEquals(viewName, modelAndView.getViewName());
        assertTrue(modelAndView.getModel().containsKey(objectName));
        assertEquals(object, modelAndView.getModel().get(objectName));
    }
}
