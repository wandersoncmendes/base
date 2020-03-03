package com.base.services.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailContentBuilder {

    private TemplateEngine templateEngine;

    @Autowired
    public MailContentBuilder(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String build(String nome, String senha, String content) {
        Context context = new Context();
        context.setVariable("nome", nome);
        context.setVariable("senha", senha);
        context.setVariable("content", content);

        return templateEngine.process("template", context);
    }

    public String buildLink(String title, String preheader, String nome, String url, String content) {
        Context context = new Context();
        context.setVariable("title", title);
        context.setVariable("preHeader", preheader);
        context.setVariable("nome", nome);
        context.setVariable("url", url);
        context.setVariable("content", content);

        return templateEngine.process("templateLink", context);
    }


}