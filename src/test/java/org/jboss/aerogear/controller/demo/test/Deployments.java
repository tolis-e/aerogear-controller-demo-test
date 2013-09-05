/**
 * JBoss, Home of Professional Open Source
 * Copyright Red Hat, Inc., and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.aerogear.controller.demo.test;

import java.io.File;

import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;

/**
 * Contains operations related to the deployment procedure.
 */
public class Deployments {

    /**
     * The pom file location.
     */
    private static final String WAR_FILE = "../aerogear-controller-demo/target/aerogear-controller-demo.war";

    /**
     * Creates the archive which will be deployed on the AS.
     * 
     * @return An {@link Archive}
     */
    public static Archive<?> createDeployment() {
        return ShrinkWrap.createFromZipFile(WebArchive.class, new File(WAR_FILE));
    }
}
