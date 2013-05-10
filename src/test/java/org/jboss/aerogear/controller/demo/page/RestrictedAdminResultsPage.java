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

import java.text.MessageFormat;

import org.jboss.aerogear.controller.demo.page.fragment.FormContainer;
import org.jboss.arquillian.graphene.enricher.findby.ByJQuery;
import org.jboss.arquillian.graphene.enricher.findby.FindBy;
import org.openqa.selenium.WebElement;

/**
 * Represents the restricted amdin results page.
 * 
 * @author <a href="mailto:aemmanou@redhat.com">Tolis Emmanouilidis</a>
 * 
 */
public class RestrictedAdminResultsPage extends ResultsPage {

    /**
     * Locator for the list of registered users.
     */
    @FindBy(jquery = "div ul")
    private WebElement registeredUsersUL;

    /**
     * Locator for the form container.
     */
    @FindBy(className = "container")
    private FormContainer deleteUserFormContainer;

    /**
     * Format for the href attribute value of the registered user links.
     */
    private static final String registeredUserLinkHrefFormat = "a[href=\"show/{0}\"]";

    /**
     * The heading title for the restricted admin results page.
     */
    private static final String restrictedAdminPageHeadingTitle = "Restricted Admin page";

    /**
     * Gets the page heading title for the Restricted Admin results page.
     * 
     * @return Page heading title
     */
    public String getRestrictedAdminPageHeadingTitle() {
        return restrictedAdminPageHeadingTitle;
    }

    /**
     * Deletes the selected user.
     */
    public void deleteUser() {
        deleteUserFormContainer.submitFormByButtonHttp();
    }

    /**
     * Returns a {@link WebElement} which represents a link of registered user.
     * 
     * @param username The registered user's name.
     * @return a {@link WebElement}
     */
    public WebElement getRegisteredUser(String username) {
        return (registeredUsersUL != null) ? registeredUsersUL.findElement(ByJQuery.jquerySelector(MessageFormat.format(
                registeredUserLinkHrefFormat, username))) : null;
    }
}
