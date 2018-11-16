package learn.spring5.util.thymeleaf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

import javax.annotation.PostConstruct;

/* Configure Spring resource template resolver bean.
    It's a class that resolves templates using Spring resource resolution mechanism.
    It will find the template in the templates directory when the controller returns the view name.
    (similar to the way view resolver is used with JSP files to resolve views using prefix and suffix )
 */
@Slf4j
@Component
public class DecoupledLogicSetup {

    // == fields ==
    private final SpringResourceTemplateResolver templateResolver;

    // == constructors ==
    public DecoupledLogicSetup(SpringResourceTemplateResolver templateResolver) {
        this.templateResolver = templateResolver;
    }

    // == init ==
    @PostConstruct
    public void init(){
        templateResolver.setUseDecoupledLogic(true);
        log.info("Decoupled template logic enabled");
    }

}
