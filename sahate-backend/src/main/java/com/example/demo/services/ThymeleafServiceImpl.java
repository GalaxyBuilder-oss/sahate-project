package com.dapa.dapa.service;

import java.util.Map;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Service
public class ThymeleafServiceImpl implements ThymeleafService{
    private static final String MAIL_TEMPLATE_NAME_BASE = "mail/MailMessage";

    private static final String MAIL_TEMPLATE_PREFIX = "/templates/";

    private static final String MAIL_TEMPLATE_SUFFIX = ".html";

    private  static final String UTF_8 = "UTF-8";

    private static TemplateEngine templateEngine;

    static {

        templateEngine = emailTemplateEngine();
    };

    @Override
    public String createContext(String template, Map<String, Object> variables) {
       
        final Context context = new Context();
        context.setVariables(variables);
        
        return templateEngine.process(template, context);
    }

    private static TemplateEngine emailTemplateEngine() {
        
        final SpringTemplateEngine templateEngine = new SpringTemplateEngine();

        templateEngine.setTemplateResolver(htmlTemplateResolver());
        templateEngine.setTemplateEngineMessageSource(emailMessageSource());

        return templateEngine;
    }

    private static ITemplateResolver htmlTemplateResolver() {
        
        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix(MAIL_TEMPLATE_PREFIX);
        templateResolver.setSuffix(MAIL_TEMPLATE_SUFFIX);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding(UTF_8);
        templateResolver.setCacheable(false);

        return templateResolver;
    }

    private static ResourceBundleMessageSource emailMessageSource() {
        
        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

        messageSource.setBasename(MAIL_TEMPLATE_NAME_BASE);
        return messageSource;
    }
}
