package com.jss.anagram.misc;

import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 *
 * This class is required to handle a bug with Spring Boot Testing
 * Original Suggestion at https://stackoverflow.com/questions/18813615/how-to-avoid-the-circular-view-path-exception-with-spring-mvc-test
 * @author Jasvinder S Saggu
 */
public class StandaloneMvcTestViewResolver extends InternalResourceViewResolver {

    public StandaloneMvcTestViewResolver() {
        super();
    }

    @Override
    protected AbstractUrlBasedView buildView(final String viewName) throws Exception {
        final InternalResourceView view = (InternalResourceView) super.buildView(viewName);
        // prevent checking for circular view paths
        view.setPreventDispatchLoop(false);
        return view;
    }
}