package br.com.gerenciamento.method;


import org.springframework.web.servlet.ModelAndView;

public class Printar{


        public ModelAndView modelAndView(String viewName, Object object, String objectName) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName(viewName);
            modelAndView.addObject(objectName, object);
            return modelAndView;
        }

    }
