package br.com.gerenciamento.service;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;


@Component
public class PrintService {


        public ModelAndView modelAndView(String viewName, Object object, String objectName) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName(viewName);
            modelAndView.addObject(objectName, object);
            return modelAndView;
        }

    }
