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

package org.jboss.aerogear.controller.demo.page.fragment;

import static org.jboss.arquillian.graphene.Graphene.guardHttp;

import java.text.MessageFormat;

import org.apache.commons.lang3.ArrayUtils;
import org.jboss.aerogear.controller.demo.page.dto.NameValuePair;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.enricher.findby.ByJQuery;
import org.jboss.arquillian.graphene.enricher.findby.FindBy;
import org.openqa.selenium.WebElement;

/**
 * Represents a form container. Created in order to avoid writing similar parts of code which handle the forms.
 * 
 * @author <a href="mailto:aemmanou@redhat.com">Tolis Emmanouilidis</a>
 * 
 */
public class FormContainer {

    /**
     * Pattern used to locate an input field by name.
     */
    private static final String inputFieldByNameLocator = "input[name=\"{0}\"]";

    /**
     * Locator for the form.
     */
    @FindBy(tagName = "form")
    private WebElement form;

    /**
     * Locator for the submit button.
     */
    @FindBy(jquery = "input[type=submit]")
    private WebElement submitButton;

    /**
     * Waits until the form is visible.
     */
    public void waitUntilFormIsVisible() {
        Graphene.waitModel().until().element(form).is().visible();
    }

    /**
     * Fills the form dynamically.
     * 
     * @param nameValuePairs The {@link NameValuePair} array which contains the field name and the field value.
     */
    public void fillForm(NameValuePair[] nameValuePairs) {
        if (!ArrayUtils.isEmpty(nameValuePairs)) {
            for (NameValuePair p : nameValuePairs) {
                if (p != null) {
                    final WebElement field = form.findElement(ByJQuery.jquerySelector(MessageFormat.format(
                            inputFieldByNameLocator, p.getName())));
                    clearField(field);
                    fillField(field, p.getValue());
                }
            }
        }
    }

    /**
     * Submits the form by pressing the submit button. The request produced from the button's click is Http and is guarded.
     */
    public void submitFormByButtonHttp() {
        Graphene.waitModel().until().element(submitButton).is().visible();

        guardHttp(submitButton).click();
    }

    /**
     * Fills the form and submits it. The submission ends with an Http request which is guarded.
     * 
     * @param nameValuePairs The {@link NameValuePair} array which contains the field name and the field value.
     */
    public void fillFormAndSubmitHttp(NameValuePair[] nameValuePairs) {
        fillForm(nameValuePairs);
        submitFormByButtonHttp();
    }

    /**
     * Clears a field.
     * 
     * @param field The {@link WebElement} to be cleared.
     */
    public void clearField(WebElement field) {
        field.clear();
    }

    /**
     * Fills a field with a value.
     * 
     * @param field The {@link WebElement} to be filled.
     * @param keys The value.
     */
    public void fillField(WebElement field, String keys) {
        field.sendKeys(keys);
    }
}
