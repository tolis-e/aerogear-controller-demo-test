/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.aerogear.controller.demo.page;

import org.jboss.aerogear.controller.demo.page.dto.NameValuePair;
import org.jboss.aerogear.controller.demo.page.fragment.FormContainer;
import org.jboss.arquillian.graphene.enricher.findby.FindBy;

/**
 * Represents the login page.
 * 
 * @author <a href="mailto:aemmanou@redhat.com">Tolis Emmanouilidis</a>
 * 
 */
public class LoginPage extends AerogearControllerDemoPage {

    /**
     * Locator for the form container.
     */
    @FindBy(className = "container")
    private FormContainer loginFormContainer;

    /**
     * The name attribute's value for the username field.
     */
    private final String usernameFieldName = "aeroGearUser.username";

    /**
     * The name attribute's value for the password field.
     */
    private final String passwordFieldName = "aeroGearUser.password";

    /**
     * The heading title for the login page.
     */
    private String loginPageHeadingTitle = "Login";

    /**
     * Gets the heading title.
     * 
     * @return heading title
     */
    public String getLoginPageHeadingTitle() {
        return loginPageHeadingTitle;
    }

    /**
     * Waits until the login page is loaded.
     */
    @Override
    public void waitUntilPageIsLoaded() {
        loginFormContainer.waitUntilFormIsVisible();
    }

    /**
     * Fills the login form and submits it. The submission results in an Http request which is guarded.
     * 
     * @param username The username.
     * @param password The password.
     */
    public void loginHttp(String username, String password) {
        final NameValuePair[] nameValuePairs = { new NameValuePair(usernameFieldName, username),
                new NameValuePair(passwordFieldName, password) };
        loginFormContainer.fillFormAndSubmitHttp(nameValuePairs);
    }
}
